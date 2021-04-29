<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2021. 4. 26.        임하람 
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../cmn/common.jsp" %>
<%@ include file="../cmn/header.jsp" %>
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

<title>QNA LIST</title>

  <!-- 부트스트랩 -->
   <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet"> 

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${hContext}/resources/js/jquery.min.js"></script>
    <script src="${hContext}/resources/css/bootstrap.min.css"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
	<script src="${hContext}/resources/js/eutil.js"></script>
	<script src="${hContext}/resources/js/jquery.bootpag.js"></script>
	

</head>
<body>

<!-- div container -->
	<div class="wrap container">

	<!-- Jumbotron -->
	<!-- 수평선 긋기 -->
	 <hr class="my-2">
	    <div class="jumbotron jumbotron-fluid mx-4">
	    	<!-- ~를 위한 추천 메세지 띄우기 -->
	    	<h2>
				<c:choose>
					<c:when test="${null != sessionScope.memberInfo }"><!-- 로그인 했을때 -->
							${sessionScope.memberInfo.nickname}님을 위한 질문과 답변
					</c:when>
					<c:otherwise><!-- 로그인 안했을때 -->
						당신을 위한 질문과 답변
					</c:otherwise>
				</c:choose>
			</h2>
			<!--// ~를 위한 추천 메세지 띄우기 -->
	        <p class="lead">인테리어 고수들에게 조언을 받으세요</p>
	        <input type="button" class="btn btn-lg btn-success"  value="질문 등록하기" id="doRegistBtn"/>
	     </div>
	<hr class="my-2">
	<!-- 수평선 긋기 -->
	<!--// Jumbotron -->

	<!-- 검색영역 --><!--  -->
		<div class="row col-lg-12">
			<div class="col-xs-8 col-sm-9 col-md-8 col-lg-2 text-right ">
	 				<select class="form-control input-sm " name="pageSize" id="pageSize">				
	    		  		<option value="3">3개씩 보기</option>	    		  		
						<option value="6">6개씩 보기</option>
	    		  </select> 
		    </div>
		  </div>
	   <div class="row col-lg-12">
		    <div class="col-xs-8 col-sm-9 col-md-8 col-lg-2">
	 				<select class="form-control input-sm" name="searchDiv" id="searchDiv">			
	    		  		<option value="10">제목</option>	    		  		
						<option value="20">태그</option>
						<option value="30">제목+태그</option>
	    		  </select> 
		    </div>
		   	<div class="col-xs-8 col-sm-9 col-md-6 col-lg-4">
	   			<input type = "text" size = "40" name = "searchWord" id = "searchWord" placeholder = "검색어를 입력해주세요" />
			</div>
			<div class="col-xs-8 col-sm-9 col-md-12 col-lg-4">
					<input type="button" class="btn btn-primary btn-sm"  value="조회" id="doRetrieveBtn"/>
			</div>
		 </div>  
	<!-- //검색영역 -->       
         
	<!-- row of columns -->
	<div class="container">
		<div class="row" id="rowCol">
	
		   </div>
	   </div>
	 <!-- //row of columns -->
	   	 
	 <!-- pagenation -->
		<div class="text-center">
			<div id="page-selection" class="text-center page"></div>
		</div>
	 <!--// pagenation -->	
	    
	</div>
	<!-- /container -->
	
	<!-- javascript -->
	<script type="text/javascript">
		//jquery 객체 생성 완료
		$(document).ready(function(){
			console.log("document ready");
			
			//화면 로딩시 보여줄 데이터
			doRetrieve(1);
		});
		
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
				doRetrieve(num);//ajax 서버 호출
			}); 
		}//--renderingPage
		
		
		//등록 버튼 
		//확인완료
		$("#doRegistBtn").on("click",function(e){
			console.log("doRegistBtn");
			e.preventDefault();//한번만 호출
			
			let tds = $(this).children();
			console.log("tds:"+tds);
			
			//멤버ID,qnaSeq 등록페이지로 데이터 넘김
			var memberId = tds.eq(0).text();
			console.log("memberId:"+memberId);
			
			var qnaSeq = tds.eq(1).text();
			console.log("qnaSeq:"+qnaSeq);
			
			window.location.href = "${hContext}/qna/qna_regist.do?memberId=memberId+&qnaSeq=qnaSeq" ;

		});	//--doRegistBtn
		
		
		
		//조회 버튼
		$("#doRetrieveBtn").on("click",function(e){
			console.log("doRetrieveBtn");
			e.preventDefault();//한번만 호출
			doRetrieve(1);
		});
		
		//조회버튼 실행시 
		function doRetrieve(page){
			console.log("page:"+page);
			$.ajax({
	    		type: "GET",
	    		url:"${hContext}/qna/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			pageSize:$("#pageSize").val(),
	    			searchDiv: $("#searchDiv").val(),
	    			searchWord: $("#searchWord").val(),
	    			pageNum:page
	    		},
	    		success:function(data){//통신 성공
	        		console.log("success data:"+data);
	        		var parseData = JSON.parse(data);
	        		
	        		//기존데이터 삭제
	        		$("#rowCol").empty();
	        		var html = "";
	        		
	        		//페이징 변수
	        		let totalCount = 0;//총 글수
	        		let pageTotal = 1;//총 페이지수
	        		
	        		console.log("parseData.length:"+parseData.length);
	        		console.log("totalCount:"+parseData[0].totalCount);
	        		
					//data가 있는 경우
					if(parseData.length>0){
						
						totalCount = parseData[0].totalCount;
						pageTotal  = totalCount/$("#pageSize").val();//42/10->4.2
						pageTotal = Math.ceil(pageTotal);//42/10->4.2->5
						
						
						$.each(parseData,function(i,value){
							console.log(i+","+value.name);
							html += "<div class='col-lg-4'>";
							html += "   <h2>"+value.title+"</h2>";
							html += "    <img src='${hContext}/resources/../..' alt='Image placeholder'>";
							html += "   <p>"+value.tag+"</p>";	
							html += "	<div class='row col-lg-4'>";
							html +=	"		<input type='button' class='btn btn-primary' value='Read more'/>";
							html += "	</div>";
							html += "</div>";
						});
						
					}else{//data가 없는 경우
	    				html +="<tr>";
	    				html +="	<td class='text-center' colspan='99'>등록된 게시물이 없습니다.</td>";    		
	    				html +="</tr>";   
					}
					
					//body에 데이터 추가
					$("#rowCol").append(html);	
					
					//페이징처리
					console.log(pageTotal+","+page);
					renderingPage(pageTotal,page);
		
	        	},
	        	error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
		}//--doRetrieve
		
		
		
		//data click -> doSelectOne(단건조회)로 이동
		//확인완료
		$("#rowCol").on("click",function(e){
			e.preventDefault();
			console.log("rowCol");
			
			let tds = $(this).children();
			console.log("tds:"+tds);
			
			var qnaSeq = tds.eq(1).text();
			console.log(qnaSeq);
			
			window.location.href = "${hContext}/qna/qna_detail.do?qnaSeq="+qnaSeq;	
		});
	
	</script>
	<!-- //javascript -->
</body>
</html>