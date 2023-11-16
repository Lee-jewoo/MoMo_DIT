package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ApplicantDTO;
import com.dto.GuestDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.MarkDTO;
import com.dto.MemberDTO;
import com.service.ApplicantService;
import com.service.GuestService;
import com.service.HostService;
import com.service.MainboardService;
import com.service.MarkService;

@Controller
public class ProfileController {

	@Autowired
	MarkService markService;
	@Autowired
	GuestService guestService;
	@Autowired
	MainboardService mainboardService;
	@Autowired
	HostService hostService;
	@Autowired
	ApplicantService applicantService;

	// 프로필 화면 - 찜 목록 조회 / 참여 목록 조회 / 로그인 한 사용자 정보는 session에 저장 (key값은 login)
	@GetMapping("/momoprofile_mymomo")
	public String markList(HttpSession session, Model m) {
		// 세션에서 사용자 닉네임을 받아 찜한 목록 조회
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		String nickname = login.getNickname();

		List<MarkDTO> markList = markService.markList(nickname);
		// 찜한 목록의 momo_id로 해당하는 모모를 조회 -> 모델에 list로 저장
		List<MainboardDTO> markMainboardList = new ArrayList<MainboardDTO>();
		for (int i = 0; i < markList.size(); i++) { // 가져온 markList에서 momo_id에 해당하는 mainboardDTO를 List에 저장
			int momo_id = markList.get(i).getMomo_id();
			MainboardDTO mainboardDTO = mainboardService.momoSelectById(momo_id);
			if (mainboardDTO.getStatus() == 0) {
				markMainboardList.add(mainboardDTO);
			}
		}
		m.addAttribute("markMainboardList", markMainboardList);

		// 참여 목록의 momo_id로 해당하는 모모를 조회 -> 모델에 list로 저장
		List<GuestDTO> guestList = guestService.guestList(nickname);
		List<HashMap<String, Object>> profileList = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < guestList.size(); i++) { // 가져온 guestList에서 momo_id에 해당하는 mainboardDTO를 List에 저장
			HashMap<String, Object> profileMap = new HashMap<String, Object>();
			int momo_id = guestList.get(i).getMomo_id();
			MainboardDTO mainboardDTO = mainboardService.momoSelectById(momo_id);
			if (mainboardDTO.getStatus() == 0) {
				profileMap.put("momoName", mainboardDTO.getMomoName()); // 모임글 정보 저장
				LocationDTO locDTO = hostService.detail_Location(mainboardDTO.getLoc_num());
				String loc_path = locDTO.getLoc_path(); // 지역 정보 저장
				profileMap.put("loc_path", loc_path);
				profileMap.put("momo_id", mainboardDTO.getMomo_id());
				profileMap.put("momoDate", mainboardDTO.getMomoDate());
				profileMap.put("headcount", mainboardDTO.getHeadcount());
				profileMap.put("maxHeadcount", mainboardDTO.getMaxHeadcount());
				int guest_id = guestList.get(i).getGuest_id();
				profileMap.put("guest_id", guest_id);
				profileList.add(profileMap);
			}
		}
		m.addAttribute("profileList", profileList);

		// 참여 신청한 모모 -> 모델에 list로 저장
		List<HashMap<String, Object>> aMap_list = new ArrayList<HashMap<String, Object>>();
		List<ApplicantDTO> applicantList = applicantService.findApplicantbyNickname(nickname);
		for (int i = 0; i < applicantList.size(); i++) {
			HashMap<String, Object> aMap = new HashMap<String, Object>();
			ApplicantDTO applicantDTO = applicantList.get(i);
			int momo_id = applicantDTO.getMomo_id();
			MainboardDTO mainboardDTO = mainboardService.momoSelectById(momo_id);
			LocationDTO locationDTO = hostService.detail_Location(mainboardDTO.getLoc_num());
			if (mainboardDTO.getStatus() == 0) {
				aMap.put("applicant_id", applicantDTO.getApplicant_id());
				aMap.put("momo_id", mainboardDTO.getMomo_id());
				aMap.put("momoName", mainboardDTO.getMomoName());
				aMap.put("loc_path", locationDTO.getLoc_path());
				aMap.put("momoDate", mainboardDTO.getMomoDate());
				aMap.put("headcount", mainboardDTO.getHeadcount());
				aMap.put("maxHeadcount", mainboardDTO.getMaxHeadcount());
				aMap_list.add(aMap);
			}
		}
		m.addAttribute("aMap_list", aMap_list);

		return "profile_mymomo";
	}

	@GetMapping("/cancelApplicant")
	@ResponseBody
	public void cancelApplicant(int applicant_id) {
		int n = hostService.deleteApplicantById(applicant_id);
	}

}