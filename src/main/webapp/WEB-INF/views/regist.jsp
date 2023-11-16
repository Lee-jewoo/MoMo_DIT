<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두 모여라, MoMo</title>
</head>
<body>
   <jsp:include page="common/top.jsp" flush="true" /><br>
   <jsp:include page="momo/regist.jsp" flush="true" /><br>
</body>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="js/image_preview2.js"></script>
<script>
   function checkNickname() {

      if ($('#nickname').val() != '') {

         // 아이디를 서버로 전송 > DB 유효성 검사 > 결과 반환받기
         $.ajax({

            type : 'GET',
            url : 'login/idcheck.action',
            data : 'nickname=' + $('#nickname').val(),
            dataType : 'json',
            success : function(result) {
               if (result == '0') {
                  $('#result').text('사용 가능한 아이디입니다.');
               } else {
                  $('#result').text('이미 사용중인 아이디입니다.');
               }
            },
            error : function(a, b, c) {
               console.log(a, b, c);
            }

         });

      } else {
         alert('아이디를 입력하세요.');
         $('#nickname').focus();
      }
   }

   function checkPasswordMatch() {
      var password = document.getElementsByName("passwd")[0].value;
      var confirmPassword = document.getElementsByName("passwd2")[0].value;

      if (password === confirmPassword) {
         document.getElementById("passwordMatch").innerHTML = "비밀번호가 일치합니다";
         document.getElementById("passwordMatch").style.color = "green";
      } else {
         document.getElementById("passwordMatch").innerHTML = "비밀번호가 일치하지 않습니다";
         document.getElementById("passwordMatch").style.color = "red";
      }
   }

   //비밀번호 양식확인
	function isValidPassword(passwd) {
	    var regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*])[A-Za-z\d~!@#$%^&*]{10,16}$/;
	    return regex.test(passwd);
	}
	
	// 닉네임 중복 여부/검사여부 확인
	function idcheck() {
		if ($('#Authentication').text() == '') {
			alert("이메일 인증을 하지 않았습니다. 다시 시도해주세요.");
			event.preventDefault();
			}
			else if ($('#result').text() == '') {
			alert("별명 중복검사가 실행되지 않았습니다. 다시 시도해주세요.");
			event.preventDefault();
		} else if ($('#result').text() == '이미 사용중인 아이디입니다.') {
			alert("중복된 별명이 이미 존재합니다. 다시 시도해주세요.");
			event.preventDefault();
		}  else if(!isValidPassword(passwd))  {
			  alert("비밀번호양식을 다시 확인해주시기 바랍니다.");
			event.preventDefault();
		} else {
			if($('input:radio[name=agree]:checked').val()=='disagree') {
				alert("개인정보 이용을 동의해주세요.");
				event.preventDefault();
			} else {
				alert("회원가입이 완료되었습니다. 로그인 화면으로 이동합니다.");
			}
		}
	}
   
   function byteCheck( obj ) {

       var calByte = {
          // 현재 바이트 확인
           getByteLength : function( string ) {

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

                   if(size == length) {
                       rIndex = num + 1;
                       break;
                   }
               }
               return string.substring(0, rIndex);
           }
           // 글자 종류별 바이트 크기 설정
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
</script>

</html>