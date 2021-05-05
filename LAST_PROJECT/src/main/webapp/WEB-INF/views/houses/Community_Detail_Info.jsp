<%--
/*
	Class Name:
	Description:
	Modification information
	
	수정일    	 수정자       수정내용
	------------ ---------  ------------------
	2021. 4. 26. 곽소언       최초작성
	2021. 4. 27. 곽소언       수정 
	2021. 4. 28. 곽소언       수정_댓글
	2021. 4. 29. 곽소언       수정_헤더등록, 댓글처리
	
	author eclass 개발팀
	since 2020/11/23
	
*/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../cmn/common.jsp" %>
<%@ include file="../cmn/header.jsp"%>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>집들이 단건조회</title>
	
	<!-- 부트스트랩 -->
    <%-- <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
  	<link href="${hContext}/resources/soeon/css/style.css" rel="stylesheet">
  	
  	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,500,500i,600,600i,700,700i&subset=cyrillic" rel="stylesheet">

	<!-- Vendor CSS Files -->
	<link href="${hContext}/resources/soeon/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${hContext}/resources/soeon/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
	<link href="${hContext}/resources/soeon/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
	<link href="${hContext}/resources/soeon/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
	<link href="${hContext}/resources/soeon/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

	<!-- Template Main CSS File -->
	<link href="${hContext}/resources/soeon/css/style.css" rel="stylesheet">
	
  	
    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
     <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<!-- 순서 중요함! jquery먼저 인식->bootstrap인식 -->
	<script src="${hContext}/resources/js/jquery.min.js"></script><!-- 위에 url쳐서 나오는 내용 복사해서 js파일에 그대로 붙여넣기 한거! 이거 인식 안되면 화면에서 버전 올릴 수 없다? -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>
    <script src="${hContext}/resources/js/eutil.js"></script>
    <script src="${hContext}/resources/js/jquery.bootpag.js"></script>  
    
</head>
<body>
<%-- ${vo }
${vo.memberId}
${member.memberId} 
${sessionScope.member }--%>

	<!-- div container -->
	<div class="wrap container">
<!-- ----------------------------------------------------------------------------------------------------- --> 
		<main id="main" class="scrolled-offset">
		
		    <!-- ======= Breadcrumbs Section ======= -->
		    <section class="breadcrumbs">
		      <div class="container">
		 
		        <div class="d-flex justify-content-between align-items-center">
		          <h2>${vo.title}</h2>
		        </div>
		      </div>
		    </section><!-- Breadcrumbs Section -->
		
		    <!-- ======= Portfolio Details Section ======= -->
			<section id="portfolio-details" class="portfolio-details">
				<div class="container">
			
					<div class="row gy-4">
		
					<div class="col-lg-8">
		            	<div class="portfolio-details-slider swiper-container swiper-container-initialized swiper-container-horizontal swiper-container-pointer-events">
							<div class="swiper-wrapper align-items-center" id="swiper-wrapper-b72bbc63d015cdf4" aria-live="off" style="transform: translate3d(-1804px, 0px, 0px); transition-duration: 2ms;">
		              			
		              			<div class="swiper-slide swiper-slide-duplicate swiper-slide-next swiper-slide-duplicate-prev" data-swiper-slide-index="2" role="group" aria-label="1 / 5" style="width: 451px;">
		                  			<img src="${hContext}${imagePath}" alt="">
		              			</div>
		
		                		<div class="swiper-slide swiper-slide-duplicate-active" data-swiper-slide-index="0" role="group" aria-label="2 / 5" style="width: 451px;">
		                  			<img src="${hContext}${imagePath}" alt="">
		                		</div>
		
				                <div class="swiper-slide" data-swiper-slide-index="1" role="group" aria-label="3 / 5" style="width: 451px;">
				                  <img src="${hContext}${imagePath}" alt="">
				                </div>
		
				                <div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="2" role="group" aria-label="4 / 5" style="width: 451px;">
				                  <img src="${hContext}${imagePath}" alt="">
				                </div>
		
								<div class="swiper-slide swiper-slide-duplicate swiper-slide-active" data-swiper-slide-index="0" role="group" aria-label="5 / 5" style="width: 451px;">
				                  <img src="${hContext}${imagePath}" alt="">
				                </div>
				                
							</div>
						<div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets">
		              		<span class="swiper-pagination-bullet swiper-pagination-bullet-active" tabindex="0" role="button" aria-label="Go to slide 1"></span>
			              	<span class="swiper-pagination-bullet" tabindex="0" role="button" aria-label="Go to slide 2"></span>
			              	<span class="swiper-pagination-bullet" tabindex="0" role="button" aria-label="Go to slide 3"></span>
		              	</div>
		            	<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
		            </div>
				</div>
		
				<div class="col-lg-4">
					<div class="portfolio-info">
						<h3>집들이 정보</h3>
						<ul>
			                <li><strong>집 주인</strong>: ${vo.memberId}</li>
			                <li><strong>태그</strong>: ${vo.tag}</li>
			                <li><strong>게시일</strong>: ${vo.regDt}</li>
			                <li>
			                	<div class="row text-left">
				                	<div class="col-xs-8 col-sm-9 col-md-10 col-lg-10">
		        						<input type="button" class="btn btn-primary btn-sm"  value="스크랩" id="scrap_btn"/>
		        					</div>
	        					</div>
	        				</li>
						</ul>
					</div>
					<div class="portfolio-description">
						<p>${vo.contents}</p>
		    		</div>
				</div>
		

		      </div>
		    </div>
		    
			</section><!-- End Portfolio Details Section -->
	
		</main>
