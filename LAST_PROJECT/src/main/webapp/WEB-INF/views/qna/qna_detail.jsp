<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  ---------------------------------------------
    2021. 4. 26.       	임하람 
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.---------
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

	<title>QNA DETAIL</title>
	
	  <!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">
<%--     <link href="${hContext}/resources/css/detail.css" rel="stylesheet">
    <link href="${hContext}/resources/css/detail-style.css" rel="stylesheet"> --%>

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
	${vo }
   <input type="hidden" name="qnaSeq"   id="qnaSeq" value="${vo.qnaSeq}" />
	<!-- QnA 단건조회 -->
	<section class="ftco-section ftco-degree-bg">
      <div class="container">
        <div class="row">
	        <!-- 단건조회 데이터 시작 -->	
			<div class="row" id="qnaSelectOne">
	
	       	</div>
	     	<!-- //단건조회 데이터 -->	
	     
	    <!-- 버튼 -->	
	    <div class="col-xs-12">
	        <div class="btn-group btn-group-sm" role="group" style="float:right;">
	          <input type="button" class="btn btn-default"  value="목록" id="moveToQnaList"/>
	          <input type="button" class="btn btn-default"  value="삭제" id="doDeleteQna"/>
	          <input type="button" class="btn btn-default"  value="수정" id="doUpdateQna"/>
	        </div>
	    </div>
		<!--// 버튼 -->	
 			
 	    <!--  댓글  -->
	    <div class="container">
	        <label for="content">comment</label>

	        <form name="commentInsertForm">
	            <div class="input-group">
	               <input type="hidden" name="bno" value=""/>
	               <input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요.">
	               <span class="input-group-btn">
	                    <button class="btn btn-default" type="button" id="commentInsertBtn">등록</button>
	               </span>
	            </div>
	        </form>
    	</div> 
    	
    	<!-- 페이징 선택 -->	        
	   <div class="col-xs-8 col-sm-9 col-md-8 col-lg-2">
    		<select class="form-control input-sm" name="pageSize" id="pageSize" >
	    		<option value="4">4개씩 보기</option>	    		  		
				<option value="8">8개씩 보기</option>
				<option value="12">12개씩 보기</option>
	    	</select>
	    </div>	
	    <!-- //페이징 선택 -->
	    <br/>
	    <br/>
	       
    	
    <div class="container">
        <div id="commentList" class="commentList">

    	</div>

    	<!-- pagenation -->
		<div class="row col-lg-12">
			<div id="page-selection" class="text-right page">
				
			</div>
		</div>
		<!--// pagenation -->
    </div>
    <!-- // 댓글  -->		
            
            
          </div> <!-- .col-md-8 -->
        </div><!--  <div class="row"> -->
    </section> <!-- //QnA 단건조회 -->
    
    
    <!-- javascript -->
    <script type="text/javascript">
    
		//jquery 객체 생성 완료
		$(document).ready(function(){
			console.log("document ready");
	
			//화면 로딩시 보여줄 데이터
			doSelectOne();//게시물 단건조회
			commentList(1);//리뷰 목록조회
		});
		
		$("#pageSize").on("change", function(e){
	    	console.log("pageSize change");
	     	commentList(1);
		});
		
//게시물 관련--------------------------------------------------
//단건조회 페이지 화면 로딩 시 실행
		function doSelectOne(){
			console.log("function doSelectOne-QnA");
			
			 let url = "${hContext}/qna/do_selectone.do";
			 let parameters = {"qnaSeq":$("#qnaSeq").val()};
			 let method = "GET";
			 let async = true;
			 
			 console.log("url:"+url);
			 console.log("parameters:"+parameters);
			 
			 EClass.callAjax(url, parameters, method, async, function(data) {
				 console.log("data:"+data);
				 console.log("data.qnaSeq:"+data.qnaSeq);

				 	var html = "";
				 	
				 	html+="  <div class='col-lg-8 ftco-animate'>";
				 	html+="		<h2 class='mb-3'>"+data.title+"</h2>";     
				 	html+="          <p><img src='' alt='Image placeholder' class='img-fluid'></p>";
				 	html+="        <p>"+data.contents+"</p>"; 
				 	html+="    <div class='tag-widget post-tag-container mb-5 mt-5'>";
				 	html+="      <div class='tagcloud'>";
				 	html+="        <a href='#' class='tag-cloud-link'>"+data.tag+"</a>";
				 	html+="      </div>";
				 	html+="   </div>";
				 
					//body에 데이터 추가
					$("#qnaSelectOne").append(html);	
			 });

		}//--doSelectOne

//게시물 관련 버튼 이벤트
//1)목록화면으로 이동 
		$("#moveToQnaList").on("click",function(e){
			console.log("moveToQnaList");
			
			window.location.href = "${hContext}/qna/qna_view.do?";
		});//--moveToQnaList
	
//2)게시물 수정버튼
		$("#doUpdateQna").on("click",function(e){
			console.log("doUpdateQna");
			e.preventDefault();//기능 안먹음..여러번 클릭됨 ㅜ
			

		});//--doUpdateQna///
	
//3)게시물 삭제버튼 
		$("#doDeleteQna").on("click",function(e){
			console.log("doDeleteQna");
			e.preventDefault();//기능 안먹음..여러번 클릭됨 ㅜ
	
		});//--doDeleteQna
	


