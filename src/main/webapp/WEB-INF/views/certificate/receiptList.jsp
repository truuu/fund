<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	$(function() {
		$("tbody tr").click(function() {
			location.href=$(this).attr("data-url");
		});
		$("div.pagination a").click(function() {
			$("input[name=pg]").val($(this).attr("data-page"));
			$("form").submit();
		});
	});
</script>

<h1>기부금 영수증 발급대장</h1>
<hr/>

<form:form method="get" modelAttribute="pagination">
	<input type="hidden" name="pg" value="1"/>
	<div class="form-inline">
		<form:select path="ss">
			<form:option value="0" label="검색조건" />
			<form:option value="1" label="기간" />
			<form:option value="2" label="후원자명" />
		</form:select>
		<form:input path="st" />
		<button type="submit" class="btn btn-small">검색</button>
		<c:if test="${ pagination.ss != 0 }">
			<a href="receiptList.do" class="btn btn-small">취소</a>
		</c:if>
		<button class="btn" type="button">조회</button>
		<button class="btn" type="submit" name="type" value="pdf">인쇄</button>
	</div>
	
		<!-- 기부금영수증 발급버튼 두 가지  -->
	<div class="pull-right">
		<a href="deleteReceipts.do" class="btn btn-info">선택삭제</a>
		<a href="receiptByDur.do" class="btn btn-info">기부금영수증 일괄발급</a>
		<a href="receiptByName.do" class="btn btn-info">기부금영수증 개별발급</a>
	</div>
	
	<div class="receipt_List">	
		<table class="table table-bordered">
		    <thead>
		        <tr>
		            <th><input type="checkbox"></th>
		            <th>영수증번호</th>
		            <th>후원인 이름</th>
		            <th>주민번호 / 사업자번호</th>
		            <th>연락처</th>
		            <th>기부금액</th>
		            <th>영수증발급일자</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="receipt" items="${ receiptList }">
		        	<tr data-url="receiptView.do?id=${ receipt.id }&${ pagination.queryString}">
		            	<td><input type="checkbox" name="rid" value="${ receipt.id  }">${ receipt.id  }</td>
		             	<td>${ receipt.no }</td>
		             	<td>${ receipt.name }</td>
		             	<td>${ receipt.juminNo }</td>
		             	<td>${ receipt.mobilePhone }</td>
		             	<td>${ receipt.amount }</td>
		             	<td>${ receipt.createDate }</td>
		           </tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
	
	<div class="pagination">
		<ul class="pagination pagination-sm">
			<c:forEach var="page" items="${pagination.pageList }">
				<li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
			</c:forEach>
		</ul>
	</div>
</form:form>
