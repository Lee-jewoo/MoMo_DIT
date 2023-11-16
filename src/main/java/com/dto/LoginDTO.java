package com.dto;

public class LoginDTO {
	private String mail1;
	private String mail2;
	private String passwd;

	public String getMail1() {
		return mail1;
	}

	public void setMail1(String mail1) {
		this.mail1 = mail1;
	}

	public String getMail2() {
		return mail2;
	}

	public void setMail2(String mail2) {
		this.mail2 = mail2;
	}

	public String getPassword() {
		return passwd;
	}

	public void setPassword(String password) {
		this.passwd = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [mail1=" + mail1 + ", mail2=" + mail2 + ", passwd=" + passwd + "]";
	}

}