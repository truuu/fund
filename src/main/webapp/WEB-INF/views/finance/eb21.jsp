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
						CMS <small>- EB21</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->
			<form:form method="post" modelAttribute="eb21List">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>EB21 금융등록</h4>
						<div class="row">
							<div class="col-lg-7">
								<label> 구분 : 이체신청</label> 
								&nbsp;&nbsp;&nbsp;&nbsp;
								<label> 출금일 선택 : </label>
								<select id="paymentDay" name="paymentDay">
									<option value="20"> 20일 </option>
									<option value="25"> 25일 </option>
								</select>
								<button type="submit" class="btn btn-default" name="cmd" value="selectEB21List"> 조회 </button>
							</div>
							<div class="col-lg-5">
								<div id="column-right">
									<input type="text" id="paymentDate">
									<button type="submit" class="btn btn-default" name="cmd" value="createEB21file">EB21
										생성</button>
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
								<c:forEach var="eb21List" items="${eb21List}">
									<tbody>
										<tr>
											<td>${eb21List.sponsorNo }</td>
											<td>${eb21List.accountHolder }</td>
											<td>${eb21List.jumin }</td>
											<td>${eb21List.codeName }</td>
											<td>${eb21List.accountNo }</td>
											<td>${eb21List.amountPerMonth }</td>
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

</div>