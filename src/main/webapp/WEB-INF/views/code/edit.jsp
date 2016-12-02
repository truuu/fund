<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function deleteFunction() {
    if(confirm("삭제하시겠습니까?")==true){
    	location.href="/fund_sys/code/delete.do?ID=${code.ID}";
    }
    else{
    		return;
    }
}
</script>
<style>
input[name=title] {
	width: 700px;
	border-style: groove;
	margin: 4px;
}
table.table {
	width: 40%;
}
#table_a {
	width: 50%;
	vertical-align: middle;
}
#btn3 {
	margin-left: 22%;
}
</style>

<h2>${name} 수정</h2>
<hr />

<form:form method="post" modelAttribute="code">
	<table class="table">
		<tr>
			<td id="table_a">코드명</td>
			<td><form:input path="codeName" class="commoninput" /><br>${error}
			</td>
		</tr>
		<tr>
			<td id="table_a">기타</td>
			<td><form:input path="etc1" class="commoninput" /></td>
		</tr>
	</table>

	<form:input type="hidden" path="CodeGroupID" />


	<div>
		<button type="submit" id="btn3" class="btn btn-primary">
			<i class="icon-ok icon-white"></i> 저장
		</button>
		<button type="button" onclick="deleteFunction()"
			class="btn btn-default">
			<i class="icon-ok icon-white"></i> 삭제
		</button>
		<a href="codeList.do?CodeGroupID=${ code.codeGroupID }"
			class="btn btn-default"> <i class="icon-ban-circle"></i> 취소
		</a>
	</div>
</form:form>