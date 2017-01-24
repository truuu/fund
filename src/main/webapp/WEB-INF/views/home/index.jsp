<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.login {
    width: 800px; height: 400px;
    border: 1px solid #ddd; background-color: #eee;
    margin-top: 100px; margin-left: 130px;
    text-align: center;   
}
.login img { margin: 40px; }
</style>

<h1>LOGIN</h1>

<div class="login">
  <form method="POST" action="/fund_sys/home/login_processing.do">
    <div>
        <img src="/fund_sys/res/images/skhu_logo.png" style="width: 200px;" />
    </div>

    <div class="controls">
      <input type="text" name="loginName" placeholder="아이디" /> 
      <input type="password" name="password" placeholder="비밀번호" />
      <button type="submit" class="btn btn-primary">로그인</button>
    </div>
  </form>
</div>

<c:if test="${ param.error != null }">
  <div class="alert alert-error">로그인 실패</div>
</c:if>


