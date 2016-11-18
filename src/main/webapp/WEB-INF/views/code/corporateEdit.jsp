<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	function deleteFunction() {

		if (confirm("삭제하시겠습니까?") == true) {
			location.href = "/fund_sys/code/corporateDelete.do?ID=${corporate.ID}";
		} else {
			return;
		}
	}
</script>
<style>
table.table {
	width: 40%;
}

#table_a {
	width: 50%;
	vertical-align: middle;
}

#btn3 {
	margin-left: 22%;
}
.post{
	width:44%;
}
</style>

<h2>기관 수정</h2>
<hr />

<form:form method="post" modelAttribute="corporate">
	<table class="table">
		<tr>
			<td id="table_a">이름</td>
			<td><form:input path="name" /><br>
			<form:errors path="name" /></td>
		</tr>
		<tr>
			<td id="table_a">기관번호</td>
			<td><form:input path="corporateNo" /><br>
			<form:errors path="corporateNo" /></td>
		</tr>
		<tr>
			<td id="table_a">대표자명</td>
			<td><form:input path="representative" /><br>
			<form:errors path="representative" /></td>
		</tr>
		<tr>
				<td id="table_a">주소</td>
				<td>
				<input type="text" name="postNum" id="sample6_postcode" class="post" placeholder="우편번호" value="${post}" >
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				<input type="text" name="address1" id="sample6_address" placeholder="주소" value="${address1}" >
				<input type="text" name="address2" id="sample6_address2" placeholder="상세주소" value="${address2}" >
				<br>
				<input type="hidden" name="address" />
				<form:errors path="address"/>
				</td>
			</tr>
	</table>
	<div>
		<button type="submit" id="btn3" class="btn btn-primary">
			<i class="icon-ok icon-white"></i> 저장
		</button>
		<button type="button" onclick="deleteFunction()"
			class="btn btn-danger">
			<i class="icon-ok icon-white"></i> 삭제
		</button>
		<a href="corporateList.do" class="btn btn-default"> <i
			class="icon-ban-circle"></i> 취소
		</a>
	</div>
</form:form>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample6_address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('sample6_address2').focus();
            }
        }).open();
    }
</script>

