<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div>
    <h1>기부금영수증 미리보기</h1>
    <p>${ receipt.id }</p>
    <p> 영수증 일련번호 | ${ receipt.no }</p>
    <div class="pull-right">
    	<a href="receiptList.do?${ pagination.queryString }" class="btn">
    		<i class="icon-list">목록으로</i>
    	</a>
    </div>
    <h4>기부자</h4>
    <p>성명 / ${ receipt.name }</p>
    <p>주민등록번호 /  ${ receipt.juminNo }</p>
    <p>주소(소재지) / ${ receipt.address }</p>
    <hr/>
    <h4>기부금단체</h4>
    <p>단체명 / ${ receipt.corName }</p>
    <p>사업자등록번호 /  ${ receipt.corporateNo }</p>
    <p>소재지 / ${ receipt.corAddress }</p>
    <p> 기부금공제대상 기부금단체 근거법령 / 법인세법 제24조 제2항 가목</p>
    <hr/>
    <h4>기부내용</h4>
    <table class="table table-bordered">
		    <thead>
		        <tr>
		            <th>유형</th>
		            <th>코드</th>
		            <th>구분</th>
		            <th>연월일</th>
		            <th>품명</th>
		            <th>금액</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="payment" items="${ paymentList }">
		        	<tr>
		            	<td>법정</td>
		             	<td>10</td>
		             	<td>금전/현물</td>
		             	<td>${ payment.paymentDate }</td>
		             	<td>교육,장학</td>
		             	<td>${ payment.amount }</td>
		           </tr>
		        </c:forEach>
		    </tbody>
	</table>
	<hr/>
	<p>신청인 / ${receipt.name }</p>
	<p>기부금 수령인 / ${receipt.representative }</p>
    <p>발급일자 / ${ receipt.createDate }</p>
    

</div>
