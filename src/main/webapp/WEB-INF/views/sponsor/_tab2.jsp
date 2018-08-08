<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul class="nav nav-tabs">
  <li class="${ tab1 }"><a href="/funds/sponsor/edit.do?id=${sponsor.id}&${pagination.queryString}">회원정보</a></li>
  <c:if test="${ sponsor.id != 0 }">
    <li class="${ tab2 }"><a href="/funds/sponsor/commitment/list.do?sid=${sponsor.id}&${pagination.queryString}">약정관리</a></li>
    <li class="${ tab3 }"><a href="/funds/sponsor/payment/list1.do?sid=${sponsor.id}&${pagination.queryString}">정기납입관리</a></li>
    <li class="${ tab4 }"><a href="/funds/sponsor/payment/list2.do?sid=${sponsor.id}&${pagination.queryString}">비정기납입관리</a></li>
    <li class="${ tab5 }"><a href="/funds/sponsor/files.do?id=${sponsor.id}&${pagination.queryString}">첨부파일(${fileCount})</a></li>
  </c:if>
</ul>