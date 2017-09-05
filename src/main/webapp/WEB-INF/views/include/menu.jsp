<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="wrapper">
  <nav class="navbar navbar-default" role="navigation">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1">
          <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/funds/">후원관리시스템</a>
      </div>

      <sec:authorize access="authenticated">
        <div class="navbar-collapse collapse" id="navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">기초정보관리<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/funds/corporate/list.do">기관</a></li>
                <li><a href="/funds/donationPurpose/list.do">기부목적</a></li>
                <c:forEach var="codeGroup" items="${ codeGroupList }">
                  <li><a href="/funds/code/list.do?gid=${codeGroup.id}">${codeGroup.name}</a></li>
                </c:forEach>
              </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">후원인관리<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/funds/sponsor/list.do">후원인 목록</a></li>
                <li><a href="/funds/sponsor/dm.do">우편 발송</a></li>
              </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">금융연동<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/funds/cms/eb13.do">EB13 생성</a></li>
                <li><a href="/funds/cms/eb14.do">EB14 등록</a></li>
                <li><a href="/funds/cms/eb14result.do">EB13/14 결과조회</a></li>
                <li class="divider"></li>
                <li><a href="/funds/cms/eb21.do">EB21 생성</a></li>
                <li><a href="/funds/cms/eb22.do">EB22 등록</a></li>
                <li><a href="/funds/cms/eb22result.do">EB21/22 결과조회</a></li>
                <li class="divider"></li>
                <li><a href="/funds/cms/xfer.do">자동이체 결과등록</a></li>
                <li><a href="/funds/cms/sal.do">급여공제 결과등록</a></li>
              </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">납입조회<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/funds/payment/srch1a.do">납입 내역 조회</a></li>
                <li class="divider"></li>
                <li><a href="/funds/payment/srch1b.do">후원인별 납입 합계</a></li>
                <li><a href="/funds/payment/srch2/0.do">기부목적별 납입 합계</a></li>
                <li><a href="/funds/payment/srch2/1.do">회원구분별 납입 합계</a></li>
                <li><a href="/funds/payment/srch2/2.do">소속교회별 납입 합계</a></li>
              </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">영수증<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/funds/receipt/list.do">영수증 목록</a></li>
                <li><a href="/funds/receipt/create1.do">영수증 개별생성</a></li>
                <li><a href="/funds/receipt/create2.do">영수증 일괄생성</a></li>
                <li class="divider"></li>
                <li><a href="/funds/receipt/taxData.do">국세청 보고자료</a></li>
              </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">증서<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/funds/certificate/0/list.do">장학증서</a></li>
                <li><a href="/funds/certificate/1/list.do">기부증서</a></li>
              </ul>
            </li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">시스템관리<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <sec:authorize access="hasRole('ROLE_시스템관리자')">              
                  <li><a href="/funds/user/list.do">사용자목록</a></li>
                </sec:authorize>
                <li><a href="/funds/log/list.do">로그 기록</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="/funds/user/myinfo.do"><sec:authentication property="user.name" /> 정보수정</a></li>
            <li><a href="/funds/home/logout.do">로그아웃</a></li>
          </ul>
        </div>
        <script>
        function resetTimeout() { 
            startTime = new Date(); 
          }
          function checkTimeout() { 
                var span = (new Date() - startTime) / 1000;
                if (span > 10) { //30 * 60) {
                  location.href = '/funds/home/logout.do';
                  alert('일정 시간동안 작업이 없어서 자동 로그아웃되었습니다.');
                }
              }
          
          $("body").click(resetTimeout);
          $("body").keydown(resetTimeout);
          resetTimeout();     
          setInterval(checkTimeout, 5000);          
        </script>
      </sec:authorize>

      <sec:authorize access="not authenticated">
        <div class="navbar-collapse collapse" id="navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="/funds/home/login.do">로그인</a></li>
          </ul>
        </div>
      </sec:authorize>

    </div>
  </nav>

</div>
