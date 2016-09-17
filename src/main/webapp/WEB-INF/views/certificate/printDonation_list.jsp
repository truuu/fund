<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
a#donationBtn {
	float: right;
}
form.pagination{ width:100%; }
</style>
<body>

	<h3>기부증서 발급대장</h3>
	<form:form method="get" modelAttribute="pagination" class="pagination">
		<input type="hidden" name="pg" value="1" />
		<form:hidden path="bd" />
	

	<div class="form-inline">
			<form:select path="ss" id="search" class="msize">
				<form:option value="0" label="검색조건" />
				<form:option value="1" label="이름" />
				<form:option value="2" label="발행일" />
				<form:option value="3" label="약정액" />
			</form:select>
			<form:input path="st" />
			<button type="submit" class="btn btn-small">검색</button>
		
		<a href="printDonation.do" id="donationBtn"
				class="button button-reversed">발급하기</a>
	</div>

	<div class="table-responsive">
		<table id="table1" class="table table-bordered">
			<thead>
				<tr>
					<th>일련번호</th>
					<th>이름</th>
					<th>약정액</th>
					<th>발행일</th>
					<th>발행인</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="donation" items="${ list }">
					<tr>
						<td>${ donation.ID }</td>
						<td>${ donation.sponsorName }</td>
						<td>${ donation.amount }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd"
								value="${ donation.createDate }" /></td>
						<td>${ donation.name }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<div class="pagination pagination-small pagination-centered">
		<ul>
			<c:forEach var="page" items="${ pagination.pageList }">
				<li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
			</c:forEach>
		</ul>
	</div>

</form:form>

</body>