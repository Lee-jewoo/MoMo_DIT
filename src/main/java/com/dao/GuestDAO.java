package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.GuestDTO;

@Repository
public class GuestDAO {

    @Autowired
    SqlSessionTemplate session;
    
    public List<GuestDTO> guestList(String nickname) {
    	return session.selectList("GuestMapper.guestList", nickname);
    }
    
}