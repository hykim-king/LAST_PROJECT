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
<c:set var="hContext" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>스토어 단건 조회</title>
	
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${hContext}/resources/css/store-detail.style.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="${hContext}/resources/js/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
    <script src="${hContext}/resources/js/eutil.js"></script>
</head>
<body>

    <!-- 스토어 단건 조회 -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="product__details__img">
                        <div class="product__details__big__img">
                            <img class="big_img" src="${hContext}/resources/img/shop/details/product-big-1.jpg" alt="">
                        </div>
                        <div class="product__details__thumb">
                            <div class="pt__item active">
                                <img data-imgbigurl="${hContext}/resources/img/shop/details/product-big-2.jpg"
                                src="${hContext}/resources/img/shop/details/product-big-2.jpg" alt="">
                            </div>
                            <div class="pt__item">
                                <img data-imgbigurl="${hContext}/resources/img/shop/details/product-big-1.jpg"
                                src="${hContext}/resources/img/shop/details/product-big-1.jpg" alt="">
                            </div>
                            <div class="pt__item">
                                <img data-imgbigurl="${hContext}/resources/img/shop/details/product-big-4.jpg"
                                src="${hContext}/resources/img/shop/details/product-big-4.jpg" alt="">
                            </div>
                            <div class="pt__item">
                                <img data-imgbigurl="${hContext}/resources/img/shop/details/product-big-3.jpg"
                                src="${hContext}/resources/img/shop/details/product-big-3.jpg" alt="">
                            </div>
                            <div class="pt__item">
                                <img data-imgbigurl="${hContext}/resources/img/shop/details/product-big-5.jpg"
                                src="${hContext}/resources/img/shop/details/product-big-5.jpg" alt="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="product__details__text">
                        <div class="product__label">category</div>
                        <h4 id="title"></h4>
                        <div class="star-rating"><span id="totalReview"></span> 개 리뷰 </div>
                        <h5><span id="price"></span>원</h5>
                        <ul>
                            <li>브랜드: <span id="company"></span></li>
                            <li>카테고리: <span id="category"></span></li>
                            <li>태그: <span id="tag"></span></li>
                        </ul>
						<div class="row mt-4">
							<div class="col-md-6">
								<div class="form-group d-flex">
					              <div class="select-wrap">
				                 	 <div class="icon"><span class="ion-ios-arrow-down"></span></div>
				                 	 <select name="optone" id="optone" class="form-control">           
				                  		<option value="1" disabled selected hidden>색상</option>
	        				 		</select>                                                      
	        				    </div>                                                       
				                </div>
					            <div class="select-wrap">
				                  <div class="icon"><span class="ion-ios-arrow-down"></span></div>
				                  <select name="opttwo" id="opttwo" class="form-control">
				                  	<option value="2" disabled selected hidden>사이즈</option>
				                  </select>
				                </div>
					    	</div>
					    	</div>
					    </div>
			            <div class="product__details__option">
                        	<div class="quantity">
                                <div class="pro-qty">
                                	<span class="dec qtybtn">-</span>                                                
									<input type="text" value="1" id="quantity">                                                
									<span class="inc qtybtn">+</span>
                                </div>
                            </div>
                        </div>
                        <div class="product__details__text">
                            <h5>주문금액 <span id="total"></span>원</h5>
                            <input type="button" class="primary-btn" id="addCart" value="장바구니">
                            <input type="button" class="primary-btn" id="buyNow" value="바로구매">
                            <!-- <a href="#" class="primary-btn">바로구매</a> -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="product__details__tab">
                <div class="col-lg-12">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#descriptionTab" role="tab">상품정보</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#reviewTab" role="tab">리뷰</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#qnaTab" role="tab">문의</a>
                        </li>                        
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#refundTab" role="tab">배송/환불</a>
                        </li>
                    </ul>
                    <div class="tab-content active">
                        <div class="tab-pane" id="descriptionTab" role="tabpanel">
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-8">
                                <h5>상품 정보 <span id="contents"></span></h5>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="reviewTab" role="tabpanel">
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-8">
                                    <h5>리뷰 <span id="totalReview">리뷰</span></h5>
                                    <h5>평점 <span id="avgStar">평점</span></h5>
                                </div>
                            </div>
                        <div class="contact__form">
                        <form action="#">
                            <div class="row">
                                <div class="col-lg-12">
                                    <textarea id="reviewContents" placeholder="별점과 이용경험을 남겨주세요."></textarea>
                                    <button type="submit" class="site-btn" id="reviewInsert">등록</button>
                                </div>
                            </div>
                        </form>
                      </div>
                    <div class="blog__details__author">
	                   <div class="blog__details__comment">         
						  <h5>리뷰</h5>       
						    <div class="blog__details__comment__item" id="reviewList">
						    
						    </div>                  
					      </div>
                       </div>
                     </div>
                        <div class="tab-pane" id="qnaTab" role="tabpanel">
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-8">
                                	<h5>문의 <span id="qna"></span></h5>
                                </div>
                            </div>
                        </div>                        
                        <div class="tab-pane" id="refundTab" role="tabpanel">
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-8">
                                	<h5>배송/환불 정책 <span id="refund"></span></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 스토어 단건 조회 -->

    <!-- 추천 상품 -->
    <section class="related-products spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                        <h2>추천 상품</h2>
                </div>
            </div>
            <div class="row">
                <div class="related__products__slider owl-carousel">
                    <div class="col-lg-3">
                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="${hContext}/resources/img/shop/product-1.jpg">
                                <div class="product__label">
                                    <span>Cupcake</span>
                                </div>
                            </div>
                            <div class="product__item__text">
                                <h6><a href="#">Dozen Cupcakes</a></h6>
                                <div class="product__item__price">$32.00</div>
                                <div class="cart_add">
                                    <a href="#">Add to cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="${hContext}/resources/img/shop/product-2.jpg">
                                <div class="product__label">
                                    <span>Cupcake</span>
                                </div>
                            </div>
                            <div class="product__item__text">
                                <h6><a href="#">Cookies and Cream</a></h6>
                                <div class="product__item__price">$30.00</div>
                                <div class="cart_add">
                                    <a href="#">Add to cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="${hContext}/resources/img/shop/product-3.jpg">
                                <div class="product__label">
                                    <span>Cupcake</span>
                                </div>
                            </div>
                            <div class="product__item__text">
                                <h6><a href="#">Gluten Free Mini Dozen</a></h6>
                                <div class="product__item__price">$31.00</div>
                                <div class="cart_add">
                                    <a href="#">Add to cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="${hContext}/resources/img/shop/product-4.jpg">
                                <div class="product__label">
                                    <span>Cupcake</span>
                                </div>
                            </div>
                            <div class="product__item__text">
                                <h6><a href="#">Cookie Dough</a></h6>
                                <div class="product__item__price">$25.00</div>
                                <div class="cart_add">
                                    <a href="#">Add to cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 추천 상품 -->
	
	<script type="text/javascript">

	$(document).ready(function() {
			console.log("1.document:최초수행!");
			storeSelectOne();
			optionRetrieve();
		});
		
		/* 스토어 단건 조회 */
		function storeSelectOne() {
			console.log("doSelectOne()");

			var storeSeqData = "2021/04/2364420dadcc1e40648ecd785ec9fcc8a8";
			
			let url = "${hContext}/product/do_selectone.do";
			let parameter = {"storeSeq":storeSeqData}; 
			let method = "GET";
			let async = true;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
				console.log("data:"+data);		
				console.log("storeSeq:"+data.storeSeq);	
				console.log("contents:"+data.contents);	
				
				//Product [storeSeq=2021/04/2364420dadcc1e40648ecd785ec9fcc8a8, memberId=test01, imgId=img01, title=title01, 
				//contents=contents01, company=company01, category=10, tag=tag01, price=1, quantity=1, refund=refund01, regDt=2021/04/23, modId=null, modDt=null, toString()=DTO [num=0, totalCnt=0, totalScrap=0, totalReview=0, avgStar=0.0]]
				
				var total = data.price * $("#quantity").val(); //합계
				console.log("total1:"+total);	
				
			    /* 수량 변경 */
			    var proQty = $(".pro-qty");
			    proQty.on("click", ".qtybtn", function () {
			        var $button = $(this);
			        var oldValue = $button.parent().find("input").val();
			        if ($button.hasClass("inc")) {
			            var newVal = parseFloat(oldValue) + 1;
			        } else {
			            if (oldValue > 0) {
			                var newVal = parseFloat(oldValue) - 1;
			            } else {
			                newVal = 0;
			            }
			        }
			        $button.parent().find("input").val(newVal);
			        
				    total = data.price * newVal; //수량 변경 후 합계
					console.log("total2:"+total);	
					
	    			$("#total").text(numberWithCommas(total));

			    });
			    
    			$("#imgId").text(data.imgId);
    			$("#title").text(data.title);
    			$("#contents").val(data.contents);
    			$("#company").text(data.company);
    			$("#category").text(data.category);
    			$("#tag").text(data.tag);
    			$("#price").text(numberWithCommas(data.price));
    			$("#quantity").text(data.quantity);
    			$("#refund").text(data.refund);
    			$("#total").text(numberWithCommas(total));
    			$("#totalReview").text(data.totalReview);
    			$("#avgStar").text(data.avgStar);

			});
		}
		
		/* 옵션 선택 */
		function optionRetrieve() {
			console.log("optionRetrieve()");
			
			var storeSeqData = "12345";
			var divData = "1";
			
	      	$.ajax({
	    		type: "GET",
	    		url:"${hContext}/opt/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
		    			"storeSeq" : storeSeqData, 
						"div"      : divData 
	    		},
	    		success:function(data){//통신 성공
	        		console.log("success data : " + data);
	    		
	        		var parseData = JSON.parse(data);
	        		
	        		var html = "";
	        		
	        		console.log("parseData.length : " + parseData.length);

	        		//데이터가 있는 경우
	        		if(parseData.length > 0) {

	        			$.each(parseData, function(i, value) {
	        				console.log(i+","+value.title);
	        				
	        				html +=" <option value=''>"+value.title+"</option> ";
	        				
	        			});
	        			
	        		}
	        		
	        		if (divData == "1") {
	        			$("#optone").append(html); //데이터 추가
	        		} else if (divData == "2") {
	        			$("#opttwo").append(html); //데이터 추가
	        		}
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
		}
		
	    /* 장바구니 */
		$("#addCart").on("click", function(e) {
			console.log("addCart btn click");
			
			var basketSeqData = "2021/04/2664420dadcc1e40648ecdcc8a8785ec9f";
			var storeSeqData = "2021/04/2364420dadcc1e40648ecd785ec9fcc8a8";
			var memberIdData = "test01";
			var shipfeeData = "3000";
			
			let url = "${hContext}/basket/do_insert.do";
			let parameter = {
								"basketSeq" : basketSeqData,
								"storeSeq"  : storeSeqData,
								"memberId"  : memberIdData,
								"title"     : $("#title").text(),
								"optone"    : $("#optone option:selected").text(),
								"opttwo"    : $("#opttwo option:selected").text(),
								"quantity"  : $("#quantity").val(),
								"shipfee"   : shipfeeData,
								"price"     : removeComma($("#price").text())
							};
			let method = "POST";
			let async = true;

			if(confirm("장바구니에 담으시겠습니까?")==false) return;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
    			console.log("data:"+data.msgContents);	
    			if(data.msgId=="1") { //등록 성공
        			alert(data.msgContents);   
        			
    				window.location.href = "${hContext}/member/basket_list.do?storeSeq="+storeSeqData;
    			
    			} else { //등록 실패
        			alert(data.msgId+ "\n" +data.msgContents);
    			}
	    	});
		});
		
		/* 바로구매 */
		$("#buyNow").on("click", function(e) {
			console.log("buyNow btn click");

		});
		
		/* 탭 전환 */
		$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
		    var tab = $(e.target);
		    var contentId = tab.attr("href");

		    //tab active
		    if (tab.parent().hasClass('active')) {
		         //console.log('the tab with the content id ' + contentId + ' is visible');
		         
		         if (contentId=="#descriptionTab") { 
		        	 $("#reviewList").empty();
		         } else if (contentId=="#reviewTab") { 
		 		    reviewRetrieve();
		         } else if (contentId=="#qnaTab") {
		        	 qnaRetrieve();
		        	 $("#reviewList").empty();
			     } else if (contentId=="#refundTab") {
			    	 refundRetrieve();
			    	 $("#reviewList").empty();
			     }
		         
		    } 

		});
		
		/* 리뷰 쓰기 */
		$("#reviewInsert").on("click", function(e) {
			console.log("reviewInsert()");
			
			var reviewSeqData = "12345";
			var storeSeqData = "2021/04/2364420dadcc1e40648ecd785ec9fcc8a8"; // reviewFk 상품/집들이/q&a fk값
			var memberIdData = "yeonsu_review_test";
			var divData = "0";        // div 리뷰 구분(상품:0, 집들이:1, q&a:2)
			
			let url = "${hContext}/review/do_insert.do";
			let parameter = {
								"reviewSeq" : reviewSeqData,
								"memberId"  : memberIdData,
								"reviewFk"  : storeSeqData,
								"div"       : divData,
								"contents"  : $("#reviewContents").val(),
								"modId"     : memberIdData
							};
			let method = "POST";
			let async = true;

			if(confirm("리뷰를 등록하시겠습니까?")==false) return;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
    			console.log("data:"+data.msgContents);	
    			if(data.msgId=="1") { //등록 성공
        			alert(data.msgContents);   
    			} else { //등록 실패
        			alert(data.msgId+ "\n" +data.msgContents);
    			}
	    	});
			
			reviewRetrieve();
			
		});
		
		/* 리뷰 */
		function reviewRetrieve() {
			console.log("reviewRetrieve()");
			
			var reviewFkData = "2021/04/2364420dadcc1e40648ecd785ec9fcc8a8";
			
	      	$.ajax({
	    		type: "POST",
	    		url:"${hContext}/review/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
		    			"reviewFk" : reviewFkData
	    		},
	    		success:function(data){//통신 성공
	        		console.log("success data : " + data);
	    		
	        		var parseData = JSON.parse(data);
	        		
	        		//기존 데이터 삭제
	        		$("#reviewList").empty();
	        		
	        		var html = "";
	        		
	        		console.log("parseData.length : " + parseData.length);

	        		//데이터가 있는 경우
	        		if(parseData.length > 0) {

	        			$.each(parseData, function(i, value) {
	        				console.log(i+","+value.contents);
	        				
	        				//{"reviewSeq":"1","memberId":"tjdus","reviewFk":"1","div":"0","contents":"test1_up","regDt":"2021/04/23",
	        				//"modId":"tjdus1","modDt":"2021/04/23","num":1,"totalCnt":3,"totalScrap":0,"totalReview":0,"avgStar":0.0}
	        				// reviewFk 상품/집들이/q&a fk값
							// div 리뷰 구분(상품:0, 집들이:1, q&a:2)

	        				html += "<div class='blog__details__comment__item'>              ";
	        				html += "    <div class='blog__details__comment__item__pic'>     ";
	        				html += "        <img src='' alt=''>                             ";
	        				html += "    </div>                                              ";
	        				html += "    <div class='blog__details__comment__item__text'>    ";
	        				html += "        <h6>"+value.memberId+"</h6>                     ";
	        				html += "        <span>"+value.regDt+"</span>                    ";
	        				html += "        <span>"+value.contents+"</span>                 ";
	        				html += "    </div>                                              ";
	        				html += "</div>                                                  ";
            
	        			});
	        			
	        			 $("#reviewList").append(html);
	        			
	        		}
	        		
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
		}
		
		/* 문의 */
		function qnaRetrieve() {
			console.log("qnaRetrieve()");
			
		}
		
		/* 배송/환불 */
		function refundRetrieve() {
			console.log("refundRetrieve()");
			
		}
		
		/* 콤마 제거 */
		function removeComma(str) {
			n = parseInt(str.replace(/,/g,""));
			return n;
		}
		
		/* 숫자 3자리 콤마찍기 */
		function numberWithCommas(x) {
		    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
		

	</script>
</body>
</html>