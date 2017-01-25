<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="mt4">
  <span>정렬:</span>
  <select id="orderBy">
      <option value="0" ${param.od1==0?"selected":""}>납입일 내림차순</option>
      <option value="1" ${param.od1==1?"selected":""}>납입일 오름차순</option>
      <option value="2" ${param.od1==2?"selected":""}>등록순서 내림차순</option>
      <option value="3" ${param.od1==3?"selected":""}>등록순서 오름차순</option>
  </select>
</div>    

<script>
$("select#orderBy").change(function() { 
  var i = $(this).val();
  var url = location.href;
  var re = /od1=[0-9]/;
  url = (url.search(re) >= 0) ? url.replace(re, "od1="+i) : url + "&od1="+i;
  location.href = url;
})
</script>