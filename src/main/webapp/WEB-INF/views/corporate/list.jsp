<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>기관</h1>

<div class="pull-right mb4">
  <a href="create.do" class="btn btn-primary">새로 등록</a>
</div>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>기관명</th>
      <th>기관번호</th>
      <th>대표자명</th>
      <th>주소</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="corporate" items="${ list }">
      <tr data-url="edit.do?id=${corporate.id}">
        <td>${ corporate.name }</td>
        <td>${ corporate.corporateNo }</td>
        <td>${ corporate.representative }</td>
        <td>${ corporate.roadAddress } ${ corporate.detailAddress } ${ corporate.postCode }</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
