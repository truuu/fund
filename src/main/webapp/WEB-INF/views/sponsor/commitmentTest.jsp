<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:useBean id="toDay" class="java.util.Date" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="/fund/res/js/jquery.js" type="text/javascript"></script>
<script src="/fund/res/js/jquery.mask.min.js" type="text/javascript"></script>
<head>
<script>

</script>
</head>
<form method="post">
<table class="tb1" id="detail2">
			<tbody>
				<tr>
					<td id="table_a">1회납입액</td>
					<td><input type="text" class="money" name="amountPerMonth" /></td>
					<td id="table_a">약정금액</td>
					<td></td>
				</tr>
				<tr>
					<td id="table_a">시작일</td>
					<td></td>
					<td id="table_a">종료일</td>
					<td></td>
				</tr>
				<tr>
					<td id="table_a">은행명</td>
					<td><input name="bankID" type="text"  /></td>
					<td id="table_a">계좌번호</td>
					<td><input name="accountNo" type="text" /></td>
				</tr>
				<tr>
					<td id="table_a">예금주</td>
					<td><input name="accountHolder" type="text" /></td>
					<td id="table_a">결제일</td>
					<td><select name="paymentDay"><option value="20">20일</option>
							<option value="25">25일</option></select></td>
				</tr>
				<tr>
					<td id="table_a">약정상태</td>
					<td><button>종료하기</button></td>
					<td id="table_a">비고</td>
					<td><input name="etc" type="text" /></td>
				</tr>
				
			<input name="commitmentID" type="hidden" value="3" />
			</tbody>
		</table>
		
		<button type="submit" class="btn" id="save" >저장</button>
</form>