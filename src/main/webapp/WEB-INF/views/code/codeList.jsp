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
</head>
<body>
<h2 class="page-header">${name}</h2>
<div class="code">
	<div id="column-right">
		<a href="/fund_sys/code/create.do?CodeGroupID=${CodeGroupID}" type="button" class="button button-reversed" 
			>추가</a> 
	</div>
	<form method="get">
    <input type="hidden" name="pg" value="1" />
	
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>코드명</th>
				<th>etc1</th>
			</tr>
		</thead>
		 <tbody>
            <c:forEach var="code" items="${ list }">
                <tr data-url="edit.do?ID=${code.ID}">
                    <td>${ code.codeName }</td>
                    <td>${ code.etc1 }</td>
                </tr>
            </c:forEach>
        </tbody>
	</table>
	<!-- 
	    <div class="pagination pagination-small pagination-centered">
        <ul>
            <c:forEach var="page" items="${ pagination.pageList }">
                <li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
            </c:forEach>
        </ul>
    </div>
     -->
</form>

</div>
</body>
</html>