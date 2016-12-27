<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="head" />
</head>
<body>

<tiles:insertAttribute name="menu" />

	<div class="container">
		<div style="min-height: 700px">
			<c:if test="${ not empty errorMsg }">
				<center>
				<div class="alert alert-danger" style="width:700px">${ errorMsg }</div>
				</center>
			</c:if>
			<c:if test="${ not empty successMsg }">
				<center>
				<div class="alert alert-success" style="width:700px">${ successMsg }</div>
				</center>
			</c:if>
			<tiles:insertAttribute name="content" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
