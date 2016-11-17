<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
$(function() {                          

	$("form").submit(function() {
		
		 var startDate=$('#startDate').val();
		 var endDate=$('#endDate').val();
	    	
		if(startDate==''||endDate==''){
			alert('날짜를 모두 입력해주세요');
			return false;
		}
		else
			return true;
	});
})

</script>
<style>
div.commitmentTable {
	display: none;
}

.size {
	width: 150px;
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


tr#topTable td,tr#topTable th{ text-align:center; }
#outPut1{ margin-left:10%; }
#outPut2{ margin-left:26%; }
#outPut3{ margin-left:28%; }
#time{ float:right; }


</style>
<form method="post">
	<div id="wrapper">

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
						납입관리 <small>- 납입총계조회</small>
					</h1>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
						<div id="column-right">
						<button type="submit" class="btn btn-primary">검색</button>
						<button type="submit" class="btn btn-default" name="cmd" value="report">보고서</button>

					</div>
					<div class="col-lg-12">
				
						<table>
							<tr>
							
								<td><label>정기/비정기</label></td>
								<td><select name="srchType1" class="select_s">
										<option value="0" ${gu1 == 0 ? "selected" : "" }>선택</option>
										<option value="1" ${gu1 == 1 ? "selected" : "" }>정기</option>
										<option value="2" ${gu1 == 2 ? "selected" : "" }>비정기</option>
								</select></td>
								
								


								<td><label>소속교회</label></td>
								<td><select name="srchType3" class="select_s">
										<option value="0" ${ churchID == 0 ? "selected" : "" }>선택</option>
										<c:forEach var="church" items="${churchList}">
											<option value="${church.ID}" ${ churchID == church.ID ? "selected" : "" }>${church.codeName}</option>
										</c:forEach>
								</select></td>
							<tr>
							<td><label>납입일</label></td>
								<td><input type="date" id="startDate" class="commoninput" name="startDate" value="${startDate}" >~<input
									type="date" id="endDate" class="commoninput" name="endDate" value="${endDate}" ></td>
									

								<td><label>납입방법</label></td>
								<td><select name="srchType4" class="select_s">
										<option value="0">선택</option>
										<c:forEach var="paymentMethod" items="${paymentMethodList}">
											<option value="${paymentMethod.ID}" ${ paymentMethodID==paymentMethod.ID ? "selected" : "" }>${paymentMethod.codeName}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
							<td><label>기부목적</label></td>
								<td><form:form method="post">
										<div class="form-inline">
											<input type="text" name="dname" readonly value="${ donationPurpose }" /> <a
												href="#searchDialog" class="btn btn-default"
												data-toggle="modal">검색</a> <input type="hidden"
												name="srchType2" id="donationPurposeID" />
										</div>
									</form:form></td>
								

								<td><label>후원인구분</label></td>
								<td><select name="srchType5" class="select_s">
										<option value="0">선택</option>
										<c:forEach var="sponsorType" items="${sponsorType2List}">
											<option value="${sponsorType.ID}" ${ sponsorTypeID==sponsorType.ID ? "selected" : "" }>${sponsorType.codeName}</option>
										</c:forEach>
								</select></td>

							</tr>
							<tr>
							<td><label>후원인이름</label></td>
								<td colspan="4"><input type="text" name="sponsorName" value="${sponsorName}" /></td>
							</tr>


						</table>

					</div>
				</div>


				<div class="row">
					<div class="col-lg-12">
						<hr>
						<div id="column-right">
							<a href="#" class="btn btn-default">출력</a> <a href="#" class="btn btn-default">엑셀다운</a>
						</div>
						

						<div class="reporting">


							<div class="row">
								<div class="col-lg-12">
									<table class="report_table">
										<tr>
											<td id="table_a"><label>정기/비정기</label></td>
											<td id="table_b">${gubun}</td>
											<td id="table_a"><label>납입일</label></td>
											<td id="table_b">${startDate}~${endDate}</td>
										</tr>
										<tr>
											<td id="table_a"><label>기부목적</label></td>
											<td id="table_b">${donationPurpose}</td>

											<td id="table_a"><label>소속교회</label></td>
											<td id="table_b">${church}</td>

										</tr>
										<tr>
											<td id="table_a"><label>기부기관</label></td>
											<td id="table_b">${corporateName}</td>
											<td id="table_a"><label>후원인구분</label></td>
											<td id="table_b">${sponsorType}</td>

										</tr>
										<tr>
											<td id="table_a"><label>납입방법</label></td>
											<td id="table_b">${paymentMethod}</td>
											<td id="table_a"><label>후원인이름</label></td>
											<td id="table_b">${sponsorName}</td>
										</tr>

									</table>
								</div>
							</div>
						<div id="time">${time}</div>
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>후원인번호</th>
											<th>이름</th>
											<th>후원인구분</th>
											<th>소속교회</th>
											<th>총납입건수</th>
											<th>총납입액</th>
											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="payment" items="${ list }">
											<tr id="topTable">
												<td>${ payment.sponsorNo }</td>
												<td>${ payment.name }</td>
												<td>${ payment.sponsorType2 }</td>
												<td>${ payment.church }</td>
												<td>${ payment.totalCount }</td>
												<td class="money">${ payment.totalSum }</td>
												
											</tr>
										</c:forEach>


									</tbody>
								</table>

							</div>
							<span id="outPut1" style="font-weight: bold">계:${count}</span>
							<span id="outPut2" style="font-weight: bold">총 납입건수:${total2}</span>
							<span id="outPut3" style="font-weight: bold">총 납입액:</span>
							<span  class="money" style="font-weight: bold">${total}</span>





						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->

	</div>
</form>

<!-- modal -->
<div id="searchDialog" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3>기부목적 선택</h3>
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
