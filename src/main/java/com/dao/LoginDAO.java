package com.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository
public class LoginDAO {

    @Autowired
    private SqlSessionTemplate session;
    
    public boolean authenticateUser(String email, String password) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("passwd", password);

    	int result = session.selectOne("MemberMapper.authenticateUser", parameters);
    	return result >= 1;
    }
    
    // 로그인 한 회원의 dto 받기
    public MemberDTO nicknameByLogin(HashMap<String, String> map) {
    	return session.selectOne("MemberMapper.nicknameByLogin", map);
    }
    
    // 비밀번호 찾기
    public MemberDTO findEmail(String email){
    	MemberDTO dto = session.selectOne("MemberMapper.findEmail",email);
    	return dto;
    }
    public int changePassword(String email,int ran) {
    	Map<String,Object> parameters=new HashMap();
    	   parameters.put("email", email);
           parameters.put("passwd", ran);
    	int n= session.update("MemberMapper.changePassword",parameters);
    	return n;
    	
    }
    
}