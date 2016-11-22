<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
jQuery(function($){
	 $.datepicker.regional['ko'] = {
	  closeText: '닫기',
	  prevText: '이전',
	  nextText: '다음',
	  currentText: '오늘',
	  monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	  monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
	  dayNames: ['일','월','화','수','목','금','토'],
	  dayNamesShort: ['일','월','화','수','목','금','토'],
	  dayNamesMin: ['일','월','화','수','목','금','토'],
	  dateFormat: 'yyyy-mm-dd',
	  firstDay: 0,
	  isRTL: false,
	  showMonthAfterYear: true,
	  yearSuffix: ''};
	 $.datepicker.setDefaults($.datepicker.regional['ko']);

	 $('#datepicker1').datepicker(); 

	});

/*
$(function(){
	//$("#datepicker1").datepicker({
	//	format : 'yyyy-mm-dd',
	//	language: 'kr'
	//});
	$("#datepicker2").datepicker({
		format : 'yyyy-mm-dd'		
	});
	$("#datepicker3").datepicker({
		format: 'yyyy-mm-dd'
	});
})*/
</script>
<h1>기부금 영수증 일괄발급</h1>
<hr/>
<form method="post">
	<div>
		<p>기간과 발급일자를 입력해주세요. 입력한 기간에 납입된 내역에 대해 입력한 발급일자로 영수증이 발급됩니다.</p>
		<p>기간 : <input class="commoninput" id="datepicker1" name="startDate" >~<input id="datepicker2" class="commoninput" name="endDate" >&nbsp; 발급일자 : <input class="commoninput" id="datepicker3" name="createDate" >
			<input type="submit" class="btn btn-info"></p>
	</div>
</form>