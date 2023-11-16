package com.dto;

public class RegistDTO {
    private String mail1;
    private String mail2;
    private String passwd;
    private String passwd2;
    private boolean checkedPasswd;
    private String nickname;
    private boolean checkedNickname;
    private String gender;
    private String birth;
    private String phone;
    private String memberintro;
    private String profile_img;
    private String email;
    
	public RegistDTO() {}
    
	public String getEmail() {
		return email;
	}
	public String getMail1() {
		return mail1;
	}
	public void setMail1(String mail1) {
		this.mail1 = mail1;
	}
	public String getMail2() {
		return mail2;
	}
	public void  setMail2(String mail2) {
		this.mail2 = mail2;
	}

	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String password) {
		this.passwd = password;
	}
	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}
	public boolean isCheckedPasswd() {
		return checkedPasswd;
	}
	public void setCheckedPasswd(boolean checkedPasswd) {
		this.checkedPasswd = checkedPasswd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean isCheckedNickname() {
		return checkedNickname;
	}
	public void setCheckedNickname(boolean checkedNickname) {
		this.checkedNickname = checkedNickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMemberintro() {
		return memberintro;
	}
	public void setMemberintro(String memberintro) {
		this.memberintro = memberintro;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@Override
	public String toString() {
		return "RegistDTO [mail1=" + mail1 + ", mail2=" + mail2 + ", passwd=" + passwd + ", passwd2=" + passwd2
				+ ", checkedPasswd=" + checkedPasswd + ", nickname=" + nickname + ", checkedNickname=" + checkedNickname
				+ ", gender=" + gender + ", birth=" + birth + ", phone=" + phone + ", memberintro=" + memberintro + ", profile_img="
				+ profile_img + ", email=" + email + "]";
	}

	public RegistDTO(String mail1, String mail2, String passwd, String passwd2, boolean checkedPasswd, String nickname,
			boolean checkedNickname, String gender, String birth, String phone, String memberintro, String profile_img,
			String email) {
		super();
		this.mail1 = mail1;
		this.mail2 = mail2;
		this.passwd = passwd;
		this.passwd2 = passwd2;
		this.checkedPasswd = checkedPasswd;
		this.nickname = nickname;
		this.checkedNickname = checkedNickname;
		this.gender = gender;
		this.birth = birth;
		this.phone = phone;
		this.memberintro = memberintro;
		this.profile_img = profile_img;
		this.email = email;
	}


	

    
    
}