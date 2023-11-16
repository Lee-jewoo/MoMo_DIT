package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ApplicantDTO;
import com.dto.CategoryDTO;
import com.dto.LocationDTO;
import com.dto.MainboardDTO;
import com.dto.PageDTO;

@Repository
public class MainboardDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	public int momoCreate (MainboardDTO dto) {
		return session.insert("MainboardMapper.momoCreate", dto);
	}
	
	public List<LocationDTO> locList () {
		return session.selectList("LocationMapper.locList");
	}
	
	public List<LocationDTO> locList2 (int loc_num1) {
		return session.selectList("LocationMapper.locList2", loc_num1);
	}
	
	public List<CategoryDTO> cateList () {
		return session.selectList("CategoryMapper.cateList");
	}
	
	public List<CategoryDTO> cateList2 (int cate_num1) {
		return session.selectList("CategoryMapper.cateList2", cate_num1);
	}
	
	public MainboardDTO momoSelectById(int momo_id) {
		return session.selectOne("MainboardMapper.momoSelectById", momo_id);
	}
	
	public int momoUpdate (MainboardDTO dto) {
		return session.update("MainboardMapper.momoUpdate", dto);
	}
	
	public int momoApplicant (ApplicantDTO dto) {
		return session.insert("ApplicantMapper.momoApplicant", dto);
	}
	
	public List<MainboardDTO> momoSelectByCate (int par_cate_num) {
		return session.selectList("MainboardMapper.momoSelectByCate", par_cate_num);
	}
	
	public PageDTO momoSelectByCate(int curPage, int par_cate_num, HashMap<String, Object> map) {
		PageDTO pagedto = new PageDTO();
		
		int offset = (curPage-1)*pagedto.getPerPage();
		int limit = pagedto.getPerPage();

		List<MainboardDTO> list = session.selectList("MainboardMapper.momoSelectByCate", map, new RowBounds(offset, limit));
		
		for (int i=0; i<list.size(); i++) {
			int momo_id = list.get(i).getMomo_id();
			session.update("MainboardMapper.updateStatus", momo_id);
		}
		list = session.selectList("MainboardMapper.momoSelectByCate", map, new RowBounds(offset, limit));
		pagedto.setList(list);
		pagedto.setCurPage(curPage);
		
		int totalRecord = 0;
		if (map.get("searchValue")==null) {
			totalRecord = session.selectOne("MainboardMapper.totalRecord", par_cate_num);
		} else {
			totalRecord = session.selectOne("MainboardMapper.totalRecordSearch", map);
		}
		pagedto.setTotalRecord(totalRecord);
		pagedto.setSearchName((String)map.get("searchName"));
		pagedto.setSearchValue((String)map.get("searchValue"));
		pagedto.setCate_num((Integer)map.get("cate_num"));
		pagedto.setLoc_num((Integer)map.get("loc_num"));
		
		return pagedto;
	}

}
