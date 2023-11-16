package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.RegistDTO;
@Repository
public class RegistDAO {

    @Autowired
    private SqlSession Session;

    
    public int saveUser(RegistDTO dto){
    	return Session.insert("RegistMapper.saveUser", dto);
    }
    public List<String> Deleteid_list(String nickname){
    	return Session.selectList("DeleteidMapper.Deleteid_list",nickname);
    }
    public List<String> Deleteid_list_byEmail(String email){
    	return Session.selectList("DeleteidMapper.Deleteid_list_byEmail",email);
    }
}