<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일    	 수정자       수정내용
	------------ ---------  ------------------
	2021. 4. 26. 곽소언       최초작성
	2021. 4. 27. 곽소언       수정1
	
	author eclass 개발팀
	since 2020/11/23
	
*/
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>집들이 단건조회</title>
	
	<!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<!-- 순서 중요함! jquery먼저 인식->bootstrap인식 -->
	<!-- <script src="${hContext}/resources/js/jquery.min.js"></script>  --> <!-- 위에 url쳐서 나오는 내용 복사해서 js파일에 그대로 붙여넣기 한거!이거 인식 안되면 화면에서 버전 올릴 수 없다? -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
    <script src="${hContext}/resources/js/eutil.js"></script>
    <script src="${hContext}/resources/js/jquery.bootpag.js"></script>  
</head>
<body>
	${vo }
	${image }
	<!-- div container -->
	<div class="wrap container">
	   <!-- header -->
		 <jsp:include page="../cmn/header.jsp"></jsp:include> 
	   <!--//header -->
	   
	   	 <!-- 제목 -->
	 	<div class="page-header">
	 		<h2>타이틀</h2>
	 	</div>
	    <!--// 제목 -->
	    
	    <!-- 버튼 -->
	    <div class="row text-right">
	        <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
	        	<input type="button" class="btn btn-primary btn-sm"  value="스크랩" id="scrap_btn"/>
	        	<input type="button" class="btn btn-primary btn-sm"  value="수정" id="update_btn"/>
	        </div>
	    </div>
	    <!--// 버튼 -->

        <!-- table -->
		<div class="table-responsive">
		    <!-- table -->
			<table id="housePost" class="table table-striped table-bordered table-hover table-condensed">
	
			</table>
		</div>
        <!--// table -->
	</div>
	<!--// div container -->
	
	
	
	    <!--  댓글  -->
	    <div class="container">
	        <label for="content">comment</label>
	        <form name="commentInsertForm">
	            <div class="input-group">
	               <input type="hidden" name="bno" value="${detail.bno}"/>
	               <input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요.">
	               <span class="input-group-btn">
	                    <button class="btn btn-default" type="button" id="commentInsertBtn">등록</button>
	               </span>
	            </div>
	        </form>
    	</div>

	

    <div class="container">
        <div id="commentList" class="commentList">

    	</div>
    		<select class="form-control input-sm" name="pageSize" id="pageSize" >
	    		<option value="4">4개씩 보기</option>	    		  		
				<option value="8">8개씩 보기</option>
				<option value="12">12개씩 보기</option>
	    	</select>	
    	<!-- pagenation -->
		<div class="row col-lg-12">
			<div id="page-selection" class="text-right page">
				
			</div>
		</div>
		<!--// pagenation -->
    </div>








<!-- javascript -->
<script type="text/javascript">


	//jquery 객채생성이 완료
	$(document).ready(function() {
		console.log("1.document:최초수행!");
		commentList(1);
		doSelectOne(1234);  //임시 seq값. 집들이 목록으로부터 seq값 전달받기
		
	});//--document ready  

	$("#pageSize").on("change", function(e){//change: SelectBox의 이벤트
    	console.log("pageSize change");
     	commentList(1);
	});
	
