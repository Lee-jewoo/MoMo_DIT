<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두 모여라, MoMo</title>
<link rel="stylesheet" href="css/mainSelectCategory.css"> 
</head>
<body>
	<jsp:include page="common/top.jsp" flush="true" /><br>
	<jsp:include page="common/left_menu2.jsp" flush="true"/><br>
	<jsp:include page="momo/main.jsp" flush="true" /><br>
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/loc_cate_select.js"></script>
	<script>
		function cate_default_remove() {
			$("#cate_default").remove();
		}
	</script>
</html>