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

<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<h1 class="page-header">장학 증서</h1>
			<div id="column-right">
			<a href="#" class="button">미리보기</a> <a href="#"
					class="button button-reversed">발급</a>
			</div>
			<table class="table">
				<tbody>
					<tr>
						<td id="table_a"></td>
						<td id="table_b"></td>
						<td id="table_a">학과</td>
						<td id="table_b"><select>
								<option selected="selected">선택</option>
								<option value="0">신학과</option>
								<option value="1">영어학과</option>
								<option value="2">일어일본학과</option>
								<option value="3">중어중국학과</option>
								<option value="4">사회복지학과</option>
								<option value="5">사회과학부</option>
								<option value="6">신문방송학과</option>
								<option value="7">경영학부</option>
								<option value="8">디지털컨텐츠학과</option>
								<option value="9">컴퓨터공학과</option>
								<option value="10">소프트웨어공학과</option>
								<option value="11">정보통신공학과</option>
								<option value="12">글로컬IT학과</option>
						</select></td>
					</tr>
					<tr>
						<td id="table_a">학번</td>
						<td id="table_b"><input type="text"></td>
						<td id="table_a">성명</td>
						<td id="table_b"><input type="text"></td>
					</tr>
					<tr>
						<td id="table_a">내용</td>
						<td colspan="3" id="table_b"><textarea></textarea></td>
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


