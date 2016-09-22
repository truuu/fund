<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:useBean id="toDay" class="java.util.Date" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="/fund_sys/res/js/jquery.js" type="text/javascript"></script>
<script src="/fund_sys/res/js/jquery.mask.min.js" type="text/javascript"></script>
<script>
	function chkfrm1() {
		document.getElementById("detail").style.display = "inline-block";
		document.getElementById("detail2").style.display = "inline-block";
	}
	function chkfrm2() {
		document.getElementById("detail2").style.display = "inline-block";
	}

	
	function save() {

		if (confirm("저장하시겠습니까?") == true) {
			location.href = "/fund_sys/sponsor/create.do";
		} else {
			return;
		}
	}
</script>

<style>
#new, #detail2 {
	float: right;
}

table.tb1 {
	display: none;
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
</style>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3>약정 내역</h3>
		<div class="input-group1">

			<button type="button" id="new" class="btn btn-small"
				onclick="chkfrm1()">새로 등록</button>
		</div>

		<div class="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>약정번호</th>
						<th>기부목적</th>
						<th>납입방법</th>
						<th>약정일</th>
						<th>기간</th>
						<th>약정상태</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="commitment" items="${ list }">
						<tr>
							<td>${ commitment.commitmentNo }</td>
							<td>${ commitment.name }</td>
							<td>${ commitment.codeName }</td>
							<td>${ commitment.commitmentDate }</td>
							<td>
									${ commitment.startDate } ~ ${ commitment.endDate }</td>
							<td>${ commitment.state }</td>
							<td>${ commitment.etc }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<form method="post">
			<input type="hidden" name="sponsorID" value="${sponsorID}" />
			<table class="tb1" id="detail">
				<tbody>

					<tr>
						<td id="table_a">납입방법</td>
						<td id="td2"><select name="paymentMethodID">
								<c:forEach var="paymentMethod" items="${paymentMethodList}">
									<option value="${paymentMethod.ID}">${paymentMethod.codeName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td id="table_a">기부목적</td>
						<td id="td2"><form:form method="post">
								<div class="form-inline">
									<input type="text" name="dname" readonly /> <a
										href="#searchDialog" class="btn btn-default"
										data-toggle="modal">검색</a> <input type="hidden"
										name="donationPurposeID" id="donationPurposeID" />
								</div>
							</form:form></td>
					</tr>
					<tr>
						<td id="table_a">기부기관</td>
						<td id="td2"><input type="text" name="corporateName" readonly />
						</td>
					</tr>
					<tr>
						<td id="table_a">약정일자</td>
						<td id="td2">
							  <input type="date" name="commitmentDate">
						</td>
					</tr>
				</tbody>
			</table>

			<table class="tb1" id="detail2">
			<tbody>
				<tr>
					
					<td id="table_a">1회납입액</td>
					<td><input type="text" class="money" name="amountPerMonth" /></td>
					<td id="table_a">약정금액</td>
					<td></td>
				
				</tr>
				<tr>
					<td id="table_a">시작일</td>
					<td> <input type="date" name="commitmentStartDate"></td>
					<td id="table_a">종료일</td>
					<td>  <input type="date" name="endDate" ></td>
				</tr>
				<tr>
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
					<td id="table_a">결제일</td>
					<td><select name="paymentDay"><option value="20">20일</option>
							<option value="25">25일</option></select></td>
				</tr>
				<tr>
					<td id="table_a">약정상태</td>
					<td><button>종료하기</button></td>
					<td id="table_a">비고</td>
					<td><input name="commitmentEtc" type="text" /></td>
				</tr>
			</tbody>
		</table>

			<hr>
			<center><!-- div 가운데로 하는 css서식 페이지네이션뷰에서 찾아서 수정 -->
			<button type="submit" class="btn" name="cmd" value="create">저장</button>
			</center>
		</form>
	</div>
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
					<button style="margin-bottom: 0px" class="btn" data-dismiss="modal">닫기</button>
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