<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">코드관리<span class="caret"></span></a>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">후원인관리<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		   
		    <li><a href="#">후원인목록</a></li>
		  
		    <li><a href="#">후원인등록</a></li>
		  
		    <li><a href="#">DM주소록 생성</a></li>
		  </ul>
		</li>
		
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">금융연동<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="/fund_sys/finance/eb13.do">EB13생성</a></li>
		    <li><a href="/fund_sys/finance/eb14.do">EB14등록</a></li>
		    <li><a href="/fund_sys/finance/eb21.do">EB21생성</a></li>
		    <li><a href="/fund_sys/finance/eb22.do">EB22등록</a></li>
		    <li><a href="/fund_sys/finance/automation.do">자동이체 결과등록</a></li>
		    <li><a href="/fund_sys/finance/salary.do">급여공제 결과등록</a></li>
		    <li><a href="#">EB13/14 결과조회</a></li>
		    <li><a href="#">EB21/22 결과조회</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">납입관리<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    

		    <li><a href="#">납입 내역 보기</a></li>
		    <li><a href="#">비정기 납입 등록</a></li>
		     <li class="divider"></li>
		       <li><a href="#">납입관련 각종통계</a></li>
		    <li><a href="#">미납 내역(추후)</a></li>

		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">증서관리<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="#">영수증발급대장</a></li>
		    <li><a href="#">영수증일괄생성</a></li>
		    <li><a href="#">영수증개별생성</a></li>
		    <li><a href="#">국세청보고자료</a></li>
		     <li class="divider"></li>
		    <li><a href="#">장학증서 발급대장</a></li>
		    <li><a href="#">기부증서 발급대장</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">자료출력<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="#">기부목적별 납입현황</a></li>
		    <li><a href="#">후원인별 납입현황</a></li>
		    <li><a href="#">소속교회별납입현황</a></li>
	
		  </ul>
		</li>
		<li class="dropdown">
		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">시스템관리<span class="caret"></span></a>
		  <ul class="dropdown-menu" role="menu">
		    <li><a href="#">시스템에러메세지</a></li>
		    <li><a href="#">사용자등록</a></li>
		    <li><a href="#">사용자목록</a></li>
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
