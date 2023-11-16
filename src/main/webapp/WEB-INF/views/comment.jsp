<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>comment</title>
<style>
.comment-section {
	margin-top: 40px;
	/* 상단 여백 추가 */
	height: 260px;
	overflow-y: auto;
	/* 세로 스크롤을 활성화합니다. */
}

.comment-section::-webkit-scrollbar {
	display: none;
}

.comment_title {
	font-size: 40px;
	margin: 50px auto;
	text-align: center;
}

.comment-user {
	font-size: 25px;
	display: flex;
	align-items: center;
	margin-left: 10%;
	margin-top: 50px;
	margin-bottom: 10px;
}

.comment-profile-img {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	margin-right: 10px;
}

.comment-input {
	width: 80%;
	height: 80px;
	border: 2px solid black;
	padding: 5px;
	box-sizing: border-box;
	margin-bottom: 10px;
	resize: none;
}

.comment-list {
	width: 70%;
	margin: 0 auto;
	max-height: 180px;
	overflow-y: auto;
	margin-bottom: 20px;
}

.comment-item {
	margin-bottom: 15px;
	display: flex;
	align-items: flex-start;
	padding: 10px;
	border-radius: 8px;
	box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
}

.comment-item-text {
	flex: 1;
	padding: 10px;
	border-radius: 8px;
	margin-left: 10px;
	position: relative;
}

.comment-item-text::before {
	content: '';
	position: absolute;
	left: -7px;
	top: 10px;
	border-width: 10px 7px 10px 0;
	border-color: transparent #ffffff transparent transparent;
	border-style: solid;
	display: inline-block;
}

.comment-input-container {
	width: 70%;
	margin: 0 auto;
	display: flex;
	align-items: start;
	gap: 10px;
	border-top: 2px solid #FFA101;
	padding-top: 20px;
}

input:focus, textarea:focus {
	border-color: #FFA101;
	outline: none;
	/* 브라우저 기본 아웃라인 제거 */
	box-shadow: 0 0 3px #FFA101;
	/* 선택되었을 때 약간의 그림자 효과 추가 */
}

.submit-button {
	background-color: #FFA101;
	/* 배경색 설정 */
	border: none;
	/* 경계선 제거 */
	color: white;
	/* 텍스트 색상 설정 */
	padding: 20px 40px;
	/* 패딩 설정 */
	text-align: center;
	/* 텍스트 중앙 정렬 */
	text-decoration: none;
	/* 텍스트 장식 제거 */
	display: inline-block;
	/* 인라인 블록으로 표시 */
	font-size: 16px;
	/* 폰트 크기 설정 */
	margin: 4px 2px;
	/* 마진 설정 */
	cursor: pointer;
	/* 마우스 커서를 포인터로 설정 */
	border-radius: 4px;
	/* 모서리 둥글게 설정 */
	margin-top: 50px;
}

.application-section {
	text-align: center; /* 버튼을 중앙에 위치시킵니다. */
	margin-top: 20px; /* 상단 여백 추가 */
}

a:hover, button:hover, .submit-button:hover, .application-button:hover {
	transform: scale(1.05);
	/* 약간 확대 */
	transition: transform 0.3s;
	/* 부드럽게 전환 */
}

