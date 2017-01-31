<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>${ codeGroup.name }</h1>
<hr />

<div class="pull-right mb4">
  <a href="create.do?gid=${ codeGroup.id }" class="btn btn-primary">새로 등록</a>
</div>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>코드명</th>
      <th>${ codeGroup.etc1 }</th>
      <th>${ codeGroup.etc2 }</th>
      <th>${ codeGroup.etc3 }</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="code" items="${list}">
      <tr data-url="edit.do?id=${code.id}&gid=${codeGroup.id}">
        <td>${ code.codeName }</td>
        <td>${ code.etc1 }</td>
        <td>${ code.etc2 }</td>
        <td>${ code.etc3 }</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
