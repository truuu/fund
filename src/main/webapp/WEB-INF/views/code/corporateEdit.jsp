<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function deleteFunction() {

    if(confirm("삭제하시겠습니까?")==true){
    	location.href="/fund_sys/code/corporateDelete.do?ID=${corporate.ID}";
    }
    else{
    		return;
    }
}
</script>

<h2>기관 수정</h2>
<hr />

<form:form method="post" modelAttribute="corporate">
    <div>
        <div>
        <span>이름:</span>
        <form:input path="name" />
    </div>
    <div>
        <span>기관번호:</span>
        <form:input path="corporateNo" />
    </div>
     <div>
        <span>대표자명:</span>
        <form:input path="representative" />
    </div>
     <div>
        <span>주소:</span>
        <form:input path="address" />
    </div>

    </div>
    

    <div>
        <button type="submit" class="btn btn-primary" >
            <i class="icon-ok icon-white"></i> 저장
        </button>
        <button type="button" onclick="deleteFunction()" class="btn btn-default">
            <i class="icon-ok icon-white"></i> 삭제
        </button>
        <a href="corporateList.do" class="btn btn-default">
            <i class="icon-ban-circle"></i> 취소
        </a>
    </div>
</form:form>

