<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<input type="hidden" value="${sponsor.id}" />

<h2>${sponsor.name} <small>${sponsor.sponsorNo}</small></h2>
<br />

<ul class="nav nav-tabs">
  <c:if test="${ sponsor.id == 0 }">
    <li class="active"><a href="/fund_sys/sponsor/sponsor.do" data-toggle="tab">회원정보</a></li>
  </c:if>

  <c:if test="${sponsor.id != 0}">
    <li class="active"><a href="/fund_sys/sponsor/detail.do?id=${sponsor.id}" data-toggle="tab">회원정보</a></li>
  </c:if>

  <c:if test="${sponsor.id != 0}">
    <li><a href="/fund_sys/sponsor/commitment.do?id=${sponsor.id}">약정관리</a></li>
    <li><a href="/fund_sys/sponsor/paymentList.do?id=${sponsor.id}">정기납입관리</a></li>
    <li><a href="/fund_sys/sponsor/paymentList2.do?id=${sponsor.id}">비정기납입관리</a></li>
    <li><a href="/fund_sys/sponsor/insertIrrgularPayment.do?id=${sponsor.id}">비정기납입등록</a></li>
  </c:if>
</ul>

<%@ include file="/WEB-INF/views/sponsor/sponsorRegister.jsp"%>
