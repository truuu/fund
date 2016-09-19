<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 class="page-header">${name} 추가</h2>
<form method="post">
    <div>
        <span>코드명:</span>
        <input type="text" name="codeName" />
    </div>
    <div>
        <span>etc1:</span>
        <input type="text" name="etc1" />
    </div>
    
     <button type="submit" class="btn btn-primary">
            <i class="icon-ok icon-white"></i> 저장하기
        </button>
         <a href="codeList.do?CodeGroupID=${ CodeGroupID }" class="btn btn-default">
            <i class="icon-ban-circle"></i> 취소
        </a>
        
 </form>
 
</body>
</html>