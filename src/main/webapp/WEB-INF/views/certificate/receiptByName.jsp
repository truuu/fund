<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>기부금 영수증 개별발급</h1>
<hr/>
<form:form method="post" modelAttribute="pagination">
	<div class="condition">
			<p>
				기간 : <input type="date" name="startDate" value="${pagination.startDate}" />~<input type="date" name="endDate" value="${pagination.endDate}" />
				&nbsp;&nbsp; 이름 : <input type="text" name="srchText" value='${pagination.srchText }'/>
				&nbsp;&nbsp; 기관 : 
				<select name="corporateID">
					<option value="0">기관선택</option>
					<option value="1">학교</option>
					<option value="2">법인</option>
				</select>
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
			             	<td>${ payment.amount }</td>
			           </tr>
			        </c:forEach>
			    </tbody>
			</table>		
	
	</div>
</form:form>
<div class="Issue">
	<p>발급일자 : <input type="date" name="createDate">&nbsp;&nbsp;<button type="submit" class="btn btn-small" name="cmd" value="issueRct">기부금 영수증 발급</button></p>
</div>