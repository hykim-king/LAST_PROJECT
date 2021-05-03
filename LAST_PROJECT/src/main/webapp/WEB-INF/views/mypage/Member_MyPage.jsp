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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../cmn/header.jsp"%>
<c:set var="hContext" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>bootstrap form</title>

<!-- 부트스트랩 -->
<link href="${hContext }/resources/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="${hContext }/resources/jji/mypage.css" />

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- <script scr="hContext/resources/js/jquery.min.js"></script> -->
<script src="${hContext}/resources/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="main-body">

			<nav class="navbar navbar-expand-lg navbar-light bg-light">
  

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="nav justify-content-center">
    
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          프로필
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <button id = "testt" class="dropdown-item" >집들이</button>
          <div class="dropdown-divider"></div>
          <button id = "test" class="dropdown-item" >질문과답변</button>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          나의 쇼핑
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <button id = "test3" class="dropdown-item">주문배송내역 조회</button>
          <div class="dropdown-divider"></div>
          <button id = "test5" class="dropdown-item">등록상품내역</button>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          나의 리뷰
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <button id = "test6" class="dropdown-item" >내가 작성한 리뷰</button>
        </div>
      </li>
    </ul>
  </div>
</nav>

			<div class="container">
    <div class="main-body">
    ${sessionScope.member }
          <div id="profile" class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5350%2F2020%2F12%2F11%2F0000097162_001_20201211110314106.jpg&type=sc960_832" width="150">
                    <div class="mt-3">
                      <h4>${member.nickname}</h4>
                      <p class="text-secondary mb-1">${member.introduce}</p>
                      <button id="scrap_book_btn" class="btn btn-primary">스크랩북</button>
                      <button id="mng_btn" class="btn btn-outline-primary">회원정보수정</button>
                    </div>
                  </div>
                </div>
              </div>
              
            </div>
            <div class="col-md-8">
              <div class="card mb-3">
                <div class="card-body">
                  <div id="content_box">
                  </div>
                </div>
              </div>
              
            </div>
          </div>
        </div>
    </div>
	
	

</body>
<script type="text/javascript">

$("#scrap_book_btn").on("click",function(e){
	console.log("scrap_book_btn click");
	window.location.href = "${hContext}/mypage/scrap_list.do";
});

$("#mng_btn").on("click",function(e){
	console.log("reg_btn click");
	window.location.href = "${hContext}/member/mng_view.do";
});

$("#testt").on("click",function(e){//집들이
	console.log("dotestBtn");
	e.preventDefault();//한번만 호출
	doRetrieve(1);
	function doRetrieve(page){
		console.log("page:"+page);
		$.ajax({
    		type: "GET",
    		url:"${hContext}/houses/do_retrieve.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			memberId:"${member.memberId }",
    			pageSize:3,
    			searchDiv: 10,
    			searchWord: "",
    			pageNum:page
    		},
    		success:function(data){//통신 성공
        		console.log("success data:"+data);
        		var parseData = JSON.parse(data);
        		
        		//기존데이터 삭제
        		$("#content_box").empty();
        		var html = "";
        		
        		//페이징 변수
        		let totalCount = 0;//총 글수
        		let pageTotal = 1;//총 페이지수
        		
        		console.log("parseData.length:"+parseData.length);
        		console.log("totalCount:"+parseData[0].totalCount);
        		
				//data가 있는 경우
				if(parseData.length>0){
					
					totalCount = parseData[0].totalCount;
					pageTotal  = totalCount/4;//42/10->4.2
					pageTotal = Math.ceil(pageTotal);//42/10->4.2->5
					
					
					$.each(parseData,function(i,value){
						console.log(i+","+value.name);
						html += "<div class='col-lg-4'>";
						html += "   <h2>"+value.title+"</h2>";
						html += "    <img src='${hContext}/resources/../..' alt='Image placeholder'>";
						html += "   <p>"+value.tag+"</p>";	
						html += "	<div class='row col-lg-4'>";
						html +=	"		<input type='button' class='btn btn-primary' value='Read more'/>";
						html += "	</div>";
						html += "</div>";
					});
					
				}else{//data가 없는 경우
    				html +="<tr>";
    				html +="	<td class='text-center' colspan='99'>등록된 게시물이 없습니다.</td>";    		
    				html +="</tr>";   
				}
				
				//body에 데이터 추가
				$("#content_box").append(html);	
				
				//페이징처리
				console.log(pageTotal+","+page);
				//renderingPage(pageTotal,page);
	
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	});
	}//--doRetrieve
	
	$("#content_box").on("click",function(e){
		e.preventDefault();
		console.log("rowCol");
		
		let tds = $(this).children();
		console.log("tds:"+tds);
		
		var qnaSeq = tds.eq(1).text();
		console.log(qnaSeq);
		
		window.location.href = "${hContext}/qna/qna_detail.do?qnaSeq="+qnaSeq;	
	});

	
});

