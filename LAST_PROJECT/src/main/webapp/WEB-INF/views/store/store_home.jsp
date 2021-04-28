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

	<!-- body -->
	<div class="wrap container">
		<section class="product spad">
		
			<!-- 카테고리존 -->
			 <div class="container">
			 	<div class="row">
	                <div class="col-lg-12">
	                    <ul class="filter__controls">
	                        <li class="active" >Category Best Sellers</li>
	                    </ul>
	                </div>
	            </div>
			 </div>
			 <div class="row text-center" id="category">
			 	<div class="row no-gutters clients-wrap clearfix wow fadeInUp">
		         </div>                                                                                                                                               
	         </div>
			 <!-- //카테고리존 -->
			 
			 <!-- 상품존 -->
	       	 <div class="container">
	            <div class="row">
	                <div class="col-lg-12">
	                    <ul class="filter__controls">
	                        <li id="bestSellers" class="active" data-filter="*">Best Sellers</li>
	                        <li id="newArrivals" data-filter=".new-arrivals">New Arrivals</li>
	                    </ul>
	                </div>
	            </div>
	            <div class="row product__filter" id="MixItUp666159">
	            </div>
	        </div>
	        <!-- //상품존 -->
	        
	    </section>
    </div>
    
    
    <script type="text/javascript">
    
	  	//jquery 객채생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			doRetrieveBest(1);
		});//--document ready
		
		//상품클릭
		$("#productClick").on("click", function(e) {
			console.log("상품상세페이지이동");
		});
		
		//스크랩클릭
		$("#scrap").on("click", function(e) {
			console.log("스크랩완료");
		});
		
		
		//베스트 혹은 신상품 클릭
		$("#newArrivals").on("click", function(e) {
			console.log("신상품순클릭");
			doRetrieveNew(1);
		});
		
		$("#bestSellers").on("click", function(e) {
			console.log("베스트상품순클릭");
			doRetrieveBest(1);
		});
		
		
		
		function doRetrieveBest(page) {
			
			$.ajax({
	    		type: "GET",
	    		url:"${hContext}/product/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			pageSize: $("#pageSize").val(),
	    			searchDiv: "",
	    			searchWord: "",
	    			orderDiv: "20",
	    			pageNum: page
	    		},
	    		
	    		success:function(data){//통신 성공
	        		console.log("success data:"+data);
	        		var parseData = JSON.parse(data);
	    
	    			//기존 데이터 삭제
	    			var html="";
	    			console.log("parseData.length:"+parseData.length);
	    			$("#MixItUp666159").empty();
	    			
	    			
    				//data가 있는 경우
	    			if(parseData.length>0){
	    				
	    				$.each(parseData, function(i, value) {
	    					console.log("value");
	    					
	    					html += " <div class='col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals'>                                                                            ";
	    					html += " 	<div class='product__item'>                                                                                                                                            ";
	    					html += " 		<div class='product__item__pic set-bg'> ";
	    					html += "			<img src='${hContext}/resources/store/img/"+value.imgId+".jpg'> ";
	    					html += " 			<ul class='product__hover' id='scrap'>                                                                                                                                    ";
	    					html += " 				<li><a href='#'><img src='${hContext}/resources/store/img/icon/bookmark.png' alt=''><span>scrap</span></a></li>                                                     ";
	    					html += " 			</ul>                                                                                                                                                          ";
	    					html += " 		</div>                                                                                                                                                             ";
	    					html += " 		<div class='product__item__text' id='productItem'>                                                                                                                 ";
	    					html +=  			"<h6>"+value.company+"</h6>";                                                                                                                                  
	    					html +=  			"<h5>"+value.title+"</h5>";                                                                                                                                    
	    					html +=  			"<h6>가격 "+value.price+"</h5>";                                                                                                                                
	    					html += 			"<h6>리뷰 "+value.totalReview+"</h6>";                                                                                                                          
	    					html += " 		</div>                                                                                                                                                             ";
	    					html += " 	</div>                                                                                                                                                                 ";
	    					html += " </div>                                                                                                                                                                   ";

	    				});
	    				
	    			}else{ //data가 없는 경우
	    				html+= " <div class='col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals'>                                                                                          ";
	    				html+= "    <div class='product__item'>                                                                                                                                       ";
	    				html+= " 	   <div class='product__item__pic set-bg' data-setbg='${hContext}/resources/store/img/img01.JPG' style='background-image: url('${hContext}/resources/img/img01.JPG');'>  ";
	    				html+= " 		   <ul class='product__hover' id='scrap'>                                                                                                                                 ";
	    				html+= " 			   <li><a href='#'><img src='${hContext}/resources/store/img/icon/bookmark.png' alt=''><span>scrap</span></a></li>                                                  ";
	    				html+= " 		   </ul>                                                                                                                                                       ";
	    				html+= " 	   </div>                                                                                                                                                          ";
	    				html+= " 	   <div class='product__item__text' id='productItem'>                                                                                                              ";
	    				html+= " 			<h6>제조사없음</h6>                                                                                                                                           ";
	    				html+= " 			<h5>상품명없음</h5>                                                                                                                                           ";
	    				html+= " 			<h6>가격없음</h5>                                                                                                                                            ";
	    				html+= " 			<h6>리뷰 0</h6>                                                                                                                                             ";
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
 		
		
		
		
		function doRetrieveNew(page) {
			
			$.ajax({
	    		type: "GET",
	    		url:"${hContext}/product/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			pageSize: $("#pageSize").val(),
	    			searchDiv: "",
	    			searchWord: "",
	    			orderDiv: "10",
	    			pageNum: page
	    		},
	    		
	    		success:function(data){//통신 성공
	        		console.log("success data:"+data);
	        		var parseData = JSON.parse(data);
	    
	    			//기존 데이터 삭제
	    			var html="";
	    			console.log("parseData.length:"+parseData.length);
	    			$("#MixItUp666159").empty();
	    			
	    			
    				//data가 있는 경우
	    			if(parseData.length>0){
	    				
	    				$.each(parseData, function(i, value) {
	    					console.log("value");
	    					
	    					html += " <div class='col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals'>                                                                            ";
	    					html += " 	<div class='product__item'>                                                                                                                                            ";
	    					html += " 		<div class='product__item__pic set-bg' data-setbg='이미지주소' style='background-image: url(\'img_girl.jpg\');' >  ";
	    					html += " 			<ul class='product__hover' id='scrap'>                                                                                                                                    ";
	    					html += " 				<li><a href='#'><img src='${hContext}/resources/store/img/icon/bookmark.png' alt=''><span>scrap</span></a></li>                                                     ";
	    					html += " 			</ul>                                                                                                                                                          ";
	    					html += " 		</div>                                                                                                                                                             ";
	    					html += " 		<div class='product__item__text' id='productItem'>                                                                                                                 ";
	    					html +=  			"<h6>"+value.company+"</h6>";                                                                                                                                  
	    					html +=  			"<h5>"+value.title+"</h5>";                                                                                                                                    
	    					html +=  			"<h6>가격 "+value.price+"</h5>";                                                                                                                                
	    					html += 			"<h6>리뷰 "+value.totalReview+"</h6>";                                                                                                                          
	    					html += " 		</div>                                                                                                                                                             ";
	    					html += " 	</div>                                                                                                                                                                 ";
	    					html += " </div>                                                                                                                                                                   ";

	    				});
	    				
	    			}else{ //data가 없는 경우
	    				html+= " <div class='col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals'>                                                                                          ";
	    				html+= "    <div class='product__item'>                                                                                                                                       ";
	    				html+= " 	   <div class='product__item__pic set-bg' data-setbg='${hContext}/resources/store/img/img01.JPG' style='background-image: url('${hContext}/resources/img/img01.JPG');'>  ";
	    				html+= " 		   <ul class='product__hover' id='scrap'>                                                                                                                                 ";
	    				html+= " 			   <li><a href='#'><img src='${hContext}/resources/store/img/icon/bookmark.png' alt=''><span>scrap</span></a></li>                                                  ";
	    				html+= " 		   </ul>                                                                                                                                                       ";
	    				html+= " 	   </div>                                                                                                                                                          ";
	    				html+= " 	   <div class='product__item__text' id='productItem'>                                                                                                              ";
	    				html+= " 			<h6>제조사없음</h6>                                                                                                                                           ";
	    				html+= " 			<h5>상품명없음</h5>                                                                                                                                           ";
	    				html+= " 			<h6>가격없음</h5>                                                                                                                                            ";
	    				html+= " 			<h6>리뷰 0</h6>                                                                                                                                             ";
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