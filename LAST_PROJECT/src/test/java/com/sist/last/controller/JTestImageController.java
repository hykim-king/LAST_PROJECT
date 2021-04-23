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
import com.sist.last.vo.Image;

//메소드 수행 순서(method ASCENDING)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
//스프링 테스트 컨텍스트 프레임의 JUnit 기능 확장
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class JTestImageController {

	final Logger LOG = LoggerFactory.getLogger(JTestImageController.class);

	@Autowired
	WebApplicationContext webApplicationContext;

	// 브라우저를 대신할 Mock
	MockMvc mockMvc;

	Image image01;
	Image image02;
	Image image03;

	@Before
	public void setUp() throws Exception {
		LOG.debug("setUp()");

		image01 = new Image("20210415", 1, "spring.jpg", "spring.jpg", "last/upload", "12.345", "");
		image02 = new Image("20210416", 2, "summer.jpg", "summer.jpg", "last/upload", "12.345", "");
		image03 = new Image("20210417", 3, "fall.jpg", "fall.jpg", "last/upload", "12.345", "");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("tearDown()");
	}
	
	@Test
	public void doUpdateTest() throws Exception {
		// 1. 기존 데이터 삭제
		// 2. 신규 데이터 입력
		// 3. 데이터 수정 + update
		// 4. 데이터 조회
		
		// 1.
		doDelete(image01);
		doDelete(image02);
		doDelete(image03);
		
		// 2.
		int flag = doInsert(image01);
		assertThat(flag, is(1));
		
		flag += doInsert(image02);
		assertThat(flag, is(2));
		
		flag += doInsert(image03);
		assertThat(flag, is(3));
		
		// 3.
		image01.setOrgName(image01.getOrgName()+"_up");
		image01.setSaveName(image01.getSaveName()+"_up");
		
		LOG.debug("user01: "+image01);
		
		flag = doUpdate(image01);
		assertThat(flag, is(1));
		
		// 4.
		doSelectOne(image01);
			
	}

	@Test
	public void doRetrieve() throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/image/do_retrieve.do")
				.param("imgId",image01.getImgId());

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

		// 출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		// gson -> List
		Gson gson = new Gson();

		List<Image> list = gson.fromJson(result, new TypeToken<List<Image>>() {
		}.getType());

		for (Image vo : list) {
			LOG.debug("vo: " + vo);
		}
	}

	public int doUpdate(Image image01) throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/image/do_update.do")
				.param("imgId", image01.getImgId()).param("imgNum", image01.getImgNum() + "")
				.param("orgName", image01.getOrgName()).param("saveName", image01.getSaveName())
				.param("savePath", image01.getSavePath()).param("imgSize", image01.getImgSize())
				.param("imgExt", image01.getImgExt());

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

		return Integer.parseInt(getMessage.getMsgId());

	}

	public Image doSelectOne(Image image01) throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/image/do_selectone.do").param("imgId",
				image01.getImgId());

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

		// 출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		// gson 데이터 꺼내기
		Gson gson = new Gson();
		Image outVO = gson.fromJson(result, Image.class);

		return outVO;

	}

	public void doDelete(Image image01) throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/image/do_delete.do")
				.param("imgId",image01.getImgId());

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
		resultMsg = "이미지 삭제 성공";

		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);

	}

	public int doInsert(Image image01) throws Exception {
		// url 호출, set param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/image/do_insert.do")
				.param("imgId", image01.getImgId()).param("imgNum", image01.getImgNum() + "")
				.param("orgName", image01.getOrgName()).param("saveName", image01.getSaveName())
				.param("savePath", image01.getSavePath()).param("imgSize", image01.getImgSize())
				.param("imgExt", image01.getImgExt());

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
		resultMsg = "이미지 등록 성공";

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
