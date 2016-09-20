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
						CMS <small>- EB22</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>EB22 금융등록</h4>

					<div class="row">
						<div class="col-lg-9">
							<label> 구분 : 에러 </label>
						</div>
						<div class="col-lg-3">
							<div id="column-right">
								<a href="#" class="btn btn-default">EB22 조회</a>
								<a href="#" class="btn btn-default">EB22 적용</a>
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
									<th>납입금액</th>
									<th>납입일</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>20160001</td>
									<td>김하나</td>
									<td>830203</td>
									<td>신한은행</td>
									<td>102-142-33019</td>
									<td>50,000</td>
									<td>2016-09-29</td>
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