package com.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.LoginDAO;
import com.dto.MemberDTO;

@Service
public class LoginService {

	@Autowired
    private LoginDAO loginDAO;
	
	public boolean authenticateUser(String email, String password) {
		 return loginDAO.authenticateUser(email, password);
    }
	
	// 로그인 한 회원의 닉네임 받기
	public MemberDTO nicknameByLogin(HashMap<String, String> map) {
    	return loginDAO.nicknameByLogin(map);
	}
	
	// 비밀번호 찾기
	public int changePassword(String email,int ran) {
		return loginDAO.changePassword(email, ran);
	}
    public MemberDTO findEmail(String email){
    	MemberDTO dto = loginDAO.findEmail(email);
    	return dto;
    }
	
}