<!-- ----------------------------------------------------------------------------------------------------- --> 
	    <!--  댓글  -->
	    <div class="container">
	        <label for="content">comment</label>
	        <form name="commentInsertForm">
	            <div class="input-group">
	               <input type="hidden" name="hno" value=${vo.housesSeq}/>
	               <input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요."/>
	               <span class="input-group-btn">
	                    <button class="btn btn-default" type="button" id="commentInsertBtn">등록</button>
	               </span>
	            </div>
	        </form>
    	</div>
	</div>
	

    <div class="container">

		<div class="col-xs-8 col-sm-9 col-md-8 col-lg-2 pull-right">
			<select class="form-control input-sm" name="pageSize" id="pageSize" >
				<option value="4">4개씩 보기</option>	    		  		
				<option value="8">8개씩 보기</option>
				<option value="12">12개씩 보기</option>
		    </select>			
		</div>
		
		<div id="commentList" class="commentList">

    	</div>
    	<!-- pagenation -->
		<div class="row col-lg-10 ">
			<div class="col-xs-8 col-sm-9 col-md-8 col-lg-7">
				<div id="page-selection" class="text-right page">
				
				</div>
			</div>
		</div>
		<!--// pagenation -->
		
    </div>
	





<!-- javascript -->
<script type="text/javascript">
	$(document).ready(function() {
		console.log("1.document:최초수행!");
		commentList(1);
		var houseSeq = "${vo.housesSeq}";
	});//--document ready  

	$("#pageSize").on("change", function(e){//change: SelectBox의 이벤트
    	console.log("pageSize change");
     	commentList(1);
	});
	
//------------------------댓글 이벤트처리------------------------

	//댓글목록조회
	function commentList(page){
     	$.ajax({
    		type : "GET",
    		url  : "${hContext}/reply/do_retrieve.do",
    		asyn :"true",
    		dataType:"html",
    		data:{
    			pageSize: $("#pageSize").val(),
    			pageNum: page,
    			reviewFk: "${vo.housesSeq}"
    		},
    		success:function(data){

        		console.log("success data:"+data);
     			var parseData = JSON.parse(data);
    			
    			$("#commentList").empty();
    			
    			var html = "";
    			
    			let totalCount = 0;
    			let pageTotal = 1;
    			
    			//data가 있는 경우
    			if(parseData.length>0) {
    				totalCount = parseData[0].totalCnt;
    				pageTotal =  totalCount/$("#pageSize").val(); //42/10 ->4.2
    				pageTotal = Math.ceil(pageTotal); // 42/10 ->5
    					
			        $.each(parseData, function(i, value){ 
		                html += "<div class='commentArea' style='border-bottom:1px solid darkgray; margin-bottom: 15px;'>";
		                html += "		<div>작성자: "+value.memberId + "</div>";
		                html += "		<div>내용: "+value.contents +"</div>";
		                html += "		<div>"+value.regDt +"</div>";
		                html += "		<a href='javascript:commentDelete(\""+value.replySeq+"\",\"" + value.reviewSeq + "\");'> 삭제 </a>";
		                html += "</div>"  
			        });

    			}else {//data가 없는 경우
    				html +="<div>";
    				html +="	<span class='text-center' colspan='99'> 첫 번째 댓글을 남겨보세요!</span>";    		
    				html +="</div>";    				
    			}
    			
	    		$("#commentList").append(html);
	    			
	    		console.log("pageTotal:"+pageTotal);
	    		console.log("page:"+page);
	    			
	    		//페이징 처리: 총 페이지, 현재글
	    		renderingPage(pageTotal,page);

        	},
        	error:function(data){
        		console.log("error:"+data);
        	},
        	complete:function(data){
        		console.log("complete:"+data);
        	}
    	}); 
	}

	//댓글등록버튼클릭
	$("#commentInsertBtn").on("click", function(){
		if(checkLogin() == false){
			alert("로그인 후 댓글을 남겨보세요:)")
			$("#content").val("");
			return;
		}
        if(eUtil.ISEmpty($("#content").val()) == true){
        	alert("내용을 입력 해주세요:)");
        	$("#content").focus();
        	return;            	
        }
	
		var insertData = $("#content").val();
		console.log(insertData);
	    commentInsert(insertData);
		commentList(1);
		$("#content").val("");
	});
	
	//댓글등록
	function commentInsert(insertData){
		let url = "${hContext}/reply/do_insert.do";
		let parameters = {
							"memberId"  : "${member.memberId}", 
							"reviewSeq" : "${vo.housesSeq}",    //housesSeq
							"contents"  : insertData,
							"modId"     : "${member.memberId}"      		
						};
		let method = "POST";
		let async  = true;
		
		console.log("parameters:"+parameters);
		console.log("url:"+url);
		
  		EClass.callAjax(url, parameters, method, async, function(data) {
				console.log("data:"+data); 
		}); 
  		
  		commentList(1); 
	}
	
	//댓글삭제버튼클릭
	function commentDelete(replySeq, reviewSeq){
		console.log("commentDelete call");
		console.log("replySeq : " + replySeq);

		if(confirm("삭제하시겠습니까?")==false){
			return;
		}
		
		let url = "${hContext}/reply/do_delete.do";
	 	let parameters = {
							"replySeq" : replySeq,
							"reviewSeq" : reviewSeq
						};
		let method = "POST";
		let async  = true;
		
		console.log("parameters:"+parameters);
		console.log("url:"+url);
		
		
 		EClass.callAjax(url, parameters, method, async, function(data) {
				console.log("data:"+data); 
				
  		}); 
		commentList(1); 
   		alert("댓글이 삭제되었습니다.");
	} 
