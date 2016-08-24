<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4>EB13 금융등록</h4>

		<div class="row">
			<div class="col-lg-9">
				<label> 구분 : 신규 </label> 
			</div>
			<div class="col-lg-3">
				<div id="column-right">
					<a href="#" class="button button-reversed">목록 조회</a>
					<a href="#" class="button button-reversed">EB13 생성</a>
				</div>
			</div>
		</div>
	</div>
	<!-- /.panel-heading -->


	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="table_s">
				<thead>
					<tr>
						<th>후원인번호</th>
						<th>예금주</th>
						<th>생년월일</th>
						<th>은행</th>
						<th>계좌번호</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>20160001</td>
						<td>김하나</td>
						<td>830203</td>
						<td>신한은행</td>
						<td>102-142-33019</td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
</div>

