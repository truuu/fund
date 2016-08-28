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
<h2 class="page-header">${codeGroup}</h2>
<div class="code">
	<div id="column-right">
		<a href="/fund_sys/code/create.do?codeGroup=${codeGroup}" type="button" class="button button-reversed" 
			>추가</a> <a href="#" class="button">수정</a> <a
			href="#" class="button">삭제</a>
	</div>
	<form method="get">
    <input type="hidden" name="pg" value="1" />
	
	<table class="table table-bordered">
		<thead>
			<tr>
				<th><input type="checkbox" id='check_all2' class='input_check' /></th>
				<th>코드명</th>
			</tr>
		</thead>
		 <tbody>
            <c:forEach var="code" items="${ list }">
                <tr >
                	<td class="input_check"><input type="checkbox" name='class[1]' /></td>
                    <td>${ code.codeName }</td>
                </tr>
            </c:forEach>
        </tbody>
	</table>
	    <div class="pagination pagination-small pagination-centered">
        <ul>
            <c:forEach var="page" items="${ pagination.pageList }">
                <li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
            </c:forEach>
        </ul>
    </div>
</form>

</div>
</body>
</html>