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
<%@ include file="../cmn/common.jsp" %>
<%@ include file="../cmn/header.jsp"%>
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
    <script src="${hContext}/resources/js/jquery.bootpag.js"></script>  
</head>
<body>
<%-- ${sessionScope.member } --%>
    <!-- 스토어 단건 조회 -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="product__details__img">
                        <div class="product__details__big__img">
                        <c:choose>
		            		<c:when test="${imageList.size() >0 }">
		            			<c:forEach var="vo" items="${imageList}">
		            				<c:if test="${vo.imgNum==1}">
		            					<img class="big_img" src="${hContext}${imagePath}" alt="">
		            				</c:if>
		            			</c:forEach>
		            		</c:when>
		            	</c:choose>  
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
                        <h4 id="title">${vo.title}</h4>
                        <h4 id="storeSeq" style="display: none;">${vo.storeSeq}</h4>
                        <h4 id="memberId" style="display: none;">${vo.memberId}</h4>
                        <h6><span id="totalReviews">${vo.totalReview}</span> 개 리뷰</h6>
                        <br/>
                        <h5><span id="price">${vo.price}</span>원</h5>
                        <ul>
                            <li>브랜드: <span>${vo.company}</span></li>
                            <li>카테고리: <span>${vo.category}</span></li>
                            <li>태그: <span></span>${vo.tag}</li>
                        </ul>
						<div class="row mt-4">
							<div class="col-md-6">
					              <div class="select-wrap">
				                 	 <div class="icon"><span class="ion-ios-arrow-down"></span></div>
				                 	 <select name="optone" id="optone" class="form-control">           
				                  		<option value="1" disabled selected hidden>색상</option>
	        				 		</select>                                                      
	        				    </div>                                            
	        				    <br/>        
					            <div class="select-wrap">
				                  <div class="icon"><span class="ion-ios-arrow-down"></span></div>
				                  <select name="opttwo" id="opttwo" class="form-control">
				                  	<option value="2" disabled selected hidden>사이즈</option>
				                  </select>
				                </div>
					    	</div>
					    	</div>
					    </div>
					    <br/>
			            <div class="product__details__option">
                        	<div class="quantity">
                                <div class="pro-qty">
                                	<span class="dec qtybtn">-</span>                                                
									<input type="text" value="1" id="quantity">                                                
									<span class="inc qtybtn">+</span>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="product__details__text">
                            <h5>주문금액 <span id="total"></span>원</h5>
                            <a class="primary-btn" onClick="addBasket();">장바구니</a>
                            <a class="primary-btn" onClick="buyNow('${vo.storeSeq}');">바로구매</a>
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
                    <br/>
                    <div class="tab-content">
                        <div class="tab-pane" id="descriptionTab" role="tabpanel">
                            <div class="row d-flex justify-content-center">
                                <div class="col-lg-8">
                                <h5><span id="productContents">${vo.contents}</span></h5>
                                </div>
                            </div>
                        </div>
                        <br/>
                    <div class="tab-pane" id="reviewTab" role="tabpanel">
                		<div class="container">
		                    <div class="row d-flex justify-content-center">
		                        <div class="col-lg-4 col-md-7">
		                            <div class="map__inner">
		                                <h6>리뷰 <span id="totalReview">${vo.totalReview}</span></h6>
		                                <h6>평점 <span id="avgStar">${vo.avgStar}</span></h6>
		                            </div>
		                        </div>
		                    </div>
                            <div class="container">
					        	<label for="content">comment</label>
					        	<div class="rating">
					                <!-- 해당 별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
					                <input type="checkbox" name="rating" id="rating1" value="1" class="rate_radio" title="1점" onclick='getCheckboxValue(event)'>
					                <label for="rating1"></label>
					                <input type="checkbox" name="rating" id="rating2" value="2" class="rate_radio" title="2점" onclick='getCheckboxValue(event)'>
					                <label for="rating2"></label>
					                <input type="checkbox" name="rating" id="rating3" value="3" class="rate_radio" title="3점" onclick='getCheckboxValue(event)'>
					                <label for="rating3"></label>
					                <input type="checkbox" name="rating" id="rating4" value="4" class="rate_radio" title="4점" onclick='getCheckboxValue(event)'>
					                <label for="rating4"></label>
					                <input type="checkbox" name="rating" id="rating5" value="5" class="rate_radio" title="5점" onclick='getCheckboxValue(event)'>
					                <label for="rating5"></label>
				           		</div>
				            	<input type="hidden" id="starRating">
						        <form name="commentInsertForm">
						            <div class="input-group">
						               <%-- <input type="hidden" name="bno" value="${detail.bno}"/> --%>
						               <input type="text" class="form-control" id="reviewContents" name="reviewContents" placeholder="평점과 이용경험을 남겨주세요.">
						               <span class="input-group-btn">
						                    <button class="btn btn-default" type="button" onClick="reviewInsert('${vo.storeSeq}');">등록</button>
						               </span>
						              </div>
						        </form>
						   </div>
						   <br/>
						   <!-- 페이징 선택 -->	        
						   <div class="col-xs-8 col-sm-9 col-md-8 col-lg-2 pull-right">
					    		<select class="form-control input-sm" name="pageSize" id="pageSize">
						    		<option value="4">4개씩 보기</option>	    		  		
									<option value="8">8개씩 보기</option>
									<option value="12">12개씩 보기</option>
						    	</select>
						    </div>	
						    <!-- //페이징 선택 -->
						    <br/>
						    <br/>
						    <div class="container">
						        <div id="reviewList" class="reviewList">
						    	
						    	</div>
						    	<!-- pagenation -->
								<div class="text center">
									<div id="page-selection" class="text-right page">
									
									</div>
								</div>
								<!--// pagenation -->
						    </div>
						    <!-- // 댓글  -->		
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
                                	<h5><span id="refund">${vo.refund}</span></h5>
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
            <div style="text-align: center;">
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
			//storeSelectOne(); //스토어 단건 조회
			qtyChange();
			optionRetrieve();	
		});
		
		$("#pageSize").on("change", function(e){//change: SelectBox의 이벤트
	    	console.log("pageSize change");
	    	reviewRetrieve(1);
		});
		
		/* 수량, 주문 금액 변경 */
		function qtyChange() {
			console.log("qtyChange()");
			
			var price = $("#price").text();
			var total = price * $("#quantity").val(); //합계
			//console.log("total1:"+total);	
		
			var proQty = $(".pro-qty");
			proQty.on("click", ".qtybtn", function () {
			    var $button = $(this);
			    var oldValue = $button.parent().find("input").val();
			    if ($button.hasClass("inc")) {
			        var newVal = parseFloat(oldValue) + 1;
			    } else {
			        if (oldValue > 1) {
			            var newVal = parseFloat(oldValue) - 1;
			        } else {
			            newVal = 1;
			        }
			    }
			    $button.parent().find("input").val(newVal);
			    
			    total = price * newVal; //수량 변경 후 합계
				console.log("sell total:"+total);	
				
		    	$("#total").text(numberWithCommas(total));

			});
			
	    	$("#price").text(numberWithCommas(price));
	    	$("#total").text(numberWithCommas(total));

		}
		
		/* 옵션 선택 */
		function optionRetrieve() {
			console.log("optionRetrieve()");
		
			var storeSeqData = "2021/04/2364420dadcc1e40648ecd785ec9fcc8a8";
			var divData = "1";
			
	      	$.ajax({
	    		type: "GET",
	    		url:"${hContext}/opt/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
		    			"storeSeq" : $("#storeSeq").text(), 
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
		function addBasket() {
			console.log("addBasket btn click");
			console.log("optone : "+$("#optone option:selected").val());
			
/* 			if($("#optone option:selected").val()==1) {
				alert("옵션을 선택하세요.");
				$("#optone").focus();
				return;
			}
			 */

			var memberIdData = "${member.memberId}";
			var shipfeeData = "3000";
			
			let url = "${hContext}/basket/do_insert.do";
			let parameter = {
								"storeSeq"  : $("#storeSeq").text(),
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
    			
        			if(confirm("장바구니로 이동하시겠습니까?")==false) return;
    				window.location.href = "${hContext}/member/basket_list.do?memberId="+memberIdData;
    			
    			} else { //등록 실패
        			alert(data.msgId+ "\n" +data.msgContents);
    			}
	    	});
		}
		
		/* 바로구매 */
		function buyNow(storeSeq) {
			console.log("buyNow btn click");
			
			window.location.href = "${hContext}/qna/payment.do?storeSeq="+storeSeq;
			
		}
		
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
		 		    reviewRetrieve(1);
		         } else if (contentId=="#qnaTab") {
		        	 qnaRetrieve();
		        	 $("#reviewList").empty();
			     } else if (contentId=="#refundTab") {
			    	 refundRetrieve();
			    	 $("#reviewList").empty();
			     }
		         
		    } 

		});
		
		/* 별점 */
		function Rating(){};
		Rating.prototype.rate = 0;
		Rating.prototype.setRate = function(newrate){
		    //별점 마킹 - 클릭한 별 이하 모든 별 체크 처리
		    this.rate = newrate;
		    let items = document.querySelectorAll('.rate_radio');
		    items.forEach(function(item, idx){
		        if(idx < newrate){
		            item.checked = true;
		        }else{
		            item.checked = false;
		        }
		    });
		}
		
		let rating = new Rating();

		document.addEventListener('DOMContentLoaded', function(){
		    //별점 선택 이벤트 리스너
		    document.querySelector('.rating').addEventListener('click',function(e){
		        let elem = e.target;
		        if(elem.classList.contains('rate_radio')){
		            rating.setRate(parseInt(elem.value));
		            
					console.log("star rating : "+elem.value);
					

		        }
		    })
		});

		/* 별점 value */
		function getCheckboxValue(event)  {
			let result = '';
			if (event.target.checked)  {
				result = event.target.value;
			} else {
				result = '';
			}
			  
			document.getElementById('starRating').innerText = result;
			console.log("starRating : "+result);

		}
		
		/* 리뷰 등록 */
		function reviewInsert(storeSeq) {
			console.log("reviewInsert()");
			
			if ($('input[name=rating]').is(":checked")) {

			} else {
				alert("평점을 선택하세요.");
				$("#rating").focus();
				return;
			}
			
			if(eUtil.ISEmpty($("#reviewContents").val())==true) {
				alert("내용을 입력하세요.");
				$("#reviewContents").focus();
				return;
			}
			
			var star = $("#starRating").text();
			console.log("star : " + star);
			
			var divData = "0";        // div 리뷰 구분(상품:0, 집들이:1, q&a:2)
			
			let url = "${hContext}/review/do_insert.do";
			let parameter = {
								"memberId"  : "${member.memberId}",
								"reviewFk"  : storeSeq,
								"div"       : divData,
								"contents"  : $("#reviewContents").val(),
								"storeSeq"  : storeSeq,
								"starScore" : star
							};
			let method = "POST";
			let async = true;

			if(confirm("리뷰를 등록하시겠습니까?")==false) return;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
    			console.log("data:"+data.msgContents);	
    			if(data.msgId=="1") { //등록 성공
        			alert(data.msgContents);   
        			reviewRetrieve(1);
    			} else { //등록 실패
        			alert(data.msgId+ "\n" +data.msgContents);
    			}
	    	});
			
			$("#reviewContents").val(""); //text 초기화
			reviewRetrieve(1);
			
		}

		/* 리뷰 목록 조회 */
		function reviewRetrieve(page) {
			console.log("reviewRetrieve()");
						
	      	$.ajax({
	    		type: "GET",
	    		url:"${hContext}/review/review_star_list.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			    "pageSize" : $("#pageSize").val(),
	    			    "pageNum"  : page,
	    				"reviewFk" : $("#storeSeq").text()
	    			},
	    		success:function(data){//통신 성공
	        		console.log("success data : " + data);
	    		
	        		var parseData = JSON.parse(data);
	        		
	        		//기존 데이터 삭제
	        		$("#reviewList").empty();
	        		
	        		var html = "";
	        		
	        		console.log("parseData.length : " + parseData.length);

	        		let totalCount = parseData.length;
	    			let pageTotal = 1;

	        		//데이터가 있는 경우
	        		if(parseData.length > 0) {
	        			console.log("totalCount:"+totalCount);
		    			
	        			pageTotal = (totalCount/$("#pageSize").val());//42/10->4.2
	        			console.log("pageTotal:"+pageTotal);
	        			
	        			pageTotal = Math.ceil(pageTotal);//42/10->4.2->5
	        			console.log("pageTotal:"+pageTotal);
	        			
	        			$.each(parseData, function(i, value) {
	        				//console.log(i+","+value.contents);
	        				console.log(i+","+value.reviewSeq);
	        				console.log(i+","+value.starScore);
	        				console.log(i+","+value.totalReviews);
	        				console.log(i+","+value.avgStars);
	        				
	        				//{"reviewSeq":"1","memberId":"tjdus","reviewFk":"1","div":"0","contents":"test1_up","regDt":"2021/04/23",
	        				//"modId":"tjdus1","modDt":"2021/04/23","num":1,"totalCnt":3,"totalScrap":0,"totalReview":0,"avgStar":0.0}
	        				// reviewFk 상품/집들이/q&a fk값
							// div 리뷰 구분(상품:0, 집들이:1, q&a:2)
                                                                                                               
	        				html += " <div class='row'>                                                                   ";
	        				html += "    <div class='blog__details__comment__item__text'>                                 ";
	        				html += "        <h6>"+value.memberId+"</h6>                                                  ";
	        				html += "        <span>"+value.modDt+"</span>                                                 ";
	        				html += "        <span id='contents'>"+value.contents+"</span>                                ";
	        				html += "    </div>                                                                           ";
	        				html += "	<div class='blog__details__comment__btns '>                                       ";
	        				html += "        <span id='star'>("+value.starScore+"점)</span>                               ";
	        				html += "        <a href='javascript:reviewUpdate(&quot;"+value.reviewSeq+"&quot;);'>수정</a>  ";
	        				html += "        <a href='javascript:reviewDelete(&quot;"+value.reviewSeq+"&quot;);'>삭제</a>  ";
	        				html += "    </div>                                                                           ";
	        				html += " </div>                                                                              ";
            
	        				$("#totalReview").text(value.totalReviews);
	        				$("#totalReviews").val(value.totalReviews);
	        				$("#avgStar").text(value.avgStars);
	        				
	        			});
	        			
	        	} else {//data가 없는 경우
        			html += "	<div class='blog__details__comment__item__text'>           ";
        			html += "        <span>등록된 리뷰가 없습니다. 리뷰를 등록해주세요 :)</span>       ";
        			html += "    </div>                                                    ";
           				
	        	}
	        			
	        		$("#reviewList").append(html);
	        			
		    		console.log("pageTotal:"+pageTotal);
		    		console.log("page:"+page);
		    			
		    		//페이징 처리: 총 페이지, 현재글
		    		renderingPage(pageTotal,page);
	        		
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
		}
		
		//paging
		//pageTotal:총 페이지수= 총글수/페이지사이즈(10)
		//page:현재페이지
		//maxVisible:bottom 페이지
		function renderingPage(pageTotal,page){
			//이전 연결된 Event 핸들러 요소에서 제거
			$("#page-selection").unbind('page');
			$("#page-selection").bootpag({
			    total: pageTotal,
			    page: page,//시작
			    maxVisible: 10,
			    leaps: true,
			    firstLastUse: true,
			    first: '←',
			    last: '→',
			    wrapClass: 'pagination',
			    activeClass: 'active',
			    disabledClass: 'disabled',
			    nextClass: 'next',
			    prevClass: 'prev',
			    lastClass: 'last',
			    firstClass: 'first' 
			}).on("page", function(event, num){
				reviewRetrieve(num);//ajax 서버 호출
			}); 
		}//--renderingPage	
			      	
		/* 리뷰 수정 */
		function reviewUpdate(reviewSeq) {
			console.log("reviewUpdate()");
						
			let url = "${hContext}/review/do_update.do";
			let parameter = {
								"reviewSeq" : reviewSeq,
								"contents"  : $("#contents").val(),
								"modId"     : "${member.memberId}"
							};
			let method = "POST";
			let async = true;
			
			if(confirm("리뷰를 수정하시겠습니까?")==false) return;

 			EClass.callAjax(url, parameter, method, async, function(data) {
    			console.log("data:"+data);
    			console.log("data:"+data.msgContents);	
    			if(data.msgId=="1") { //수정 성공
        			alert(data.msgContents);   
        			doRetrieve(1);
    			} else { //수정 실패
        			alert(data.msgId+ "\n" +data.msgContents);
    			}
	    	});
			
		}
		
		/* 리뷰 삭제 */
		function reviewDelete(reviewSeq) {
			console.log("reviewDelete()");
			
			var starSeqData = "1234567890";
			
			let url = "${hContext}/review/do_delete.do";
			let parameter = {
								"reviewSeq" : reviewSeq,
								"starSeq"   : starSeqData
							};
			let method = "POST";
			let async = true;
			
			if(confirm("리뷰를 삭제하시겠습니까?")==false) return;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
    			console.log("data:"+data);	
    			console.log("data:"+data.msgContents);	
    			if(data.msgId=="1") { //삭제 성공
        			alert(data.msgContents);   
        			reviewRetrieve(1);
    			} else { //삭제 실패
        			alert(data.msgId+ "\n" +data.msgContents);
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
		
		/* 이미지 선택시 event */
	    $('.product__details__thumb img').on('click', function () {
	        $('.product__details__thumb .pt__item').removeClass('active');
	        $(this).addClass('active');
	        var imgurl = $(this).data('imgbigurl');
	        var bigImg = $('.big_img').attr('src');
	        if (imgurl != bigImg) {
	            $('.big_img').attr({
	                src: imgurl
	            });
	        }
	    });
		
	</script>
</body>
</html>