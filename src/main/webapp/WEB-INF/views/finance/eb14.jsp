<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						CMS <small>- EB14</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>EB14 금융등록</h4>

					<div class="row">
						<div class="col-lg-9">
							<label> 구분 : 에러 </label>
							<label> 파일명 : 연 파일명 출력해주기 </label>
						</div>
						<div class="col-lg-3">
							<div id="column-right">
								<a href="#" class="button button-reversed">EB14 조회</a> <a href="#"
									class="button button-reversed">EB14 적용</a>
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
									<th>생년월일</th>
									<th>은행</th>
									<th>계좌번호</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>20160001</td>
									<td>830203</td>
									<td>신한은행</td>
									<td>102-142-33019</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

</div>