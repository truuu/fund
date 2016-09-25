<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
$(function() {
	$("").hide();
	$("input").attr("readonly", true);
	$("select").attr("readonly", true);
});


$("#editButton").click(function() {
	$("#btn1").show();   
	$("input").attr("readonly", false);
	$("select").attr("readonly", false);
});
</script>
<style>
a#list, button#editButton, button.btn, a#deleteBtn {
	float: right;
}
table tbody tr td{ vertical-align: middle; padding:0px;}
</style>

<button type="button" id="editButton" class="btn btn-small">
	수정하기</button>
<a href="commitment.do" id="list" class="btn btn-default"> 약정목록 </a>
<h3>약정</h3>
<form:form method="post" modelAttribute="commitment">
	<input type="hidden" name="sponsorID" value="${sponsorID}" />
	<div class="commitmentTable">
		<table class="table">
			<tbody>
				<tr>
					<td id="table_a">납입방법</td>
					<td>${ commitment.paymentMethodID }</td>
					<td id="table_a">기부목적</td>
					<td><form:form method="post">
							<div class="form-inline">
								<input type="text" name="dname" readonly /> <a
									href="#searchDialog" class="btn btn-default"
									data-toggle="modal">검색</a> <input type="hidden"
									name="donationPurposeID" id="donationPurposeID" />
							</div>
						</form:form></td>
					<td id="table_a">기부기관</td>
					<td style="vertical-align: middle;"><input type="text"
						name="corporateName" readonly /></td>
				</tr>
				<tr>
					<td id="table_a">약정일자</td>
					<td><form:input path="commitmentDate" /></td>
					<td id="table_a">시작일</td>
					<td><form:input path="startDate" /></td>
					<td id="table_a">종료일</td>
					<td><form:input path="endDate" /></td>
				</tr>
				<tr>
					<td id="table_a">비고</td>
					<td colspan="3"><form:input path="endDate" size="100" /></td>
					<td id="table_a">약정종료</td>
					<td><button>종료하기</button></td>
				</tr>
			</tbody>
		</table>
	</div>
	<button type="submit" id="btn1" class="btn" name="cmd" value="edit">저장</button>
</form:form>
<br>
<h3>약정 상세#1</h3>

<form:form method="post" modelAttribute="commitmentDetail">
	<table class="table">
		<tbody>
			<tr>
				<td id="table_a">1회납입액</td>
				<td><form:input path="amountPerMonth" /></td>
				<td id="table_a">결제일</td>
				<td><form:select path="paymentDay">
						<form:option value="20" label="20일" />
						<form:option value="25" label="25일" />
					</form:select></td>
				<td id="table_a">은행명</td>
				<td><form:input path="bankID" /></td>
				<td id="table_a">계좌번호</td>
				<td><form:input path="accountNo" /></td>
			</tr>
			<tr>

				<td id="table_a">예금주</td>
				<td><form:input path="accountHolder" /></td>
				<td id="table_a">약정금액</td>
				<td></td>
				<td id="table_a">비고</td>
				<td colspan="3"><form:input path="etc" size="50" /></td>
			</tr>

		</tbody>
	</table>
	<button type="submit" id="btn1" class="btn" name="cmd" value="edit">저장</button>
	<a id="deleteBtn"  class="btn btn-default" href="#">삭제</a>
</form:form>
<br>
<h3>신규</h3>
<form method="post">
	<table class="table">
		<tbody>
			<tr>
				<td id="table_a">1회납입액</td>
				<td><input type="text" class="money" name="amountPerMonth" />
				</td>
				<td id="table_a">결제일</td>
				<td><select name="paymentDay"><option value="20">20일</option>
						<option value="25">25일</option></select></td>
				<td id="table_a">은행명</td>
				<td><select name="bankID">
						<c:forEach var="bank" items="${bankList}">
							<option value="${bank.ID}">${bank.codeName}</option>
						</c:forEach>
				</select></td>
				<td id="table_a">계좌번호</td>
				<td><input name="accountNo" type="text" /></td>
			</tr>
			<tr>

				<td id="table_a">예금주</td>
				<td><input name="accountHolder" type="text" /></td>
				<td id="table_a">약정금액</td>
				<td></td>
				<td id="table_a">비고</td>
				<td colspan="3"><input size="50" id="etc" type="text"
					name="commitmentDetailEtc"></td>
			</tr>

		</tbody>
	</table>
	<button type="submit" id="btn1" class="btn" name="cmd" value="create">신규 저장</button>
</form>
