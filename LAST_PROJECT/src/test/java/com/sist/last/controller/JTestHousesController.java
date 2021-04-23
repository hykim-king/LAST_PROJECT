package com.sist.last.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import com.sist.last.cmn.SearchOrder;
import com.sist.last.vo.Houses;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration // controller test
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) // 설정파일 set
public class JTestHousesController {

	final static Logger LOG = Logger.getLogger(JTestHousesController.class);

	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mockMvc;

	Houses houses01;
	Houses houses02;
	Houses houses03; 

	// 목록조회 검색
	SearchOrder search;

	@Before
	public void setUp() throws Exception {
		houses01 = new Houses("4444", "yeonsu44", "4444", "test44", "test44", "test44", "", "yeonsu44", "");
		houses02 = new Houses("5555", "yeonsu55", "5555", "test55", "test55", "test55", "", "yeonsu55", "");
		houses03 = new Houses("6666", "yeonsu66", "6666", "test66", "test66", "test66", "", "yeonsu66", "");
		
		search = new SearchOrder("20", "tag", 10, 1, "10");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void doRetrieve() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/houses/do_retrieve.do")
				.param("searchDiv", search.getSearchDiv())
				.param("searchWord", search.getSearchWord())
				.param("pageSize", search.getPageSize()+"")
				.param("pageNum", search.getPageNum()+"")
				.param("orderDiv", search.getOrderDiv());

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		//출력 결과 요약
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		
		LOG.debug("------------------");
		LOG.debug("result : "+result);
		LOG.debug("------------------");
		
		Gson gson = new Gson();
		List<Houses> list = gson.fromJson(result, new TypeToken<List<Houses>>() {}.getType());
		
		for(Houses vo : list) {
			LOG.debug("vo : "+vo);
		}
		
	}
	
	@Test
	public void doUpdateTest() throws Exception {
		int flag = 0;
		
		houses01.setImgId(houses01.getImgId()+"_U");
		houses01.setTitle(houses01.getTitle()+"_U");
		houses01.setContents(houses01.getContents()+"_U");
		houses01.setTag(houses01.getTag()+"_U");
		houses01.setModId(houses01.getModId()+"_U");
		LOG.debug(houses01);
		
		flag = doUpdate(houses01);
		assertThat(flag, is(1));
		
		//비교
		Houses checkHouses = (Houses) doSelectOne(houses01);
		LOG.debug("checkHouses : "+checkHouses);
		checkHouses(checkHouses, houses01);
		
	}
	
	private void checkHouses(Houses vsHouses, Houses houses) {
		// 비교
		assertThat(vsHouses.getHousesSeq(), is(houses.getHousesSeq()));
		assertThat(vsHouses.getMemberId(), is(houses.getMemberId()));
		assertThat(vsHouses.getImgId(), is(houses.getImgId()));
		assertThat(vsHouses.getTitle(), is(houses.getTitle()));
		assertThat(vsHouses.getContents(), is(houses.getContents()));
		assertThat(vsHouses.getTag(), is(houses.getTag()));
		assertThat(vsHouses.getModId(), is(houses.getModId()));
	}
	
	public Houses doSelectOne(Houses houses) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/houses/do_selectone.do")
				.param("housesSeq", houses.getHousesSeq());

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

		// 출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("------------------");
		LOG.debug("result : " + result);
		LOG.debug("------------------");

		Gson gson = new Gson();
		Houses outVO = gson.fromJson(result, Houses.class);

		LOG.debug("------------------");
		LOG.debug("outVO : " + outVO);
		LOG.debug("------------------");

		checkHouses(houses, outVO);

		return outVO;
	}
	
	public int doUpdate(Houses houses) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/houses/do_update.do")
				.param("housesSeq", houses.getHousesSeq())
				.param("memberId", houses.getMemberId())
				.param("imgId", houses.getImgId())
				.param("title", houses.getTitle())
				.param("contents", houses.getContents())
				.param("tag", houses.getTag())
				.param("modId", houses.getModId());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk());

		//출력 결과 요약
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		
		LOG.debug("------------------");
		LOG.debug("result : "+result);
		LOG.debug("------------------");
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		String resultMsg = "";
		resultMsg = houses.getHousesSeq()+"\n 집들이 게시글을 수정하였습니다.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
		
		return Integer.parseInt(getMessage.getMsgId());
	}

	@Test
	public void doInsert() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/houses/do_insert.do")
				.param("housesSeq", houses01.getHousesSeq())
				.param("memberId", houses01.getMemberId())
				.param("imgId", houses01.getImgId())
				.param("title", houses01.getTitle())
				.param("contents", houses01.getContents())
				.param("tag", houses01.getTag())
				.param("modId", houses01.getModId());

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

		//출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("------------------");
		LOG.debug("result : " + result);
		LOG.debug("------------------");

		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);

		String resultMsg = "";
		resultMsg = houses01.getMemberId() + "님 (" + houses01.getHousesSeq() + ") 집들이 게시글을 등록하였습니다.";

		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);

		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));

	}
	
	@Test
	public void doDelete() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/houses/do_delete.do")
				.param("housesSeq", houses01.getHousesSeq());

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

		// 출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("------------------");
		LOG.debug("result : " + result);
		LOG.debug("------------------");

		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);

		String resultMsg = "";
		resultMsg = houses01.getMemberId() + "님 (" + houses01.getHousesSeq() + ") 집들이 게시글을 삭제하였습니다.";

		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);

	}

	@Test
	public void beans() {
		LOG.debug("=@Test beans()=");
		LOG.debug("webApplicationContext : " + webApplicationContext);
		LOG.debug("mockMvc : " + mockMvc);
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}
