<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/funds/res/js/daum_postcode.js"></script>

<style>
  input.address { min-width: 300px; margin-bottom: 2px; }
  textarea { width: 100%; height: 100px; }
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
    <td><input  type="text" name="sponsorNo" readonly value="${ sponsor.sponsorNo }" tabindex="1" /></td>
    <td class="lb">우편물 발송여부</td>
    <td><label class="clean"><input type="radio" value="true" name="mailReceiving" ${ sponsor.mailReceiving ? "checked" :"" } tabindex="2" />발송동의</label> 
        <label class="clean"><input type="radio" value="false" name="mailReceiving" ${ !sponsor.mailReceiving ? "checked" :"" } tabindex="2" />발송미동의</label> </td>
  </tr>
  <tr>
    <td class="lb">이름</td>
    <td><form:input  path="name" placeholder="이름을 입력해주세요" tabindex="1" /></td>
    <td class="lb">우편물 발송지</td>
    <td><label class="clean"><input type="radio" value="0" name="mailTo" ${ sponsor.mailTo==0 ? "checked" :"" } tabindex="2" />자택</label> 
        <label class="clean"><input type="radio" value="1" name="mailTo" ${ sponsor.mailTo==1 ? "checked" :"" } tabindex="2" />직장</label></td>
  </tr>
  <tr>
    <td class="lb">주민번호</td>
    <td><input  type="text" name="juminNo" placeholder="-를 제외하고 입력해주세요." value="${ sponsor.juminNo }" tabindex="1" /></td>
    <td class="lb" rowspan="2">자택주소</td>
    <td rowspan="2">
      <input class="address" type="text" name="homePostCode" id="homePostCode" placeholder="우편번호" value="${ sponsor.homePostCode }" tabindex="2" /> 
      <input type="button" onclick="postCodeSearch('homePostCode', 'homeRoadAddress')" value="우편번호 찾기" class="btn btn-sm" tabindex="2" /><br/>
      <input class=" address" type="text" name="homeRoadAddress" id="homeRoadAddress" placeholder="도로명주소" value="${sponsor.homeRoadAddress }" tabindex="2" /><br/>
      <input class=" address" type="text" name="homeDetailAddress" id="homeDetailAddress" placeholder="상세주소" value="${ sponsor.homeDetailAddress }" tabindex="2" />        
      </td>
  </tr>
  <tr>
    <td class="lb">후원인구분1</td>
    <td><form:select path="sponsorType1Id" tabindex="1" >
        <form:options itemValue="id" itemLabel="codeName" items="${ sponsorType1List }" />
      </form:select></td>
  </tr>
  <tr>
    <td class="lb">후원인구분2</td>
    <td><form:select path="sponsorType2Id" tabindex="1" >
        <form:options itemValue="id" itemLabel="codeName" items="${ sponsorType2List }" />
      </form:select></td>
    <td class="lb">후원인구분상세</td>
    <td><form:input  path="sponsorTypeDetail" tabindex="2"  class="w300" /></td>
  </tr>
  <tr>
    <td class="lb">가입일</td>
    <td><input class=" date" type="text" name="signUpDate" value="${ sponsor.signUpDate }" tabindex="1" /></td>
    <td class="lb">자택 전화번호</td>
    <td><input  type="text" name="homePhone" placeholder="02-0000-0000" value="${ sponsor.homePhone}" tabindex="2" /></td>
  </tr>
  <tr>
    <td class="lb">추천인</td>
    <td><input  type="text" name="recommender" placeholder="추천인 이름을 적어주세요." value="${ sponsor.recommender }" tabindex="1" /> 
      <span class="sponsorError"><form:errors path="recommender" /></span></td>
    <td class="lb">핸드폰 번호</td>
    <td><input  type="text" name="mobilePhone" placeholder="010-0000-0000" value="${ sponsor.mobilePhone }" tabindex="2" /></td>
  </tr>
  <tr>
    <td class="lb">추천인관계</td>
    <td><form:select path="recommenderRelation" tabindex="1">
        <form:option value="없음" />
        <form:option value="가족" />
        <form:option value="지인" />
      </form:select></td>
    <td class="lb">이메일</td>
    <td><input  type="email" name="email" placeholder="abcd@skhu.kr" value="${ sponsor.email}" tabindex="2" /> </td>
  </tr>
  <tr>
    <td class="lb">소속교회</td>
    <td>
        <span id="churchName">${ sponsor.church }</span> 
        <form:hidden path="churchId" value="${ sponsor.churchId } " />
        <a href="#churchDialog" class="btn btn-sm btn-gray" data-toggle="modal" tabindex="1">검색</a>
    </td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td class="lb">비고</td>
    <td colspan="3"><textarea  name="etc" tabindex="2">${ sponsor.etc}</textarea></td>
  </tr>
</table>

<div class="row">
  <div class="col-lg-7">
    <h4>근무처정보</h4>
    <table class="table table-bordered lbw150">
      <tr>
        <td class="lb">직장</td>
        <td><input type="text" name="company" value="${ sponsor.company}" class="w300" tabindex="3" /></td>
      </tr>
      <tr>
        <td class="lb">부서</td>
        <td><input  type="text" name="department" value="${ sponsor.department}" tabindex="3" /></td>
      </tr>
      <tr>
        <td class="lb">직위</td>
        <td><input  type="text" name="position" value="${ sponsor.position}" tabindex="3" /></td>
      </tr>
      <tr>
        <td class="lb">직장전화번호</td>
        <td><input  type="text" name="officePhone" value="${ sponsor.officePhone}" tabindex="3" /></td>
      </tr>
      <tr>
        <td class="lb">직장주소</td>
        <td>
          <input class=" address" type="text" name="officePostCode" id="officePostCode" placeholder="우편번호" value="${ sponsor.officePostCode}"  tabindex="3" /> 
          <input type="button" onclick="postCodeSearch('officePostCode', 'officeRoadAddress')" value="우편번호 찾기" class="btn btn-sm"  tabindex="3" /> <br/>
          <input class=" address" type="text" name="officeRoadAddress" id="officeRoadAddress" placeholder="도로명주소" value="${ sponsor.officeRoadAddress}"  tabindex="3" /> <br/>
          <input class=" address" type="text" name="officeDetailAddress" id="officeDetailAddress" placeholder="상세주소" value="${ sponsor.officeDetailAddress}"  tabindex="3" /></td>
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
