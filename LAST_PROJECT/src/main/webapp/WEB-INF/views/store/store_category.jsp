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
	<title>Store Category</title>
	
	<link rel="stylesheet" href="${hContext}/resources/store/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${hContext}/resources/store/css/style.css" type="text/css">
    
    <script src="${hContext}/resources/store/js/jquery-3.3.1.min.js"></script>
	<script src="${hContext}/resources/js/bootstrap.min.js"></script>
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
	
		
	<section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">
                        <h4>Store</h4>
                        <div class="breadcrumb__links">
                            <a href="${hContext}/store/store_view.do">Home</a>
                            <span>Category</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
	
	<section class="shop spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                	<!-- 사이드바 -->
                    <div class="shop__sidebar">
                    
                    	<!-- 검색 -->
                        <div class="shop__sidebar__search">
                            <form action="">
                                <input type="text" name="searchWord" id="searchWord" placeholder="Search..." >
								<button type="button" id="searchBtn"><span class="icon_search"></span></button>
                            </form>
                        </div>
                        <!-- //검색 -->
                        
                        <!-- 카테고리 -->
                        <div class="shop__sidebar__accordion">
                            <div class="accordion" id="accordionExample">
                                <div class="card">
                                    <div class="card-heading">
                                        <a>Categories</a>
                                    </div>
                                    <div>
                                        <div class="card-body">
                                            <div class="shop__sidebar__categories">
                                              	<select name="searchDiv" id="searchDiv">
													<option value="0" <c:if test="${searchDiv=='0'}">selected</c:if>>전체보기</option>
													<option value="10" <c:if test="${searchDiv=='10'}">selected</c:if>>가구</option>
													<option value="20" <c:if test="${searchDiv=='20'}">selected</c:if>>주방</option>
													<option value="30" <c:if test="${searchDiv=='30'}">selected</c:if>>가전</option>
													<option value="40" <c:if test="${searchDiv=='40'}">selected</c:if>>생활</option>                      
													<option value="50" <c:if test="${searchDiv=='50'}">selected</c:if>>DIY/공구</option>
												</select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                               	<!-- 태그 -->
                                <div class="card">
                                    <div class="card-heading">
                                        <a>Tags</a>
                                    </div>
                                    <div>
                                        <div class="card-body">
                                            <div class="shop__sidebar__tags">
                                                <a>우드</a>
                                                <a>원목</a>
                                                <a>화이트</a>
                                                <a>led</a>
                                                <a>패브릭</a>
                                                <a>쉬폰</a>
                                                <a>블라인드</a>
                                                <a>소파</a>
                                                <a>테이블</a>
                                                <a>무드등</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- data load total Cnt -->
                <div class="col-lg-9">
                    <div class="shop__product__option">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="shop__product__option__left"></div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <!-- 인기순 -->
                    <div class="container">
					 	<div class="row">
			                <div class="col-lg-12">
			                    <ul class="filter__controls">
			                        <li class="active" id="orderDiv" value="20" >Best Sellers</li>
			                    </ul>
			                </div>
			            </div>  
					 </div>
					 <div class="row" id="MixItUp666159"></div>
                    
                    <!-- 신상순 -->
                    <div class="container">
					 	<div class="row">
			                <div class="col-lg-12">
			                    <ul class="filter__controls">
			                        <li class="active" id="orderDiv" value="10" >New Arrivals</li>
			                    </ul>
			                </div>
			            </div>
					 </div>
					 <div class="row" id="MixItUp666160"></div> 
                </div>
            </div>
        </div>
    </section>
    
	<script type="text/javascript">
    
	  	//jquery 객채생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			doRetrieve(1,"20");
			doRetrieve(1,"10");
		});//--document ready
		

		//검색버튼 클릭시
		$("#searchBtn").on("click", function(e) {
			doRetrieve(1,"20");
			doRetrieve(1,"10");
		});
		
		
		//카테고리별 조회
		$("#searchDiv").on("change", function(){
     		console.log("searchDiv");
     		location.href = "${hContext}/store/store_category.do?searchDiv="+$("#searchDiv").val();
     	});	
		
		//Enter키 막기
		$('input[type="text"]').keydown(function() {
			  if (event.keyCode === 13) {
			    event.preventDefault();
			  };
		});
		
		
		function doRetrieve(page,div) {
			$.ajax({
	    		type: "GET",
	    		url:"${hContext}/product/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			pageSize: "100",
	    			searchDiv: $("#searchDiv").val(),
	    			searchWord: $("#searchWord").val(),
	    			orderDiv: div,
	    			pageNum: page
	    		},
	    		
	    		success:function(data){//통신 성공
	        		console.log("success data:"+data);
	        		var parseData = JSON.parse(data);
	        		console.log("parseData.length:"+parseData.length);
	        		
	        		
	    			//기존 데이터 삭제
	    			var html="";
	    			var html2="";
	    			if(div == "20"){
	    				$("#MixItUp666159").empty();
					}else if(div == "10"){
						$("#MixItUp666160").empty();
					}
	    			$(".shop__product__option__left").empty();
	    			
    				//data가 있는 경우
	    			if(parseData.length>0){
	    				
	    				$.each(parseData, function(i, value) {
	    					html += " <div class='col-lg-4 col-md-6 col-sm-6'>                                                                            ";
	    					html += " 	<div class='product__item'>                ";
	    					html += " 		<div class='product__item__pic set-bg'> ";
	    					html += "			<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><img class='product__item__pic set-bg' src='${hContext}/"+value.imgId+"'></a> ";
	    					html += " 		</div>                                                                                                                                                             ";
	    					html += " 		<div class='product__item__text'>                                                                                                                 ";
	    					html +=  			"<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><h6>"+value.company+"</h6></a>";                                                                                                                                  
	    					html +=  			"<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><h5>"+value.title+"</h5></a>";                                                                                                                                    
	    					html +=  			"<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><h4>"+value.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"</h4></a>";                                                                                                                                
	    					html += 			"<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><h3>리뷰 "+value.totalReview.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"</h3></a>";                                                                                                                          
	    					html += " 		</div>                                                                                                                                                             ";
	    					html += " 	</div>                                                                                                                                                                 ";
	    					html += " </div>                                                                                                                                                                   ";
	    				
	    					html2 ="<p>Showing 1-"+parseData.length+" of "+parseData.length+" results</p>";
	    				});
	    				
	    			}else{ //data가 없는 경우
	    				html+= " <div class='col-lg-4 col-md-6 col-sm-6'>                                                                                         ";
	    				html+= "    <div class='product__item'>                                                                                                                                       ";
	    				html+= " 	   <div class='product__item__pic set-bg'>  ";
	    				html += "			<a href='${hContext}/store/store_detail.do?storeSeq="+value.storeSeq+"'><img class='product__item__pic set-bg' src='${hContext}/"+value.imgId+"'></a> ";
	    				html+= " 	   </div>                                                                                                                                                          ";
	    				html+= " 	   <div class='product__item__text'>                                                                                                              ";
	    				html+= " 			<h6>제조사없음</h6>                                                                                                                                           ";
	    				html+= " 			<h5>상품명없음</h5>                                                                                                                                           ";
	    				html+= " 			<h4>가격없음</h4>                                                                                                                                            ";
	    				html+= " 			<h3>리뷰 0</h3>                                                                                                                                             ";
	    				html+= " 	   </div>                                                                                                                                                          ";
	    				html+= "    </div>                                                                                                                                                            ";
	    				html+= " </div>                                                                                                                                                               ";
	    			}
    				
					//데이터 추가
					if(div == "20"){
						$("#MixItUp666159").append(html);
					}else if(div == "10"){
						$("#MixItUp666160").append(html);
					}
	    			
	    			//결과개수출력
	    			$(".shop__product__option__left").append(html2);
	    			
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