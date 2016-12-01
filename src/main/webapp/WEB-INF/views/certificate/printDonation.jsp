<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<script>
function print(index) {
	$("[class=money]").unmask();
	amount = $('#amount').val();
	sponsorName = $('#sponsorName').val();
	serialNo = $('#serialNo').val();
	content = $('#content').val();

	if (index == 2) {
		alert("기부증서를 발급하시겠습니까?");
		location.href = "../certificate/donationIssue.do?amount="+ amount + "&sponsorName="+ sponsorName
			+ "&serialNo="+ serialNo;
	}
	if (index == 1) {
		location.href = "../report/printDonation.do?type=pdf&amount="+amount+"&sponsorName="+sponsorName+"&serialNo="+serialNo+"&content="+content;
	}

}
function htmlReport() {
	amount = $('#amount').val();
	sponsorName = $('#sponsorName').val();
	serialNo = $('#serialNo').val();
	content = $('#content').val();
	var url = "/fund_sys/report/printDonation.do?type=html&amount="+amount+"&sponsorName="+sponsorName+"&serialNo="+serialNo+"&content="+content;
	$("iframe").attr("src", url);
}
</script>
<style>
#table_a {
	vertical-align: middle;
}
textarea{
	width:85%;
	height:100px;
	border: 1px solid #ccc;
    border-radius: 4px;
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
}
iframe { width:800px; border: 1px solid #ddd; height:900px; }
.btn{
	margin-bottom:5px;
}
</style>

<body>
<form method="post" action="/fund_sys/report/printDonation.do">
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="container-fluid">
				<h1 class="page-header">기부증서</h1>
				<div id="column-right">
					<button class="btn btn-info" type="button" onclick="htmlReport()">조회</button>
					<button type="button" style="margin-bottom: 5px"
						class="btn btn-default" onclick="print(1)">파일생성</button>
					<button type="button" style="margin-bottom: 5px"
						class="btn btn-primary" onclick="print(2)">기부증서발급</button>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td id="table_a">일련번호</td>
							<td id="table_b"><input type="text" id="serialNo" class="commoninput" name="serialNo"
								value="${serialNo}" readonly /></td>
						</tr>
						<tr>
							<td id="table_a">후원자명</td>
							<td id="table_b"><input type="text" id="sponsorName" class="commoninput" name="sponsorName"/></td>
						</tr>
						<tr>
							<td id="table_a">약정액</td>
							<td id="table_b"><input type="text" id="amount" class="money" 
								name="amount"/></td>
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
<div align="center">
	<iframe></iframe>
</div>
</body>
