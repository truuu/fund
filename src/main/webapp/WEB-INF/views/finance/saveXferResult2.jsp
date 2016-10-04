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
						금융연동 <small>- 자동이체 결과확인</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->
			<form:form method="post" modelAttribute="xferResultList">
				<div class="panel panel-default">
					
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="table_s">
								<thead>
									<tr>
										<th>약정번호</th>
										<th>납입일</th>
										<th>납입금액</th>
									</tr>
								</thead>
								<c:forEach var="paymentList" items="${paymentList}" >
										<tbody>
											<tr>
												<td>${paymentList.commitmentNo }</td>
												<td>${paymentList.paymentDate}</td>
												<td class="money">${paymentList.amount}</td>
						
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
