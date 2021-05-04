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

<!-- 국제화 -->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../cmn/common.jsp" %>
<%@ include file="../cmn/header.jsp"%>
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta name="description" content="">
  <meta name="author" content="">
    
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>bootstrap index</title>

  <!-- 부트스트랩 -->
   <%--  <link href="${hContext }/resources/css/bootstrap.min.css" rel="stylesheet"> --%>

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<%-- <script src="${hContext}/resources/css/bootstrap.min.css"></script> --%>
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
	<script src="${hContext}/resources/js/eutil.js"></script>
	<script src="${hContext}/resources/js/jquery.bootpag.js"> </script>
	
  <!-- Bootstrap core CSS -->
  <link href="${hContext}/resources/lhc/bootstrap_lhc.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <%-- <link href="${hContext}/resources/lhc/shop-homepage.css" rel="stylesheet"> --%>
  <link rel="stylesheet" type="text/css" href="${hContext}/resources/lhc/base.css">
  <link rel="stylesheet" type="text/css" href="${hContext}/resources/lhc/normalize.css">
  

</head>
<body>

		
	<!-- hidden -->
		
			
			
	<!-- //hidden -->
		
  <!--  컨테이너 -->
 <div class="container">
  <h4>집들이</h4><br/>
		<div class="row col-lg-12">			
		<!-- 프로젝트 출력 -->	   	
	   	  <div class="row col-lg-12">
		   	<div class="col-xs-8 col-sm-9 col-md-8 col-lg-2">
	 				<select class="form-control input-sm" name="orderDiv" id="orderDiv">					
	    		  		<option value="10">최신순</option>	    		  		
						<option value="20">인기순</option>
	    		  </select> 
		    </div>
		    <div class="col-xs-8 col-sm-9 col-md-8 col-lg-2">
	 				<select class="form-control input-sm" name="searchDiv" id="searchDiv">			
	    		  		<option value="10">제목</option>	    		  		
						<option value="20">태그</option>
						<option value="30">제목+태그</option>
	    		  </select> 
		    </div>
		   	<div class="col-xs-8 col-sm-9 col-md-6 col-lg-6">
	   			<input type = "text" size = "20" name = "searchWord" id = "searchWord" placeholder = "검색어를 입력해주세요" />
			</div>
			<div class="col-xs-8 col-sm-9 col-md-12 col-lg-2">
					<input type="button" class="btn btn-primary btn-sm"  value="조회" id="doRetrieveBtn"/>
			</div>	  	
		  </div>
			<br/>
			<br/>
		  <!-- //row-->
		  <div class="row col-lg-12">
			<div class="col-lg-3 text-right ">
	 				<select class="form-control input-sm " name="pageSize" id="pageSize">			
	    		  		<option value="4">4개씩 보기</option>	  		  		
						<option value="8">8개씩 보기</option>
						<option value="12">12개씩 보기</option>

	    		  </select> 
		    </div>
		  </div>
		  <br/>
		  <br/>
		  <!-- row -->	
		  <div id="rowCard" class="row col-lg-12">

		  </div>	
		   <!-- //row -->	
		<!-- pagenation -->
		<div class="row col-lg-12">
			<div id="page-selection" class="text-right page">
				
			</div>
		</div>
		<!--// pagenation -->
		</div>
