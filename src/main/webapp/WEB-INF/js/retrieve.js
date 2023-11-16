// retrieve.js

//페이지 로드 시 실행
$(document).ready(function() {
	initApplicationButton();
});

// 버튼 초기화 및 이벤트 바인딩
function initApplicationButton() {
	var applicationButton = $(".application-button");
	var momo_id = applicationButton.attr("data-momo_id");
	var nickname = $("#nickname").val();

	// 상태 체크 및 초기 이벤트 바인딩
	checkApplicationStatus(momo_id, nickname, function(state) {
		updateButtonState(applicationButton, state, momo_id, nickname);
	});
}

// 신청 상태 확인 함수 -> 호스트 / 이미 신청한 사람 / 그 외
function checkApplicationStatus(momo_id, nickname, callback) {
	$.ajax({
		url : "checkApplicant",
		type : "POST",
		data : {
			momo_id : momo_id,
			nickname : nickname
		},
		success : function(response) {
			callback(response.status);
		},
		error : function() {
			alert("상태 확인 실패");
		}
	});
}

// 신청 상태에 따라 버튼 업데이트
function updateButtonState($button, status, momo_id, nickname) {
	switch (status) {
	case "guest":
		$("#button").val("참여완료");
		break;
	case "applicant":
		$("#button").val("신청완료");
		break;
	case "host":
		$("#button").val("수정하기").prop("disabled", false).off("click").on("click", function() {
			location.href = "momoUpdateForm?momo_id=" + momo_id;
		});
		break;
	case "not_applied":
	default:
		var maxHeadcount = $("#maxHeadcount").val();
		var headcount = $("#headcount").val();
		var status = $("#status").val();
		if(status==0) {
			$("#button").val("신청하기").prop("disabled", false).on("click", function() {
				if (headcount >= maxHeadcount){
					alert("인원이 가득 찼습니다.");
				} else {
					location.href = "momoApplicantForm?momo_id=" + momo_id;
				}
			});
		} else {
			$("#button").val("모집 종료")
		}
		break;
	}
	
}
