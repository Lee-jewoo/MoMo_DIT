<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<div class="main">
       
        <p id="title">모모와 함께 취미생활을 공유해보세요</p>
        <p>모모란?</p>
        <p>"모두 모여라" 누구나 호스트로 모임을 주도할 수 있으며 누구나 게스트로 모임에 참여할 수 있습니다.</p>
        <div class="start_guest">
            <p id="title">게스트</p>
            <img src="https://kr.object.ncloudstorage.com/team6-image/default_img/guest.jpg">
            <p>게스트는 기존에 생성된 각종 모임에 참여할 수 있습니다.</p>
            <p>게스트는 모임 참여 신청시 로그인이 필요합니다.</p>
            <input type="button" value="게스트로 시작하기" id="guest_button" onClick="location.href='category'">
        </div>
        <div class="start_host">
            <p id="title">호스트</p>
            <img src="https://kr.object.ncloudstorage.com/team6-image/default_img/host.png">
            <p>호스트는 새로운 모임을 생성할 수 있습니다.</p>
            <p>호스트는 본인이 생성한 모임을 관리할 수 있습니다.</p>
            <p>호스트는 반드시 로그인이 필요합니다.</p>
            <input type="button" value="호스트로 시작하기" id="host_button" onClick="location.href='momoManage?nickname=${login.nickname}'">
        </div>
 
        

    </div>
  </body>
</html>