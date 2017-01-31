<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(function() {
		$("#logout").click(function() {
			location.href = "../home/logout.do";
		});
	});
</script>
<div class="wrapper">
  <nav class="navbar navbar-default" role="navigation">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1">
          <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
        </button>

        <sec:authorize access="isAnonymous()">
          <a class="navbar-brand" href="/fund_sys">후원회</a>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
          <a class="navbar-brand" href="../sponsor/sponsor_m.do">후원회 </a>
        </sec:authorize>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="navbar-collapse collapse" id="navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">기초정보관리<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="/fund_sys/corporate/list.do">기관</a></li>
              <li><a href="/fund_sys/donationPurpose/list.do">기부목적</a></li>
              <c:forEach var="codeGroup" items="${ codeGroupList }">
                <li><a href="/fund_sys/code/list.do?gid=${codeGroup.id}">${codeGroup.name}</a></li>
              </c:forEach>
            </ul></li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">후원인관리<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="/fund_sys/sponsor/list.do">후원인 목록</a></li>
              <li><a href="/fund_sys/sponsor/sendDM.do">우편 발송</a></li>
            </ul></li>

          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">금융연동<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="/fund_sys/finance/eb13.do">EB13생성</a></li>
              <li><a href="/fund_sys/finance/uploadEB14.do">EB14등록</a></li>
              <li><a href="/fund_sys/finance/eb21.do">EB21생성</a></li>
              <li><a href="/fund_sys/finance/uploadEB22.do">EB22등록</a></li>
              <li><a href="/fund_sys/finance/uploadXferResult.do">자동이체 결과등록</a></li>
              <li><a href="/fund_sys/finance/uploadSalaryResult.do">급여공제 결과등록</a></li>
              <li><a href="/fund_sys/finance/resultEB1314.do">EB13/14 결과조회</a></li>
              <li><a href="/fund_sys/finance/resultEB2122.do">EB21/22 결과조회</a></li>
            </ul></li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">납입관리<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="/fund_sys/dataPrint/paymentRecordStats.do">납입 내역 조회</a></li>
              <li><a href="/fund_sys/dataPrint/paymentTotalStats.do">납입 총계 조회</a></li>
              <li><a href="/fund_sys/dataPrint/monthPerDonationPurposePayment.do">월별 기부목적별 납입현황</a></li>
            </ul></li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">증서관리<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="/fund_sys/certificate/receiptList.do">영수증발급대장</a></li>
              <li><a href="/fund_sys/certificate/receiptByDur.do">영수증일괄생성</a></li>
              <li><a href="/fund_sys/certificate/receiptByName.do">영수증개별생성</a></li>
              <li><a href="/fund_sys/certificate/taxData.do">국세청보고자료</a></li>
              <li class="divider"></li>
              <li><a href="/fund_sys/certificate/printScholarship_list.do">장학증서 발급대장</a></li>
              <li><a href="/fund_sys/certificate/printDonation_list.do">기부증서 발급대장</a></li>
            </ul></li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">자료출력<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="/fund_sys/dataPrint/donationPurposeStats.do">기부목적별 납입현황</a></li>
              <li><a href="/fund_sys/sponsor/cast.do">후원인별 납입현황</a></li>
              <li><a href="/fund_sys/user/church.do">소속교회별납입현황</a></li>

            </ul></li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">시스템관리<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="#">시스템에러메세지</a></li>
              <li><a href="/fund_sys/user/user_r.do">사용자등록</a></li>
              <li><a href="/fund_sys/user/user_m.do">사용자목록</a></li>
              <li class="divider"></li>
              <li><a href="/fund_sys/sponsor/encryptNo.do">후원인 암호화</a></li>
            </ul></li>

        </ul>

        <sec:authorize access="authenticated">
          <ul class="nav navbar-nav navbar-right">
            <li><sec:authentication property="user.name" /> 님 <a id="logout" style="display: inline-block;">로그아웃</a></li>
          </ul>
        </sec:authorize>

        <sec:authorize access="not authenticated">
          <ul class="nav navbar-nav navbar-right">
          </ul>
        </sec:authorize>

      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
  </nav>

</div>
