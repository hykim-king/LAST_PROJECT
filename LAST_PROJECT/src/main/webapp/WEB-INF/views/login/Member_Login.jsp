<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../cmn/common.jsp" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Untitled</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${hContext }/resources/css/login.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
</head>
<body>
    <div class="login-dark">
        <form action="<c:url value='/login/login.do'/>" name="login_frm" method="POST">
            <h2 class="sr-only">Login Form</h2>
            <div class="illustration">
            	<!-- <i class="icon ion-ios-locked-outline"></i> -->
            	<img src="${hContext}/resources/images/logo2.png" width="250" align="center" />
            </div>
            <div class="form-group">
            	<input class="form-control" type="text" id="memberId" name="memberId" placeholder="이메일">
            </div>
            <div class="form-group">
            	<input class="form-control" type="password" id="passwd" name="passwd" placeholder="비밀번호">
            </div>
            <div class="form-group">
	            <input type="button" value="로그인" id="login_btn" class="btn btn-primary btn-block" />
				<input type="button" value="회원가입" id="reg_btn" class="btn btn-primary btn-block" />
            </div>
            <a id="findPasswdBtn" class="forgot">비밀번호 찾기</a>
        </form>
    </div>	
    <!--//contents  -->	
    
    <!-- script -->
  	<script type="text/javascript">
    	$(document).ready(function(){
    		console.log("document ready");
    	});
    	
    	$("#findPasswdBtn").on("click",function(e){
    		console.log("findPasswdBtn click");
    		window.location.href = "${hContext}/member/passwd_view.do";
    	});
    	
    	$("#reg_btn").on("click",function(e){
    		console.log("reg_btn click");
    		window.location.href = "${hContext}/member/reg_view.do";
    	});
    	
    	$("#login_btn").on("click",function(e){
    		console.log("login_btn click");
    		e.preventDefault();
    		
    		let url = "${hContext}/member/do_login.do";
			let parameters = {"memberId"    :$("#memberId").val(),
							  "passwd" :$("#passwd").val()
							  };
			let method = "POST";
			let async  = true; 
			
			//console.log("parameters:"+parameters);
			//console.log("url:"+url);
			
  			EClass.callAjax(url, parameters, method, async, function(data) {
 				console.log(data);
 				
 				if(null!=data && "30"==data.msgId){
 					alert(data.msgContents);
 					window.location.href = "${hContext}/houses/home_view.do";
 				} else if("10"==data.msgId){
 					alert(data.msgContents);
 					$("#memberId").focus();
 				}else if("20"==data.msgId){
 					alert(data.msgContents);
 					$("#passwd").focus();
 				} else {
 					alert(data.msgContents);
 				}
 			});
    	});
    	
    </script>
</body>

</html>