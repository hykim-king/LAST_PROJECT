package com.sist.last.controller;

import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sist.last.cmn.Message;
import com.sist.last.cmn.Search;
import com.sist.last.vo.Payment;
import com.sist.last.vo.Reply;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestReplyController {

	final Logger LOG = LoggerFactory.getLogger(JTestReplyController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대신할 Mock
	MockMvc mockMvc;
		
	Reply reply01;
	Reply reply02;
	Reply reply03;
	
	Search search01;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("setUp()");
		
		reply01 = new Reply("R_01", "L_100_01", "1234", "reply01~~", "", "L_100_01", "");
		reply02 = new Reply("R_02", "L_100_02", "1234", "reply02~~", "", "L_100_02", "");
		reply03 = new Reply("R_03", "L_100_03", "1234", "reply03~~", "", "L_100_03", "");
		
		search01 = new Search("","",10, 1);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("tearDown()");
	}

	
	@Test
	@Ignore
	public void view() throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/reply/reply_view.do");
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().isOk());
				
		String result =  resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("result:"+result);
				
	}
	
	
	@Test
	@Ignore
	public void doUpdateTest() throws Exception {
		//1.
		doDelete(reply01);
		doDelete(reply02);
		doDelete(reply03);
		
		//2.
		int flag = doInsert(reply01);
		assertThat(flag, is(1)); //flag(actual)과 is(예상) 비교
		
		flag += doInsert(reply02);
		assertThat(flag, is(2)); //flag(actual)과 is(예상) 비교
		
		flag += doInsert(reply03);
		assertThat(flag, is(3)); //flag(actual)과 is(예상) 비교
		
		//3.
		reply01.setContents(reply01.getContents()+"99");
		
		LOG.debug("reply01:"+reply01);
		
		flag = doUpdate(reply01);
		assertThat(flag, is(1));
		
		//3.1 수정 데이터 조회
		Reply changeReply = (Reply) doSelectOne(reply01);
		LOG.debug("changeReply"+changeReply);
		checkUser(changeReply,reply01);
				
	}
	
	
	
	public int doInsert(Reply reply01) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/reply/do_insert.do")
										.param("replySeq", reply01.getReplySeq())
										.param("memberId", reply01.getMemberId())
										.param("reviewSeq", reply01.getReviewSeq())
										.param("contents",reply01.getContents())
										.param("modId",reply01.getModId());
										
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk());
		
		String result =  resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("----------------------------------------");
		LOG.debug("result"+result);
		LOG.debug("----------------------------------------");
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		LOG.debug("----------------------------------------");
		LOG.debug("getMessage:"+getMessage);
		LOG.debug("----------------------------------------");
		
		String resultMsg = "";
		resultMsg = reply01.getMemberId() + "님\n수정 성공.";
	
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
	
		return Integer.parseInt(getMessage.getMsgId());

	}
	
	
	public void doDelete(Reply reply01) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/reply/do_delete.do")
				.param("replySeq", reply01.getReplySeq())
				.param("memberId", reply01.getReplySeq())
				.param("reviewSeq", reply01.getReviewSeq());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String result =  resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("----------------------------------------");
		LOG.debug("result"+result);
		LOG.debug("----------------------------------------");
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		LOG.debug("----------------------------------------");
		LOG.debug("getMessage:"+getMessage);
		LOG.debug("----------------------------------------");
		
		String resultMsg = "";
		resultMsg = reply01.getMemberId() + "님\n 삭제 성공.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
//		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
//		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
		
	}
	
	
	public int doUpdate(Reply reply01) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/reply/do_update.do")
										.param("replySeq", reply01.getReplySeq())
										.param("memberId", reply01.getMemberId())
										.param("reviewSeq", reply01.getReviewSeq())
										.param("contents",reply01.getContents())
										.param("modId",reply01.getModId());
										
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk());
		
		String result =  resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("----------------------------------------");
		LOG.debug("result"+result);
		LOG.debug("----------------------------------------");
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		LOG.debug("----------------------------------------");
		LOG.debug("getMessage:"+getMessage);
		LOG.debug("----------------------------------------");
		
		String resultMsg = "";
		resultMsg = reply01.getMemberId() + "님\n수정 성공.";
	
		return Integer.parseInt(getMessage.getMsgId());
		
	}

	public Reply doSelectOne(Reply reply01) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/reply/do_selectone.do")
				.param("replySeq", reply01.getReplySeq())
				.param("memberId", reply01.getReplySeq())
				.param("reviewSeq", reply01.getReviewSeq());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String result =  resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		Gson gson = new Gson();
		Reply outVO = gson.fromJson(result, Reply.class);
		
		LOG.debug("----------------------------------------");
		LOG.debug("outVO:"+outVO);
		LOG.debug("----------------------------------------");
		
		checkUser(reply01, outVO);
		
		return outVO;
	}
	
	private void checkUser(Reply vsReply, Reply reply01) {
		//비교
		assertThat(vsReply.getReplySeq(), is(reply01.getReplySeq()));
		assertThat(vsReply.getMemberId(), is(reply01.getMemberId()));
		assertThat(vsReply.getReviewSeq(), is(reply01.getReviewSeq()));
		//컬럼추가
		assertThat(vsReply.getContents(), is(reply01.getContents()));
		assertThat(vsReply.getModId(), is(reply01.getModId()));
	}
	
	
	public void doRetrieve() throws Exception {
		
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/reply/do_retrieve.do")
				.param("searchDiv", search01.getSearchDiv())
				.param("searchWord", search01.getSearchWord())
				.param("pageSize", String.valueOf(search01.getPageSize()))
				.param("pageNum", String.valueOf(search01.getPageNum()));
	
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	
		String result =  resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
	
		Gson gson = new Gson();
		List<Reply> list = gson.fromJson(result, new TypeToken<List<Reply>>() {}.getType());
	
		for(Reply vo: list) {
			LOG.debug("vo:"+vo);
		}
		
	}
	

}
