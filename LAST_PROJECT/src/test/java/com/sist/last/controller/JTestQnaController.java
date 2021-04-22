package com.sist.last.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sist.last.cmn.Message;
import com.sist.last.cmn.Search;
import com.sist.last.vo.Qna;


//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                           		   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class JTestQnaController {

	final Logger LOG = LoggerFactory.getLogger(JTestQnaController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저를 대신할 객체 Mock ==>브라우저 없이, 화면없이 테스트해볼 수 있음!
	MockMvc mockMvc;
	
	Qna qna01;
	Qna qna02;
	Qna qna03;
	
	//수정용
	Qna qnaUp;
	
	Search search01;
	Search search02;
	Search search03;
	Search search04;
	Search search05;
	
	
	
	@Before
	public void setUp() throws Exception {
		qna01 =  new Qna("qnaSeqTest01", "down8325", "qnaImageTest01", "qnaTitleTest01", "qnaContentsTest01", "qnaTagTest01", "", "", "");
		qna02 =  new Qna("qnaSeqTest02", "down8325", "qnaImageTest02", "qnaTitleTest02", "qnaContentsTest02", "qnaTagTest02", "", "", "");
		qna03 =  new Qna("qnaSeqTest03", "down8325", "qnaImageTest03", "qnaTitleTest03", "qnaContentsTest03", "qnaTagTest03", "", "", "");
		
		//수정용
		qnaUp = new Qna("qnaSeqTest01", "down8325", "qnaImageTest01_U", "qnaTitleTest01_U", "qnaContentsTest01_U", "qnaTagTest01", "", "down8325", "");
		
		//검색조건: 제목(10), 태그(20), 제목+태그(30)
		search01 =  new Search("10", "Test01", 		10, 1);
		search02 =  new Search("20", "TagTest03",	10, 1);
		search03 =  new Search("30", "Title", 		2,  1);
		search04 =  new Search("30", "Tag",   		10, 1);
		search05 =  new Search(""  , ""   ,   		10, 1);		
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}

	
	@Test
	//@Ignore
	public void doRetrieve()throws Exception{
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/qna/do_retrieve.do")
			.param("searchDiv", search01.getSearchDiv())
			.param("searchWord", search01.getSearchWord())
			.param("pageSize", search01.getPageSize()+"")
			.param("pageNum", search01.getPageNum()+"");
		ResultActions resultActions = mockMvc.perform(createMessage)
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))		
			.andExpect(status().isOk());
		String result = resultActions.andDo(print())
		 				.andReturn()
						.getResponse().getContentAsString();
				
		//gson -> List 
		Gson gson = new Gson();
		List<Qna> list = gson.fromJson(result, new TypeToken<List<Qna>>() {}.getType());

		for(Qna vo: list) {
			LOG.debug("vo:" + vo);
		}
				
	}
	
	@Test
	//@Ignore
	public void doUpdate() throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/qna/do_update.do")
				.param("qnaSeq", qnaUp.getQnaSeq())
				.param("memberId", qnaUp.getMemberId())
				.param("imgId", qnaUp.getImgId())
				.param("title", qnaUp.getTitle())
				.param("contents", qnaUp.getContents())
				.param("tag", qnaUp.getTag())
				.param("modId", qnaUp.getModId());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))		
				.andExpect(status().isOk());
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("-------------------");
		LOG.debug("-result-" + result);
		LOG.debug("-------------------");
			
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
			
		LOG.debug("-------------------");
		LOG.debug("-getMessage:" + getMessage);
		LOG.debug("-------------------");
			
			
		//return Integer.parseInt(getMessage.getMsgId());
			
	}
	
