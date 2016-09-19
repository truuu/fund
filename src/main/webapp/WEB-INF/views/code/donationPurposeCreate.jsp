<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 class="page-header">기부목적 추가</h2>
	<form method="post">
		<div>
			<span>기관:</span> <select name="corporateID">
				<c:forEach var="corporate" items="${corporateList}">
					<option value="${corporate.ID}">${corporate.name}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<span>기관종류:</span> <select name="organizationID">
				<c:forEach var="organization" items="${organizationList}">
					<option value="${organization.ID}">${organization.codeName}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<span>기부목적:</span> <input type="text" name="name" />
		</div>
		<div>
			<span>구분:</span> <input type="text" name="gubun" />
		</div>

		<button type="submit" class="btn btn-primary">
			<i class="icon-ok icon-white"></i> 저장하기
		</button>
		<a href="donationPurposeList.do" class="btn btn-default"> <i
			class="icon-ban-circle"></i> 취소
		</a>

	</form>

</body>
</html>