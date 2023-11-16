package com.service;

import java.util.HashMap;
import java.util.List;

import com.dto.ApplicantDTO;
import com.dto.CategoryDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.PageDTO;

public interface MainboardService {
	
	public int momoCreate (MainboardDTO dto);
	
	public List<LocationDTO> locList ();
	
	public List<LocationDTO> locList2 (int loc_num1);
	
	public List<CategoryDTO> cateList ();

	public List<CategoryDTO> cateList2 (int cate_num1);
	
	public MainboardDTO momoSelectById(int momo_id);
	
	public int momoUpdate (MainboardDTO dto);
	
	public int momoApplicant (ApplicantDTO dto);

	public PageDTO momoSelectByCate (int curPage, int par_cate_num, HashMap<String, Object> map);
	
}
