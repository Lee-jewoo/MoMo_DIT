<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="css/momoCreateUpdate.css" />

<div class="momoCreateUpdate">
	<p class="momoCreateUpdate" style="font-size: 30px;">모모 정보 수정하기</p>
	<form action="momoUpdate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="momo_id" value="${mainboardDTO.momo_id}">
		<input type="hidden" name="nickname" value="${login.nickname}">
		<input type="hidden" name="momo_img_origin" value="${mainboardDTO.momo_img}">
		<ul>
			<li><p>*분야</p></li>
			<!-- 카테고리 선택 select -->
			<select name="cate_num1" id="cate_num1" onchange="cate_select()">
				<c:forEach var="cateDTO" items="${cateList}">
					<c:if test="${cateDTO.cate_level == 1}">
						<c:choose>
							<c:when test="${fn:substring(cateDTO.cate_num, 0, 2) == fn:substring(mainboardDTO.cate_num, 0, 2)}">
								<option value="${cateDTO.cate_num}" selected>${cateDTO.cate}</option>
							</c:when>
							<c:otherwise>
								<option value="${cateDTO.cate_num}">${cateDTO.cate}</option>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</select>
			<select name="cate_num" id="cate_num2">
				<c:forEach var="cateDTO" items="${cateList}">
					<c:if test="${fn:substring(cateDTO.par_cate_num, 0, 2) == fn:substring(mainboardDTO.cate_num, 0, 2)}">
						<c:choose>
							<c:when test="${cateDTO.cate_num == mainboardDTO.cate_num}">
								<option value="${cateDTO.cate_num}" selected>${cateDTO.cate}</option>
							</c:when>
							<c:otherwise>
								<option value="${cateDTO.cate_num}">${cateDTO.cate}</option>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</select>
			<br>
			<li><p>*모임 이름</p></li>
			<input type="text" name="momoName" value="${mainboardDTO.momoName}" placeholder="(필수)" onKeyup="byteCheck(this);" data-byte="60" required>
			<br>
			<li><p>*모임 한 줄 소개</p></li>
			<input type="text" name="momoIntro" value="${mainboardDTO.momoIntro}" placeholder="(필수)" onKeyup="byteCheck(this);" data-byte="150" required>
			<br>
			<li><p>대표 이미지</p></li>
			<span class="image-container"> <!--기본 이미지 삽입-->
				<li><div class="image-box">
						<img style="width: 500px;" id="preview-image" src="https://kr.object.ncloudstorage.com/team6-image/momo_img/${mainboardDTO.momo_img}">
					</div></li> <br>
			</span>
			<input type="file" name="momo_img_input" id="momo_img">
			<br>
			<li><p>*모임 상세 소개</p></li>
			<li><textarea name="momoDetail" onKeyup="byteCheck(this);" data-byte="2000">${mainboardDTO.momoDetail}</textarea></li>
			<br>
			<li><p>*장소</p></li>
			<!-- 지역 선택 select -->
			<select name="loc_num1" id="loc_num1" onchange="loc_select()">
				<c:forEach var="locDTO" items="${locList}">
					<c:if test="${locDTO.loc_level == 1}">
						<c:choose>
							<c:when test="${fn:substring(locDTO.loc_num, 0, 2) == fn:substring(mainboardDTO.loc_num, 0, 2)}">
								<option value="${locDTO.loc_num}" selected>${locDTO.loc}</option>
							</c:when>
							<c:otherwise>
								<option value="${locDTO.loc_num}">${locDTO.loc}</option>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</select>
			<select name="loc_num" id="loc_num2">
				<c:forEach var="locDTO" items="${locList}">
					<c:if test="${fn:substring(locDTO.par_loc_num, 0, 2) == fn:substring(mainboardDTO.loc_num, 0, 2)}">
						<c:choose>
							<c:when test="${locDTO.loc_num == mainboardDTO.loc_num}">
								<option value="${locDTO.loc_num}" selected>${locDTO.loc}</option>
							</c:when>
							<c:otherwise>
								<option value="${locDTO.loc_num}">${locDTO.loc}</option>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</select>
			<br>
			<li><p></p></li>
			<input type="text" name="momoLoc" placeholder="상세 장소"
				value="${mainboardDTO.momoLoc}" onKeyup="byteCheck(this);" data-byte="150" required>
			<br>
			<li><p>*일자</p></li>
			<input type="date" name="momoDate" value="${mainboardDTO.momoDate}" min="" required>
			<br>
			<li><p>*상세시간</p></li>
			<input type="text" name="momoTime" placeholder="오후 4시 / 매주 토요일 오전 8시"
				value="${mainboardDTO.momoTime}" onKeyup="byteCheck(this);" data-byte="100" required>
			<br>
			<li><p>*참가 비용(원)</p></li>
			<input type="text" name="fee" placeholder="(필수) ex) 5000원(음료) / 100만원 미만 / 없으면  0 또는 없음"
				value="${mainboardDTO.fee}" onKeyup="byteCheck(this);" data-byte="50" required>
			<br>
			<li><p>*최대 인원(명)</p></li>
			<input type="text" name="maxHeadcount" oninput="onlyNumber()" placeholder="100명 미만 / 숫자만"
				value="${mainboardDTO.maxHeadcount}" onKeyup="byteCheck(this);" data-byte="2" required>
			<br>
			<li><p>준비물</p></li>
			<input type="text" name="materials" placeholder="개인이 준비할 것"
				value="${mainboardDTO.materials}" onKeyup="byteCheck(this);" data-byte="200">
			<br>
			<li><p>신청 질문1</p></li>
			<input type="text" name="question1"
				placeholder="이 모임에 참가하고자 하는 동기는 무엇인가요?"
				value="${mainboardDTO.question1}" onKeyup="byteCheck(this);" data-byte="200">
			<br>
			<li><p>신청 질문2</p></li>
			<input type="text" name="question2"
				placeholder="이 모임 이전에 어떤 경험이나 지식이 있나요?"
				value="${mainboardDTO.question2}" onKeyup="byteCheck(this);" data-byte="200">
			<br>
		</ul>
		<input type="submit" value="수정하기" onclick="momoCreateUpdate()">
	</form>
</div>