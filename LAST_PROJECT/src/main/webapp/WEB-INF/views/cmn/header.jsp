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

<html>
<head>
    <meta charset="UTF-8">
    <title>인테리</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${hContext}/resources/jji/header-14.css" rel="stylesheet">
    <link href="${hContext}/resources/jji/reset.min.css" rel="stylesheet">
    <link href="${hContext}/resources/jji/style.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
	<link href="${hContext}/resources/lhc/bootstrap_lhc.css" rel="stylesheet">
	<!-- 부트스트랩 -->
	<link href="${hContext }/resources/css/bootstrap.min.css"
	rel="stylesheet">

	<link rel="stylesheet" type="text/css"
	href="${hContext }/resources/jji/mypage.css" />
	
	  <!-- Custom styles for this template -->
  <link href="${hContext}/resources/lhc/shop-homepage.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="${hContext}/resources/lhc/base.css">
  <link rel="stylesheet" type="text/css" href="${hContext}/resources/lhc/normalize.css">
 </head>
<body>
    <!-- Header Start -->
    <header class="site-header">
      <div class="site-header__top">
        <div class="wrapper site-header__wrapper">
          <div class="site-header__start">
            <ul class="">
              <li class=""><a href="#">About</a></li>
              <li class=""><a href="#">Contact</a></li>
            </ul>
          </div>
          <div class="site-header__middle">
            <img src="${hContext }/resources/images/logo.png" width="100"/>
          </div>
          <div class="site-header__end top">
            <c:choose>
				<c:when test="${null != sessionScope.member }">
					<ul class="nav navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href='javascript:goLogOut();'>
								 <span class="glyphicon glyphicon-log-out" aria-hidden="true">&nbsp;로그아웃</span>
							</a>
						</li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href='${hContext}/member/login_view.do'>
								 <span class="glyphicon glyphicon-log-in" aria-hidden="true">&nbsp;로그인</span>
							</a>
						</li>
					</ul>
				</c:otherwise>
			</c:choose>
          </div>
        </div>
      </div>
      <div class="site-header__bottom">
        <div class="wrapper site-header__wrapper">
          <div class="site-header__start">
            <nav class="nav">
              <button class="nav__toggle" aria-expanded="false" type="button">
                menu
              </button>
              <ul class="nav__wrapper">
                
                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
                                    <ul class="dropdown-menu animated fadeInDownShort go">
                                       <li><a href="${hContext}/houses/home_view.do">커뮤니티 홈</a></li>
                                       <li><a href="${hContext}/houses/list_view.do">집들이</a></li>
                                    </ul>
                 </li>
                 <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">스토어</a>
                                    <ul class="dropdown-menu animated fadeInDownShort go">
                                       <li><a href="${hContext}/store/store_view.do">스토어 홈</a></li>
                                       <li><a href="${hContext}/store/store_category.do?searchDiv=0">카테고리</a></li>
                                    </ul>
                 </li>
               	 <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">글쓰기</a>
                                    <ul class="dropdown-menu animated fadeInDownShort go">
                                       <li><a href="${hContext}/houses/houses_regist.do">집들이 등록</a></li>
                                       <li><a href="${hContext}/store/product_regist.do">상품등록</a></li>
                                    </ul>
                 </li>
                 <li><a href="${hContext}/qna/qna_view.do">질문과 답변</a></li>
              </ul>
            </nav>
          </div>

          <div class="site-header__end bottom">
            <a href="${hContext}/member/basket_list.do" class="cart">
              <svg version="1.1" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                <g>
                  <title>Cart</title>
                  <path d="m95.398 23.699c-1.8008-2.3008-4.6016-3.6992-7.5-3.6992h-60.898l-1.8984-7.3984c-1.1016-4.3008-4.8984-7.3008-9.3008-7.3008h-10.199c-1.6992 0-3.1016 1.3984-3.1016 3.1016 0 1.6992 1.3984 3.1016 3.1016 3.1016h10.199c1.5 0 2.8008 1 3.1992 2.5l12.199 48.602c1.1016 4.3008 4.8984 7.3008 9.3008 7.3008h39.898c4.3984 0 8.3008-3 9.3008-7.3008l7.5-30.801c0.69922-2.8047 0.10156-5.8047-1.8008-8.1055zm-4.2969 6.6992-7.5 30.801c-0.39844 1.5-1.6992 2.5-3.1992 2.5h-39.902c-1.5 0-2.8008-1-3.1992-2.5l-8.6992-34.898h59.301c1 0 2 0.5 2.6016 1.3008 0.59766 0.79688 0.89453 1.7969 0.59766 2.7969z"></path>
                  <path d="m42.602 73.898c-5.6992 0-10.398 4.6992-10.398 10.398s4.6992 10.398 10.398 10.398c5.6992 0.003907 10.398-4.6953 10.398-10.395s-4.6992-10.402-10.398-10.402zm0 14.5c-2.3008 0-4.1016-1.8008-4.1016-4.1016s1.8008-4.1016 4.1016-4.1016c2.3008 0 4.1016 1.8008 4.1016 4.1016-0.003906 2.2031-1.9023 4.1016-4.1016 4.1016z"></path>
                  <path d="m77 73.898c-5.6992 0-10.398 4.6992-10.398 10.398s4.6992 10.398 10.398 10.398 10.398-4.6992 10.398-10.398c-0.097657-5.6953-4.6992-10.398-10.398-10.398zm0 14.5c-2.3008 0-4.1016-1.8008-4.1016-4.1016s1.8008-4.1016 4.1016-4.1016 4.1016 1.8008 4.1016 4.1016c0 2.2031-1.9023 4.1016-4.1016 4.1016z"></path>
                </g>
              </svg>
            </a>
            <a href="${hContext}/mypage/Member_MyPage.do">
              <svg version="1.1" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                <title>Profile</title>
                <path d="m65.57 52.5c6.9336-4.5078 11.574-11.797 12.723-19.988 1.1484-8.1875-1.3047-16.473-6.7344-22.715-5.4258-6.2422-13.289-9.8242-21.559-9.8242s-16.133 3.582-21.559 9.8242c-5.4297 6.2422-7.8828 14.527-6.7344 22.715 1.1484 8.1914 5.7891 15.48 12.723 19.988-10.012 3.2812-18.73 9.6406-24.914 18.172-6.1836 8.5273-9.5117 18.793-9.5156 29.328h7.1445c0-15.312 8.168-29.461 21.426-37.117 13.262-7.6523 29.598-7.6523 42.859 0 13.258 7.6562 21.426 21.805 21.426 37.117h7.1445c-0.003906-10.535-3.332-20.801-9.5156-29.328-6.1836-8.5312-14.902-14.891-24.914-18.172zm-37-23.93c0-5.6836 2.2578-11.133 6.2773-15.152 4.0195-4.0156 9.4688-6.2734 15.152-6.2734s11.133 2.2578 15.152 6.2734c4.0195 4.0195 6.2773 9.4688 6.2773 15.152 0 5.6836-2.2578 11.137-6.2773 15.152-4.0195 4.0195-9.4688 6.2773-15.152 6.2773s-11.133-2.2578-15.152-6.2773c-4.0195-4.0156-6.2773-9.4688-6.2773-15.152z"></path>
              </svg>
            </a>
          </div>
        </div>
      </div>
    </header>
    <!-- Header End -->
	<script>
		function goLogOut(){
			console.log("goLogOut");
			window.location.href = '<c:out value="${hContext}/member/do_logoff.do"/>';
		}
	</script>
  	

</body>
</html>