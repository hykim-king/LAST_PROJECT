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
<c:set var="hContext" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>인테리</title>
	
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

    <form id = "option_pop_up_frame">
        <input type = "hidden" id = "storeSeq" value = "${vo.storeSeq}">
        <input type = "hidden" id = "memberId" value = "${vo.memberId}">
        <input type = "hidden" id = "basketSeq" value = "${basketVO.basketSeq}">
        <input type = "hidden" id = "price" value = "${basketVO.price}">
        <label>상품명 : </label> <span name="title" id="title">${basketVO.title}</span>
       <br>
			<label>옵션 1 : </label>
				<div class="select-wrap">
			    	<div class="icon"><span class="ion-ios-arrow-down"></span></div>
			   	 		<select name="optone" id="optone" class="form-control">           
			    			<option value="1">색상</option>
			    				<c:choose>
									<c:when test="${list.size() >0 }">
										<c:forEach var="vo" items="${list}">
			    							<c:if test="${vo.getDiv()==1}">
												<option value='' id="opt1"><c:out value="${vo.title}"/></option>
											</c:if>
										</c:forEach>
									</c:when>
								</c:choose>
							</select>                                                      
						</div>                                                       
			  		</div>
			<label>옵션 2 : </label>
				<div class="select-wrap">
			    	<div class="icon"><span class="ion-ios-arrow-down"></span></div>
			   	 		<select name="opttwo" id="opttwo" class="form-control">           
			    			<option value="2">사이즈</option>
			    				<c:choose>
									<c:when test="${listTwo.size() >0 }">
										<c:forEach var="vo" items="${listTwo}">
			    							<c:if test="${vo.getDiv()==2}">
												<option value='' id="opt2"><c:out value="${vo.title}"/></option>
											</c:if>
										</c:forEach>
									</c:when>
								</c:choose>
							</select>                                                      
						</div>                                                       
			  		</div>
	        <br/>
	        <label>수량 : </label>
			<div class="product__details__option">
				<div class="quantity">
					<div class="pro-qty">
						<span class="dec qtybtn">-</span>                                                
							<input type="text" value="${basketVO.quantity}" id="quantity">                                                
						<span class="inc qtybtn">+</span>
					</div>
				</div>
			</div>
	    </form>
	    <br/>
	    <div style="text-align: center;">
			<a class='primary-btn' onclick='optionUpdate();'>변경하기</a>
		</div>
	  	 
   
    <script type="text/javascript">
    
		$(document).ready(function() {
			console.log("1.document:최초수행!");
		});	
		
		function setParentResult() {
			console.log("setParentResult");

	    	if(typeof($("#title"))!="undefined") {
	    		
				window.opener.document.getElementById("title").value = $("#title").text();
				window.opener.document.getElementById("optone").value = $("#optone").text();
				window.opener.document.getElementById("opttwo").value = $("#opttwo").text();
				window.opener.document.getElementById("quantity").value = $("#quantity").text();
				console.log("sendToParent title:"+window.opener.document.getElementById("title").value);
				console.log("sendToParent optone:"+window.opener.document.getElementById("optone").value);
				console.log("sendToParent opttwo:"+window.opener.document.getElementById("opttwo").value);
				console.log("sendToParent quantity:"+window.opener.document.getElementById("quantity").value);

	    	}
	    				
			//self.close();
		}
		
 		function optionUpdate() {
			console.log("optionUpdate()");
			
	        var frm = $("#optionFrm").serialize(); // 해당하는 frm을 serialize를 해줍니다. ajax로 데이터를 보내기위해서 하는 작업입니다.

			var memberIdData = $("#memberId").text();
			var optoneData = "화이트";
			var opttwoData = "S";
			var shipfeeData = "3000";
			
			let url = "${hContext}/basket/do_update.do";
			let parameter = {
								"basketSeq" : $("#basketSeq").val(),
								"storeSeq"  : $("#storeSeq").val(),
								"memberId"  : memberIdData,
								"title"     : $("#title").text(),
								"optone"    : optoneData,
								"opttwo"    : opttwoData,
								"quantity"  : $("#quantity").val(),
								"shipfee"   : shipfeeData,
								"price"     : $("#price").val(),
								"modId"     : $("#memberId").text()
							};
			let method = "POST";
			let async = true;
			
			if(confirm("옵션을 변경하시겠습니까?")==false) return;

 			EClass.callAjax(url, parameter, method, async, function(data) {
    			console.log("data:"+data);
    			console.log("data:"+data.msgContents);	
    			if(data.msgId=="1") { //수정 성공
        			alert(data.msgContents);  
        			
        			window.opener.location.href = "${hContext}/member/basket_list.do?memberId="+memberIdData;
        			
        			window.self.close();
        			
    			} else { //수정 실패
        			alert(data.msgId+ "\n" +data.msgContents);
    			}
	    	});
 			
	        //setParentResult();
	        
 		} 
 		
	    /* 수량 변경 */
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
    });
	        
	</script>

</body>
</html>