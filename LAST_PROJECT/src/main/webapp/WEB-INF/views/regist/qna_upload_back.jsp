<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../cmn/common.jsp"%>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,viewport-fit=cover" />
<meta name="naver-site-verification" content="907a7f8c9a61df897ccf0f03a835f6d2668945b1" />
<meta property="fb:admins" content="100006457426639" />
<meta name="google-site-verification" content="d-veBobRNo_in0thN3uOOoIcUWMUOjQZeMidTyItfGk" />
<meta name="google-site-verification" content="AZK3GfRwg4q_EH-0OSbbkCurocEY7okWrPZF93COxYU" />

<title>Intery</title>

<!-- phone 즐겨찾기 아이콘 설정 -->
<link rel="alternate" href="android-app://net.bucketplace/http/ohou.se/deep" />
<link rel="apple-touch-icon" sizes="57x57" href="/assets/bookmark_icon/favicon_57x57.png" />
<link rel="apple-touch-icon" sizes="72x72" href="/assets/bookmark_icon/favicon_72x72.png" />
<link rel="apple-touch-icon" sizes="114x114" href="/assets/bookmark_icon/favicon_114x114.png" />
<link rel="apple-touch-icon" sizes="144x144" href="/assets/bookmark_icon/favicon_144x144.png" />
<link rel="shortcut icon" href="/assets/bookmark_icon/favicon_144x144.png" />
<link href="https://static.ohou.se/assets/favicon-186ac0d991a44c522f984d86e6a50d24c65b7b3a02a004ba7e13f5722aabd952.png" rel="shortcut icon" />
<script type="text/javascript" src="//static.criteo.net/js/ld/ld.js" async="true"></script>

<meta name="csrf-param" content="authenticity_token" />
<meta name="csrf-token" content="0V9P1ftsK8zosAse5xBLJy0T+W1fIUa27Yx86gJElyzL+i5IhOYPKpL7fLsaot5+XjcLeYodT2MFo58n9P4EEw==" />

<script src="https://static.ohou.se/dist/js/194-d44a2468c3407aa37514.js"></script>
<script src="https://static.ohou.se/dist/js/vendor-dcbe06d9855203355c47.chunk.js"></script>
<script src="https://static.ohou.se/dist/js/193-66bdb6510874890bdddb.js"></script>
<script src="https://static.ohou.se/dist/js/4-071f6e7b12de575d895d.chunk.js"></script>
<script src="https://static.ohou.se/dist/js/7-e0222204942b9172423d.chunk.js"></script>
<script src="https://static.ohou.se/dist/js/utils-921658ce09bda989d792.chunk.js"></script>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700&display=swap&subset=korean" rel="stylesheet">
<link rel="stylesheet" media="all" href="https://static.ohou.se/assets/v3/bucket_ui/bucket_ui-cf437a161bdb44b28d08d72550bb85e52b07cf425b26f21c6c412cc524af5a4c.css" />
<link rel="stylesheet" media="screen" href="https://static.ohou.se/assets/v3/layout/application_simplified-292aaabc44d7729c34b0c1af5b3aa9b030c551b99804d8f9ee75b0a53eaf76a2.css" />
<script src="https://static.ohou.se/assets/v3/application_simplified-ae04e029483bf0d229f40e6cb9d19892ec667a70558f59e649fb366f2ee0dbd9.js"></script>
<link rel="stylesheet" media="screen" href="https://static.ohou.se/assets/v3/bucket_ui/components-aba4a2f67926f213a42ac70be01cb690e099b8322468e5f451fb06854a9eac80.css" />
<script src="https://static.ohou.se/assets/v3/components-6019c7548988bcd561b81198c535da7f6212a74ec466624fb2c30c9030a2809e.js"></script>
<link rel="stylesheet" media="all" href="https://static.ohou.se/assets/v3/questions/form-e8d0a44a21b35012d2bec7c4deef8dc434f5ce4ffaf638217463987acb38c8b7.css" />
<script src="https://static.ohou.se/assets/v3/questions/form-3f5f83810b3b4853590a845ae3f3a1f785f740891cbe56134c480b663fbcf7a0.js"></script>
<script src="https://static.ohou.se/assets/v2/common/img_uploader-a40e9821b82bc9199f48dd59c50db0f44aceab819574810686b33b51624c5349.js"></script>

