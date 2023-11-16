// momoCancel.js
function momoCancel(){
	if(!confirm("취소 신청서를 제출하시겠습니까?")){
		event.preventDefault();
	}
}
