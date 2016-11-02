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
  	        	 alert("true11")
  	        	 $('#checkResult').html('<b style="font-size:18px;color:blue">사용가능</b>');
  	        	}
  	         if(json.loginName!=null)
  	         {
  	        	 $('#checkResult').html('<b style="font-size:18px;color:red">사용불가능</b>');
  	        	 alert("false11")
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
						
							<form method="POST" action="userInsert.do">
							<div class="input">
								 <input type="text" class="box" name="loginName" id="loginName" placeholder="아이디" />
								
							</div>
							<div id="checkResult">
							
							</div>
							<div class="input">
								 <input type="password" class="box" name="password" placeholder="비밀번호" />
						    </div>
							<div class="input">
								 <input type="text" class="box" name="name" placeholder="이름" />
							</div>
							<div class="input">
								 <input type="email" class="box" name="email" placeholder="이메일" />
						    </div>
							<div >
								<center><button type="submit" class="login_btn">발급하기</button></center>
						    </div>

						</form>

					</div>
				</div>
			</div>

		</div>
		<!-- /.container-fluid -->
	</div>

</div>