<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.dto.LoginDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/top.css" />
<link rel="stylesheet" href="css/login.css" />

<div class="login-container">
	<form action="loginA" method="post">
		<!-- action을 /loginA으로 설정 -->
		<div class="input-container">
			<label for="email" id="email">E-mail</label> <span> <input
				type="text" name="mail1" id="mail1" placeholder="aaa"> @ <input
				type="text" name="mail2" id="mail2" list="domains"
				placeholder="도메인을 선택하세요" required>
			</span> <br>
			<datalist id="domains">
				<option value="gmail.com">gmail.com</option>
				<option value="naver.com">naver.com</option>
				<option value="daum.net">daum.net</option>
				<option value="yahoo.com">yahoo.com</option>
				<option value="">직접입력</option>
			</datalist>
		</div>
		<div class="input-container">
			<label for="password">비밀번호</label> <input type="password"
				name="passwd" id="password" placeholder="비밀번호" style="width: 300px;">
		</div>
		<input type="submit" class="login-button" value="로그인"><br>
		<br>
	</form>
	<button class="login-button" onClick="location.href='findpassword'">비밀번호찾기</button>
</div>

