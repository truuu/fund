<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<style>
#table_a {
	vertical-align: middle;
}
textarea{
	width:90%;
	height:100px;
}
</style>


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
			<button type="submit" style="margin-bottom: 5px" class="btn btn-default">미리보기</button> 
			<a style="margin-bottom: 5px" href="#"
					class="btn btn-primary">인쇄</a>
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
						<td id="table_b"><input type="text" class="money" name="amount"></td>
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