//------------------------댓글 이벤트처리------------------------
	//댓글등록버튼클릭
	$("#commentInsertBtn").on("click", function(){ //댓글 등록 버튼 클릭시 
		
        if(eUtil.ISEmpty($("#content").val()) == true){
        	alert("내용을 입력 해주세요.");
        	$("#content").focus();
        	return;            	
        }
	
		var insertData = $("#content").val(); //commentInsertForm의 내용을 가져옴
		console.log(insertData);
	    commentInsert(insertData); //Insert 함수호출(아래)
		commentList(1);
		$("#content").val("");
	});

	//댓글 목록
	function commentList(page){
     	$.ajax({
    		type : "GET",
    		url  : "${hContext}/reply/do_retrieve.do",
    		asyn :"true",
    		dataType:"html",
    		data:{
    			pageSize: $("#pageSize").val(),
    			searchDiv: $("#searchDiv").val(),
    			searchWord: $("#searchWord").val(),
    			pageNum: page
    		},
    		success:function(data){//통신 성공

        		console.log("success data:"+data);
     			var parseData = JSON.parse(data);
    			
    			//기존 데이터 삭제.
    			$("#commentList").empty();
    			
    			var html = "";
    			
    			let totalCount = 0;
    			let pageTotal = 1;
    			
    			//data가 있는 경우
    			if(parseData.length>0) {
    				totalCount = parseData[0].totalCnt;
    				pageTotal =  totalCount/$("#pageSize").val(); //42/10 ->4.2
    				pageTotal = Math.ceil(pageTotal); // 42/10 ->5
    					
    			        $.each(parseData, function(i, value){ 
    			        	html += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
    			            html += '<div class="commentInfo'+value.cno+'">'+'댓글번호 : '+value.cno+' / 작성자 : '+value.memberId;
    			            html += '<a onclick="commentUpdate('+value.cno+',\''+value.content+'\');"> 수정 </a>';
    			            html += '<a onclick="commentDelete('+value.cno+');"> 삭제 </a> </div>';
    			            html += '<div class="commentContent'+value.cno+'"> <p> 내용 : '+value.contents +'</p>';
    			            html += '</div></div>';
    			        });

    				
    			}else {//data가 없는 경우
    				html +="<tr>";
    				html +="	<td class='text-center' colspan='99'>등록된 게시물이 없습니다.</td>";    		
    				html +="</tr>";    				
    			}
    			
	    		//body에 데이터 추가
	    		$("#commentList").append(html);
	    			
	    		console.log("pageTotal:"+pageTotal);
	    		console.log("page:"+page);
	    			
	    		//페이징 처리: 총 페이지, 현재글
	    		renderingPage(pageTotal,page);

        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	}); 
	}

	//댓글 등록 처리
	function commentInsert(insertData){
		let url = "${hContext}/reply/do_insert.do";
		let parameters = {
							"memberId"  : "asdfg",     //임시 아이디
							"reviewSeq" : "1234",      //임시 seq
							"contents"  : insertData,
							"modId"     : "asdfg"      //임시 아이디
						};
		let method = "POST";
		let async  = true;
		
		console.log("parameters:"+parameters);
		console.log("url:"+url);
		
  		EClass.callAjax(url, parameters, method, async, function(data) {
				console.log("data:"+data); 
				
				
		}); 
	}
//------------------------//댓글 이벤트처리------------------------


		//paging
		//pageTotal : 총페이지 수 : 총글수/페이지사이즈(10)
		//page :      현재 페이지
		//maxVisible : bottom page
		function renderingPage(pageTotal,page){
			//이전 연결된 Event 핸들러를 요소에서 제거
			$("#page-selection").unbind('page');
			
			$('#page-selection').bootpag({
			    total: pageTotal,
			    page: page,
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
				commentList(num); // or some ajax content loading...
			}); 			
		}
	
	
	//스크랩 버튼 클릭 이벤트 처리
	$("#scrap_btn").on("click", function(e){
		console.log("scrap_btn click");
		//해당 게시물 스크랩 유무별 처리 로직 
		
		//if(로그인유무false){
		//	alert("로그인 후 이용가능합니다.");
		//}
		//else{
			
			let url = "${hContext}/scrap/do_insert.do";
			let parameters = {
								"memberId"  : "soeon",  //임시 아이디
								"housesSeq" : "1234",   //임시 seq
								"modId"     : "soeon"   //임시 아이디
							};
			let method = "POST";
			let async  = true;
			
			console.log("parameters:"+parameters);
			console.log("url:"+url);
			
	  		EClass.callAjax(url, parameters, method, async, function(data) {
					console.log("data:"+data); 
			}); 
		//}
	});
	
	
	//게시물 수정 버튼 클릭 이벤트 처리
	$("#update_btn").on("click", function(e){
		console.log("update_btn click");
		
		//집들이 수정 화면으로 전환, houses_seq값 전달 어케함....
		
/* 		let url = "${hcontext}/houses/test_view.do";
		let parameters = {	"housesSeq" : 1111
					     };
		let method     = "POST";
		let async      = true;
		console.log("url: " + url);
		console.log("parameters: " + parameters);
	 	
		 EClass.callAjax(url, parameters, method, async, function(data){
			console.log("data: " + data); */
			window.location.href="${hcontext}/houses/test_view.do"; //화면이동
			
//		}); 

		
	
	});
	
	function doSelectOne(housesSeq){
        	let url = "${hContext }/houses/do_selectone.do";
			let parameters = {"housesSeq": housesSeq};
			let method     = "GET";
			let async      = true;
			
			console.log("parameters:"+parameters);
			console.log("url:"+url);
			
  			EClass.callAjax(url, parameters, method, async, function(data) {
				console.log("data:"+data);
				console.log("data.housesSeq:"+data.housesSeq);
				
				var html = "";
				html +="<tr>";
				html +="	<td class='text-center'>"+data.title+"</td>";
				html +="</tr>";
				html +="<tr>";
				html +="	<td class='text-center'>"+data.memberId+"</td>";
				html +="	<td class='text-center'>"+data.regDt+"</td>";
				html +="</tr>";
				html +="<tr>";
				html +="	<td class='text-center'>"+data.imgId+"</td>";
				html +="</tr>";
				
				//tbody에 데이터 추가
        		$("#housePost").append(html);   
			});  
	}






</script>
<!--// javascript -->

</body>
</html>