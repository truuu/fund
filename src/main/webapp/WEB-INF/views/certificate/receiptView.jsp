<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div>
    <h1>기부금영수증 미리보기</h1>
    <p>${ receipt.id }</p>
    <div class="pull-right">
    	<a href="receiptList.do?${ pagination.queryString }" class="btn">
    		<i class="icon-list">목록으로</i>
    	</a>
    </div>
    

</div>
