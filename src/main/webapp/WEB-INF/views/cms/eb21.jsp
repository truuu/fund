<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>EB21 생성</h1>

<form:form class="mb4" modelAttribute="wrapper">
  <span>출금일:</span>
  <form:input path="map[paymentDay]" class="w50" />
  <button type="submit" class="btn btn-info" name="cmd" value="search">조회</button>
  
  <span class="block ml100">출금일:</span>
  <form:input path="map[paymentDate]" class="date" />
  <button type="submit" class="btn btn-primary" name="cmd" value="create">EB21 파일 생성</button>
</form:form>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>후원인번호</th>
      <th>후원인</th>
      <th>약정번호</th>
      <th>약정일</th>
      <th>계좌</th>
      <th>주민등록번호</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="c" items="${ list }">
      <tr class="${ c.valid ? '' : 'my-error' }">
        <td>${ c.sponsorNo }</td>
        <td>${ c.name }</td>
        <td>${ c.commitmentNo }</td>
        <td>${ c.commitmentDate }</td>
        <td>${ c.bankName } ${ c.accountNo } ${ c.accountHolder }</td>
        <td>${ c.juminNo }</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
