<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>${sponsor.name} <small>${sponsor.sponsorNo}</small></h2>
<br />

<c:set var="q" value="id=${sponsor.id}&${pagination.queryString}" />

<ul class="nav nav-tabs">
  <li class="${ tab1 }"><a href="/fund_sys/sponsor/basicInfo.do?${q}">후원인정보</a></li>

  <c:if test="${ sponsor.id != 0 }">
    <li class="${ tab2 }"><a href="/fund_sys/sponsor/commitment.do?${q}">약정관리</a></li>
    <li class="${ tab3 }"><a href="/fund_sys/sponsor/paymentList.do?${q}">정기납입관리</a></li>
    <li class="${ tab4 }"><a href="/fund_sys/sponsor/paymentList2.do?${q}">비정기납입관리</a></li>
    <li class="${ tab5 }"><a href="/fund_sys/sponsor/insertIrrgularPayment.do?${q}">비정기납입등록</a></li>
  </c:if>
</ul>
