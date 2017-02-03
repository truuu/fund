<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="sum" value="${ 0 }" />
<table class="table table-bordered mt20">
  <thead>
    <tr>
      <th>약정번호</th>
      <th>납입방법</th>
      <th class="right">납입금액</th>
      <th>납입일</th>
      <th>계좌번호</th>
      <th>은행</th>
      <th>예금주</th>
      <th>기부목적</th>
      <th>비고</th>
    </tr>
  </thead>
    <tbody>
    <c:forEach var="p" items="${ list }">
      <tr>
        <td>${ p.commitmentNo }</td>
        <td>${ p.paymentMethodName }</td>
        <td class="right"><fmt:formatNumber value="${ p.amount}" /></td>
        <td>${ p.paymentDate}</td>
        <td>${ p.accountNo }</td>
        <td>${ p.bankName }</td>
        <td>${ p.accountHolder }</td>
        <td>${ p.corporateName } / ${ p.organizationName } / ${ p.donationPurposeName }</td>
        <td>${ p.etc }</td>
        <c:set var="sum" value="${ sum + p.amount }" />
      </tr>
    </c:forEach>
    <tr>
      <td colspan="2">합계</td>
      <td class="right"><fmt:formatNumber value="${ sum }" /></td>
      <td colspan="6"></td>
    </tr>
  </tbody>
</table>
