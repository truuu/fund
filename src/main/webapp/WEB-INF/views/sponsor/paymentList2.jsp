<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="tab4" value="active" />
<%@include file="_tab.jsp" %>

<div class="pull-right mt4 mb4">
  <a href="paymentNew.do?${q}" class="btn btn-primary">새로 등록</a>
</div>    

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
  <c:forEach var="payment" items="${ list }">
    <tbody>
      <tr>
        <td>${ payment.paymentMethod }</td>
        <td class="right"><fmt:formatNumber value="${ payment.amount}" /></td>
        <td><fmt:formatDate value="${ payment.paymentDate}" pattern="yyyy-MM-dd" /></td>
        <td>${ payment.corporate } / ${ payment.organizationName } / ${ payment.donationPurpose }</td>
        <td>${ payment.etc }</td>
      </tr>
    </tbody>
  </c:forEach>
</table>
