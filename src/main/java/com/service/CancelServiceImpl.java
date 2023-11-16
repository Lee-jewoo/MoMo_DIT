package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CancelDAO;
import com.dto.CancelDTO;

@Service
public class CancelServiceImpl implements CancelService {
	
	@Autowired
	CancelDAO dao;

	@Transactional
	@Override
	public int momoCancel(CancelDTO dto, int guest_id) {
		int n = dao.deleteGuest(guest_id);
		n = dao.momoCancel(dto); // dao.decreaseHeadcount(momo_id);
		return n;
	}

	
}
