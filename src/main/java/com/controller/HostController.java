package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.dto.ApplicantDTO;
import com.dto.CancelDTO;
import com.dto.CategoryDTO;
import com.dto.GuestDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.MemberDTO;
import com.service.HostService;
import com.service.MainboardService;

@Controller
public class HostController {

	@Autowired
	HostService service;
	@Autowired
	MainboardService mainboardService;

	@GetMapping("/momoDetail_info") // 모임글 상세보기 - 정보
	public String momoDetail_info(@RequestParam int momo_id, Model m, HttpSession session) {
		MainboardDTO mdto;
		LocationDTO ldto;
		CategoryDTO cdto;
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		mdto = mainboardService.momoSelectById(momo_id);
		if (mdto != null && login.getNickname().equals(mdto.getNickname())) {
			int loc_num = mdto.getLoc_num();
			int cate_num = mdto.getCate_num();
			ldto = service.detail_Location(loc_num);
			cdto = service.detail_Category(cate_num);
			m.addAttribute("momo_id", momo_id);
			m.addAttribute("mdto", mdto);
			m.addAttribute("ldto", ldto);
			m.addAttribute("cdto", cdto);
			return "detail_info";
		} else {
			return "wrongApproach";
		}
	}

	@GetMapping("/momoDetail_manage") // 모임글 상세보기 - 신청관리
	public String momoDetail_manage(@RequestParam int momo_id, Model m, HttpSession session) {
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		MainboardDTO mainboarddto = mainboardService.momoSelectById(momo_id);
		if (mainboarddto != null && login.getNickname().equals(mainboarddto.getNickname())) {
			// 모임글 상세보기 - 신청관리 - 참여자 목록
			List<GuestDTO> gList = service.detail_Guest(momo_id);
			List<Map<String, Object>> gMap_list = new ArrayList<Map<String, Object>>();
			for (int num = 0; num < gList.size(); num++) {
				Map<String, Object> map = new HashMap<String, Object>();
				GuestDTO dto = gList.get(num);
				String nickname = dto.getNickname();
				MemberDTO mdto = service.detail_Member(nickname);
				String profile_img = mdto.getProfile_img();
				String gender = mdto.getGender();
				int guest_id = dto.getGuest_id();
				map.put("nickname", nickname);
				map.put("profile_img", profile_img);
				map.put("gender", gender);
				map.put("guest_id", guest_id);
				map.put("momo_id", momo_id);
				gMap_list.add(map);
			}

			// 모임글 상세보기 - 신청관리 - 신청자 목록
			List<ApplicantDTO> aList = service.selectApplicantByMomo_id(momo_id);
			List<Map<String, Object>> aMap_list = new ArrayList<Map<String, Object>>();
			for (int num = 0; num < aList.size(); num++) {
				Map<String, Object> map = new HashMap<String, Object>();
				ApplicantDTO dto = aList.get(num);
				String nickname = dto.getNickname();
				MemberDTO memdto = service.detail_Member(nickname);
				MainboardDTO maindto = mainboardService.momoSelectById(momo_id);
				String profile_img = memdto.getProfile_img();
				String gender = memdto.getGender();
				String reason1 = dto.getReason1();
				String reason2 = dto.getReason2();
				int applicant_id = dto.getApplicant_id();
				int headcount = maindto.getHeadcount();
				int maxHeadcount = maindto.getMaxHeadcount();
				map.put("nickname", nickname);
				map.put("profile_img", profile_img);
				map.put("gender", gender);
				map.put("reason1", reason1);
				map.put("reason2", reason2);
				map.put("applicant_id", applicant_id);
				map.put("headcount", headcount);
				map.put("maxHeadcount", maxHeadcount);
				aMap_list.add(map);
			}

			// 모임글 상세보기 - 신청관리 - 신청취소자 목록
			List<CancelDTO> cList = service.detail_Cancel(momo_id);
			List<Map<String, Object>> cMap_list = new ArrayList<Map<String, Object>>();
			for (int num = 0; num < cList.size(); num++) {
				Map<String, Object> map = new HashMap<String, Object>();
				CancelDTO dto = cList.get(num);
				String reason1 = dto.getReason1();
				String reason2 = dto.getReason2();
				int cancel_id = dto.getCancel_id();
				map.put("reason1", reason1);
				map.put("reason2", reason2);
				map.put("cancel_id", cancel_id);
				cMap_list.add(map);
			}
			m.addAttribute("momo_id", momo_id);
			m.addAttribute("gMap_list", gMap_list);
			m.addAttribute("aMap_list", aMap_list);
			m.addAttribute("cMap_list", cMap_list);
			m.addAttribute("dto", mainboarddto);
			return "detail_manage";
		} else {
			return "wrongApproach";
		}
	}

	@GetMapping("/momoManage") // 호스트 화면 본문 (모모 관리)
	public String manage(Model m, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
		List<MainboardDTO> list = service.management(memberDTO.getNickname());
		m.addAttribute("list", list);
		return "manage";
	}

	@GetMapping("/banishGuest") // 모임글 상세보기 - 신청관리 추방ajax
	public String banishGuest(@RequestParam int guest_id, @RequestParam int momo_id) {
		MainboardDTO dto = mainboardService.momoSelectById(momo_id);
		int headcount = dto.getHeadcount() - 1;
		int recruit = dto.getRecruit();
		if (recruit == 1) {
			recruit = 0;
		}
		int n = service.updateHeadcount(momo_id, headcount, recruit);
		n = service.delete_Guest(guest_id);
		return "detail_manage";
	}

	@GetMapping("/agreeApplicant") // 모임글 상세보기 - 신청관리 신청동의 ajax
	public String agreeApplicant(@RequestParam String nickname, @RequestParam int applicant_id,
			@RequestParam int momo_id) {
		MainboardDTO dto = mainboardService.momoSelectById(momo_id);
		int headcount = dto.getHeadcount() + 1;
		int recruit = dto.getRecruit();
		if (recruit == 0) {
			recruit = 1;
		}
		int n = service.updateHeadcount(momo_id, headcount, recruit);
		n = service.Agree_applicant(applicant_id, momo_id, nickname);
		return "detail_manage";
	}

	@GetMapping("/disagreeApplicant") // 모임글 상세보기 - 신청관리 신청거부 ajax
	public String disagreeApplicant(@RequestParam int applicant_id) {
		int n = service.deleteApplicantById(applicant_id);
		return "detail_manage";

	}

	@GetMapping("/cancelGuest") // 모임글 상세보기 - 신청관리 신청취소 확인 ajax
	public String cancelGuest(@RequestParam int cancel_id) {
		int n = service.delete_Cancel(cancel_id);
		return "detail_manage";

	}

	@GetMapping("/deleteMomo") // 호스트 화면 본문 (모모 관리) 모임글 삭제 ajax
	public String deleteMomo(@RequestParam int momo_id) {
		final String endPoint = "https://kr.object.ncloudstorage.com";
		final String regionName = "kr-standard";

		// S3 client
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new ClasspathPropertiesFileCredentialsProvider("com/config/credentials.properties"))
				.build();

		String bucketName = "team6-image";

		MainboardDTO dto = mainboardService.momoSelectById(momo_id);
		String objectName = "momo_img/" + dto.getMomo_img();
		try {
			if (!dto.getMomo_img().equals("momo_img_default.png")) {
				s3.deleteObject(bucketName, objectName);
			}
		} catch (AmazonS3Exception e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		}
		int n = service.deleteMomo(momo_id);
		return "manage";

	}
}
