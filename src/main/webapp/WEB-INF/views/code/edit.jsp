<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function deleteFunction() {

    if(confirm("삭제하시겠습니까?")==true){
    	location.href="/fund_sys/code/delete.do?ID=${code.ID}";
    }
    else{
    		return;
    }
}
</script>
<style>
  input[name=title] { width: 700px; border-style: groove; margin: 4px;}
</style>

<h2>${name}</h2>
<hr />

<form:form method="post" modelAttribute="code">
    <div>
        <span>코드명:</span>
        <form:input path="codeName" /><br>
        <span>etc1:</span>
        <form:input path="etc1" />
        <form:input type="hidden" path="CodeGroupID" />

    </div>
    

    <div>
        <button type="submit" class="btn btn-primary" >
            <i class="icon-ok icon-white"></i> 저장
        </button>
        <button type="button" onclick="deleteFunction()" class="btn btn-default">
            <i class="icon-ok icon-white"></i> 삭제
        </button>
        <a href="codeList.do?CodeGroupID=${ code.codeGroupID }" class="btn btn-default">
            <i class="icon-ban-circle"></i> 취소
        </a>
    </div>
</form:form>

