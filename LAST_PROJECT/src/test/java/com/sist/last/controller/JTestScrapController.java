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
import com.sist.last.vo.Scrap;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //bean들을 다 올려놓음
public class JTestScrapController {

	final static Logger LOG = LoggerFactory.getLogger(JTestScrapController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대신할 Mock
	//화면 없이 컨트롤러 테스트를 끝까지 할 수 있음
	MockMvc mockMvc;
	
	Scrap scrap01;
	Scrap scrap02;
	Scrap scrap03;
	
	@Before
	public void setUp() throws Exception {
		scrap01 = new Scrap("AA", "11", "JJ","","","");
		scrap02 = new Scrap("BB", "22", "KK","","","");
		scrap03 = new Scrap("CC", "33", "LL","","","");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/scrap/do_selectone.do")
														.param("scrapSeq", scrap01.getScrapSeq());
		ResultActions resultActions = mockMvc.perform(createMessage)
				  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				  .andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();	

		Gson gson = new Gson();
		Scrap outVO = gson.fromJson(result, Scrap.class);
		LOG.debug("------------------------------");
		LOG.debug("outVO:"+outVO);
		LOG.debug("------------------------------");
				
	}
	
	@Test
//	@Ignore
	public void doRetrieve() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/scrap/do_retrieve.do")
														.param("scrapSeq", scrap01.getScrapSeq());
		ResultActions resultActions = mockMvc.perform(createMessage)
				  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				  .andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();		
		
		//gson -> List
		Gson gson = new Gson();
		List<Scrap> list = gson.fromJson(result, new TypeToken<List<Scrap>>() {}.getType());
		
		for(Scrap vo :list) {
			LOG.debug("vo:"+vo);
		}
	}
	
	@Test
	@Ignore
	public void doInsert() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/scrap/do_insert.do")
														.param("scrapSeq", scrap01.getScrapSeq())
														.param("housesSeq", scrap01.getHousesSeq())
														.param("memberId", scrap01.getMemberId())
														.param("regDt", scrap01.getRegDt())
														.param("modId", scrap01.getModId())
														.param("modDt", scrap01.getModDt());
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
		resultMsg = "스크랩 등록 성공.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
	}
	
	@Test
	@Ignore
	public void doDelete() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/scrap/do_delete.do")
														.param("scrapSeq", scrap01.getScrapSeq());
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
		resultMsg = "스크랩이 삭제되었습니다.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
	}

	@Test
//	@Ignore
	public void beans() {
		LOG.debug("webApplicationContext"+webApplicationContext);
		LOG.debug("mockMvc:"+mockMvc);
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}
