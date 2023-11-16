package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("PageDTO")
public class PageDTO {
	
	List<MainboardDTO> list;
	int perPage = 5;
	int curPage;
	int totalRecord;
	String searchName;
	String searchValue;
	Integer cate_num;
	Integer loc_num;
	public PageDTO() {}
	public PageDTO(List<MainboardDTO> list, int perPage, int curPage, int totalRecord, String searchName,
			String searchValue, Integer cate_num, Integer loc_num) {
		this.list = list;
		this.perPage = perPage;
		this.curPage = curPage;
		this.totalRecord = totalRecord;
		this.searchName = searchName;
		this.searchValue = searchValue;
		this.cate_num = cate_num;
		this.loc_num = loc_num;
	}
	public List<MainboardDTO> getList() {
		return list;
	}
	public void setList(List<MainboardDTO> list) {
		this.list = list;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public Integer getCate_num() {
		return cate_num;
	}
	public void setCate_num(Integer cate_num) {
		this.cate_num = cate_num;
	}
	public Integer getLoc_num() {
		return loc_num;
	}
	public void setLoc_num(Integer loc_num) {
		this.loc_num = loc_num;
	}
	@Override
	public String toString() {
		return "PageDTO [list=" + list + ", perPage=" + perPage + ", curPage=" + curPage + ", totalRecord="
				+ totalRecord + ", searchName=" + searchName + ", searchValue=" + searchValue + ", cate_num=" + cate_num
				+ ", loc_num=" + loc_num + "]";
	}
	
	
	
}