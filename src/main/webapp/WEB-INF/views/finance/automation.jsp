<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
textarea {
	width: 600px;
	height: 200px;
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
<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						금융연동 <small>- 자동이체</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->
			<form:form method="post" modelAttribute="automationList">
				<div class="panel panel-default">
					<div class="panel-heading">

						<div class="row">

							<div id="column-right">

								<form id="uploadform" method="post"
									enctype="multipart/form-data">
									<input type="file" id="files" name="files" style="width: 90%;" />
									<button class="btn btn-default" type="submit">업로드</button>
								</form>

								<button class="btn btn-default" type="submit" name="cmd"
									value="automationStart">선택 항목 연동</button>
							</div>

						</div>
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="table_s">
								<thead>
									<tr>
										<th>선택</th>
										<th>약정번호</th>
										<th>계좌</th>
										<th>납입일</th>
										<th>납입금액</th>
										<th>후원인명(적요1)</th>
										<th>납부방법</th>
										<th>후원인번호입력</th>
									</tr>
								</thead>
								<c:forEach var="automationList" items="${automationList}">
									<c:if
										test="${automationList.accountNo eq '159-22-01424-5(240-890012-16304)'}">
										<tbody>
											<tr>
												<td>체크</td>
												<td>후원인번호입력</td>
												<td>${automationList.accountNo }</td>
												<td>${automationList.paymentDate}</td>
												<td>${automationList.amount}</td>
												<td>${automationList.sponsorName}</td>
												<td>${automationList.paymentWay}</td>
												<td>
													<div class="form-inline">
														<input type="text" id="sponsorNo" readonly /> <a
															href="#searchCommitment" class="btn btn-default btn-xs"
															data-toggle="modal" style="margin: 0px;">검색</a>

													</div>
												</td>
											</tr>
										</tbody>
									</c:if>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</form:form>
			<div class="modal fade" id="searchCommitment" tabindex="1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3>약정 검색</h3>
				</div>
				<div class="modal-body">
					<form id="searchCommitment" class="form-inline">
						<input type="text" name="sponsorNo" />
						<button type="button" class="btn btn-default btn-xs"
							onclick="searchCommitment()" style="margin: 0px;">검색</button>
					</form>
					<div id="scroll">
						<div id="searchResult"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="searchCommitment()"
						data-dismiss="modal">선택</button>
					<button class="btn" data-dismiss="modal">닫기</button>

				</div>
			</div>
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

<script>
	function searchCommitment() {
		var params = {
			name : $("input[name=sponsorNo]").val()
		};
		$("#searchResult").load("searchCommitment.do", params, function() {
			$("#searchResult tr").click(function() {
				$("#searchResult tr").removeClass("selected");
				$(this).addClass("selected");
			})
		});
	}
	function searchCommitment() {
		var selectedTr = $("#searchResult tr.selected");
		var commitmentId = selectedTr.attr("data-id");
		var commitmentNo = selectedTr.find("td:nth-child(1)").text();
		$("input[name=commitmentTo]").val(commitmentId);
		$("input#commitmentNo").val(commitmentNo);
	}
</script>