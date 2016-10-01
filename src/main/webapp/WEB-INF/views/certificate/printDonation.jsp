<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<head>
<script>
function print(index) {
	$("[class=money]").unmask();
	amount = $('#amount').val();
	sponsorName = $('#sponsorName').val();
	serialNo = $('#serialNo').val();
	content = $('#content').val();

	if (index == 2) {
		
		alert(amount);
		location.href = "../certificate/donationIssue.do?amount="+ amount + "&sponsorName="+ sponsorName
			+ "&serialNo="+ serialNo;
		

	}
	if (index == 1) {
		alert("미리보기2");
		location.href = "../certificate/dpreview.do?amount="
				+ amount
				+ "&sponsorName="
				+ sponsorName
				+ "&serialNo="
				+ serialNo
				+ "&content="
				+ content;
				

	}

}
</script>
<style>
#table_a {
	vertical-align: middle;
}

textarea {
	width: 90%;
	height: 100px;
}
</style>

</head>
<body>
<form method="post">
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="container-fluid">
				<h1 class="page-header">기부증서</h1>
				<div id="column-right">
					<button type="button" style="margin-bottom: 5px"
						class="btn btn-default" onclick="print(1)">영수증발급</button>
					<button type="button" style="margin-bottom: 5px"
						class="btn btn-primary" onclick="print(2)">인쇄</button>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td id="table_a">일련번호</td>
							<td id="table_b"><input type="text" id="serialNo" name="serialNo"
								value="${serialNo}"></td>
						</tr>
						<tr>
							<td id="table_a">후원자명</td>
							<td id="table_b"><input type="text" id="sponsorName" name="sponsorName"></td>
						</tr>
						<tr>
							<td id="table_a">약정액</td>
							<td id="table_b"><input type="text" id="amount" class="money"
								name="amount"></td>
						</tr>
						<tr>
							<td id="table_a">내용</td>
							<td id="table_b"><textarea id="content" name="content"></textarea></td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</form>
</body>
