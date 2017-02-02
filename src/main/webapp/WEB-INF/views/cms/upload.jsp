<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>${ title }</h1>
<hr />

<form method="post" enctype="multipart/form-data">
  <div class="mb20"><input type="file" name="file" class="w500" /></div>  
  <button type="submit" class="btn btn-primary" name="cmd" value="upload">업로드</button>
</form>
