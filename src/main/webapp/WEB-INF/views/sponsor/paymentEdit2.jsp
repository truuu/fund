<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="tab4" value="active" />
<%@include file="_tab.jsp" %>

<form:form method="post" modelAttribute="payment">

<div class="pull-right mt4 mb4">
  <button type="submit" class="btn btn-primary" name="cmd" value="save">저장</button>
  <c:if test="${ payment.id > 0 }">
    <button type="submit" class="btn btn-danger" name="cmd" value="delete" data-confirm-delete>삭제</button>
  </c:if>    
  <a href="paymentList2.do?sid=${ sponsor.id }&${ pagination.queryString }" class="btn btn-info">납입 목록</a>
</div>

<table class="table table-bordered lbw150">
  <tr>
    <td class="lb">납입일</td>
    <td><form:input path="paymentDate" class="date" /></td>
    <td class="lb">납입방법</td>
    <td><form:select path="paymentMethodId" itemValue="id" itemLabel="codeName" items="${ paymentMethods }" /></td>
  </tr>
  <tr>
    <td class="lb">납입액</td>
    <td><form:input path="amount" class="money" /></td>
    <td class="lb">기부목적</td>
    <td>
        <c:set var="paramObj" value="${ payment }" />
        <%@include file="_donationPurposeInput.jsp" %>
    </td>
  </tr>
  <tr>  
    <td class="lb">비고</td>
    <td colspan="3"><form:textarea path="etc" class="w600 h100" /></td>
  </tr>
</table>  

</form:form>

<%@include file="_donationPurposeDialog.jsp" %>
