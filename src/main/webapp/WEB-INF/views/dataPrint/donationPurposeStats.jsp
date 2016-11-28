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
					<h1 class="page-header">기부목적별 납입현황</h1>
  </div>
   <form method="post">
      <div class="row">
         <div class="col-lg-9">
            <label> 기간(시작-끝) </label> <input id="datepicker1" class="commoninput" name="startDate" value="${startDate}">
            ~ <input id="datepicker2" class="commoninput" name="endDate" value="${endDate}">
         </div>
         <div class="col-lg-3">
            <div id="column-right">
               <button type="submit" class="btn btn-primary" onclick="searched()" >검색</button>
               <button class="btn btn-default" type="submit" name="cmd" value="pdf">보고서</button>
               <button class="btn btn-default" type="submit" name="cmd" value="xlsx">엑셀</button>
            </div>


         </div>
      </div>
      <h4>기부목적별 납입보고서</h4>



		<table class="table table-bordered table-hover">
			<thead>
				<th>총 후원인수</th>
				<th>총 납입수</th>
				<th>총 납입액</th>
			</thead>
			<tbody>
				<tr id="topTable">
					<td>${totalSponsor}</td>
					<td>${totalDonationPurpose}</td>
					<td class="money">${totalSum}</td>
				</tr>
			</tbody>
		</table>


		<table class="table table-bordered table-hover">

		<table class="table table-bordered">

			<thead>
				<th>기부목적</th>
				<th>기부후원인수</th>
				<th>납입수</th>
				<th>금액</th>
				<th>비율</th>
			</thead>

         <c:forEach var="payment" items="${ list }">
            <tbody>
               <tr>
                  <td>${payment.donationPurpose}</td>
                  <td>${payment.count1}
                  <td>${payment.count2}
                  <td class="money">${payment.sum}
                  <td>${payment.percent}%</td>
               </tr>
         </c:forEach>
         <tr>
            <td>소계</td>
            <td>${totalSponsor}</td>
            <td>${totalDonationPurpose}</td>
            <td class="money">${totalSum}</td>
            <td>${totalPercent}%</td>
         </tr>
         </tbody>
      </table>

   </form>
</body>