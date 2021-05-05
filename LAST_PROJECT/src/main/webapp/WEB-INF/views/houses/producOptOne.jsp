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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../cmn/common.jsp" %>
<%@ include file="../cmn/header.jsp"%>
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>bootstrap index</title>

  <!-- 부트스트랩 -->
    <link href="${hContext }/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
   <%--  <script src="${hContext}/resources/css/bootstrap.min.css"></script> --%>
    <script src="${hContext}/resources/js/eclass.js"></script>
	<script src="${hContext}/resources/js/eutil.js"></script>
	<script src="${hContext}/resources/js/jquery.bootpag.js"> </script>
	
</head>
<body>
	<!-- hidden -->
	<input type="hidden" id="storeSeq" name="storeSeq" value="${vo.storeSeq }">
	<!-- hidden -->
	<div class="container">
		<div class="row">	
		<h2>옵션1</h2>	
			<div class="col-lg-12">
	   			<input type = "text" class="col-lg-5" name = "optOneOne" id = "optOneOne" value="" placeholder = "옵션1 이름 들어갑니다" />
	   		</div>
	   		<div class="col-lg-12">
	   			<input type = "number" class="col-lg-5" name = "priceOne" id = "priceOne" value="" placeholder = "옵션1 가격 들어갑니다" />
	   		</div>
	   		<div class="col-lg-12">
				<input type="button" class="btn btn-primary btn-sm col-lg-2"  value="등록" id="oneOne"/>
			</div>
		
		<br/>
		<br/>
		<h2>옵션2</h2>	
			<div class="col-lg-12">
		   		<input type = "text" class="col-lg-5" name = "optTwoOne" id = "optTwoOne" value="" placeholder = "옵션2 이름 들어갑니다" />
		   	</div>
		   	<div class="col-lg-12">
		   		<input type = "number" class="col-lg-5" name = "priceTwo" id = "priceTwo" value="" placeholder = "옵션2 가격 들어갑니다" />
		   	</div>
		   	<div class="col-lg-12">
				<input type="button" class="btn btn-primary btn-sm col-lg-2"  value="등록" id="oneTwo"/>
			</div>

		</div>
		<br/>
		<div class="row ">
				<div class="col-xs-8 col-sm-9 col-md-6 col-lg-7">
				</div>	
				<div class="col-xs-8 col-sm-9 col-md-6 col-lg-5">
		   			<input type="button" class="btn btn-primary btn-sm col-lg-3"  value="상품 삭제" id="doDelete"/>
					<input type="button" class="btn btn-primary btn-sm col-lg-2"  value="이전" id="doReturn"/>
				</div>
		</div>
		<br/>
		<br/>
		<div class="row col-lg-12">	
		<!--  optOnetable  -->
			<div class="col-lg-6">				
				<table id="optOneTable" class="table table-striped table-bordered table-hover table-condensed">
					<thead class="bg-primary">  
						<th class="text-center col-lg-4">옵션명</th>
						<th class="text-center col-lg-2">가격</th>
					</thead>
				    <tbody>
				        <!-- 문자: 왼쪽, 숫자: 오른쪽, 같은면: 가운데 -->
				    	<tr>
				    		<td class="text-center">없음</td>
				    		<td class="text-center">없음</td>		
				    	</tr>		
				    </tbody>
				</table>
			</div>
        <!--// optOnetable -->
        <!--  optTwotable  -->
			<div class="col-lg-6">				
				<table id="optTwotable" class="table table-striped table-bordered table-hover table-condensed">
					<thead class="bg-primary">  
						<th class="text-center col-lg-4">옵션명</th>
						<th class="text-center col-lg-2">가격</th>
					</thead>
				    <tbody>
				        <!-- 문자: 왼쪽, 숫자: 오른쪽, 같은면: 가운데 -->
				    	<tr>
				    		<td class="text-center">없음</td>
				    		<td class="text-center">없음</td>		
				    	</tr>		
				    </tbody>
				</table>
			</div>
        <!--// optTwotable -->
		</div>
	</div>
	<!-- container -->
		
		
		
		
	<script type="text/javascript">
	//jquery 객채생성이 완료
	$(document).ready(function() {
		console.log("1.document:최초수행!");

		optOnetable()
		
		optTwotable()
	});//--document ready
	
	//옵션1 1버튼
	$("#oneOne").on("click",function(e) {
		var title = $("#optOneOne").val();
		var price = $("#priceOne").val();
		console.log("title"+title);
		let url = "${hContext}/opt/do_insert.do";
		let parameters = {
				"storeSeq"  : "${vo.storeSeq}", 
				"memberId" : "${member.memberId}",    //housesSeq
				"title"  : title,
				"price"     : price,
				"div": 1
						};
		let method = "POST";
		let async  = true;
		
		console.log("parameters:"+parameters);
		console.log("url:"+url);
		
		
		
  		EClass.callAjax(url, parameters, method, async, function(data) {
  			alert("등록 성공");
		}); 
		
	});
	

		
	
	$("#oneTwo").on("click",function(e) {
		var title = $("#optTwoOne").val();
		var price = $("#priceTwo").val();
		console.log("title"+title);
		let url = "${hContext}/opt/do_insert.do";
		let parameters = {
							"storeSeq"  : "${vo.storeSeq}", 
							"memberId" : "${member.memberId}",    //housesSeq
							"title"  : title,
							"price"     : price,
							"div": 2
						};
		let method = "POST";
		let async  = true;
		
		console.log("parameters:"+parameters);
		console.log("url:"+url);
		
  		EClass.callAjax(url, parameters, method, async, function(data) {
  			alert("등록 성공");
		}); 
		
	});
	
	$("#doReturn").on("click",function(e) {
		var storeSeq = $("#storeSeq").val();
		console.log("storeSeq"+storeSeq);
		
		window.location.href = "${hContext}/mypage/Member_MyPage.do";
		
	});
	
	$("#doDelete").on("click",function(e) {
		var storeSeq = $("#storeSeq").val();
		console.log("storeSeq"+storeSeq);
		let url = "${hContext}/product/do_delete.do";
		let parameters = {
							storeSeq:storeSeq
						};
		let method = "POST";
		let async  = true;
		
		console.log("parameters:"+parameters);
		console.log("url:"+url);
		
		if(confirm("삭제 하시겠습니까?")==false) return;
		
  		EClass.callAjax(url, parameters, method, async, function(data) {
  			alert("삭제 성공");
  			
  			window.location.href = "${hContext}/mypage/Member_MyPage.do";
		}); 
		
	});
	
	
	//--optOnetable 
		    function optOnetable() {
	     	$.ajax({
	    		type: "GET",
	    		url:"${hContext }/opt/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			storeSeq: "${vo.storeSeq}",
	    			div: 1
	    		},
	    		success:function(data){//통신 성공
	        		//console.log("success data:"+data);
	    			var parseData = JSON.parse(data);
	    			
	    			//기존 데이터 삭제.
	    			$("#optOneTable>tbody").empty();
	    			var html = "";
	    			
	    			
	    			//data가 있는 경우
	    			if(parseData.length>0) {	    				
	    				$.each(parseData, function(i, value) {
	    					//console.log(i+","+value.name);
	    					html +="<tr>";
	    					html +="	 <td class='text-center'>"+value.title+"</td>";
	    					html +="	 <td class='text-left'>"+value.price+"</td>"; 		
	    					html +="	 <td class='text-left' style = 'display:none;'>"+value.optSeq+"</td>"; 
	    					html +="</tr>";		
	    				});
	    				
	    			}else {//data가 없는 경우
	    				html +="<tr>";
	    				html +="	<td class='text-center' colspan='99'>등록된 옵션이 없습니다.</td>";    		
	    				html +="</tr>";    				
	    			}
	    			
	    			//body에 데이터 추가
	    			$("#optOneTable>tbody").append(html);
	    			
	    			
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
	    }
	    
	
			//--optTwotable 
		    function optTwotable() {
	     	$.ajax({
	    		type: "GET",
	    		url:"${hContext }/opt/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			storeSeq: "${vo.storeSeq}",
	    			div: 2
	    		},
	    		success:function(data){//통신 성공
	        		//console.log("success data:"+data);
	    			var parseData = JSON.parse(data);
	    			
	    			//기존 데이터 삭제.
	    			$("#optTwotable>tbody").empty();
	    			var html = "";
	    			
	    			
	    			//data가 있는 경우
	    			if(parseData.length>0) {	    				
	    				$.each(parseData, function(i, value) {
	    					//console.log(i+","+value.name);
	    					html +="<tr>";
	    					html +="	 <td class='text-center'>"+value.title+"</td>";
	    					html +="	 <td class='text-left'>"+value.price+"</td>";
	    					html +="	 <td class='text-left' style = 'display:none;'>"+value.optSeq+"</td>"; 
	    					html +="</tr>";		
	    				});
	    				
	    			}else {//data가 없는 경우
	    				html +="<tr>";
	    				html +="	<td class='text-center' colspan='99'>등록된 옵션이 없습니다.</td>";    		
	    				html +="</tr>";    				
	    			}
	    			
	    			//body에 데이터 추가
	    			$("#optTwotable>tbody").append(html);
	    			
	    			
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
	    }
	    
			
			//--optOnetable click
			$("#optOneTable>tbody").on("click","tr",function(e){
				e.preventDefault();
				console.log("optOneTable>tbody");
				let tds = $(this).children();
				var uIdData = tds.eq(2).text();
				console.log(uIdData);
				
	 			let url = "${hContext}/opt/do_delete.do"
				let parameters = {"optSeq":uIdData};
				let method = "POST";
				let async = true;
				
				if(confirm("삭제 하시겠습니까?")==false) return;
				
	 			EClass.callAjax(url , parameters, method ,async, function(data){
	 				console.log("data:"+data);

						alert(data.msgContents);
						optOnetable()
						
						optTwotable()
	
				}); 
					 
	 
				
					

				
			});
			//--//optOnetable click
			
			//--optTwotable click
			$("#optTwotable>tbody").on("click","tr",function(e){
				e.preventDefault();
				console.log("optTwotable>tbody");
				let tds = $(this).children();
				var uIdData = tds.eq(2).text();
				console.log(uIdData);
				
	 			let url = "${hContext}/opt/do_delete.do"
				let parameters = {"optSeq":uIdData};
				let method = "POST";
				let async = true;
				
				if(confirm("삭제 하시겠습니까?")==false) return;
				
	 			EClass.callAjax(url , parameters, method ,async, function(data){
	 				console.log("data:"+data);

						alert(data.msgContents);
						optOnetable()
						
						optTwotable()
	
				}); 
					 
	 
				
					

				
			});
			//--//optTwotable click
	
	</script>
</body>
</html>