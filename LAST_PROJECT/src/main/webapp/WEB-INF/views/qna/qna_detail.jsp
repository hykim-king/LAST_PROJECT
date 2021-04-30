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
	
	<!-- QnA 단건조회 -->
	<section class="ftco-section ftco-degree-bg">
      <div class="container">
        <div class="row">
	        <!-- 단건조회 데이터 시작 -->	
			<div class="row" id="qnaSelectOne">
	
	       	</div>
	     	<!-- //단건조회 데이터 -->	
     			
     		<!-- 리뷰 관련 -->       
              <!-- Leave a comment -->
              <div class="comment-form-wrap pt-5">
                <h3 class="mb-5">Leave a comment</h3>
                <form action="#" class="p-5 bg-light">
                  <div class="form-group">
                    <input type="text" class="form-control" id="QnaReviewContents">
                  </div>

                  <div class="form-group">
                    <input type="submit" value="Post Comment" class="btn py-3 px-4 btn-primary" id="QnaReviewInsert">
                  </div>

                </form>
              </div>
              <!-- //Leave a comment -->
			
			
            <div class="pt-5 mt-5">
	           <h3 class="mb-5">Comments</h3>
	           <!-- 페이징 개수선택 --> 
	              	<div class="row col-lg-12">
						<div class="col-xs-8 col-sm-9 col-md-8 col-lg-2 text-right ">
						 		<select class="form-control input-sm " name="pageSize" id="pageSize">				
						    		  <option value="4">4개씩 보기</option>	    		  		
									  <option value="8">8개씩 보기</option>
									  <option value="12">12개씩 보기</option>
						    	</select> 
						</div>
					 </div>
				 <!--// 페이징 개수선택 --> 
	              <ul class="comment-list" id="qnaReviewList"><!-- start comment-list -->
	                <li class="comment">
		                  
	                </li>
	              </ul><!-- END comment-list -->

	              	 <!-- pagenation -->
						<div class="text-center">
							<div id="page-selection" class="text-center page"></div>
						</div>
					 <!--// pagenation -->	
            </div>
            <!-- //리뷰 관련-->
            
            
          </div> <!-- .col-md-8 -->
        </div><!--  <div class="row"> -->
      </div><!-- container -->
    </section> <!-- //QnA 단건조회 -->
    
    
    <!-- javascript -->
    <script type="text/javascript">
    
		//jquery 객체 생성 완료
		$(document).ready(function(){
			console.log("document ready");
	
			//화면 로딩시 보여줄 데이터
			doSelectOne();//게시물 단건조회
			doRetrieveReview();//리뷰 목록조회
		});
		
		
		
		//단건조회 페이지 화면 로딩 시 실행
		function doSelectOne(){
			console.log("function doSelectOne-QnA");
			
			var qnaSeqData ="qnaSeqTest03";
			
			 let url = "${hContext}/qna/do_selectone.do";
			 let parameter = {"qnaSeq":qnaSeqData};
			 let method = "GET";
			 let async = true;
			 
			 console.log("url:"+url);
			 console.log("parameter:"+parameter);
			 
			 EClass.callAjax(url, parameter, method, async, function(data) {
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
		
		
		
		//리뷰 작성 
		$("#QnaReviewInsert").on("click",function(e){
			console.log("QnaReviewInsert");
			
			var reviewSeqData = "";
			var memberIdData = "haram";
			var qnaSeqData = "qnaSeqTest03";
			var divData = "2";
			// div 리뷰 구분(q&a:2)
			
			 let url = "${hContext}/review/do_insert.do";
			 let parameter = {
								"reviewSeq" : reviewSeqData,
								"memberId"  : memberIdData,
								"reviewFk"  : qnaSeqData,
								"div"       : divData,
								"contents"  : $("#QnaReviewContents").val(),
								"modId"     : memberIdData
					 		 };
			 let method = "POST";
			 let async = true;
			 
			 if(confirm("리뷰를 등록하시겠습니까?")==false) return;
			 
			 EClass.callAjax(url, parameter, method, async, function(data) {
				 console.log("data:"+data.msgContents);
				 
					if("1"==data.msgId) {//등록 성공
						alert(data.msgContents);
					}else {//등록 실패
						alert(data.msgId+"\n"+data.msgContents);
					}
			 });	
			 
			 doRetrieveReview();//리뷰 목록조회
			 
		});//--QnaReviewInsert
		
		
		//리뷰 목록 - 단건조회 페이지 화면 로딩 시 실행
		function doRetrieveReview(page){
			console.log("doRetrieveReview-QnA");
			
			//해당 게시물에 작성된 리뷰 
			var reviewFkData = "qnaSeqTest03";
			
	      	$.ajax({
	    		type: "POST",
	    		url:"${hContext}/review/do_retrieve.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
						"reviewFk" :reviewFkData
	    			  },
	    		success:function(data){//통신 성공
	        		console.log("success data:"+data);
	    		
	        		var parseData = JSON.parse(data);
	        		
	        		//기존데이터 삭제
	        		$("#qnaReviewList").empty();
	        		
	        		var html = "";
	        		
	        		console.log("parseData.length:"+parseData.length);
	        		
	        		//페이징 변수
	        		let totalCount = 0;
	        		let pageTotal = 1;
	        		
	        		console.log("totalCount:"+parseData[0].totalCnt);
	        		
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
	        				html+= "<li class='comment'>";
	        				html+= "	<div class='comment-body'>";
	        				html+= "  		<h3>"+value.memberId+"</h3>";
	        				html+= "  		<div class='meta'>"+value.regDt+"</div>";
	        				html+= "  		<p>"+value.contents+"</p>";
	        				html+= "  		<p><a href='#' class='reply'>Reply</a></p>";
	        				html+= "	</div>";
	        				html+= "</li>";
	        			});
	        			
	        		}else{//data가 없는 경우
	        			html += "<tr>";
	        			html += 	"<td class='text-center' colspan'99>등록된 리뷰가 없습니다.</td>";
	        			html += "</tr>";
	        		}
	        		
	        		//tbody data add
	        		$("#qnaReviewList").append(html);
	        		
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
				doRetrieveReview(num);//ajax 서버 호출
			}); 
		}//--renderingPage

    </script>
    <!-- //javascript -->
	
</body>
</html>