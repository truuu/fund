<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="pull-right">
  <a href="list.do?${ pagination.queryString }" class="btn btn-info">후원인 목록</a>
</div>

<h2>${sponsor.name} <small>${sponsor.sponsorNo}</small></h2>
<br />

<c:set var="pg" value="${pagination.queryString}" />
<c:set var="sid" value="${sponsor.id}" />

<ul class="nav nav-tabs">
  <li class="${ tab1 }"><a href="/fund_sys/sponsor/basicInfo.do?id=${sid}&${pg}">후원인정보</a></li>
  <c:if test="${ sid != 0 }">
    <li class="${ tab2 }"><a href="/fund_sys/sponsor/commitmentList.do?sid=${sid}&${pg}">약정관리</a></li>
    <li class="${ tab3 }"><a href="/fund_sys/sponsor/paymentList1.do?sid=${sid}&${pg}">정기납입관리</a></li>
    <li class="${ tab4 }"><a href="/fund_sys/sponsor/paymentList2.do?sid=${sid}&${pg}">비정기납입관리</a></li>
  </c:if>
</ul>