</div>
<!--  // 컨테이너 -->
		
 
	<!-- javascript -->
 <script type="text/javascript">
 
 
	//jquery 객채생성이 완료
	$(document).ready(function() {
		console.log("1.document:최초수행!");
		//화면 로딩 되면 바로 출력
		doRetrieve(1);
		
	});//--document ready
 
	$("#doRetrieveBtn").on("click",function(e) {
		console.log("doRetrieveBtn");
		e.preventDefault(); //두번 호출 방지.
		doRetrieve(1);
		
	});
	

	
    function doRetrieve(page) {
     	$.ajax({
    		type: "GET",
    		url:"${hContext }/houses/do_retrieve.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			pageSize: $("#pageSize").val(),
    			searchDiv: $("#searchDiv").val(),
    			searchWord: $("#searchWord").val(),
    			orderDiv: $("#orderDiv").val(),
    			pageNum: page
    		},
    		success:function(data){//통신 성공
        		//console.log("success data:"+data);
    			var parseData = JSON.parse(data);
    			
    			//기존 데이터 삭제.
    			$("#rowCard").empty();
    			var html = "";
    			var scrapBtn = $("#doScrapBtn");
    			
    			let totalCount = 0;
    			let pageTotal = 1;
    			
    			console.log("parseData:"+parseData[0].totalCnt);
    			console.log("scrapBtn:"+scrapBtn);
    			
    			console.log("parseData.length:"+parseData.length);
    			
    			//data가 있는 경우
    			if(parseData.length>0) {
    				totalCount = parseData[0].totalCnt;
    				pageTotal =  totalCount/$("#pageSize").val(); //42/10 ->4.2
    				pageTotal = Math.ceil(pageTotal); // 42/10 ->5
    				
    				$.each(parseData, function(i, value) {
    					//console.log(i+","+value.name);
    					console.log("eachscrapBtn"+scrapBtn);
    					html+="<div  class='col-lg-3 col-md-6 mb-4'>";
    					html+="	<div class='card h-100'>";
    					html+="		<a href='${hContext}/houses/houses_detail.do?housesSeq="+value.housesSeq+"'><img class='card-img-top' src='${hContext}/"+value.imgId+"' ></a>";
    					html+="		<h6  class='text-muted'>"+value.tag+"</h6 >";
    					html+="		<div id='buttonClick' class='row col-lg-12'>";
    					html+="			<h6 class='card-title col-lg-8'>"+value.memberId+"</h6>";
    					html+="			<input type='button' class='btn btn-primary btn-sm col-lg-4'  value='스크랩' name='doScrapBtn' id='doScrapBtn'/>";
    					html+="			<small class = 'gotta' style = 'display:none;''>"+value.housesSeq+"</small>";
    					html+="		</div>";
    					html+="		<div id='rowCardClick' class='text-center col-lg-12'>";
    					html+="			<h4 class='text-muted'>"+value.title+"</h4>";
    					html+="			<small class = 'gotta' style = 'display:none;''>"+value.housesSeq+"</small>";
    					html+="		</div>";	
    					html+="	</div>";	
    					html+="</div>";	
    					
    					

    				});
    				
    			}else {//data가 없는 경우
    				html +="<tr>";
    				html +="	<td class='text-center' colspan='99'>등록된 게시물이 없습니다.</td>";    		
    				html +="</tr>";    				
    			}
    			
    			//body에 데이터 추가
    			$("#rowCard").append(html);
    			
    			console.log("pageTotal:"+pageTotal);
    			console.log("page:"+page);
    			
    			//페이징 처리: 총 페이지, 현재글
    			renderingPage(pageTotal,page);
    			
    			
    			//doInit();//입력 form 초기화
    			
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	});
    }
    
    
 	//--table click
	$("#rowCard").on("click","#buttonClick",function(e){
		e.preventDefault();
		console.log("buttonClick");
 	 	let tds = $(this).children();
 	 	console.log(tds);

 	 	var memberId= tds.eq(0).text();
		var button = tds.eq(1).text();
		var housesSeq = tds.eq(2).text();
		console.log("button"+button); 

		console.log("housesSeq"+housesSeq); 
		
		var memberNull = "${member.memberId}";
		console.log("memberNull"+memberNull); 
		if(memberNull==""){
			alert("로그인 하십시오");
			return;
		}

		let url = "${hContext}/scrap/do_insert.do"
			let parameters = {
				"scrapSeq" :-1,
				"memberId" :memberId,//이부분은 차후 세션에서 받을 예정
				"housesSeq" :housesSeq,
				"modId":"${member.memberId}"//이부분은 차후 세션에서 받을 예정
				};
			let method = "POST";
			let async = true;	
			console.log(parameters);
			console.log(url);
 		EClass.callAjax(url , parameters, method ,async, function(data){
			console.log("data"+data.memberId);
			console.log("data"+data.housesSeq);
			if("1"==data.msgId) {//등록 성공
				alert(data.msgContents);
				//doRetrieve(1);
			}else {//등록 실패
				alert(data.msgId+"\n"+data.msgContents);
			}
			

			
				
			}); 
			 
		}); 
    
		
 	//--button click
	$("#rowCard").on("click","#rowCardClick",function(e){
		e.preventDefault();
		console.log("rowCard");
 	 	let tds = $(this).children();
 	 	console.log(tds);
		var housesSeq = tds.eq(1).text();
		console.log(housesSeq); 
		
		
 
			
			window.location.href = "${hContext}/houses/houses_detail.do?housesSeq="+housesSeq;
	
			 
		}); 
		
		
	
	//paging
	//pageTotal : 총페이지 수 : 총글수/페이지 사이즈(10)
	//page : 현재페이지
	function renderingPage(pageTotal,page) {
		//이전 연결된 Event 핸들러를 요소에서 제거
		$("#page-selection").unbind('page');
		
		$("#page-selection").bootpag({
		    total: pageTotal,  		
		    page: page,			
		    maxVisible: 5,	    
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
		   doRetrieve(num);
		}); 
		
	}
	
 
 
 
 </script>		
	
	
</body>
</html>