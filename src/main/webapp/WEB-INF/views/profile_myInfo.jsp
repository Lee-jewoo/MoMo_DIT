<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두 모여라, MoMo</title>
<link rel="stylesheet" href="css/profile_myInfo.css"/>
</head>
<body>
	<jsp:include page="common/top.jsp" flush="true" /><br>
	<div class="container">
		<div class="card-container">
			<div class="image-box">
				<img src="https://kr.object.ncloudstorage.com/team6-image/profile_img/${login.profile_img}" alt="user-profile">
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
	<br>
    <div class="mypage">
        <h2>회원 정보</h2>
        <br>
        <form method="post" action="myInfoModify" enctype="multipart/form-data">
        	<table class="mypage_tb">
		        <tr>
		            <th>닉네임</th>
		            <td><input type="text" name="nickname" id="nickname" value="${info.nickname}" onClick="alert('닉네임은 수정할 수 없습니다.')" readonly></td>
		        </tr>
		        <tr>
		            <th class="col1">이메일</th>
		            <td class="col2"><input type="text" name="mail1" id="mail1" placeholder="aaa" value="${fn:split(info.email, '@')[0]}" readonly>
		            <span class="a">@</span>              
		            <input type="text" name="mail2" id="mail2" list="domains" placeholder="도메인을 선택하세요" required value="${fn:split(info.email, '@')[1]}" readonly>
		            <datalist id="domains">
		                <option value="gmail.com">gmail.com</option>
		                <option value="naver.com">naver.com</option>
		                <option value="daum.net">daum.net</option>
		                <option value="yahoo.com">yahoo.com</option>
		                <option value="">직접입력</option>
		            </datalist></td>
	            </tr>
	            <tr>
            		<th class="col1">비밀번호</th>
            		<td class="col2"><input type="password" maxlength="16" name="passwd" id="password" value="${info.passwd}" required onkeyup="checkPasswordMatch()">
       	 			<p style="font-size:15px;">※비밀번호는 <span class="num">문자, 숫자, 특수문자(~!@#$%^&*)의 조합 10 ~ 16자리</span>로 입력이 가능합니다.</p></td>
       	 		</tr>
       	 		<tr>
		       	 	<th class="col1">비밀번호 확인</th>
		       	 	<td class="col2" height="50px"><input type="password" name="passwd2" maxlength="16" required onkeyup="checkPasswordMatch()">
		            <p id="passwordMatch" style="font-size:15px;">&nbsp;</p></td>
	            </tr>
	            <tr>
	            	<th class="col1">성별</th>
	            	<c:choose>
						<c:when test="${info.gender == 'm'}">
							<td class="td_gender"><input type="text" name="gender" id="gender" value="남자" onClick="alert('성별은 수정할 수 없습니다.')" readonly></td>
						</c:when>
						<c:otherwise>
							<td class="td_gender"><input type="text" name="gender" id="gender" value="여자" onClick="alert('성별은 수정할 수 없습니다.')" readonly></td>
						</c:otherwise>
					</c:choose>
	            </tr>
				<tr>
					<th class="col1">한줄소개</th>
					<td class="col2"><input type="text" style="width: 340px;" name="memberIntro" id="memberIntro" value="${info.memberIntro}" ></td>
				</tr>
            	<tr>
					<th class="col1">프로필 사진 선택</th>
					<td class="col2"><div class="image-box-input"><img id="preview-image" src="https://kr.object.ncloudstorage.com/team6-image/profile_img/${info.profile_img}"></div>
					<br><input type="file" id="profile_img" name="profile_img_input" accept="image/*"></td>
				</tr>
        	</table>
            <button type="submit" class="modify-btn">수정</button>
            <input type="button" class="delete-btn" value="회원탈퇴" data-nickname="${info.nickname}"></input>
            <!-- 수정 전 프로필사진 파일명을 전달  -->
            <input type="hidden" name="profile_img_origin" value="${info.profile_img}">
        </form>
    </div>
   
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type ="text/javascript" src="js/image_preview2.js"></script>
<script>
	// 비밀번호 일치 여부 확인
	function checkPasswordMatch() {
		var password = document.getElementsByName("passwd")[0].value;
		var confirmPassword = document.getElementsByName("passwd2")[0].value;

		if (password === confirmPassword) {
			document.getElementById("passwordMatch").innerHTML = "비밀번호가 일치합니다";
			document.getElementById("passwordMatch").style.color = "green";
		} else {
			document.getElementById("passwordMatch").innerHTML = "비밀번호가 일치하지 않습니다";
			document.getElementById("passwordMatch").style.color = "red";
		}
	}
	
	// 회원탈퇴 ajax -> 첫 화면으로 이동
	$(document).ready(function() {
		$(".delete-btn").on("click",function(){
			var nickname = $(this).attr("data-nickname");
			if(confirm("정말로 회원탈퇴 하시겠습니까?")){
				$.ajax({
					type:"get",
					url:"deleteID",
					data:{
					nickname:nickname
					},
					dataType:"text",  //응답받을 데이터 타입
					success:function(data,status,xhr){
						if(data=="success"){
							alert("회원탈퇴가 완료되었습니다.");
							location.href="/momo";
						}
					},
					error:function(xhr,status,error){
						console.log("에러발생")
					}
				});
	        		
			}else{
				
			}
	    	
		});
	});
	
</script>
<script>
	// 생년월일 최대값
	document.getElementById('birth').max = new Date().toISOString().substring(0, 10);
</script>
</html>