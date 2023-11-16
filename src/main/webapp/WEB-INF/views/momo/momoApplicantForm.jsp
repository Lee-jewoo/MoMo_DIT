<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/top.css" />
<link rel="stylesheet" href="css/momoCancelApplicant.css" />

<form action="momoApplicant" method="post">
	<div class="momoCancelApplicant">
		<div class="momoCancelApplicant" style="font-size: 30px;">모모 신청 하기</div>
		<input type="hidden" name="momo_id" value="${mainboardDTO.momo_id}">
		<input type="hidden" name="nickname" value="${login.nickname}">
		<input type="hidden" name="gender" value="${login.gender}">
		<div class="image-box">
			<img style="width: 500px;" id="momo_img" src="https://kr.object.ncloudstorage.com/team6-image/momo_img/${mainboardDTO.momo_img}">
		</div>
		<ul>
			<li style="font-size: 20px;">모모 이름</li><br>
			<li style="font-size: 30px;">${mainboardDTO.momoName}</li><br><br>
			<li style="font-size: 20px;">모모 소개</li><br>
			<li style="font-size: 30px;">${mainboardDTO.momoIntro}</li>
		</ul><br>
		<hr>
		<div class="momoCancel" style="font-size: 30px;">모모 신청서</div>
		<div>호스트에게 모모 참가 신청서를 작성해주세요.</div><br>
		<ul>
			<li><p style="font-size: 25px; font-weight: bold;">신청 정보</p></li><br>
			<li><p style="font-size: 20px;">닉네임</p></li>&nbsp;
			<li><p>${login.nickname}</p></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<li><p style="font-size: 20px;">성별</p></li>&nbsp;
			<c:choose>
				<c:when test="${login.gender == 'm'}">
				<li><p>남자</p></li>
				</c:when>
				<c:otherwise>
				<li><p>여자</p></li>
				</c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when test="${!empty mainboardDTO.question1}">
				<li><p style="font-size: 20px;">${mainboardDTO.question1}</p></li>
				</c:when>
				<c:otherwise>
				<li><p style="font-size: 20px;">이 모임에 참가하고자 하는 동기는 무엇인가요?</p></li>
				</c:otherwise>
			</c:choose>
			<br>
			<input type="text" name="reason1" onKeyup="byteCheck(this);" data-byte="150">
			<br>
			<c:choose>
				<c:when test="${!empty mainboardDTO.question2}">
				<li><p style="font-size: 20px;">${mainboardDTO.question2}</p></li>
				</c:when>
				<c:otherwise>
				<li><p style="font-size: 20px;">이 모임 이전에 어떤 경험이나 지식이 있나요?</p></li>
				</c:otherwise>
			</c:choose>
			<br>
			<input type="text" name="reason2" onKeyup="byteCheck(this);" data-byte="150">
			<br>
		</ul>
		<input type="submit" value="제출하기" onclick="momoApplicant()">
	</div>
</form>