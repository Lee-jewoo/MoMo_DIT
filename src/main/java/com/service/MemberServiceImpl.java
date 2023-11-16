package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProfileEditDAO;
import com.dto.MemberDTO;

@Service	
public class MemberServiceImpl implements MemberService{
	@Autowired
	ProfileEditDAO dao;

	@Override
	public MemberDTO getMemberInfoByNickname(String nickname) {
	
		 return dao.getMemberInfoByNickname(nickname);
	}

	@Override
	public void changeProfile(MemberDTO memberdto) {
		dao.changeProfile(memberdto);
	}

//	@Override
//	public int modifySave(ProfileEditDTO dto) {
//		int result = 0;
//		
//		try {
//			result = dao.modifySave(dto);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
}
