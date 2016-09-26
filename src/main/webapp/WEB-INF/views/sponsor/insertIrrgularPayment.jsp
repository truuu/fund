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
					<h1 class="page-header">비정기 납입등록</h1>
				</div>
			</div>
			<!-- /.row -->
			<form method="post" class="form-horizontal">
				<input type="hidden" name="sponsorID" value="109" />
				<div class="form-group">
					<label for="amount" class="col-lg-2 control-label">납입금액</label>
					<div class="col-lg-10">
						<input type="text" class="money" id="amount" name="amount" placeholder="금액" />
					</div>
				</div>
				<div class="form-group">

					<label for="paymentDateString" class="col-lg-2 control-label">납입일</label>
					<div class="col-lg-10">
						<input type="date" class="form-control" id="paymentDateString"
							name="paymentDateString" placeholder="날짜" />
					</div>
				</div>
				<div class="form-group">
					<label for="donationPurposeID" class="col-lg-2 control-label">기부목적</label>
					<div class="col-lg-10">
						<input type="hidden" name="donationPurposeID" value="90" />
					</div>
				</div>
				<div class="form-group">
					<label for="select" class="col-lg-2 control-label">납입 방법</label>
					<div class="col-lg-10">
						<select class="form-control" name="paymentMethodID">
							<option value="13">직접입금</option>
							<option value="14">현물</option>
							<option value="15">부동산</option>
							<option value="16">신용카드</option>
						</select>
						
					</div>
				</div>
				<div class="form-group">
					<label for="etc" class="col-lg-2 control-label">비고</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" id="etc" name="etc"
							placeholder="비고" />
					</div>
				</div>
				<hr>
				<div align="center">
					<input type="submit" class="btn btn-primary"
						style="background-color: #bd5151; border: 0px; color: white;"
						value="저장" />
				</div>
			</form>
			
		</div>

	</div>
	<!-- /.panel -->
</div>
<!-- /.container-fluid -->

<!-- /#page-wrapper -->
