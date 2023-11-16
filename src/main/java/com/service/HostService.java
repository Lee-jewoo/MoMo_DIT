package com.service;

import java.util.List;

import com.dto.ApplicantDTO;
import com.dto.CancelDTO;
import com.dto.CategoryDTO;
import com.dto.GuestDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.MemberDTO;

public interface HostService {
	public LocationDTO detail_Location(int loc_num);
	public CategoryDTO detail_Category(int cate_num);
	public List<GuestDTO> detail_Guest(int momo_id);
	public List<CancelDTO> detail_Cancel(int momo_id);
	public List<ApplicantDTO> selectApplicantByMomo_id(int momo_id);
	public MemberDTO detail_Member(String nickname);
	public GuestDTO find_Guest(int guest_id);
	public List<MainboardDTO> management(String nickname);
	public int delete_Guest(int guest_id);
	public int delete_Cancel(int cancel_id);
	public int deleteApplicantById(int applicant_id);
	public int Agree_applicant(int applicant_id, int momo_id, String nickname);
	public int deleteMomo(int momo_id);
	public int updateHeadcount(int momo_id, int headcount,int recruit);
	public int Add_deleteid(String nickname, String email);
	public int delete_Member(String nickname);
}
