package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MomoMainDAO;
import com.dto.Criteria;
import com.dto.MainboardDTO;
import com.dto.MomoMainDTO;
import com.dto.PageDTO;

@Service
public class MomoMainServiceImpl implements MomoMainService{

	@Autowired
	MomoMainDAO dao;

	@Override
	public int countMomoIdsByCate(){
		return dao.countMomoIdsByCate();
	}

	@Override
	public List<MomoMainDTO> momoGetList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MainboardDTO> selectBoard(PageDTO dto) {
		return dao.selectBoard(dto);
	}

}
