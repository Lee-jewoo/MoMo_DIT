<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/top.css" />

<c:if test="${empty login}">
	<div class="logo">
		<div class="user">
			<p><a class="logo_momo" href="/momo">MoMo</a></p>
			<div class="ButtonsFrame">
			    <div class="signUpButton"><a href="regist">SignUp</a></div>
			    <div class="logInButton"><a href="login">LogIn</a></div>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${! empty login}">
	<div class="logo">
		<div class="user">
			<p><a class="logo_momo" href="/momo">MoMo</a></p>
			<div class="ButtonsFrame">
			    <div class="logoutButton"><a href="logout">Logout</a></div>
			    <div class="profile"><a href="momoMyInfo"><img src="https://kr.object.ncloudstorage.com/team6-image/profile_img/${login.profile_img}" alt="이미지"></a></div>
			</div>
		</div>
	</div>
</c:if>

<div class="menu">
	<nav class="clearfix">
		<ul class="clearfix">
			<li><a href="category">게스트</a></li>
			<li><a href="momoManage?nickname=${login.nickname}">호스트</a></li>
		</ul>
	</nav>
	<hr>
</div>