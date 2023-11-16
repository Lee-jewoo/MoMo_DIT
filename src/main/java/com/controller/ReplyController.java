package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.dto.ReplyDTO;
import com.service.HostService;
import com.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	@Autowired
	private HostService hostService;

	// 댓글 등록
	@PostMapping("/addReply")
	@ResponseBody
	public String addReply(@ModelAttribute ReplyDTO replyDTO) {
		try {
	        int n = replyService.addReply(replyDTO);
	        return "success"; // AJAX 요청에 대한 성공 응답
	    } catch (Exception e) {
	        return "fail"; // AJAX 요청에 대한 실패 응답
	    }
	}
	
	// 댓글 조회
	@GetMapping("/getAllReplies")
    @ResponseBody
    public List<Map<String, Object>> getAllReplies() {
		List<Map<String, Object>> replyMapList = new ArrayList<Map<String,Object>>();
		List<ReplyDTO> replyList = replyService.getAllReplies();
		for(int num=0; num<replyList.size(); num++) {
			ReplyDTO replyDTO = replyList.get(num);
			Map<String, Object> replyMap = new HashMap<String, Object>();
			String nickname = replyDTO.getNickname();
			MemberDTO memberDTO = hostService.detail_Member(nickname);
			replyMap.put("replyDTO", replyDTO);
			replyMap.put("memberDTO", memberDTO);
			replyMapList.add(replyMap);
		}
        return replyMapList;
    }

	// 댓글 수정
	@PostMapping("/updateReply")
	@ResponseBody
	public String updateReply(@ModelAttribute ReplyDTO replyDTO) {
	    try {
	        replyService.updateReply(replyDTO);
	        return "success";
	    } catch (Exception e) {
	        return "fail";
	    }
	}

	// 댓글 삭제
	@PostMapping("/deleteReply")
	@ResponseBody
	public String deleteReply(@RequestParam("id") int reply_id) {
	    try {
	        replyService.deleteReplyById(reply_id);
	        return "success";
	    } catch (Exception e) {
	        return "fail";
	    }
	}
	
}