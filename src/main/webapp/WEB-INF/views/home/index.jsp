<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<h1>후원관리시스템</h1>
<hr />

<h3>일정 관리</h3>

<my:test2>
  <jsp:attribute name="param1">
    <h1>hello</h1>
  </jsp:attribute>
  <jsp:attribute name="param2">
    <h1>world</h1>
  </jsp:attribute>
</my:test2>

<div class="pull-right mb4">
  <button class="btn btn-primary" onclick="showTodoModal()">일정 등록</button>
</div>

<form method="post">
  <input type="hidden" id="id" name="id" value="0" />
  <table class="table table-bordered">
    <thead>
      <tr>
        <th class="w100">작성자</th>
        <th class="w100">일정</th>
        <th>내용</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="m" items="${ list }">
        <tr>
          <td>${ m.userName }</td>
          <td>${ m.dueDate }</td>
          <td>${ m.message }</td>
          <td><button type="submit" class="btn btn-small" name="cmd" value="delete" onclick="setId(${m.id})" data-confirm-delete>삭제</button></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <div class="modal fade" id="totoModal" role="dialog" tabindex='-1'>
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">일정</h4>
        </div>
        <div class="modal-body">          
            <table class="table table-bordered">
              <tr>
                  <td class="lb">일정</td>
                  <td><input name="dueDate" type="text" class="date" /></td>
              </tr>
              <tr>
                  <td class="lb">내용</td>
                  <td><textarea name="message" class="w500 h200"></textarea></td>
              </tr>
            </table>                
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary" name="cmd" value="create">저장</button>
          <button type="button" class="btn" data-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>
</form>
  
<script>
function showTodoModal() {
	$("#totoModal").modal("show");
}
function setId(id) {
    $("input#id").val(id);
}
</script>

