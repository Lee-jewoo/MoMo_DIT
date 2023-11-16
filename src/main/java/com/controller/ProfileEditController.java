package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.dao.CheckedNicknameDAO;
import com.dto.ApplicantDTO;
import com.dto.GuestDTO;
import com.dto.MainboardDTO;
import com.dto.MarkDTO;
import com.dto.MemberDTO;
import com.dto.ReplyDTO;
import com.service.ApplicantService;
import com.service.GuestService;
import com.service.HostService;
import com.service.MarkService;
import com.service.MemberService;
import com.service.ReplyService;

@Controller
public class ProfileEditController {

	@Autowired
	private MemberService ms;
	@Autowired
	private CheckedNicknameDAO dao;
	@Autowired
	private HostService hostservice;
	@Autowired
	private GuestService guestservice;
	@Autowired
	private ApplicantService applicantservice;
	@Autowired
	private MarkService markservice;
	@Autowired
	private ReplyService replyservice;

	@GetMapping("/momoMyInfo")
	public String showUserInfo(HttpSession session, Model model) {
		try {
			MemberDTO login = (MemberDTO) session.getAttribute("login");
			String nickname = login.getNickname();
			model.addAttribute("info", ms.getMemberInfoByNickname(nickname));
			return "profile_myInfo";
		} catch (Exception e) {
			e.printStackTrace();
			return "wrongApproach";
		}
	}

	@RequestMapping(value = "/login/idcheck2.action", method = { RequestMethod.GET })
	@ResponseBody
	public int idcheck(HttpServletRequest req, HttpServletResponse resp, String nickname, MemberDTO memberDTO) {
		System.out.println("닉네임 체크");
		int result = dao.idcheck(nickname);
		return result;
	}

	// 회원 정보 수정
	@PostMapping("/myInfoModify")
	public String myInfoModify(@RequestParam String mail1, @RequestParam String passwd2, @RequestParam String mail2,
			MemberDTO memberDTO, @RequestParam("profile_img_input") CommonsMultipartFile profile_img,
			@RequestParam String profile_img_origin) {
		memberDTO.setEmail(mail1 + "@" + mail2);
		String passwd = memberDTO.getPasswd();
		if (passwd.length() <= 9
				|| !passwd.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^!&*])[A-Za-z\\d@#$%^!&*]{9,}$")) {
			// 비밀번호가 9자 이하이거나 조건에 맞지 않을 때 처리
			return "momo/passwdFail2";
		}
		if (!profile_img.isEmpty()) {
			// 파일 이름 생성 (파일명 중복 방지를 위해 uuid 삽입)
			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + "_" + profile_img.getOriginalFilename();

			final String endPoint = "https://kr.object.ncloudstorage.com";
			final String regionName = "kr-standard";

			// S3 client
			final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
					.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
					.withCredentials(
							new ClasspathPropertiesFileCredentialsProvider("com/config/credentials.properties"))
					.build();

			String bucketName = "team6-image";
			String objectName = "profile_img/" + fileName;

			// 새로운 파일 업로드
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(profile_img.getContentType()); // 파일 타입에 따라 적절한 값을 설정
			metadata.setContentLength(profile_img.getSize()); // 파일의 길이 (바이트)
			PutObjectRequest request;
			try {
				request = new PutObjectRequest(bucketName, objectName, profile_img.getInputStream(), metadata)
						.withCannedAcl(CannedAccessControlList.PublicRead);
				s3.putObject(request);
				memberDTO.setProfile_img(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 수정했을 때 기존 사진 삭제
			try {
				if (!profile_img_origin.equals("profile_img_default.png")) {
					objectName = "profile_img/" + profile_img_origin;
					s3.deleteObject(bucketName, objectName);
				}
			} catch (AmazonS3Exception e) {
				e.printStackTrace();
			} catch (SdkClientException e) {
				e.printStackTrace();
			}
		} else { // 새로운 파일이 없을 때 기존 파일명 저장
			memberDTO.setProfile_img(profile_img_origin);
		}
		ms.changeProfile(memberDTO);
		return "redirect:momoMyInfo";
	}

	// 회원 정보 삭제
	@GetMapping("deleteID")
	@ResponseBody
	public String deleteID(@RequestParam String nickname, HttpSession session) {
		List<GuestDTO> glist = guestservice.guestList(nickname);
		for (GuestDTO dto : glist) {
			hostservice.delete_Guest(dto.getGuest_id());
		}
		List<ApplicantDTO> alist = applicantservice.findApplicantbyNickname(nickname);
		for (ApplicantDTO dto : alist) {
			hostservice.deleteApplicantById(dto.getApplicant_id());
		}
		List<MarkDTO> mlist = markservice.markList(nickname);
		for (MarkDTO dto : mlist) {
			markservice.markDelete(dto.getMark_id());
		}
		List<ReplyDTO> rlist = replyservice.selectReplyByNickname(nickname);
		for (ReplyDTO dto : rlist) {
			replyservice.deleteReplyById(dto.getReply_id());
		}
		List<MainboardDTO> mainlist = hostservice.management(nickname);
		for (MainboardDTO dto : mainlist) {
			hostservice.deleteMomo(dto.getMomo_id());
		}
		MemberDTO memdto = hostservice.detail_Member(nickname);
		hostservice.Add_deleteid(nickname, memdto.getEmail());
		// 프로필사진 삭제
		final String endPoint = "https://kr.object.ncloudstorage.com";
		final String regionName = "kr-standard";

		// S3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new ClasspathPropertiesFileCredentialsProvider("com/config/credentials.properties"))
				.build();

		String bucketName = "team6-image";
		String objectName = "profile_img/" + memdto.getProfile_img();
		try {
			if (!memdto.getProfile_img().equals("profile_img_default.png")) {
				s3.deleteObject(bucketName, objectName);
			}
		} catch (AmazonS3Exception e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
		hostservice.delete_Member(nickname);
		session.invalidate();
		return "success";
	}

}
