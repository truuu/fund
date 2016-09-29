<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="wrapper">

<div class="panel panel-default">
				<div class="panel-body">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs">
						<li class="active"><a href="#user" data-toggle="tab">회원관리</a></li>
						<li><a href="#contract" data-toggle="tab">약정관리</a></li>
						<li><a href="#payment" data-toggle="tab">납입관리</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade in active" id="user">
							<%@ include file="/WEB-INF/views/sponsor/sponsorRegister.jsp"%>
						</div>
						<div class="tab-pane fade" id="contract">
							
						</div>
						<div class="tab-pane fade" id="payment">
							
						</div>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->



</div>