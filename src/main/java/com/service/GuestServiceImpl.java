package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.GuestDAO;
import com.dto.GuestDTO;

@Service
public class GuestServiceImpl implements GuestService {
	
	@Autowired
	GuestDAO dao;

	@Override
	public List<GuestDTO> guestList(String nickname) {
		return dao.guestList(nickname);
	}

}
