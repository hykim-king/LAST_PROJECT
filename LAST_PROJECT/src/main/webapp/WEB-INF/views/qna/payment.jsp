<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2021. 4. 26.       	임하람 
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

	<title>PAYMENT</title>
	
	  <!-- 부트스트랩 -->
    <link href="${hContext }/resources/css/bootstrap.min.css" rel="stylesheet">

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
	<!-- table -->
	<table border="1" class="orderinfo">
	         <thead>
	            <tr style="text-align: center;">
	              <!--  <th width="150px;">이미지</th> -->
	               <th width="180px;">상품명 </th>
	               <th width="100px;">판매가</th>
	               <th width="60px;">수량</th>
	               <th width="100px;">합계</th>
	            </tr>
	         </thead>
	         <tbody>
	         
	         <c:choose>
	            <c:when test="">
	               <h1><strong>주문할 상품이 없습니다.</strong></h1>
	            </c:when>
	            <c:when test="">
	         <c:forEach items="" var="cart">
	         <c:set var="pro_saving" value=""  />
	         <fmt:formatNumber value="" type="number" var=""/>
	         <fmt:formatNumber value="" type="number" var=""/>
	            <tr>
	               <th><%-- <a href="${contextPath}/product/productdetail.do?pro_code=${cart.cart_pro_code}"> --%>
	              <!--  <img width="70px" height="105px" src=""></a></th> -->
	               <td><%-- <a href="${contextPath}/product/productdetail.do?pro_code=${cart.cart_pro_code}">${cart.pro_name}</a> --%></td>
	               <td><strong><br></strong></td>
	               <td>원</td>
	               <td>개</td>
	               <td><strong><fmt:formatNumber value="" pattern="" />원</strong></th> 
	            </tr>
	            <c:set var="final_total_order_price" value="" />
	            <c:set var="final_total_order_quantity" value="" />
	             <c:set var="final_total_pro_saving" value=""/> 
	             
	             <input type="hidden" name="" value=" " />
	             <input type="hidden" name="size" value="" />
	             <input type="hidden" name="color"  value=""/>
	              <input type="hidden" name="price" value="" /> 
	             <input type="hidden" name="quantity" value="" />
	             <input type="hidden" name="saving" value="" />
	             <input type="hidden" name="total" value="" /> 
	         </c:forEach>
	         </c:when>
	         </c:choose>
	         </tbody>
	      </table>
	<!-- //table -->
	
  

       <!--11111111111111111111111111-->
		<div class="row">
               <div class="col-md-12 mb-5 mb-md-0">
               <Br><br>
                  <h2 class="h3 mb-3 text-black">상세정보 입력</h2>
                  <div style="border: 1px solid darkgray;" class="p-3 p-lg-5">

                     <!--이름-->
                     <div class="form-group row">
                        <div class="col-md-12">
                           <label for="c_companyname" class="text-black">주문하시는분 
                           <span style="color: red;">*</span></label> 
                           <input type="text" class="form-control" value="${memberInfo.member_name }" id="member_name" />
                        </div>
                     </div>

                     <!--주소-->
                     <div class="form-group row">
                        <div class="col-md-8">
                           <label for="c_address" class="text-black">주소 <span
                              class="text-danger">*</span></label> <input type="text"
                              class="form-control" id="postaddr" placeholder="우편번호"
                              value="${memberInfo.member_addr1 }">
                        </div>

                        <div class="col-md-4">
                           <label for="c_address" class="text-black"><br></label> <input
                              type="button" class="form-control" id="c_address"
                              name="c_address" onclick="find_addr()" value="주소찾기">
                        </div>
                     </div>

                     <div class="form-group">
                        <input type="text" class="form-control" id="loadaddr"
                           placeholder="주소" value="${memberInfo.member_addr2 }">
                     </div>
                     <div class="form-group">
                        <input type="text" class="form-control" id="jiaddr"
                           placeholder="동" value="${memberInfo.member_addr3 }">
                     </div>
                     <div class="form-group">
                        <input type="text" class="form-control" id="detailaddr"
                           placeholder="나머지주소" value="${memberInfo.member_addr4 }">
                     </div>


                     <!--전화번호-->
<%--                      <div class="form-group row">
                        <div class="col-md-4">
                           <label for="c_state_country" class="text-black">휴대전화 <span
                              class="text-danger">*</span></label> <input type="text"
                              class="form-control" id="member_cp1" name="c_state_country"
                              value="${memberInfo.member_cp1 }">
                        </div>
                        <div class="col-md-4">
                           <label for="c_postal_zip" class="text-black"><br></label>
                           <input type="text" class="form-control" maxlength="4"
                              id="member_cp2" value="${memberInfo.member_cp2 }"
                              onkeypress="if (event.keyCode<48|| event.keyCode>57)  event.returnValue=false;"
                              style='IME-MODE: disabled;'>
                        </div>
                        <div class="col-md-4">
                           <label for="c_postal_zip" class="text-black"><br></label>
                           <input type="text" class="form-control" maxlength="4"
                              id="member_cp3" value="${memberInfo.member_cp3 }"
                              onkeypress="if (event.keyCode<48|| event.keyCode>57)  event.returnValue=false;"
                              style='IME-MODE: disabled;'>
                        </div>
                     </div> --%>

                  </div>
               </div>

               <Br><Br><Br>
        </div>



			<div class="row mb-5">
			               <div class="col-md-12">
			                  <br><br>
			                     <h2 class="h3 mb-3 text-black">Your Order</h2>
			                        <div style="border: 1px solid darkgray;" class="p-3 p-lg-5 border">
			                           <table class="table site-block-order-table mb-5">
			   
			                              <tbody>
			                                 <tr>
			                                    <td class="text-black font-weight-bold"><strong>총주문금액</strong></td>
			
			                                    <td><label  id="final_total_order_price">${final_total_order_price }</label>원</td>
			                                 </tr>
			
			<!-- <button class="bubbly-buttons" onclick="apply_discount()" ></button>
			 -->                                 
			                              </tbody>                           
			                              
			                           </table>
									</div>
								</div>
							</div> 
			<center>
			    <button type="button" class="bubbly-buttons"  onclick="order(this.form)">결제하기   </button> 
			 </center>
		</body>
</html>