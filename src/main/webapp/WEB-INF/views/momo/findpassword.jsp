<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.dto.LoginDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/top.css" />
<link rel="stylesheet" href="css/findpassword.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
 <script>
    $(document).ready(function() {
    	$(".send-button").on("click",function(){
    		
    		var mail1=$("#mail1").val();
    		var mail2=$("#mail2").val();
    		var ran=Math.floor((Math.random()*(999999-100000)+1)+100000);
    		if(!(mail1==""||mail2=="")){
    		$.ajax({
    			
                type:"get",
                url:"SendAuthentication",
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
                		alert("메일이 존재하지 않습니다.");
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
    	$(".code-button").on("click",function(){
    		
    		var code = $("#code").val();
    		var random=$("#random").val();
    		if(random<100000||random>999999){
    			alert("인증번호를 발급받아주세요");
    		}
    		else{
    			if(code==random){
    				alert("인증번호가 일치합니다.");
    			}
    			else{
    				alert("인증번호가 일치하지 않습니다.");
    			}
    		}
    	});    	
    	$(".find-button").on("click",function(){
    		var code = $("#code").val();
    		var mail1=$("#mail1").val();
    		var mail2=$("#mail2").val();
    		var ran=Math.floor((Math.random()*(99999999-10000000)+1)+10000000);
    		var random=$("#random").val();
    		if(!(mail1==""||mail2==""||code==null||random==null)){
    		if((code==random)&&!(code<100000||code>999999)){
    		$.ajax({
    			
                type:"post",
                url:"SendPassword",
                data:{
                	mail1:mail1,
                	mail2:mail2,
                	ran:ran
                	},
                dataType:"text",  //응답받을 데이터 타입
                success:function(data,status,xhr){
                	alert("새로운 비밀번호가 메일로 전송되었습니다.");
                	location.href="login"
                },
                error:function(xhr,status,error){
                    console.log("에러발생")
                }
            })}
    		else{
    			alert("인증번호를 확인해주세요");
    		} 	}else{
    			alert("이메일을 입력 후 인증해주세요");
    		}
    	});   	
    });
    
    </script>
<input type="hidden" id="random">
<div class="email-container">
	<div class="input-container">
	<table>
	<tr>
	<td>
	<p>E-mail</p>
	</td>
	<td>
		<span><input type="text" name="mail1" id="mail1"
			placeholder="aaa"> @ <input type="text" name="mail2"
			id="mail2" list="domains" placeholder="도메인을 선택하세요" required>
		</span> <br>
		<datalist id="domains">
			<option value="gmail.com">gmail.com</option>
			<option value="naver.com">naver.com</option>
			<option value="daum.net">daum.net</option>
			<option value="yahoo.com">yahoo.com</option>
			<option value="">직접입력</option>
		</datalist>
	</td>
	<td>
		<input type="submit" class="send-button" value="인증번호 보내기"> 
	</td>
	</tr>
	<tr>
	<td>
		<label for="code">인증번호</label> 
	</td>	
	<td>
	<span>
		<input type="text" name="code" id="code" placeholder="인증번호" style="width: 300px;"> 
	</span>
	</td>
	<td>
		<input type="submit" class="code-button" value="인증번호 확인"> <br>
	</td>
	</tr>
	<tr>
	<td colspan="3">
		<input type="submit" class="find-button" value="비밀번호 찾기">	
	</td>
	</tr>	
	</table>
		



	</div>


</div>

