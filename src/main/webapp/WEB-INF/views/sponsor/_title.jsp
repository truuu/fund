<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${ sponsor.id > 0 }">
  <h3>${sponsor.name} <small>${sponsor.sponsorNo}</small></h3>
</c:if>
<c:if test="${ sponsor.id == 0 }">
  <h3>회원 신규 등록</h3>
</c:if>    
