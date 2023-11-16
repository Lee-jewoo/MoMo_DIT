package com.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository
public class ProfileEditDAO {

    @Autowired
    private SqlSession Session;

    public MemberDTO getMemberInfoByNickname(String nickname) {
        return Session.selectOne("ProfileEditMapper.getMemberInfoByNickname", nickname);
    }
    public void changeProfile(MemberDTO memberdto) {
		Session.update("ProfileEditMapper.changeProfile" , memberdto);
	}
}