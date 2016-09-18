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
						CMS <small>- EB14</small>
					</h1>
				</div>
			</div>
			<!-- /.row -->
			<form:form method="post" modelAttribute="eb13List">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>EB14 금융등록</h4>

					<div class="row">
						<div class="col-lg-9">
							<label> 구분 : 에러 </label> <label> 파일명 : 연 파일명 출력해주기 </label>
						</div>
						<div class="col-lg-3">
							<div id="column-right">
								<button class="right" type="submit" name="cmd" value="selectEB14"> EB14 조회 </button>
								<button class="right" type="submit" name="cmd" value="updateEB14"> EB14 적용 </button>
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
							<c:forEach var="eb14List" items="${eb14List}">
							<tbody>
								<tr>
									<td>${eb14List.sponsorNo }</td>
									<td>${eb14List.jumin }</td>
									<td>${eb14List.codeName }</td>
									<td>${eb14List.accountNo }</td>
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