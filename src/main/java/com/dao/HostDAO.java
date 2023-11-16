package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ApplicantDTO;
import com.dto.CancelDTO;
import com.dto.CategoryDTO;
import com.dto.GuestDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.MemberDTO;

@Repository
public class HostDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	public LocationDTO detail_Location(int loc_num){
		LocationDTO dto = session.selectOne("LocationMapper.detail_Location", loc_num);
		return dto;
	}
	public CategoryDTO detail_Category(int cate_num){
		CategoryDTO dto = session.selectOne("CategoryMapper.detail_Category", cate_num);
		return dto;
	}
	public List<GuestDTO> detail_Guest(int momo_id) {
		List<GuestDTO> list = session.selectList("GuestMapper.detail_Guest", momo_id);
		return list;
	}
	public List<CancelDTO> detail_Cancel(int momo_id) {
		List<CancelDTO> list = session.selectList("CancelMapper.detail_Cancel", momo_id);
		return list;
	}
	public List<ApplicantDTO> selectApplicantByMomo_id(int momo_id) {
		List<ApplicantDTO> list = session.selectList("ApplicantMapper.selectApplicantByMomo_id", momo_id);
		return list;
	}
	public MemberDTO detail_Member(String nickname){
		MemberDTO dto = session.selectOne("MemberMapper.detail_Member", nickname);
		return dto;
	}
	public GuestDTO find_Guest(int guest_id) {
		GuestDTO dto =session.selectOne("GuestMapper.find_Guest", guest_id);
		return dto;
	}
	public List<MainboardDTO> management(String nickname) {
		List<MainboardDTO> list = session.selectList("MainboardMapper.management",nickname);
		return list;
	}
	public int delete_Guest(int guest_id) {
		int n=session.delete("GuestMapper.delete_Guest",guest_id);
		return n;
	}
	public int add_Guest(int momo_id,String nickname) {
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("momo_id",momo_id);
		map.put("nickname",nickname);
		int n=session.insert("GuestMapper.add_Guest",map);
		return n;
	}
	public int deleteApplicantById(int applicant_id) {
		int n=session.delete("ApplicantMapper.deleteApplicantById",applicant_id);
		return n;
	}
	public int delete_Cancel(int cancel_id) {
		int n=session.delete("CancelMapper.delete_Cancel",cancel_id);
		return n;
	}
	public int deleteMomo(int momo_id) {
		int n=session.delete("MainboardMapper.deleteMomo",momo_id);
		return n;
	}
	public int updateHeadcount(int momo_id,int headcount,int recruit) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("momo_id",momo_id);
		map.put("headcount",headcount);
		map.put("recruit",recruit);
		int n=session.update("MainboardMapper.updateHeadcount",map);
		return n;
	}
    public int Add_deleteid(String nickname,String email) {
    	HashMap<String, String> map=new HashMap<String, String>();
    	map.put("nickname",nickname);
    	map.put("email",email);
    	
    	return session.insert("DeleteidMapper.Add_deleteid", map);
    }
    public int delete_Member(String nickname) {
    	return session.delete("MemberMapper.delete_Member",nickname);
    }
}
