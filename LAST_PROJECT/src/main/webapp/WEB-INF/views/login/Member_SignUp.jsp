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
    <title>Intery_회원가입</title>
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
            <!-- idCheck -->
		    <input type="hidden" class="form-control" id="memberIdStatus" name="uIdStatus"/>
		    <!-- nickCheck -->
		    <input type="hidden" class="form-control" id="nicknameStatus" name="nicknameStatus"/>
		    <!-- authCheck -->
		    <input type="hidden" class="form-control" id="memberAuthStatus" name="memberAuthStatus"/>
            <div class="illustration">
            	<i class="icon ion-ios-person-outline"></i>
            </div>
            <div class="form-group" id="memberIdAuth">
            	<input class="form-control" type="text" id="memberId" name="memberId" placeholder="이메일" value='<c:if test="${null != sessionScope.member  }">${sessionScope.member.memberId }</c:if>'>
            	<a id="idCheckBtn" class="sub-button">이메일 확인</a>
            </div>
            <div class="form-group">
            	<input class="form-control" type="password" id="passwd" name="passwd" placeholder="비밀번호">
            </div>
            <div class="form-group">
            	<input class="form-control" type="text" id="nickname" name="nickname" placeholder="닉네임" value='<c:if test="${null != sessionScope.member  }">${sessionScope.member.nickname }</c:if>'>
            	<a id="nickCheckBtn" class="sub-button">닉네임 확인</a>
            </div>
            <div class="form-group">
		        <button type="button" class="btn btn-primary btn-block" id="doInsertBtn">가입하기</button>
		        <button type="button" class="btn btn-primary btn-block" id="moveToLogin">로그인</button>
		    </div>
        </form>
    </div>	
    <!--//contents  -->	
    
    <!-- script -->
  	<script type="text/javascript">
    	
   	 	//인증번호 전역변수
   	 	var auth = '';
    
		//jquery 객채생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
		});//--document ready
		
		$("#moveToLogin").on("click", function(e){
			window.location.href = "${hContext}/member/login_view.do";
		});
		
		//인증번호 입력 input 추가
		const add_auth = () => {
	            const box = document.getElementById("memberIdAuth");
	            const newP = document.createElement('p');
	            newP.innerHTML = "<input type='text' id='auth' name='auth' placeholder='인증번호 입력'> <input type='button' class='btn btn-primary btn-sm'  value='인증확인' id='checkAuthBtn'/> ";
	            memberIdAuth.appendChild(newP);
	    }
		
		//인증번호 check
		$(document).on("click","#checkAuthBtn",function(e){ 
			console.log("checkAuthBtn");
			e.preventDefault();
			
			var authNum = $('input:text[name="auth"]').val();
			console.log("authNum:"+authNum);
			
 			if(auth==authNum){
				alert("인증되었습니다.");
				$('input:text[name="auth"]').attr('readonly', true);
				$("#memberAuthStatus").val("1");
			}else {
				alert("인증번호를 다시 입력해주세요.");
			} 
		});
		
		//인증번호 메일 발송
		function sendAuthEmail(email){
			$.ajax({
	    		type: "POST",
	    		url:"${hContext}/member/send_email.do",
	    		asyn:"true",
	    		dataType:"html",
	    		data:{
	    			email:$("#memberId").val(),
	    		},
	    		success:function(data){//통신 성공
	    			console.log("data:"+data);
	        		auth = data;
	        		console.log("auth:"+auth);
	        	},
	        	error:function(data){//실패시 처리  
	        		console.log("error:"+data);
	        	},
	        	complete:function(data){//성공/실패와 관계없이 수행!
	        		console.log("complete:"+data);
	        	}
	    	});
		}
		
		//아이디 check
		$("#idCheckBtn").on("click", function(e){
			console.log("idCheckBtn");
			e.preventDefault();
			
			if(null == $("#memberId").val()){
				alert("이메일을 입력해주세요.");
				return;
			}
			
			let url =  "${hContext}/member/id_check.do";
			let parameter = {"memberId" : $("#memberId").val() };
			let method = "POST";
			let async  =  true;
			
			EClass.callAjax(url, parameter, method, async, function(data) {
 				console.log("data:"+data);
				console.log("data.msgContents:"+data.msgContents);
				
				let member = '<%=session.getAttribute("member")%>';
				console.log(member);
					
				//"msgId":"1","msgContents"
				if("0"==data.msgId){//사용할 수 있음
					$("#memberIdStatus").val("1");//사용할 수 있음
					if('null'==member){
						alert("인증번호가 전송되었습니다.");
						sendAuthEmail($("#memberId").val());
						add_auth();
					}else{
						$("#memberAuthStatus").val("1");
						alert(data.msgContents);
					}
				}else{//사용할 수 없음
					$("#memberIdStatus").val("0");//사용할 수 없음
					alert(data.msgContents);
				}
			});
		});
		
		//닉네임 check
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
				
				//"msgId":"1","msgContents"
				if("0"==data.msgId){//사용할 수 있음
					$("#nicknameStatus").val("1");//사용할 수 있음
				}else{//사용할 수 없음
					$("#nicknameStatus").val("0");//사용할 수 없음
				}
				alert(data.msgContents);
			});  
		});

		//등록
		$("#doInsertBtn").on("click",function(e){
			console.log("doInsertBtn");
			e.preventDefault();
			console.log("memberId:"+$("#memberId").val());
			
			//"" IDcheck 버튼을 눌러주세요.
			//   ID를 확인해주세요.
			if(eUtil.ISEmpty($("#memberId").val()) == true){
				alert("아이디를 입력하세요.");
				$("#memberId").focus();
				return;
			}
			
			if(eUtil.ISEmpty($("#memberIdStatus").val()) == true){
				alert("아이디를 확인해주세요.");
				$("#idCheckBtn").focus();
				return;
			}
			
			if(eUtil.ISEmpty($("#memberAuthStatus").val()) == true){
				alert("인증번호를 입력해주세요.");
				$('input:text[name="auth"]').focus();
				return;
			}

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
			
			if(eUtil.ISEmpty($("#passwd").val()) == true){
				alert("비밀번호를 입력하세요.");
				$("#passwd").focus();
				return;
			}
			
			if("0" == $("#memberIdStatus").val()) {
				alert("이미 가입된 이메일입니다.");
				$("#memberId").focus();
				return;
			}
			
			if("0" == $("#nicknameStatus").val()) {
				alert("이미 등록된 닉네임입니다.");
				$("#nickname").focus();
				return;
			}

			if(confirm("등록 하시겠습니까?")==false)return;
			
			let url =  "${hContext}/member/do_insert.do";
			let parameter = {  "memberId"    : $("#memberId").val(),
								"passwd"     : $("#passwd").val(),
								"nickname"   : $("#nickname").val() };
			let method = "POST";
			let async  =  true;
			
 			EClass.callAjax(url, parameter, method, async, function(data) {
				console.log("data.msgContents:"+data.msgContents);
				//"msgId":"1","msgContents"
				if("1"==data.msgId){//등록 성공
					alert(data.msgContents);
					window.location.href = "${hContext}/member/login_view.do";
				}else{//등록 실패
					alert(data.msgId+"\n"+data.msgContents);
				}
			});   
		});
		
    </script>
    <!--// javascript -->  
</body>

</html>