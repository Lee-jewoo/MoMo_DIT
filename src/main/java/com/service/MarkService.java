package com.service;

import java.util.List;

import com.dto.MarkDTO;

public interface MarkService {
	
	public List<MarkDTO> markList(String nickname);
	
	public int markAdd (MarkDTO markDTO);
	
	public int markDelete (int mark_id);
	
	public MarkDTO findMarkByMomoIDandNickname(int momo_id,String nickname);
}
