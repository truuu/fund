<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<h2 class="page-header">기관 추가</h2>
	<form method="post">
		<table class="table">
			<tr>
				<td id="table_a">이름</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td id="table_a">기관번호</td>
				<td><input type="text" name="corporateNo" /></td>
			</tr>
			<tr>
				<td id="table_a">대표자명</td>
				<td><input type="text" name="representative" /></td>
			</tr>
			<tr>
				<td id="table_a">주소</td>
				<td><input type="text" name="address" /></td>
			</tr>
		</table>

		<span><button type="submit" id="btn3" class="btn btn-primary">
				<i class="icon-ok icon-white"></i> 저장하기
			</button></span> <a href="corporateList.do" class="btn btn-default"> <i
			class="icon-ban-circle"></i> 취소
		</a>

	</form>

</body>
</html>