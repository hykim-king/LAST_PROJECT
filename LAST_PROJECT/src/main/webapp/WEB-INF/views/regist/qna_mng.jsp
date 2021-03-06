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
<%@ include file="../cmn/common.jsp"%>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>Intery</title>

<!-- 부트스트랩 -->
<link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

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
	<div class="container">

		<!-- 제목 -->
		<div class="page-header">
			<h2>Q&A 수정</h2>
		</div>
		<!--// 제목 -->

		<!-- form -->
		<form id="updateFrm" action="${hContext}/image/qna_upload.do" method="POST" enctype="multipart/form-data" class="form-horizontal">
			<input type="hidden" class="form-control" id="memberId" name="memberId" value="tjdus"> 	
			<input type="hidden" class="form-control" id="qnaSeq" name="qnaSeq" value="2021043024295712cc91b00cd4405794aee9f0b981d3c4"> 	
						
			<div class="form-group">
				<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">제목</label>
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<input type="text" class="form-control" id="title" name="title" placeholder="제목">
				</div>
			</div>

			<div class="form-group">
				<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">사진추가</label>
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<input type="file" class="form-control" id="image" name="image" placeholder="사진">
				</div>
			</div>

			<div class="form-group">
				<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">내용</label>
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<textarea rows="5" cols="20" name="contents" id="contents" class="form-control" placeholder="내용을 입력하세요."></textarea>
				</div>
			</div>

			<div class="form-group">
				<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">태그</label>
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<input type="text" class="form-control" id="tag" name="tag" placeholder="태그를 입력하세요.(최대 5개)">
				</div>
			</div>

			<div class="row text-right">
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<button type="submit" class="btn btn-primary btn-sm" id="doUpdate">수정</button>
					<input type="button" class="btn btn-primary btn-sm"  value="목록" id="move_to_list"/>
				</div>
			</div>

		</form>
		<!--// form -->

	</div>
	<!--// div container -->


	<script type="text/javascript">
	
		//jquery 객채생성이 완료
		$(document).ready(function() {			
			console.log("document 최초수행");
			selectQnaSeq();

		});//--document ready
		
		//목록으로 이동--------------------------------------
		$("#move_to_list").on("click", function(e){
			console.log("moveToList");
			e.preventDefault();	
			moveToList();
		
		});
				
	    function moveToList(){
	    	window.location.href = "${hContext}/qna/qna_view.do";
	    }
	    
	    //목록으로 이동--------------------------------------
		
		function selectQnaSeq(){
			console.log("selectQnaSeq()");	
			
			$.ajax({
		  		type: "GET",
		  		url:"${hContext}/qna/do_selectone.do",
		  		asyn:"true",
		  		dataType:"html",
		  		data:{
		  				qnaSeq : $("#qnaSeq").val()
		  			},
		  		success:function(data){//통신 성공
		  			console.log("data:"+data);	  		    
		      	},
		      	error:function(data){//실패시 처리
		      		console.log("error:"+data);
		      	}
		      	
		  	 });
		  }  

 		$("#doUpdate").on("click", function(e){
			console.log("doUpdate");
			e.preventDefault();
			
			if(eUtil.ISEmpty($("#title").val()) == true){
				alert("제목을 입력하세요.");
				$("#title").focus();
				return;
			}			
			
			if(eUtil.ISEmpty($("#contents").val()) == true){
				alert("내용을 입력하세요.");
				$("#contents").focus();
				return;
			}	
					
			if(eUtil.ISEmpty($("#tag").val()) == true){
				alert("태그를 입력하세요.");
				$("#tag").focus();
				return;
			}				
			
			if(confirm("수정하시겠습니까?")==false)return;
			
			document.getElementById('updateFrm').submit();			
				
			});
	
	</script>

</body>
</html>