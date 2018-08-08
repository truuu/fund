<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/funds/res/js/daum_postcode.js"></script>

<style>
  input.address { min-width: 300px; margin-bottom: 2px; }
  textarea { width: 100%; height: 100px; }
</style>

<div class="navigation-info">
  &gt; 회원 관리 &gt; <a href="/funds/sponsor/list.do?${ pagination.queryString }">회원 목록</a> &gt; 회원정보 수정
</div>

<form:form method="post" modelAttribute="sponsor">

<div class="panel panel-default shadow">
  <div class="panel-heading">
    <%@include file="_title.jsp" %> 
  </div>
  <div class="panel-body">
  
    <c:set var="tab1" value="active" />
    <%@include file="_tab2.jsp" %> 
      
    <table class="table table-bordered lbw120 pd4 mt10">
      <tr>
        <td class="lb">후원인번호</td>
        <td> ${ sponsor.sponsorNo }</td>
        <td class="lb">소속교회</td>
        <td>
            <span id="churchName" style="display:inline-block; min-width: 100px;">${ sponsor.church }</span> 
            <form:hidden path="churchId" value="${ sponsor.churchId } " />
            <a href="#churchDialog" class="btn btn-flat btn-xs" data-toggle="modal" tabindex="2">교회 찾기</a>
        </td>
      </tr>
      <tr>
        <td class="lb">이름</td>
        <td><form:input  path="name" placeholder="이름을 입력해주세요" tabindex="1" class="w200" /></td>
        <td class="lb">후원인구분상세</td>
        <td><form:input  path="sponsorTypeDetail" tabindex="2"  class="w300" /></td>
      </tr>
      <tr>
        <td class="lb">주민번호</td>
        <td><input  type="text" name="juminNo" placeholder="-를 제외하고 입력해주세요." value="${ sponsor.juminNo }" tabindex="1" /></td>
        <td class="lb">우편물 발송여부</td>
        <td><label class="clean"><input type="radio" value="true" name="mailReceiving" ${ sponsor.mailReceiving ? "checked" :"" } tabindex="2" />발송동의</label> 
            <label class="clean"><input type="radio" value="false" name="mailReceiving" ${ !sponsor.mailReceiving ? "checked" :"" } tabindex="2" />발송미동의</label> </td>
      </tr>
      <tr>
        <td class="lb">가입구분</td>
        <td><form:select path="sponsorType1Id" tabindex="1" >
            <form:options itemValue="id" itemLabel="codeName" items="${ sponsorType1List }" />
          </form:select></td>
        <td class="lb">우편물 발송지</td>
        <td><label class="clean"><input type="radio" value="0" name="mailTo" ${ sponsor.mailTo==0 ? "checked" :"" } tabindex="2" />자택</label> 
            <label class="clean"><input type="radio" value="1" name="mailTo" ${ sponsor.mailTo==1 ? "checked" :"" } tabindex="2" />직장</label></td>
          
      </tr>
      <tr>
        <td class="lb">회원구분</td>
        <td><form:select path="sponsorType2Id" tabindex="1" >
            <form:options itemValue="id" itemLabel="codeName" items="${ sponsorType2List }" />
          </form:select></td>
        <td class="lb" rowspan="2">자택주소</td>
        <td rowspan="2">
          <input class="address" type="text" name="homePostCode" id="homePostCode" placeholder="우편번호" value="${ sponsor.homePostCode }" tabindex="2" /> 
          <input type="button" onclick="postCodeSearch('homePostCode', 'homeRoadAddress')" value="우편번호 찾기" class="btn btn-flat btn-xs" tabindex="2" /><br/>
          <input class=" address" type="text" name="homeRoadAddress" id="homeRoadAddress" placeholder="도로명주소" value="${sponsor.homeRoadAddress }" tabindex="2" /><br/>
          <input class=" address" type="text" name="homeDetailAddress" id="homeDetailAddress" placeholder="상세주소" value="${ sponsor.homeDetailAddress }" tabindex="2" />        
          </td>      
      </tr>
      <tr>
        <td class="lb">가입일</td>
        <td><input class=" date" type="text" name="signUpDate" value="${ sponsor.signUpDate }" tabindex="1" /></td>
      </tr>
      <tr>
        <td class="lb">추천인</td>
        <td><input  type="text" name="recommender" placeholder="추천인 이름을 적어주세요." value="${ sponsor.recommender }" tabindex="1" class="w200" /> 
          <span class="sponsorError"><form:errors path="recommender" /></span></td>
        <td class="lb">직장</td>
        <td><input type="text" name="company" value="${ sponsor.company}" class="w300" tabindex="2" /></td>
      </tr>
      <tr>
        <td class="lb">추천인관계</td>
        <td><form:select path="recommenderRelation" tabindex="1">
            <form:option value="없음" />
            <form:option value="가족" />
            <form:option value="지인" />
          </form:select></td>
          <td class="lb">부서</td>
          <td><input  type="text" name="department" value="${ sponsor.department}" tabindex="2" /></td>
      </tr>
      <tr>
        <td class="lb">자택 전화번호</td>
        <td><input  type="text" name="homePhone" placeholder="02-0000-0000" value="${ sponsor.homePhone}" tabindex="1" /></td>
        <td class="lb">직위</td>
        <td><input  type="text" name="position" value="${ sponsor.position}" tabindex="2" /></td>
      </tr>
      <tr>
        <td class="lb">핸드폰 번호</td>
        <td><input  type="text" name="mobilePhone" placeholder="010-0000-0000" value="${ sponsor.mobilePhone }" tabindex="1" /></td>
        <td class="lb" rowspan="2">직장주소</td>
        <td rowspan="2">
          <input class=" address" type="text" name="officePostCode" id="officePostCode" placeholder="우편번호" value="${ sponsor.officePostCode}"  tabindex="2" /> 
          <input type="button" onclick="postCodeSearch('officePostCode', 'officeRoadAddress')" value="우편번호 찾기" class="btn btn-flat btn-xs"  tabindex="2" /> <br/>
          <input class=" address" type="text" name="officeRoadAddress" id="officeRoadAddress" placeholder="도로명주소" value="${ sponsor.officeRoadAddress}"  tabindex="2" /> <br/>
          <input class=" address" type="text" name="officeDetailAddress" id="officeDetailAddress" placeholder="상세주소" value="${ sponsor.officeDetailAddress}"  tabindex="2" /></td>
      </tr>
      <tr>
        <td class="lb">직장전화번호</td>
        <td><input  type="text" name="officePhone" value="${ sponsor.officePhone}" tabindex="1" /></td>
      </tr>
      <tr>
        <td class="lb">이메일</td>
        <td><input  type="text" name="email" placeholder="abcd@skhu.kr" value="${ sponsor.email}" tabindex="1" class="w200" /> </td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td class="lb">비고</td>
        <td colspan="3"><textarea  name="etc" tabindex="3">${ sponsor.etc}</textarea></td>
      </tr>
      <c:if test="${ sponsor.id > 0 && files.size() > 0 }">  
        <tr>
          <td class="lb">첨부파일</td>
          <td colspan="3">
            <c:forEach var="file" items="${ files }">
              <div style="padding: 5px;">
                <span>${ file.fileName }</span> 
                <a class="btn btn-gray btn-xs" href="fileDown.do?id=${file.id}"> 다운로드</a> 
                <a class="btn btn-gray btn-xs" href="fileDel.do?id=${file.id}&sid=${sponsor.id}&${pagination.queryString}" data-confirm-delete> 삭제</a>
              </div>
            </c:forEach>
          </td>
        </tr>
      </c:if>    
    </table>

    <div class="">
      <button class="btn btn-primary btn-sm" type="submit" name="cmd" value="save">회원정보 저장</button>
      <c:if test="${ sponsor.id > 0 }">
        <button class="btn btn-danger btn-sm" type="submit" name="cmd" value="delete" data-confirm-delete>회원 삭제</button>
      </c:if>
      <a href="/funds/sponsor/list.do?${ pagination.queryString }" class="btn btn-gray btn-sm">회원 목록으로</a>
    </div>
  </div>
</div>      

</form:form>

<span id="guide" style="color: #999"></span> <!-- daum_postcode.js 에서 사용함 -->
<%@include file="_churchDialog.jsp" %> 
