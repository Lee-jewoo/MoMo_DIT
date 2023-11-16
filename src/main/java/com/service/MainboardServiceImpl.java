package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MainboardDAO;
import com.dto.ApplicantDTO;
import com.dto.CategoryDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.PageDTO;

@Service
public class MainboardServiceImpl implements MainboardService {
	
	@Autowired
	MainboardDAO dao;

	@Override
	public int momoCreate(MainboardDTO dto) {
		return dao.momoCreate(dto);
	}
	
	@Override
	public List<LocationDTO> locList() {
		return dao.locList();
	}
	@Override
	public List<LocationDTO> locList2(int loc_num1) {
		return dao.locList2(loc_num1);
	}

	@Override
	public List<CategoryDTO> cateList() {
		return dao.cateList();
	}
	
	@Override
	public List<CategoryDTO> cateList2(int cate_num1) {
		return dao.cateList2(cate_num1);
	}

	@Override
	public MainboardDTO momoSelectById(int momo_id) {
		return dao.momoSelectById(momo_id);
	}

	@Override
	public int momoUpdate(MainboardDTO dto) {
		return dao.momoUpdate(dto);
	}

	@Override
	public int momoApplicant(ApplicantDTO dto) {
		return dao.momoApplicant(dto);
	}

	@Override
	public PageDTO momoSelectByCate(int curPage, int par_cate_num, HashMap<String, Object> map) {
		return dao.momoSelectByCate(curPage, par_cate_num, map);
	}
	
}
