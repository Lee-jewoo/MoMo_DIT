<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
		<div class="mainPage">
		<div class="main">
			<!-- 검색 영역 -->
			<div class="search_wrap">
				<form action="main" id="searchForm">
				<div class="selectCategory">
				<div class="getMomoList">
					<span class="categoryList" id="categoryList">분야선택</span>
					<!-- 태그 선택 -->
					<c:choose>
					<c:when test="${!empty mainboardList.cate_num}">
					<select name="cate_num" id="cate_num2" onchange="cate_default_remove()">
						<c:forEach var="cateDTO" items="${cateList}" varStatus="status">
							<c:if test="${cateDTO.par_cate_num == par_cate_num}">
								<c:choose>
									<c:when test="${cateDTO.cate_num == mainboardList.cate_num}">
										<option value="${cateDTO.cate_num}" selected>${cateDTO.cate}</option>
									</c:when>
									<c:otherwise>
										<option value="${cateDTO.cate_num}">${cateDTO.cate}</option>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</select>
					</c:when>
					<c:otherwise>
					<select name="cate_num" id="cate_num2" onchange="cate_default_remove()">
						<c:forEach var="cateDTO" items="${cateList}" varStatus="status">
							<c:if test="${cateDTO.par_cate_num == par_cate_num}">
							<c:choose>
								<c:when test="${cateDTO.cate_num%100 == 99}">
								<option value="${cateDTO.cate_num}" selected>${cateDTO.cate}</option>
								</c:when>
								<c:otherwise>
								<option value="${cateDTO.cate_num}">${cateDTO.cate}</option>
								</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</select>
					</c:otherwise>
					</c:choose>
					<!-- 지역 선택 -->
					<span class="locationList" id="locationList">지역선택</span>
					<c:choose>
					<c:when test="${!empty mainboardList.loc_num}">
					<select name="loc_num1" id="loc_num1" onchange="loc_select()">
						<c:forEach var="locDTO" items="${locList}">
							<c:if test="${locDTO.loc_level == 1}">
								<c:choose>
									<c:when test="${fn:substring(locDTO.loc_num, 0, 2) == fn:substring(mainboardList.loc_num, 0, 2)}">
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
						<c:if test="${fn:substring(locDTO.par_loc_num, 0, 2) == fn:substring(mainboardList.loc_num, 0, 2)}">
							<c:choose>
								<c:when test="${locDTO.loc_num == mainboardList.loc_num}">
									<option value="${locDTO.loc_num}" selected>${locDTO.loc}</option>
								</c:when>
								<c:otherwise>
									<option value="${locDTO.loc_num}">${locDTO.loc}</option>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
					</select>
					</c:when>
					<c:otherwise>
						<select name="loc_num1" id="loc_num1" onchange="loc_select()">
						<c:forEach var="locDTO" items="${locList}">
							<c:if test="${locDTO.loc_level == 1}">
							<c:choose>
								<c:when test="${locDTO.loc_num == 2700}">
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
						<option value="">전국</option>
					</select>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
					<select name="searchName">
						<c:choose>
						<c:when test="${mainboardList.searchName == 'nickname'}">
							<option value="momoName">제목</option>
							<option value="nickname" selected>작성자</option>
						</c:when>
						<c:otherwise>
							<option value="momoName" selected>제목</option>
							<option value="nickname">작성자</option>
						</c:otherwise>
						</c:choose>
					</select>
					<input type="text" name="searchValue" value="${mainboardList.searchValue}">
					<input type="hidden" name="par_cate_num" value="${par_cate_num}">
					<input type="submit" value="검색">
				</form>
			</div>

			<div class="momoMain_table_wrap">
				<!-- 게시물 o -->
				<c:if test="${!empty mainboardList.list}">
					<table class="momoMain_table" border="1">
						<thead>
							<tr>
								<th class="th_column_1">닉네임</th>
								<th class="th_column_2">모모제목</th>
								<th class="th_column_3">모모소개</th>
								<th class="th_column_4">이미지</th>
								<th class="th_column_5">인원</th>
								<th class="th_column_6">찜하기</th>
							</tr>
						</thead>
						<c:forEach items="${mainboardList.list}" var="mainboardList">
							<tr>
								<td class="nickname">${mainboardList.nickname}</td>
								<td>${mainboardList.momoName}</td>
								<td>${mainboardList.momoIntro}</td>
								<td><a href="retrieve?momo_id=${mainboardList.momo_id}"><img
										width="150px" height="150px"
										src="https://kr.object.ncloudstorage.com/team6-image/momo_img/${mainboardList.momo_img}"></a></td>
								<td>${mainboardList.headcount}/${mainboardList.maxHeadcount}</td>
						<c:choose>
									<c:when test="${empty login}">
										<td><img width="50px" height="50px"
											src="images/dislike.png" onclick="location.href='loginFail'"></td>
									</c:when>
									<c:when test="${not empty login and !idList.contains(mainboardList.momo_id)}">
										<td><img width="50px" height="50px"
											src="images/dislike.png" id="markInput${mainboardList.momo_id}"
											onclick="markAdd(${mainboardList.momo_id})"></td>
									</c:when>
									<c:otherwise>
										<td><img width="50px" height="50px" src="images/like.png"
											id="markInput${mainboardList.momo_id}"
											onclick="markDelete(${mainboardList.momo_id})"></td>
									</c:otherwise>
								</c:choose>
								<input type="hidden" id="nickname" name="nickname"
									value="${login.nickname}">
								<input type="hidden" id="momo_id" name="momo_id"
									value="${mainboardList.momo_id}">
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<!-- 게시물 x -->
				<c:if test="${empty mainboardList.list}">
					<div class="table_empty">등록된 모모가 없습니다.</div>
				</c:if>
			</div>
			
			<table>
			<tr>
				<td colspan="6">
					<c:set var="totalPage" value="${mainboardList.totalRecord/mainboardList.perPage}"/>
					<c:if test="${mainboardList.totalRecord % mainboardList.perPage != 0}">
						<c:set var="totalPage" value="${totalPage+1}"/>
					</c:if>
					<c:forEach var="i" begin="1" end="${totalPage}">
                		<c:if test="${mainboardList.curPage==i}">${i}</c:if>
                		<c:if test="${mainboardList.curPage!=i}">
							<a href="main?par_cate_num=${par_cate_num}&searchName=${mainboardList.searchName}&searchValue=${mainboardList.searchValue}&cate_num=${mainboardList.cate_num}&loc_num=${mainboardList.loc_num}&curPage=${i}">${i}</a>
						</c:if>
                	</c:forEach>
		        </td>
			  </tr>
			</table>
			
		</div>
	</div>

	<script type="text/javascript">
	function markDelete(momo_id) {

		$.ajax({
			url : 'markDelete',
			type : 'get',
			data : {
				momo_id:momo_id
			},
			success : function(data, status, xhr) {
					if(data=="success"){
					$('#markInput'+momo_id).attr('src', 'images/dislike.png');	
					location.reload();
					}
					else{
						alert('찜 삭제 중 에러가 발생했습니다.1');
					}
			},
			error : function(data, status, xhr) {
				alert('찜 삭제 중 에러가 발생했습니다.2');
			}
		});
	};
	function markAdd(momo_id) {
		$.ajax({
			url : 'markAdd',
			type : 'get',
			data : {
				momo_id:momo_id
			},
			success : function(data, status, xhr) {
					if(data=="success"){
					$('#markInput'+momo_id).attr('src', 'images/like.png');
					location.reload();
				}
				else{
					alert('찜하는 중 에러가 발생했습니다.1');
				}
				},
			error : function(data, status, xhr) {
				alert('찜하는 중 에러가 발생했습니다.2');
				console.log(data);
			}
		});
	};
	</script>