//------------------------//댓글 이벤트처리------------------------

	function checkLogin(){
		console.log("checkLogin click")
		var loginId = "${member.memberId}"
			if(loginId.length>0){
				console.log(loginId.length);
				return true;
			}else{
				return false;
			}
	}

	//paging
	//pageTotal : 총페이지 수 : 총글수/페이지사이즈(10)
	//page :      현재 페이지
	//maxVisible : bottom page
	function renderingPage(pageTotal,page){
		//이전 연결된 Event 핸들러를 요소에서 제거
		$("#page-selection").unbind('page');
			
		$('#page-selection').bootpag({
		    total: pageTotal,
		    page: page,
		    maxVisible: 10,
		    leaps: true,
		    firstLastUse: true,
		    first: '←',
		    last: '→',
		    wrapClass: 'pagination',
			activeClass: 'active',
			disabledClass: 'disabled',
		    nextClass: 'next',
			prevClass: 'prev',
			lastClass: 'last',
		    firstClass: 'first'
		}).on("page", function(event, num){
			commentList(num); //or some ajax content loading
		}); 			
	}
	
	
	//스크랩 버튼 클릭 이벤트 처리
	$("#scrap_btn").on("click", function(e){
		console.log("scrap_btn click");
		if(checkLogin()==false){
			alert("로그인 후 이용 가능합니다:)");
			return;
		}else{
		
			let url = "${hContext}/scrap/do_insert.do";
			let parameters = {
								"memberId"  : "${member.memberId}", //세션 아이디
								"housesSeq" : "${vo.housesSeq}",   	//집들이seq
								"modId"     : "${member.memberId}"  //세션 아이디
							};
			let method = "POST";
			let async  = true;
			
			console.log("parameters:"+parameters);
			console.log("url:"+url);
			
	  		EClass.callAjax(url, parameters, method, async, function(data) {
					console.log("data:"+data); 
					
					if("1"==data.msgId) {//등록 성공
						alert(data.msgContents);
					}else {//등록 실패
						alert(data.msgContents);
					}	
			});
		}
	});
	
/* 	//게시물 수정 버튼 클릭 이벤트 처리
	$("#update_btn").on("click", function(e){
		console.log("update_btn click");
			window.location.href="${hcontext}/houses/test_view.do"; //화면이동

	
	}); */
	
</script>
<!--// javascript -->

	<!-- Vendor JS Files -->
	<script src="${hContext}/resources/soeon/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${hContext}/resources/soeon/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="${hContext}/resources/soeon/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="${hContext}/resources/soeon/vendor/php-email-form/validate.js"></script>
	<script src="${hContext}/resources/soeon/vendor/purecounter/purecounter.js"></script>
	<script src="${hContext}/resources/soeon/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="${hContext}/resources/soeon/vendor/waypoints/noframework.waypoints.js"></script>
		
	<!-- Template Main JS File -->
	<script src="${hContext}/resources/soeon/js/main.js"></script>
</body>
</html>