<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>영수증 일괄발급</h1>
<hr />

<form:form method="post" modelAttribute="wrapper">

<span>납입기간:</span>
<form:input path="map[startDate]" class="startDt" placeholder="필수" /> ~ 
<form:input path="map[endDate]" class="endDt" placeholder="필수" />
<span>발급일:</span>
<form:input path="map[createDate]" class="date" placeholder="필수" />

<button type="submit" class="btn btn-primary" onclick="showWaitMsg()">영수증 일괄 발급</button>

</form:form>
