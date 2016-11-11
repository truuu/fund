<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
   prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<script>
var startDate=$('#startDate').val();
var endDate=$('#endDate').val();

if(startDate==''||endDate==''){
	$(function() {
		$("form").submit(function() {
			
				alert('날짜를 모두 입력해주세요');
			
				
		});
		return false;
	});
	}	
	
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
            <label> 기간(시작-끝) </label> <input type="date" id="startDate" class="commoninput" name="startDate" value="${startDate}">
            ~ <input type="date" id="endDate" class="commoninput" name="endDate" value="${endDate}">
         </div>
         <div class="col-lg-3">
            <div id="column-right">
               <button type="submit" class="btn btn-primary" >검색</button>
               <button class="btn btn-default" type="submit" name="cmd" value="pdf">보고서</button>
               <button class="btn btn-default" type="submit" name="cmd" value="xlsx">엑셀</button>
            </div>

         </div>
      </div>
   
		<table class="table table-bordered">
			<thead>
				<th>기관</th>
				<th>기관종류</th>
				<th>기부목적</th>
				<th>1월</th>
				<th>2월</th>
				<th>3월</th>
				<th>4월</th>
				<th>5월</th>
				<th>6월</th>
				<th>7월</th>
				<th>8월</th>
				<th>9월</th>
				<th>10월</th>
				<th>11월</th>
				<th>12월</th>
			</thead>

         <c:forEach var="payment" items="${ list }">
            <tbody>
               <tr>
                  <td>${payment.corporate}</td>
                  <td>${payment.organization}</td>
                  <td>${payment.donationPurpose}</td>
                  <td class="money">${payment.m1}</td>
                  <td class="money">${payment.m2}</td>
                  <td class="money">${payment.m3}</td>
                  <td class="money">${payment.m4}</td>
                  <td class="money">${payment.m5}</td>
                  <td class="money">${payment.m6}</td>
                  <td class="money">${payment.m7}</td>
                  <td class="money">${payment.m8}</td>
                  <td class="money">${payment.m9}</td>
                  <td class="money">${payment.m10}</td>
                  <td class="money">${payment.m11}</td>
                  <td class="money">${payment.m12}</td>
                  
                  
               </tr>
         </c:forEach>
        
         </tbody>
      </table>

   </form>
</body>