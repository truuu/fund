<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>기부증서</h1>
<hr />

<form:form method="get" modelAttribute="pagination">
  <input type="hidden" name="pg" value="1" />

  <div class="pull-right mb4">
    <a href="create.do?type=${ type }" class="btn btn-primary">새로 등록</a>
  </div>

  <div class="form-inline">    
    <form:select path="ss">
      <form:option value="0" label="조회조건" />
      <form:option value="1" label="이름" />
      <form:option value="2" label="회원번호" />
    </form:select>
    <form:input path="st" />

    <button type="submit" class="btn btn-info">조회</button>
    <c:if test="${ pagination.ss != 0 }">
      <a href="list.do" class="btn btn-default">조회조건 취소</a>
    </c:if>
  </div>

  <table class="table table-bordered">
    <thead>
      <tr>
        <th>번호</th>
        <th>날짜</th>
        <th>회원번호</th>
        <th>이름</th>
        <th>금액</th>
        <th>발행인</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="c" items="${list}">
        <tr data-url="detail.do?id=${c.id}&${pagination.queryString}">
          <td>${ c.certificateNo }</td>
          <td>${ c.createDate }</td>
          <td>${ c.personNo }</td>
          <td>${ c.personName }</td>
          <td>${ c.amount }</td>
          <td>${ c.userName }</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <ul class="pagination mt0">
    <c:forEach var="page" items="${ pagination.pageList }">
      <li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
    </c:forEach>
  </ul>

  <form:select path="sz" data-auto-submit="true">
    <form:option value="10" />
    <form:option value="15" />
    <form:option value="30" />
    <form:option value="100" />
  </form:select>

</form:form>