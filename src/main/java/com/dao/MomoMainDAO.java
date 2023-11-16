package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CateFilterDTO;
import com.dto.Criteria;
import com.dto.MainboardDTO;
import com.dto.MomoMainDTO;
import com.dto.PageDTO;

@Repository
public class MomoMainDAO {

    @Autowired
    SqlSessionTemplate session;
    
    //momo 목록
  	public List<MainboardDTO> selectBoard(PageDTO dto){
  		return session.selectList("MainboardMapper.selectBoard", dto);
  	};
  	
  	public int countMomoIdsByCate(){
  		return session.selectOne("MainboardMapper.countMomoIdsByCate");
  	};
  	/* 카테고리 리스트 */
  	
  	
  	/* 검색 대상 카테고리 리스트 */
  	public String[] getCateList(Criteria cri){
  		return null;
  	};
  	
  	/* 카테고리 정보(+검색대상 갯수) */
  	public CateFilterDTO getCateInfo(Criteria cri){
  		return null;
  	}

}