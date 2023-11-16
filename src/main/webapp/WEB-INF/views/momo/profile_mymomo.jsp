<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="card-container">
		<div class="image-box">
			<img
				src="https://kr.object.ncloudstorage.com/team6-image/profile_img/${login.profile_img}"
				alt="user-profile">
		</div>
		<div class="main-box">
			<div class="user-info">
				<span class="name">${login.nickname}</span> <span class="email">${login.email}</span>
			</div>
		</div>
		<div class="circle-1"></div>
		<div class="circle-2"></div>
	</div>
</div>

<div class="profile_menu">
	<nav class="clearfix">
		<ul class="clearfix">
			<li><a href="momoMyInfo">회원 정보</a></li>
			<li><a href="momoprofile_mymomo">나의 모모</a></li>
		</ul>
		<a id="pull" href="#"></a>
	</nav>
</div>
<hr>
<h2 align="center">나의 모모</h2>
<div class="list">
	<div class="interest">
		<table class="interest_list">
			<tr>
				<th colspan="4"><h2>&nbsp;&nbsp;&nbsp;&nbsp;찜한 모모</h2></th>
			</tr>
			<tr>
				<th width="500px">모모 이름</th>
				<th width="400px">장소</th>
				<th width="200px">날짜</th>
				<th width="150px">인원</th>
			</tr>
			<c:choose>
				<c:when test="${not empty markMainboardList}">
					<c:forEach var="markList" items="${markMainboardList}">
						<tr>
							<td><a href="retrieve?momo_id=${markList.momo_id}">${markList.momoName}</a></td>
							<td>${markList.momoLoc}</td>
							<td>${markList.momoDate}</td>
							<td>${markList.headcount}/${markList.maxHeadcount}</td>
							<td class="cancel_button"><input type="button" value="찜 취소"
								class="cancel_button" onClick="markDelete(${markList.momo_id})"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4"><font size="4">찜한 모모가 없습니다.</font></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<div class="applicant">
		<table class="applicant_list">
			<tr>
				<th colspan="5"><h2>신청한 모모</h2></th>
			</tr>
			<tr>
				<th width="500px">모모 이름</th>
				<th width="400px">장소</th>
				<th width="200px">날짜</th>
				<th width="150px">인원</th>
				<th></th>
			</tr>
			<c:choose>
				<c:when test="${not empty aMap_list}">
					<c:forEach var="aMap" items="${aMap_list}">
						<tr>
							<td><a href="retrieve?momo_id=${aMap.momo_id}">${aMap.momoName}</a></td>
							<td>${aMap.loc_path}</td>
							<td>${aMap.momoDate}</td>
							<td>${aMap.headcount}/${aMap.maxHeadcount}</td>
							<td class="cancel_button"><input type="button" value="모임 취소"
								class="applicant_button"
								data-applicant_id="${aMap.applicant_id}"></td>
						</tr>
						<input type="hidden" name="applicant_id"
							value="${aMap.applicant_id}">
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5"><font size="4">신청중인 모모가 없습니다.</font></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

	<div class="join">
		<table class="join_list">
			<tr>
				<th colspan="5"><h2>참여한 모모</h2></th>
			</tr>
			<tr>
				<th width="500px">모모 이름</th>
				<th width="400px">장소</th>
				<th width="200px">날짜</th>
				<th width="150px">인원</th>
				<th></th>
			</tr>
			<c:choose>
				<c:when test="${not empty profileList}">
					<c:forEach var="profileMap" items="${profileList}">
						<tr>
							<td><a href="retrieve?momo_id=${profileMap.momo_id}">${profileMap.momoName}</a></td>
							<td>${profileMap.loc_path}</td>
							<td>${profileMap.momoDate}</td>
							<td>${profileMap.headcount}/${profileMap.maxHeadcount}</td>
							<td class="cancel_button"><input type="button" value="모임 취소"
								class="cancel_button"
								onClick="location.href='momoCancelForm?guest_id=${profileMap.guest_id}'"></td>
						</tr>
						<input type="hidden" name="guest_id"
							value="${profileMap.guest_id}">
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5"><font size="4">참여한 모모가 없습니다.</font></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
</div>
