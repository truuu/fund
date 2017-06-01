<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="sum" value="${ 0 }" />
<div style="width: 100%; overflow-x: scroll;">
  <div  style="width:2500px;" id="xscroll">
    <table class="table table-bordered mt20" style="width:auto;">
      <thead>
        <tr>
          <th style="width:130px">약정번호</th>
          <th style="width:80px">납입방법</th>
          <th style="width:100px"class="right">납입금액</th>
          <th style="width:100px">납입일</th>
          <th style="width:140px">계좌번호</th>
          <th style="width:70px">은행</th>
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
            <td style="font-size: 9pt;">${ p.corporateName } / ${ p.organizationName } / ${ p.donationPurposeName }</td>
            <td style="font-size: 9pt;">${ fn:substring(p.etc, 0, 80) }</td>
            <c:set var="sum" value="${ sum + p.amount }" />
          </tr>
        </c:forEach>
        <tr>
          <td>합계</td>
          <td class="right"><fmt:formatNumber value="${ sum }" /></td>
          <td colspan="6"></td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
