<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>${ codeGroup.name } ${ code.id > 0 ? "수정" : "등록" }</h1>
<hr />

<form:form method="post" modelAttribute="code">

<div class="pull-right mt4 mb4">
  <button type="submit" class="btn btn-primary" name="cmd" value="save">저장</button>
  <c:if test="${ code.id > 0 }">
    <button type="submit" class="btn btn-danger" name="cmd" value="delete" data-confirm-delete>삭제</button>
  </c:if>    
  <a href="list.do?gid=${codeGroup.id}" class="btn btn-info">목록으로</a>
</div>

<form:hidden path="codeGroupId" />

<table class="table table-bordered lbw150">
  <tr>
    <td class="lb">코드명</td>
    <td><form:input path="CodeName" /></td>
    <td class="lb">${ codeGroup.etc1 }</td>
    <td><form:input path="etc1" /></td>
  </tr>
  <tr>
    <td class="lb">${ codeGroup.etc2 }</td>
    <td><form:input path="etc2" /></td>
    <td class="lb">${ codeGroup.etc3 }</td>
    <td><form:input path="etc3" /></td>
  </tr>
</table>  

</form:form>

