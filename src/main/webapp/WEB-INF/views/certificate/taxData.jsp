<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>국세청 보고자료</h1>
<hr/>
<form:form method="get" modelAttribute="pagination">
	<input type="hidden" name="pg" value="1"/>
	<input type="hidden" name="bd" value="1"/>
	<div class="form-inline">
	 <!--  기간과 기관 함께 검색  -->
		기간:
		<form:input type="date" path="sd"/>~
		<form:input type="date" path="ed" />
		기관종류<form:select path="cp">
			<form:option value="0" label="검색조건" />
			<form:option value="1" label="학교" />
			<form:option value="2" label="법인"/>
		</form:select>

		<button type="submit" class="btn btn-small">검색</button>
		<c:if test="${ pagination.ss != 0 }">
			<a href="taxData.do" class="btn btn-small">취소</a>
		</c:if>
		<button class="btn" type="button">조회</button>
	</div>
	
	<div class="pull-right">
		<button class="btn" type="button">엑셀파일</button>
	</div>
	
	<div class="taxData_List">	
		<table class="table table-bordered">
		    <thead>
		        <tr>
		            <th>주민번호</th>
		            <th>이름</th>
		            <th>기부내용구분</th>
		            <th>기부금단체코드</th>
		            <th>기부일자</th>
		            <th>기부금액</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="data" items="${ taxDataList }">
		        	<tr>
		            	<td>${ data.juminNo }</td>
		             	<td>${ data.name }</td>
		             	<td>1: 금전기부 고정</td>
		             	<td>BBA 고정</td>
		             	<td>${ data.paymentDate }</td>
		             	<td>${ data.amount }</td>
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
