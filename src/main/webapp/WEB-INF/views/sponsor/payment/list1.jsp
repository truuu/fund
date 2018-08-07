<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="navigation-info">
  &gt; 회원 관리 &gt; <a href="/funds/sponsor/list.do?${ pagination.queryString }">회원 목록</a>  
  &gt; 정기납입 관리 &gt; 정기납입 목록
</div>

<div class="panel panel-default shadow">
  <div class="panel-heading">
    <%@include file="../_title.jsp" %> 
  </div>
  <div class="panel-body">  
    <c:set var="tab3" value="active" />
    <%@include file="../_tab2.jsp" %> 
    
    <div class="mt4">
      <span>약정번호:</span>
      <select name="commitmentId">
        <option value="0">전체</option>
        <c:forEach var="c" items="${ commitments }">
          <option value="${c.id}">${ c.commitmentNo }</option>
        </c:forEach>
      </select> 
      <button type="button" class="btn btn-primary btn-sm" onclick="searchPayment()">조회</button>
      <a id="btnChange" href="#updateDialog" class="btn btn-info btn-sm" data-toggle="modal">기부목적 일괄변경</a>
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
                <td><input type="text" name="startDate" class="startDt" /> ~ <input type="text"  name="endDate" class="endDt" /></td>
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
            <button type="button" class="btn btn-primary btn-sm" onclick="updatePayment()" data-dismiss="modal">변경사항 적용</button>
            <button class="btn btn-default btn-sm" data-dismiss="modal">취소</button>
          </div>
        </div>
      </div>
    </div>

    </div>
</div>    

<%@include file="../_donationPurposeDialog.jsp" %>

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
$("select[name=commitmentId]").change(function() {
    if ($(this).val() == 0) $('#btnChange').hide(); 
    else $('#btnChange').show();
});

$('#btnChange').hide();
</script>
