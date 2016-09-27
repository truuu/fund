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
<h2 class="page-header">기부목적</h2>
<div class="code">
	<div id="column-right">
		<a href="donationPurposeCreate.do" type="button" class="button button-reversed" >추가</a> 
	</div>
	
	<form method="get">
    <input type="hidden" name="pg" value="1" />
	
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>기관</th>
				<th>기관종류</th>
				<th>기부목적</th>
				<th>구분</th>
			</tr>
		</thead>
		 <tbody>
            <c:forEach var="donationPurpose" items="${ list }">
                <tr data-url="donationPurposeEdit.do?ID=${donationPurpose.ID}" >
                    <td>${ donationPurpose.corporateName }</td>
                    <td>${ donationPurpose.codeName }</td>
                    <td>${ donationPurpose.name }</td>
                    <td>${ donationPurpose.gubun }</td>
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