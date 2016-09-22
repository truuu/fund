<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="http://code.jquery.com/jquery-1.5.js"></script>
<script src="./images/jquery-migrate-1.4.1.min.js"></script>
<script>
	$(function() {
		//전체선택 체크박스 클릭
		$("#allCheck").click(function() {
			//만약 전체 선택 체크박스가 체크된상태일경우
			if ($("#allCheck").prop("checked")) {
				//해당화면에 전체 checkbox들을 체크해준다
				$("input[type=checkbox]").prop("checked", true);
				// 전체선택 체크박스가 해제된 경우
			} else {
				//해당화면에 모든 checkbox들의 체크를해제시킨다.
				$("input[type=checkbox]").prop("checked", false);
			}
		})
	})

	function deleteFunction() {
		var arrayParam = new Array();

		if (confirm("삭제하시겠습니까?") == true) {

			// name이 같은 체크박스의 값들을 배열에 담는다.
			var checkboxValues = [];
			$("input[name=class[1]]:checked").each(function(i) {
				
				checkboxValues.push($(this).val());
			});

			// 사용자 ID(문자열)와 체크박스 값들(배열)을 name/value 형태로 담는다.
			var allData = {
				"checkArray" : checkboxValues
			};

			$.ajax({
				url : "scholarshipDelete.do",
				type : 'GET',
				data : allData,
				success : function(data) {
					
					window.opener.location.reload();
					self.close();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);

				}
			});

		} else {
			return;
		}
	}
</script>
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
</style>
<body>

	<h3>장학증서 발급대장</h3>
	<form:form method="get" modelAttribute="pagination" class="pagination">
		<input type="hidden" name="pg" value="1" />
		<form:hidden path="bd" />
	

	<div class="form-inline">
			<form:select path="ss" id="search" class="msize">
				<form:option value="0" label="검색조건" />
				<form:option value="1" label="학번" />
				<form:option value="2" label="이름" />
				<form:option value="3" label="학과" />
				<form:option value="4" label="발행일" />	
			</form:select>
			<form:input path="st" />
			<button type="submit" class="btn btn-small">검색</button>
		<a id="deleteBtn" onclick="deleteFunction()" class="button">삭제</a>
		<a href="printScholarship.do" id="donationBtn"
				class="button button-reversed">발급하기</a>
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
								name="class[1]" class='input_ch' id='input_check1'
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

<div class="row text-center">
	<div class="pagination pagination-small pagination-centered">
		<ul>
			<c:forEach var="page" items="${ pagination.pageList }">
				<li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
</form:form>

</body>