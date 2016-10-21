<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<input type="hidden" value="${sponsor.sponsorNo}" />
<input type="hidden" value="${sponsor.id}" />
<div id="wrapper">
	<!-- Nav tabs -->
					<ul class="nav nav-tabs">
						<li class="active"><a href="/fund_sys/sponsor/detail.do?id=${sponsor.id}" data-toggle="tab">회원관리</a></li>
						<li><a href="/fund_sys/sponsor/commitment.do?id=${sponsor.id}">약정관리</a></li>
						<li><a href="/fund_sys/sponsor/paymentList.do?id=${sponsor.id}" >정기납입관리</a></li>
						<li><a href="/fund_sys/sponsor/paymentList2.do?id=${sponsor.id}">비정기납입관리</a></li>
						<li><a href="/fund_sys/sponsor/insertIrrgularPayment.do?id=${sponsor.id}" >비정기납입등록</a></li>
					</ul>
					<p>${sponsor.sponsorNo}&nbsp;  ${sponsor.name}&nbsp;  ${sponsor.sponsorType1}&nbsp;</p>
<div class="panel panel-default">
	
<div class="panel-body">
				

					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade in active" id="user">
							<%@ include file="/WEB-INF/views/sponsor/sponsorRegister.jsp"%>
						</div>
						<div class="tab-pane fade" id="contract">
							<%@ include file="/WEB-INF/views/sponsor/commitment.jsp"%>
						</div>
						<div class="tab-pane fade" id="regular">
						<%@ include file="/WEB-INF/views/sponsor/paymentList.jsp"%>
						</div>
						<div class="tab-pane fade" id="unregular">
							<%@ include file="/WEB-INF/views/sponsor/paymentList2.jsp"%>
						</div>
						<div class="tab-pane fade" id="enroll">
							<%@ include file="/WEB-INF/views/sponsor/insertIrrgularPayment.jsp"%>
						</div>
					</div>
				</div>
				<!-- /.panel-body -->


</div>
</div>
							
										
									