<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script>

var startDate,endDate,check; 

function search(){
	alert('asd '+pageNumber)
	
	startDate=$('.startDate').val();
	endDate=$('.endDate').val();

	
	//location.href="http://localhost:8080/fund_sys/sponsor/postSearch.do?startDate="+startDate+"&endDate="+endDate;
	

	
}
$(function(){
	$('#postExcel').click(function(){
		alert('click')
		alert(startDate)
		alert(endDate)
		
		startDate=$('#startDate').val();
		endDate=$('#endDate').val();
		check='t';
		alert(startDate)
		alert(endDate)
		


		
		location.href="../sponsor/postSearch.do?startDate="+startDate+"&endDate="+endDate+"&check="+check;
		
		
	});
	
	$('#search').click(function(){
		startDate=$('#startDate').val();
		endDate=$('#endDate').val();
		check='f';
		alert(startDate)
		alert(endDate)
		


		
		location.href="../sponsor/postSearch.do?startDate="+startDate+"&endDate="+endDate+"&check="+check;
	
		
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
						DM 발송용 주소록
					</h1>
				</div>
			</div>
			<!-- /.row -->
                   
			<div>
				<label> 신청기간 </label> <input type="date" id="startDate" value="${ pagination.startDate}"> ~ <input type="date" id="endDate" value="${ pagination.endDate}">
				 <button  class="btn btn-primary" id="search">검색</button>
			</div>

			<div id="column-right">
				<button class="btn" type="submit" name="cmd" value="xlsx">엑셀파일</button>
			</div>

			<div class="row">
				<div class="col-lg-12">
				<hr>
					<div class="table-responsive">
					 <form method="get">
                      <input type="hidden" name="pg" value="1" />
                       <input type="hidden" name="startDate" value="${ pagination.startDate}" />
                        <input type="hidden" name="endDate" value="${ pagination.endDate}" />
                        <input type="hidden" name="check" value="f" />
                      
						<table class="table table-bordered table-hover" id="table_s">
							<thead>
								<tr>
									<th>회원번호</th>
									<th>이름</th>
									<th>후원인구분2</th>
									<th>소속교회</th>
									<th>우편번호</th>
									<th>주소</th>
									
								</tr>
							</thead>
							<tbody>
							
							
			<c:forEach var="post" items="${postList}">
			<tr>
			<td>${post.sponsorNo}</td>
			<td>${post.name}</td>
			<td>${post.sponsorType2}</td>
			<td>${post.church}</td>
			<td>${post.postCode}</td>
			<td>${post.address}</td>
			</tr>
			</c:forEach>



							</tbody>
						</table>
						<center>
						 <div class="pagination pagination-small pagination-centered">
				        	<ul>
				            	<c:forEach var="page" items="${ pagination.pageList }">
				                	<li class='${ page.cssClass }'><a data-page="${ page.number }" >${ page.label }</a></li>
				            	</c:forEach>
				        	</ul>
                        </div>
                        </center>
                         </form>

					</div>

				</div>

			</div>




		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /#page-wrapper -->

</div>
