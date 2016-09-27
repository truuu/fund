<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</head>
<body>
	<h2 class="page-header">기부목적 추가</h2>
	<form method="post">
		<table class="table">
			<tr>

				<td id="table_a">기관</td>
				<td><select name="corporateID">
						<c:forEach var="corporate" items="${corporateList}">
							<option value="${corporate.ID}">${corporate.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td id="table_a">기관종류</td>
				<td><select name="organizationID">
						<c:forEach var="organization" items="${organizationList}">
							<option value="${organization.ID}">${organization.codeName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td id="table_a">기부목적</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td id="table_a">구분</td>
				<td><input type="text" name="gubun" /></td>
			</tr>
		</table>
		<span>
		<button type="submit" id="btn3" class="btn btn-primary">
			<i class="icon-ok icon-white"></i> 저장하기
		</button></span>
		<a href="donationPurposeList.do" class="btn btn-default"> <i
			class="icon-ban-circle"></i> 취소
		</a>

	</form>

</body>
</html>