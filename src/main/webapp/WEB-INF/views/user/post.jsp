<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<script>
function search(){
	alert('start')
	var startDate,endDate;
	
	startDate=$('.startDate').val();
	endDate=$('.endDate').val();
	
	alert(startDate)
	alert(endDate)
	
	
	$.ajax({
		url:"http://localhost:8080/fund_sys/user/postSearch.do",
		type:"GET",
		data:{startDate:startDate,endDate:endDate},
		success : function(data) {
		alert('success')
		}
		
		
	});

	
}


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
				<label> 신청기간 </label> <input type="date" class="startDate"> ~ <input type="date" class="endDate">
				 <button  class="btn btn-primary" onclick="search()">검색</button>
			</div>

			<div id="column-right">
				<a href="#" class="button button-reversed">엑셀 다운</a>
			</div>

			<div class="row">
				<div class="col-lg-12">
				<hr>
					<div class="table-responsive">
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
