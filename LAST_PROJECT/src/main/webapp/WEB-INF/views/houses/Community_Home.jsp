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
  <link href="${hContext}/resources/lhc/shop-homepage.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="${hContext}/resources/lhc/base.css">
  <link rel="stylesheet" type="text/css" href="${hContext}/resources/lhc/normalize.css">
  

</head>
<body>
<%-- ${list} --%>
<%--  "총글수 : " ${total_cnt} ${search }<br/>
	${list }  --%>
	
	<!-- header -->

  <!--//header -->
		<!-- hidden -->

		<input type = "hidden" name = "pageSize"  id="pageSize" value="8" />

		<!-- hidden -->
		
  <!--  컨테이너 -->
 <div class="container">

 
 
  <h2>집들이</h2><br/>
  <h4>인기순</h4>
 <div id="rowPopularCard" class="row">      
           <c:choose>
			<c:when test = "${list.size() > 0 }">
				<c:forEach var = "vo" items = "${list }"> 
					<div class="col-lg-3 col-md-6 mb-4">
			            <div class="card h-100">
			              <a href="${hContext}/houses/houses_detail.do?housesSeq=${vo.housesSeq}"><img class="card-img-top" src="${hContext }${vo.imgId }" alt=""></a>
			              <h6  class="text-muted">${vo.tag}</h6 >
			              <div id="pupularButtonClick"  class="row col-lg-12">			              
			               <h6  class="card-title col-lg-8">${vo.memberId}</h6 >	
			              	<input  type="button" class="btn btn-primary btn-sm col-lg-4"  value="스크랩" />
			              	<small class="gotta" style="display:none;">${vo.housesSeq } </small>
				            </div>
				            <div id="rowPopularCardClick" class="text-center">
			                <a href="${hContext}/houses/houses_detail.do?housesSeq=${vo.housesSeq}">
			                <h4 class="text-muted">
			                  ${vo.title }
			                </h4>
			                </a>
			                <small class="gotta" style="display:none;">${vo.housesSeq } </small>
			              </div>
			            </div>
			          </div>
				</c:forEach>
			 </c:when>
				
			<c:otherwise>
				<h6>데이터가 없습니다.</h6><br/>
			</c:otherwise>
			            		
          </c:choose>  
         
           		
          </div>
  	<br/>
  	<h4>최신순</h4>
	<div id="rowCard" class="row">

	</div>
	<br/>	
	<!-- //rowHousesCard -->	 
		<!-- //집들이 출력 -->		
		<!-- qna 출력 -->
	<h4>QnA</h4><br/>
	<div id="rowQnaCard" class="row">

	</div>	
		<!-- //qna 출력 -->		   
		   

