package com.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MarkDTO;

@Repository
public class MarkDAO {

    @Autowired
    SqlSessionTemplate session;
    
    public List<MarkDTO> markList(String nickname) {
    	return session.selectList("MarkMapper.markList", nickname);
    }
    
    public int markAdd (MarkDTO markDTO) {
    	return session.insert("MarkMapper.markAdd", markDTO);
    }
    
    public int markDelete (int mark_id) {
    	return session.insert("MarkMapper.markDelete", mark_id);
    }
    public MarkDTO findMarkByMomoIDandNickname(int momo_id,String nickname) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("momo_id",momo_id);
    	map.put("nickname",nickname);
    	return session.selectOne("MarkMapper.findMarkByMomoIDandNickname",map);
    }
}