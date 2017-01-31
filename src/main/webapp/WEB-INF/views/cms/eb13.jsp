<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>EB13 생성</h1>

<form class="pull-right mb4" method="post">
  <span>신청일:</span>
  <input name="date" type="text" class="date w100" value="${ today }" />
  <button type="submit" class="btn btn-primary">EB13 파일 생성</button>
</form>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>후원인번호</th>
      <th>후원인</th>
      <th>약정번호</th>
      <th>계좌</th>
      <th>생년월일</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="c" items="${ list }">
      <tr style="background-color: ${ c.valid ? '#fff' : '#fee' }">
        <td>${ c.sponsorNo }</td>
        <td>${ c.name }</td>
        <td>${ c.commitmentNo }</td>
        <td>${ c.bankName } ${ c.accountNo } ${ c.accountHolder }</td>
        <td>${ c.juminNo.length() == 13 ? c.juminNo.substring(0, 6) : c.juminNo }</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
