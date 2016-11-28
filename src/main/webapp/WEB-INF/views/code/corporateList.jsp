<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
    $(function() {
        $("tbody tr").click(function() {
            location.href = $(this).attr("data-url");
        });
        $("div.pagination a").click(function() {
            $("input[name=pg]").val($(this).attr("data-page"));
            $("form").submit();
        });
    });
</script>
<style>
tr td { text-align: center; }
tr:hover{ background-color: #ffe; cursor: pointer; }
</style>
</head>
<body>
<h1 class="page-header">기관</h1>
<div class="code">
	<div id="column-right">
		<a href="/fund_sys/code/corporateCreate.do" type="button" class="btn btn-primary" >추가</a> 
	</div>
	<form method="get">
    <input type="hidden" name="pg" value="1" />
    
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>기관명</th>
				<th>기관번호</th>
				<th>대표자명</th>
				<th>주소</th>
			</tr>
		</thead>
		 <tbody>
            <c:forEach var="corporate" items="${ list }">
                <tr data-url="corporateEdit.do?ID=${corporate.ID}">
                    <td>${ corporate.name }</td>
                    <td>${ corporate.corporateNo }</td>
                    <td>${ corporate.representative }</td>
                    <td>${ corporate.address }</td>
                </tr>
            </c:forEach>
        </tbody>
	</table>
	
</form>
	
	
</div>
</body>
</html>