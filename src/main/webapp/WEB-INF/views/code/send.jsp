<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
table#donationTable{
	width: 100%;
}
#searchResult { width: 100%; }
div#scroll { height: 280px; overflow-y: scroll; }
#searchResult tr:hover { background-color: #ffe; cursor: pointer; }
#searchResult tr.selected { background-color: #fee; font-weight: bold; }
</style>


<h2>기부목적 선택 test</h2>
<hr />
<form:form method="post">

	<label>기부목적</label>
	<div class="form-inline">
		<input type="text" name="dname" readonly /> <a href="#searchDialog"
			class="btn btn-default" data-toggle="modal">검색</a>
			<input type="hidden" name="donationPurposeID" id="donationPurposeID"/>

	</div>

	<div>
		<button type="submit" class="btn btn-primary">
			<i class="icon-envelope icon-white"></i> 보내기
		</button>
	</div>
</form:form>

<div id="searchDialog" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3>기부목적 검색</h3>
			</div>
			<div class="modal-body">
			<!-- 
				<form class="form-inline" method="post" action="#">
					<span>이름:</span> <input type="text" name="name" />
					<button type="submit" class="btn btn-default">검색</button>
				</form>
			-->
				<div id="scroll">
				<div id="searchResult">
					<table id="donationTable">
						<thead>
							<tr>
								<th>기관</th>
								<th>기관종류</th>
								<th>기부목적</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="donationPurpose" items="${ donationPurposeList }">
								<tr data-id="${ donationPurpose.ID }">
									<td>${ donationPurpose.corporateName }</td>
									<td>${ donationPurpose.codeName }</td>
									<td>${ donationPurpose.name }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn" data-dismiss="modal">닫기</button>
				<button class="btn btn-primary" onclick="selectDonationPurpose()" data-dismiss="modal">선택</button>
			</div>

		</div>

	</div>
</div>

<script>
function selectDonationPurpose() {
    var selectedTr = $("#searchResult tr.selected");
    var donationPurposeID = selectedTr.attr("data-id");
    var name = selectedTr.find("td:nth-child(3)").text();
    $("input[name=dname]").val(name);
    $("input#donationPurposeID").val(donationPurposeID);
}

$(document).ready(function(){
	 $("#searchResult tr").click(function() {
         $("#searchResult tr").removeClass("selected");
         $(this).addClass("selected");
     })
});
</script>