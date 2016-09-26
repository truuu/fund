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
						결과 조회 <small>- EB21 / EB22</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->
			<form:form method="post" modelAttribute="EB21_commitmentDetail">
				<label>EB21 생성일 </label>
				<input type="date" name="startDate"> ~ <input type="date"
					name="endDate">
				&nbsp;&nbsp;&nbsp;
				<button type="submit" class="button">검색</button>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="table_s">
							<thead>
								<tr>
									<th>EB21생성일</th>
									<th>후원인번호</th>
									<th>약정번호</th>
									<th>이름</th>
									<th>기부목적</th>
									<th>상태</th>
								</tr>
							</thead>
							<c:forEach var="eb2122List" items="${eb2122List}">
								<input type="hidden" name="commitmentDetailID"
									value="${eb13List.commitmentDetailID}">
								<tbody>
									<tr>
										<td>${eb2122List.createDate}</td>
										<td>${eb2122List.sponsorNo}</td>
										<td>${eb2122List.commitmentNo}</td>
										<td>${eb2122List.name}</td>
										<td>${eb2122List.donationPurpose}</td>
										<td>${eb2122List.state}</td>
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