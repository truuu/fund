<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<body>
	<h3>기부증서 발급대장</h3>

	<div class="input-group1">
		<select name="search" id="search" class="msize">
			<option selected="selected" value="검색조건">검색조건</option>
			<option value="0">전체</option>
			<option value="1">이름</option>
			<option value="2">발행일</option>
			<option value="3">약정액</option>
		</select> <input type="text" id="search_a" name="search_a">
		<button type="submit" class="btn btn-small">검색</button>
	</div>

	<div class="table-responsive">
		<table class="table table-bordered">
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



</body>