<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두 모여라, MoMo</title>
</head>
<body>
	<jsp:include page="common/top.jsp" flush="true"/><br>
	<jsp:include page="common/left_menu.jsp" flush="true"/><br>
	<jsp:include page="momo/momoCreateForm.jsp" flush="true"/><br>
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/image_preview.js"></script>
	<script type="text/javascript" src="js/loc_cate_select.js"></script>
	<script type="text/javascript" src="js/momoCreateUpdate.js"></script>
	<script type="text/javascript" src="js/byteCheck.js"></script>
	<script type="text/javascript">
		document.getElementById('momoDate').min = new Date().toISOString().substring(0, 10);
		document.getElementById('momoDate').value = new Date().toISOString().substring(0, 10);
	</script>
</html>