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
						금융연동 <small>- 자동이체</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->
			<form:form method="post" modelAttribute="xferResultList">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div id="column-right">
								<button class="btn btn-default" type="submit" name="cmd"
									value="saveCommitmentNo">선택 항목 연동</button>
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
										<th>약정번호</th>
										<th>계좌</th>
										<th>납입일</th>
										<th>납입금액</th>
										<th>후원인명(적요1)</th>
										<th>납부방법</th>
									</tr>
								</thead>
								<c:forEach var="xferResultList" items="${xferResultList}" varStatus="status">
										<tbody>
											<tr>
												<td><input type="checkbox" name="index" value="${status.index }"/></td>
												<td><input type="text" name="commitmentNo"/></td>
												<td>${xferResultList.accountNo }</td>
												<td>${xferResultList.paymentDate}</td>
												<td class="money">${xferResultList.amount}</td>
												<td>${xferResultList.sponsorName}</td>
												<td>${xferResultList.paymentWay}</td>
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
