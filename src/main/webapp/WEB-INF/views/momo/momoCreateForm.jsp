<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/momoCreateUpdate.css" />

<div class="momoCreateUpdate">
	<p class="momoCreateUpdate" style="font-size: 30px;">모모 정보 입력하기</p>
	<form action="momoCreate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="nickname" value="${login.nickname}">
		<ul>
			<li><p>*분야</p></li>
			<!-- 카테고리 선택 select -->
			<select name="cate_num1" id="cate_num1" onchange="cate_select()">
				<option id="cate_default" selected>-- 카테고리 선택 --</option>
				<c:forEach var="cateDTO" items="${cateList}">
					<c:if test="${cateDTO.cate_level == 1}">
						<option value="${cateDTO.cate_num}">${cateDTO.cate}</option>
					</c:if>
				</c:forEach>
			</select>
			<select name="cate_num" id="cate_num2">
				<option selected>-- 태그 선택 --</option>
			</select>
			<br>
			<li><p>*모임 이름</p></li>
			<input type="text" name="momoName" id="momoName" placeholder="(필수)" onKeyup="byteCheck(this);" data-byte="60">
			<br>
			<li><p>*모임 한 줄 소개</p></li>
			<input type="text" name="momoIntro" id="momoIntro" placeholder="(필수)" onKeyup="byteCheck(this);" data-byte="150">
			<br>
			<li><p>대표 이미지</p></li>
			<span class="image-container">
				<li><div class="image-box">
						<img style="width: 500px;" id="preview-image" src="https://kr.object.ncloudstorage.com/team6-image/momo_img/momo_img_default.png">
					</div></li> <br>
			</span>
			<input type="file" name="momo_img_input" id="momo_img" accept="image/*">
			<br>
			<li><p>*모임 상세 소개</p></li>
			<li><textarea name="momoDetail" id="momoDetail" onKeyup="byteCheck(this);" data-byte="2000"></textarea></li>
			<br>
			<li><p>*장소</p></li>
			<!-- 지역 선택 select -->
			<select name="loc_num1" id="loc_num1" onchange="loc_select()">
				<option id="loc_default" selected>-- 시/도 선택 --</option>
				<c:forEach var="locDTO" items="${locList}">
					<c:if test="${locDTO.loc_level == 1}">
						<option value="${locDTO.loc_num}">${locDTO.loc}</option>
					</c:if>
				</c:forEach>
			</select>
			<select name="loc_num" id="loc_num2">
				<option selected>-- 시/군/구 선택 --</option>
			</select>
			<br>
			<li><p></p></li>
			<input type="text" name="momoLoc" id="momoLoc" placeholder="(필수) 상세 장소 / 정확한 주소가 아니면  시,군,구청이 지도에 표시됩니다." onKeyup="byteCheck(this);" data-byte="150">
			<br>
			<li><p>*일자</p></li>
			<input type="date" name="momoDate" id="momoDate" min="">
			<br>
			<li><p>*상세시간</p></li>
			<input type="text" name="momoTime" id="momoTime" placeholder="(필수) 오후 4시 / 매주 토요일 오전 8시" onKeyup="byteCheck(this);" data-byte="100">
			<br>
			<li><p>*참가 비용(원)</p></li>
			<input type="text" name="fee" id="fee" placeholder="(필수) ex) 5000원(음료) / 100만원 미만 / 없으면  0 또는 없음" onKeyup="byteCheck(this);" data-byte="50">
			<br>
			<li><p>*최대 인원(명)</p></li>
			<input type="text" name="maxHeadcount" id="maxHeadcount" oninput="onlyNumber()" placeholder="(필수) 100명 미만 / 숫자만" onKeyup="byteCheck(this);" data-byte="2">
			<br>
			<li><p>준비물</p></li>
			<input type="text" name="materials" placeholder="개인이 준비할 것" onKeyup="byteCheck(this);" data-byte="200">
			<br>
			<li><p>신청 질문1</p></li>
			<input type="text" name="question1"
				placeholder="이 모임에 참가하고자 하는 동기는 무엇인가요?" onKeyup="byteCheck(this);" data-byte="200">
			<br>
			<li><p>신청 질문2</p></li>
			<input type="text" name="question2"
				placeholder="이 모임 이전에 어떤 경험이나 지식이 있나요?" onKeyup="byteCheck(this);" data-byte="200">
			<br>
		</ul>
		<input type="submit" value="모모 알리기" onclick="momoCreateUpdate()">
	</form>
</div>