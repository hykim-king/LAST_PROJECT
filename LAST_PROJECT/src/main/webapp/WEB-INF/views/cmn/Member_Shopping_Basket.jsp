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

    <!-- 장바구니 -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="cart-table">
                        <table id="cartTable">
                            <thead>
                                <tr>
                                	<th style="visibility:hidden;position:absolute;">basketSeq</th>
                                    <th>상품명</th>
                                    <th>옵션 1</th>
                                    <th>옵션 2</th>
                                    <th>가격</th>
                                    <th>수량</th>
                                    <th>합계</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>  
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="cart-buttons">
                                <a href="#" class="primary-btn continue-shop" id="continueShop">쇼핑 계속하기</a>
                                <!-- <input type="button" class="primary-btn up-cart" value="수량 변경하기" id="addUpdate"> -->
                            </div>
                        </div>
<!--                         <div class="col-lg-4 offset-lg-4 pull-right">
                            <div class="proceed-checkout">
                                <ul>
                                    <li class="subtotal text-left">총 상품금액 <span id="totalPrice"></span></li>
                                    <li class="subtotal text-left">총 배송비 <span id="totalShip"></span></li>
                                    <li class="cart-total text-left">결제금액 <span id="totalPay"></span></li>
                                </ul>
                                <a href="#" class="proceed-btn">상품 구매하기</a>
                            </div>
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--// 장바구니 -->
	
    <script type="text/javascript">
    
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			doRetrieve();
		});
		
		/* 장바구니 목록 조회 */
		function doRetrieve() {
			
			var memberIdData = "${member.memberId}";
			
	      	$.ajax({
	    		type: "POST",
	    		url:"${hContext}/basket/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			"memberId" : memberIdData
	    		},
	    		success:function(data){//통신 성공
	        		console.log("success data : " + data);
	    		
	        		var parseData = JSON.parse(data);
	        		
	        		//기존 데이터 삭제
	        		$("#cartTable>tbody").empty();
	        		var html = "";
	        		
	        		console.log("parseData.length : " + parseData.length);

	        		//데이터가 있는 경우
	        		if(parseData.length > 0) {

	        			$.each(parseData, function(i, value) {
	        				console.log(i+","+value.title);
	        				console.log(i+","+value.storeSeq);
	        				console.log(i+","+value.basketSeq);
	        				
	        				html += "<tr>                                                                                                                     ";
	        				html += "    <td style='visibility:hidden;position:absolute;'>"+value.basketSeq+"</td>                                            ";
	        				html += "    <td style='visibility:hidden;position:absolute;'>"+value.storeSeq+"</td>                                             ";
	        				html += "    <td class='cart-title first-row' id='title'>"+value.title+"</td>                                                     ";
	        				html += "    <td class='cart-option first-row' id='optone'>"+value.optone+"</td>                                                  ";
	        				html += "    <td class='cart-option first-row' id='opttwo'>"+value.opttwo+"</td>                                                  ";
	        				html += "    <td class='p-price first-row' id='price'>"+numberWithCommas(value.price)+"원</td>                                     ";
	        				html += "    <td class='first-row' id='quantity'>"+value.quantity+"</td>                                                          ";
	        				html += "    <td class='p-price first-row'>"+numberWithCommas(value.quantity*value.price)+"원</td>                                 ";
	        				html += "    <td class='first-row'><a class='up-cart' onclick='openOption(\""+value.storeSeq+"\",\"" + value.basketSeq + "\");'>옵션변경</a></td>         ";
	        				html += "    <td class='first-row'><a class='primary-btn' onclick='buyNow(&quot;"+value.basketSeq+"&quot;)'>바로구매</a></td>         ";
	        				html += "    <td class='close-td first-row' onClick='deleteBasket(&quot;"+value.basketSeq+"&quot;)'><i class='ti-close'></i></td> ";
	        				html += "</tr>                                                                                                                    ";
	        				
	        			});
	        			
	        		} else { //데이터가 없는 경우
	        			html += "<tr>                                                           ";
	        			html += "	<td class='text-center' colspan='99'>장바구니가 비었습니다.</td>  ";
	        			html += "</tr>                                                          ";
	        		}
	        		
	        		$("#cartTable>tbody").append(html); //데이터 추가
	        			        		
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
		}
		
		/* 팝업 창 */
		/* 옵션, 수량 변경 */
	    function openOption(storeSeq, basketSeq){
			console.log("openOption()");
			console.log("storeSeq : "+storeSeq);
			console.log("basketSeq : "+basketSeq);

	    	window.open(
						  "option_pop_up.do?storeSeq="+storeSeq+"&basketSeq="+basketSeq,
						  "option_pop_up_frame",
						  "width=500, height=350"
					   );

	    	if(typeof($("#title"))!="undefined")
	    	{
	    		document.getElementById("title").value = $("#title").text();
	    		document.getElementById("optone").value = $("#optone").text();
	    		document.getElementById("opttwo").value = $("#opttwo").text();
	    		document.getElementById("quantity").value = $("#quantity").text();
				console.log("sendToChild title:"+document.getElementById("title").value);
				console.log("sendToChild optone:"+document.getElementById("optone").value);
				console.log("sendToChild opttwo:"+document.getElementById("opttwo").value);
				console.log("sendToChild quantity:"+document.getElementById("quantity").value);
	    	}
	    		    	
	    }
		
	    /* 상품명 클릭시 상세 페이지로 이동 */
		$("#cartTable>tbody").on("click", "tr", function(){
			var storeSeqData =  $(this).find("td:eq(1)").text();
			console.log("storeSeqData:"+storeSeqData);

			$(this).find("td:eq(2)").on("click", function(e) { 
				
				window.location.href = "${hContext}/store/store_detail.do?storeSeq="+storeSeqData;
				
			});

		});
		
		/* 바로구매 */
		function buyNow(basketSeq) {
			console.log("buyNow()");
			
			window.location.href = "${hContext}/qna/payment.do?basketSeq="+basketSeq;

		}
		
		/* 쇼핑 계속하기 */
		$("#continueShop").on("click", function(e) {
			console.log("continueShop btn click");
			
			window.location.href = "${hContext}/store/store_view.do";
			
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
        			doRetrieve();
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