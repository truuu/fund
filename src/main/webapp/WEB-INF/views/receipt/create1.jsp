<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>기부금 영수증 개별발급</h1>
<hr />

${ param.map.createDate } / ${ param.map[createDate] } / ${ param } <br />
${ param.map.a } / ${ param.map[a] } / ${ param.getMap() } <br />
${ map.a } / ${ map } <br />

<form:form method="post" modelAttribute="param">

<span>납입기간:</span>
<form:input path="map[startDate]" class="startDt" placeholder="필수" /> ~ 
<form:input path="map[endDate]" class="endDt" placeholder="필수" />
<span>후원인명:</span>
<form:input path="map[srchText]" placeholder="필수" />
<span>기부기관:</span>
<form:select path="map[corporateId]" itemValue="id" itemLabel="name" items="${ corporates }" />
<button type="submit" class="btn btn-primary" name="cmd" value="search">납입내역 검색</button>

<table class="table table-bordered mt4">
  <thead>
    <tr>
      <th>선택</th>
      <th>영수증번호</th>
      <th>후원인번호</th>
      <th>후원인명</th>
      <th>정기/비정기</th>
      <th>납입일</th>
      <th class="right">금액</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="p" items="${ list }">
      <tr>
        <td><c:if test="${ p.receiptId == null }">
              <input type="checkbox" name="pid" value="${ p.id }" onchange="toggleCreateButton()" />
            </c:if>
        </td>
        <td>${ p.receiptNo }</td>          
        <td>${ p.sponsorNo }</td>
        <td>${ p.name }</td>
        <td>${ p.commitmentId != 0 ? '정기' : '비정기' }</td>
        <td>${ p.paymentDate }</td>
        <td class="right"><fmt:formatNumber value="${ p.amount }" /></td>
      </tr>
    </c:forEach>
  </tbody>
  <c:if test="${ list.size() <= 0 }">
    <tr><td colspan="8">조회 결과가 없습니다.</td></tr>
  </c:if>
</table>


<c:if test="${ list.size() > 0 }">
  <div class="createReceipt">
    <span id="sum">금액 합계: 0</span>
    <span class="block ml30">영수증 발급일:</span>
    <input name="createDate" class="date" value="${ param.map.createDate }">
    <button type="submit" class="btn btn-info" name="cmd" value="createReceipt">선택항목 영수증 발급</button>
  </div>
</c:if>

</form:form>

<script>
function toggleCreateButton() {
  if ($("input[name=pid]:checked").size() > 0) {
	  $("div.createReceipt").show();
	  var sum = 0;
	  $("input[name=pid]:checked").each(function(i, c) {
		  sum += (1 * $(c).parents("tr").find("td.right").text().replace(",", ""));
	  });
	  $("span#sum").text("금액 합계: " + sum);
  }
  else $("div.createReceipt").hide();
}
$("div.createReceipt").hide();
</script>
