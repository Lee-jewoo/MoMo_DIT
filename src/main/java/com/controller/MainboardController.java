package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dto.CancelDTO;
import com.dto.CategoryDTO;
import com.dto.GuestDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.MemberDTO;
import com.service.CancelService;
import com.service.HostService;
import com.service.MainboardService;

@Controller
public class MainboardController {

	@Autowired
	MainboardService service;
	@Autowired
	CancelService cancelservice;
	@Autowired
	HostService hostservice;

	// 지역, 카테고리 데이터, 호스트 닉네임을 모임글 작성 화면으로 전달
	@GetMapping("/momoCreateForm")
	public String momoCreateForm(Model m) {
		List<LocationDTO> locList = service.locList();
		m.addAttribute("locList", locList);
		List<CategoryDTO> cateList = service.cateList();
		m.addAttribute("cateList", cateList);
		return "momoCreateForm";
	}

	// 지역 목록(시/구)을 ajax로 전달
	@PostMapping("/locList2")
	@ResponseBody
	public List<LocationDTO> locList2(@RequestBody int loc_num1) {
		List<LocationDTO> locList2 = service.locList2(loc_num1);
		return locList2;
	}

	// 카테고리 목록(태그)을 ajax로 전달
	@PostMapping("/cateList2")
	@ResponseBody
	public List<CategoryDTO> cateList2(@RequestBody int cate_num1) {
		List<CategoryDTO> cateList2 = service.cateList2(cate_num1);
		return cateList2;
	}

	// 모임글 정보 저장
	@PostMapping("/momoCreate")
	public String momoCreate(MainboardDTO mainboardDTO, @RequestParam("momo_img_input") CommonsMultipartFile file) {

		// 이미지 업로드 및 처리 코드 추가
		if (!file.isEmpty()) {
			// 파일 이름 생성 (파일명 중복 방지를 위해 uuid 삽입)
			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + "_" + file.getOriginalFilename();

			final String endPoint = "https://kr.object.ncloudstorage.com";
			final String regionName = "kr-standard";

			// S3 client
			final AmazonS3 s3 = AmazonS3ClientBuilder.standard() // credentials.properties로 키 등록
					.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
					.withCredentials(
							new ClasspathPropertiesFileCredentialsProvider("com/config/credentials.properties"))
					.build();

			String bucketName = "team6-image";
			String objectName = "momo_img/" + fileName;

			// 파일 업로드
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(file.getContentType()); // 파일 타입에 따라 적절한 값을 설정
			metadata.setContentLength(file.getSize()); // 파일의 길이 (바이트)
			PutObjectRequest request;
			try {
				request = new PutObjectRequest(bucketName, objectName, file.getInputStream(), metadata)
						.withCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(request);
				mainboardDTO.setMomo_img(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // 파일이 없을 때 기본값 설정
			mainboardDTO.setMomo_img("momo_img_default.png");
		}

		// 모임글 저장
		int n = service.momoCreate(mainboardDTO);

		String nickname = null;
		try {
			nickname = URLEncoder.encode(mainboardDTO.getNickname(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "redirect:momoManage?nickname=" + nickname;
	}

	// 취소하려는 참여 목록 DTO, 모모 정보를 받아 취소 화면으로 전달
	@GetMapping("/momoCancelForm")
	public String momoCancelForm(Model m, int guest_id, HttpSession session) {
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		GuestDTO guestDTO = hostservice.find_Guest(guest_id);
		if (guestDTO != null && login.getNickname().equals(guestDTO.getNickname())) {
			m.addAttribute("guestDTO", guestDTO);
			MainboardDTO mainboardDTO = service.momoSelectById(guestDTO.getMomo_id());
			m.addAttribute("mainboardDTO", mainboardDTO);
			return "momoCancelForm";
		} else {
			return "wrongApproach";
		}
	}

	// 취소 테이블에 저장 + 참여 테이블에서 삭제 + 모임 테이블에서 headcount -1 트랜잭션
	@PostMapping("/momoCancel")
	public String momoCancel(CancelDTO cancelDTO, @RequestParam String nickname, @RequestParam int guest_id) {
		int momo_id = cancelDTO.getMomo_id();
		MainboardDTO dto = service.momoSelectById(momo_id);
		int headcount = dto.getHeadcount() - 1;
		dto.setHeadcount(headcount);
		int recruit = dto.getRecruit();
		if (recruit == 1) {
			recruit = 0;
		}
		int n = hostservice.updateHeadcount(momo_id, headcount, recruit);
		n = cancelservice.momoCancel(cancelDTO, guest_id);

		try { // url에 한글이 전달될 때 인코딩
			nickname = URLEncoder.encode(nickname, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "redirect:momoprofile_mymomo";
	}

	// momo_id에 해당하는 mainboardDTO와 지역, 카테고리 목록을 수정 화면에 전달
	@GetMapping("/momoUpdateForm")
	public String momoUpdateForm(@RequestParam int momo_id, Model m, HttpSession session) {
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		MainboardDTO mainboardDTO = service.momoSelectById(momo_id);
		if (mainboardDTO != null && login.getNickname().equals(mainboardDTO.getNickname())) {
			m.addAttribute("mainboardDTO", mainboardDTO);
			List<LocationDTO> locList = service.locList();
			m.addAttribute("locList", locList);
			List<CategoryDTO> cateList = service.cateList();
			m.addAttribute("cateList", cateList);
			return "momoUpdateForm";
		} else {
			return "wrongApproach";
		}
	}

	// 수정 정보 저장
	@PostMapping("/momoUpdate")
	public String momoUpdate(MainboardDTO mainboardDTO, @RequestParam("momo_img_input") CommonsMultipartFile file,
			@RequestParam("momo_img_origin") String momo_img_origin) throws Exception {
		// 이미지 업로드 및 처리 코드 추가
		if (!file.isEmpty()) {
			// 파일 이름 생성 (파일명 중복 방지를 위해 uuid 삽입)
			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + "_" + file.getOriginalFilename();

			final String endPoint = "https://kr.object.ncloudstorage.com";
			final String regionName = "kr-standard";

			// S3 client
			final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
					.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
					.withCredentials(
							new ClasspathPropertiesFileCredentialsProvider("com/config/credentials.properties"))
					.build();

			String bucketName = "team6-image";
			String objectName = "momo_img/" + fileName;

			// 새로운 파일 업로드
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(file.getContentType()); // 파일 타입에 따라 적절한 값을 설정
			metadata.setContentLength(file.getSize()); // 파일의 길이 (바이트)
			PutObjectRequest request;
			try {
				request = new PutObjectRequest(bucketName, objectName, file.getInputStream(), metadata)
						.withCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(request);
				mainboardDTO.setMomo_img(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 수정했을 때 기존 사진 삭제
			try {
				if (!momo_img_origin.equals("momo_img_default.png")) {
					objectName = "momo_img/" + momo_img_origin;
					s3.deleteObject(bucketName, objectName);
				}
			} catch (AmazonS3Exception e) {
				e.printStackTrace();
			} catch (SdkClientException e) {
				e.printStackTrace();
			}
		} else { // 새로운 파일이 없을 때 기존 파일명 저장
			mainboardDTO.setMomo_img(momo_img_origin);
		}

		int n = service.momoUpdate(mainboardDTO);
		// url에 한글이 전달될 때 인코딩
		String nickname = null;
		try {
			nickname = URLEncoder.encode(mainboardDTO.getNickname(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "redirect:momoManage?nickname=" + nickname;
	}

}