$("#test").on("click",function(e){//질문과답변
	console.log("dotestBtn");
	e.preventDefault();//한번만 호출
	doRetrieve(1);
	function doRetrieve(page){
		console.log("page:"+page);
		$.ajax({
    		type: "GET",
    		url:"${hContext}/qna/do_retrieve.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			pageSize:3,
    			searchDiv: 10,
    			searchWord: "",
    			pageNum:page
    		},
    		success:function(data){//통신 성공
        		console.log("success data:"+data);
        		var parseData = JSON.parse(data);
        		
        		//기존데이터 삭제
        		$("#content_box").empty();
        		var html = "";
        		
        		//페이징 변수
        		let totalCount = 0;//총 글수
        		let pageTotal = 1;//총 페이지수
        		
        		console.log("parseData.length:"+parseData.length);
        		console.log("totalCount:"+parseData[0].totalCount);
        		
				//data가 있는 경우
				if(parseData.length>0){
					
					totalCount = parseData[0].totalCount;
					pageTotal  = totalCount/4;//42/10->4.2
					pageTotal = Math.ceil(pageTotal);//42/10->4.2->5
					
					
					$.each(parseData,function(i,value){
						console.log(i+","+value.name);
						html += "<div class='col-lg-4'>";
						html += "   <h2>"+value.title+"</h2>";
						html += "    <img src='${hContext}/resources/../..' alt='Image placeholder'>";
						html += "   <p>"+value.tag+"</p>";	
						html += "	<div class='row col-lg-4'>";
						html +=	"		<input type='button' class='btn btn-primary' value='Read more'/>";
						html += "	</div>";
						html += "</div>";
					});
					
				}else{//data가 없는 경우
    				html +="<tr>";
    				html +="	<td class='text-center' colspan='99'>등록된 게시물이 없습니다.</td>";    		
    				html +="</tr>";   
				}
				
				//body에 데이터 추가
				$("#content_box").append(html);	
				
				//페이징처리
				console.log(pageTotal+","+page);
				//renderingPage(pageTotal,page);
	
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	});
	}//--doRetrieve
	
	$("#content_box").on("click",function(e){
		e.preventDefault();
		console.log("rowCol");
		
		let tds = $(this).children();
		console.log("tds:"+tds);
		
		var qnaSeq = tds.eq(1).text();
		console.log(qnaSeq);
		
		window.location.href = "${hContext}/qna/qna_detail.do?qnaSeq="+qnaSeq;	
	});

	
});

$("#test3").on("click",function(e){//주문배송내역 조회
	console.log("test3");
	e.preventDefault();//한번만 호출
	doRetrieve(1);
	function doRetrieve(page){
		console.log("page:"+page);
		$.ajax({
    		type: "GET",
    		url:"${hContext}/payment/do_retrieve.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			pageSize:4,
    			memberId:"${member.memberId }",
    			pageNum:page
    		},
    		success:function(data){//통신 성공
        		console.log("success data:"+data);
        		var parseData = JSON.parse(data);
        		
        		//기존데이터 삭제
        		$("#content_box").empty();
        		var html = "";
        		
        		//페이징 변수
        		let totalCount = 0;//총 글수
        		let pageTotal = 1;//총 페이지수
        		
        		console.log("parseData.length:"+parseData.length);
        		
				//data가 있는 경우
				if(parseData.length>0){
					
					totalCount = parseData[0].totalCount;
					pageTotal  = totalCount/4;//42/10->4.2
					pageTotal = Math.ceil(pageTotal);//42/10->4.2->5
					
					html += "<table class ='table'>";
					html += "	<thead>";
					html += "		<th scope = 'col'>#</th>";
					html += "		<th scope='col'>상품명</th>";
					html += "		<th scope='col'>금액</th>";
					html += "		<th scope='col'>날짜</th>";
					html += "		<th scope='col'>결재상태</th>";
					html += "	</thead>";
					html += "	<tbody>";
				     					
					$.each(parseData,function(i,value){
						console.log(i+","+value.name);
						html += "<tr>";
						html += "   <th scope ='row'>"+(i+1)+"</th>";
						html += "    <td>"+value.title+"</td>";
						html += "    <td>"+value.price+"</td>";
						html += "    <td>"+value.regDt+"</td>";
						html += "    <td>"+value.status+"</td>";
						html += "</tr>";
					});
					html += "	</tbody>";
					html += "</table>";
					
				}else{//data가 없는 경우
    				html +="<tr>";
    				html +="	<td class='text-center' colspan='99'>등록된 게시물이 없습니다.</td>";    		
    				html +="</tr>";   
				}
				
				//body에 데이터 추가
				$("#content_box").append(html);	
				
				//페이징처리
				console.log(pageTotal+","+page);
				//renderingPage(pageTotal,page);
	
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	});
	}//--doRetrieve
});

