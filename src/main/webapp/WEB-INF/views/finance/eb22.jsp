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
						CMS <small>- EB22</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->
			<form:form method="post" modelAttribute="EB22">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>EB22 금융등록</h4>

						<div class="row">
							<div class="col-lg-9">
								<label> 구분 : 에러 </label>
							</div>
							<div class="col-lg-3">
								<div id="column-right">
									<button class="btn btn-default" type="submit" name="cmd"
										value="updateEB22">EB22 적용</button>
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
										<th>불능코드</th>
									</tr>
								</thead>
								<c:forEach var="eb22List" items="${eb22List}">
									<tbody>
										<tr>
											<td>${eb22List.sponsorNo }</td>
											<td>${eb22List.name }</td>
											<td>${eb22List.jumin }</td>
											<td>${eb22List.bankCode }</td>
											<td>${eb22List.accountNo }</td>
											<td class="money">${eb22List.amountPerMonth }</td>
											<td>${eb22List.errorCode }</td>
										</tr>
									</tbody>
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

