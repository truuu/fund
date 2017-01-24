<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="tab2" value="active" /> 
<%@include file="_tab.jsp" %>

<div class="pull-right mt4 mb4">
  <button type="button" class="btn btn-primary">새로 등록</button>
  <a href="list.do?${ pagination.queryString }" class="btn btn-info">후원인 목록</a>
</div>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>약정번호</th>
      <th>기부목적</th>
      <th>납입방법</th>
      <th>약정일</th>
      <th>기간</th>
      <th>약정상태</th>
      <th class="right">월납입액</th>
      <th class="right">납입일</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="commitment" items="${ list }">
      <tr data-url="commitmentEdit.do?ID=${commitment.ID}">
        <td>${ commitment.commitmentNo }</td>
        <td>${ commitment.donationPurposeName }</td>
        <td>${ commitment.paymentMethodName }</td>
        <td>${ commitment.commitmentDate }</td>
        <td>${ commitment.startDate } ~ ${ commitment.endDate } (${ commitment.months } 개월)
        </td>
        <td>${ commitment.state }</td>
        <td class="right"><fmt:formatNumber value="${ commitment.amountPerMonth }" type="number" /></td>
        <td class="right">${ commitment.paymentDay }</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<c:if test="${ list.size() == 0 }">
    <div class="alert alert-info">약정이 없습니다.</div>
</c:if>
