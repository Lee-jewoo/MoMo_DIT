// loc_cate_select.js
function loc_select() {
	let loc_num1 = $("#loc_num1 option:selected").val();
	$.ajax({
		url : 'locList2',
		type : 'POST',
		data : loc_num1,
		contentType : 'application/json', /* 보내는 loc_num1 */
		dataType : "json", /* 받는 locList2 */
		success : function(data) {
			var options = "";
			for (var i = 0; i < data.length; i++) {
				options += "<option value='" + data[i].loc_num + "'>"
						+ data[i].loc + "</option>"
			}
			$("#loc_num2").html(options);
			$("#loc_default").remove();
		},
		error : function() {
			alert("잘못된 요청입니다.");
		}
	})
}

function cate_select() {
	let cate_num1 = $("#cate_num1 option:selected").val();
	$.ajax({
		url : 'cateList2',
		type : 'POST',
		data : cate_num1,
		contentType : 'application/json', /* 보내는 cate_num1 */
		dataType : "json", /* 받는 cateList2 */
		success : function(data) {
			var options = "";
			for (var i = 0; i < data.length; i++) {
				options += "<option value='" + data[i].cate_num + "'>"
				+ data[i].cate + "</option>"
			}
			$("#cate_num2").html(options);
			$("#cate_default").remove();
		},
		error : function() {
			alert("잘못된 요청입니다.");
		}
	})
}
