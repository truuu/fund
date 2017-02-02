<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>급여공제 결과 데이터</h1>
<hr />

<form method="post">
  <div class="pull-right mb4">
    <button type="submit" class="btn btn-primary" name="cmd" value="save">저장</button>
  </div>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th></th>
        <th>약정번호</th>
        <th>이름</th>
        <th>납입일</th>
        <th class="right">납입금액</th>
        <th>적요</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="r" items="${ sal_notSaved }">
        <tr>
          <td></td>
          <td><input type="text" name="commitmentNo" value="${ r.commitmentNo }" /></td>
          <td>${ r.name }</td>
          <td><fmt:formatDate pattern="yyyy-MM-dd" value="${ r.date }" /></td>
          <td class="right"><fmt:formatNumber value="${ r.amount }" /></td>
          <td>${r.etc }</td>
        </tr>
      </c:forEach>    
      <c:forEach var="r" items="${ sal_saved }">
        <tr class="my-success">
          <td>저장됨</td>
          <td>${ r.commitmentNo }</td>
          <td>${ r.name }</td>
          <td><fmt:formatDate pattern="yyyy-MM-dd" value="${ r.date }" /></td>
          <td class="right"><fmt:formatNumber value="${ r.amount }" /></td>
          <td>${r.etc }</td>
        </tr>
      </c:forEach>    
    </tbody>
  </table>
</form>