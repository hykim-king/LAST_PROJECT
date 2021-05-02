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
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>Intery_네이버 아이디로 로그인</title>
	
	<!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
    <script src="${hContext}/resources/js/jquery.min.js"></script>
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
</head>
<body>
	<script type="text/javascript">
	  var naver_id_login = new naver_id_login("7Tx_yEf8B2xRnpXcyFk2", "http://localhost:8080/last/member/callback_view.do");
	  // 네이버 사용자 프로필 조회
	  naver_id_login.get_naver_userprofile("naverSignInCallback()");
	  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	  function naverSignInCallback() {
	    var email = naver_id_login.getProfileData('email');
	    var nickname = naver_id_login.getProfileData('nickname');
	    
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
	</script>
</body>
</html>