<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
  .panel h3 { margin-top: 10px; margin-bottom: 5px; }
</style>
    
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/funds/res/js/daum_postcode.js"></script>

<div class="navigation-info">
  &gt; 시스템 관리 &gt; <a href="list.do">사용자 목록</a> &gt; 사용자 정보
</div>
 
<form:form method="post" modelAttribute="user">

<div class="panel panel-default shadow w900">
  <div class="panel-heading">
    <h3>사용자 정보</h3>
  </div>
  <div class="panel-body">    
    <table class="table table-bordered table-condensed lbw150">
      <tr>
        <td class="lb">로그인</td>
        <td><form:input path="loginName" />
        <td class="lb">이름</td>
        <td><form:input path="name" />
      </tr>
      <tr>
        <td class="lb">이메일</td>
        <td><form:input path="email" />
        <td class="lb">사용자유형</td>
        <td>
            <form:select path="userType">
              <form:option value="직원" />
              <form:option value="관리자" />
            </form:select>
        </td>
      </tr>
      <tr>
        <td class="lb">활성화</td>
        <td><form:checkbox path="enabled" /></td>
        <td  class="lb"></td>
        <td></td>    
      </tr>
    </table>

    <div>
      <button class="btn btn-primary" type="submit" name="cmd" value="saveInfo">사용자 정보 저장</button>
      <button class="btn btn-danger" type="submit" name="cmd" value="delete" data-confirm-delete>사용자 삭제</button>
      <a class="btn btn-gray" href="list.do">목록으로 나가기</a>
    </div>    
  </div>
</div>  
  
<hr />

<div class="panel panel-default shadow w900">
  <div class="panel-heading">
    <h3>비밀번호 변경</h3>
  </div>
  <div class="panel-body">    
    <table class="table table-bordered lbw150">
      <tr>
        <td class="lb">비밀번호</td>
        <td><form:password path="password1" />
        <td class="lb">비밀번호확인</td>
        <td><form:password path="password2" />
      </tr>
      <tr>
        <td colspan=4" style="background-color: #eee;">
          비밀번호는 7자 이상이어야 하고, 숫자, 영어소문자, 영어대문자 중 3가지 이상을 조합해야 합니다.</td>
      </tr>
    </table>
    <div class="">
      <button class="btn btn-primary" type="submit" name="cmd" value="savePassword">비밀번호 저장</button>
    </div>
  </div>
</div>  

<hr />

<div class="panel panel-default shadow w500">
  <div class="panel-heading">
    <h3>메뉴 권한</h3>
  </div>
  <div class="panel-body">    
    <table class="table table-bordered table-condensed">
      <thead>
        <tr><th>메뉴</th><th>권한</th></tr>
      </thead>
      <tbody>
        <c:forEach var="item" items="${ menu }" >
          <tr>
            <td>${ item.title }</td>
            <td><input type="checkbox" name="menuId" ${ item.enabled ? "checked" : "" } value="${ item.menuId }" /></td>
          </tr>
        </c:forEach>
      </tbody>
    </table> 
    <div class="">
      <button class="btn btn-primary" type="submit" name="cmd" value="saveMenu">권한 저장</button>
    </div>
  </div>
</div>

</form:form>