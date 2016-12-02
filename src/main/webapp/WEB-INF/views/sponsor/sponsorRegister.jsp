<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function sponorSearch(){
	var codeName=$("#search option:selected").text();
    var nameForSearch=$('#nameForSearch').val();
	
	
    if(codeName=='이름'){
		
		 $.ajax({
    	      url:"nameCheck.do",
    	      type:"GET",
    	      contentType: "application/json; charset=utf-8",
    	      data :{nameForSearch:nameForSearch},
    	      dataType : "json",
    	      success : function(data){
    	    	  var json = data;
  	             if(json==""){
  	                 alert('존재하지 않은 이름입니다');
  	             }else{
  	            	 location.href="search.do?codeName="+codeName+"&nameForSearch="+nameForSearch;
  	             }
  
    	      },
    	      error : function(request, status,error) {
    	          alert("통신오류")
    	          console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	      }
    	   });
		
	
	}else{
		
		if(codeName=='검색조건'){
			if (confirm("전체 목록 페이지로 넘가시겠습니까?") == true) {
				location.href="sponsor_m.do";
			}
		}
	
		if(codeName!='검색조건'){
		 $.ajax({
    	      url:"codeNameCheck.do",
    	      type:"GET",
    	      contentType: "application/json; charset=utf-8",
    	      data :{codeName:codeName},
    	      dataType : "json",
    	      success : function(data){
    	    	  var json = data;
    	    	  if(json=="")
  	            {
  	        	 alert('조건에 맞는 후원자가 존재하지 않습니다.')
  	        	}else{
  	        		alert('존재하는 조건입니다.')
  	        		location.href="search.do?codeName="+codeName;
  	        	}
  	       
    	      },
    	      error : function(request, status,error) {
    	         alert("통신실패")
    	          console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	      }
    	   });
		
		}

		
	}
	
}

$(function() {
   
    $("tbody tr").click(function() {
    	var check=1;
        location.href = "detail.do?id=" + $(this).attr("data-id");
    })
 
})




</script>
<style>
tr#topTable td{
	text-align:center;
}
</style>

<div id="wrapper">
	<div id="page-wrapper">
		<div class="container-fluid">
			<h1 class="page-header">회원관리</h1>
			<div id="column-right">
				<a href="sponsor.do" class="btn btn-info">신규</a> 
			</div>

			<div id="search">
				<div class="column-right">
					<select name="search"  id="search" class="commoninput">
						<option selected="selected" value="검색조건">검색조건</option>
						<option value="0">이름</option>
						<c:forEach var="code" items="${sponsorType}">
                              <option value="${code.ID}">${code.codeName}</option>
                         </c:forEach>
						
					</select> <input type="text" class="commoninput" id="nameForSearch" name="nameForSearch">
					<button type="submit" class="btn btn-primary" onclick="sponorSearch()">검색</button>
				</div>
			</div>

<form>
			<div id="column-right">
				<button class="btn btn-info" type="submit" name="cmd" value="xlsx">엑셀파일</button>
			</div>
    <input type="hidden" name="pg" value="1" />
    <input type="hidden" name="codeName" value="${pagination.codeName }" />


			<table class="table table-bordered table-hover">
				<thead>
					<tr >
						<th>후원인번호</th>
						<th>이름</th>
						<th>후원인구분1</th>
						<th>후원인구분2</th>
						<th>소속교회</th>
						<th>가입일</th>
						<th>연락처</th>
						<th>이메일</th>
						
					</tr>
				</thead>
				<tbody>
			<c:forEach var="sponsor" items="${list}">
			<tr data-id="${sponsor.id}" id="topTable">
			<td>${sponsor.sponsorNo}</td>
			<td>${sponsor.name}</td>
			<td>${sponsor.sponsorType1}</td>
			<td>${sponsor.sponsorType2}</td>
			<td>${sponsor.church}</td>
			<td>${sponsor.signUpDate}</td>
			<td>${sponsor.mobilePhone}</td>
			<td>${sponsor.email}</td>
			</tr>
			</c:forEach>
				</tbody>
			</table>
			<div align="center">
			    <div class="pagination">
					<ul class="pagination pagination-sm">
						<c:forEach var="page" items="${pagination.pageList }">
							<li class='${ page.cssClass }'><a data-page="${ page.number }">${ page.label }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
</form>



		</div>
	</div>
</div>


