<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>기관</h1>

<div class="pull-right mb4">
  <a href="create.do" class="btn btn-primary">새로 등록</a>
</div>

<table class="table table-bordered">
  <thead>
      <tr>
          <th>기관</th>
          <th>기관종류</th>
          <th>기부목적</th>
          <th>구분</th>
          <th>사용안함</th>
      </tr>
  </thead>
   <tbody>
      <c:forEach var="donationPurpose" items="${ list }">
          <tr data-url="edit.do?id=${donationPurpose.id}" >
              <td>${ donationPurpose.corporateName }</td>
              <td>${ donationPurpose.organizationName }</td>
              <td>${ donationPurpose.name }</td>
              <td>${ donationPurpose.gubun }</td>
              <td>${ donationPurpose.closed }</td>
          </tr>
      </c:forEach>
  </tbody>
</table>

