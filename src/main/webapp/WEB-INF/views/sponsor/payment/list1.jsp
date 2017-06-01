<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="tab3" value="active" />
<%@include file="../_tab.jsp" %>

<script>
function searchPayment() {
    var params = { commitmentId: $("select[name=commitmentId]").val(),
    		       sid: ${ sponsor.id } };
    $("#searchResult").load("list1ajax.do", params);
}
function updatePayment() {
    var params = { id: $("select[name=commitmentId]").val(),
    	           startDate: $("input[name=startDate]").val(),
    	           endDate: $("input[name=endDate]").val(),
    	           donationPurposeId: $("input[name=donationPurposeId]").val(), 
    	           sid: ${ sponsor.id } };
    $("#searchResult").load("list1updateajax.do", params);
}
</script>

<div class="mt4">
  <span>약정번호:</span>
  <select name="commitmentId">
    <option value="0">전체</option>
    <c:forEach var="c" items="${ commitments }">
      <option value="${c.id}">${ c.commitmentNo }</option>
    </c:forEach>
  </select> 
  <button type="button" class="btn btn-primary" onclick="searchPayment()">조회</button>
  <a href="#updateDialog" class="btn btn-info" data-toggle="modal">기부목적 일괄변경</a>
</div>  

<div id="searchResult">
</div>

<div id="updateDialog" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3>기부목적 일괄변경</h3>
      </div>
      <div class="modal-body">
        <table class="table table-bordered lbw150 mb4">
          <tr>
            <td class="lb">기간</td>
            <td><input name="startDate" class="startDt" /> ~ <input name="endDate" class="endDt" /></td>
          </tr>
          <tr>
            <td class="lb">기부목적</td>
            <td><c:set var="paramObj" value="${ commitment }" />
              <%@include file="../_donationPurposeInput.jsp" %>
            </td>
          </tr>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="updatePayment()" data-dismiss="modal">변경사항 적용</button>
        <button class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<%@include file="../_donationPurposeDialog.jsp" %>