.application-button, .submit-button, .comment-item {
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
function addReply() {
    var currentDateTime = new Date().toISOString().slice(0, 16).replace('T', ' ');
    var formData = $("#commentForm").serialize() + 
    "&reply_id=1" +  
    "&momo_id=55" + 
    "&nickname=춘식이" +   // DB 정보 받아오기 - reply_id 시퀀스로 생성 / momo_id 상세보기의 mainboardDTO / nickname session
    "&replyDate=" + currentDateTime +
    "&fixDate=" + currentDateTime;
    $.ajax({
        url: "/app/addReply",
        type: "POST",
        data: formData,
        success: function(response) {
            if (response === "success") {
                window.location.href = "applicant";
            } else {
                alert("댓글 추가 실패");
            }
        },
        error: function() {
            alert("댓글 추가에 실패했습니다.");
        }
    });
}

function loadReplies() {
    $.ajax({
        url: "/app/getAllReplies",
        type: "GET",
        success: function(replies) {
            var html = '';
            $.each(replies, function(index, reply){ // 이미지 수정
                html += '<div class="comment-item" data-reply-id="' + reply.reply_id + '">'
                     + '<img src="images/avatar.png" alt="유저 프로필" class="comment-profile-img">'
                     + '<div class="comment-item-text">' + reply.replyContent + '</div>'
                     + '<button type="button" onclick="prepareUpdateReply(' + reply.reply_id + ');">수정</button>'
                     + '<button type="button" onclick="deleteReply(' + reply.reply_id + ');">삭제</button>'
                     + '</div>';
            });
            $('.comment-list').html(html);
        },
        error: function() {
            alert("댓글 로드 실패");
        }
    });
}

$(document).ready(function() {
    loadReplies();
});

function prepareUpdateReply(replyId) {
    var commentItem = $('div[data-reply-id="' + replyId + '"]');
    var currentContent = commentItem.find('.comment-item-text').text();
    
    var updateFormHtml = '<textarea class="comment-input" name="replyContent">' + currentContent + '</textarea>' +
        '<button type="button" onclick="updateReply(' + replyId + ');">저장</button>';

    commentItem.find('.comment-item-text').html(updateFormHtml);
}

function updateReply(replyId) {
    var commentItem = $('div[data-reply-id="' + replyId + '"]');
    var updatedContent = commentItem.find('textarea[name="replyContent"]').val();

    var formData = {
        reply_id: replyId,
        momo_id: 55,  // 예시 데이터; 필요에 따라 조정 -> DB에서 정보 불러오기
        nickname: "춘식이",  // 예시 데이터; 필요에 따라 조정
        replyContent: updatedContent,
        replyDate: new Date().toISOString().slice(0, 16).replace('T', ' '),
        fixDate: new Date().toISOString().slice(0, 16).replace('T', ' ')
    };
    
    $.ajax({
        url: "/app/updateReply",
        type: "POST",
        data: formData,
        success: function(response) {
            if (response === "success") {
                window.location.href = "application";
            } else {
                alert("댓글 수정 실패");
            }
        },
        error: function() {
            alert("댓글 수정에 실패했습니다.");
        }
    });
}

function deleteReply(replyId) {
    $.ajax({
        url: "/app/deleteReply",
        type: "POST",
        data: { id: replyId },  
        success: function(response) {
            if (response === "success") {
                window.location.reload(); 
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

<html>

<hr>
<!-- 댓글 섹션 시작 -->
<p class="comment_title">comment</p>
<div class="comment-section">

	<!-- 댓글 리스트 시작 -->
	<!-- 댓글 리스트 -->
	<div class="comment-list">
		<c:forEach items="${replies}" var="reply">
			<div class="comment-item" data-reply-id="${reply.reply_id}">
			<!-- 이미지 수정 -->
			<img src="images/avatar.png" alt="유저 프로필"
					class="comment-profile-img">
				<div class="comment-item-text">${reply.nickname}:
					${reply.replyContent}</div>
				<!-- 댓글 수정 및 삭제 버튼에 댓글 ID 전달 -->
				<button type="button"
					onclick="prepareUpdateReply(${reply.reply_id});">수정</button>
				<button type="button" onclick="deleteReply(${reply.reply_id});">삭제</button>
			</div>
		</c:forEach>
	</div>

	<!-- 댓글 리스트 끝 -->

	<form action="/addReply" method="post" id="commentForm">

		<!-- 댓글추가 -->
		<form id="replyForm">
			<div class="comment-input-container">
				<img src="images/avatar.png" alt="내 프로필 이미지"
					class="comment-profile-img">
				<textarea class="comment-input" name="replyContent"
					placeholder="댓글 작성"></textarea>
				<button type="button" onclick="addReply();">댓글 추가</button>

			</div>
		</form>


	</form>
</div>

<!-- 댓글 입력 부분 끝 -->

</body>

</html>