<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<script src="/fund/res/js/jquery.js" type="text/javascript"></script>
<script src="/fund/res/js/jquery.mask.min.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('.money').mask('000,000,000,000,000,000,000', {
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

<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<h1 class="page-header">기부 증서</h1>
			<div id="column-right">
			<a href="#" class="button">미리보기</a> <a href="#"
					class="button button-reversed">발급</a>
			</div>
			<table class="table">
				<tbody>
					
					<tr>
						<td id="table_a">후원자명</td>
						<td id="table_b"><input type="text"></td>
					</tr>
					<tr>
						<td id="table_a">약정액</td>
						<td id="table_b"><input type="text" class="money"></td>
					</tr>
					<tr>
						<td id="table_a">내용</td>
						<td id="table_b"><textarea></textarea></td>
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


