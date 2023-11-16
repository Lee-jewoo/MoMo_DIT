package com.service;

import java.util.List;

import com.dto.GuestDTO;

public interface GuestService {
	
	public List<GuestDTO> guestList(String nickname);
	
}
