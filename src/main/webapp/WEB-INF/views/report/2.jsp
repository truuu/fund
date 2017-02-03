<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h1>${ title }별 납입 합계</h1>
<hr />

<form:form modelAttribute="param">
  <div class="pull-right mb4">
    <button type="submit" class="btn btn-primary" name="cmd" value="search">검색</button>
    <button type="button" class="btn btn-default" onclick="cancelSearch()">검색취소</button>    
    <button type="submit" class="btn btn-gray" name="cmd" value="excel">엑셀</button>
  </div>

  <div>
    <span>기간:</span>
    <form:input path="map[startDate]" class="startDt" /> ~ 
    <form:input path="map[endDate]" class="endDt" />
  </div>  
</form:form>

<table class="table table-bordered mt4">
  <thead>
    <tr>
      <th>${ title }</th>
      <th class="right">후원인수</th>
      <th class="right">납입수</th>
      <th class="right">금액</th>
      <th class="right">비율</th>
      
    </tr>
  </thead>
  <tbody>
    <c:forEach var="p" items="${list}">
      <tr>
        <td>${p.corporateName} ${p.organizationName} ${p.donationPurposeName}</td>
        <td class="right"><fmt:formatNumber value="${p.sponsorCount}" /></td>
        <td class="right"><fmt:formatNumber value="${p.paymentCount}" /></td>
        <td class="right"><fmt:formatNumber value="${p.amount}" /></td>
        <td class="right"><fmt:formatNumber value="${p.ratio }" pattern="##0.00"/></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
