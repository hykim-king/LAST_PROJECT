<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일             수정자     수정내용
	------------    -------  ------------------------------------
	2021. 4. 30.     곽소언     최초작성
	
	author eclass 개발팀
	since 2020/11/23
	
*/
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../cmn/header.jsp"%>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>인테리</title>
	
	<!-- 부트스트랩 -->
 	  <!-- <link href="${hContext}/resources/scrap/css/style.css" rel="stylesheet"> --> 
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
	<script src="${hContext}/resources/js/jquery.min.js"></script> <!-- 위에 url쳐서 나오는 내용 복사해서 js파일에 그대로 붙여넣기 한거!이거 인식 안되면 화면에서 버전 올릴 수 없다? -->
    <script src="${hContext}/resources/js/bootstrap.min.js"></script>
    <script src="${hContext}/resources/js/eclass.js"></script>

</head>
<body>


	<!-- div container -->
	<div class="wrap container">
<!-- 
		카테고리존
		<div class="container">
			<div class="row">
	    		<div class="container">
					<ul class="filter__controls">
						<li class="active">스크랩북</li>
					</ul>
					<div id="memberInfo" class="col-lg-3 col-md-3">
						<h5>회원 닉네임</h5>
					</div>
					<hr>

				</div>
			</div>
		</div> -->


<!-- --------------------------------------------------------------------------------------------------- -->

	 	<!-- ======= Portfolio Section ======= -->
	    <section id="portfolio" class="portfolio section-bg">
	      <div class="container">
	
	        <div class="section-title">
	          <h2>스크랩북</h2>
	          <p>Magnam dolores commodi suscipit. Necessitatibus eius consequatur ex aliquid fuga eum quidem. Sit sint consectetur velit. Quisquam quos quisquam cupiditate. Et nemo qui impedit suscipit alias ea. Quia fugiat sit in iste officiis commodi quidem hic quas.</p>
	        </div>
	
	        <div class="row">
	          <div class="col-lg-12 d-flex justify-content-center">
	            <ul id="portfolio-flters">
	              <li data-filter="*" class="filter-active">All</li>
	              <li data-filter=".filter-app">App</li>
	              <li data-filter=".filter-card">Card</li>
	              <li data-filter=".filter-web">Web</li>
	            </ul>
	          </div>
	        </div>
	
	        <div class="row portfolio-container">
	
	   		  <!-- row -->	
			  <div id="rowCard" class="row">
	
			  </div>	
			   <!-- //row -->	
	
		    	<!-- pagenation -->
					<div class="row col-lg-10">
						<div class="col-xs-8 col-sm-9 col-md-8 col-lg-7">
							<div id="page-selection" class="text-right page">
							
							</div>
						</div>
			
						<div class="col-xs-8 col-sm-9 col-md-8 col-lg-3">
							<select class="form-control input-sm" name="pageSize" id="pageSize" >
					    		<option value="4">4개씩 보기</option>	    		  		
								<option value="8">8개씩 보기</option>
								<option value="12">12개씩 보기</option>
				    		</select>			
						</div>
					</div>
				<!--// pagenation -->
	
	        </div>
	
	      </div>
	    </section><!-- End Portfolio Section -->











<!-- --------------------------------------------------------------------------------------------------- -->


<!-- 
		상품존
		<div class="container">
			<div class="row product__filter" id="MixItUp666159">
			</div>
		</div>
		//상품존 -->

	</div>
	<!--// div container -->











