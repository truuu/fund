<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
function deleteFunction() {

    if(confirm("삭제하시겠습니까?")==true){
    	location.href="/fund_sys/code/donationPurposeDelete.do?ID=${donationPurpose.ID}";
    }
    else{
    		return;
    }
}
</script>

<h2>기관목적 수정</h2>
<hr />

<form:form method="post" modelAttribute="donationPurpose">
    <div>
			<span>기관:</span> <form:select path="corporateID"> 
			<c:forEach var="corporate" items="${corporateList}">
			<form:option value="${corporate.ID}" label="${corporate.name}" /> 
			</c:forEach>
			</form:select>
		</div>
		<div>
			<span>기관종류:</span> <form:select path="organizationID">
				<c:forEach var="organization" items="${organizationList}">
					<form:option value="${organization.ID}" label="${organization.codeName}" /> 
				</c:forEach>
			</form:select>
		</div>
		<div>
			<span>기부목적:</span> <form:input path="name" />
		</div>
		<div>
			<span>구분:</span> <form:input path="gubun" />
		</div>

    <div>
        <button type="submit" class="btn btn-primary" >
            <i class="icon-ok icon-white"></i> 저장
        </button>
        <button type="button" onclick="deleteFunction()" class="btn btn-default">
            <i class="icon-ok icon-white"></i> 삭제
        </button>
        <a href="donationPurposeList.do" class="btn btn-default">
            <i class="icon-ban-circle"></i> 취소
        </a>
    </div>
</form:form>

