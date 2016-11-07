<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">

$(function() {
    $('#loginName').blur(function() {
    	var loginName=$('#loginName').val();

    	  $.ajax({
    	      url:"repeatCheck.do",
    	      type:"GET",
    	      data :{loginName:loginName},
    	      dataType : "json",
    	      success : function(data){
    	    	  var json = data;
    	    	  if(json.loginName==null)
  	            {
  	        	 
  	        	 $('#checkResult').html('<b style="font-size:18px;color:blue">사용가능</b>');
  	        	}
  	         if(json.loginName!=null)
  	         {
  	        	 $('#checkResult').html('<b style="font-size:18px;color:red">사용불가능</b>');
  	        	
  	         }
    	        
    	      },
    	      error : function(request, status,error) {
    	         alert("통신실패")
    	          console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	      }
    	   });

                });

 });



</script>



<div id="wrapper">

	<div id="page-wrapper">
		<div class="container-fluid">

			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">발급</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="issue">
						
							
							<form:form method="post" action="userInsert.do" id="target" modelAttribute="user">
							<div class="input">
								 
								  <input type="text" value="${user.loginName }" class="box" name="loginName" id="loginName" placeholder="아이디" />
								  
							</div>
							<form:errors path="loginName"/>
							<div id="checkResult">
							
							</div>
							<div class="input">
								 <input type="password" value="${user.password }" class="box" name="password" placeholder="비밀번호" />
								
						    </div>
						      <form:errors path="password"/>
							<div class="input">
								 <input type="text" value="${user.name }" class="box" name="name" placeholder="이름" />
								  
							</div>
							<form:errors path="name"/>
							<div class="input">
								 <input type="email" value="${user.email}" class="box" name="email" placeholder="이메일" />
								  
						    </div>
						      <form:errors path="email"/>
							<div >
								<center><button type="submit" class="login_btn">발급하기</button></center>
						    </div>

						</form:form>

					</div>
				</div>
			</div>

		</div>
		<!-- /.container-fluid -->
	</div>

</div>