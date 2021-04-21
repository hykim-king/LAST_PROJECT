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
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sist.last.cmn.Message;
import com.sist.last.vo.Opt;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //bean들을 다 올려놓음
public class JTestOptController {

	final static Logger LOG = LoggerFactory.getLogger(JTestOptController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대신할 Mock
	//화면 없이 컨트롤러 테스트를 끝까지 할 수 있음
	MockMvc mockMvc;
	
	Opt opt01;
	Opt opt02;
	Opt opt03;
	
	@Before
	public void setUp() throws Exception {
		opt01 = new Opt("20210414", "12345", "solshine", "레드", 12000, 1, "", "", "");
		opt02 = new Opt("20210415", "12345", "solshine", "블루", 12000, 1, "", "", "");
		opt03 = new Opt("20210416", "12345", "sunny", "S", 12000, 2, "", "", "");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	@Ignore
	public void doRetrieve() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/opt/do_retrieve.do")
														.param("storeSeq", opt01.getStoreSeq())
														.param("div", opt01.getDiv()+"");
		ResultActions resultActions = mockMvc.perform(createMessage)
				  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				  .andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();		
		
		//gson -> List
		Gson gson = new Gson();
		List<Opt> list = gson.fromJson(result, new TypeToken<List<Opt>>() {}.getType());
		
		for(Opt vo :list) {
			LOG.debug("vo:"+vo);
		}
	}
	
	@Test
	public void doSelectOne() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/opt/do_selectone.do")
														.param("optSeq", opt01.getOptSeq());
		ResultActions resultActions = mockMvc.perform(createMessage)
				  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				  .andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();	

		Gson gson = new Gson();
		Opt outVO = gson.fromJson(result, Opt.class);
		LOG.debug("------------------------------");
		LOG.debug("outVO:"+outVO);
		LOG.debug("------------------------------");
		
		checkOpt(opt01, outVO);
		
	}
	
	private void checkOpt(Opt vsOpt, Opt opt01) {
		//비교
		assertThat(vsOpt.getOptSeq(), is(opt01.getOptSeq()));
		assertThat(vsOpt.getStoreSeq(), is(opt01.getStoreSeq()));
		assertThat(vsOpt.getMemberId(), is(opt01.getMemberId()));
		assertThat(vsOpt.getTitle(), is(opt01.getTitle()));
		assertThat(vsOpt.getPrice(), is(opt01.getPrice()));
		assertThat(vsOpt.getDiv(), is(opt01.getDiv()));
	}
	
	@Test
	@Ignore
	public void doUpdate() throws Exception {
		opt01.setTitle(opt01.getTitle()+"_U");
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/opt/do_update.do")
				                                      .param("optSeq", opt01.getOptSeq())
				                                      .param("title", opt01.getTitle())
				                                      .param("price", opt01.getPrice()+"")
				                                      .param("div", opt01.getDiv()+"")
				                                      .param("modId", opt01.getModId());
		ResultActions resultActions = mockMvc.perform(createMessage)
				  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				  .andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();		
		LOG.debug("------------------------------");
		LOG.debug("result:"+result);
		LOG.debug("------------------------------");
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		LOG.debug("------------------------------");
		LOG.debug("getMessage:"+getMessage);
		LOG.debug("------------------------------");
		
		String resultMsg = "";
		resultMsg = opt01.getOptSeq()+"\n수정 성공.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));

	}
	
	@Test
	public void doInsert() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/opt/do_insert.do")
													 .param("optSeq", opt01.getOptSeq())
							                         .param("storeSeq", opt01.getStoreSeq())
							                         .param("memberId", opt01.getMemberId())
							                         .param("title", opt01.getTitle())
							                         .param("price", opt01.getPrice()+"")
							                         .param("div", opt01.getDiv()+"")
							                         .param("regDt", opt01.getRegDt())
							                         .param("modId", opt01.getModId())
							                         .param("modDt", opt01.getModDt());
		ResultActions resultActions = mockMvc.perform(createMessage)
				  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				  .andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();		
		LOG.debug("------------------------------");
		LOG.debug("result:"+result);
		LOG.debug("------------------------------");
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		LOG.debug("------------------------------");
		LOG.debug("getMessage:"+getMessage);
		LOG.debug("------------------------------");
		
		String resultMsg = "";
		resultMsg = "옵션 등록 성공.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
	}
	
	@Test
	public void doDelete() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/opt/do_delete.do")
														.param("optSeq", opt01.getOptSeq());
		ResultActions resultActions = mockMvc.perform(createMessage)
				  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				  .andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();		
		LOG.debug("------------------------------");
		LOG.debug("result:"+result);
		LOG.debug("------------------------------");
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		LOG.debug("------------------------------");
		LOG.debug("getMessage:"+getMessage);
		LOG.debug("------------------------------");
		
		String resultMsg = "";
		resultMsg = "옵션 삭제되었습니다.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
	}

	@Test
	@Ignore
	public void beans() {
		LOG.debug("webApplicationContext"+webApplicationContext);
		LOG.debug("mockMvc:"+mockMvc);
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}
