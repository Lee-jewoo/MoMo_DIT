package com.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CheckedNicknameDAO {

    @Autowired
    private SqlSessionTemplate session;

    public int idcheck(String nickname) {
    	
    	System.out.println(nickname);
    	
        return session.selectOne("CheckedNicknameMapper.idcheck", nickname);
    }
}