//	@Test
//	@Ignore
//	public void addAndGet()throws Exception {
//		LOG.debug("=======================");
//		LOG.debug("=@addAndGet=");
//		LOG.debug("=======================");
//
//		
//		//검색용도
//		Qna qna09=new Qna();
//		qna09.setQnaSeq("qnaSeqTest99");
//		
//		//삭제
//		doDelete(qna01);
//		doDelete(qna02);
//		doDelete(qna03);
//		
//		//등록
//		int flag = doInsert(qna01);
//		assertThat(flag, is(1));//flag(actual)과 is(예상) 비교
//				
//				
//		flag += doInsert(qna02);
//		assertThat(flag, is(2));//flag(actual)과 is(예상) 비교
//				
//
//		flag += doInsert(qna03);
//		assertThat(flag, is(3));//flag(actual)과 is(예상) 비교
//		
//		//단건조회:user01  
//		Qna vsQna01 = doSelectOne(qna01);
//		
//		//단건조회:user02
//		Qna vsQna02 = doSelectOne(qna02);
//		
//		//단건조회:user03
//		Qna vsQna03 = doSelectOne(qna03);
//		
//		//비교 
//		checkQna(vsQna01, qna01);
//		checkQna(vsQna02, qna02);
//		checkQna(vsQna03, qna03);
//	}
	
	

	public Qna doSelectOne() throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/qna/do_selectone.do")
				.param("qnaSeq", qna01.getQnaSeq());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)) 
				.andExpect(status().isOk());
				
		//출력 결과 요약 
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
				
		Gson gson = new Gson();
		Qna outVO = gson.fromJson(result, Qna.class);
				
		LOG.debug("-------------------");
		LOG.debug("-outVO-" + outVO);
		LOG.debug("-------------------");
				
		checkQna(qna01,outVO);
				
		return outVO;
	}
	
	private void checkQna(Qna vsQna, Qna qna) {
		//비교
		assertThat(vsQna.getQnaSeq(), is(qna.getQnaSeq()));
		assertThat(vsQna.getMemberId(), is(qna.getMemberId()));
		assertThat(vsQna.getImgId(), is(qna.getImgId()));	
		assertThat(vsQna.getTitle(), is(qna.getTitle()));	
		assertThat(vsQna.getContents(), is(qna.getContents()));	
		assertThat(vsQna.getTag(), is(qna.getTag()));	
	}
	

	@Test
	//@Ignore
	public void doDelete() throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/qna/do_delete.do")
				.param("qnaSeq", qna01.getQnaSeq());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))		
				.andExpect(status().isOk());
				
		//출력 결과 요약 
		String result = resultActions.andDo(print())
			.andReturn()
			.getResponse().getContentAsString();
		LOG.debug("-------------------");
		LOG.debug("-result-" + result);
		LOG.debug("-------------------");
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		String resultMsg = "";
		resultMsg = qna01.getQnaSeq() + "가 QnA에서 삭제 되었습니다.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
	}
	
	
	@Test
	//@Ignore
	public void doInsert() throws Exception {
	//url호출, param전달
	MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/qna/do_insert.do")
			.param("qnaSeq", qna01.getQnaSeq())
			.param("memberId", qna01.getMemberId())
			.param("imgId", qna01.getImgId())
			.param("title", qna01.getTitle())
			.param("contents", qna01.getContents())
			.param("tag", qna01.getTag());
	
	ResultActions resultActions = mockMvc.perform(createMessage)
			.andExpect(status().isOk());
			
	//출력 결과 요약 
	String result = resultActions.andDo(print())
		.andReturn()
		.getResponse().getContentAsString();
	LOG.debug("-------------------");
	LOG.debug("-result-" + result);
	LOG.debug("-------------------");
			
	Gson gson = new Gson();
	Message getMessage = gson.fromJson(result, Message.class);
	
	LOG.debug("-------------------");
	LOG.debug("-getMessage:" + getMessage);
	LOG.debug("-------------------");
	
	String resultMsg = "";
	resultMsg = qna01.getMemberId() + "님 QnA가 등록되었습니다.";
	
	
	Message message = new Message();
	message.setMsgId("1");
	message.setMsgContents(resultMsg);
	assertThat(getMessage.getMsgId(), is(message.getMsgId()));
	assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
	
	//return Integer.parseInt(getMessage.getMsgId());
	
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void beans() {
		LOG.debug("webApplicationContext"+webApplicationContext);
		LOG.debug("mockMvc"+mockMvc);
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}
