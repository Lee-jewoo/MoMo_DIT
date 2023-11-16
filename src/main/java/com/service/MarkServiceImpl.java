package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MarkDAO;
import com.dto.MarkDTO;

@Service
public class MarkServiceImpl implements MarkService {
	
	@Autowired
	MarkDAO dao;

	@Override // 찜 목록 조회
	public List<MarkDTO> markList(String nickname) {
		return dao.markList(nickname);
	}

	@Override // 찜 버튼을 누르면 찜 목록에 추가
	public int markAdd(MarkDTO markDTO) {
		return dao.markAdd(markDTO);
	}

	@Override // 찜 취소
	public int markDelete(int mark_id) {
		return dao.markDelete(mark_id);
	}
	@Override
	public MarkDTO findMarkByMomoIDandNickname(int momo_id, String nickname) {
		
		return dao.findMarkByMomoIDandNickname(momo_id, nickname);
	}
}
