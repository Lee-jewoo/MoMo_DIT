package com.service;

import com.dto.MemberDTO;

public interface MemberService {

	//s내 정보 조회
	public MemberDTO getMemberInfoByNickname(String nickname);
	
	public void changeProfile(MemberDTO memberdto);
//	public int modifySave(ProfileEditDTO dto);

}
