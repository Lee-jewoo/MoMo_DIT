package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("CateFilterDTO")
public class CateFilterDTO {

	int cate_num;
	String cate;
	int cate_level;
	int par_cate_num;
	String cate_path;
	// 카테고리 모모 수
	int cateCount;
	
	int loc_num;
	String loc;
	int loc_level;
	int par_loc_num;
	String loc_path;
	// 지역별 모모 수
	int locCount;

	@Override
	public String toString() {
		return "CateFilterDTO [cate_num=" + cate_num + ", cate=" + cate + ", cate_level=" + cate_level
				+ ", par_cate_num=" + par_cate_num + ", cate_path=" + cate_path + ", cateCount=" + cateCount
				+ ", loc_num=" + loc_num + ", loc=" + loc + ", loc_level=" + loc_level + ", par_loc_num=" + par_loc_num
				+ ", loc_path=" + loc_path + ", locCount=" + locCount + "]";
	}

	public CateFilterDTO(int cate_num, String cate, int cate_level, int par_cate_num, String cate_path, int cateCount,
			int loc_num, String loc, int loc_level, int par_loc_num, String loc_path, int locCount) {
		super();
		this.cate_num = cate_num;
		this.cate = cate;
		this.cate_level = cate_level;
		this.par_cate_num = par_cate_num;
		this.cate_path = cate_path;
		this.cateCount = cateCount;
		this.loc_num = loc_num;
		this.loc = loc;
		this.loc_level = loc_level;
		this.par_loc_num = par_loc_num;
		this.loc_path = loc_path;
		this.locCount = locCount;
	}

	public CateFilterDTO() {
	}

	public int getCate_num() {
		return cate_num;
	}

	public void setCate_num(int cate_num) {
		this.cate_num = cate_num;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public int getCate_level() {
		return cate_level;
	}

	public void setCate_level(int cate_level) {
		this.cate_level = cate_level;
	}

	public int getPar_cate_num() {
		return par_cate_num;
	}

	public void setPar_cate_num(int par_cate_num) {
		this.par_cate_num = par_cate_num;
	}

	public String getCate_path() {
		return cate_path;
	}

	public void setCate_path(String cate_path) {
		this.cate_path = cate_path;
	}

	public int getCateCount() {
		return cateCount;
	}

	public void setCateCount(int cateCount) {
		this.cateCount = cateCount;
	}

	public int getLoc_num() {
		return loc_num;
	}

	public void setLoc_num(int loc_num) {
		this.loc_num = loc_num;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public int getLoc_level() {
		return loc_level;
	}

	public void setLoc_level(int loc_level) {
		this.loc_level = loc_level;
	}

	public int getPar_loc_num() {
		return par_loc_num;
	}

	public void setPar_loc_num(int par_loc_num) {
		this.par_loc_num = par_loc_num;
	}

	public String getLoc_path() {
		return loc_path;
	}

	public void setLoc_path(String loc_path) {
		this.loc_path = loc_path;
	}

	public int getLocCount() {
		return locCount;
	}

	public void setLocCount(int locCount) {
		this.locCount = locCount;
	}


}
