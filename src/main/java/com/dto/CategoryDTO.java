package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("CategoryDTO")
public class CategoryDTO {

	int cate_num;
	String cate;
	int cate_level;
	int par_cate_num;
	String cate_path;
	
	public CategoryDTO() {}

	public CategoryDTO(int cate_num, String cate, int cate_level, int par_cate_num, String cate_path) {
		this.cate_num = cate_num;
		this.cate = cate;
		this.cate_level = cate_level;
		this.par_cate_num = par_cate_num;
		this.cate_path = cate_path;
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

	@Override
	public String toString() {
		return "CategoryDTO [cate_num=" + cate_num + ", cate=" + cate + ", cate_level=" + cate_level + ", par_cate_num="
				+ par_cate_num + ", cate_path=" + cate_path + "]";
	}	
	
}