</div>
<!--  // 컨테이너 -->
  
  
	<!-- javascript -->
 <script type="text/javascript">
 
 
	//jquery 객채생성이 완료
	$(document).ready(function() {
		console.log("1.document:최초수행!");
		//화면 로딩 되면 바로 출력
		doRetrieve(1);
		doRetrieveQna(1);
		
	});//--document ready
 	
	//최신순
    function doRetrieve(page) {
    	console.log("doRetrieve");
     	$.ajax({
    		type: "GET",
    		url:"${hContext }/houses/do_retrieve.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			pageSize: $("#pageSize").val(),
    			pageNum: page
    		},
    		success:function(data){//통신 성공
        		//console.log("success data:"+data);
    			var parseData = JSON.parse(data);
    			
    			//기존 데이터 삭제.
    			$("#rowCard").empty();
    			var html = "";
    			
    			let totalCount = 0;
    			let pageTotal = 1;
    			
    			console.log("parseData:"+parseData[0].totalCnt);
    			
    			
    			console.log("parseData.length:"+parseData.length);
    			
    			//data가 있는 경우
    			if(parseData.length>0) {
    				totalCount = parseData[0].totalCnt;
    				pageTotal =  totalCount/$("#pageSize").val(); //42/10 ->4.2
    				pageTotal = Math.ceil(pageTotal); // 42/10 ->5
    				
    				$.each(parseData, function(i, value) {
    					//console.log(i+","+value.name);
    					
    					html+="<div  class='col-lg-3 col-md-6 mb-4'>";
    					html+="	<div class='card h-100'>";
    					html+="		<a href='${hContext}/houses/houses_detail.do?housesSeq="+value.housesSeq+"'><img class='card-img-top' src='${hContext}/"+value.imgId+"' ></a>";
    					html+="		<h6  class='text-muted'>"+value.tag+"</h6 >";
    					html+="		<div id='buttonClick' class='row col-lg-12'>";
    					html+="			<h6 class='card-title col-lg-8'>"+value.memberId+"</h6>";
    					html+="			<input type='button' class='btn btn-primary btn-sm col-lg-4'  value='스크랩' name='doScrapBtn' id='doScrapBtn'/>";
    					html+="			<small class = 'gotta' style = 'display:none;''>"+value.housesSeq+"</small>";
    					html+="		</div>";
    					html+="		<div id='rowCardClick' class='text-center'>";
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
    			//renderingPage(pageTotal,page);
    			
    			
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
    
	//qna 
    function doRetrieveQna(page) {
    	console.log("doRetrieveQna");
     	$.ajax({
    		type: "GET",
    		url:"${hContext }/qna/do_retrieve.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			pageSize: $("#pageSize").val(),
    			pageNum: page
    		},
    		success:function(data){//통신 성공
        		//console.log("success data:"+data);
    			var parseData = JSON.parse(data);
    			
    			//기존 데이터 삭제.
    			$("#rowQnaCard").empty();
    			var html = "";
    			
    			let totalCount = 0;
    			let pageTotal = 1;
    			
    			console.log("parseData:"+parseData[0].totalCnt);
    			
    			
    			console.log("parseData.length:"+parseData.length);
    			
    			//data가 있는 경우
    			if(parseData.length>0) {
    				totalCount = parseData[0].totalCnt;
    				pageTotal =  totalCount/$("#pageSize").val(); //42/10 ->4.2
    				pageTotal = Math.ceil(pageTotal); // 42/10 ->5
    				
    				$.each(parseData, function(i, value) {
    					//console.log(i+","+value.name);
    					
    					html+="<div  class='col-lg-3 col-md-6 mb-4'>";
    					html+="	<div class='card h-100'>";
    					html +=			"<a href='${hContext}/qna/qna_detail.do?qnaSeq="+value.qnaSeq+"'><img class='card-img-top col-lg-3 col-md-6 mb-4' src='${hContext}/"+value.imgId+"' ></a>";
    					html+="		<h6  class='text-muted'>"+value.tag+"</h6 >";
    					html+="		<div id='buttonClick' class='row col-lg-12'>";
    					html+="			<h6 class='card-title col-lg-8'>"+value.memberId+"</h6>";
    					html+="			<small class = 'gotta' style = 'display:none;''>"+value.qnaSeq+"</small>";
    					html+="		</div>";
    					html+="		<div id='rowQnaCardClick' class='text-center'>";
    					html+="			<h4 class='text-muted'>"+value.title+"</h4>";
    					html+="			<small class = 'gotta' style = 'display:none;''>"+value.qnaSeq+"</small>";
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
    			$("#rowQnaCard").append(html);
    			
    			console.log("pageTotal:"+pageTotal);
    			console.log("page:"+page);
    			
    			//페이징 처리: 총 페이지, 현재글
    			//renderingPage(pageTotal,page);
    			
    			
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
	
	//최신순 스크랩 버튼 클릭
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
 	//집들이 최신순 title 클릭시 페이지 이동
	$("#rowCard").on("click","#rowCardClick",function(e){
		e.preventDefault();
		console.log("rowCard");
 	 	let tds = $(this).children();
 	 	console.log(tds);
		var housesSeq = tds.eq(1).text();
		console.log(housesSeq); 
		
		
 
			
			window.location.href = "${hContext}/houses/houses_detail.do?housesSeq="+housesSeq;
	
			 
		}); 
    
 	//qna 타이틀 클릭시 페이지 이동
	$("#rowQnaCard").on("click","#rowQnaCardClick",function(e){
		e.preventDefault();
		console.log("rowCard");
 	 	let tds = $(this).children();
 	 	console.log(tds);
		var qnaSeq = tds.eq(1).text();
		console.log(qnaSeq); 
		
		
 
			
			window.location.href = "${hContext}/qna/qna_detail.do?qnaSeq="+qnaSeq;
	
			 
		}); 
    
 	
  	//인기순 scrap 클릭시
	$("#rowPopularCard").on("click","#pupularButtonClick",function(e){
		console.log("scrapPopular");
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
	
		
		
 
 
 
 </script>		
	
	
</body>
</html>