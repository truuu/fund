<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/fund_sys/res/js/daum_postcode.js"></script>

<style>
  input.address { min-width: 300px; margin-bottom: 2px; }
  textarea { width: 400px; height: 100px; }
</style>

<c:set var="tab1" value="active" /> 
<%@include file="_tab.jsp" %>

<form:form method="post" modelAttribute="sponsor">

<div class="pull-right mt4 mb4">
  <button class="btn btn-primary" type="submit" name="cmd" value="save">저장</button>
  <c:if test="${ sponsor.id > 0 }">
    <button class="btn btn-danger" type="submit" name="cmd" value="delete" data-confirm-delete>삭제</button>
  </c:if>
</div>

<table class="table table-bordered lbw150">
  <tr>
    <td class="lb">후원인번호</td>
    <td><input  type="text" name="sponsorNo" readonly value="${ sponsor.sponsorNo }" /></td>
    <td class="lb">우편물 발송여부</td>
    <td><label class="clean"><input type="radio" value="true" name="mailReceiving" ${ sponsor.mailReceiving ? "checked" :"" } />발송동의</label> 
        <label class="clean"><input type="radio" value="false" name="mailReceiving" ${ !sponsor.mailReceiving ? "checked" :"" } />발송미동의</label> </td>
  </tr>
  <tr>
    <td class="lb">이름</td>
    <td><form:input  path="name" placeholder="이름을 입력해주세요" />
      <span class="sponsorError"><form:errors path="name" /></span></td>
    <td class="lb">우편물 발송지</td>
    <td><label class="clean"><input type="radio" value="0" name="mailTo" ${ sponsor.mailTo==0 ? "checked" :"" } />자택</label> 
        <label class="clean"><input type="radio" value="1" name="mailTo" ${ sponsor.mailTo==1 ? "checked" :"" } />직장</label></td>
  </tr>
  <tr>
    <td class="lb">주민번호</td>
    <td><input  type="text" name="juminNo" placeholder="-를 제외하고 입력해주세요." value="${ sponsor.juminNo }" /> 
      <span class="sponsorError"><form:errors path="juminNo" /></span></td>
    <td class="lb" rowspan="2">자택주소</td>
    <td rowspan="2">
      <input class="address" type="text" name="homePostCode" id="homePostCode" placeholder="우편번호" value="${ sponsor.homePostCode }" /> 
      <input type="button" onclick="postCodeSearch('homePostCode', 'homeRoadAddress')" value="우편번호 찾기" class="btn btn-sm" />
      <div>
        <input class=" address" type="text" name="homeRoadAddress" id="homeRoadAddress" placeholder="도로명주소" value="${sponsor.homeRoadAddress }" />
      </div>
      <div>
        <input class=" address" type="text" name="homeDetailAddress" id="homeDetailAddress" placeholder="상세주소" value="${ sponsor.homeDetailAddress }" /> 
        <span class="sponsorError"><form:errors path="homeDetailAddress" /></span>
      </div></td>
  </tr>
  <tr>
    <td class="lb">후원인구분1</td>
    <td><form:select path="sponsorType1Id">
        <form:options itemValue="id" itemLabel="codeName" items="${ sponsorType1List }" />
      </form:select></td>
  </tr>
  <tr>
    <td class="lb">후원인구분2</td>
    <td><form:select path="sponsorType2Id">
        <form:options itemValue="id" itemLabel="codeName" items="${ sponsorType2List }" />
      </form:select></td>
    <td class="lb">자택 전화번호</td>
    <td><input  type="text" name="homePhone" placeholder="02-0000-0000" value="${ sponsor.homePhone}" /></td>
  </tr>
  <tr>
    <td class="lb">가입일</td>
    <td><input class=" date" type="text" name="signUpDate" value="${ sponsor.signUpDate }" /></td>
    <td class="lb">핸드폰 번호</td>
    <td><input  type="text" name="mobilePhone" placeholder="010-0000-0000" value="${ sponsor.mobilePhone }" /></td>
  </tr>
  <tr>
    <td class="lb">추천인</td>
    <td><input  type="text" name="recommender" placeholder="추천인 이름을 적어주세요." value="${ sponsor.recommender }" /> 
      <span class="sponsorError"><form:errors path="recommender" /></span></td>
    <td class="lb">이메일</td>
    <td><input  type="email" name="email" placeholder="abcd@skhu.kr" value="${ sponsor.email}" /> 
      <span class="sponsorError"><form:errors path="email" /></span></td>
  </tr>
  <tr>
    <td class="lb">추천인관계</td>
    <td><form:select path="recommenderRelation">
        <form:option value="없음" />
        <form:option value="가족" />
        <form:option value="지인" />
      </form:select></td>
    <td rowspan="2"class="lb">비고</td>
    <td rowspan="2"><textarea  name="etc">${ sponsor.etc}</textarea></td>
  </tr>
  <tr>
    <td class="lb">소속교회</td>
    <td>
        <span id="churchName">${ sponsor.church }</span> 
        <form:hidden path="churchId" value="${ sponsor.churchId } " />
        <a href="#churchDialog" class="btn btn-sm btn-gray" data-toggle="modal">검색</a>
    </td>
  </tr>
</table>

<div class="row">
  <div class="col-lg-7">
    <h4>근무처정보</h4>
    <table class="table table-bordered lbw150">
      <tr>
        <td class="lb">직장</td>
        <td><input type="text" name="company" value="${ sponsor.company}" class="w300" />
          <span class="sponsorError"><form:errors path="company" /></span></td>
      </tr>
      <tr>
        <td class="lb">부서</td>
        <td><input  type="text" name="department" value="${ sponsor.department}" />
          <span class="sponsorError"><form:errors path="department" /></span></td>
      </tr>
      <tr>
        <td class="lb">직위</td>
        <td><input  type="text" name="position" value="${ sponsor.position}" />
          <span class="sponsorError"><form:errors path="position" /></span></td>
      </tr>
      <tr>
        <td class="lb">직장전화번호</td>
        <td><input  type="text" name="officePhone" value="${ sponsor.officePhone}" />
          <span class="sponsorError"><form:errors path="officePhone" /></span></td>
      </tr>
      <tr>
        <td class="lb">직장주소</td>
        <td>
          <input class=" address" type="text" name="officePostCode" id="officePostCode" placeholder="우편번호" value="${ sponsor.officePostCode}" /> 
          <input type="button" onclick="postCodeSearch('officePostCode', 'officeRoadAddress')" value="우편번호 찾기" class="btn btn-sm"/>
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
                <a class="btn btn-small" href="download.do?id=${file.id}"><i class="icon icon-file"></i> ${ file.fileName } / ${file.id} </a> 
                <a class="glyphicon glyphicon-remove" href="fileDelete.do?id=${file.id}&sid=${sponsor.id}" data-confirm-delete></a></td>
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

<%@include file="_churchDialog.jsp" %> 
