package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RegistDAO;
import com.dto.RegistDTO;

@Service
public class RegistService {

    @Autowired
    private RegistDAO registDAO;

    public int saveUser(RegistDTO dto) {

        return registDAO.saveUser(dto);
    }
    public List<String> Deleteid_list(String nickname){
    	return registDAO.Deleteid_list(nickname);
    }
    public List<String> Deleteid_list_byEmail(String email){
    	return registDAO.Deleteid_list_byEmail(email);
    }
}