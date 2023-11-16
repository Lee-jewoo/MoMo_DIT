package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class MemberDTO {
	
	String nickname;
	String email;
	String passwd;
	int phone;
	String gender;
	String memberIntro;
	String profile_img;
	String birth;
	
	public MemberDTO() {}

	public MemberDTO(String nickname, String email, String passwd, int phone, String gender, String memberIntro,
			String profile_img, String birth) {
		this.nickname = nickname;
		this.email = email;
		this.passwd = passwd;
		this.phone = phone;
		this.gender = gender;
		this.memberIntro = memberIntro;
		this.profile_img = profile_img;
		this.birth = birth;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMemberIntro() {
		return memberIntro;
	}

	public void setMemberIntro(String memberIntro) {
		this.memberIntro = memberIntro;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "MemberDTO [nickname=" + nickname + ", email=" + email + ", passwd=" + passwd + ", phone=" + phone
				+ ", gender=" + gender + ", memberIntro=" + memberIntro + ", profile_img=" + profile_img + ", birth="
				+ birth + "]";
	}
	
}
