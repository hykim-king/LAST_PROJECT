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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../cmn/common.jsp" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<%
	
	session.setAttribute("member", session.getAttribute("member"));
%>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>회원</title>
	
	<!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
    <script src="${hContext}/resources/js/jquery.min.js"></script>
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
    <script src="${hContext}/resources/js/eutil.js"></script>
    <script src="${hContext}/resources/js/jquery.bootpag.js"></script>
</head>
<body >

	<!-- div container -->
	<div class="wrap container">
	    <!-- header -->
		<%-- <jsp:include page="/inc/header.jsp"></jsp:include> --%>
	    <!--//header -->
	 	<!-- 제목 -->
	 	<div class="page-header">
	 		<h2>회원정보수정</h2>
	 	</div>
	    <!--// 제목 -->
	    ${member}
	    
	    <!-- <img src="/resources/img/member/2021/04/20210427162417078e7af8fa3ee340c18a0464b13eea75e0.png" /> -->
	    <!-- form -->
	    <form action="${hContext}/image/member_upload.do" method="post" enctype="multipart/form-data"  
	    class="form-horizontal" id="doUpdateFrm">
	      <!-- hidden -->
	      <input type="hidden" class="form-control" value="1" id="div" name="div"/>
	      <input type="hidden" class="form-control" value="${member.savePath}" id="savePath" name="savePath"/>
	      <input type="hidden" class="form-control" value="${member.saveName}" id="saveName" name="saveName"/>
	      <input type="hidden" class="form-control" value="${member.imgId}" id="imgId" name="imgId"/>
	      <!-- nickCheck -->
	      <input type="hidden" class="form-control" id="nicknameStatus" name="nicknameStatus"/>
		  <div class="form-group">
		  	<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">ID</label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <input type="text" class="form-control" readonly="readonly" id="memberId" name="memberId" value="${member.memberId }" maxlength="200">
		    </div>
		  </div>
		  <div class="form-group">
		  	<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">프로필 사진</label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <img src="${hContext}${member.savePath}/${sessionScope.member.saveName}" width="200" height="200" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="reg_id" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label"></label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <input type="file" class="form-control" id="file" name="file" placeholder="파일">
		    </div>
		  </div>	
		  <div class="form-group">
		 	<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">비밀번호</label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <input type="password" class="form-control" id="passwd" name="passwd" value="${member.passwd}" maxlength="15">
		    </div>
		  </div> 	 
		  <div class="form-group">
		 	<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">닉네임</label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <input type="text" class="form-control" id="nickname" name="nickname" value="${member.nickname}" maxlength="10">
		      <input type="button" class="btn btn-primary btn-sm"  value="닉네임 확인" id="nickCheckBtn"/>
		    </div>
		  </div> 
		  <div class="form-group">
		  	<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">소개글</label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <input type="text" class="form-control" id="introduce" name="introduce" value="${member.introduce}" maxlength="100">
		    </div>
		  </div>
		  <div class="form-group"> 
		  	<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">회원등급</label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <input type="text" class="form-control" readonly="readonly" id="grade" name="grade" value="${member.grade }" maxlength="10">
		    </div>
		  </div>
		  <div class="form-group"> 
		  	<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">스크랩 수</label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <input type="text" class="form-control" readonly="readonly" id="scrap" name="scrap" value="${member.scrap }" maxlength="10">
		    </div>
		  </div>
		  <div class="form-group">
		  	<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">방문수</label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <input type="text" class="form-control" readonly="readonly" id="login" name="login" value="${member.login }" maxlength="10">
		    </div>
		  </div>
		  <div class="form-group">
		  	<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">가입일</label>
		    <div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		      <input type="text" class="form-control" readonly="readonly" id="regDt" name="regDt" value="${member.regDt }" maxlength="10">
		    </div>
		  </div>
		  <!-- 버튼 -->
		  <div class="row text-right">
		    <div   class="col-xs-8 col-sm-9 col-md-12 col-lg-12">
		      <input type="button" class="btn btn-primary btn-sm"  value="수정" id="doUpdateBtn"/>
		      <input type="button" class="btn btn-primary btn-sm"  value="취소" id="moveToMain"/>
		    </div>
		  </div>
		    <!--// 버튼 -->
	    </form>
	    
	    <!--// form -->
	    


		<!--// div container -->
	    <!--// form -->
	    	    
	    <!-- footer -->		
		<%-- <jsp:include page="/inc/footer.jsp"></jsp:include> --%>
		<!--// footer -->	
     </div>
     <!--// contents -->
    <!-- javascript -->
    <script type="text/javascript">
    
	  //jquery 객채생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");  
		});//--document ready
		
		//수정
		$("#moveToMain").on("click", function(e){
			console.log("moveToMain");
			e.preventDefault();
			
			doUpdateSession();
			window.location.href = "${hContext}/houses/home_view.do";
		});
		
    	function doUpdateSession(){
    		console.log("doUpdateSession:")
    		$.ajax({
	    		type: "POST",
	    		url:"${hContext}/member/update_session.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			memberId:$("#memberId").val(),
	    			passwd: $("#passwd").val()
	    		},
	    		success:function(data){//통신 성공
	    			console.log("data:"+data);
	    		},
	    		error:function(data){//실패시 처리
	        		console.log("error:"+data);
	        	}
    		});
    	}
		
		$("#nickCheckBtn").on("click", function(e){
			console.log("nickCheckBtn");
			e.preventDefault();
			
			let url =  "${hContext}/member/nick_check.do";
			let parameter = {"nickname" : $("#nickname").val() };
			let method = "POST";
			let async  =  true;
			
 			EClass.callAjax(url, parameter, method, async, function(data) {
 				console.log("data:"+data);
				console.log("data.msgContents:"+data.msgContents);
				
				if('${member.nickname}'==$("#nickname").val()){
					$("#nicknameStatus").val("1");
					alert("사용하실 수 있는 닉네임입니다.");
					return;
				} else if('${member.nickname}'!=$("#nickname").val()){
					if("0"==data.msgId){//사용할 수 있음
						$("#nicknameStatus").val("1");//사용할 수 있음
						alert(data.msgContents);
					}else{//사용할 수 없음
						$("#nicknameStatus").val("0");//사용할 수 없음
						alert(data.msgContents);
					}
				}
				
			});  
		});

		//수정
		$("#doUpdateBtn").on("click", function(e){
			console.log("doUpdateBtn");
			e.preventDefault();
			
			if(eUtil.ISEmpty($("#nickname").val()) == true){
				alert("닉네임을 입력하세요.");
				$("#nickname").focus();
				return;
			}
			
			if(eUtil.ISEmpty($("#nicknameStatus").val()) == true){
				alert("닉네임을 확인해주세요.");
				$("#idCheckBtn").focus();
				return;
			}
			
			if("0" == $("#nicknameStatus").val()) {
				alert("이미 등록된 닉네임입니다.");
				$("#nickname").focus();
				return;
			}
			
			if(confirm("수정 하시겠습니까?")==false)return;
			
			document.getElementById('doUpdateFrm').submit();
		});
	</script>
    <!--// javascript -->    
</body>
</html>