<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script>

var startDate,endDate,check; 


$(function(){
	
	
	$('#searched').click(function(){
		startDate=$('#startDate').val();
		endDate=$('#endDate').val();
	


		
		location.href="/fund_sys/sponsor/castList.do?startDate="+startDate+"&endDate="+endDate;
	
		
	});
	
	$('#pdf').click(function(){
		startDate=$('#startDate').val();
		endDate=$('#endDate').val();
		
		location.href="/fund_sys/sponsor/castList.do?cmd=pdf&startDate="+startDate+"&endDate="+endDate;
	})
	
	$('#xlsx').click(function(){
		startDate=$('#startDate').val();
		endDate=$('#endDate').val();
		
		location.href="/fund_sys/sponsor/castList.do?cmd=xlsx&startDate="+startDate+"&endDate="+endDate;
	})
});

</script>
<div id="wrapper">

	<div id="page-wrapper">

		<div class="container-fluid">

			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">회원구분별 출연 내역</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-9">
					<label> 기간(시작-끝) </label> <input type="date" name="startDate" id="startDate"
						value="${startDate}"> ~ <input type="date" name="endDate" id="endDate"
						value="${endDate}">
				</div>
				<div class="col-lg-3">
					<div id="column-right">
						<button type="submit" class="btn btn-reversed" id="searched">검색</button>
						<button id="pdf"  class="btn" type="submit" name="cmd" value="pdf">보고서</button>
						<button id="xlsx"  class="btn" type="submit" name="cmd" value="xlsx">엑셀</button>
					</div>

				</div>
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
										<td>${cast.sum}원</td>

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