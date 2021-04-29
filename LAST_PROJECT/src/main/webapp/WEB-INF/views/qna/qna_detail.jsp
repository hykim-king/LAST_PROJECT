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

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  	<script src="${hContext}/resources/js/jquery.min.js"></script>
    <script src="${hContext}/resources/css/bootstrap.min.css"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
    <script src="${hContext}/resources/js/eutil.js"></script>
	<script src="${hContext}/resources/js/jquery.bootpag.js"></script>
</head>
	<body>
	<!-- div container -->
	<div class="wrap container">
	
	  <div class="container">
	  <div class="row">
			<!-- 단건조회 데이터 -->	
			<div class="row" id="qnaSelectOne">

				</div>
	
	    <!-- tag -->        
	            <div class="tag-widget post-tag-container mb-5 mt-5">
	              <div class="tagcloud">
	                <a href="#" class="tag-cloud-link">Life</a>
	                <a href="#" class="tag-cloud-link">Sport</a>
	                <a href="#" class="tag-cloud-link">Tech</a>
	                <a href="#" class="tag-cloud-link">Travel</a>
	              </div>
	            </div>
	    <!-- //tag --> 
	    </div><!--// Container -->
	    
	          
	    <!--  댓글  --><!--레이아웃 참고사이트 : https://private.tistory.com/65 -->
	    <div class="container">
	        <label for="content">comment</label>
	        <form name="commentInsertForm">
	            <div class="input-group">
	               <input type="hidden" name="bno" value="${detail.bno}"/>
	               <input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요.">
	               <span class="input-group-btn">
	                    <button class="btn btn-default" type="button" name="commentInsertBtn">등록</button>
	               </span>
	              </div>
	        </form>
	    </div>
	     <!-- // 댓글  -->
	     
	    <div class="container">
	        <div class="commentList"></div>
	    </div>
		</div><!-- //row -->
	</div><!-- //div container -->
	
	<!-- javascript -->
	<script type="text/javascript">

	
	//jquery 객체 생성 완료
	$(document).ready(function(){
		console.log("document ready");

		//화면 로딩시 보여줄 데이터
		doSelectOne("qnaSeqTest03");
	});
	
	//단건조회 페이지 화면 로딩 시 실행
	function doSelectOne(qnaSeq){
		
		console.log("function doSelectOne");
		
		 let url = "${hContext}/qna/do_selectone.do";
		 let parameter = {"qnaSeq":qnaSeq};
		 let method = "GET";
		 let async = true;
		 
		 console.log("url:"+url);
		 console.log("parameter:"+parameter);
		 
		 EClass.callAjax(url, parameter, method, async, function(data) {
			 console.log("data:"+data);
			 
			 console.log("data.qnaSeq:"+data.qnaSeq);

			 	var html = "";
			 	
				html += "<div>";
				html += "<hr class='my-2'>";
				html += "	<h2 class='mb-3'>"+data.title+"</h2>";
				html += "<hr class='my-2'>";
				html += "    <img src='' alt='Image placeholder'>";
				html += "<p>"+data.contents+"</p>";
				html += "</div>";
			 
				//body에 데이터 추가
				$("#qnaSelectOne").append(html);	
		 });

	}//--doSelectOne
	
	</script>
	<!-- //javascript -->
		
</body>
</html>