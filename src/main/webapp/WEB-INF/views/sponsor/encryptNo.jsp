<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-bordered table-condensed">
    <c:forEach var="sponsor" items="${ list }">
        <tr>
            <td>${ sponsor.id }</td>
            <td>${ sponsor.name }</td>
            <td>${ sponsor.sponsorNo }</td>
            <td>${ sponsor.juminNo }</td>
        </tr>
    </c:forEach>
</table>