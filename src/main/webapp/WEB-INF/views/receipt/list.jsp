<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>기부금 영수증 목록</h1>
<hr />

<form:form modelAttribute="pagination">

<input type="hidden" name="pg" value="1" />

<span>후원인명:</span>
<form:input path="nm" />

<span class="block ml20">발급기간:</span>
<form:input path="sd" class="startDt" /> ~
<form:input path="ed" class="endDt" />

<button type="submit" class="btn btn-primary ml20">검색</button>
<c:if test="${ not empty pagination.nm || not empty pagination.sd }">
  <a href="list.do" class="btn btn-default">취소</a>
</c:if>

<div class="pull-right">
  <button type="submit" class="btn btn-info" name="cmd" value="download">선택한 영수증 다운로드</button>
</div>

<table class="table table-bordered">
  <thead>
    <tr>
      <th><input type="checkbox" id="check_all"></th>
      <th>영수증번호</th>
      <th>후원인 이름</th>
      <th>주민번호 / 사업자번호</th>
      <th>연락처</th>
      <th>기부금액</th>
      <th>영수증발급일자</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="receipt" items="${ receiptList }">
      <tr data-url="detail.do?id=${receipt.id}">
        <td><input type="checkbox" name="rid" value="${ receipt.id  }"></td>
        <td>${ receipt.no }</td>
        <td>${ receipt.name }</td>
        <td>${ receipt.juminNo }</td>
        <td>${ receipt.mobilePhone }</td>
        <td><fmt:formatNumber value="${ receipt.amount }" /></td>
        <td>${ receipt.createDate }</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<ul class="pagination">
  <c:forEach var="page" items="${pagination.pageList }">
    <li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
  </c:forEach>
</ul>

</form:form>
