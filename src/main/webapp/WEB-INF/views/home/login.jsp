<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sec:authorize access="not authenticated">

<style>
.login {
    width: 800px; height: 400px;
    border: 1px solid #ddd; background-color: #eee;
    margin-top: 100px; margin-left: 130px;
    text-align: center;   
}
.login img { margin: 40px; }
</style>

<h1>로그인 ${ test }</h1>

<div class="login">
  <form method="POST" action="/funds/home/login_processing.do">
    <div>
        <img src="/funds/res/images/skhu_logo.png" style="width: 200px;" />
    </div>

    <div class="controls">
      <input type="text" name="loginName" placeholder="아이디" /> 
      <input type="password" name="password" placeholder="비밀번호" />
      <button type="submit" class="btn btn-primary">로그인</button>
      
      <c:if test="${ param.locked != null }">
         <div class="mt20">비밀번호 5 회 오류로 계정이 30분동안 잠겼습니다.</div>
      </c:if>
      <c:if test="${ param.count != null }">
         <div class="mt20">비밀번호 ${ param.count } 회 오류.</div>
      </c:if>
      <c:if test="${ param.error != null }">
        <div class="mt20">로그인 실패.</div>
      </c:if>      
      <c:if test="${ param.out != null }">
        <div class="mt20">로그아웃 되었습니다.</div>
      </c:if>      
    </div>
  </form>
</div>

</sec:authorize>