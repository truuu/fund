<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="R" />

<div id="wrapper">

	<div id="page-wrapper">
		<div class="container-fluid">

			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">LOGIN</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-6 col-lg-offset-1">
					<div class="login">
						<form method="POST" action="home/login_processing.do">
							<div class="row">
								<div class="col-lg-6 col-lg-offset-4">
									<img src="/fund_sys/res/images/skhu_logo.png" style="width: 200px;" />
								</div>
							</div>

							<fieldset>
								<input type="text" class="form-control" name="loginName" placeholder="아이디" /> <input
									type="password" class="form-control" name="password" placeholder="비밀번호" />
								<button type="submit" class="btn btn-primary">로그인</button>
							</fieldset>

						</form>

					</div>
				</div>
			</div>

		</div>
		<!-- /.container-fluid -->
	</div>
</div>

<c:if test="${ param.error != null }">
	<div class="alert alert-error">로그인 실패</div>
</c:if>


