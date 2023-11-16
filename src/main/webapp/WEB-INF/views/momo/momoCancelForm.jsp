<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/momoCancelApplicant.css" />

<form action="momoCancel" method="post">
	<div class="momoCancelApplicant">
		<div class="momoCancelApplicant" style="font-size: 30px;">모모 취소 하기</div>
		<input type="hidden" name="guest_id" value="${guestDTO.guest_id}">
		<input type="hidden" name="momo_id" value="${guestDTO.momo_id}">
		<input type="hidden" name="nickname" value="${login.nickname}">
		<div class="image-box">
			<img style="width: 500px;" id="momo_img" src="https://kr.object.ncloudstorage.com/team6-image/momo_img/${mainboardDTO.momo_img}">
		</div><br>
		<ul>
			<li style="font-size: 20px;">모모 이름</li><br>
			<li style="font-size: 30px;">${mainboardDTO.momoName}</li><br><br>
			<li style="font-size: 20px;">모모 소개</li><br>
			<li style="font-size: 30px;">${mainboardDTO.momoIntro}</li>
		</ul><br>
		<hr>
		<div class="momoCancelApplicant" style="font-size: 30px;">취소신청서</div>
		<div>호스트에게 모모 참가 취소 신청서를 작성해주세요.</div>
		<ul>
			<br>
			<li><p>1. 이번 모모를 취소하게 된 이유는 무엇인가요?</p></li>
			<br>
			<input type="text" name="reason1" onKeyup="byteCheck(this);" data-byte="150">
			<br>
			<li><p>2. 나중에 다시 참가할 의향이 있나요?</p></li>
			<br>
			<input type="text" name="reason2" onKeyup="byteCheck(this);" data-byte="150">
			<br>
		</ul>
		<input type="submit" value="모임취소하기" onclick="momoCancel()">
	</div>
</form>