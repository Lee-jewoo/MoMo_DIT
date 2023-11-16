package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MoMoMainDTO")
public class MomoMainDTO {
	int momo_id;
	String nickname;
	int cate_num;
	int loc_num;
	String momoName;
	String momoIntro;;
	String momo_img;
	int headcount;
	int maxHeadcount;
	int recruit;
	int status;
	int mark_id;
	int mark_check;
	
	public MomoMainDTO() {
	}
	public MomoMainDTO(int momo_id, String nickname, int cate_num, int loc_num, String momoName, String momoIntro,
			String momo_img, int headcount, int maxHeadcount, int recruit, int status) {
		this.momo_id = momo_id;
		this.nickname = nickname;
		this.cate_num = cate_num;
		this.loc_num = loc_num;
		this.momoName = momoName;
		this.momoIntro = momoIntro;
		this.momo_img = momo_img;
		this.headcount = headcount;
		this.maxHeadcount = maxHeadcount;
		this.recruit = recruit;
		this.status = status;
	}
	@Override
	public String toString() {
		return "MomoMainDTO [momo_id=" + momo_id + ", nickname=" + nickname + ", cate_num=" + cate_num + ", loc_num="
				+ loc_num + ", momoName=" + momoName + ", momoIntro=" + momoIntro + ", momo_img=" + momo_img
				+ ", headcount=" + headcount + ", maxHeadcount=" + maxHeadcount + ", recruit=" + recruit + ", status="
				+ status + "]";
	}
	public int getMomo_id() {
		return momo_id;
	}
	public void setMomo_id(int momo_id) {
		this.momo_id = momo_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getCate_num() {
		return cate_num;
	}
	public void setCate_num(int cate_num) {
		this.cate_num = cate_num;
	}
	public int getLoc_num() {
		return loc_num;
	}
	public void setLoc_num(int loc_num) {
		this.loc_num = loc_num;
	}
	public String getMomoName() {
		return momoName;
	}
	public void setMomoName(String momoName) {
		this.momoName = momoName;
	}
	public String getMomoIntro() {
		return momoIntro;
	}
	public void setMomoIntro(String momoIntro) {
		this.momoIntro = momoIntro;
	}
	public String getMomo_img() {
		return momo_img;
	}
	public void setMomo_img(String momo_img) {
		this.momo_img = momo_img;
	}
	public int getHeadcount() {
		return headcount;
	}
	public void setHeadcount(int headcount) {
		this.headcount = headcount;
	}
	public int getMaxHeadcount() {
		return maxHeadcount;
	}
	public void setMaxHeadcount(int maxHeadcount) {
		this.maxHeadcount = maxHeadcount;
	}
	public int getRecruit() {
		return recruit;
	}
	public void setRecruit(int recruit) {
		this.recruit = recruit;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
