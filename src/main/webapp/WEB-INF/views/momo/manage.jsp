<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link href="css/manage.css" rel="stylesheet">
</head>
<body>
	<div class="management">
		<P id="managetitle">모모 관리하기</P>
		<br>
		<p>나의 모모</p>
		<br>
		<div class="manage_table">
		<table>
			<c:choose>
				<c:when test="${!empty list}">
					<c:forEach var="MainboardDTO" items="${list}">
							<tr>
								<c:if test="${MainboardDTO.status==0}">
									<td id="momoName_td">&nbsp;<a
										href="momoDetail_info?momo_id=${MainboardDTO.momo_id}">${MainboardDTO.momoName}</a></td>
								</c:if>
							</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<table>
						<tr>
							<td id="momoName_td">&nbsp;등록한 모모가 없습니다.</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</table>
		</div>
	</div>
</body>
</html>