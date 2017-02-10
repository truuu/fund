<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>우편 발송</h1>
<hr />

<form:form method="get" modelAttribute="pagination">
  <input type="hidden" name="pg" value="1" />

  <div class="form-inline mb4">
    <span>기간:</span>
    <form:input class="startDt w100" path="sd" />
    ~
    <form:input class="endDt w100" path="ed" />
    <button type="submit" class="btn btn-primary">검색</button>
    <a href="dmx.do?${pagination.queryString}" class="btn btn-info">액셀 다운로드</a>
  </div>

  <table class="table table-bordered" id="table_s">
    <thead>
      <tr>
        <th>회원번호</th>
        <th>이름</th>
        <th>후원인구분2</th>
        <th>소속교회</th>
        <th>우편번호</th>
        <th>주소</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="s" items="${list}">
        <tr>
          <td>${s.sponsorNo}</td>
          <td>${s.name}</td>
          <td>${s.sponsorType2}</td>
          <td>${s.church}</td>
          <td>${s.postCode}</td>
          <td>${s.address}</td>
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

  <ul class="pagination mt0">
    <c:forEach var="page" items="${ pagination.pageList }">
      <li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
    </c:forEach>
  </ul>

</form:form>
