<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>

	$(function() {
		$(".btn1").hide();
		$("select").attr("readonly", true);  // 적용안됨 질문
		$("input").attr("readonly", true);
		

		$("#editButton").click(function() {
			$(".btn1").show();
			$("select").attr("readonly", false);
			$("input").attr("readonly", false);
			
		});
	});
</script>

<script>
function deleteCommitmentDetail(commitmentDetailID) {
	if (confirm("약정 상세를 삭제하시겠습니까?") == true) {
		commitmentID=$("#cmmID").val();
		location.href="http://localhost:8080/fund_sys/sponsor/commitmentDetailDelete.do?commitmentDetailID="+commitmentDetailID+"&commitmentID="+commitmentID;

	}
};

function deleteCommitment(commitmentID) {
	sponsorID=${commitment.sponsorID};
	if (confirm("약정을 삭제하시겠습니까?") == true) {
		
		location.href="http://localhost:8080/fund_sys/sponsor/commitmentDelete.do?commitmentID="+commitmentID+"&sponsorID="+sponsorID;

	}
};

function end() {
	ID=$("#cid").val();
	sponsorID=${commitment.sponsorID};
	if (confirm("약정을 종료하시겠습니까?") == true) {
		location.href = "/fund_sys/sponsor/commitmentEnd.do?ID="+ID+"&sponsorID="+sponsorID;
	} 
};


</script>

<style>
.table{ margin-bottom:3px; }
button#btn1 { margin-top: 5px; }
table#donationTable {
	width: 100%;
}

#searchResult {
	width: 100%;
}

div#scroll {
	height: 280px;
	overflow-y: scroll;
}

#searchResult tr:hover {
	background-color: #ffe;
	cursor: pointer;
}

#searchResult tr.selected {
	background-color: #fee;
	font-weight: bold;
}

a#list, button#editButton, button.btn, a#btn1, button#btn1 {
	float: right;
}

table tbody tr td {
	vertical-align: middle;
}

div table.table tbody tr td {
	vertical-align: middle;
}

.table tbody tr td {
	vertical-align: middle;
}

button#editButton, button#btn1 {
	background-color: #222;
	color: #FFFFFF;
	border-radius: 5px;
	display: inline-block;
	font-weight: bold;
	padding: 5px 21px;
	font-size: 0.7em;
	letter-spacing: 0.25px;
	text-transform: uppercase;
	margin: 5px 0 5px 0;
	border: solid #222;
	font-weight: bold;
	padding: 5px 21px;
	font-size: 0.7em;
	letter-spacing: 0.25px;
	text-transform: uppercase;
	margin: 5px 0 5px 0;
}
</style>

<div class="panel panel-default">

	<button type="button" style="margin-left: 3px;" id="editButton"
		class="button">수정하기</button>
	<a href="commitment.do?id=${commitment.sponsorID}" id="list" class="button">약정목록</a>
	<h3>약정</h3>
	<form method="post" action="commitmentUpdate.do">

		<input type="hidden" name="ID" id="cid" value="${commitment.ID}" />

		<div class="commitmentTable">
			<table class="table">
				<tbody>
					<tr>
						<td id="table_a">납입방법</td>
						<td>${ commitment.codeName }</td>
						<td id="table_a">기부목적</td>
						<td><div class="form-inline">
							 <input type="text" name="dname" readonly value="${commitment.name}" />
								<a href="#searchDialog" class="btn btn-default" data-toggle="modal">검색</a> 
								<input type="hidden" name="donationPurposeID" id="donationPurposeID" value="${commitment.donationPurposeID }" />
							</div>
						</td>
						<td id="table_a">기부기관</td>
						<td style="vertical-align: middle;"><input type="text"
							name="corporateName2" readonly value="${commitment.corporateName}"/></td>
					</tr>
					
					<tr>
						<td id="table_a">약정일자</td>
						<td><input type="date" name="commitmentDate" value="${commitment.commitmentDate}" /></td>
						<td id="table_a">시작일</td>
						<td><input type="date" name="startDate" value="${commitment.startDate}" /></td>
						<td id="table_a">종료일</td>
						<td><input type="date" name="endDate" value="${commitment.endDate}" /></td>
					</tr>
					<tr>
						<td id="table_a">비고</td>
						<td colspan="3"><input name="etc" size="100" value="${commitment.etc }" /></td>
						<td id="table_a">약정종료</td>
						<td><button type="button" id="endCommitment" onclick="end()">종료하기</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<button type="submit" id="btn1" style="margin-left: 3px;" class="button btn1">저장</button>
		<a id="btn1" class="button btn1" onClick="deleteCommitment(${commitment.ID})">삭제</a>
	</form>
	<br>
	<h3>약정 상세</h3>

