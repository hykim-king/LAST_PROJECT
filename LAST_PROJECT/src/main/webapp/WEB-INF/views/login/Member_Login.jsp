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
    <title>Intery_당신의 집에 가치를 더하다</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${hContext }/resources/css/login.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
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
            <div class="form-group" align="center">
	            <input type="button" value="로그인" id="login_btn" class="btn btn-primary btn-block" />
				<input type="button" value="회원가입" id="reg_btn" class="btn btn-primary btn-block" />
				<a href="javascript:kakaoLogin();"><img src="https://gb.go.kr/Main/Images/ko/member/certi_kakao_login.png" style="width:240px;align:center;margin-top:10px;"></a>
				<div id="naver_id_login" style="margin-top:10px;"></div>
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
    	
    	<!-- 네이버 아이디로 로그인 -->
		var naver_id_login = new naver_id_login("7Tx_yEf8B2xRnpXcyFk2", "http://localhost:8080/last/member/callback_view.do");
		var state = naver_id_login.getUniqState();
		naver_id_login.setButton("green", 3,52);
		naver_id_login.setDomain(".service.com");
		naver_id_login.setState(state);
		naver_id_login.init_naver_id_login();
  		<!-- // 네이버 아이디 로로그인 -->
    	
    	<!-- 카카오톡 연동 API -->
    	window.Kakao.init("54131d3569613dce759403ac3bb3bfa6");

    	function kakaoLogin(){
    		window.Kakao.Auth.login({
    			scope: 'profile, account_email',
    			success: function(authObj){
    				console.log(authObj);
    				window.Kakao.API.request({
    					url:'/v2/user/me',
    					success: res => {
    						const kakao_account= res.kakao_account;
    						console.log(kakao_account);
    						console.log(kakao_account.profile.nickname);
    						console.log(kakao_account.email);
    						doLogin(kakao_account.email,kakao_account.profile.nickname);
    					}
    				});
    			}
    		});
    	}
    	
    	//카카오톡으로 로그인하기
    	function doLogin(email,nickname){
    		let url = "${hContext}/member/do_sns_login.do";
    		let parameters = {"memberId" :email,
    						  "nickname" :nickname
    						  };
    		let method = "POST";
    		let async  = true; 
    		
    		EClass.callAjax(url, parameters, method, async, function(data) {
    			console.log(data);
    			
    			if(null!=data && "20"==data.msgId){
    				alert(data.msgContents);
    				window.location.href = "${hContext}/houses/home_view.do";
    			} else { 
    				alert("등록되지 않은 회원입니다.\n회원가입 창으로 이동합니다");
    				window.location.href = "${hContext}/member/reg_view.do";
    			}
    		});  
    	}
    	
    	//비밀번호 찾기 화면으로 이동
    	$("#findPasswdBtn").on("click",function(e){
    		console.log("findPasswdBtn click");
    		window.location.href = "${hContext}/member/passwd_view.do";
    	});
    	
    	//회원가입 화면으로 이동
    	$("#reg_btn").on("click",function(e){
    		console.log("reg_btn click");
    		window.location.href = "${hContext}/member/reg_view.do";
    	});
    	
    	//로그인 수행
    	$("#login_btn").on("click",function(e){
    		console.log("login_btn click");
    		e.preventDefault();
    		
    		let url = "${hContext}/member/do_login.do";
			let parameters = {"memberId"    :$("#memberId").val(),
							  "passwd" :$("#passwd").val()
							  };
			let method = "POST";
			let async  = true; 

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