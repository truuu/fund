<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h1>EB21/EB22 결과</h1>

<form:form class="pull-right mb4" modelAttribute="param">
  <span>기간:</span>
  <form:input path="startDt" class="startDt" /> ~
  <form:input path="endDt" class="endDt" />
  <span>상태:</span>
  <form:select path="state">
    <form:option value="all" label=" 전체" />
    <form:option value="신청" label="신청" />
    <form:option value="에러" label="에러" />
    <form:option value="성공" label="정상" />
  </form:select>
  <button type="submit" class="btn btn-primary">조회</button>
</form:form>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>후원인번호</th>
      <th>후원인</th>
      <th>약정번호</th>
      <th>기부목적</th>
      <th>EB21생성일</th>
      <th>상태</th>
      <th class="w350">에러코드</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="e" items="${ list }">
      <tr class="${ e.state=='에러' ? 'my-error' : '' }">
        <td>${ e.sponsorNo }</td>
        <td>${ e.sponsorName }</td>
        <td>${ e.commitmentNo }</td>
        <td>${ e.corporateName } ${ e.organizationName } ${ e.donationPurposeName }</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${ e.paymentDate }" /></td>
        <td>${ e.state }</td>
        <td>${ e.errorCodeMsg }</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
