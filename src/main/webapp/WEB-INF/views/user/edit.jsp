<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<style>
  .panel h3 { margin-top: 10px; margin-bottom: 5px; }
</style>
    
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/funds/res/js/daum_postcode.js"></script>

<form:form method="post" modelAttribute="user">

<div class="panel panel-default" style="width:900px;">
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
          <sec:authorize access="hasRole('ROLE_시스템관리자')">
            <form:select path="userType">
            <form:option value="직원" />
            <form:option value="관리자" />
            <form:option value="시스템관리자" />
            </form:select>
          </sec:authorize>        
          <sec:authorize access="!hasRole('ROLE_시스템관리자')">
            ${ user.userType }
          </sec:authorize>        
        </td>
      </tr>
      <tr>
        <td class="lb">활성화</td>
        <td><form:checkbox path="enabled" /></td>
        <td  class="lb"></td>
        <td></td>    
      </tr>
    </table>

    <button class="btn btn-primary" type="submit" name="cmd" value="saveInfo">사용자정보 저장</button>
    <sec:authorize access="hasRole('ROLE_시스템관리자')">
      <a class="btn btn-gray" href="list.do">목록으로</a>
      <button class="btn btn-danger" type="submit" name="cmd" value="delete" data-confirm-delete>삭제</button>
    </sec:authorize>    
  </div>
</div>  
  
<hr />

<div class="pull-right mt4 mb4">
  <button class="btn btn-primary" type="submit" name="cmd" value="savePassword">비밀번호 저장</button>
</div>
<h2>비밀번호</h2>

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
<hr />

<div class="w500">
  <div class="pull-right mt4 mb4">
    <button class="btn btn-primary" type="submit" name="cmd" value="saveMenu">권한 저장</button>
  </div>
  <h2>메뉴 권한</h2>
  
  
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
</div>

</form:form>