<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="tab2" value="active" /> 
<%@include file="_tab.jsp" %>

<form:form method="post" modelAttribute="commitment">

<div class="pull-right mt4 mb4">
  <button type="submit" class="btn btn-primary" name="cmd" value="save">저장</button>
  <button type="submit" class="btn btn-danger" name="cmd" value="delete" data-confirm-delete>삭제</button>
  <a href="commitmentList.do?id=${ sponsor.id }&${ pagination.queryString }" class="btn btn-info">약정 목록</a>
</div>

<table class="table table-bordered lbw150">
  <tr>
    <td class="lb">약정번호</td>
    <td>${ commitment.commitmentNo }</td>
    <td class="lb">납입방법</td>
    <td><form:select path="paymentMethodId" itemValue="id" itemLabel="codeName" items="${ paymentMethods }" /></td>
  </tr>
  <tr>
    <td class="lb">약정일</td>
    <td><form:input path="commitmentDate" class="date" /></td>
    <td class="lb">월납입액</td>
    <td><form:input path="amountPerMonth" class="money" /></td>
  </tr>
  <tr>
    <td class="lb">시작일</td>
    <td><form:input path="startDate" class="startDt" /></td>
    <td class="lb">은행</td>
    <td>${ commitment.bankName }</td>
  </tr>
  <tr>
    <td class="lb">종료일</td>
    <td><form:input path="endDate" class="endDt" />
        <button type="submit" class="btn btn-sm" name="cmd" value="close" data-confirm="종료하시겠습니까?">종료하기</button>
    </td>
    <td class="lb">계좌번호</td>
    <td>${ commitment.accountNo }</td>
  </tr>
  <tr>
    <td class="lb">월납입일</td>
    <td><form:input path="paymentDay" /></td>
    <td class="lb">예금주</td>
    <td>${ commitment.accountHolder }</td>
  </tr>
  <tr>
    <td class="lb">기부목적</td>
    <td colspan="3">
      <span id="corporateName">${  commitment.corporateName }</span> /
      <span id="organizationName">${  commitment.organizationName }</span> /
      <span id="donationPurposeName">${ commitment.donationPurposeName }</span>
      <input type="hidden" name="donationPurposeId" id="donationPurposeId" value="${ commitment.donationPurposeId }" />
      <a href="#donationPurposeDialog" class="btn btn-sm btn-gray" data-toggle="modal">검색</a>     
    </td>
  </tr>
  <tr>  
    <td class="lb">비고</td>
    <td colspan="3"><form:textarea path="etc" class="w600 h100" /></td>
  </tr>
</table>  

</form:form>

<style>
    div.modal-dialog { width: 700px; }
    div#scroll { height: 600px; width: 100%; overflow-y: scroll; }
    #scroll tbody tr:hover { background-color: #ffe; cursor: pointer; }
</style>

<div id="donationPurposeDialog" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3>기부목적 검색</h3>
      </div>
      <div class="modal-body">
        <div id="scroll">
          <table class="table table-bordered" style="width: 100%;">
            <thead>
              <tr>
                <th>기관</th>
                <th>기관종류</th>
                <th>기부목적</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="donationPurpose" items="${ donationPurposes }">
              <tr data-id="${ donationPurpose.ID }">
                <td>${ donationPurpose.corporateName }</td>
                <td>${ donationPurpose.codeName }</td>
                <td>${ donationPurpose.name }</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<script>
    $(document).ready(function() {
        $("#scroll tr").click(function() {
            var tr = $(this);
            var donationPurposeId = tr.attr("data-id");
            var corporateName = tr.find("td:nth-child(1)").text();
            var organizationName = tr.find("td:nth-child(2)").text();
            var donationPurposeName = tr.find("td:nth-child(3)").text();
            $("span#corporateName").text(corporateName);
            $("span#organizationName").text(organizationName);
            $("span#donationPurposeName").text(donationPurposeName);
            $("input[name=donationPurposeId]").val(donationPurposeId);
            $("#donationPurposeDialog").modal('toggle');
        })
    });
</script>
