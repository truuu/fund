<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<script src="/fund/res/js/jquery.js" type="text/javascript"></script>
<script src="/fund/res/js/jquery.mask.min.js" type="text/javascript"></script>
	
<script>
function chkfrm(){ 
	document.getElementById("detail").style.display="inline-block";	
	document.getElementById("detail2").style.display="inline-block";	
	}
	
$(document).ready(function(){
	  $('.money').mask('000,000,000,000,000,000', {reverse: true});
});

function save() {
    alert("저장하시겠습니까?");
}
</script>
<style>
#new,#detail2{
	float:right;
}
table.tb1{
	display:none;
}
.size{
	width:150px;
}

table#detail{
	width:35%;
}
table#detail2{
	width:60%;
}
</style>
	
	<div class="panel panel-default">
	<div class="panel-heading">
		<h3>약정 내역</h3>
		<div class="input-group1">
			<select name="search" id="search" class="msize">
				<option selected="selected" value="검색조건">검색조건</option>
				<option value="0">약정번호</option>
				<option value="2">약정상태</option>
				<option value="3">기부형태</option>
				<option value="4">기부목적</option>
				<option value="5">약정금액</option>
				<option value="6">약정기간</option>
			</select> <input type="text" id="search_a" name="search_a">
			<button type="submit" class="btn btn-small">검색</button>
			<button id="new" class="btn btn-small" onclick="chkfrm()">신규 추가</button>
		</div>
	
		<div class="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>약정번호</th>
						<th>기부목적</th>
						<th>납입방법</th>
						<th>약정일</th>
						<th>기간</th>
						<th>약정상태</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>2016-00001-01</td>
						<td>장학금</td>
						<td>CMS</td>
						<td>2016.08.08</td>
						<td>2016.08.08~2017.08.08(12개월)</td>
						<td>진행</td>
						<td></td>
					</tr>		
				</tbody>
			</table>
		</div>
		<table class="tb1" id="detail">
				<tbody>
					<tr>
						<td id="table_a">약정번호</td>
						<td id="td2"><input type="text" name="contract_num" class="tsize"
							readonly value="2016-0001-02"></td>
					</tr>
					
					<tr>
						<td id="table_a">납입방법</td>
						<td id="td2"><select name="donation_f" id="donation_f" class="asize">
								<option selected="selected" value="선택">선택</option>
								<option value="CMS">CMS</option>
								<option value="자동이체">자동이체</option>
								<option value="급여공제">급여공제</option>
						</select></td>
					</tr>
					<tr>
						<td id="table_a">기부목적</td>
						<td id="td2"><select name="donation_o" id="donation_o" class="asize">
								<option selected="selected" value="선택">선택</option>
								<option value="장학금">장학금</option>
								<option value="학교복지">학교복지</option>
						</select></td>
					</tr>
					<tr>
						<td id="table_a">기부기관</td>
						<td id="td2"><select><option>학교</option>
						<option>법인</option></select></td>
						
					</tr>
					<tr>
						<td id="table_a">약정일자</td>
						<td id="td2"><input type="date" name="contract_num" class="asize"></td>
					</tr>
				</tbody>
			</table>
			
			<table class="tb1" id="detail2">
				<tbody>
					<tr>
						<td id="table_a">약정금액</td>
						<td><input type="text" class="money" /></td>
						<td id="table_a"></td>
						<td></td>
					</tr>	
					<tr>
						<td id="table_a">시작일</td>
						<td><input type="date" name="contract_num1" class="size"></td>
						<td id="table_a">종료일</td>
						<td><input type="date" name="contract_num2" class="size">(x개월)</td>
					</tr>
					<tr>
						<td id="table_a">1회납입액</td>
						<td><input type="text"  class="money"/></td>
						<td id="table_a">은행명</td>
						<td><input type="text" /></td>
					</tr>
					<tr>
						<td id="table_a">계좌번호</td>
						<td><input type="text" /></td>
						<td id="table_a">예금주</td>
						<td><input type="text" /></td>
					</tr>
					<tr>
						<td id="table_a">약정상태</td>
						<td><select><option selected>진행</option>
						<option>종료</option></select></td>
						<td id="table_a">비고</td>
						<td><input type="text" /></td>
					</tr>
				</tbody>
			</table>
			<hr>
			<center><button type="submit" class="btn" id="save" onclick="save()">저장</button></center>
	</div>
	</div>