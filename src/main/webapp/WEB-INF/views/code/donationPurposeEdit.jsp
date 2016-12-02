<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
function deleteFunction() {
    if(confirm("삭제하시겠습니까?")==true){
    	location.href="/fund_sys/code/donationPurposeDelete.do?ID=${donationPurpose.ID}";
    }
    else{
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
	vertical-align: middle;
}
#btn3 {
	margin-left: 22%;
}
</style>

<h2>기부목적 수정</h2>
<hr />

<form:form method="post" modelAttribute="donationPurpose">
	<table class="table">
		<tr>
			<td id="table_a">기관</td>
			<td><form:select path="corporateID" class="commoninput">
					<c:forEach var="corporate" items="${corporateList}">
						<form:option value="${corporate.ID}" label="${corporate.name}" />
					</c:forEach>
				</form:select></td>

		</tr>
		<tr>
			<td id="table_a">기관종류</td>
			<td><form:select path="organizationID" class="commoninput">
					<c:forEach var="organization" items="${organizationList}">
						<form:option value="${organization.ID}"
							label="${organization.codeName}" />
					</c:forEach>
				</form:select></td>
		</tr>
		<tr>
			<td id="table_a">기부목적</td>
			<td><form:input path="name" class="commoninput" /><br>
			<form:errors path="name" /></td>
		</tr>
		<tr>
			<td id="table_a">구분</td>
			<td><form:input path="gubun" class="commoninput" /><br>
			<form:errors path="gubun" /></td>
		</tr>
		
		</table>
		<div>
			<button type="submit" id="btn3" class="btn btn-primary">
				<i class="icon-ok icon-white"></i> 저장
			</button>
			<button type="button" onclick="deleteFunction()"
				class="btn btn-danger">
				<i class="icon-ok icon-white"></i> 삭제
			</button>
			<a href="donationPurposeList.do" class="btn btn-default"> <i
				class="icon-ban-circle"></i> 취소
			</a>
		</div>
		</form:form>