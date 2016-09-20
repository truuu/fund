<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<script src="/fund/res/js/jquery.js" type="text/javascript"></script>
<script src="/fund/res/js/jquery.mask.min.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('.money').mask('000,000,000,000,000,000', {
			reverse : true
		});
	});
</script>
<style>
#table_a {
	vertical-align: middle;
}
textarea{
	width:90%;
	height:100px;
}
</style>
<form method="post">
<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<h1 class="page-header">기부증서</h1>
			<div id="column-right">
			<button type="submit" class="button">미리보기</button> <a href="#"
					class="button button-reversed">인쇄</a>
			</div>
			<table class="table">
				<tbody>
					<tr>
						<td id="table_a">일련번호</td>
						<td id="table_b"><input type="text" name="serialNo" value="${serialNo}"></td>
					</tr>
					<tr>
						<td id="table_a">후원자명</td>
						<td id="table_b"><input type="text" name="sponsorName"></td>
					</tr>
					<tr>
						<td id="table_a">약정액</td>
						<td id="table_b"><input type="text"  name="amount"></td>
					</tr>
					<tr>
						<td id="table_a">내용</td>
						<td id="table_b"><textarea name="content"></textarea></td>
					</tr>

				</tbody>
			</table>
			<div class="panel show">
				<div class="panel-heading">
					<h4>미리 보기</h4>
				</div>
				<div class="panel-body">
					<div class="show-body"></div>


				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->

		</div>
	</div>
</div>
</form>

