<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
   prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<script>
$(function() {                          

	$("form").submit(function() {
		
		 var startDate=$('#datepicker1').val();
		 var endDate=$('#datepicker2').val();
	    	
		if(startDate==''||endDate==''){
			alert('날짜를 모두 입력해주세요');
			return false;
		}
		else
			return true;
	});
})

	
</script>
<style>
tr#topTable td,h4{
   text-align:center; }
a2{ float:right; }

</style>
</head>
<body>
  <div class="col-lg-12">
					<h1 class="page-header">월별 기부목적별 납입현황</h1>
  </div>
   <form method="post">
      <div class="row">
         <div class="col-lg-9">
            <label> 기간(시작-끝) </label> <input id="datepicker1"  name="startDate" value="${startDate}">
            ~ <input id="datepicker2"  name="endDate" value="${endDate}">
         </div>
         <div class="col-lg-3">
            <div id="column-right">
               <button type="submit" class="btn btn-primary" >검색</button>
               <button class="btn btn-default" type="submit" name="cmd" value="xlsx">엑셀</button>
            </div>

         </div>
      </div>
   <c:if test="${ not empty from }">
		<table class="table table-bordered">
			<thead>
				<th>기관</th>
				<th>기관종류</th>
				<th>기부목적</th>
				 <c:forEach var="i" begin="${from}" end="${to}" step="1">
      				<th><c:out value="${i}월" /></th>
      			 </c:forEach>
				
			</thead>

         <c:forEach var="payment" items="${ list }">
            <tbody>
               <tr>
                  <td>${payment.corporate}</td>
                  <td>${payment.organization}</td>
                  <td>${payment.donationPurpose}</td>
                   <c:forEach var="i" begin="${from}" end="${to}" step="1">
                  <c:if test="${ i == 1 }"><td class="money">${payment.m1}</td> </c:if>
                  <c:if test="${ i == 2 }"><td class="money">${payment.m2}</td> </c:if>
                  <c:if test="${ i == 3 }"><td class="money">${payment.m3}</td> </c:if>
                  <c:if test="${ i == 4 }"><td class="money">${payment.m4}</td> </c:if>
                  <c:if test="${ i == 5 }"><td class="money">${payment.m5}</td> </c:if>
                  <c:if test="${ i == 6 }"><td class="money">${payment.m6}</td> </c:if>
                  <c:if test="${ i == 7 }"><td class="money">${payment.m7}</td> </c:if>
                  <c:if test="${ i == 8 }"><td class="money">${payment.m8}</td> </c:if>
                  <c:if test="${ i == 9 }"><td class="money">${payment.m9}</td> </c:if>
                  <c:if test="${ i == 10 }"><td class="money">${payment.m10}</td> </c:if>
                  <c:if test="${ i == 11 }"><td class="money">${payment.m11}</td> </c:if>
                  <c:if test="${ i == 12 }"><td class="money">${payment.m12}</td> </c:if>
                  
                  </c:forEach>
                
                  
                  
               </tr>
         </c:forEach>
        
         </tbody>
      </table>
</c:if>
   </form>
</body>