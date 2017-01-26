<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/fund_sys/res/js/daum_postcode.js"></script>

<style>
  input.address { min-width: 300px; }
</style>

<script type="text/javaScript">
$(function() {
  $('#autoChurch').keyup(function() {
     var content = $('#autoChurch').val();
     if (content.length >= 2) {
        $.ajax({
           url: "http://localhost:8080/fund_sys/sponsor/autoList.do?input=" + content,
           type: "GET", dataType: "json",
           error: function(request, status,error) { },
           success: function(data) { 
        	   var a = $.map(data, function(e) { return e.codeName; });
        	    $("#autoChurch").autocomplete({ source : a });
           }
        });
     }
  });
});
</script>

<form:form method="post" action="sponsorInsert.do" id="target" modelAttribute="sponsor">

<div class="row">
  <div id="column-right">
    <button class="btn btn-primary" type="submit">저장</button>
    <a href="delete.do?id=${ sponsor.id }" class="btn btn-danger" data-confirm-delete>삭제</a>
    <a href="list.do?${ pagination.queryString }" class="btn btn-info">목록으로</a>
  </div>
</div>

<table class="table">
  <tr>
    <c:if test="${ sponsor.signUpDate == null}">
      <input type="hidden" name="sort" value="0" />
    </c:if>
    <c:if test="${ sponsor.signUpDate != null}">
      <input type="hidden" name="sort" value="1" />
    </c:if>
    <td id="table_a">후원인번호</td>
    <td id="table_b"><input  type="text" name="sponsorNo" readonly value="${ sponsor.sponsorNo }" /></td>
    <td id="table_a">우편물 발송여부</td>
    <td id="table_b"><input type="radio" value="true" name="mailReceiving" ${ sponsor.mailReceiving ? "checked" :"" } />발송동의 
      <input type="radio" value="false" name="mailReceiving" ${ !sponsor.mailReceiving ? "checked" :"" } />발송미동의</td>
  </tr>
  <tr>
    <td id="table_a">이름</td>
    <td id="table_b"><form:input  path="name" placeholder="이름을 입력해주세요" />
      <span class="sponsorError"><form:errors path="name" /></span></td>
    <td id="table_a">우편물 발송지</td>
    <td id="table_b"><input type="radio" value="0" name="mailTo" ${ sponsor.mailTo==0 ? "checked" :"" } />자택 
      <input type="radio" value="1" name="mailTo" ${ sponsor.mailTo==1 ? "checked" :"" } /> 직장</td>
  </tr>
  <tr>
    <td id="table_a">주민번호</td>
    <td id="table_b"><input  type="text" name="juminNo" placeholder="-를 제외하고 입력해주세요." value="${ sponsor.juminNo }" /> 
      <span class="sponsorError"><form:errors path="juminNo" /></span></td>
    <td id="table_a" rowspan="2">자택주소</td>
    <td id="table_b" rowspan="2">
      <input class=" address" type="text" name="homePostCode" id="homePostCode" placeholder="우편번호" value="${ sponsor.homePostCode }" /> 
      <input type="button" onclick="homeSearch()" value="우편번호 찾기" style="display: inline-block;" />
      <div>
        <input class=" address" type="text" name="homeRoadAddress" id="homeRoadAddress" placeholder="도로명주소" value="${sponsor.homeRoadAddress }" />
      </div>
      <div>
        <input class=" address" type="text" name="homeDetailAddress" id="homeDetailAddress" placeholder="상세주소" value="${ sponsor.homeDetailAddress }" /> 
        <span class="sponsorError"><form:errors path="homeDetailAddress" /></span>
      </div></td>
  </tr>
  <tr>
    <td id="table_a">후원인구분1</td>
    <td id="table_b"><form:select path="sponsorType1Id">
        <form:options itemValue="ID" itemLabel="codeName" items="${ sponsorType1List }" />
      </form:select></td>
  </tr>
  <tr>
    <td id="table_a">후원인구분2</td>
    <td id="table_b"><form:select path="sponsorType2Id">
        <form:options itemValue="ID" itemLabel="codeName" items="${ sponsorType2List }" />
      </form:select></td>
    <td id="table_a">자택 전화번호</td>
    <td id="table_b"><input  type="text" name="homePhone" placeholder="02-0000-0000" value="${ sponsor.homePhone}" /></td>
  </tr>
  <tr>
    <td id="table_a">가입일</td>
    <td id="table_b"><input class=" date" type="text" name="signUpDate" value="${ sponsor.signUpDate }" /></td>
    <td id="table_a">핸드폰 번호</td>
    <td id="table_b"><input  type="text" name="mobilePhone" placeholder="010-0000-0000" value="${ sponsor.mobilePhone }" /></td>
  </tr>
  <tr>
    <td id="table_a">추천인</td>
    <td id="table_b"><input  type="text" name="recommender" placeholder="추천인 이름을 적어주세요." value="${ sponsor.recommender }" /> 
      <span class="sponsorError"><form:errors path="recommender" /></span></td>
    <td id="table_a">이메일</td>
    <td id="table_b"><input  type="email" name="email" placeholder="abcd@skhu.kr" value="${ sponsor.email}" /> 
      <span class="sponsorError"><form:errors path="email" /></span></td>
  </tr>
  <tr>
    <td id="table_a">추천인관계</td>
    <td><form:select path="recommenderRelation">
        <form:option value="없음" />
        <form:option value="가족" />
        <form:option value="지인" />
      </form:select></td>
    <td rowspan="2" id="table_a">비고</td>
    <td rowspan="2" id="table_b"><textarea  name="etc" cols="30" rows="3" style="height: 100px;">${ sponsor.etc}</textarea></td>
  </tr>
  <tr>
    <td id="table_a">소속교회</td>
    <td id="table_b"><input  type="text" id="autoChurch" name="church" value="${ sponsor.church}" /></td>
  </tr>
