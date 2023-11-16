<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("replaceChar", "\n"); %>

	<!-- 모임 상세 정보 -->
	<table>
		<tr>
			<td width="80px">진행 일자</td>
			<td>${mainboardDTO.momoDate}</td>
		</tr>
		<tr>
			<td>진행 장소</td>
			<td>${ldto.loc_path} &nbsp; ${mainboardDTO.momoLoc}</td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="map" style="width:800px;height:400px;"></div>
			</td>
		</tr>
		<tr>
			<td>인원</td>
			<td>${mainboardDTO.headcount}/${mainboardDTO.maxHeadcount}</td>
		</tr>
		<tr>
			<td>진행 시간</td>
			<td>${mainboardDTO.momoTime}</td>
		</tr>
		<tr>
			<td>참가비</td>
			<td>${mainboardDTO.fee}</td>
		</tr>
		<tr>
			<td>준비물</td>
			<td>${mainboardDTO.materials}</td>
		</tr>
		<tr>
			<td>모임 상세 정보</td>
			<td>${fn:replace(mainboardDTO.momoDetail, replaceChar, "<br/>")}</td>
		</tr>
	</table>

	<hr>

	<!-- 호스트 정보 -->
	<p class="momo_detail">호스트 정보</p>
	<div class="host-image">
		<img src="https://kr.object.ncloudstorage.com/team6-image/profile_img/${memberDTO.profile_img}" alt="호스트 이미지">
	</div>
	<div class="host-info">
		<h3>${memberDTO.nickname}</h3>
		<p class="momo_detail">${memberDTO.memberIntro}</p>
	</div>

	<div class="application-section">
		<input type="button" id="button" class="application-button" value="신청하기"
			data-momo_id="${mainboardDTO.momo_id}">
	</div>
	<!-- 사용자 닉네임을 저장할 hidden 필드 -->
	<input type="hidden" name="nickname" id="nickname" value="${login.nickname}">
	<input type="hidden" id="headcount" value="${mainboardDTO.headcount}">
	<input type="hidden" id="maxHeadcount" value="${mainboardDTO.maxHeadcount}">
	<input type="hidden" id="status" value="${mainboardDTO.status}">

