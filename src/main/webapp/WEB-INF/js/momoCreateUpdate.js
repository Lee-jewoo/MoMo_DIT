// momoCreate.js

// 입력값이 없을 때 처리
function momoCreateUpdate(){
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
      cate_num1.focus();
      event.preventDefault();
   } else {
	   if (cate_num2.value == "-- 태그 선택 --"){
		   alert("태그(소분류) 입력하세요.");
		   cate_num2.focus();
		   event.preventDefault();
	   } else {
		   if (momoName.value == ""){
			   alert("모모 이름을 입력하세요.");
			   momoName.focus();
			   event.preventDefault();
		   } else {
			   if (momoIntro.value == ""){
				   alert("한 줄 소개를 입력하세요.");
				   momoIntro.focus();
				   event.preventDefault();
			   } else {
				   if (momoDetail.value == ""){
					   alert("상세소개를 입력하세요.");
					   momoDetail.focus();
					   event.preventDefault();
				   } else {
					   if (loc_num1.value == "-- 시/도 선택 --"){
						   alert("시/도를 입력하세요.");
						   loc_num1.focus();
						   event.preventDefault();
					   } else {
						   if (loc_num2.value == "-- 시/구 선택 --"){
							   alert("시/구를 입력하세요.");
							   loc_num2.focus();
							   event.preventDefault();
						   } else {
							   if (momoLoc.value == ""){
								   alert("상세 장소를 입력하세요.");
								   momoLoc.focus();
								   event.preventDefault();
							   } else {
								   if (momoDate.value == ""){
									   alert("모모 일자를 입력하세요.");
									   momoDate.focus();
									   event.preventDefault();
								   } else {
									   if (momoTime.value == ""){
										   alert("상세 시간을 입력하세요.");
										   momoTime.focus();
										   event.preventDefault();
									   } else {
										   if (fee.value == ""){
											   alert("참가비를 입력하세요. 없으면 0을 입력하세요.");
											   fee.focus();
											   event.preventDefault();
										   } else {
											   if (maxHeadcount.value.length == 0){
												   alert("최대 인원을 입력하세요.");
												   maxHeadcount.focus();
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

// 숫자가 아닌 값은 입력되지 않게 처리
function onlyNumber(){
  	event.target.value = event.target.value.replace(/\D/g, "");
}

// 최대 바이트 크기를 넘었을 때 처리
function byteCheck(obj) {
    var calByte={
    	// 현재 바이트 확인
        getByteLength : function(string) {
            if(string == null || string.length == 0) {
                return 0;
            }
            let size = 0;
            for(let num = 0; num < string.length; num++) {
                size += this.charByteSize(string.charAt(num));
            }
            return size;
        }
    	// 최대 바이트를 넘는 글자 제거
        , cutByteLength : function(string, length) {
            if(string == null || string.length == 0) {
                return 0;
            }
            let size = 0;
            let rIndex = string.length;
            for(let num = 0; num < string.length; num++) {
                size += this.charByteSize(string.charAt(num));
                if(size >= length) {
                    rIndex = num + 1;
                    break;
                }
            }
            return string.substring(0, rIndex);
        }
        // 글자 종류별 바이트 크기 설정(유니코드 활용)
        , charByteSize : function(ch) {
            if(ch == null || ch.length == 0) {
                return 0;
            }
            let charCode = ch.charCodeAt(0);
            if(charCode <= 0x00007F) {
                return 1;
            } else if(charCode <= 0x0007FF) {
                return 2;
            } else if(charCode <= 0x00FFFF) {
                return 3;
            } else {
                return 4;
            }
        }
    };
    if( calByte.getByteLength(obj.value) > obj.dataset.byte) {
        alert("작성할 수 있는 글자 수를 초과하였습니다. -"+obj.dataset.byte+"byte제한");
        obj.value = calByte.cutByteLength(obj.value, obj.dataset.byte);
    }
}



