<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>영수증</h1>
<hr />

<div class="pull-right mb4">
  <a class="btn btn-primary" href="report.do?rid=${ receipt.id }">영수증 다운로드</a>
  <a class="btn btn-info" href="list.do?${ pagination.queryString }">목록으로</a>
  <a class="btn btn-danger" href="delete.do?id=${ receipt.id }&${ pagination.queryString }" data-confirm-delete>영수증 삭제</a>
</div>

<table class="table table-bordered lbw150">  
  <tr>
    <td class="lb">영수증 번호:</td>
    <td>${ receipt.no }</td>
    <td class="lb">발급일:</td>
    <td>${ receipt.createDate }</td>
  </tr>
  <tr>
    <td class="lb">후원인명:</td>
    <td>${ sponsor.name }</td>
    <td class="lb">주민번호:</td>
    <td>${ sponsor.juminNo }</td>
  </tr>
  <tr>
    <td class="lb">후원인 주소:</td>
    <td colspan="3">${ sponsor.homePostCode } ${ sponsor.homeRoadAddress } ${ sponsor.homeDetailAddress }</td>
  </tr>
  <tr>
    <td class="lb">기관:</td>
    <td>${ corporate.name } ${ corporate.corporateNo }</td>
    <td class="lb">대표:</td>
    <td>${ corporate.representative }</td>
  </tr>  
  <tr>
    <td class="lb">기관 주소:</td>
    <td colspan="3">${ corporate.postCode } ${ corporate.roadAddress } ${ corporate.detailAddress }</td>
  </tr>
</table>

<c:set var="sum" value="0" />
<table class="table table-bordered mt10 w300">
  <thead>
    <tr>
      <th>기부일</th>
      <th class="right">기부금액</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="p" items="${ list }">
      <tr>
        <td>${ p.paymentDate }</td>
        <td class="right"><fmt:formatNumber value="${ p.amount }" /></td>
      </tr>
      <c:set var="sum" value="${ sum + p.amount }" />
    </c:forEach>
    <tr>
      <td>합계:</td>
      <td class="right"><fmt:formatNumber value="${ sum }" /></td>
    </tr>
  </tbody>
</table>
