<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h1>후원인 목록</h1>
<hr />

<form:form method="get" modelAttribute="pagination">
    <input type="hidden" name="pg" value="1" />

    <div class="form-inline">
        <span>정렬:</span>
        <form:select path="od" data-auto-submit="true">
            <form:option value="0" label="등록순서" />
            <form:option value="1" label="후원인번호 순서" />
        </form:select>
        <form:select path="ss">
            <form:option value="0" label="검색조건" />
            <form:option value="1" label="이름" />
            <form:option value="2" label="후원인번호" />
        </form:select>
        <form:input path="st" />
        
        <form:select path="st1" class="ml10">
            <form:option value="0" label="후원인구분1 " />
            <form:options itemValue="id" itemLabel="codeName" items="${ sponsorType1Codes }" />         
        </form:select>

        <form:select path="st2" class="ml10 mr10">
            <form:option value="0" label="후원인구분2 " />
            <form:options itemValue="id" itemLabel="codeName" items="${ sponsorType2Codes }" />         
        </form:select>
        
        <button type="submit" class="btn btn-small btn-info">검색</button>
        <c:if test="${ pagination.ss != 0 }">
            <a href="list.do" class="btn btn-small btn-default">취소</a>
        </c:if>
    </div>

    <table class="table table-bordered">
      <thead>
        <tr>
          <th>후원인번호</th>
          <th>이름</th>
          <th>후원인구분1</th>
          <th>후원인구분2</th>
          <th>소속교회</th>
          <th>가입일</th>
          <th>연락처</th>
          <th>이메일</th>

        </tr>
      </thead>
      <tbody>
        <c:forEach var="sponsor" items="${list}">
          <tr data-url="sponsorEdit.do?id=${sponsor.id}&${pagination.queryString}">
            <td>${sponsor.sponsorNo}</td>
            <td>${sponsor.name}</td>
            <td>${sponsor.sponsorType1}</td>
            <td>${sponsor.sponsorType2}</td>
            <td>${sponsor.church}</td>
            <td>${sponsor.signUpDate}</td>
            <td>${sponsor.mobilePhone}</td>
            <td>${sponsor.email}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <div class="pagination">
        <ul class="pagination">
            <c:forEach var="page" items="${ pagination.pageList }">
                <li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
            </c:forEach>
        </ul>
    </div>

    <form:select path="sz" data-auto-submit="true">
        <form:option value="10" />
        <form:option value="15" />
        <form:option value="30" />
        <form:option value="100" />
    </form:select>

</form:form>

