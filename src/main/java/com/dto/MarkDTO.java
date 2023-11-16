package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MarkDTO")
public class MarkDTO {

	int mark_id;
	int momo_id;
	String nickname;
	int mark_check;
	
	public MarkDTO() {
	}
	public MarkDTO(int mark_id, int momo_id, String nickname, int mark_check) {
		this.mark_id = mark_id;
		this.momo_id = momo_id;
		this.nickname = nickname;
		this.mark_check = mark_check;
	}
	
	public int getMark_id() {
		return mark_id;
	}
	public void setMark_id(int mark_id) {
		this.mark_id = mark_id;
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
	public int getMark_check() {
		return mark_check;
	}
	public void setMark_check(int mark_check) {
		this.mark_check = mark_check;
	}
	
}
