// profile_mymomo.js

$(document).ready(function() {
	$(".applicant_button").on("click", function() {
		var applicant_id = $(this).attr("data-applicant_id");	
		if(confirm("정말로 모임신청을 취소하시겠습니까?")){
		$.ajax({

			type : "get",
			url : "cancelApplicant",
			data : {
				applicant_id : applicant_id
			},
			dataType : "text", //응답받을 데이터 타입
			success : function(data, status, xhr) {
				alert("신청이 취소되었습니다.");
				location.reload();
			},
			error : function(xhr, status, error) {
				console.log("에러발생")
				alert("신청 취소중 에러가 발생했습니다.");
			}
		})}
		else{
		}
	});
});


function markDelete(momo_id) {

	$.ajax({
		url : 'markDelete',
		type : 'get',
		data : {
			momo_id:momo_id
		},
		success : function(data, status, xhr) {
				if(data=="success"){
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