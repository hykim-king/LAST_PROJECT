<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../cmn/common.jsp" %>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!--  This file has been downloaded from bootdey.com    @bootdey on twitter -->
    <!--  All snippets are MIT license http://bootdey.com/license -->
    <title>Intery_회원정보수정</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${hContext}/resources/css/profile.css">
    <script src="${hContext}/resources/js/eclass.js"></script>
    <script src="${hContext}/resources/js/eutil.js"></script>
    <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<form action="${hContext}/image/member_upload.do" method="post" enctype="multipart/form-data"  
	    class="form-horizontal" id="doUpdateFrm">
<!-- hidden -->
<input type="hidden" class="form-control" value="1" id="div" name="div"/>
<input type="hidden" class="form-control" value="${member.savePath}" id="savePath" name="savePath"/>
<input type="hidden" class="form-control" value="${member.saveName}" id="saveName" name="saveName"/>
<input type="hidden" class="form-control" value="${member.imgId}" id="imgId" name="imgId"/>
<input type="hidden" class="form-control" value="${member.regDt}" id="regDt" name="regDt"/>
<!-- nickCheck -->
<input type="hidden" class="form-control" id="nicknameStatus" name="nicknameStatus"/>
<div class="row gutters">
	<div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
		<div class="card h-100">
			<div class="card-body">
				<div class="account-settings">
					<div class="user-profile">
						<div class="user-avatar">
							<h5 class="user-name"> </h5>
							<h5 class="user-name"> </h5>
						</div>
						<div class="user-avatar">
						<img src="<c:choose>
									<c:when test="${empty member.savePath}">
										${hContext}/resources/images/logo.png
									</c:when>
									<c:otherwise>
										${hContext}${member.savePath}/${member.saveName}
									</c:otherwise>
								</c:choose>">
							<%-- <img src="${hContext}${member.savePath}/${member.saveName}"> --%>
						</div>
						<h5 class="user-name">${member.nickname}</h5>
						<h6 class="user-email">${member.memberId }</h6>
						<h6 class="user-email">${member.regDt }</h6>
					</div>
					<div class="about">
						<h5 class="mb-2 text-primary">____________</h5>
						<h5 class="mb-2 text-primary">Profile</h5>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
		<div class="card h-100">
			<div class="card-body">
				<div class="row gutters">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<h6 class="mb-3 text-primary title-font">회원정보수정</h6>
						<h6 class="mb-3 text-primary">____________</h6>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
						<div class="form-group">
							<label for="memberId">아이디</label>
							<input type="text" readonly="readonly" class="form-control" id="memberId" name="memberId" value="${member.memberId }">
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
						<div class="form-group">
							<label for="file">프로필사진</label>
							<input type="file" class="form-control" id="file" name="file" placeholder="파일">
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
						<div class="form-group">
							<label for="passwd">비밀번호</label>
							<input type="password" style="font-family:consolas;" class="form-control" id="passwd" name="passwd" value="${member.passwd }">
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
						<div class="form-group">
							<label for="nickname">닉네임</label>
							<input type="text" class="form-control" id="nickname" name="nickname" value="${member.nickname }">
							<a  href="#" style="cursor:pointer;" id="nickCheckBtn" class="sub-button" onclick="nickCheckBtn();">닉네임 확인</a>
						</div>
					</div>
				</div>
				<div class="row gutters">
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
						<div class="form-group">
							<label for="intro">소개글</label>
							<input type="text" class="form-control" id="introduce" name="introduce" value="${member.introduce}" placeholder="소개글을 입력하세요.">
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
						<div class="form-group">
							<label for="grade">회원등급</label>
							<input type="text" readonly="readonly" class="form-control" id="grade" name="grade" value="${member.grade}">
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
						<div class="form-group">
							<label for="scrap">스크랩 수</label>
							<input type="text" class="form-control" readonly="readonly" id="scrap" name="scrap" value="${member.scrap }" maxlength="10">
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
						<div class="form-group">
							<label for="login">방문수</label>
							<input type="text" class="form-control" readonly="readonly" id="login" name="login" value="${member.login }" maxlength="10">
						</div>
					</div>
				</div>
				<div class="row gutters">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="text-right">
							<button type="button" id="moveToMain" class="btn btn-secondary">취소</button>
							<button type="button" id="doUpdateBtn" class="btn btn-primary">수정</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</div>
 <!-- javascript -->
    <script type="text/javascript">
    
	 	//jquery 객채생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
		});//--document ready
		
		//취소버튼 클릭 시 메인페이지로 이동
		$("#moveToMain").on("click", function(e){
			console.log("moveToMain");
			e.preventDefault();
			
			doUpdateSession();
			window.location.href = "${hContext}/mypage/Member_MyPage.do";
		});
		
		//회원 수정 후 session 업데이트
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
		
		//닉네임 중복확인
		function nickCheckBtn(){
			console.log("nickCheckBtn");
			
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
		}

		//회원정보수정
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