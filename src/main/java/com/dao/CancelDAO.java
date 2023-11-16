package com.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CancelDTO;

@Repository
public class CancelDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	public int momoCancel(CancelDTO dto) {
		return session.insert("CancelMapper.momoCancel", dto);
	}
	
	public int deleteGuest(int guest_id) {
		return session.delete("GuestMapper.delete_Guest", guest_id);
	}
	
	public int decreaseHeadcount(int momo_id) {
		return session.update("MainboardMapper.decreaseHeadcount", momo_id);
	}

}
