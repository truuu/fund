<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
   prefix="sec"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javaScript">

$(function() {
	$('#searchChurch').click(function(){
		
		var startDate=$( "input[name$='startDate']" ).val();
		var endDate=$( "input[name$='endDate']" ).val();
		if(startDate==''||endDate==''){
			alert('날짜를 모두 입력해주세요')
		}
		if(startDate!=''&&endDate!=''){
		location.href="churchSearch.do?startDate="+startDate+"&endDate="+endDate;
		}
	  });
	$('#xlsx').click(function(){
		startDate=$('#startDate').val();
		endDate=$('#endDate').val();
		
		location.href="churchSearch.do?cmd=xlsx&startDate="+startDate+"&endDate="+endDate;
	})
	
});



</script>

<div id="wrapper">

   <div id="page-wrapper">

      <div class="container-fluid">

         <!-- Page Heading -->
         <div class="row">
            <div class="col-lg-12">
               <h1 class="page-header">
                  소속 교회별 납부 현황
               </h1>
            </div>
         </div>
         <!-- /.row -->

         <div>
            <label> 신청기간 </label> <input type="date" class="commoninput" name="startDate"> ~ <input type="date" class="commoninput" name="endDate">
             <button id="searchChurch" class="btn btn-primary">검색</button>
         </div>

         
         <div class="row">
            <div class="col-lg-12">
            <hr>
            <center><label> 신청기간 </label> <input type="text" class="commoninput" value="${ pagination.startDate}"> ~ <input type="text" class="commoninput" value="${ pagination.startDate}"></center>
            <hr>
               <div class="table-responsive">
					<form method="get">

                      <input type="hidden" name="pg" value="1" />
                       <input type="hidden"  name="startDate" value="${ pagination.startDate}" />
                        <input type="hidden" name="endDate" value="${ pagination.endDate}" />
                       
						<div id="column-right">
						 <c:if test="${total!=null }">
            					<button class="btn" type="submit" name="cmd" value="xlsx">엑셀파일</button>
            			 </c:if>   
           				</div>        
           				           
                  <table class="table table-bordered table-hover" id="table_s">
                     <thead>
                        <tr>
                           
                           <th>소속교회</th>
                           <th>납입액</th>
                           
                           
                        </tr>
                     </thead>
                     <tbody>
          <c:forEach var="sponsor" items="${ list }">
                <tr>
                    <td>${sponsor.church}</td>
                    <td>${sponsor.sum} 원</td>
                   
                </tr>
            </c:forEach>

           <c:if test="${sponsor.sum!=0}">
                <tr>
                 <td><b>총합</b> </td>
                <td><b>${total} 원</b></td>
                </tr>
            </c:if>
                     </tbody>
                  </table>
                 
                  
                         </form>

               </div>

            </div>

         </div>




      </div>
      <!-- /.container-fluid -->
   </div>
   <!-- /#page-wrapper -->

</div>