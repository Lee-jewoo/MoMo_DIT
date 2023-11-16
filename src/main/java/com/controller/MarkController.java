package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MarkDTO;
import com.dto.MemberDTO;
import com.service.MainboardService;
import com.service.MarkService;

@Controller
public class MarkController {
	
	@Autowired
	MarkService markService;
    
	 @GetMapping("/markAdd")
		@ResponseBody
		public String markAdd(HttpSession session, @RequestParam int momo_id) {
	    	MemberDTO memberDTO = (MemberDTO)session.getAttribute("login");
	    	String nickname=memberDTO.getNickname();
	    	MarkDTO markDTO=markService.findMarkByMomoIDandNickname(momo_id,nickname);
	    	if(markDTO==null) {
	    		MarkDTO mdto=new MarkDTO();
	    		mdto.setMark_check(1);
	    		mdto.setMomo_id(momo_id);
	    		mdto.setNickname(nickname);
	    		int n = markService.markAdd(mdto);
	    		return "success";
	    	}
	    	else {
	    		return "fail";
	    	}
		}
	    
	    // 찜취소
	    @GetMapping("/markDelete")
	    @ResponseBody
	    public String markDelete(HttpSession session, @RequestParam int momo_id) {
	    	MemberDTO memberDTO = (MemberDTO)session.getAttribute("login");
	    	String nickname=memberDTO.getNickname();
	    	MarkDTO markDTO=markService.findMarkByMomoIDandNickname(momo_id,nickname);
	    	if(markDTO!=null) {
	    	int n= markService.markDelete(markDTO.getMark_id());
			return "success";
	    	}
	    	else {
	    		return "fail";
	    	}
	    }
}