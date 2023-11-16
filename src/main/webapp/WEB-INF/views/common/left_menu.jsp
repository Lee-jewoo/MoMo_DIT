<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/left_menu.css" />

<ul class="left_menu">
	<li><a href="momoManage?nickname=${login.nickname}">모모 관리</a></li><br><br>
	<li><a href="momoCreateForm?nickname=${login.nickname}">모모 생성</a></li>
</ul>