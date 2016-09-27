<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	function deleteFunction() {

		if (confirm("삭제하시겠습니까?") == true) {
			location.href = "/fund_sys/code/corporateDelete.do?ID=${corporate.ID}";
		} else {
			return;
		}
	}
</script>
<style>
table.table {
	width: 40%;
}

#table_a {
	width: 50%;
}

#btn3 {
	margin-left: 22%;
}
</style>

<h2>기관 수정</h2>
<hr />

<form:form method="post" modelAttribute="corporate">
	<table class="table">
		<tr>
			<td id="table_a">이름</td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td id="table_a">기관번호</td>
			<td><form:input path="corporateNo" /></td>
		</tr>
		<tr>
			<td id="table_a">대표자명</td>
			<td><form:input path="representative" /></td>
		</tr>
		<tr>
			<td id="table_a">주소</td>
			<td><form:input path="address" /></td>
		</tr>
	</table>
	<div>
		<button type="submit" id="btn3" class="btn btn-primary">
			<i class="icon-ok icon-white"></i> 저장
		</button>
		<button type="button" onclick="deleteFunction()"
			class="btn btn-default">
			<i class="icon-ok icon-white"></i> 삭제
		</button>
		<a href="corporateList.do" class="btn btn-default"> <i
			class="icon-ban-circle"></i> 취소
		</a>
	</div>
</form:form>

