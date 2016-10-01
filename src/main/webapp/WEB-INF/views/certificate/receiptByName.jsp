<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
$(function(){
		$("button[value=deleteRct]").click(function() {
		alert("선택한 영수증을 삭제하시겠습니까?");
	});
});
</script>
<h1>기부금 영수증 개별발급</h1>
<hr/>
<form:form method="post" modelAttribute="pagination" action="receiptByName.do">
	<input type="hidden" name="bd" value="3" />
	<div class="condition">
			<p>
				기간 : <form:input type="date" path="sd" name="startDate"/>~<form:input type="date" path="ed" />
				&nbsp;&nbsp; 이름 : <form:input type="text" path="st"/>
				&nbsp;&nbsp; 기관 : 
				<form:select path="cp" name="corporateID">
					<form:option value="0" label="검색조건"/>
					<form:option value="1" label="학교"/>
					<form:option value="2" label="법인"/>
				</form:select>
				&nbsp;&nbsp;<button type="submit" class="btn btn-small" name="cmd" value="search">검색</button>
			</p>
	</div>
	<div class="select">
			<table class="table table-bordered">
			    <thead>
			        <tr>
			            <th>체크박스/영수증번호</th>
			            <th>후원인번호</th>
			            <th>후원인명</th>
			            <th>정기/비정기</th>
			            <th>납입일</th>
			            <th>금액</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach var="payment" items="${ paymentList }">
			        	<tr>
			            	<td><c:if test="${ payment.receiptID != 0 }">
			            			<button type="submit" class="btn btn-info" name="cmd" value="deleteRct">
			            			<input type="hidden" name="delid" value="${payment.receiptID}"/> 삭제</button>&nbsp;&nbsp;${ payment.rctNo }</c:if>
			            		   <c:if test="${ payment.receiptID == 0 }"><input type="checkbox" name="pid" value="${ payment.id }"></c:if></td>
			             	<td>${ payment.sponsorNo }</td>
			             	<td>${ payment.name }</td>
			             	<td><c:if test="${ payment.commitmentID != 0}">정기 </c:if>
			             	 	   <c:if test="${ payment.commitmentID == 0}">비정기</c:if></td>
			             	<td class="date">${ payment.paymentDate }</td>
			             	<td class="money">${ payment.amount }</td>
			           </tr>
			        </c:forEach>
			    </tbody>
			</table>		
	
	</div>

<div class="Issue">
	<p>발급일자 : <input type="date" name="createDate">&nbsp;&nbsp;<button type="submit" class="btn btn-small" name="cmd" value="issueRct">기부금 영수증 발급</button></p>
	<c:if test="${ error != null }"><div class="alert alert-error">${error}</div></c:if>
</div>
</form:form>