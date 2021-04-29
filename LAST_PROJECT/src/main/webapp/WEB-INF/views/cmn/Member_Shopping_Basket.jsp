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
	<title>장바구니</title>
	
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${hContext}/resources/css/store-cart.style.css" rel="stylesheet">
    <link href="${hContext}/resources/css/themify-icons.css" rel="stylesheet">
    <link href="${hContext}/resources/css/elegant-icons.css" rel="stylesheet">

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
    <!-- Shopping Basket Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="cart-table">
                        <table id="cartTable">
                            <thead>
                                <tr>
                                	<th style="visibility:hidden;position:absolute;">basketSeq</th>
                                    <th>이미지</th>
                                    <th>상품명</th>
                                    <th>옵션 1</th>
                                    <th>옵션 2</th>
                                    <th>가격</th>
                                    <th>수량</th>
                                    <th>합계</th>
                                    <th><i class="ti-close"></i></th>
                                </tr>
                            </thead>
                            <tbody>  
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="cart-buttons">
                                <a href="#" class="primary-btn continue-shop">쇼핑 계속하기</a>
                                <input type="button" class="primary-btn up-cart" value="수량 변경하기" id="addUpdate">
                            </div>
                        </div>
                        <div class="col-lg-4 offset-lg-4 pull-right">
                            <div class="proceed-checkout">
                                <ul>
                                    <li class="subtotal text-left">총 상품금액 <span id="totalPrice"></span></li>
                                    <li class="subtotal text-left">총 배송비 <span id="totalShip"></span></li>
                                    <li class="cart-total text-left">결제금액 <span id="totalPay"></span></li>
                                </ul>
                                <a href="#" class="proceed-btn">상품 구매하기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Basket Section End -->
	
    <script type="text/javascript">
    
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			doRetrieve(1);
		});
		
		//paging
		//pageTotal  : 총 페이지수 : 총글수/페이지사이즈(10)
		//page       : 현재 페이지
		//maxVisible : bottom에 보일 페이지 수
		function renderingPage(pageTotal, page) {
			//이전 연결된 Event 핸들러 요소에서 제거
			$("#page-selection").unbind('page');
			
			$("#page-selection").bootpag({
			    total: pageTotal,            //총글수       
			    page: page,                  //시작 페이지
			    maxVisible: 10,              //bottom에 보일 페이지 수
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
				doRetrieve(num); // ajax 서버 호출
			}); 
		}
		
		function doRetrieve(page) {
	      	$.ajax({
	    		type: "GET",
	    		url:"${hContext}/basket/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			pageSize: $("#pageSize").val(),
	    			searchDiv: $("#searchDiv").val(),
	    			searchWord: $("#searchWord").val(),
	    			pageNum: page	
	    		},
	    		success:function(data){//통신 성공
	        		console.log("success data : " + data);
	    		
	        		var parseData = JSON.parse(data);
	        		
	        		//기존 데이터 삭제
	        		$("#cartTable>tbody").empty();
	        		var html = "";
	        		
	        		console.log("parseData.length : " + parseData.length);

	        		//총 글 수
	        		//총 페이지 수 : 총글수/페이지사이즈
	        		let totalCount = 0;
	        		let pageTotal = 1;

	        		//데이터가 있는 경우
	        		if(parseData.length > 0) {
	        			totalCount = parseData[0].totalCnt;
	        			pageTotal = (totalCount/$("#pageSize").val()); //42/10 = 4.2 
	        			pageTotal = Math.ceil(pageTotal);              //-> 5 페이지
	        			
	        			$.each(parseData, function(i, value) {
	        				console.log(i+","+value.title);
	        				html += "<tr>                                                                                                           ";
	        				html += "    <td style='visibility:hidden;position:absolute;'>"+value.basketSeq+"</td>                                            ";
	        				html += "    <td class='cart-pic first-row'><img src='${hContext}/resources/img/cart-page/product-1.jpg' alt=''></td>   ";
	        				html += "    <td class='cart-title first-row' id='title'>"+value.title+"</td>                                           ";
	        				html += "    <td class='cart-option first-row' id='optone'>"+value.optone+"</td>                                        ";
	        				html += "    <td class='cart-option first-row' id='opttwo'>"+value.opttwo+"</td>                                        ";
	        				html += "    <td class='p-price first-row' id='price'>"+numberWithCommas(value.price)+"원</td>                           ";
	        				html += "    <td class='qua-col first-row'>                                                                             ";
	        				html += "        <div class='quantity'>                                                                                 ";
	        				html += "            <div class='pro-qty'>                                                                              ";
	        				html += "            	 <span class='dec qtybtn'>-</span>                                                              ";
	        				html += "                <input type='text' id='quantity' value="+value.quantity+">                                     ";
	        				html += "            	 <span class='inc qtybtn'>+</span>                                                              ";
	        				html += "            </div>                                                                                             ";
	        				html += "        </div>                                                                                                 ";
	        				html += "    </td>                                                                                                      ";
	        				html += "    <td class='p-price first-row'>"+numberWithCommas(value.quantity*value.price)+"원</td>                       ";
	        				html += "    <td class='close-td first-row'><i class='ti-close'></i></td>                             ";
	        				html += "</tr>                                                                                                          ";
	        			});
	        			
	        		} else { //데이터가 없는 경우
	        			html += "<tr>                                                           ";
	        			html += "	<td class='text-center' colspan='99'>장바구니가 비었습니다.</td>  ";
	        			html += "</tr>                                                          ";
	        		}
	        		
	        		$("#cartTable>tbody").append(html); //데이터 추가
	    			
	        		//페이징 : 총 페이지, 현재 글번호
	        		console.log(pageTotal+","+page);
	        		renderingPage(pageTotal, page);
	        			        		
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
		}
		
		$("#addUpdate").on("click", function(e) {
			console.log("addUpdate btn click");
			e.preventDefault(); 
			
			var basketSeqData = "2021/04/2664420dadcc1e40648ecd785ec9fcc8a8";
			var storeSeqData = "2021/04/2364420dadcc1e40648ecd785ec9fcc8a8";
			var memberIdData = "test01";
			var modIdData = "test01";
			var shipfeeData = "3000";
			
			let url = "${hContext}/basket/do_update.do";
			let parameter = {
								"basketSeq" : basketSeqData,
								"storeSeq"  : storeSeqData,
								"memberId"  : memberIdData,
								"title"     : $("#title").text(),
								"optone"    : $("#optone").text(),
								"opttwo"    : $("#opttwo").text(),
								"quantity"  : $("#quantity").val(),
								"shipfee"   : shipfeeData,
								"price"     : $("#total").val(),
								"modId"     : modIdData
							};
			let method = "POST";
			let async = true;
			
			if(confirm("수량을 변경하시겠습니까?")==false) return;

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
		});
		
		$("#cartTable>tbody").on("click", "tr", function(){
			var basketSeqData =  $(this).find("td:eq(0)").text();
			console.log("basketSeqData:"+basketSeqData);
			
			$(this).find("td:eq(8)").on("click", function(e) { /* 삭제 */
				deleteBasket(basketSeqData);
			});
			
			$(this).find("td:eq(6)").on("click", function(e) { /* 수량 변경 */
				var qty = $("#quantity").val();
				console.log("qty:"+qty);

			    var proQty = $(".pro-qty");
				proQty.on("click", ".qtybtn", function () {
					var $button = $(this);
					var oldValue = $button.parent().find("input").val();
					console.log("oldValue:"+oldValue);

					if ($button.hasClass("inc")) {
						var newVal = parseFloat(oldValue) + 1;
					} else {
						if (oldValue > 0) {
							var newVal = parseFloat(oldValue) - 1;
						} else {
							newVal = 0;
						}
					}
					console.log("newVal:"+newVal);

					qty = $button.parent().find("input").val(newVal);
				});

				
			});
			
			
		});
		
		/* 삭제 */
		function deleteBasket(basketSeq) {
			console.log("deleteBasket()");
			
			let url = "${hContext}/basket/do_delete.do";
			let parameter = {
								"basketSeq" : basketSeq
							};
			let method = "POST";
			let async = true;
			
			if(confirm("장바구니에서 삭제하시겠습니까?")==false) return;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
    			console.log("data:"+data);	
    			console.log("data:"+data.msgContents);	
    			if(data.msgId=="1") { //삭제 성공
        			alert(data.msgContents);   
        			doRetrieve(1);
    			} else { //삭제 실패
        			alert(data.msgId+ "\n" +data.msgContents);
    			}
	    	});
		}
		
		/* 숫자 3자리 콤마찍기 */
		function numberWithCommas(x) {
		    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}

		
	</script>
   
</body>
</html>