<!-- javascript -->
	<script type="text/javascript">
		//jquery 객채생성이 완료
		$(document).ready(function() {
			console.log("1.document:최초수행!");
			doRetrieve(1);
		});//--document ready  
	
		
	function doRetrieve(page) {
		console.log("doRetrieve() call");
		
		$.ajax({
				type: "GET",
		    	url:"${hContext}/scrap/do_retrieveById.do",
		    	asyn:"true",
		    	dataType:"html",
		    	data:{
		    			pageSize: $("#pageSize").val(),
		    			//searchDiv: "",
		    			//searchWord: "", 
		    			pageNum: page,
		    			searchWord:"soeon"  //세션아이디
		    	},
		    		
		    	success:function(data){//통신 성공
		        	console.log("success data:"+data);
		        	var parseData = JSON.parse(data);
		    
		    		//기존 데이터 삭제
		    		var html="";
		    		console.log("parseData.length:"+parseData.length);
		    		$("#rowCard").empty();
		    			
		    			
	    			//data가 있는 경우
		    		if(parseData.length>0){
		    				
		    			$.each(parseData, function(i, value) {
		    				console.log("value");
		    					
							html += '<div class="col-lg-4 col-md-6 portfolio-item filter-card">';
							html += '	<div class="portfolio-wrap">';
							html += '		<img src="${hContext}/resources/soeon/img/portfolio/portfolio-8.jpg" class="img-fluid" alt="">';
							html += '   	<div class="portfolio-info">';
							html += '      		<h4>' + value.memberId + "님의 집에 놀러가보세요!" + '</h4>';
							html += '    	</div>';
							html += '    	<div class="portfolio-links">';
							html += '      		<a onclick="deleteScrap(\''+value.scrapSeq+'\');" data-gallery="portfolioGallery" class="portfolio-lightbox" title="Card 3">';
							html += '      			<i class="bx bx-tag"></i></a>';
							html += '      		</a>';
							html += '     		<a href="${hContext}/houses/houses_detail.do?housesSeq='+value.housesSeq+'" title="구경가기"><i class="bx bx-link"></i></a>  ';   
							html += '    	</div>';
							html += '	</div>';
							html += '</div>';
							
						//	html += '		<a href="${hContext}/houses/houses_detail.do?housesSeq='+value.housesSeq+'" data-gallery="portfolioGallery" class="portfolio-lightbox" title="Card 3"></a>';
							
/* html += '<a href="${hContext}/houses/houses_detail.do?housesSeq='+value.housesSeq+'"  data-gallery="portfolioGallery" class="portfolio-lightbox" title="Card 3">'
									
							<a href='${hContext}/houses/houses_detail.do?housesSeq="+value.housesSeq+"'> */
		    			});
		    				
		    		}else{ //data가 없는 경우
		    				html += " <div class='col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals'>";
		    				html += "    <div class='product__item'>";
		    				html += " 	   <div class='product__item__pic set-bg' data-setbg='${hContext}/resources/store/img/img01.JPG' style='background-image: url('${hContext}/resources/img/img01.JPG');'>";
		    				html += " 	   </div>";
		    				html += " 	   <div class='product__item__text' id='productItem'>";
		    				html += "			<div>";
		    				html += "				<h5 class='text-center' colspan='99'>마음에 드는 집을 스크랩 해보세요!</h5>";    		
		    				html += "			</div>";
		    				html += " 	   </div>";
		    				html += "    </div>";
		    				html += " </div>";
		    		}
	    				
		    		//tbody에 데이터 추가
		    		$("#rowCard").append(html);
		    		
		        },
		        error:function(data){//실패시 처리
		        		console.log("error:"+data);
		        },
		        complete:function(data){//성공/실패와 관계없이 수행!
		        	console.log("complete:"+data);
		        }
		}); 
		
	}	
		
		
		

		
	function deleteScrap(srcapSeq){
		console.log("deleteScrap call");
		
		if(confirm("스크랩을 취소하시겠습니까?")==false){
			return;
		}
		
		
		let url = "${hContext}/scrap/do_delete.do";
		let parameters = {
							"scrapSeq"  : srcapSeq	
						};
		let method = "POST";
		let async  = true;
		
		console.log("parameters:"+parameters);
		console.log("url:"+url);
		
   		EClass.callAjax(url, parameters, method, async, function(data) {
				console.log("data:"+data); 
   		});
   		
   		
   		doRetrieve(1);
   		alert("스크랩이 취소되었습니다.");
	}
		
		
	
		
		
		
		
	
	</script>

</body>
</html>