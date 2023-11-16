// byteCheck.js


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
