package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ApplicantDTO")
public class ApplicantDTO {
	int applicant_id;
	int momo_id;
	String nickname;
	String reason1;
	String reason2;
	
	public ApplicantDTO() {}
	public ApplicantDTO(int applicant_id, int momo_id, String nickname, String reason1, String reason2) {
		this.applicant_id = applicant_id;
		this.momo_id = momo_id;
		this.nickname = nickname;
		this.reason1 = reason1;
		this.reason2 = reason2;
	}
	
	public int getApplicant_id() {
		return applicant_id;
	}
	public void setApplicant_id(int applicant_id) {
		this.applicant_id = applicant_id;
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
	public String getReason1() {
		return reason1;
	}
	public void setReason1(String reason1) {
		this.reason1 = reason1;
	}
	public String getReason2() {
		return reason2;
	}
	public void setReason2(String reason2) {
		this.reason2 = reason2;
	}

}
