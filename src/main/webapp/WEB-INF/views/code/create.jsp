<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
table.table {
	width: 40%;
}

#table_a {
	width: 50%;
}

#btn3 {
	margin-left: 25%;
}
</style>
<body>
	<h2 class="page-header">${name} 추가</h2>
	<form method="post">
		<table class="table">
			<tr>
				<td id="table_a">코드명</td>
				<td><input type="text" name="codeName" /> <br>${error}</td>
				
			</tr>
			<tr>
				<td id="table_a">기타</td>
				<td><input type="text" name="etc1" /></td>
			</tr>
		</table>
		

		<button type="submit" id="btn3" class="btn btn-primary">
			<i class="icon-ok icon-white"></i> 저장하기
		</button>
		<a href="codeList.do?CodeGroupID=${ CodeGroupID }"
			class="btn btn-default"> <i class="icon-ban-circle"></i> 취소
		</a>

	</form>
</body>
</html>