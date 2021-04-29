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
	<div class="container">

		<!-- 제목 -->
		<div class="page-header">
			<h2>집들이 등록</h2>
		</div>
		<!--// 제목 -->

		<!-- form -->
		<form action="${hContext}/houses/houses_upload.do" method="POST"
			enctype="multipart/form-data" class="form-horizontal" id="uploadFrm">

			<div class="form-group">
				<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">제목</label>
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<input type="text" class="form-control" id="title" name="title" placeholder="제목">
				</div>
			</div>

			<div class="form-group">
				<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">내용</label>
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<textarea rows="5" cols="20" name="contents" id="contents" class="form-control" placeholder="내용을 입력하세요."></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">사진추가</label>
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<input type="file" class="form-control" id="image" name="image" placeholder="사진">
				</div>
			</div>			
			
			<div class="form-group">
				<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">태그</label>
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<input type="text" class="form-control" id="tag" name="tag" placeholder="태그를 입력하세요.(최대 5개)">
				</div>
			</div>
			
			<div class="form-group" id='linkFrm'>
				<label class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">링크</label>
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10 form-inline">
					<input type="text" class="form-control" id="tag" name="tag" placeholder="URL은 최대 3개까지 입력할 수 있습니다.">
					<input type="button" class="btn btn-primary btn-sm"  value="추가" id="addLink">
				</div>
			</div>			

		</form>
		<!--// form -->
		
			<div class="row text-right">
				<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
					<input type="button" class="btn btn-primary btn-sm" value="등록" placeholder="등록" id="doInsert">
				</div>
			</div>
			
			<!-- 값 임의 설정 -->
			<input type="hidden" class="form-control" id="housesSeq" name="housesSeq" value="">
			<input type="hidden" class="form-control" id="memberId" name="memberId" value="tjdus">
			<input type="hidden" class="form-control" id="imgId" name="imgId" value="">
			<input type="hidden" class="form-control" id="regDt" name="regDt" value="">
			<input type="hidden" class="form-control" id="modId" name="modId" value="tjdus2">

	</div>
	<!--// div container -->


	<script type="text/javascript">
	
		var count = 0;
	
		//jquery 객채생성이 완료
		$(document).ready(function() {
			console.log("document 최초수행");

		});//--document ready
		
		
		// house doInsert ------------------------------
		$("#doInsert").on("click", function(e){
			console.log("doInsert");
			e.preventDefault();
			
			if(confirm("등록하시겠습니까?")==false)return;
			
			let url = "${hContext}/houses/do_insert.do";
			let parameter = {
								"housesSeq"      : $("#housesSeq").val(),
								"memberId"     : $("#memberId").val(),
								"imgId"   : $("#imgId").val(),
								"title"     : $("#title").val(),
								"contents"   : $("#contents").val(),
								"tag"    : $("#tag").val(),
								"regDt"     : $("#regDt").val(),
								"modId"   : $("#modId").val(),
								"modDt"   : $("#modDt").val()
							};
			let method = "POST";
			let async = true;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
				console.log("data: "+data);
				//console.log("data.msgContents: "+data.msgContents);
				
				if("1"==data.msgId){//등록성공
					alert(data.msgContents);
				}else{//등록실패
					alert(data.msgId+"\n"+data.msgContents);
				}
		
			});

		});
		// house doInsert ------------------------------	
	
		// 링크 html ------------------------------
		var maxAppend = 1; // 버튼누른 횟수 저장
	
		$("#addLink").on("click",function(){
			console.log("addLink");  					
			
			if (maxAppend >= 3) return; // 4번째부터는 append 안되고 return 시키기
			
    		var html =  "<div class='form-group' id='linkFrm'>                                                                            ";
    			html += "	<label class='col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label'>링크</label>                     		  	  ";
    			html += "	<div class='col-xs-8 col-sm-9 col-md-10 col-lg-10 form-inline'>                                               ";
    			html += "		<input type='text' class='form-control' id='tag' name='tag' placeholder='URL은 최대 3개까지 입력할 수 있습니다.'> ";
    			html += "		<input type='button' class='btn-del btn btn-primary btn-sm'  value='삭제'>						  ";
    			html += "	</div>                                                                                                        ";
    			html += "</div>																										      ";
    		
			$("#uploadFrm").append(html);   	
			maxAppend++;
			
			}); 

		
		$(document).on("click",".btn-del",function(e){
			console.log("btn_del_file");
			$(this).parent().parent().remove();
			
		});
		// 링크 html ------------------------------

	</script>

</body>
</html>