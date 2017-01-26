<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="head" />
<style>
    div.alert.autoclose { float: right; min-width: 300px; margin-right: 20px; }
</style>
</head>
<body>

<tiles:insertAttribute name="menu" />

<div class="container">
	<div style="min-height: 700px">
		<tiles:insertAttribute name="content" />
	</div>
	<tiles:insertAttribute name="footer" />
</div>

<c:if test="${ not empty errorMsg }">
  <div class="modal fade myMsg" id="errorModal" role="dialog" tabindex='-1'>
    <div class="modal-dialog modal-sm">
      <div class="modal-content"  style="background-color: #fff0f0">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">작업실패</h4>
        </div>
        <div class="modal-body">
          <p>${ errorMsg }</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>
  <script>$("#errorModal").modal("show");</script>
</c:if>

<c:if test="${ not empty successMsg }">
  <div class="modal fade" id="successModal" role="dialog" tabindex='-1'>
    <div class="modal-dialog modal-sm">
      <div class="modal-content"  style="background-color: #f0fff0">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">작업성공</h4>
        </div>
        <div class="modal-body">
          <p>${ successMsg }</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success" data-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>
  <script>$("#successModal").modal("show");</script>
</c:if>

<script>
  function closeMsgModal() {
  	$("div.modal.myMsg").modal("hide");
  	$(document).off("keydown", closeMsgModal);
  }
  $(document).keydown(closeMsgModal);
</script>    
</body>
</html>
