<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="R" />
<div class="wrapper">


	<nav class="navbar navbar-default" role="navigation">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1">
		<span class="sr-only">Toggle navigation</span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="/fund_sys">후원회</a>
	    </div>

	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="navbar-collapse collapse" id="navbar-collapse-1">
	      <ul class="nav navbar-nav">
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">회원관리<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="#">사용자 등록</a></li>
		    <li class="divider"></li>
		    <li><a href="${R}user/user_m.do">후원인 관리</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">자료조회<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		   
		    <li><a href="#">미납내역조회</a></li>
		    <li class="divider"></li>
		    <li><a href="#">CMS내역조회</a></li>
		    <li class="divider"></li>
		    <li><a href="#">국체청보고자료</a></li>
		  </ul>
		</li>
		
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">기초정보관리<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="#">Action</a></li>
		    <li><a href="#">Another action</a></li>
		    <li><a href="#">Something else here</a></li>
		    <li class="divider"></li>
		    <li><a href="#">Separated link</a></li>
		    <li class="divider"></li>
		    <li><a href="#">One more separated link</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">금융연동<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    
		    <li><a href="${R}finance/cms.do">CMS</a></li>
		    <li><a href="${R}finance/automation.do">자동이체</a></li>
		    <li><a href="${R}finance/salary.do">급여공제</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">증명서발급<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="#">기부금영수증</a></li>
		    <li class="divider"></li>
		    <li><a href="#">기부증서</a></li>
		    <li class="divider"></li>
		    <li><a href="#">장학증서</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">증명서관리<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="#">기부금영수증발급대장</a></li>
		    <li class="divider"></li>
		    <li><a href="#">기부금영수증삭제목록</a></li>
		    <li class="divider"></li>
		    <li><a href="#">기부증서발급대장</a></li>
		    <li class="divider"></li>
		    <li><a href="#">장학증서발급대장</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">후원자예우<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="#">Action</a></li>
		    <li><a href="#">Another action</a></li>
		    <li><a href="#">Something else here</a></li>
		    <li class="divider"></li>
		    <li><a href="#">Separated link</a></li>
		    <li class="divider"></li>
		    <li><a href="#">One more separated link</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">보고서<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		   <li><a href="#">납입총계조회</a></li>
		    <li class="divider"></li>
		    <li><a href="#">기간별보고서</a></li>
		  </ul>
		</li>
	      </ul>
	     
	      <ul class="nav navbar-nav navbar-right">
		<li><a href="#">Login</a></li>
		
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>

</div>
