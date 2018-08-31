<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="navigation-info">
  &gt; 회원관리 &gt; 예우 업로드
</div>

<div class="panel panel-default shadow">
  <div class="panel-heading">
    <h3>예우 업로드</h3> 
  </div>
  <div class="panel-body">

      <form method="post">
        <table id="eventUpload" class="table table-bordered">
          <thead>
            <tr>
              <th></th>
              <th>회원번호</th>
              <th>이름</th>
              <th>예우 내용</th>
              <th>날짜</th>
              <th>비고</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="e" items="${ list_notSaved }">
              <tr class="${ e.sponsorId > 0 ? '' : 'my-error' }">
                <td></td>
                <td>${ e.sponsorNo }</td>
                <td>${ e.sponsorName }</td>
                <td>${ e.description }</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${ e.eventDate }" /></td>
                <td>${ e.etc }</td>
              </tr>
            </c:forEach>    
            <c:forEach var="e" items="${ list_saved }">
              <tr class="my-success">
                <td>저장됨</td>
                <td>${ e.sponsorNo }</td>
                <td>${ e.sponsorName }</td>
                <td>${ e.description }</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${ e.eventDate }" /></td>
                <td>${ e.etc }</td>
              </tr>
            </c:forEach>    
          </tbody>
        </table>
        
        <div class="mt4">
          <button type="submit" class="btn btn-primary btn-sm" name="cmd" value="save">저장</button>
        </div>        
      </form>
      
    </div>
</div>

<script>
tableHVScroll2($("table#eventUpload"));
</script>
