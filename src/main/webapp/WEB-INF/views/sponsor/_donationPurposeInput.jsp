<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<span id="corporateName">${ paramObj.corporateName }</span> /
<span id="organizationName">${ paramObj.organizationName }</span> /
<span id="donationPurposeName">${ paramObj.donationPurposeName }</span>
<input type="hidden" name="donationPurposeId" id="donationPurposeId" value="${ paramObj.donationPurposeId }" />
<a href="#donationPurposeDialog" class="btn btn-sm btn-gray" data-toggle="modal">검색</a>
