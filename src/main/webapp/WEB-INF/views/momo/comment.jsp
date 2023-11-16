<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>모두 모여라, MoMo</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
// 페이지가 로드될 때 댓글 목록 조회
$(document).ready(function() {
    loadReplies();
});
// 댓글 목록 조회
function loadReplies() {
    $.ajax({
        url: "/momo/getAllReplies",
        type: "GET",
        success: function(replyMapList) {
            var html = '';
            var nickname = $('#nickname').val();
            var momo_id = $('#momo_id').val();
            $.each(replyMapList, function(index, replyMap){
            	// 줄바꿈 포함 출력
            	replyMap.replyDTO.replyContent = replyMap.replyDTO.replyContent.replace(/(?:\r\n|\r|\n)/g, '<br />');
            	// 댓글 작성자 닉네임과 momo_id 일치 여부 확인
            	if (replyMap.replyDTO.nickname == nickname && replyMap.replyDTO.momo_id == momo_id){
	                html += '<div class="comment-item" data-reply_id="' + replyMap.replyDTO.reply_id + '">'
	                     + '<img src="https://kr.object.ncloudstorage.com/team6-image/profile_img/'+replyMap.memberDTO.profile_img+'" alt="유저 프로필" class="comment-profile-img">'
	                     + '<div class="comment-item-nickname">' + replyMap.replyDTO.nickname + '</div>'
	                     + '<div class="comment-item-text">' + replyMap.replyDTO.replyContent + '</div>'
	                     + '<button type="button" id="updateReply" onclick="prepareUpdateReply(' + replyMap.replyDTO.reply_id + ');">수정</button>'
	                     + '<button type="button" onclick="deleteReply(' + replyMap.replyDTO.reply_id + ');">삭제</button>'
	                     + '</div>';
            	} else if (replyMap.replyDTO.nickname != nickname && replyMap.replyDTO.momo_id == momo_id){
            		html += '<div class="comment-item" data-reply_id="' + replyMap.replyDTO.reply_id + '">'
                    + '<img src="https://kr.object.ncloudstorage.com/team6-image/profile_img/'+replyMap.memberDTO.profile_img+'" alt="유저 프로필" class="comment-profile-img">'
                    + '<div class="comment-item-nickname">' + replyMap.replyDTO.nickname + '</div>'
                    + '<div class="comment-item-text">' + replyMap.replyDTO.replyContent + '</div>'
                    + '</div>';
            	}
            });
            $('.comment-list').html(html);
        },
        error: function() {
            alert("댓글 로드 실패");
        }
    });
}
// 댓글 추가
$(document).ready(function() {
	document.getElementById('addReply_btn').addEventListener('click',function (){
		var momo_id = $("#momo_id").val();
		var nickname = $("#nickname").val();
		var currentDateTime = new Date().toISOString().slice(0, 16).replace('T', ' ');
	    var formData = $("#commentForm").serialize() + 
	    "&momo_id" + momo_id+ 
	    "&nickname" + nickname+
	    "&replyDate=" + currentDateTime +
	    "&fixDate=" + currentDateTime;
	    $.ajax({
	        url: "addReply",
	        type: "POST",
	        data: formData,
	        success: function(response) {
	            if (response === "success") {
	            	// 작성한 내용을 textarea에서 삭제 + 새로운 댓글 목록 조회
	            	$(".comment-input").val("");
	            	loadReplies();
	            } else {
	                alert("로그인이 필요합니다.");
	                window.location.href = "login";
	            }
	        },
	        error: function() {
	            alert("댓글 추가에 실패했습니다.");
	        }
	    });
	});
});
// 댓글 수정 버튼을 누르면 textarea로 변환 + 수정버튼을 저장버튼으로 변환
function prepareUpdateReply(reply_id) {
    var commentItem = $('div[data-reply_id="' + reply_id + '"]');
    var currentContent = commentItem.find('.comment-item-text').text();
    
    var updateFormHtml = '<textarea class="comment-input" name="replyContent">' + currentContent + '</textarea>'
    var updateReply = $('#updateReply');
    updateReply.html('<button type="button" onclick="updateReply(' + reply_id + ');">저장</button>');

    commentItem.find('.comment-item-text').html(updateFormHtml);
}
// 댓글 수정 후 수정된 댓글 목록 조회
function updateReply(reply_id) {
    var commentItem = $('div[data-reply_id="' + reply_id + '"]');
    var updatedContent = commentItem.find('textarea[name="replyContent"]').val();
    var reply_id = reply_id;
    var formData = {
    	reply_id: reply_id,
        replyContent: updatedContent
    };
    $.ajax({
        url: "updateReply",
        type: "POST",
        data: formData,
        success: function(response) {
            if (response === "success") {
            	loadReplies();
            } else {
                alert("댓글 수정 실패");
            }
        },
        error: function() {
            alert("댓글 수정에 실패했습니다.");
        }
    });
}
// 댓글 삭제
function deleteReply(replyId) {
    $.ajax({
        url: "/momo/deleteReply",
        type: "POST",
        data: { id: replyId },  
        success: function(response) {
            if (response === "success") {
            	loadReplies();
            } else {
                alert("댓글 삭제 실패");
            }
        },
        error: function() {
            alert("댓글 삭제에 실패했습니다.");
        }
    });
}
</script>
</head>
<body>
<hr>
<!-- 댓글 섹션 시작 -->
<p class="comment_title">comment</p>
<div class="comment-section">

	<!-- 댓글 리스트 시작 -->
	<!-- 댓글 리스트 -->
	<div class="comment-list">
		
	</div>
	<!-- 댓글 리스트 끝 -->

	<form id="commentForm">
		<!-- 댓글추가 -->
			<div class="comment-input-container">
			<c:choose>
				<c:when test="${not empty login}">
					<img src="https://kr.object.ncloudstorage.com/team6-image/profile_img/${login.profile_img}" alt="내 프로필 이미지"
					class="comment-profile-img">
				</c:when>
				<c:otherwise>
					<img src="https://kr.object.ncloudstorage.com/team6-image/profile_img/profile_img_default.png" alt="내 프로필 이미지"
					class="comment-profile-img">
				</c:otherwise>
			</c:choose>
				<textarea class="comment-input" name="replyContent"
					placeholder="댓글 작성"></textarea>
				<button type="button" id="addReply_btn">댓글 추가</button>
				<input type="hidden" name="nickname" id="nickname" value="${login.nickname}">
				<input type="hidden" name="momo_id" id="momo_id" value="${mainboardDTO.momo_id}">
			</div>
	</form>
</div>
<!-- 댓글 입력 부분 끝 -->
</body>
</html>