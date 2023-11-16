package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.CategoryDTO;
import com.dto.LocationDTO;
import com.dto.MarkDTO;
import com.dto.MemberDTO;
import com.dto.PageDTO;
import com.service.MainboardService;
import com.service.MarkService;
import com.service.MomoMainService;

@Controller
public class MomoMainController {
	@Autowired
	MomoMainService momoMainService;
	@Autowired
	MainboardService service;
	@Autowired
	MarkService markService;

	@GetMapping("/main") // 카테고리 메인
	public String mainGET(
			@RequestParam(value = "par_cate_num", required = false, defaultValue = "1000") int par_cate_num,
			Model model, HttpSession session, @RequestParam(defaultValue = "1") int curPage,
			@RequestParam(value="searchName" , required=false) String searchName, 
			@RequestParam(value="searchValue" , required=false) String searchValue,
			@RequestParam(value="cate_num" , required=false) Integer cate_num,
			@RequestParam(value="loc_num" , required=false) Integer loc_num) {

		List<LocationDTO> locList = service.locList();
		model.addAttribute("locList", locList);

		List<CategoryDTO> cateList = service.cateList();
		model.addAttribute("cateList", cateList);

		model.addAttribute("par_cate_num", par_cate_num);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);
		map.put("par_cate_num", par_cate_num);
		map.put("cate_num", cate_num);
		map.put("loc_num", loc_num);

		PageDTO pageDTO = service.momoSelectByCate(curPage, par_cate_num, map);
		model.addAttribute("mainboardList", pageDTO);
		
		// 세션에서 사용자 닉네임을 받아 찜한 목록 조회
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		if (login != null) {
			String nickname = login.getNickname();
			List<MarkDTO> markList = markService.markList(nickname);
			List<Integer> idList=new ArrayList<Integer>();
			for(int i=0;i<markList.size();i++) {
				MarkDTO dto =markList.get(i);
				idList.add(dto.getMomo_id());
			}
			model.addAttribute("idList", idList);
		}
		return "main";
	}
	
//	@GetMapping("/main")
//	public String boardList(PageDTO dto, Model model, @RequestParam(value="nowPage", required=false)String nowPage,
//			@RequestParam(value="cntPerPage", required=false)String cntPerPage) {
//		int total = momoMainService.countMomoIdsByCate();
//		if (nowPage == null && cntPerPage == null) {
//			nowPage = "1";
//			cntPerPage = "5";
//		} else if (nowPage == null) {
//			nowPage = "1";
//		} else if (cntPerPage == null) { 
//			cntPerPage = "5";
//		}		
//		dto = new PageDTO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
//		model.addAttribute("paging", dto);
//		model.addAttribute("viewAll", momoMainService.selectBoard(dto));
//		return "main";
//		
//	}
	
}
