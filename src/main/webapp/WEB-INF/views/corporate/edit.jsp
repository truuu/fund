<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/fund_sys/res/js/daum_postcode.js"></script>

<h1>기관 ${ corporate.id > 0 ? "수정" : "등록" }</h1>
<hr />

<form:form method="post" modelAttribute="corporate">

<div class="pull-right mt4 mb4">
  <button type="submit" class="btn btn-primary" name="cmd" value="save">저장</button>
  <c:if test="${ corporate.id > 0 }">
    <button type="submit" class="btn btn-danger" name="cmd" value="delete" data-confirm-delete>삭제</button>
  </c:if>    
  <a href="list.do" class="btn btn-info">목록으로</a>
</div>

<table class="table table-bordered lbw150">
  <tr>
    <td class="lb">기관명</td>
    <td><form:input path="name" tabindex="1" /></td>
    <td class="lb">우편번호</td>
    <td>
        <form:input path="postCode" tabindex="4" />
        <input type="button" onclick="postCodeSearch('postCode', 'roadAddress')" value="우편번호 찾기" class="btn btn-sm" />
    </td>
  </tr>
  <tr>
    <td class="lb">사업자등록번호</td>
    <td><form:input path="corporateNo" tabindex="2" /></td>
    <td class="lb">도로명주소</td>
    <td><form:input path="roadAddress" class="w300" tabindex="5" /></td>
  </tr>
  <tr>
    <td class="lb">대표자명</td>
    <td><form:input path="representative" tabindex="3" /></td>
    <td class="lb">상세주소</td>
    <td><form:input path="detailAddress" class="w300" tabindex="6" /></td>
  </tr>
</table>  

</form:form>

