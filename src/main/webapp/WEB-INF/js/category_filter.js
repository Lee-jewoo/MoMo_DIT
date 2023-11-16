//category_filter.js

function cateFilter(){
	$.ajax({
		url: '',
		type: 'GET',
		success: function(){
			
		},
		error: function(){
			
		}
	})
}

// 이벤트 리스너를 사용하여 클릭 이벤트를 처리
document.getElementById('exercise').addEventListener('click', function(){
	cateFilter();
	//main.jsp로 이동
	window.location.href = 'main';
});