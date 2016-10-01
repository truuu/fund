<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<style>
iframe { width: 800px; border: 1px solid #ddd; height: 900px; }
</style>
<script>
function print(index){
	
	department=$('#department').val();
	studentNo=$('#studentNo').val();
	studentName=$('#studentName').val();
	serialNo=$('#serialNo').val();
	content=$('#content').val();
	
	if (index == 2) {
    	alert("장학증서를 발급하시겠습니까?");
    	location.href="../certificate/scholarshipIssue.do?department="+department+"&studentNo="+studentNo+"&studentName="+studentName;
      
    }
    if (index == 1) {
    	alert("미리보기1");
    	location.href="../certificate/spreview.do?department="+department+"&studentNo="+studentNo+"&studentName="+studentName+"&serialNo="+serialNo+"&content="+content;
      
    }
				
	
}

</script>
 <style>
#table_a {
	vertical-align: middle;
}
textarea{
	width:90%;
	height:100px;
}

</style>

<form name="printForm" action="/fund_sys/report/printScholarship.do">
<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<h1 class="page-header">장학 증서</h1>
			<div id="column-right">
			<button type="button" style="margin-bottom: 5px" class="btn btn-default" onclick="print(1)">장학증서발급</button> 
			<button type="button"  style="margin-bottom: 5px" class="btn btn-primary"onclick="print(2)">인쇄</button>
			</div>
			<table class="table">
				<tbody>
					<tr>
						<td id="table_a">일련번호</td>
						<td id="table_b"><input type="text" id="serialNo" name="serialNo" value="${serialNo}"></td>
						<td id="table_a">학과</td>
						<td id="table_b"><select name="department" id="department">
								<option selected="selected">선택</option>
								<option value="신학과">신학과</option>
								<option value="영어학과">영어학과</option>
								<option value="일어일본학과">일어일본학과</option>
								<option value="중어중국학과">중어중국학과</option>
								<option value="사회복지학과">사회복지학과</option>
								<option value="사회과학부">사회과학부</option>
								<option value="신문방송학과">신문방송학과</option>
								<option value="경영학부">경영학부</option>
								<option value="디지털컨텐츠학과">디지털컨텐츠학과</option>
								<option value="컴퓨터공학과">컴퓨터공학과</option>
								<option value="소프트웨어공학과">소프트웨어공학과</option>
								<option value="정보통신공학과">정보통신공학과</option>
								<option value="글로컬IT학과">글로컬IT학과</option>
						</select></td>
					</tr>
					<tr>
						<td id="table_a">학번</td>
						<td id="table_b"><input type="text" name="studentNo" id="studentNo"></td>
						<td id="table_a">성명</td>
						<td id="table_b"><input type="text" name="studentName" id="studentName"></td>
					</tr>
					<tr>
						<td id="table_a">내용</td>
						<td colspan="3" id="table_b"><textarea id="content" name="content"></textarea></td>
					</tr>
				</tbody>
			</table>

	

		</div>
	</div>
</div>
</form>


