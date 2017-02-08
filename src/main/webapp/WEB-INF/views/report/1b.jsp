<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h1>납입 합계 조회</h1>
<hr />

<form:form modelAttribute="wrapper">

  <div class="pull-right mb4">
    <button type="submit" class="btn btn-primary" name="cmd" value="search">검색</button>
    <button type="button" class="btn btn-default" onclick="cancelSearch()">검색취소</button>    
    <button type="submit" class="btn btn-gray" name="cmd" value="excel">액셀</button>
  </div>

  <div>
    <span>정렬순서:</span>
    <form:select path="map[orderBy]">
      <form:option value="" labe="" />
      <form:options itemValue="value" itemLabel="label" items="${ report1aOrderBy }" />
    </form:select>
  </div>

  <table class="table table-bordered lbw150">
    <tr>
      <td class="lb">이름</td>
      <td><form:input path="map[sponsorName]" />    
      <td class="lb">후원인번호</td>
      <td><form:input path="map[sponsorNo]" />    
    </tr>
    <tr>
      <td class="lb">후원인구분2</td>
      <td><form:select path="map[sponsorType2Id]">
          <form:option value="" label="" />
          <form:options itemValue="id" itemLabel="codeName" items="${ sponsorType2List }" />
          </form:select>
      </td>            
      <td class="lb">소속교회</td>
      <td>
        <form:input path="map[churchName]" readonly="true" /> 
        <form:hidden path="map[churchId]" /> 
        <a href="#churchDialog" class="btn btn-sm btn-gray" data-toggle="modal">검색</a>
      </td>
    </tr>
    <tr>      
      <td class="lb">기관</td>
      <td>
          <form:select path="map[corporateId]">
          <form:option value="" labe="" />
          <form:options itemValue="id" itemLabel="name" items="${ corporates }" />
          </form:select>
      </td>
      <td class="lb">정기/비정기</td>
      <td><form:select path="map[regular]">
          <form:option value="-1" label="" />
          <form:option value="1" label="정기" />
          <form:option value="0" label="비정기" />
        </form:select>
      </td>
    </tr>
    <tr>
      <td class="lb">납입일</td>
      <td><form:input path="map[startDate]" class="startDt" /> ~ <form:input path="map[endDate]" class="endDt" /></td>
      <td class="lb">납입방법</td>
      <td><form:select path="map[paymentMethodId]">
          <form:option value="" labe="" />
          <form:options itemValue="id" itemLabel="codeName" items="${ paymentMethods }" />
          </form:select>
      </td>
    </tr>      
    <tr>      
      <td class="lb">기부목적</td>
      <td colspan="3">
        <form:input path="map[donationPurposeName]" readonly="true" class="w600" />
        <form:hidden path="map[donationPurposeId]" />
        <a href="#donationPurposeDialog" class="btn btn-sm btn-gray" data-toggle="modal">검색</a>
      </td>
    </tr>
  </table>
</form:form>

<c:set var="sum" value="${ 0 }" />
<c:set var="count" value="${ 0 }" />
<table class="table table-bordered mt4">
  <thead>
    <tr>
      <th>후원인번호</th>
      <th>이름</th>
      <th>후원인구분2</th>
      <th>소속교회</th>
      <th class="right">납입액</th>
      <th class="right">납입건수</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="p" items="${list}">
      <tr>
        <td>${p.sponsorNo}</td>
        <td>${p.name}</td>
        <td>${p.sponsorType2Name}</td>
        <td>${p.churchName}</td>
        <td class="right"><fmt:formatNumber value="${p.amount}" /></td>
        <td class="right"><fmt:formatNumber value="${p.count}" /></td>
      </tr>
      <c:set var="sum" value="${ sum + p.amount }" />
      <c:set var="count" value="${ count + 1 }" />
    </c:forEach>
    <tr>
      <td colspan="4" class="right">합계</td>
      <td class="right"><fmt:formatNumber value="${ sum }" /></td>
      <td class="right"><fmt:formatNumber value="${ count }" /> 건</td>
    </tr>    
  </tbody>
</table>

<%@include file="../sponsor/_churchDialog.jsp"%>
<%@include file="../sponsor/_donationPurposeDialog.jsp"%>
