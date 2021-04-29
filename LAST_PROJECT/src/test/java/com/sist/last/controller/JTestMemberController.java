package com.sist.last.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.sist.last.cmn.Message;
import com.sist.last.vo.Grade;
import com.sist.last.vo.Member;
import com.sist.last.vo.Opt;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //bean들을 다 올려놓음
public class JTestMemberController {

	final static Logger LOG = LoggerFactory.getLogger(JTestMemberController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대신할 Mock
	//화면 없이 컨트롤러 테스트를 끝까지 할 수 있음
	MockMvc mockMvc;
	
	Member member01;
	Member member02;
	Member member03;
	Member member04;
	Member member05;
	
	@Before
	public void setUp() throws Exception {
		member01 = new Member("L_100_01","img111","","","1234","L01","안녕하세요",Grade.NEW,1,0,0,"","L01","");
		member02 = new Member("L_100_02","img222","","","1234","L02","HI",Grade.NEW,1,19,30,"","L02","");
		member03 = new Member("L_100_03","img333","","","1234","L03","Hello",Grade.SILVER,1,20,30,"","L03","");
		member04 = new Member("L_100_04","img444","","","1234","L04","Hello",Grade.GOLD,1,40,30,"","L03","");
		member05 = new Member("L_100_05","img555","","","1234","L05","Hello",Grade.GOLD,1,50,30,"","L03","");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	@Ignore
	public void idCheck() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/member/id_check.do")
														.param("memberId", member01.getMemberId()+"99");

		ResultActions resultActions = mockMvc.perform(createMessage)
		.andExpect(status().isOk());
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
				
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		assertThat(getMessage.getMsgId(), is("0"));
		LOG.debug("------------------------------");
		LOG.debug("result:"+result);
		LOG.debug("------------------------------");
	}
	
	@Test
	@Ignore
	public void doLogin() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/member/do_login.do")
														.param("memberId", member01.getMemberId())
														.param("passwd", member01.getPasswd());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				  .andExpect(status().isOk());
		
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		assertThat(getMessage.getMsgId(), is("30"));
		LOG.debug("------------------------------");
		LOG.debug("result:"+result);
		LOG.debug("------------------------------");
		
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/member/do_selectone.do")
														.param("memberId",member01.getMemberId());
		ResultActions resultActions = mockMvc.perform(createMessage)
				  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				  .andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print())
		        .andReturn()
		        .getResponse().getContentAsString();	

		Gson gson = new Gson();
		Member outVO = gson.fromJson(result, Member.class);
		LOG.debug("------------------------------");
		LOG.debug("outVO:"+outVO);
		LOG.debug("------------------------------");
		
		checkUser(member01, outVO);
		
	}
	
	private void checkUser(Member vsMember, Member member01) {
		//비교
		assertThat(vsMember.getMemberId(), is(member01.getMemberId()));
		assertThat(vsMember.getImgId(), is(member01.getImgId()));
		assertThat(vsMember.getPasswd(), is(member01.getPasswd()));
		assertThat(vsMember.getNickname(), is(member01.getNickname()));
		assertThat(vsMember.getIntroduce(), is(member01.getIntroduce()));
		assertThat(vsMember.getGrade(), is(member01.getGrade()));
		assertThat(vsMember.getDiv(), is(member01.getDiv()));
		assertThat(vsMember.getScrap(), is(member01.getScrap()));
		assertThat(vsMember.getLogin(), is(member01.getLogin()));
		assertThat(vsMember.getModId(), is(member01.getModId()));
	}
	
	@Test
	@Ignore
	public void doUpdate() throws Exception {
		member01.setNickname(member01.getNickname()+"_U");
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/member/do_update.do")
													.param("imgId", member01.getImgId())
													.param("passwd", member01.getPasswd())
													.param("nickname", member01.getNickname())
													.param("introduce", member01.getIntroduce())
													.param("grade", member01.getGrade()+"")
													.param("div", member01.getDiv()+"")
													.param("scrap", member01.getScrap()+"")
													.param("login", member01.getLogin()+"")
													.param("regDt", member01.getRegDt())
													.param("modId", member01.getModId())
													.param("modDt", member01.getModDt())
													.param("memberId", member01.getMemberId());
		
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
		resultMsg = member01.getMemberId()+"님\n수정 성공.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));		
				
	}
	
	@Test
	public void doDelete() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/member/do_delete.do")
														.param("memberId", member01.getMemberId());
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
		resultMsg = member01.getMemberId()+"님\n삭제 되었습니다.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
	}
	
	@Test
	public void doInsert() throws Exception {
		//url호출, param 전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/member/do_insert.do")
														.param("memberId", member01.getMemberId())
														.param("imgId", member01.getImgId())
														.param("passwd", member01.getPasswd())
														.param("nickname", member01.getNickname())
														.param("introduce", member01.getIntroduce())
														.param("grade", member01.getGrade()+"")
														.param("div", member01.getDiv()+"")
														.param("scrap", member01.getScrap()+"")
														.param("login", member01.getLogin()+"")
														.param("regDt", member01.getRegDt())
														.param("modId", member01.getModId())
														.param("modDt", member01.getModDt());
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
		resultMsg = member01.getMemberId()+"님 등록 성공.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
	}

	@Test
	public void beans() {
		LOG.debug("webApplicationContext"+webApplicationContext);
		LOG.debug("mockMvc:"+mockMvc);
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}
