<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
a.deleteBtn {
	margin-left: 10px;
}

a#donationBtn, a#deleteBtn {
	float: right;
	margin-left: 4px;
}

form.pagination {
	width: 100%;
}
td{
	text-align:center;
}
</style>
<script>
	$(function() {
		$("thead input[type=checkbox]").click(function() {
			$("tbody input[type=checkbox").trigger("click");
		});
	});
	
	function deleteFunction() {

		if (confirm("삭제하시겠습니까?") == true) {

			//var checkboxValues = [];
			var checkboxValues = new Array();
			
			$('input:checkbox[name="class[1]"]:checked').each(function() {

				checkboxValues.push($(this).val());
				
			});
			location.href = "http://localhost:8080/fund_sys/certificate/scholarshipDelete.do?checkboxValues="+ checkboxValues;



		} else {
			return;
		}
	}
	
</script>

	<h2>장학증서 발급대장</h2>
	<form:form method="get" modelAttribute="pagination" class="pagination">
		<input type="hidden" name="pg" value="1" />
		<form:hidden path="bd" />


		<div class="form-inline">
			<form:select path="ss" id="search" class="msize commoninput">
				<form:option value="0" label="검색조건" />
				<form:option value="1" label="학번" />
				<form:option value="2" label="이름" />
				<form:option value="3" label="학과" />
				<form:option value="4" label="발행일" />
			</form:select>
			<form:input path="st" class="commoninput" />
			<button type="submit" class="btn btn-primary">검색</button>
			<a id="deleteBtn" onclick="deleteFunction()" class="btn btn-danger">삭제</a> <a
				href="printScholarship.do" id="donationBtn"
				class="btn btn-default">발급하기</a>
		</div>

		<div class="table-responsive">
			<table id="table1" class="table table-bordered">
				<thead>
					<tr>
						<th><input type="checkbox" id="allCheck" class="input_check" /></th>
						<th>일련번호</th>
						<th>학번</th>
						<th>이름</th>
						<th>학과</th>
						<th>발행일</th>
						<th>발행인</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="scholarship" items="${ list }">
						<tr>
							<td class="input_check"><input type="checkbox"
								name="class[1]" class="input_ch" id="input_check1"
								value="${ scholarship.ID }" /></td>
							<td>${ scholarship.num2 }</td>
							<td>${ scholarship.studentNo }</td>
							<td>${ scholarship.studentName }</td>
							<td>${ scholarship.department }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${ scholarship.createDate }" /></td>
							<td>${ scholarship.name }</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div align="center">
			    <div class="pagination">
					<ul class="pagination pagination-sm">
						<c:forEach var="page" items="${pagination.pageList }">
							<li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
	</form:form>

