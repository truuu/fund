<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/funds/res/js/daum_postcode.js"></script>

<form:form method="post" modelAttribute="user">

<h2>사용자 정보</h2>
<div class="pull-right mt4 mb4">
  <button class="btn btn-primary" type="submit" name="cmd" value="saveInfo">사용자정보 저장</button>
  <sec:authorize access="hasRole('ROLE_시스템관리자')">
    <a class="btn btn-gray" href="list.do">목록으로</a>
    <button class="btn btn-danger" type="submit" name="cmd" value="delete" data-confirm-delete>삭제</button>
  </sec:authorize>
</div>

<table class="table table-bordered lbw150">
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
    <td></td>
    <td></td>    
  </tr>
</table>
<hr />

<h2>비밀번호</h2>
<div class="pull-right mt4 mb4">
  <button class="btn btn-primary" type="submit" name="cmd" value="savePassword">비밀번호 저장</button>
</div>

<table class="table table-bordered lbw150">
  <tr>
    <td class="lb">비밀번호</td>
    <td><form:password path="password1" />
    <td class="lb">비밀번호확인</td>
    <td><form:password path="password2" />
  </tr>
  <tr>
    <td colspan=4">비밀번호는 7자 이상이어야 하고, 숫자, 영어소문자, 영어대문자 중 3가지 이상을 조합해야 합니다.</td>
  </tr>
</table>

</form:form>