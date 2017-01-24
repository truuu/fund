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
						<form method="POST" action="/fund_sys/home/login_processing.do">
							<div class="row">
								<div class="col-lg-6 col-lg-offset-4">
									<img src="/fund_sys/res/images/skhu_logo.png" style="width: 200px;" />
								</div>
							</div>

							<div class="controls">							
								<input type="text"  name="loginName" placeholder="아이디" /> 
								<input type="password"  name="password" placeholder="비밀번호" />
								<button type="submit" class="btn btn-primary">로그인 </button>
								<c:if test="${user.loginCheck==false}">
								    <p style="color:red;">
								        아이디 또는 비밀번호를 다시 확인하세요.<br>
								        등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.
							        </p>
								</c:if>
							</div>

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


