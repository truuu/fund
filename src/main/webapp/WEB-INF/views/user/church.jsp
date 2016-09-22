<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javaScript">

$(function() {
	$('#searchChurch').click(function(){
		
		var startDate=$( "input[name$='startDate']" ).val();
		var endDate=$( "input[name$='endDate']" ).val();
		alert("start "+startDate+" end "+endDate)
		
		location.href="churchSearch.do?startDate="+startDate+"&endDate="+endDate;
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
						소속 교회별 납부 현황
					</h1>
				</div>
			</div>
			<!-- /.row -->

			<div>
				<label> 신청기간 </label> <input type="date" name="startDate"> ~ <input type="date" name="endDate">
				 <button id="searchChurch" class="btn btn-primary">검색</button>
				 <div id="column-right">
				<a href="#" class="button button-reversed">엑셀 다운</a>
			     </div>
			</div>

			

			<div class="row">
				<div class="col-lg-12">
				<hr>
				<center><label> 신청기간 </label> <input type="text"> ~ <input type="text"></center>
				<hr>
					<div class="table-responsive">
						<table class="table table-bordered table-hover" id="table_s">
							<thead>
								<tr>
									
									<th>소속교회</th>
									<th>납입액</th>
									
									
								</tr>
							</thead>
							<tbody>
		    <c:forEach var="sponsor" items="${ list }">
                <tr>
                    <td>${sponsor.church}</td>
                    <td>${sponsor.sum} 원</td>
                   
                </tr>
            </c:forEach>


                <tr>
                 <td> 총합 </td>
                <td>${total} 원</td>
                </tr>
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