$("#test5").on("click",function(e){//등록상품내역
	console.log("test5");
	e.preventDefault();//한번만 호출
	doRetrieve(1);
	function doRetrieve(page){
		console.log("page:"+page);
		$.ajax({
    		type: "GET",
    		url:"${hContext}/product/do_retrieve.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			pageSize:4,
    			memberId:"${member.memberId }",
    			pageNum:page
    		},
    		success:function(data){//통신 성공
        		console.log("success data:"+data);
        		var parseData = JSON.parse(data);
        		
        		//기존데이터 삭제
        		$("#content_box").empty();
        		var html = "";
        		
        		//페이징 변수
        		let totalCount = 0;//총 글수
        		let pageTotal = 1;//총 페이지수
        		
        		console.log("parseData.length:"+parseData.length);
        		
				//data가 있는 경우
				if(parseData.length>0){
					
					totalCount = parseData[0].totalCount;
					pageTotal  = totalCount/4;//42/10->4.2
					pageTotal = Math.ceil(pageTotal);//42/10->4.2->5
					
					html += "<table class ='table'>";
					html += "	<thead>";
					html += "		<th scope = 'col'>#</th>";
					html += "		<th scope='col'>상품명</th>";
					html += "		<th scope='col'>가격</th>";
					html += "		<th scope='col'>등록일</th>";
					html += "	</thead>";
					html += "	<tbody>";
				     					
					$.each(parseData,function(i,value){
						console.log(i+","+value.name);
						html += "<tr>";
						html += "   <th scope ='row'>"+(i+1)+"</th>";
						html += "    <td>"+value.title+"</td>";
						html += "    <td>"+value.price+"</td>";
						html += "    <td>"+value.regDt+"</td>";
						html += "</tr>";
					});
					html += "	</tbody>";
					html += "</table>";
					
				}else{//data가 없는 경우
    				html +="<tr>";
    				html +="	<td class='text-center' colspan='99'>등록된 게시물이 없습니다.</td>";    		
    				html +="</tr>";   
				}
				
				//body에 데이터 추가
				$("#content_box").append(html);	
				
				//페이징처리
				console.log(pageTotal+","+page);
				//renderingPage(pageTotal,page);
	
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	});
	}//--doRetrieve
});

$("#test6").on("click",function(e){//나의 리뷰
	console.log("test6");
	e.preventDefault();//한번만 호출
	doRetrieve(1);
	function doRetrieve(page){
		console.log("page:"+page);
		$.ajax({
    		type: "GET",
    		url:"${hContext}/review/do_retrieve.do",
    		asyn:"true",
    		dataType:"html",
    		data:{
    			pageSize:4,
    			memberId:"${member.memberId }",
    			pageNum:page
    		},
    		success:function(data){//통신 성공
        		console.log("success data:"+data);
        		var parseData = JSON.parse(data);
        		
        		//기존데이터 삭제
        		$("#content_box").empty();
        		var html = "";
        		
        		//페이징 변수
        		let totalCount = 0;//총 글수
        		let pageTotal = 1;//총 페이지수
        		
        		console.log("parseData.length:"+parseData.length);
        		
				//data가 있는 경우
				if(parseData.length>0){
					
					totalCount = parseData[0].totalCount;
					pageTotal  = totalCount/4;//42/10->4.2
					pageTotal = Math.ceil(pageTotal);//42/10->4.2->5
					
					html += "<table class ='table'>";
					html += "	<thead>";
					html += "		<th scope = 'col'>#</th>";
					html += "		<th scope='col'>상품명</th>";
					html += "		<th scope='col'>별점</th>";
					html += "		<th scope='col'>작성날짜</th>";
					html += "	</thead>";
					html += "	<tbody>";
				     					
					$.each(parseData,function(i,value){
						console.log(i+","+value.name);
						html += "<tr>";
						html += "   <th scope ='row'>"+(i+1)+"</th>";
						html += "    <td>"+value.title+"</td>";
						html += "    <td>"+value.price+"</td>";
						html += "    <td>"+value.regDt+"</td>";
						html += "</tr>";
					});
					html += "	</tbody>";
					html += "</table>";
					
				}else{//data가 없는 경우
    				html +="<tr>";
    				html +="	<td class='text-center' colspan='99'>등록된 게시물이 없습니다.</td>";    		
    				html +="</tr>";   
				}
				
				//body에 데이터 추가
				$("#content_box").append(html);	
				
				//페이징처리
				console.log(pageTotal+","+page);
				//renderingPage(pageTotal,page);
	
        	},
        	error:function(data){//실패시 처리
        		console.log("error:"+data);
        	},
        	complete:function(data){//성공/실패와 관계없이 수행!
        		console.log("complete:"+data);
        	}
    	});
	}//--doRetrieve
});
</script>
	
</html>