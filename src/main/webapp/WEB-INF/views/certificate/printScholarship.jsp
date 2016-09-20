<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<style>
#table_a {
	vertical-align: middle;
}
textarea{
	width:90%;
	height:100px;
}
</style>

<form method="post">
<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<h1 class="page-header">장학 증서</h1>
			<div id="column-right">
			<button type="submit" class="button">미리보기</button> <a href="#"
					class="button button-reversed">인쇄</a>
			</div>
			<table class="table">
				<tbody>
					<tr>
						<td id="table_a">일련번호</td>
						<td id="table_b"><input type="text" name="serialNo" value="2016-0001"></td>
						<td id="table_a">학과</td>
						<td id="table_b"><select name="department">
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
						<td id="table_b"><input type="text" name="studentNo"></td>
						<td id="table_a">성명</td>
						<td id="table_b"><input type="text" name="studentName"></td>
					</tr>
					<tr>
						<td id="table_a">내용</td>
						<td colspan="3" id="table_b"><textarea name="content"></textarea></td>
					</tr>
				</tbody>
			</table>

			<div class="panel show">
				<div class="panel-heading">
					<h4>미리 보기</h4>
				</div>
				<div class="panel-body">
					<div class="show-body"></div>


				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->

		</div>
	</div>
</div>
</form>

