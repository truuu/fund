<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>

$(function() {
    // 기간 설정 타입 1 
    // start Date 설정시 end Date의 min Date 지정
    $( "#startDt" ).datepicker({
        dateFormat: "yy-mm-dd",
        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        onClose: function( selectedDate ) {
            $( "#endDt" ).datepicker( "option", "minDate", selectedDate );
        }
    }); 
     // end Date 설정시 start Date max Date 지정
    $( "#endDt" ).datepicker({
        dateFormat: "yy-mm-dd",
        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        onClose: function( selectedDate ) {
            $( "#startDt" ).datepicker( "option", "maxDate", selectedDate );
        }
    });

    // 기간 설정 타입 2 
    // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
    $("#startDt").datepicker({
        dateFormat: "yy-mm-dd",
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        onClose: function( selectedDate ) {
            if ($( "#endDt" ).val() < selectedDate)
            {
                $( "#endDt" ).val(selectedDate);
            }
        }
    }); 
    // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
    $( "#endDt" ).datepicker({
        dateFormat: "yy-mm-dd",
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        onClose: function( selectedDate ) {
            if ($("#startDt" ).val() > selectedDate)
            {
                $("#startDt" ).val(selectedDate);
            }
        }
    });


    //날짜
    $( "#date" ).datepicker({
        changeMonth: true ,
        changeYear: true ,
        showMonthAfterYear: true ,
        dateFormat: "yy-mm-dd",
        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        defaultDate: "+1w",
        numberOfMonths: 1
    }); 
}); 
</script>
<h1>기부금 영수증 일괄발급</h1>
<hr/>
<form method="post">
	<div>
		<p>기간과 발급일자를 입력해주세요. 입력한 기간에 납입된 내역에 대해 입력한 발급일자로 영수증이 발급됩니다.</p>
		<p>기간 : <input  id="startDt" name="startDate" >~<input id="endDt"  name="endDate" >&nbsp; 발급일자 : <input  id="date" name="createDate" >
			<input type="submit" class="btn btn-info"></p>
	</div>
</form>