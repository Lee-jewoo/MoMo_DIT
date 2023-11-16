package com.controller;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ApplicantDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.MemberDTO;
import com.service.ApplicantService;
import com.service.HostService;
import com.service.MainboardService;


@Controller
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;
    
    @Autowired
	MainboardService mainboardService;
    
    @Autowired
    HostService hostService;

    // HTTP GET 요청 처리 메서드 - 모임글 상세보기
    @GetMapping("/retrieve")
    public String retrieve(HttpSession session, Model model, @RequestParam int momo_id) {
        MainboardDTO mainboardDTO = mainboardService.momoSelectById(momo_id);
        if (mainboardDTO != null) {
	        model.addAttribute("mainboardDTO", mainboardDTO);
	        String nickname = mainboardDTO.getNickname();
	        MemberDTO memberDTO = hostService.detail_Member(nickname);
	        model.addAttribute("memberDTO", memberDTO);
	        LocationDTO ldto;
	        int loc_num = mainboardDTO.getLoc_num();
			ldto = hostService.detail_Location(loc_num);
			model.addAttribute("ldto", ldto);
	        return "retrieve";
        } else {
			return "wrongApproach";
		}
    }
    
	// 신청 정보를 applicant 테이블에 저장
	@PostMapping("/momoApplicant")
	public String momoApplicant(ApplicantDTO applicantDTO) {
		int n = mainboardService.momoApplicant(applicantDTO);
		return "redirect:/";
	}
    
	// 신청 정보를 확인하는 HTTP POST 요청 처리 메서드
    @PostMapping("/checkApplicant")
    @ResponseBody
    public ResponseEntity<?> checkApplicantStatus(@RequestParam("momo_id") int momo_id, 
            									  @RequestParam("nickname") String nickname) {
    	try {
            String status = applicantService.checkApplication(momo_id, nickname);
            return ResponseEntity.ok(Collections.singletonMap("status", status));
        } catch (Exception e) {
            // 예외 처리 로직
            // 존재하지 않는 사용자/모임 ID로 인한 예외 처리 등
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                                 .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    // 신청 페이지에 접속했을 때, 해당 사용자가 호스트인지 확인하는 기능을 추가합니다.
    @GetMapping("/momoApplicantForm")
    public String momoApplicantForm(Model m, @RequestParam int momo_id, HttpSession session) {
    	MainboardDTO mainboardDTO = mainboardService.momoSelectById(momo_id);
    	if (mainboardDTO != null) {
    		// 신청하려는 모임 정보를 모델에 저장
    		m.addAttribute("mainboardDTO", mainboardDTO);
    		// 신청 페이지에 접속 시 해당 사용자가 호스트인지 확인합니다.
    		MemberDTO memberDTO = (MemberDTO) session.getAttribute("login");
    		String nickname = memberDTO.getNickname();
    		boolean isHost = applicantService.isHost(momo_id, nickname);
    		m.addAttribute("isHost", isHost);
    		return "momoApplicantForm";
    	} else {
    		return "wrongApproach";
    	}
    }
}
