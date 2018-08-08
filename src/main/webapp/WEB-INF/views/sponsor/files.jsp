<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="navigation-info">
  &gt; 회원 관리 &gt; <a href="/funds/sponsor/list.do?${ pagination.queryString }">회원 목록</a>  
  &gt; 첨부파일
</div>

<div class="panel panel-default shadow">
  <div class="panel-heading">
    <%@include file="_title.jsp" %> 
  </div>
  <div class="panel-body">  
    <c:set var="tab5" value="active" />
    <%@include file="_tab2.jsp" %> 
     
    <table class="table table-bordered mt10 w700">
      <thead>
        <tr>
          <th>파일명</th>
          <th class="right">파일크기</th>
          <th>날짜</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="file" items="${ list }">
          <tr>
            <td>${ file.fileName }</td>
            <td class="right"><fmt:formatNumber value="${ file.fileSize }" groupingUsed="true"/></td>
            <td><fmt:formatDate value="${ file.createDate }" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>
                <a class="btn btn-success btn-xs" href="/funds/dataFile/download.do?id=${file.id}"> 다운로드</a> 
                <a class="btn btn-danger btn-xs" href="/funds/dataFile/delete.do?id=${file.id}&returnUrl=${url}" data-confirm-delete> 삭제</a>            
            </td>
          </tr>
        </c:forEach>
        <c:if test="${ list.size() == 0 }">
            <tr><td colspan="4">첨부파일이 없습니다.</td></tr>
        </c:if>        
      </tbody>
    </table>
    
    <form method="post" action="/funds/dataFile/upload.do" enctype="multipart/form-data">
      <input type="hidden" name="foreignType" value="sponsor" />
      <input type="hidden" name="foreignId" value="${ sponsor.id }" />
      <input type="hidden" name="returnUrl" value="${ url }" />
      <span>파일:</span> <input type="file" name="data" style="min-width: 400px; height: 2em;"/> <br />
      <button type="submit" class="btn btn-primary btn-sm"><i class="icon-ok icon-white"></i> 첨부파일 업로드</button>
    </form> 
    
  </div>
</div>    