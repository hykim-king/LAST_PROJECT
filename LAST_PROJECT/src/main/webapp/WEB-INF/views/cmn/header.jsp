<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2021. 4. 19.        최초작성 
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<!-- Bootstrap core CSS -->
<link href="${hContext}/resources/lhc/bootstrap_lhc.css" rel="stylesheet">

<header class="p-3 bg-dark text-white">
  <div class="container">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
      <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
        <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
      </a>

      <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
        <li><a href="#" class="nav-link px-2 text-secondary">인텔리</a></li>
        <li><a href="#" class="nav-link px-2 text-white">커뮤니티</a></li>
        <li><a href="#" class="nav-link px-2 text-white">스토어</a></li>
        <li><a href="#" class="nav-link px-2 text-white">스크랩</a></li>
        <li><a href="#" class="nav-link px-2 text-white">장바구니</a></li>
        <li><a href="#" class="nav-link px-2 text-white">글쓰기</a></li>
      </ul>

      <div class="text-end">
        <button type="button" class="btn btn-outline-light me-2">로그인</button>
        <button type="button" class="btn btn-warning">회원가입</button>
      </div>
    </div>
  </div>
</header>
