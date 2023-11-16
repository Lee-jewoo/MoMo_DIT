package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MainboardDTO")
public class MainboardDTO {
	
	int momo_id;
	String nickname;
	int cate_num;
	int loc_num;
	String momoName;
	String momoIntro;
	String momoDetail;
	String momoLoc;
	String momoDate;
	String fee;
	String materials;
	String momo_img;
	int headcount;
	int maxHeadcount;
	int recruit;
	int status;
	String writeDate;
	String fixDate;
	String momoTime;
	String question1;
	String question2;
	
	public MainboardDTO() {}

	public MainboardDTO(int momo_id, String nickname, int cate_num, int loc_num, String momoName, String momoIntro,
			String momoDetail, String momoLoc, String momoDate, String fee, String materials, String momo_img,
			int headcount, int maxHeadcount, int recruit, int status, String writeDate, String fixDate, String momoTime,
			String question1, String question2) {
		this.momo_id = momo_id;
		this.nickname = nickname;
		this.cate_num = cate_num;
		this.loc_num = loc_num;
		this.momoName = momoName;
		this.momoIntro = momoIntro;
		this.momoDetail = momoDetail;
		this.momoLoc = momoLoc;
		this.momoDate = momoDate;
		this.fee = fee;
		this.materials = materials;
		this.momo_img = momo_img;
		this.headcount = headcount;
		this.maxHeadcount = maxHeadcount;
		this.recruit = recruit;
		this.status = status;
		this.writeDate = writeDate;
		this.fixDate = fixDate;
		this.momoTime = momoTime;
		this.question1 = question1;
		this.question2 = question2;
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

	public String getMomoDetail() {
		return momoDetail;
	}

	public void setMomoDetail(String momoDetail) {
		this.momoDetail = momoDetail;
	}

	public String getMomoLoc() {
		return momoLoc;
	}

	public void setMomoLoc(String momoLoc) {
		this.momoLoc = momoLoc;
	}

	public String getMomoDate() {
		return momoDate;
	}

	public void setMomoDate(String momoDate) {
		this.momoDate = momoDate;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
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

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getFixDate() {
		return fixDate;
	}

	public void setFixDate(String fixDate) {
		this.fixDate = fixDate;
	}

	public String getMomoTime() {
		return momoTime;
	}

	public void setMomoTime(String momoTime) {
		this.momoTime = momoTime;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	@Override
	public String toString() {
		return "MainboardDTO [momo_id=" + momo_id + ", nickname=" + nickname + ", cate_num=" + cate_num + ", loc_num="
				+ loc_num + ", momoName=" + momoName + ", momoIntro=" + momoIntro + ", momoDetail=" + momoDetail
				+ ", momoLoc=" + momoLoc + ", momoDate=" + momoDate + ", fee=" + fee + ", materials=" + materials
				+ ", momo_img=" + momo_img + ", headcount=" + headcount + ", maxHeadcount=" + maxHeadcount
				+ ", recruit=" + recruit + ", status=" + status + ", writeDate=" + writeDate + ", fixDate=" + fixDate
				+ ", momoTime=" + momoTime + ", question1=" + question1 + ", question2=" + question2 + "]";
	}

	
}
