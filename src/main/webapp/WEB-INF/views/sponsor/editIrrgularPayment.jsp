<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
	function deleteFunction() {

		if (confirm("삭제하시겠습니까?") == true) {
			location.href = "/fund_sys/sponsor/deleteIrrgularPayment.do?id=${payment.id}&&sponsorID=${payment.sponsorID}";
		} else {
			return;
		}
	}
</script>
<style>
div.commitmentTable {
	display: none;
}

#new, #detail2 {
	float: right;
}

.size {
	width: 150px;
}

table#detail {
	width: 35%;
}

table#detail2 {
	width: 60%;
}

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

button#detail {
	margin: 0 auto;
}

div#table_a, div#td2 {
	display: inline-block;
}

div.table-responsive {
	border: 1px;
}

div.table {
	width: 100%;
}

div table.table tbody tr td {
	vertical-align: middle;
}

.table tbody tr td {
	vertical-align: middle;
}

label {
	text-align: right;
}

tr#topTable td, tr#topTable th {
	text-align: center;
}
</style>
<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">비정기 납입 수정</h1>
				</div>
			</div>
			<input type="hidden" value="${sponsor.sponsorNo}" /> <input
				type="hidden" name="sponsorID" value="${sponsorID}" />
			<!-- /.row -->
			<form:form method="post" class="form-horizontal"
				modelAttribute="payment">
				<input type="hidden" name="sponsorID" value="${sponsorID}" />
				<div class="form-group">
					<label for="amount" class="col-lg-2 control-label">납입금액</label>
					<div class="col-lg-10">
						<div class="form-control">
							<input type="text" value="${payment.amount }" class="money"
								id="amount" name="amount" placeholder="금액"
								style="border: 0px; width: 800px;" />
						</div>
					</div>
				</div>
				<div class="form-group">

					<label for="paymentDateString" class="col-lg-2 control-label">납입일</label>
					<div class="col-lg-10">

						<input type="date" value="${payment.paymentDateString}"
							 name="paymentDateString" />
					</div>
				</div>
				<div class="form-group">
					<label for="donationPurposeID" class="col-lg-2 control-label">기부목적</label>
					<div class="col-lg-10">
						<input type="text" name="dname" readonly
							value="${payment.donationPurpose }" /> <a href="#searchDialog"
							class="btn btn-primary" data-toggle="modal">검색</a> <input
							type="hidden" value="${payment.donationPurposeID }"
							name="donationPurposeID" id="donationPurposeID" />

					</div>
				</div>
				<div class="form-group">
					<label for="select" class="col-lg-2 control-label">납입 방법</label>
					<div class="col-lg-10">
						<select class="form-control" name="paymentMethodID">
							<option value="13"
								${payment.paymentMethodID == 13 ? "selected" : "" }>직접입금</option>
							<option value="14"
								${payment.paymentMethodID == 14 ? "selected" : "" }>현물</option>
							<option value="15"
								${payment.paymentMethodID == 15 ? "selected" : "" }>부동산</option>
							<option value="16"
								${payment.paymentMethodID == 16 ? "selected" : "" }>신용카드</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="etc" class="col-lg-2 control-label">비고</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" value="${payment.etc }"
							id="etc" name="etc" placeholder="비고" />
					</div>
				</div>
				<hr>
				<div align="center">
					<button type="submit" id="btn3" class="btn btn-primary">
						<i class="icon-ok icon-white"></i> 저장
					</button>
					<button type="button" onclick="deleteFunction()"
						class="btn btn-default">
						<i class="icon-ok icon-white"></i> 삭제
					</button>
					<a href="paymentList2.do?id=${ payment.sponsorID }"
						class="btn btn-default"> <i class="icon-ban-circle"></i> 취소
					</a>
				</div>
			</form:form>

		</div>

	</div>
	<!-- /.panel -->
</div>

<!-- /.container-fluid -->

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
				<div id="scroll">
					<div id="searchResult">
						<table id="donationTable">
							<thead>
								<tr id="topTable">
									<th>기관</th>
									<th>기관종류</th>
									<th>기부목적</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="donationPurpose"
									items="${ donationPurposeList }">
									<tr data-id="${ donationPurpose.ID }" id="topTable">
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
					<button style="margin-bottom: 0px" class="btn btn-default" data-dismiss="modal">닫기</button>
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
		$("input[name=corporateName]").val(corporateName);
		$("input#donationPurposeID").val(donationPurposeID);
	}

	$(document).ready(function() {
		$("#searchResult tr").click(function() {
			$("#searchResult tr").removeClass("selected");
			$(this).addClass("selected");
		})
	});
</script>