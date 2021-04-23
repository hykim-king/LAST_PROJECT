<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	response는 request 기본 객체와 반대의 기능을 수행
	웹 브라우저에 전송할 내용 담을수 있다.
	
	-헤더정보 입력: cache control
	-리다이렉트 하기 
	   
웹 캐시(영어: web cache) 또는 HTTP 캐시(HTTP cache)는 서버 지연을 줄이기 위해 웹 페이지, 
이미지, 기타 유형의 웹 멀티미디어 등의 웹 문서들을 임시 저장하기 위한 정보기술이다. 
웹 캐시 시스템은 이를 통과하는 문서들의 사본을 저장하며 이후 요청들은 특정 조건을 충족하는 경우 캐시화가 가능하다.
[1] 웹 캐시 시스템은 일종의 어플라이언스나 컴퓨터 프로그램을 의미할 수 있다. 
동일한 서버에 다시 접근할 때에는 근처에 있는 프록시 서버의 웹 캐시에 저장된 정보를 불러오므로 더 빠른 열람이 가능하다.
 --%>    

<%

	out.print("웹 캐시");
   //HTTP 1.1에서 지원하는 헤더: 웹브라우저가 응답결과를 캐시하지 않음.
   response.setHeader("Cashe-Control", "no-cache");
   //웹브라우저가 응답결과를 캐시하지 않음.(앞/뒤로 가기 no chache)    
   response.setHeader("Cashe-Control","no-store");
   
   //HTTP 1.0: 웹브라우저가 응답결과를 캐시하지 않음.
   response.setHeader("Pragma","no-cache");
   
   //HTTP 1.0: 현재시간 이전으로 캐시하지 않음
   response.setHeader("Expires","1L");
  
%>







