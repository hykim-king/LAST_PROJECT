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
<%@ include file="../cmn/header.jsp"%>
<c:set var="hContext" value="${pageContext.request.contextPath }"> </c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>Store Home</title>
	
	<link rel="stylesheet" href="${hContext}/resources/store/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/style.css" type="text/css">
    
    <script src="${hContext}/resources/store/js/jquery-3.3.1.min.js"></script>
	<script src="${hContext}/resources/store/js/bootstrap.min.js"></script>
	<script src="${hContext}/resources/store/js/jquery.nice-select.min.js"></script>
	<script src="${hContext}/resources/store/js/jquery.nicescroll.min.js"></script>
	<script src="${hContext}/resources/store/js/jquery.magnific-popup.min.js"></script>
	<script src="${hContext}/resources/store/js/jquery.countdown.min.js"></script>
	<script src="${hContext}/resources/store/js/jquery.slicknav.js"></script>
	<script src="${hContext}/resources/store/js/mixitup.min.js"></script>
	<script src="${hContext}/resources/store/js/owl.carousel.min.js"></script>
	<script src="${hContext}/resources/store/js/main.js"></script>
</head>
<body>
	<header class="header">
        <div class="container">
        	<div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="header__logo">
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <nav class="header__menu mobile-menu">
                        <ul></ul>
                    </nav>
                </div>
                <div class="col-lg-3 col-md-3">
                    <div class="header__nav__option"></div>
                </div>
            </div>
        </div>
    </header>
	<!-- //header -->

	<!-- body -->
	
	<div class="wrap container">
		<!-- 카테고리존 -->
		<section class="product spad">
			 <div class="container">
			 	<div class="row">
	                <div class="col-lg-12">
	                    <ul class="filter__controls_category">
	                        <li>Categories</li>
	                    </ul>
	                </div>
	            </div>
			 </div>
			 <div class="row text-center" id="category">
			 	<div class="col-lg-2 col-md-4 col-6">
					<div class="product__item" id="category_img">
						<a href="${hContext}/store/store_category.do?searchDiv=0">
							<img src="${hContext}/resources/store/img/category/00.JPG" class="img-fluid aos-init aos-animate" data-aos="flip-right">
						</a>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-6">
					<div class="product__item" id="category_img">
						<a href="${hContext}/store/store_category.do?searchDiv=10">
							<img src="${hContext}/resources/store/img/category/01.JPG" class="img-fluid aos-init aos-animate" data-aos="flip-right">
						</a>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-6">
					<div class="product__item" id="category_img">
						<a href="${hContext}/store/store_category.do?searchDiv=20">
							<img src="${hContext}/resources/store/img/category/02.JPG" class="img-fluid aos-init aos-animate" data-aos="flip-right">
						</a>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-6">
					<div class="product__item" id="category_img">
						<a href="${hContext}/store/store_category.do?searchDiv=30">
							<img src="${hContext}/resources/store/img/category/03.JPG" class="img-fluid aos-init aos-animate" data-aos="flip-right">
						</a>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-6">
					<div class="product__item" id="category_img">
						<a href="${hContext}/store/store_category.do?searchDiv=40">
							<img src="${hContext}/resources/store/img/category/04.JPG" class="img-fluid aos-init aos-animate" data-aos="flip-right">
						</a>
					</div>
				</div>
				<div class="col-lg-2 col-md-4 col-6">
					<div class="product__item" id="category_img">
						<a href="${hContext}/store/store_category.do?searchDiv=50">
							<img src="${hContext}/resources/store/img/category/05.JPG" class="img-fluid aos-init aos-animate" data-aos="flip-right">
						</a>
					</div>
				</div>
	         </div>                                                                                                                                           
		</section>
		<!-- //카테고리존 -->
		
		<!-- 상품존 -->
		<section class="product spad"> 
	       	 <div class="container">
	            <div class="row">
	                <div class="col-lg-12">
	                    <ul class="filter__controls">
	                        <li id="bestSellers" class="active" data-filter="*">Best Sellers</li>
	                        <li id="newArrivals" data-filter=".new-arrivals">New Arrivals</li>
	                    </ul>
	                </div>
	            </div>
	            <div class="row product__filter" id="MixItUp666159"></div>
	        </div>
	    </section>
	    <!-- //상품존 -->
	    
    </div>
     
    
    <script type="text/javascript">
    
	  	//jquery 객채생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			doRetrieve(1, "20");
		});//--document ready
		
		
		//베스트 혹은 신상품 클릭
		$("#newArrivals").on("click", function(e) {
			e.preventDefault();
			doRetrieve(1, "10");
		});
		
		$("#bestSellers").on("click", function(e) {
			e.preventDefault();
			doRetrieve(1, "20");
		});
		
		
		function doRetrieve(page,div) {
			
			$.ajax({
	    		type: "GET",
	    		url:"${hContext}/product/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			pageSize: "10",
	    			searchDiv: "",
	    			searchWord: "",
	    			orderDiv: div,
	    			pageNum: page
	    		},
	    		
	    		success:function(data){//통신 성공
	        		console.log("success data load");
	        		var parseData = JSON.parse(data);
	    
	    			//기존 데이터 삭제
	    			var html="";
	    			console.log("parseData.length:"+parseData.length);
	    			$("#MixItUp666159").empty();
	    			
    				//data가 있는 경우
	    			if(parseData.length>0){
	    				
	    				$.each(parseData, function(i, value) {
	    					html += " <div class='col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals'>                                                                            ";
	    					html += " 	<div class='product__item'>                                                                                                                                            ";
	    					html += " 		<div class='product__item__pic set-bg'> ";
	    					html += "			<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><img class='product__item__pic set-bg' src='${hContext}/"+value.imgId+"'></a> ";
	    					html += " 		</div>                                                                                                                                                             ";
	    					html += " 		<div class='product__item__text' id='productItem'>                                                                                                                 ";
	    					html +=  			"<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><h6>"+value.company+"</h6></a>";                                                                                                                                  
	    					html +=  			"<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><h5>"+value.title+"</h5></a>";                                                                                                                                    
	    					html +=  			"<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><h4>"+value.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"</h4></a>";                                                                                                                                
	    					html += 			"<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><h3>리뷰 "+value.totalReview.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"</h3></a>";                                                                                                                          
	    					html += " 		</div>                                                                                                                                                             ";
	    					html += " 	</div>                                                                                                                                                                 ";
	    					html += " </div>                                                                                                                                                                   ";
	    					
	    				});
	    				
	    			}else{ //data가 없는 경우
	    				html+= " <div class='col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals'>                                                                                          ";
	    				html+= "    <div class='product__item'>                                                                                                                                       ";
	    				html+= " 	   <div class='product__item__pic set-bg'>  ";
	    				html += "			<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><img class='product__item__pic set-bg' src='${hContext}/"+value.imgId+"'></a> ";
	    				html+= " 	   </div>                                                                                                                                                          ";
	    				html+= " 	   <div class='product__item__text' id='productItem'>                                                                                                              ";
	    				html+= " 			<h6>제조사 없음</h6>                                                                                                                                           ";
	    				html+= " 			<h5>상품명 없음</h5>                                                                                                                                           ";
	    				html+= " 			<h4>가격 없음</h4>                                                                                                                                            ";
	    				html+= " 			<h3>리뷰 0</h3>                                                                                                                                             ";
	    				html+= " 	   </div>                                                                                                                                                          ";
	    				html+= "    </div>                                                                                                                                                            ";
	    				html+= " </div>                                                                                                                                                               ";
	    			}
    				
	    			//tbody에 데이터 추가
	    			$("#MixItUp666159").append(html);
	    		
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
		}
		
	</script>
			
</body>
</html>