package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ApplicantDTO;
import com.dto.MainboardDTO;

@Repository
public class ApplicantDAO {

    @Autowired
    private SqlSessionTemplate sqlSession; 
    
    public ApplicantDTO findByNickname(String nickname) {
        return sqlSession.selectOne("ApplicantMapper.selectApplicantByNickname", nickname);
    }
    
    public List<ApplicantDTO> findApplicantbyNickname(String nickname) {
    	return sqlSession.selectList("ApplicantMapper.selectApplicantByNickname", nickname);
    }
    
    // 기존 findByNickname 메서드를 사용하여 특정 모임에 대한 신청 여부도 확인할 수 있도록 확장
    public ApplicantDTO findByMomoIdAndNickname(int momoId, String nickname) {
        Map<String, Object> params = new HashMap<>();
        params.put("momo_id", momoId);
        params.put("nickname", nickname);
        return sqlSession.selectOne("ApplicantMapper.selectApplicantByMomoIdAndNickname", params);
    }
    
    public String checkApplication(int momo_id, String nickname) {
        // MyBatis의 파라미터 맵
        Map<String, Object> params = new HashMap<>();
        params.put("momo_id", momo_id);
        params.put("nickname", nickname);

        // 호스트 여부를 확인하는 쿼리 실행
        Integer hostCount = sqlSession.selectOne("ApplicantMapper.selectHostCount", params);
        // 게스트 여부를 확인하는 쿼리 실행
        Integer guestCount = sqlSession.selectOne("ApplicantMapper.selectGuestCount", params);

        // 결과에 따라 상태 반환
     // 참여자 여부 확인
        Integer applicantCount = sqlSession.selectOne("ApplicantMapper.selectApplicantCount", params);

        // 결과에 따라 상태 반환
        if (hostCount != null && hostCount > 0) {
            return "host";
        } else if (guestCount != null && guestCount > 0) {
            return "guest";
        } else if (applicantCount != null && applicantCount > 0){
        	return "applicant";
        } else {
            return "not_applied";
        }
    }

    //  호스트 여부를 확인하는 메서드
    public int selectHostCount(int momo_id, String nickname) {
        Map<String, Object> params = new HashMap<>();
        params.put("momo_id", momo_id);
        params.put("nickname", nickname);
        return sqlSession.selectOne("ApplicantMapper.selectHostCount", params);
    }
    
    
    
}