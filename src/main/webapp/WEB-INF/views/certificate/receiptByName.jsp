<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
$(function(){
		$("button[value=deleteRct]").click(function() {
		alert("선택한 영수증을 삭제하시겠습니까?");
	});
});


$(function() {
    // 기간 설정 타입 1 
    // start Date 설정시 end Date의 min Date 지정
    $( "#startDt" ).datepicker({
        dateFormat: "yy-mm-dd",
        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
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
        numberOfMonths: 1
    }); 
}); 
</script>
<style>
td{
	text-align:center;
	vertical-align:middle;
 }
</style>
<h1>기부금 영수증 개별발급</h1>
<hr/>
<form:form method="post" modelAttribute="pagination" action="receiptByName.do">
	<input type="hidden" name="bd" value="3" />
	<div class="condition">
		 	<p>기간과 이름은 필수검색 조건입니다.</p>
			<p>
				기간 : <form:input  id="startDt" path="sd" name="startDate"/>~<form:input  id="endDt" path="ed" />
				&nbsp;&nbsp; 이름 : <form:input  type="text" path="st"/>
				&nbsp;&nbsp; 기관 : 
				<form:select  path="cp" name="corporateID">
					<form:options itemValue="ID" itemLabel="name" items="${ corporates }"/>
				</form:select>
				&nbsp;&nbsp;<button type="submit" class="btn btn-primary" name="cmd" value="search">검색</button>
			</p>
	</div>
	<div class="select_List">
			<table class="table table-bordered">
			    <thead>
			        <tr>
			            <th>체크박스/영수증번호</th>
			            <th>후원인번호</th>
			            <th>후원인명</th>
			            <th>정기/비정기</th>
			            <th>납입일</th>
			            <th>금액</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach var="payment" items="${ paymentList }">
			        	<tr>
			            	<td><c:if test="${ payment.receiptID != 0 }">
			            			<button type="submit" class="btn btn-danger" name="cmd" value="deleteRct">
			            			<input type="hidden" name="delid" value="${ payment.receiptID }"/> 삭제</button>&nbsp;&nbsp;${ payment.rctNo }</c:if>
			            		   <c:if test="${ payment.receiptID == 0 }"><input type="checkbox" name="pid" value="${ payment.id }"></c:if></td>
			             	<td style="vertical-align:middle">${ payment.sponsorNo }</td>
			             	<td style="vertical-align:middle">${ payment.name }</td>
			             	<td style="vertical-align:middle"><c:if test="${ payment.commitmentID != 0}">정기 </c:if>
			             	 	   <c:if test="${ payment.commitmentID == 0}">비정기</c:if></td>
			             	<td style="vertical-align:middle"><fmt:formatDate pattern="yyyy-MM-dd"
									value="${ payment.paymentDate }" /></td>
			             	<td style="vertical-align:middle" class="money">${ payment.amount }</td>
			           </tr>
			        </c:forEach>
			    </tbody>
			</table>		
	
	</div>

<div class="Issue">
	<p>발급일자 : <input  id="date" name="createDate">&nbsp;&nbsp;<button type="submit" class="btn btn-info" name="cmd" value="issueRct">기부금 영수증 발급</button></p>
	<!--<c:if test="${ not empty error }"><div class="alert alert-error">${error}</div></c:if>-->
</div>
</form:form>