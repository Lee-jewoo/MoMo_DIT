package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("GuestDTO")
public class GuestDTO {

	int guest_id;
	int momo_id;
	String nickname;
	
	public GuestDTO() {}
	public GuestDTO(int guest_id, int momo_id, String nickname) {
		this.guest_id = guest_id;
		this.momo_id = momo_id;
		this.nickname = nickname;
	}
	
	public int getGuest_id() {
		return guest_id;
	}
	public void setGuest_id(int guest_id) {
		this.guest_id = guest_id;
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
	
	@Override
	public String toString() {
		return "GuestDTO [guest_id=" + guest_id + ", momo_id=" + momo_id + ", nickname=" + nickname + "]";
	}
	
}
