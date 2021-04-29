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
<!-- div container -->
	<div class="wrap container">
	
		<!--장바구니 데이터 가져오기  -->
		<!-- 제목 -->
	 	<div class="page-header">
	 		<h2>결제</h2>
	 	</div>
	    <!--// 제목 -->
	    
	 <!-- 페이지 검색영역 -->
		<div class="row col-lg-12">
			<div class="col-xs-8 col-sm-9 col-md-8 col-lg-2 text-right ">
	 			 <select class="form-control input-sm " name="pageSize" id="pageSize">				
	    		  		<option value="3">3개씩 보기</option>	    		  		
						<option value="5">5개씩 보기</option>
	    		  </select> 
		    </div>
		  </div>
	<!-- //페이지 검색영역 --> 
	    
        <!-- table -->
		<div class="table-responsive">
		    <!-- table -->
			<table id="BasketTable" class="table table-striped table-bordered table-hover table-condensed">
				<thead class="bg-primary">  
					<th class="text-center col-lg-3 col-md-1  col-xs-3">상품 이미지</th>
					<th class="text-center col-lg-3 col-md-3  col-xs-3">상품명</th>
					<th class="text-center col-lg-2 col-md-2  col-xs-2">가격</th>  
					<th class="text-center col-lg-2 col-md-2  col-xs-2">수량</th>
					<th class="text-center col-lg-2 col-md-2  col-xs-2">합계</th>
				</thead>
			    <tbody>
			    </tbody>
			</table>
		</div>
        <!--// table -->
        
        <!-- pagenation -->
		<div class="text-center">
			<div id="page-selection" class="text-center page"></div>
		</div>
	    <!--// pagenation -->	
        
        <!--//장바구니 데이터 가져오기  -->
        
        
        <!-- 주문내역 -->
        <!-- 주문내역 제목 -->
	 	<div class="page-header">
	 		<h2>주문내역</h2>
	 	</div>
	    <!--// 주문내역 제목 -->
	    
	   <!-- table -->
		<div class="table-responsive">
		    <!-- table -->
			<table id="OrderTable" class="table table-striped table-bordered table-hover table-condensed">
				<thead class="bg-primary">  
					<th class="text-center col-lg-3 col-md-3  col-xs-3">총 주문금액</th>
				</thead>
			    <tbody>
			    </tbody>
			</table>
		</div>
        <!--// table -->
	    <!-- //주문내역 -->
	    	
        <!--상세정보입력  -->
		<!-- 제목 -->
	 	<div class="page-header">
	 		<h2>상세정보입력</h2>
	 	</div>
	    <!--// 제목 -->	
	    
	    <!-- form --> 
	    <form action="" class="form-horizontal">
		  <div class="form-group">
		    <div class="col-xs-8 col-sm-9 col-md-12 col-lg-12">
		      <input type="text" class="form-control" id="uId" name="uId"  placeholder="주문하시는 분" maxlength="20">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-xs-8 col-sm-9 col-md-12 col-lg-8">
		      <input type="text" class="form-control" id="name" name="name"  placeholder="우편번호" maxlength="50">
		      <input type="button" class="btn btn-primary btn-sm"  value="주소찾기" id="postBtn"/>
		    </div>
		  </div>
		  <div class="form-group">
		   <div class="col-xs-8 col-sm-9 col-md-12 col-lg-12">
		      <input type="password" class="form-control" id="passwd" name="passwd"  placeholder="주소" maxlength="50">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-xs-8 col-sm-9 col-md-12 col-lg-12">
		      <input type="text" numberonly class="form-control" id="login" name="login"  placeholder="나머지주소" maxlength="7">
		    </div>
		  </div>			    	    	
	    </form>
	    <!--// form --> 
	    <!--//상세정보입력  -->
	    
	    <!-- 버튼 -->
	    <div class="row text-right">
	        <div   class="col-xs-8 col-sm-9 col-md-12 col-lg-12">
	        	<input type="button" class="btn btn-primary btn-lg"  value="결제" id="paymentBtn"/>
	        	<input type="button" class="btn btn-primary btn-lg"  value="취소" id="cancelBtn"/>
	        </div>
	    </div>
	    <!--// 버튼 -->    
	</div>
