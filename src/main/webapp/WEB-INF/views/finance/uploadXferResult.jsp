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
			<div align="center">
				<form id="uploadform" method="post" enctype="multipart/form-data">
					<input type="file" id="file" name="file"/>
					<button class="btn btn-default" type="submit">업로드</button>
				</form>

			</div>
		</div>
	</div>
</div>