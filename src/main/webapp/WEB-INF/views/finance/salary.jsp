<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						금융연동 <small>- 급여공제</small>
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
										<th>후원인번호</th>
										<th>후원인명</th>
										<th>납입금액</th>
										<th>납입일</th>
								
									</tr>
								</thead>
								<c:forEach var="salaryList" items="${salaryList}">
									<c:if test="${salaryList.sponsorNo ne null}">
										<tbody>
											<tr>
												<td>체크</td>
												<td>${salaryList.sponsorNo }</td>
												<td>${salaryList.sponsorName}</td>
												<td>${salaryList.amount}</td>
												<td>${salaryList.paymentDate}</td>
											</tr>
										</tbody>
									</c:if>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

