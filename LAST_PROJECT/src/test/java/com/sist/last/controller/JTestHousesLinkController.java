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
import org.springframework.test.web.servlet.MvcResult;
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
import com.sist.last.vo.HousesLink;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestHousesLinkController {
	
	final static Logger LOG = LoggerFactory.getLogger(JTestHousesLinkController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대신할 객체(Mock)
	MockMvc mockMvc;
	
	HousesLink link01;
	HousesLink link02;
	HousesLink link03;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("setUp()");
		
		link01 =new HousesLink("HAHA_133_1", "2222", "haha333", "https://ohou.se/productions/585011", 1, "", "lala", ""); 
		link02 = new HousesLink("HAHA_133_2", "2222", "haha113", "https://ohou.se/productions/585022", 2, "", "haho", ""); 
		link03 = new HousesLink("HAHA_133_3", "2222", "haha666", "https://ohou.se/productions/585044", 3, "", "hoha", "");
		
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("tearDown()");
	}
	
	/**
	 * 전체조회 테스트
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void doRetrieve() throws Exception {
		
		LOG.debug("===================");
		LOG.debug("JTestHousesLinkController-doRetrieve()");
		LOG.debug("===================");
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/houseslink/do_retrieve.do")
				.param("housesSeq", link01.getHousesSeq());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===================");
		LOG.debug("result:"+result);
		LOG.debug("===================");
		
		Gson gson  = new Gson();
		List<HousesLink> list = gson.fromJson(result,  new TypeToken<List<HousesLink>>() {}.getType());
		
		
		for(HousesLink vo : list) {
			LOG.debug("vo:"+vo);
		}
	}//--doRetrieve
	
	/**
	 * 업데이트 테스트
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void doUpdateTest() throws Exception {
		LOG.debug("===================");
		LOG.debug("JTestHousesLinkController-doUpdateTest()");
		LOG.debug("===================");
		
		int flag = 0;
		
		link01.setLink(link01.getLink()+"_UP");
		link01.setDiv(link01.getDiv()+6);
		link01.setModId(link01.getModId()+"_UP");
		LOG.debug("===================");
		LOG.debug("link01:"+link01);
		LOG.debug("===================");
		
		flag = this.doUpdate(link01);	
		assertThat(flag, is(1));
	}//--doUpdateTest
	
	public int doUpdate(HousesLink link) throws  Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/houseslink/do_update.do")
				.param("linkSeq", link01.getLinkSeq())
				.param("housesSeq", link01.getHousesSeq())
				.param("memberId", link01.getMemberId())
				.param("link", link01.getLink())
				.param("div",Integer.toString(link01.getDiv()))
				.param("modId", link01.getModId());				
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===================");
		LOG.debug("result:"+result);
		LOG.debug("===================");
		
		Gson gson  = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		String resultMsg = "";
		resultMsg = "("+link.getLink()+") 링크 수정 성공";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
		
		return Integer.parseInt(getMessage.getMsgId());
		
	}//--doUpdate	
	
	/**
	 * 단건조회 테스트
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void doSelectOne() throws Exception{
		LOG.debug("===================");
		LOG.debug("JTestHousesLinkController-doSelectOne()");
		LOG.debug("===================");
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/houseslink/do_selectone.do")
				.param("linkSeq", link03.getLinkSeq());
				
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===================");
		LOG.debug("result:"+result);
		LOG.debug("===================");
		
		Gson gson  = new Gson();
		HousesLink outVO = gson.fromJson(result, HousesLink.class);
		LOG.debug("===================");
		LOG.debug("outVO:"+outVO);
		LOG.debug("===================");	
		
		checkLink(link03, outVO);
	}//--doSelectOne
	

	/**
	 * 삭제 테스트
	 * @param link
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void doDelete() throws  Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/houseslink/do_delete.do")
				.param("linkSeq", link01.getLinkSeq());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===================");
		LOG.debug("result:"+result);
		LOG.debug("===================");
		
		Gson gson  = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		String resultMsg = "";
		resultMsg = link01.getMemberId()+"님 ("+link01.getLink()+") 링크 삭제성공";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
	}//--doDelete
	
	@Test
	@Ignore
	public void doInsert() throws  Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/houseslink/do_insert.do")
				.param("linkSeq", link01.getLinkSeq())
				.param("housesSeq", link01.getHousesSeq())
				.param("memberId", link01.getMemberId())
				.param("link", link01.getLink())
				.param("div",Integer.toString(link01.getDiv()))
				.param("modId", link01.getModId());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===================");
		LOG.debug("result:"+result);
		LOG.debug("===================");
		
		Gson gson  = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		String resultMsg = "";
		resultMsg = link01.getMemberId()+"님 ("+link01.getLink()+") 링크 등록성공";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
	}//--doInsert
	
	//save data vs insert data
	private void checkLink(HousesLink vsLink, HousesLink link) {
		
		assertThat(vsLink.getLinkSeq(), is(link.getLinkSeq()));
		assertThat(vsLink.getHousesSeq(), is(link.getHousesSeq()));
		assertThat(vsLink.getMemberId(), is(link.getMemberId()));
		assertThat(vsLink.getLink(), is(link.getLink()));
		assertThat(vsLink.getDiv(), is(link.getDiv()));
		assertThat(vsLink.getModId(), is(link.getModId()));
		
	}//--checkLink

	@Test
	public void beans() {
		LOG.debug("webApplicationContext:"+webApplicationContext);
		LOG.debug("mockMvc:"+mockMvc);
		
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}//--class
