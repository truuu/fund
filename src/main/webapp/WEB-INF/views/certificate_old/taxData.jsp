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
 
}); 

</script>
<style>
td{ text-align:center; }
</style>
<h1>국세청 보고자료</h1>
<hr/>
<form:form modelAttribute="pagination">
	<input type="hidden" name="pg" value="1"/>
	<input type="hidden" name="bd" value="1"/>
	<div class="form-inline">
	 <!--  기간과 기관 함께 검색  -->
		기간:
		<form:input  id="startDt" path="sd"/>~
		<form:input  id="endDt" path="ed" />
		기관종류: 
		<form:select path="cp"  name="corporateId">
			<form:options itemValue="id" itemLabel="name" items="${ corporates }"/>
		</form:select>

		<button type="submit" class="btn btn-primary">검색</button>
		<c:if test="${ pagination.ss != 0 }">
			<a href="taxData.do" class="btn btn-default">취소</a>
		</c:if>
	</div>
	
	<div class="pull-right">
		<button class="btn btn-info" type="submit" name="cmd" value="xlsx">엑셀파일</button>
	</div>
	
	<div class="taxData_List">	
		<table class="table table-bordered">
		    <thead>
		        <tr>
		            <th>주민번호</th>
		            <th>이름</th>
		            <th>기부내용구분</th>
		            <th>기부금단체코드</th>
		            <th>기부일자</th>
		            <th>기부금액</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="data" items="${ taxDataList }">
		        	<tr>
		            	<td>${ data.juminNo }</td>
		             	<td>${ data.name }</td>
		             	<td>1: 금전기부 고정</td>
		             	<td>BBA 고정</td>
		             	<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${ data.paymentDate }" /></td>
		             	<td>${ data.amount }</td>
		           </tr>
		        </c:forEach>
		    </tbody>
		</table>
	</div>
<div align="center">
	<div class="pagination">
		<ul class="pagination pagination-sm">
			<c:forEach var="page" items="${pagination.pageList }">
				<li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
</form:form>
