<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			// 예제를 참고하여 다양한 활용법을 확인해 보세요.
		}
	}).open();
</script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="R" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

<script type="text/javaScript">

function fileDelete(id){
	alert('test '+id)
	$.ajax({
		url:"http://localhost:8080/fund_sys/sponsor/fileDelete.do",
		type:"GET",
		data :{id:id},
		success : function(){
			alert('delete success')
			location.href="http://localhost:8080/fund_sys/sponsor/sponsor.do";
		},
		error : function(request, status,error) {
			alert("통신실패")
			 console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


	$(function() {
		$('#autoChurch').keyup(function() {
							var content = $('#autoChurch').val();
							console.log('lengt >> ' + content.length)
							//alert(content)
							if (content.length >= 1) {
								$.ajax({
											url : "http://localhost:8080/fund_sys/sponsor/autoList.do?input="+content,
											type : "GET",
											error : function(request, status,error) {
												alert("통신실패")
											},
											dataType : "json",
											success : function(data) {
												var array = new Array();
												for (var i = 0; i < data.length; i++) {
													array[i] = data[i].codeName;
													
												}
												suc(array);
											}
										});
							}

						});

	});

	function suc(array) {

		console.log('넘어옴')
		var availableTags = array
		for (var i = 0; i < availableTags.length; i++) {
			console.log(availableTags[i])
		}
		$("#autoChurch").autocomplete({
			source : availableTags
		});
	}
	
	function insert(){
	alert('저장')
	$('#target').submit();
	}
	
	function update(){
		alert('update')
		$('#target').submit();
     }
	
	
	function deletes(sponsorNo){
		alert(sponsorNo)
		 location.href = "delete.do?id="+sponsorNo;
	}
</script>
<c:set var="mailReceiving" value="${sponsor.mailReceiving}"  />
<div class="panel panel-default">
	<div class="panel-heading">
		<h4>회원기본정보</h4>


		<div class="row">
			<div class="col-lg-12">
				<div id="column-right">
					<c:if test="${ sponsor.signUpDate==null}">
					<a onclick="insert()" class="button button-reversed">저장</a>
				</c:if>
				
				<c:if test="${ sponsor.signUpDate!=null}">
					<a onclick="update()" class="button button-reversed">수정</a>
				</c:if>

					<a onclick="deletes(${ sponsor.sponsorNo })" class="button">삭제</a>
				</div>
			</div>
		</div>
	</div>
	<!-- /.panel-heading -->

	<div class="panel-body">
		<div class="table-responsive">
	<table>
			<form id="target" action="sponsorInsert.do"  method="post">
				<tbody>
					<tr>
					<c:if test="${ sponsor.signUpDate==null}">
					    <input type="hidden" name="sort" value="0">
					</c:if>
					
					<c:if test="${ sponsor.signUpDate!=null}">
					    <input type="hidden" name="sort" value="1">
					</c:if>
					
						<td id="table_a">후원인번호</td>
						<td id="table_b"><input type="text" name="sponsorNo" readonly
							value="${ sponsor.sponsorNo }"></td>
						<td id="table_a">우편물 발송여부</td>
						<td id="table_b"><input type="radio" value="true" name="mailReceiving" ${sponsor.mailReceiving==true? "checked" :""} >
							발송동의 <input type="radio" value="false" name="mailReceiving" ${sponsor.mailReceiving==false? "checked" :""}> 발송미동의</td>
					</tr>
					<tr>
						<td id="table_a">이름</td>
						<td id="table_b"><input type="text" name="name" value="${ sponsor.name }"></td>
						<td id="table_a">우편물 발송지</td>
						<td id="table_b"><input type="radio" value="0" name="mailTo" ${sponsor.mailTo==0? "checked" :""}>
							자택 <input type="radio" value="1" name="mailTo" ${sponsor.mailTo==1? "checked" :""}> 직장</td>
					</tr>
					<tr>
						<td id="table_a">주민번호</td>
						<td id="table_b"><input type="text" name="juminNo" 
							placeholder="-를 제외하고 입력해주세요." value="${ sponsor.juminNo }"></td>
						<td id="table_a" rowspan="2">자택주소</td>
						<td id="table_b" rowspan="2"><input type="text" name="homePostCode" id="homePostCode"
									placeholder="우편번호" value="${ sponsor.homePostCode}" > <input type="button"
									onclick="homeSearch()" value="우편번호 찾기"
									style="display: inline-block;">
									<div>
										<input type="text" name="homeRoadAddress" id="homeRoadAddress"
											placeholder="도로명주소--------------" value="${sponsor.homeRoadAddress }" >
									</div>
									<div>
										<input type="text" name="homeDetailAddress" id="homeDetailAddress"
											placeholder="상세주소" value="${ sponsor.homeDetailAddress }">
									</div>
									</td>
						
					</tr>
					<tr>
						<td id="table_a">후원인구분1</td>
					    <td id="table_b"><select name="sponsorType1ID">
								<option value="18" ${sponsor.sponsorType1ID==18? "selected" :""}>개인</option>
								<option value="19" ${sponsor.sponsorType1ID==19? "selected" :""}>가족</option>
								<option value="20" ${sponsor.sponsorType1ID==20? "selected" :""}>단체</option>
								<option value="21" ${sponsor.sponsorType1ID==21? "selected" :""}>법인</option>

						</select></td>

					</tr>
					<tr>
						<td id="table_a">후원인구분2</td>
						<td id="table_b">
						    <input type="radio" value="22" name="sponsorType2ID" ${sponsor.sponsorType2ID==22? "checked" :""}>직원
							<input type="radio" value="23" name="sponsorType2ID" ${sponsor.sponsorType2ID==23? "checked" :""}>교수 
							<input type="radio" value="24" name="sponsorType2ID" ${sponsor.sponsorType2ID==24? "checked" :""}>동문 
							<input type="radio" value="25" name="sponsorType2ID" ${sponsor.sponsorType2ID==25? "checked" :""}>학부모
							<input type="radio" value="26" name="sponsorType2ID" ${sponsor.sponsorType2ID==26? "checked" :""}>성직자
							<input type="radio" value="27" name="sponsorType2ID" ${sponsor.sponsorType2ID==27? "checked" :""}>교인
							<input type="radio" value="29" name="sponsorType2ID" ${sponsor.sponsorType2ID==28? "checked" :""}>독지가 
							<input type="radio" value="30" name="sponsorType2ID" ${sponsor.sponsorType2ID==29? "checked" :""}>교회
							<input type="radio" value="" name="sponsorType2ID" ${sponsor.sponsorType2ID==1? "checked" :""}>단체
							<input type="radio" value="31" name="sponsorType2ID" ${sponsor.sponsorType2ID==31? "checked" :""}>기업가
							<input type="radio" value="32" name="sponsorType2ID" ${sponsor.sponsorType2ID==32? "checked" :""}>기타
						</td>
						<td id="table_a">자택 전화번호</td>
						<td id="table_b"><input type="text" name="homePhone" placeholder="02-0000-0000" value="${ sponsor.homePhone}"></td>

					</tr>
					<tr>

						<td id="table_a">가입일</td>
						<td id="table_b"><input type="date" name="signUpDate" value="${ sponsor.signUpDate}"></td>
						<td id="table_a">핸드폰 번호</td>
						<td id="table_b"><input type="text" name="mobilePhone"
							placeholder="010-0000-0000" value="${ sponsor.mobilePhone}" ></td>

					</tr>
					<tr>
						<td id="table_a">추천인</td>
						<td id="table_b"><input type="text" name="recommender"
							placeholder="추천인 이름을 적어주세요." value="${ sponsor.recommender}"></td>
						<td id="table_a">이메일</td>
						<td id="table_b"><input type="email" name="email"
							placeholder="abcd@skhu.kr" value="${ sponsor.email}"></td>

					</tr>
					<tr>
						<td id="table_a">추천인관계  </td>
						<td><select name="recommenderRelation">
								<option value="가족" ${sponsor.recommenderRelation=='가족'? "selected" :""}>가족</option>
								<option value="지인" ${sponsor.recommenderRelation=='지인'? "selected" :""}>지인</option>
						</select></td>
						<td rowspan="2" id="table_a">비고</td>
						<td rowspan="2" id="table_b"><textarea name="etc" cols="30" rows="3">${ sponsor.etc}</textarea></td>
						


					</tr>

					<tr>
						<td id="table_a">소속교회</td>
						<td id="table_b"><input type="text" id="autoChurch" name="church" value="${ sponsor.church}">
						</td>
						

					</tr>
				</tbody>
			</table>
		</div>
		<!-- /.table-responsive -->
	</div>
	<!-- /.panel-body -->
</div>
<!-- /.panel -->

<!-- 회원기본정보 -->

<div class="row">
	<div class="col-lg-6">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>근무처정보</h4>
			</div>
			<!-- /.panel-heading -->

			<div class="panel-body">
				<div class="table-responsive">
					<table class="table">
						<tbody>
							<tr>
								<td id="table_a">직장</td>
								<td id="table_b"><input type="text" name="company" value="${ sponsor.company}"></td>

							</tr>
							<tr>
								<td id="table_a">부서</td>
								<td id="table_b"><input type="text" name="department" value="${ sponsor.department}"></td>

							</tr>
							<tr>
								<td id="table_a">직위</td>
								<td id="table_b"><input type="text" name="position" value="${ sponsor.position}"></td>
							</tr>

							<tr>
								<td id="table_a">직장전화번호</td>
								<td id="table_b"><input type="text" name="officePhone" value="${ sponsor.officePhone}"></td>
							</tr>

							<tr>
								<td id="table_a">직장주소</td>
								<td id="table_b">
								<input type="text" name="officePostCode" id="officePostCode" placeholder="우편번호" value="${ sponsor.officePostCode}"> 
								<input type="button" onclick="officeSearch()" value="우편번호 찾기"
									style="display: inline-block;">
									<div>
										<input type="text" name="officeRoadAddress" id="officeRoadAddress"  placeholder="도로명주소" value="${ sponsor.officeRoadAddress}">
									</div>
									<div >
										<input type="text" name="officeDetailAddress" id="officeDetailAddress" placeholder="상세주소" value="${ sponsor.officeDetailAddress}">
									</div>
									</td>
								
							</tr>

						</tbody>
						</form>
					</table>
				
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->

	</div>
	<!-- 근무지정보 -->
	

	<div class="col-lg-6">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>첨부파일목록</h4>
				<hr />

				<form method="post" action="upload.do" enctype="multipart/form-data">
					<div>
						<span>파일:</span> <input type="file" name="file" />
					</div>
					<div>
						<button type="submit" class="btn btn-primary">
							<i class="icon-ok icon-white"></i> 저장하기
						</button>
						
					</div>
				</form>

				<!-- /.panel-heading -->


				<div class="panel-body">
					<div class="table-responsive"></div>


					<table class="table">
						<tbody>
							<span class="lbl">첨부파일1:</span>
							<c:forEach var="file" items="${ files }">
								<tr>
									<td><a class="btn btn-small" href="download.do?id=${file.ID}"><i
											class="icon icon-file"></i> ${ file.fileName } / ${file.ID} </a>
										
						<span  class="glyphicon glyphicon-remove fileDelete" aria-hidden="true"
						onclick="fileDelete(${file.ID})"></span>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->

	</div>






</div>



<span id="guide" style="color: #999"></span>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	function homeSearch() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 도로명 조합형 주소 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}
						// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
						if (fullRoadAddr !== '') {
							fullRoadAddr += extraRoadAddr;
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('homePostCode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('homeRoadAddress').value = fullRoadAddr;
						//document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							//예상되는 도로명 주소에 조합형 주소를 추가한다.
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							document.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							document.getElementById('guide').innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';

						} else {
							document.getElementById('guide').innerHTML = '';
						}
					}
				}).open();
	}
	
	function officeSearch() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 도로명 조합형 주소 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}
						// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
						if (fullRoadAddr !== '') {
							fullRoadAddr += extraRoadAddr;
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('officePostCode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('officeRoadAddress').value = fullRoadAddr;
						//document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							//예상되는 도로명 주소에 조합형 주소를 추가한다.
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							document.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							document.getElementById('guide').innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';

						} else {
							document.getElementById('guide').innerHTML = '';
						}
					}
				}).open();
	}
</script>