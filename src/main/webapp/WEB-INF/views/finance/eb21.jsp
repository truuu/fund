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
						CMS <small>- EB21</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>EB21 금융등록</h4>

					<div class="row">
						<div class="col-lg-9">
							<label> 구분 : 이체신청 </label>
							<label> 출금일 :
								<a class="dropdown-toggle"
										data-toggle="dropdown" href="#" style="color: #333;"> 출금일선
											<span class="caret"></span>
									</a>
										<ul class="dropdown-menu">
											<li><a href="#">20일</a></li>
											<li><a href="#">25일</a></li>
										</ul>
							</label>
							<a href="#" class="button button-reversed">목록 조회</a>
						</div>
						<div class="col-lg-3">
							<div id="column-right">
								<input type="text" id="paymentDate">
								<a href="#" class="button button-reversed">EB21 생성</a>
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