<script src="${hContext}/resources/js/eclass.js"></script>
<script src="${hContext}/resources/js/eutil.js"></script>

</head>
<body>

	<header id="simplified-gnb" class="simplified-gnb">
		<div class="container simplified-gnb__container">
			<a class="simplified-gnb__logo" href="https://ohou.se/"> <span
				class="icon icon-etc-brand-icon-n-bi-md" aria-hidden="true"></span>
			</a>
		</div>
	</header>
	
	<main role="main">
	
		<div class="question-form container">
			<form id="regFrm" action="${hContext}/qna/qna_upload.do" method="POST" enctype="multipart/form-data" class="form-horizontal">
			
				<h2 class="question-form__header__heading text-black bold">질문하기</h2>
				
				<div class="form-group">
					<label for="title" class="col-xs-4 col-sm-3 col-md-2 col-lg-2 control-label">제목</label>
					<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
						<input type="text" class="form-control" id="title" name="title" placeholder="제목">
					</div>
				</div>	
											
				<section>

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
							
				</section>	
				
				<footer class="question-form__footer">
					<div class="question-form__footer__submit row">
						<button type="submit" class="btn btn-lg btn-priority col-6 offset-3" value="등록" id="doInsert">등록</button>
					</div>
				</footer>
					
			</form>
		</div>
		
	</main>

	<script type="text/javascript">
	
		//jquery 객채생성이 완료
		$(document).ready(function() {
			console.log("document 최초수행");

		});//--document ready
		
		$("#doInsert").on("click", function(e){
			console.log("doInsert");
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
			
			if(confirm("등록하시겠습니까?")==false)return;
			
			$.ajax({
		  		type: "POST",
		  		url:"${hContext}/qna/do_insert.do",
		  		asyn:"false",
		  		dataType:"html",
		  		data:{
			  			qnaSeq: $("#qnaSeq").val(),
			  			memberId: $("#memberId").val(),
			  			imgId: $("#imgId").val(),
			  			title: $("#title").val(),
			  			contents: $("#contents").val(),
			  			tag: $("#tag").val(),
			  			regDt: $("#regDt").val()
			  		},
		  		
		  		success:function(data){//통신 성공
		      		var message = JSON.parse(data);
		  			console.log(message.msgContents);
		  			
		  			//상품 이미지 등록
		  			$.ajax({
		  		  		type: "POST",
		  		  		url:"${hContext}/image/do_insert.do",
		  		  		asyn:"true",
		  		  		dataType:"html",
		  		  		data:{
				  		  		imgId: $("#imgId").val(),
				  		  		imgNum: $("#imgNum").val(),
					  		  	orgName: $("#orgName").val(),
						  		savePath: $("#savePath").val(),
						  		imgSize: $("#imgSize").val(),
						  		imgExt: $("#imgExt").val(),		  		  						  		  		
				  		  	},
				  		  	
		  		  		success:function(data){//통신 성공
		  		      		var message = JSON.parse(data);
		  		  			console.log(message.msgContents);
		  		  			alert("등록이 완료되었습니다.");
		  		  			//window.location.href = "${hContext}/qna/qna_list.do";
		  		      	},
		  		      	error:function(data){//실패시 처리
		  		      		console.log("error:"+data);
		  		      	},
		  		      	complete:function(data){//성공/실패와 관계없이 수행!
		  		      		console.log("complete:"+data);
		  		      	}
		  		  	});
		  			
		      	},
		      	error:function(data){
		      		console.log("error:"+data);
		      	},
		      	complete:function(data){
		      		console.log("complete:"+data);
		      	}
		  	});
		  	
		  });
		
	</script>

</body>
</html>