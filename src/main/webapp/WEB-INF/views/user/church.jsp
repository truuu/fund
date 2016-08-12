<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

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
				<label> 신청기간 </label> <input type="date"> ~ <input type="date">
				 <button  class="btn btn-primary">검색</button>
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
