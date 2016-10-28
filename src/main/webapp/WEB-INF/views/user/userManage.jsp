<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function sponorSearch(){
	var codeName=$("#search option:selected").text();
	var nameForSearch=$('#nameForSearch').val();
	if(codeName=='이름'){
		alert(codeName+' '+nameForSearch)
	location.href="http://localhost:8080/fund_sys/sponsor/search.do?codeName="+codeName+"&nameForSearch="+nameForSearch;
	}else{
		alert(codeName)
		location.href="http://localhost:8080/fund_sys/sponsor/search.do?codeName="+codeName;
		
	}
	
}





</script>
<style>
tr#topTable td{
	text-align:center;
}
</style>

<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<h1 class="page-header">회원관리</h1>
			<div id="column-right">
				<a href="sponsor.do" class="button button-reversed">신규</a> 
			</div>

			<div id="search">
				<div class="column-right">
					<select name="search" id="search" class="msize">
						<option selected="selected" value="검색조건">검색조건</option>
						<option value="0">이름</option>
						<option value="1">개인</option>
						<option value="2">가족</option>
						<option value="3">단체</option>
						<option value="4">법인</option>
						<option value="5">직원</option>
						<option value="6">교수</option>
						<option value="7">동문</option>
						<option value="8">학부모</option>
						<option value="9">성직자</option>
						<option value="10">교인</option>
						<option value="11">독지가</option>
						<option value="12">교회</option>
						<option value="13">기업가</option>
						<option value="14">기타</option>
						
					</select> <input type="text" id="nameForSearch" name="nameForSearch">
					<button type="submit" class="btn btn-small" onclick="sponorSearch()">검색</button>
				</div>
			</div>

<form>
			<div id="column-right">
				<button class="btn" type="submit" name="cmd" value="xlsx">엑셀파일</button>
			</div>
    <input type="hidden" name="pg" value="1" />


			<table class="table table-bordered table-hover">
				<thead>
					<tr >
						<th>사용자 닉네임</th>
						<th>이름</th>
						<th>이메일</th>
						<th>권한</th>
						
						
					</tr>
				</thead>
				<tbody>
			<c:forEach var="user" items="${list}">
			<tr data-id="${sponsor.id}" id="topTable">
			<td>${user.loginName}</td>
			<td>${user.name}</td>
			<td>${user.email}</td>
			<td></td>
			
			</tr>
			</c:forEach>
				</tbody>
			</table>
			<div align="center">
			    <div class="pagination">
					<ul class="pagination pagination-sm">
						<c:forEach var="page" items="${pagination.pageList }">
							<li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
</form>



		</div>
	</div>
</div>