<!-- //div container -->

	<!-- javascript -->
	<script type="text/javascript">
//	//jquery 객체생성이 완료
//	$(document).ready(function() {
//		console.log("document ready");
//		
//		//화면 로딩시 보여줄 데이터 
//		doRetrieve();
//		//doPayment();
//	});//--document ready	
//	
//
//	//조회버튼 실행시 
//	function doRetrieve(page){
//		console.log("page:"+page);
//		$.ajax({
//    		type: "GET",
//    		url:"${hContext}/payment/do_retrieve.do",
//    		asyn:"true",
//    		dataType:"html",
//    		data:{
//    			pageSize:$("#pageSize").val(),
//    			//memberId:$("#memberId").val(),
//    			pageNum:page
//    		},
//    		success:function(data){//통신 성공
//        		console.log("success data:"+data);
//        		var parseData = JSON.parse(data);
//        		
//        		//기존데이터 삭제
//        		$("#BasketTable>tbody").empty();
//        		var html = "";
//        		
//        		//페이징 변수
//        		let totalCount = 0;//총 글수
//        		let pageTotal = 1;//총 페이지수
//        		
//        		//합계(하나의 상품 총 가격) 변수
//        		let productTotal = 0;
//        		
//        		console.log("parseData.length:"+parseData.length);
//        		console.log("totalCount:"+parseData[0].totalCount);
//        		
//				//data가 있는 경우
//				if(parseData.length>0){
//					
//					totalCount = parseData[0].totalCount;
//					pageTotal  = totalCount/$("#pageSize").val();//42/10->4.2
//					pageTotal = Math.ceil(pageTotal);//42/10->4.2->5
//					
//					//하나의 상품 총 가격
//					productTotal = price * quantity;
//					
//					$.each(parseData,function(i,value){
//						console.log(i+","+value.name);
//						html += "<tr>";
//						html += "	<td class='text-center'><img src='${hContext}/resources/../..' alt='Image placeholder'></td>";
//						html += "	<td class='text-center'>"+value.title+"</td>";//상품명
//						html += "	<td class='text-center'>"+value.price+"</td>";//가격
//						html += "	<td class='text-center'>"+value.quantity+"</td>";//수량
//						html += "	<td class='text-center'>"+value.productTotal+"</td>";//합계(하나의 상품 총 가격)
//						html += "</tr>";
//					});
//					
//				}else{//data가 없는 경우
//    				html +="<tr>";
//    				html +="	<td class='text-center' colspan='99'>장바구니에 상품을 담아주세요</td>";    		
//    				html +="</tr>";   
//				}
//				
//				//tbody에 데이터 추가
//				$("#BasketTable>tbody").append(html);	
//				
//				//페이징처리
//				console.log(pageTotal+","+page);
//				renderingPage(pageTotal,page);
//	
//        	},
//        	error:function(data){//실패시 처리
//        		console.log("error:"+data);
//        	},
//        	complete:function(data){//성공/실패와 관계없이 수행!
//        		console.log("complete:"+data);
//        	}
//    	});
//	}//--doRetrieve
//	
//	
//	//주문내역(총 주문금액)
//	function doPayment(){
//		console.log("doPayment");
//		
//		$.ajax({
//    		type: "GET",
//    		//url:"${hContext}/payment/do_retrieve.do",만들어야함
//    		asyn:"true",
//    		dataType:"html",
//    		data:{
//					//data 넣기
//    		},
//    		success:function(data){//통신 성공
//        		console.log("success data:"+data);
//        		var parseData = JSON.parse(data);
//        		
//        		//기존데이터 삭제
//        		$("#OrderTable>tbody").empty();
//        		var html = "";
//        		
//        		//합계(하나의 상품 총 가격) 변수
//        		let productTotal = 0;
//        		//총 주문금액
//        		let totalPayment = 0;
//        	
//        		console.log("parseData.length:"+parseData.length);
//
//				if(parseData.length>0){//data가 있는 경우
//					
//					//하나의 상품 총 가격
//					productTotal = price * quantity;
//					//총 주문금액
//					totalPayment += productTotal;
//					
//					$.each(parseData,function(i,value){
//						console.log(i+","+value.name);
//						html += "<tr>";
//						html += "	<td class='text-center'>"+value.totalPayment+"</td>";//총 주문금액
//						html += "</tr>";
//					});
//					
//				}else{//data가 없는 경우
//    				html +="<tr>";
//    				html +="	<td class='text-center' colspan='99'>장바구니에 상품을 담아주세요</td>";    		
//    				html +="</tr>";   
//				}
//				
//				//tbody에 데이터 추가
//				$("#OrderTable>tbody").append(html);	
//
//        	},
//        	error:function(data){//실패시 처리
//        		console.log("error:"+data);
//        	},
//        	complete:function(data){//성공/실패와 관계없이 수행!
//        		console.log("complete:"+data);
//        	}
//    	});			
//	}//--doPayment
//	
//	
//	
//	//paging
//	//pageTotal:총 페이지수= 총글수/페이지사이즈(10)
//	//page:현재페이지
//	//maxVisible:bottom 페이지
//	function renderingPage(pageTotal,page){
//		//이전 연결된 Event 핸들러 요소에서 제거
//		$("#page-selection").unbind('page');
//		
//		$("#page-selection").bootpag({
//		    total: pageTotal,
//		    page: page,//시작
//		    maxVisible: 3,
//		    leaps: true,
//		    firstLastUse: true,
//		    first: '←',
//		    last: '→',
//		    wrapClass: 'pagination',
//		    activeClass: 'active',
//		    disabledClass: 'disabled',
//		    nextClass: 'next',
//		    prevClass: 'prev',
//		    lastClass: 'last',
//		    firstClass: 'first' 
//		}).on("page", function(event, num){
//			doRetrieve(num);//ajax 서버 호출
//		}); 
//	}//--renderingPage
//	
//	
//	
//	//주소찾기
//
//	
//	
//	//결제버튼
//	$("#paymentBtn").on("click",function(e){
//		console.log("paymentBtn");
//		e.preventDefault();//한번만 호출
//		
//	 	let tds = $(this).children();
// 	 	console.log(tds);
// 	 	
// 	 	var memberId = tds.eq(0).text();
//		var paySeq = tds.eq(1).text();
//		
//		console.log("memberId:"+memberId); 
//		console.log("paySeq:"+paySeq); 
//
//		let url = "${hContext}/payment/do_insert.do"
//			let parameters = {
//				"paySeq" : paySeq,
//				"memberId" :memberId,//세션
//				"storeSeq" :storeSeq,
//				"modId":memberId//세션
//				};
//			let method = "POST";
//			let async = true;	
//			
//			console.log(parameters);
//			console.log(url);
//			
//		if(confirm("결제하시겠습니까?")==false) return;
//			
// 		EClass.callAjax(url , parameters, method ,async, function(data){
//			console.log("data"+data.memberId);
//			console.log("data"+data.paySeq);
//			
//			if("1"==data.msgId) {//결제 성공
//				alert(data.msgContents);
//				//doRetrieve(1);
//			}else {//결제 실패
//				alert(data.msgId+"\n"+data.msgContents);
//			}
//
//		}); 		
//	});//--paymentBtn
//	
//	
//	
//	//취소버튼
//	$("#cancelBtn").on("click",function(e){
//		console.log("cancelBtn");
//		e.preventDefault();//한번만 호출
//		
//		 let url = "${hContext}/payment/do_delete.do";
//		 let parameter = {
//				 			"paySeq" : paySeq};
//		 let method = "POST";
//		 let async = true;	
//		 
//		console.log(parameters);
//		console.log(url);
//		 
//		 if(confirm("결제를 취소하시겠습니까?")==false) return;
//		 
//		 EClass.callAjax(url, parameter, method, async, function(data) {
//				 console.log("data:"+data);
//				 console.log("data:"+data.paySeq);
//				 
//				 //성공/실패 여부 메세지 출력
//				 console.log("data.msgContents:"+data.msgContents);
//				 
//				 if("1"==data.msgId){//취소성공
//					 alert(data.msgContents);
//				 	 //doRetrieve(1);
//				 }else{//취소실패
//					 alert(data.msgId+"\n"+data.msgContents); 
//				 }
//				 
//			 });
//		
//	});	//--cancelBtn	
//	
		
	</script>
	<!-- //javascript -->
</body>
</html>