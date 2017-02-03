<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="tab3" value="active" />
<%@include file="_tab.jsp" %>

<script>
function searchPayment() {
    var params = { sid:0, commitmentId: $("select[name=commitmentId]").val() };
    $("#searchResult").load("paymentList1ajax.do", params);
}
</script>

<div class="mt4">
  <span>약정번호:</span>
  <select name="commitmentId">
    <c:forEach var="c" items="${ commitments }">
      <option value="${c.id}">${ c.commitmentNo }</option>
    </c:forEach>
  </select> 
  <button type="button" class="btn btn-primary" onclick="searchPayment()">조회</button>
</div>  

<div id="searchResult">
</div>
