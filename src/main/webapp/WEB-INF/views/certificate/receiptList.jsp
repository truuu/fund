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
	
	$("input.st").hide();
	$("input.dur").hide();

	$("#ss").change(function(){
		if($(this).val()==1){
			$("input.st").show();
			$("input.dur").hide();
		}
		else if($(this).val()==2){
			$("input.st").hide();
			$("input.dur").show();
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
$(function(){
	$("#datepicker1").datepicker({
		format : 'yyyy-mm-dd'		
	});
	$("#datepicker2").datepicker({
		format : 'yyyy-mm-dd'		
	});
})


</script>

<h1>기부금 영수증 발급대장</h1>
<hr/>

<form:form modelAttribute="pagination">
	<input type="hidden" name="pg" value="1"/>
	<input type="hidden" name="bd" value="2"/>
	<div class="form-inline">
		<form:select path="ss"  >
			<form:option value="0" label="검색조건" id="whdms" />
			<form:option value="1" label="후원자명" class="st"/>
			<form:option value="2" label="발급일자 검색 기간" class="dur" />
		</form:select>
		<form:input path="st" class="st" />
		<form:input  class="dur" id="datepicker1" path="sd" />
		<form:input  class="dur" id="datepicker2" path="ed" />
		<button type="submit" class="btn btn-primary">검색</button>
		<c:if test="${ pagination.ss != 0 }">
			<a href="receiptList.do" class="btn btn-default">취소</a>
		</c:if>
	</div>
	
	<div class="pull-right">
		<button type="submit" class="btn btn-danger" name="cmd" value="deleteRct">선택삭제</button>
		<button type="submit" class="btn btn-info" name="cmd" value="rct">선택영수증</button>
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
