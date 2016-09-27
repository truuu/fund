<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
   prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div id="wrapper">
   <div id="page-wrapper">
      <div class="container-fluid">
         <h1 class="page-header">회원관리</h1>
         <div id="column-right">
            <a href="#" class="button button-reversed">신규</a> <a href="#"
               class="button">엑셀</a>
         </div>

         <div id="search">
            <div class="column-right">
               <select name="search" id="search" class="msize">
                  <option selected="selected" value="검색조건">검색조건</option>
                  <option value="0">이름</option>
                  <option value="1">개인</option>
                  <option value="2">가족</option>
                  <option value="3">단체</option>
                  <option value="4">법인</option>
                  <option value="5">직원</option>
                  <option value="6">교수</option>
                  <option value="7">동문</option>
                  <option value="8">학부모</option>
                  <option value="9">성직자</option>
                  <option value="10">교인</option>
                  <option value="11">독지가</option>
                  <option value="12">교회</option>
                  <option value="13">기타</option>
                  
               </select> <input type="search" id="search_a" name="search_a">
               <button type="submit" class="btn btn-small">검색</button>
            </div>
         </div>


         <table class="table table-bordered table-hover">
            <thead>
               <tr>
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
               <tr onclick="location.href('/fund_sys/user/user.do')">
                  <td>201605-0001</td>
                  <td>김하나</td>
                  <td>개인</td>
                  <td>직원</td>
                  <td>OO교회</td>
                  <td>2016.05.02</td>
                  <td>010-8855-6986</td>
                  <td>whdms@naver.com</td>
                  
               </tr>
               
            </tbody>
         </table>


         <!-- Pagination -->
         <div class="row text-center">
            <div class="col-lg-12">
               <ul class="pagination">
                  <li><a href="#">&laquo;</a></li>
                  <li class="active"><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">&raquo;</a></li>
               </ul>
            </div>
         </div>


      </div>
   </div>
</div>

