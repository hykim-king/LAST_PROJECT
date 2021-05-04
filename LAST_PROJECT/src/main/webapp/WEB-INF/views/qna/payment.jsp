<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  ---------------------------------------------------
    2021. 4. 26.       	임하람 
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../cmn/common.jsp" %>
<%@ include file="../cmn/header.jsp" %>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

	<title>결제</title>
	
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
  	<script src="${hContext}/resources/js/jquery.min.js"></script>
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
	<script src="${hContext}/resources/js/eutil.js"></script>
	<script src="${hContext}/resources/js/jquery.bootpag.js"></script>
	
</head>
<body>
${vo}
<!-- 결제 데이터 가져오기 -->
		<!-- 제목 -->
	 	<div class="page-header">
	 		<h2>결제</h2>
	 	</div>
	    <!--// 제목 -->
	    
	 <!-- Shopping Payment Section Begin -->
    <section class="shopping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="cart-table">
                        <table id="cartTable">
                            <thead>
                                <tr>
                                	<th style="visibility:hidden;position:absolute;">paySeq</th>
                                	<th style="visibility:hidden;position:absolute;">${vo.storeSeq}</th>
                                	<th style="visibility:hidden;position:absolute;">${member.memberId}</th>
                                    <th>상품명</th>
                                    <th>옵션 1</th>
                                    <th>옵션 2</th>
                                    <th>수량</th>
                                    <th>가격</th>
                                    <th>배송비</th>
                                    <th>합계</th>
                                    <!--<th>결제상태</th>  -->
                                    <th style="visibility:hidden;position:absolute;">${member.memberId}</th>
                                    <th style="visibility:hidden;position:absolute;">${vo.regDt}</th>
                                    <th style="visibility:hidden;position:absolute;">${vo.modDt}</th>
                                   
                                </tr>
                            </thead>
                            <tbody>
                            	<tr>                                                                                                       
								    <td style='visibility:hidden;position:absolute;'>paySeq</td>                                  
								    <td style='visibility:hidden;position:absolute;'>${vo.storeSeq}</td>                                
								    <td style='visibility:hidden;position:absolute;'>${member.memberId}</td>                                
								    <td class='cart-title first-row' id='title'>${vo.title}</td>                                        
								    <td class='cart-option first-row' id='optone'>${vo.optone}</td>                                     
								    <td class='cart-option first-row' id='opttwo'>${vo.opttwo}</td>                                     
								    <td class='p-price first-row'>${vo.quantity}</td>                                                                                                  
								    <td class='p-price first-row' id='price'>${vo.price}원</td>                    
								    <td class='p-shipfee first-row' id='shipfee'>${vo.shipfee}원</td>
								    <td class='p-price first-row'>${vo.quantity*vo.price} 원</td>                                        
								    <!--<td class='p-status first-row' id='status'>status</td>-->                       				
								    <td style='visibility:hidden;position:absolute;'>${member.memberId}</td>                                   
								    <td style='visibility:hidden;position:absolute;'>${vo.regDt}</td>                                   
								    <td style='visibility:hidden;position:absolute;'>${vo.modDt}</td>                                   
								</tr>   
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                           <div class="col-lg-4 offset-lg-4 pull-right">
                            <div class="proceed-checkout">
                                <ul>
                                    <li class="subtotal text-left">총 상품금액<span id="totalPrice">${vo.quantity*vo.price}</span></li>
                                    <li class="subtotal text-left">총 배송비 <span id="totalShip">${vo.shipfee}</span></li>
                                    <li class="cart-total text-left">결제금액 <span id="totalPay">${(vo.quantity*vo.price)+vo.shipfee}</span></li>
                                </ul>
                                <a href="#" class="proceed-btn" id="paymentBtn">상품 결제하기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Payment Section End -->

	<!-- javascript -->
	<script type="text/javascript">
	//jquery 객체생성이 완료
	$(document).ready(function() {
		console.log("document ready");
		
	});//--document ready	
	
	
	
	//결제버튼
	$("#paymentBtn").on("click",function(e){
		console.log("paymentBtn");
		e.preventDefault();//한번만 호출
		
		 let url = "${hContext}/payment/do_insert.do";
		 let parameters = {
							"storeSeq"  : "${vo.storeSeq}",
							"memberId"  : "${member.memberId}",
							"title"     : "${vo.title}",
							"optone"    : "${vo.optone}",
							"opttwo"    : "${vo.opttwo}",
							"quantity"  : "${vo.quantity}",
							"price"     : "${vo.price}",
							"shipfee"   : "${vo.shipfee}",
							"status"	:  1,
							"modId"		: "${member.memberId}"
						};
		 let method = "POST";
		 let async = true;	
		 
		console.log("parameters:"+parameters);
		console.log("url:"+url);
		 
		 if(confirm("결제 하시겠습니까?")==false) return;
		 
		 EClass.callAjax(url, parameters, method, async, function(data) {
				 console.log("data:"+data);
				 
				 //성공/실패 여부 메세지 출력
				 console.log("data.msgContents:"+data.msgContents);
				 
				 if("1"==data.msgId){//결제성공
					 alert(data.msgContents);
						//결제성공시 화면이동
						moveTomain();
				 }else{//결제실패
					 alert(data.msgId+"\n"+data.msgContents); 
				 }
				 
			 }); 
	 	
	});//--paymentBtn
	
	
	//결제 성공시 화면이동(해창님-Community_Home으로 이동)
 	function moveTomain(){
		console.log("moveTomain");
		
		window.location.href = "${hContext}/houses/home_view.do?memberId=${member.memberId}";
	} 
	
/* 	//결제 취소
		$("#cancelBtn").on("click",function(e){
			console.log("cancelBtn");

			e.preventDefault(); 
			
			 if(confirm("결제를 취소하시겠습니까?")==false) return;
			 

			 let url = "${hContext}/payment/do_delete.do";
			 let parameters = {
					 			"paySeq" : "${payment.paySeq}"};
			 let method = "POST";
			 let async = true;	
			 
			console.log("parameters:"+parameters);
			console.log("url:"+url);
			 
			 
			 EClass.callAjax(url, parameters, method, async, function(data) {
					 console.log("data:"+data);
					 console.log("data:"+data.paySeq);
					 
					 //성공/실패 여부 메세지 출력
					 console.log("data.msgContents:"+data.msgContents);
					 
					 if("1"==data.msgId){//취소성공
						 alert(data.msgContents);
							//취소성공시 화면이동
							moveTomain();
					 }else{//취소실패
						 alert(data.msgId+"\n"+data.msgContents); 
					 }
					 
				 });
			
		});	//--cancelBtn */

			
	/* 숫자 3자리 콤마찍기 */
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}		
	

	</script>
	<!-- //javascript -->
</body>
</html>