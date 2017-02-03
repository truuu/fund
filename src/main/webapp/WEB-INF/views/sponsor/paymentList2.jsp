<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="tab4" value="active" />
<%@include file="_tab.jsp" %>

<c:set var="pg" value="${ pagination.queryString }" />
<c:set var="sid" value="${ sponsor.id }" />

<div class="pull-right mt4 mb4">
  <a href="paymentNew2.do?sid=${sid}&${pg}" class="btn btn-primary">새로 등록</a>
</div>

<c:set var="sum" value="${ 0 }" />
<table class="table table-bordered mt20">
  <thead>
    <tr>
      <th>납입방법</th>
      <th class="right">납입금액</th>
      <th>납입일</th>
      <th>기부목적</th>
      <th>비고</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="payment" items="${ list }">
      <tr data-url="paymentEdit2.do?id=${payment.id}&sid=${sid}&${pg}">
        <td>${ payment.paymentMethodName }</td>
        <td class="right"><fmt:formatNumber value="${ payment.amount}" /></td>
        <td>${ payment.paymentDate}</td>
        <td>${ payment.corporateName } / ${ payment.organizationName } / ${ payment.donationPurposeName }</td>
        <td>${ payment.etc }</td>
      </tr>
      <c:set var="sum" value="${ sum + payment.amount }" />
    </c:forEach>
    <tr>
      <td>합계</td>
      <td class="right"><fmt:formatNumber value="${ sum }" /></td>
      <td colspan="3"></td>
    </tr>    
  </tbody>
</table>

