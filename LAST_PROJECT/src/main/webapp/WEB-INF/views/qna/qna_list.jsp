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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../cmn/common.jsp" %>
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

<title>QNA</title>

  <!-- 부트스트랩 -->
    <link href="${hContext }/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext }/resources/js/jquery.min.js"></script>
    <script src="${hContext}/resources/css/bootstrap.min.css"></script>
    <script src="${hContext }/resources/js/eclass.js"></script>
</head>
<body>
<!-- div container -->
	<div class="wrap container">

	<!-- 검색영역 -->
	   	  <div class="row col-lg-12">
		   	<div class="col-xs-8 col-sm-9 col-md-8 col-lg-2">
	 				<select class="form-control input-sm" name="orderDiv" id="orderDiv">					
	    		  		<option value="10">최신순</option>	    		  		
						<option value="20">인기순</option>
	    		  </select> 
		    </div>
		    <div class="col-xs-8 col-sm-9 col-md-8 col-lg-2">
	 				<select class="form-control input-sm" name="searchDiv" id="searchDiv">			
	    		  		<option value="10">제목</option>	    		  		
						<option value="20">태그</option>
						<option value="30">제목+태그</option>
	    		  </select> 
		    </div>
		   	<div class="col-xs-8 col-sm-9 col-md-6 col-lg-6">
	   			<input type = "text" size = "20" name = "searchWord" id = "searchWord" placeholder = "검색어를 입력해주세요" />
			</div>
			<div class="col-xs-8 col-sm-9 col-md-12 col-lg-2">
					<input type="button" class="btn btn-primary btn-sm"  value="조회" id=""/>
					<input type="button" class="btn btn-primary btn-sm"  value="질문등록" id=""/>
			</div>
		  </div>  
	<!-- //검색영역 -->       
         
	    
	    <!-- pagenation -->
		<div class="text-center">
			<div id="page-selection" class="text-center page"></div>
		</div>
	    <!--// pagenation -->	

<!-- section -->	    
<section class="ftco-section ftco-degree-bg">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 ftco-animate">
			<div class="row">
				<div class="col-md-12 d-flex ftco-animate">
		            <div class="blog-entry align-self-stretch d-md-flex">
		              <a href="blog-single.html" class="block-20" style="background-image: url('images/image_1.jpg');">
		              </a>
		              <div class="text d-block pl-md-4">
		              	<div class="meta mb-3">
		                  <div><a href="#">April 9, 2019</a></div>
		                  <div><a href="#">Admin</a></div>
		                  <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
		                </div>
		                <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
		                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
		                <p><a href="blog-single.html" class="btn btn-primary py-2 px-3">Read more</a></p>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-12 d-flex ftco-animate">
		            <div class="blog-entry align-self-stretch d-md-flex">
		              <a href="blog-single.html" class="block-20" style="background-image: url('images/image_2.jpg');">
		              </a>
		              <div class="text d-block pl-md-4">
		              	<div class="meta mb-3">
		                  <div><a href="#">April 9, 2019</a></div>
		                  <div><a href="#">Admin</a></div>
		                  <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
		                </div>
		                <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
		                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
		                <p><a href="blog-single.html" class="btn btn-primary py-2 px-3">Read more</a></p>
		              </div>
		            </div>
		          </div>
		          <div class="col-md-12 d-flex ftco-animate">
		            <div class="blog-entry align-self-stretch d-md-flex">
		              <a href="blog-single.html" class="block-20" style="background-image: url('images/image_3.jpg');">
		              </a>
		              <div class="text d-block pl-md-4">
		              	<div class="meta mb-3">
		                  <div><a href="#">April 9, 2019</a></div>
		                  <div><a href="#">Admin</a></div>
		                  <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
		                </div>
		                <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
		                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
		                <p><a href="blog-single.html" class="btn btn-primary py-2 px-3">Read more</a></p>
		              </div>
		            </div>
		          </div>
			</div>
          </div> <!-- .col-md-8 -->
<!--           <div class="col-lg-4 sidebar ftco-animate">
            <div class="sidebar-box">
              <form action="#" class="search-form">
                <div class="form-group">
                  <span class="icon ion-ios-search"></span>
                  <input type="text" class="form-control" placeholder="Type a keyword and hit enter">
                </div>
              </form>
            </div>
          </div>search -->

        </div>
      </div>
    </section> 
    <!--//section -->
    
     	
	</div>
</body>
</html>