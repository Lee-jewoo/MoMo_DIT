// momoCreate.js
function momoCreate(){
   var cate_num1 = document.querySelector("#cate_num1");
   var cate_num2 = document.querySelector("#cate_num2");
   var momoName = document.querySelector("#momoName");
   var momoIntro = document.querySelector("#momoIntro");
   var momoDetail = document.querySelector("#momoDetail");
   var loc_num1 = document.querySelector("#loc_num1");
   var loc_num2 = document.querySelector("#loc_num2");
   var momoLoc = document.querySelector("#momoLoc");
   var momoDate = document.querySelector("#momoDate");
   var momoTime = document.querySelector("#momoTime");
   var fee = document.querySelector("#fee");
   var maxHeadcount = document.querySelector("#maxHeadcount");
   
   if (cate_num1.value == "-- 카테고리 선택 --"){
      alert("카테고리(대분류)를 입력하세요.");
      event.preventDefault();
   } else {
	   if (cate_num2.value == "-- 태그 선택 --"){
		   alert("태그(소분류) 입력하세요.");
		   event.preventDefault();
	   } else {
		   if (momoName.value == ""){
			   alert("모모 이름을 입력하세요.");
			   event.preventDefault();
		   } else {
			   if (momoIntro.value == ""){
				   alert("한 줄 소개를 입력하세요.");
				   event.preventDefault();
			   } else {
				   if (momoDetail.value == ""){
					   alert("상세소개를 입력하세요.");
					   event.preventDefault();
				   } else {
					   if (loc_num1.value == "-- 시/도 선택 --"){
						   alert("시/도를 입력하세요.");
						   event.preventDefault();
					   } else {
						   if (loc_num2.value == "-- 시/구 선택 --"){
							   alert("시/구를 입력하세요.");
							   event.preventDefault();
						   } else {
							   if (momoLoc.value == ""){
								   alert("상세 장소를 입력하세요.");
								   event.preventDefault();
							   } else {
								   if (momoLoc.value == ""){
									   alert("상세 장소를 입력하세요.");
									   event.preventDefault();
								   } else {
									   if (momoDate.value == ""){
										   alert("모모 일자를 입력하세요.");
										   event.preventDefault();
									   } else {
										   if (momoTime.value == ""){
											   alert("상세 시간을 입력하세요.");
											   event.preventDefault();
										   } else {
											   if (fee.value == ""){
												   alert("참가비를 입력하세요. 없으면 0을 입력하세요.");
												   event.preventDefault();
											   } else {
												   if (maxHeadcount.value == ""){
													   alert("최대 인원을 입력하세요.");
													   event.preventDefault();
												   }
											   }
										   }
									   }
								   }
							   }
						   }
					   }
				   }
			   }
		   }
	   }
   }


}
