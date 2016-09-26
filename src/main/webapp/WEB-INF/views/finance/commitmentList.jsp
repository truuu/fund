<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="table-responsive">
	<table class="table table-bordered" id="table_s">
		<thead>
			<tr>
				<th>약정 번호</th>
				<th>납입 방법</th>
				<th>기부 목적</th>
			</tr>
		</thead>
		<c:forEach var="list" items="${list}">
			<tbody>
				<tr>
					<td>${list.commitmentNo}</td>
					<td>${list.codeName}</td>
					<td>${list.donationPurposeName}</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</div>
