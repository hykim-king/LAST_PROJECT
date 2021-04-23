package com.sist.last.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
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
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sist.last.cmn.Message;
import com.sist.last.cmn.SearchReview;
import com.sist.last.vo.Review;

//메소드 수행 순서(method ASCENDING)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
//스프링 테스트 컨텍스트 프레임의 JUnit 기능 확장
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class JTestReviewController {

	final Logger LOG = LoggerFactory.getLogger(JTestReviewController.class);

	@Autowired
	WebApplicationContext webApplicationContext;

	// 브라우저를 대신할 Mock
	MockMvc mockMvc;

	Review review01;
	Review review02;
	Review review03;

	// 검색
	SearchReview search01;

	@Before
	public void setUp() throws Exception {
		LOG.debug("setUp()");

		review01 = new Review("1", "tjdus", "1", "0", "test1", "", "", "");
		review02 = new Review("2", "tjdus", "1", "1", "test2", "", "", "");
		review03 = new Review("3", "tjdus", "1", "2", "test3", "", "", "");

		search01 = new SearchReview(2, 1, "1");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("tearDown()");
	}
	
	@Test
	//@Ignore
	public void addAndGet() throws Exception {
		
		LOG.debug("--------------");
		LOG.debug("-@addAndGet-");
		LOG.debug("--------------");
		
		// 삭제
		doDelete(review01);
		doDelete(review02);
		doDelete(review03);
		
		// 등록
		int flag = doInsert(review01);
		assertThat(flag, is(1));// flag(actual)과 is(예상) 비교
		
		flag += doInsert(review02);
		assertThat(flag, is(2));
		
		flag += doInsert(review03);
		assertThat(flag, is(3));
		
		// 리뷰 수정
		review01.setContents(review01.getContents()+"_up");
		review01.setModId(review01.getModId()+"tjdus1");
		review01.setModDt("");
		
		LOG.debug("review01: "+review01);
		
		flag = doUpdate(review01);
		assertThat(flag, is(1));
		
		// 데이터 단건조회
		Review vsReview01 = doSelectOne(review01);
		Review vsReview02 = doSelectOne(review02);
		Review vsReview03 = doSelectOne(review03);		
	
	}
	
	@Test
	//@Ignore
	public void doRetrieve() throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_retrieve.do")
				.param("reviewFk", search01.getReviewFk())
				.param("pageSize", String.valueOf(search01.getPageSize()))
				.param("pageNum", String.valueOf(search01.getPageNum()));
	
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());		
	
		// 출력 결과 요약
		String result = resultActions.andDo(print())
					    .andReturn()
					    .getResponse()
					    .getContentAsString();	
		
		// gson -> List
		Gson gson = new Gson();	
		
		List<Review> list = gson.fromJson(result, new TypeToken<List<Review>>() {}.getType());
		
		for(Review vo : list) {
			LOG.debug("vo: "+vo);
		}
		
	}
	
	public int doUpdate(Review review01) throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_update.do")
				.param("reviewSeq", review01.getReviewSeq())
				.param("contents", review01.getContents())
				.param("modId", review01.getModId());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());	
		
		// 출력 결과 요약
		String result = resultActions.andDo(print())
					    .andReturn()
					    .getResponse()
					    .getContentAsString();	
		
		LOG.debug("------------------------");
		LOG.debug("result: "+result);
		LOG.debug("------------------------");		
		
		// gson 데이터 꺼내기
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		LOG.debug("------------------------");
		LOG.debug("getMessage: "+getMessage);
		LOG.debug("------------------------");		
		
		return Integer.parseInt(getMessage.getMsgId());
		
	}
	
	public Review doSelectOne(Review review01) throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/review/do_selectone.do")
				.param("reviewSeq", review01.getReviewSeq());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());		
		
		// 출력 결과 요약
		String result = resultActions.andDo(print())
					    .andReturn()
					    .getResponse()
					    .getContentAsString();
		
		// gson 데이터 꺼내기
		Gson gson = new Gson();
		Review outVO = gson.fromJson(result, Review.class);		
		
		return outVO;
		
	}
	
	public void doDelete(Review review01) throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_delete.do")
				.param("reviewSeq", review01.getReviewSeq());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		// 출력 결과 요약
		String result = resultActions.andDo(print())
					    .andReturn()
					    .getResponse()
					    .getContentAsString();
		
		LOG.debug("------------------------");
		LOG.debug("result: "+result);
		LOG.debug("------------------------");	
		
		// gson 데이터 꺼내기
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		LOG.debug("------------------------");
		LOG.debug("getMessage: "+getMessage);
		LOG.debug("------------------------");
		
		String resultMsg = "";
		resultMsg = "리뷰 삭제 성공";

		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
	}

	public int doInsert(Review review01) throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/review/do_insert.do")
				.param("reviewSeq", review01.getReviewSeq()).param("memberId", review01.getMemberId())
				.param("reviewFk", review01.getReviewFk()).param("div", review01.getDiv())
				.param("contents", review01.getContents()).param("modId", review01.getModId());

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

		// 출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("------------------------");
		LOG.debug("result: " + result);
		LOG.debug("------------------------");

		// gson 데이터 꺼내기
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);

		LOG.debug("------------------------");
		LOG.debug("getMessage: " + getMessage);
		LOG.debug("------------------------");

		String resultMsg = "";
		resultMsg = review01.getMemberId() + "님\n리뷰가 등록되었습니다.";

		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);

		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));

		return Integer.parseInt(getMessage.getMsgId());

	}

	@Test
	@Ignore
	public void beans() {
		LOG.debug("webApplicationContext: " + webApplicationContext);
		assertThat(webApplicationContext, is(notNullValue()));

		LOG.debug("mockMvc: " + mockMvc);
		assertThat(mockMvc, is(notNullValue()));
	}

}
