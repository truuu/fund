<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<c:set var="q" value="${ pagination.queryString }" />
<div class="pull-right">
  <a class="btn btn-danger" href="delete.do?id=${log.id}&${q}" data-confirm-delete>삭제</a>
  <a class="btn btn-gray" href="list.do?${q}">목록으로</a>
</div>

<h1>로그</h1>
<hr />

<table class="table table-bordered lbw150">
  <tr>
    <td class="lb">카테고리</td>
    <td>${ log.category }</td>    
  </tr>
  <tr>
    <td class="lb">사용자</td>
    <td>${ log.currentUser }</td>    
  </tr>
  <tr>
    <td class="lb">IP</td>
    <td>${ log.ip }</td>    
  </tr>
  <tr>
    <td class="lb">시각</td>
    <td><fmt:formatDate value="${ log.writeTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>    
  </tr>
  <tr>
    <td class="lb">URL</td>
    <td>${ log.url }</td>    
  </tr>
  <tr>
    <td colspan="2"><pre style="white-space: pre-wrap;">${ log.body }</pre></td>
  </tr>
</table>