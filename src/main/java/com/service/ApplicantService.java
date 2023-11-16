package com.service;

import com.dto.ApplicantDTO;
import com.dto.MainboardDTO;
import com.dao.ApplicantDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantDAO applicantDAO; // ApplicantDAO 클래스의 인스턴스를 주입받습니다.

	public ApplicantDTO getApplicantByNickname(String nickname) {
		return applicantDAO.findByNickname(nickname);
	}

	// 해당 모임에 대한 신청자가 호스트인지 확인하는 메서드
	public boolean isHost(int momo_id, String nickname) {
		int count = applicantDAO.selectHostCount(momo_id, nickname);
		return count > 0;
	}

	public String checkApplication(int momo_id, String nickname) {
        return applicantDAO.checkApplication(momo_id, nickname);
    }
	
	public List<ApplicantDTO> findApplicantbyNickname(String nickname){
		return applicantDAO.findApplicantbyNickname(nickname);
	}
}