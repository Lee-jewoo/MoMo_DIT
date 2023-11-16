package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.HostDAO;
import com.dto.ApplicantDTO;
import com.dto.CancelDTO;
import com.dto.CategoryDTO;
import com.dto.GuestDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.MemberDTO;

@Service
public class HostServiceImpl implements HostService {
	
	@Autowired
	HostDAO dao;
	
	@Override
	public LocationDTO detail_Location(int loc_num) {
		LocationDTO dto=dao.detail_Location(loc_num);
		return dto;
	}
	@Override
	public CategoryDTO detail_Category(int cate_num) {
		CategoryDTO dto = dao.detail_Category(cate_num);
		return dto;
	}
	@Override
	public List<GuestDTO> detail_Guest(int momo_id) {
		List<GuestDTO> list =dao.detail_Guest(momo_id);
		return list;
	}
	@Override
	public List<CancelDTO> detail_Cancel(int momo_id) {
		List<CancelDTO> list = dao.detail_Cancel(momo_id);
		return list;
	}
	@Override
	public List<ApplicantDTO> selectApplicantByMomo_id(int momo_id) {
		List<ApplicantDTO> list = dao.selectApplicantByMomo_id(momo_id);
		return list;
	}
	@Override
	public MemberDTO detail_Member(String nickname) {
		MemberDTO dto  = dao.detail_Member(nickname);
		return dto;
	}
	@Override
	public GuestDTO find_Guest(int guest_id) {
		GuestDTO dto = dao.find_Guest(guest_id);
		return dto;
	}
	@Override
	public List<MainboardDTO> management(String nickname) {
		List<MainboardDTO> list=dao.management(nickname);
		return list;
	}
	@Override
	public int delete_Guest(int guest_id) {
		int n = dao.delete_Guest(guest_id);
		return n;
	}
	@Override
	public int delete_Cancel(int cancel_id) {
		int n= dao.delete_Cancel(cancel_id);
		return n;
	}
	@Override
	public int deleteApplicantById(int applicant_id) {
		int n=dao.deleteApplicantById(applicant_id);
		return n;
	}
	@Transactional
	@Override
	public int Agree_applicant(int applicant_id, int momo_id, String nickname) {
		int n=dao.add_Guest(momo_id, nickname);
		n=dao.deleteApplicantById(applicant_id);
		return n;
	}
	@Override
	public int deleteMomo(int momo_id) {
		int n = dao.deleteMomo(momo_id);
		return n;
	}
	@Override
	public int updateHeadcount(int momo_id, int headcount,int recruit) {
		int n = dao.updateHeadcount(momo_id, headcount,recruit);
		return n;
	}
	@Override
	public int Add_deleteid(String nickname,String email) {
		return dao.Add_deleteid(nickname,email);
	}
	@Override
	public int delete_Member(String nickname) {
		return dao.delete_Member(nickname);
	}
	
}
