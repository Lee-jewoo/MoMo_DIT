<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="com.dto.LoginDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/top.css" />
<link rel="stylesheet" href="css/regist.css" />

<form method="post" action="registA" enctype="multipart/form-data">
	<div class="container">
	<div class="insert">
		<table>
			<br>
			<br>
            <caption>
				<h2>회원가입</h2>
            </caption>
				<tr>
					<td class="col1">이메일</td>
					<td class="col2">
					<input type="text" name="mail1" id="mail1" placeholder="aaa"> <span class="a">@</span>
					<input type="text" name="mail2" id="mail2" list="domains" placeholder="도메인을 선택하세요" required>
					<datalist id="domains">
						<option value="gmail.com">gmail.com</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="yahoo.com">yahoo.com</option>
						<option value="">직접입력</option>
					</datalist>
					<input type="button" value="인증번호 발송" class="emailAuthentication">
				</td>
				</tr>
				<tr>
				
				<td class="col1">인증번호<input type="hidden" id="random"></td>
				<td class="col2">
				<input type="text" id="code">
				<input type="button" value="인증번호 확인" class="AuthenticationCheck">
				<span id="Authentication"></span>
				</td>
				</tr>
				<tr>
					<td class="col1">별명</td>
					<td class="col2">
						<input type="text" name="nickname" id="nickname" onKeyup="byteCheck(this);" data-byte="18" required>
						<input type="button" id="btnCheck" onclick="checkNickname()" class="btn btn-default" value="중복검사" required />
						<span id="result"></span>
					</td>
				</tr>
				<tr>
					<td class="col1">비밀번호</td>
					<td class="col2"><input type="password" name="passwd" maxlength="16" required onkeyup="checkPasswordMatch()"> <br>
					<p>※비밀번호는 <span class="num">문자, 숫자, 특수문자(~!@#$%^&*)의 조합 10 ~ 16자리</span>로 입력이 가능합니다.</p>
					</td>
				</tr>
				<tr>
					<td class="col1">비밀번호 확인</td>
					<td class="col2"><input type="password" name="passwd2" maxlength="16" required onkeyup="checkPasswordMatch()"></td>
					<td id="passwordMatch"></td>
				</tr>
				<tr>
					<td class="col1">성별</td>
					<td class="col2" required>
						<input style="margin-left: 65px" type="radio" name="gender" value="f" required>여성
						<input type="radio" name="gender" value="m" required>남성</td>
				</tr>
				<tr>
					<td class="col1">한줄소개</td>
					<td class="col2"><input type="text" id="memberintro" name="memberintro" required></td>
				</tr>
				<tr>
					<td class="col1">프로필 사진 선택:</td>
					<td class="image-box"><img style="width: 130px;" id="preview-image" src="">
					<td class="col2"><input type="file" id="profile_img" name="profile_img_input" accept="image/*"></td>
				</tr>
				<tr>
					<td class="col1">개인정보 이용 동의</td>
					<td class="col2">
						<input style="margin-left: 65px" id="agree" type="radio" name="agree" value="agree" required>동의합니다
						<input type="radio" id="agree" name="agree" value="disagree">동의하지 않습니다
					</td>
				</tr>
			</table>
			<button type="submit" class="createId_button" style="background-color: #FFA101;" onClick="idcheck()">가입하기</button>
		</div>
	</div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
$(document).ready(function() {
	$(".emailAuthentication").on("click",function(){
		
		var mail1=$("#mail1").val();
		var mail2=$("#mail2").val();
		var ran=Math.floor((Math.random()*(999999-100000)+1)+100000);
		if(!(mail1==""||mail2=="")){
		$.ajax({
			
            type:"get",
            url:"emailAuthentication",
            data:{
            	mail1:mail1,
            	mail2:mail2,
            	ran:ran
            	},
            dataType:"text" , //응답받을 데이터 타입
           success:function(data,status,xhr){
            	if(data=="success"){
            		alert("인증번호가 발송되었습니다.");
          	var random=$("#random").val(ran);	
            	}
            	else{
            		alert("메일이 이미 존재하거나 탈퇴한 이메일 입니다. 로그인 화면으로 이동합니다.");
            		location.href="login"
            	}
            },
            error:function(xhr,status,error){
                console.log("에러발생")
            }
        })	
		}
		else{
			alert("이메일을 입력해주세요");
		}
		
	});  
	$(".AuthenticationCheck").on("click",function(){
		var code = $("#code").val();
		var mail1=$("#mail1").val();
		var mail2=$("#mail2").val();
		var random=$("#random").val();
		if(!(mail1==""||mail2==""||code==null||random==null)){
		if((code==random)&&!(code<100000||code>999999)){
			$('#Authentication').text('인증이 완료되었습니다.');
		}
		else{
			alert("인증번호를 확인해주세요");
		} 	}else{
			alert("이메일을 입력 후 인증해주세요");
		}
	});   	
}); 	
</script>