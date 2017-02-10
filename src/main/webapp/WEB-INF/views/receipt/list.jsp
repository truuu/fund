<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function report() {
  location.href = "report.do?" + $("form#receipts").serialize(); 
}
</script>
<h1>영수증 목록</h1>
<hr />

<form:form id="receipts" modelAttribute="pagination" method="get">

<input type="hidden" name="pg" value="1" />

<span>후원인명:</span>
<form:input path="st" />

<span class="block ml20">발급기간:</span>
<form:input path="sd" class="startDt" /> ~
<form:input path="ed" class="endDt" />

<button type="submit" class="btn btn-primary ml20">검색</button>
<c:if test="${ not empty pagination.st || not empty pagination.sd }">
  <a href="list.do" class="btn btn-default">취소</a>
</c:if>

<div class="pull-right">
  <button class="btn btn-info" type="button" onclick="report()">선택한 영수증 다운로드</button>
</div>

<table class="table table-bordered mt4">
  <thead>
    <tr>
      <th><input type="checkbox"></th>
      <th>영수증번호</th>
      <th>후원인 이름</th>
      <th>후원인번호</th>
      <th>연락처</th>
      <th class="right">기부금액</th>
      <th>영수증발급일자</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="receipt" items="${ list }">
      <tr data-url="detail.do?id=${receipt.id}&${pagination.queryString}">
        <td><input type="checkbox" name="rid" value="${ receipt.id  }"></td>
        <td>${ receipt.no }</td>
        <td>${ receipt.name }</td>
        <td>${ receipt.sponsorNo }</td>
        <td>${ receipt.mobilePhone }</td>
        <td class="right"><fmt:formatNumber value="${ receipt.amount }" /></td>
        <td>${ receipt.createDate }</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<form:select path="sz" data-auto-submit="true">
  <form:option value="10" />
  <form:option value="15" />
  <form:option value="30" />
  <form:option value="100" />
</form:select>

<ul class="pagination">
  <c:forEach var="page" items="${pagination.pageList }">
    <li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
  </c:forEach>
</ul>

</form:form>
