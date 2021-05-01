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
    <title>Intery_비밀번호찾기</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${hContext }/resources/css/login.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="${hContext}/resources/js/eutil.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
</head>
<body>
    <div class="login-dark">
        <form>
            <div class="illustration">
            	<i class="icon ion-ios-locked-outline"></i>
            </div>
            <div class="form-group" id="memberIdAuth">
            	<input class="form-control" type="text" id="memberId" name="memberId" placeholder="이메일">
            </div>
            <div class="form-group">
		        <button type="button" class="btn btn-primary btn-block" id="findPasswdBtn">아이디로 비밀번호 찾기</button>
		    </div>
		    <div class="form-group">
		        <button type="button" class="btn btn-primary btn-block" id="moveToLogin">로그인</button>
		    </div>
        </form>
    </div>	
    <!--//contents  -->	
    
    <!-- script -->
  	<script type="text/javascript">
  		
  		//로그인 화면 이동
	  	$("#moveToLogin").on("click",function(e){
	  		window.location.href = "${hContext}/member/login_view.do";
	  	});
	  	
  		//비밀번호 찾기 수행
	  	$("#findPasswdBtn").on("click",function(e){
			let url =  "${hContext}/member/id_check.do";
			let parameter = {"memberId" : $("#memberId").val() };
			let method = "POST";
			let async  =  true;
			
				EClass.callAjax(url, parameter, method, async, function(data) {
					console.log("data:"+data);
				console.log("data.msgId:"+data.msgId);
				
				if("1"==data.msgId){//아이디가 존재
					alert("이메일로 비밀번호가 전송되었습니다.");
					sendPasswdEmail($("#memberId").val());
					window.location.href = "${hContext}/member/login_view.do";
				}else{//아이디가 존재하지 x
					alert("등록되지 않은 회원입니다.\n이메일을 다시 입력해주세요.");
				}
			});
		});
		
  		//비밀번호 메일 발송
		function sendPasswdEmail(member){
			$.ajax({
	    		type: "POST",
	    		url:"${hContext}/member/forgot_passwd.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			email:member
	    		},
	    		success:function(data){//통신 성공
	    			console.log("data:"+data);
	        	},
	        	error:function(data){//실패시 처리  
	        		console.log("error:"+data);
	        	}
	    	});
		}
    </script>
    <!--// javascript -->  
</body>

</html>