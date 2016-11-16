<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
$(function(){
	$("#datepicker1").datepicker({
		format : 'yyyy-mm-dd'		
	});
	$("#datepicker2").datepicker({
		format : 'yyyy-mm-dd'		
	});
	$("#datepicker3").datepicker({
		format: 'yyyy-mm-dd'
	});
})
</script>
<h1>기부금 영수증 일괄발급</h1>
<hr/>
<form method="post">
	<div>
		<p>기간과 발급일자를 입력해주세요. 입력한 기간에 납입된 내역에 대해 입력한 발급일자로 영수증이 발급됩니다.</p>
		<p>기간 : <input id="datepicker1" name="startDate" >~<input id="datepicker2" name="endDate" >&nbsp; 발급일자 : <input id="datepicker3" name="createDate" >
			<input type="submit" class="btn btn-info"></p>
	</div>
</form>