<c:forEach var="commitmentDetail" items="${ commitmentDetails }" >
	<form action="commitmentDetailSave.do" method="post">
		<input type="hidden" name="ID" value="${ commitmentDetail.ID }" />
		<input type="hidden" id="cmmID" name="commitmentID" value="${ commitmentDetail.commitmentID }" />
		<table class="table">
			<tbody>
				<tr>
					<td id="table_a">1회납입액</td>
					<td><input type="text" class="money" name="amountPerMonth" value="${ commitmentDetail.amountPerMonth }" /></td>
					<td id="table_a">결제일</td>
					<td><select name="paymentDay"><option value="20" ${commitmentDetail.paymentDay == 20 ? "selected" : "" }>20일</option>
									<option value="25" ${commitmentDetail.paymentDay == 25 ? "selected" : "" }>25일</option></select></td>
					<td id="table_a">은행명</td>
					<td><select name="bankID">
									<c:forEach var="bank" items="${bankList}">
										<option value="${bank.ID}" ${commitmentDetail.bankID==bank.ID ? "selected" : "" }>${bank.codeName}</option>
									</c:forEach>
						</select></td>
					<td id="table_a">계좌번호</td>
					<td><input type="text" name="accountNo" value="${ commitmentDetail.accountNo }" /></td>
				</tr>
				<tr>

					<td id="table_a">예금주</td>
					<td><input type="text" name="accountHolder" value="${ commitmentDetail.accountHolder }" /></td>
					<td id="table_a">약정금액</td>
					<td class="money">${commitment.month*commitmentDetail.amountPerMonth}</td>
					<td id="table_a">시작일</td>
					<td><input type="date" name="startDate" value="${ commitmentDetail.startDate }" ></td>
					<td id="table_a">비고</td>
					<td colspan="3"><input type="text" name="etc" value="${ commitmentDetail.etc }" /></td>
				</tr>

			</tbody>
		</table>
		<button type="submit" id="btn1"  style="margin-left: 3px;" class="button btn1">저장</button>
		<a id="btn1" class="button btn1 cmdBtn" onClick="deleteCommitmentDetail(${commitmentDetail.ID })">삭제</a>
	</form>
</c:forEach>

	<br>
	<h3>신규</h3>
	<form method="post" action="commitmentDetailSave.do">
		<input type="hidden" name="commitmentID" value="${commitmentID}" />
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
					<td id="table_a">시작일</td>
					<td><input type="date" name="startDate"></td>
					<td id="table_a">비고</td>
					<td colspan="3"><input size="50" id="etc" type="text"
						name="etc"></td>
				</tr>
			</tbody>
		</table>
		<button type="submit" id="btn1"  class="button btn1" >신규
			저장</button>
	</form>
</div>

<!-- modal -->
<div id="searchDialog" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3>기부목적 검색</h3>
			</div>
			<div class="modal-body">
				<!-- 
				<form class="form-inline" method="post" action="#">
					<span>이름:</span> <input type="text" name="name" />
					<button type="submit" class="btn btn-default">검색</button>
				</form>
			-->
				<div id="scroll">
					<div id="searchResult">
						<table id="donationTable">
							<thead>
								<tr>
									<th>기관</th>
									<th>기관종류</th>
									<th>기부목적</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="donationPurpose"
									items="${ donationPurposeList }">
									<tr data-id="${ donationPurpose.ID }">
										<td>${ donationPurpose.corporateName }</td>
										<td>${ donationPurpose.codeName }</td>
										<td>${ donationPurpose.name }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<div class="form-inline">
					<button style="margin-bottom: 0px; margin-left:3px;" class="btn" data-dismiss="modal">닫기</button>
					<button style="margin-bottom: 0px" class="btn btn-primary"
						onclick="selectDonationPurpose()" data-dismiss="modal">선택</button>
				</div>
			</div>

		</div>

	</div>
</div>


<script>
	function selectDonationPurpose() {
		var selectedTr = $("#searchResult tr.selected");
		var donationPurposeID = selectedTr.attr("data-id");
		var name = selectedTr.find("td:nth-child(3)").text();
		var corporateName = selectedTr.find("td:nth-child(1)").text();
		$("input[name=dname]").val(name);
		$("input[name=corporateName2]").val(corporateName2);
		$("input#donationPurposeID").val(donationPurposeID);
	}

	$(document).ready(function() {
		$("#searchResult tr").click(function() {
			$("#searchResult tr").removeClass("selected");
			$(this).addClass("selected");
		})
	});
</script>