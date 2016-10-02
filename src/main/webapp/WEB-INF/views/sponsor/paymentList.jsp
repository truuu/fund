<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						납입 목록 <small>- 정기</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->
			<form:form method="post" modelAttribute="paymentList">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="table_s">
								<thead>
									<tr>
										<th>약정번호</th>
										<th>납입방법</th>
										<th>납입금액</th>
										<th>납입일</th>
										<th>계좌번호</th>
										<th>은행</th>
										<th>예금주</th>
										<th>기부기관</th>
										<th>기부목적</th>
										<th>비고</th>
									</tr>
								</thead>
								<c:forEach var="paymentList" items="${paymentList}">
									<tbody>
										<tr>
											<td>${paymentList.commitmentNo}</td>
											<td>${paymentList.paymentMethod}</td>
											<td>${paymentList.amount}</td>
											<td><fmt:formatDate value="${paymentList.paymentDate}" pattern="yyyy-MM-dd"/></td>
											<td>${paymentList.accountNo}</td>
											<td>${paymentList.bankName}</td>
											<td>${paymentList.accountHolder}</td>
											<td>${paymentList.corporate}</td>
											<td>${paymentList.donationPurpose}</td>
											<td>${paymentList.etc}</td>
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