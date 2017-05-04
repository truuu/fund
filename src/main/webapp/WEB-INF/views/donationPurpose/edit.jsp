<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>기부목적 ${ donationPurpose.id > 0 ? "수정" : "등록" }</h1>
<hr />

<form:form method="post" modelAttribute="donationPurpose">

<div class="pull-right mt4 mb4">
  <button type="submit" class="btn btn-primary" name="cmd" value="save">저장</button>
  <c:if test="${ donationPurpose.id > 0 }">
    <button type="submit" class="btn btn-danger" name="cmd" value="delete" data-confirm-delete>삭제</button>
  </c:if>    
  <a href="list.do" class="btn btn-info">목록으로</a>
</div>

<table class="table table-bordered lbw150">
  <tr>
    <td class="lb">기관</td>
    <td><form:select path="corporateId" itemValue="id" itemLabel="name" items="${corporateList}" /></td>
    <td class="lb">기관종류</td>
    <td><form:select path="organizationId" itemValue="id" itemLabel="codeName" items="${organizationList}" /></td>
  </tr>
  <tr>
    <td class="lb">기부목적</td>
    <td><form:input path="name" class="w400" /></td>
    <td class="lb">구분</td>
    <td><form:input path="gubun" /></td>
  </tr>
  <tr>
    <td class="lb">사용안함</td>
    <td><form:checkbox path="closed"/></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td class="lb">비고</td>
    <td colspan="3"><form:textarea path="etc" style="width:100%; height: 600px" /></td>
  </tr>
</table>  

</form:form>