//게시물 관련--------------------------------------------------
		
		
//댓글--------------------------------------------------
//리뷰 등록버튼
		$("#commentInsertBtn").on("click",function(e){
			console.log("commentInsertBtn");
			
	        if(eUtil.ISEmpty($("#content").val()) == true){
	        	alert("내용을 입력 해주세요.");
	        	$("#content").focus();
	        	return;            	
	        }
		
			var insertData = $("#content").val(); //commentInsertForm의 내용을 가져옴
			console.log("insertData:"+insertData);
			
		    commentInsert(insertData); //댓글등록 함수호출
		    
			commentList(1);
		    
			$("#content").val("");			
			
		});//--QnaReviewInsert	
		
//댓글 등록 
		function commentInsert(insertData){
			
			let url = "${hContext}/reply/do_insert.do";
			let parameters = {
								"memberId"  : "haram",     //임시 아이디
								"reviewSeq" : "${vo.qnaSeq}",    //housesSeq      //임시 seq
								"contents"  : insertData,
								"modId"     : "haram"      //임시 아이디
							};
			let method = "POST";
			let async  = true;
			
			console.log("parameters:"+parameters);
			console.log("url:"+url);
			
	  		EClass.callAjax(url, parameters, method, async, function(data) {
					console.log("data:"+data); 
					
					if("1"==data.msgId) {//등록 성공
						alert(data.msgContents);
						commentList(1);//리뷰 목록조회
					}else {//등록 실패
						alert(data.msgId+"\n"+data.msgContents);
					}	
			}); 		
		}//--commentInsert	
		
		
		
//댓글 목록 - 단건조회 페이지 화면 로딩 시 실행
		function commentList(page){
			console.log("commentList-QnA");

	      	$.ajax({
	    		type : "GET",
	    		url  : "${hContext}/reply/do_retrieve.do",
	    		asyn :"true",
	    		dataType:"html",
	    		data:{
	    			pageSize: $("#pageSize").val(),
	    			pageNum: page,
	    			reviewFk: "${vo.qnaSeq}"
	    		},
	    		success:function(data){//통신 성공
	    			
	        		console.log("success data:"+data);
	        		var parseData = JSON.parse(data);
	        		
	        		//기존데이터 삭제
	        		$("#commentList").empty();
	        		
	        		var html = "";
	        		
	        		//페이징 변수
	        		let totalCount = 0;
	        		let pageTotal = 1;
	        		
	        		//data가 있는 경우
	        		if(parseData.length>0){
	        			
	        			totalCount = parseData[0].totalCnt;
	        			console.log("totalCount:"+totalCount);
	        			
	        			pageTotal = (totalCount/$("#pageSize").val());//42/10->4.2
	        			console.log("pageTotal:"+pageTotal);
	        			
	        			pageTotal = Math.ceil(pageTotal);//42/10->4.2->5
	        			console.log("pageTotal:"+pageTotal);
	        			
	        			
	        			$.each(parseData,function(i,value){
	        				//console.log(i+","+value.name);
	        				<!-- 문자: 왼쪽, 숫자: 오른쪽, 같으면: 가운데 -->
			                html += "<div class='commentArea' style='border-bottom:1px solid darkgray; margin-bottom: 15px;'>";
			                html += "	<div class='commentInfo"+value.cno+"'>"+" 작성자 : "+value.memberId;
			                html += "		<a onclick='commentDelete(\""+value.replySeq+"\",\"" + value.reviewSeq + "\");'> 삭제 </a>";
			                html += "		<p> 내용 : "+value.contents +"</p>";
			                html += "	</div>"
			                html += "</div>"  
	        			});
	        			
	        		}else{//data가 없는 경우
	        			html += "<tr>";
	        			html += 	"<td class='text-center' colspan'99>첫 번째 댓글을 남겨보세요!</td>";
	        			html += "</tr>";
	        		}
	        		
	        		//tbody data add
	        		$("#commentList").append(html);
	        		
	        		//페이징처리 :총페이지,현재글
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
	      	
		}//--doRetrieveReview	
		
//댓글 이벤트 
//1) 댓글 삭제
		function commentDelete(replySeq,reviewSeq){
			console.log("commentDelete");
			console.log("replySeq:"+replySeq);
			
			if(confirm("댓글을 삭제하시겠습니까?")==false) return;
	
 			 let url = "${hContext}/reply/do_delete.do";
			 let parameters = {
								"replySeq" : replySeq,
								"reviewSeq" : reviewSeq
					 		 };
			 let method = "POST";
			 let async = true;
			
			console.log("parameters:"+parameters);
			console.log("url:"+url);

			EClass.callAjax(url , parameters, method ,async, function(data){
				console.log("data"+data);
				
				if("1"==data.msgId) {//삭제 성공
					alert(data.msgContents);
					commentList(1);//리뷰 목록조회
				}else {//삭제 실패//
					alert(data.msgId+"\n"+data.msgContents);
				}	
				
			}); 
			 
		}//--commentDelete

		
		
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
				commentList(num);//ajax 서버 호출
			}); 
		}//--renderingPage	
		
//댓글--------------------------------------------------


    </script>
    <!-- //javascript -->
	
</body>
</html>