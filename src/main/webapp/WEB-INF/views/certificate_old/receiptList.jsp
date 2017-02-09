<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
td{ 
	text-align:center; 
}
</style>
<script type="text/javascript">
$(function() {
	$("tbody tr td.view").click(function() {
		location.href=$(this).attr("data-url");
	});
	$("div.pagination a").click(function() {
		$("input[name=pg]").val($(this).attr("data-page"));
		$("form").submit();
	});
	
	$("input[name=st]").hide();
	$("input[id=startDt]").hide();
	$("input[id=endDt]").hide();
	
	if($("#ss").val()==1){
		$("input[name=st]").show();
	}
	if($("#ss").val()==2){
		$("input[id=startDt]").show();
		$("input[id=endDt]").show();	
	}
	
	$("#ss").change(function(){
		if($(this).val()==1){
			$("input[name=st]").show();
			$("input[id=startDt]").hide();
			$("input[id=endDt]").hide();
		}
		else if($(this).val()==2){
			$("input[name=st]").hide();
			$("input[id=startDt]").show();
			$("input[id=endDt]").show();
		}
		else if($(this).val()==0){
			$("input[name=st]").hide();
			$("input[id=startDt]").hide();
			$("input[id=endDt]").hide();
		}
	});
				
	$("button[value=deleteRct]").click(function() {
		alert("선택한 영수증을 삭제하시겠습니까?");
	});
	$(function(){
		$('.money').mask('000,000,000,000,000,000',{reverse:true});
	});
	
});

$(function() {
    $("thead input[type=checkbox]").click(function() {
        $("tbody input[type=checkbox]").trigger("click");
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
        numberOfMonths: 1
    }); 
}); 


</script>

<h1>기부금 영수증 발급대장</h1>
<hr/>

<form:form modelAttribute="pagination">
	<input type="hidden" name="pg" value="1"/>
	<input type="hidden" name="bd" value="2"/>
	<div class="form-inline">
		<form:select  path="ss"  >
			<form:option value="0" label="검색조건"  />
			<form:option value="1" label="후원자명" class="st"/>
			<form:option value="2" label="발급일자 검색 기간" class="dur" />
		</form:select>
		<form:input path="st"  name="st" />
		<form:input  name="dur" id="startDt" path="sd" />
		<form:input  name="dur" id="endDt" path="ed" />
		<button type="submit" class="btn btn-primary">검색</button>
		<c:if test="${ pagination.ss != 0 }">
			<a href="receiptList.do" class="btn btn-default">취소</a>
		</c:if>
	</div>
	
	<div class="pull-right">
		<button type="submit" class="btn btn-danger" name="cmd" value="deleteRct">선택한 영수증 삭제</button>
		<button type="submit" class="btn btn-info" name="cmd" value="rct">선택한 영수증 다운로드</button>
	</div>
	
	<div class="receipt_List">	
		<table class="table table-bordered" >
		    <thead>
		        <tr>
		            <th><input type="checkbox" id="check_all"></th>
		            <th>영수증번호</th>
		            <th>후원인 이름</th>
		            <th>주민번호 / 사업자번호</th>
		            <th>연락처</th>
		            <th>기부금액</th>
		            <th>영수증발급일자</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="receipt" items="${ receiptList }">
		        	<tr>
		            	<td style="vertical-align:middle"><input type="checkbox" name="rid" value="${ receipt.ID  }"></td>
		             	<td style="vertical-align:middle" class="view" data-url="../report/receiptView.do?id=${receipt.ID}">${ receipt.no }</td>
		             	<td style="vertical-align:middle"class="view" data-url="../report/receiptView.do?id=${receipt.ID}">${ receipt.name }</td>
		             	<td style="vertical-align:middle" class="view" data-url="../report/receiptView.do?id=${receipt.ID}">${ receipt.juminNo }</td>
		             	<td style="vertical-align:middle" class="view" data-url="../report/receiptView.do?id=${receipt.ID}">${ receipt.mobilePhone }</td>
		             	<td style="vertical-align:middle" class="view" data-url="../report/receiptView.do?id=${receipt.ID}"><p class="money">${ receipt.amount }</p></td>
		             	<td style="vertical-align:middle" class="view" data-url="../report/receiptView.do?id=${receipt.ID}">${ receipt.createDate }</td>
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
