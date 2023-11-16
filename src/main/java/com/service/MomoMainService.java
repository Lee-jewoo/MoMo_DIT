package com.service;

import java.util.List;

import com.dto.CategoryDTO;
import com.dto.Criteria;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.MomoMainDTO;
import com.dto.PageDTO;

public interface MomoMainService {

	public List<MomoMainDTO> momoGetList(Criteria cri);

	public int countMomoIdsByCate();
	
	public List<MainboardDTO> selectBoard(PageDTO dto);

}