</table>

<div class="row">
  <div class="col-lg-7">
    <h4>근무처정보</h4>
    <table class="table">
      <tr>
        <td id="table_a">직장</td>
        <td id="table_b"><input  type="text" name="company" value="${ sponsor.company}" />
          <span class="sponsorError"><form:errors path="company" /></span></td>
      </tr>
      <tr>
        <td id="table_a">부서</td>
        <td id="table_b"><input  type="text" name="department" value="${ sponsor.department}" />
          <span class="sponsorError"><form:errors path="department" /></span></td>
      </tr>
      <tr>
        <td id="table_a">직위</td>
        <td id="table_b"><input  type="text" name="position" value="${ sponsor.position}" />
          <span class="sponsorError"><form:errors path="position" /></span></td>
      </tr>
      <tr>
        <td id="table_a">직장전화번호</td>
        <td id="table_b"><input  type="text" name="officePhone" value="${ sponsor.officePhone}" />
          <span class="sponsorError"><form:errors path="officePhone" /></span></td>
      </tr>
      <tr>
        <td id="table_a">직장주소</td>
        <td id="table_b">
          <input class=" address" type="text" name="officePostCode" id="officePostCode" placeholder="우편번호" value="${ sponsor.officePostCode}" /> 
          <input type="button" onclick="officeSearch()" value="우편번호 찾기" />
          <div>
            <input class=" address" type="text" name="officeRoadAddress" id="officeRoadAddress" placeholder="도로명주소" value="${ sponsor.officeRoadAddress}" />
          </div>
          <div>
            <input class=" address" type="text" name="officeDetailAddress" id="officeDetailAddress" placeholder="상세주소" value="${ sponsor.officeDetailAddress}" />
            <span class="sponsorError"><form:errors path="officeDetailAddress" /></span>
          </div></td>
      </tr>
    </table>
  </div>

  <div class="col-lg-5">
    <c:if test="${ sponsor.id != 0 }">
      <h4>첨부파일목록</h4><hr />

      <table class="table">
        <tbody>
          <c:forEach var="file" items="${ files }">
            <tr>
              <td>
                <a class="btn btn-small" href="download.do?id=${file.ID}"><i class="icon icon-file"></i> ${ file.fileName } / ${file.ID} </a> 
                <a class="glyphicon glyphicon-remove" href="fileDelete.do?id=${file.ID}&sid=${sponsor.id}" data-confirm-delete></a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
           
      <form method="post" action="upload.do" enctype="multipart/form-data">
        <span>파일:</span> <input type="file" name="file" /><br />
        <input type="hidden" name="sid" value="${ sponsor.id }" />
        <button type="submit" class="btn btn-info"><i class="icon-ok icon-white"></i> 업로드</button>
      </form>      
    </c:if>
  </div>
</div>

</form:form>

<span id="guide" style="color: #999"></span> <!-- daum_postcode.js 에서 사용함 -->
 