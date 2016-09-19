<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 class="page-header">기관 추가</h2>
<form method="post">
    <div>
        <span>이름:</span>
        <input type="text" name="name" />
    </div>
    <div>
        <span>기관번호:</span>
        <input type="text" name="corporateNo" />
    </div>
     <div>
        <span>대표자명:</span>
        <input type="text" name="representative" />
    </div>
     <div>
        <span>주소:</span>
        <input type="text" name="address" />
    </div>
    
     <button type="submit" class="btn btn-primary">
            <i class="icon-ok icon-white"></i> 저장하기
        </button>
         <a href="corporateList.do" class="btn btn-default">
            <i class="icon-ban-circle"></i> 취소
        </a>
        
 </form>
 
</body>
</html>