<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>

<script>

var startDate,endDate,check; 


$(function(){
	
	
	$('#search').click(function(){
		startDate=$('#startDate').val();
		endDate=$('#endDate').val();
		
		alert(startDate)
		alert(endDate)
		


		
		location.href="http://localhost:8080/fund_sys/sponsor/castList.do?startDate="+startDate+"&endDate="+endDate;
	
		
	});
	
});

</script>

<div id="wrapper">

	<div id="page-wrapper">

		<div class="container-fluid">

			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
					회원구분별 출연 내역	
					</h1>
				</div>
			</div>
			<!-- /.row -->
                   
			<div>
				<label> 신청기간 </label> <input type="date" id="startDate" value="${ pagination.startDate}"> ~ <input type="date" id="endDate" value="${ pagination.endDate}">
				 <button  class="btn btn-primary" id="search">검색</button>
			</div>

		

			<div class="row">
				<div class="col-lg-12">
				<hr>
					<div class="table-responsive">
                      
						<table class="table table-bordered table-hover" id="table_s">
							<thead>
								<tr>
									<th>회원구분</th>
									<th>회원수</th>
									<th>출연수</th>
									<th>금액</th>
									
									
								</tr>
							</thead>
							<tbody>
							
							
		<c:forEach var="cast" items="${list}">
			<tr>
			<td>${cast.sponsorType2}</td>
			<td>${cast.sponsorCount}</td>
			<td>${cast.castCount}</td>
			<td>${cast.sum} 원</td>
			
			</tr>
	</c:forEach>
	<c:if test="${sum!=0}">
            <tr>
			<td><b>소계</b></td>
			<td><b>${sponsorCount}</b></td>
			<td><b>${castCount}</b></td>
			<td><b>${sum} 원</b></td>
			</tr>
	 </c:if>


							</tbody>
						</table>
						
			
                       

					</div>

				</div>

			</div>




		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /#page-wrapper -->

</div>