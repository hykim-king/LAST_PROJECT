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
import com.sist.last.cmn.SearchOrder;
import com.sist.last.cmn.StringUtil;
import com.sist.last.vo.Product;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								  "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class JTestProductController {

	
	final Logger LOG = LoggerFactory.getLogger(JTestProductController.class);

	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대신할 Mock
	MockMvc mockMvc;
	
	Product product01;
	Product product02;
	Product product03;
	Product product04;
	Product product05;
	Product product06;
	
	
	SearchOrder search01;
	SearchOrder search02;
	
	@Before
	public void setUp() throws Exception {
		
		
		search01 = new SearchOrder("10", "tle01", 10, 1, "10");
		search02 = new SearchOrder("", "ag01", 20, 1, "10");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		product01 = new Product("1234", "test01", "img01", "title01", "contents01", "company01", "10", "tag01", 1, 1, "refund01", "", "test01", "");
		product02 = new Product("2", "test02", "img02", "title02", "contents02", "company02", "20", "tag02", 2, 2, "refund02", "", "test02", "");
		product03 = new Product("3", "test03", "img03", "title03", "contents03", "company03", "30", "tag03", 3, 3, "refund03", "", "test03", "");
		product04 = new Product("4", "test01", "img01", "title01", "contents01", "company01", "10", "tag01", 1, 1, "refund01", "", "test01", "");
		product05 = new Product("5", "test02", "img02", "title02", "contents02", "company02", "20", "tag02", 2, 2, "refund02", "", "test02", "");
		product06 = new Product("6", "test03", "img03", "title03", "contents03", "company03", "30", "tag03", 3, 3, "refund03", "", "test03", "");
	
		
	
		
	}
	
	@Test
	@Ignore
	public void doInsert() throws Exception {
	
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/product/do_insert.do")
				.param("storeSeq", product01.getStoreSeq()).param("memberId", product01.getMemberId()).param("imgId", product01.getImgId())
				.param("title", product01.getTitle()).param("contents", product01.getContents()).param("company", product01.getCompany())
				.param("category", product01.getCategory()).param("tag", product01.getTag()).param("price", product01.getPrice()+"")
				.param("quantity", product01.getQuantity()+"").param("refund", product01.getRefund());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		// 출력 결과 요약
				String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
				LOG.debug("=========================================");
				LOG.debug("======result======" + result);
				LOG.debug("=========================================");

				Gson gson = new Gson();
				Message getMessage = gson.fromJson(result, Message.class);

				LOG.debug("=========================================");
				LOG.debug("======getMessage======" + getMessage);
				LOG.debug("=========================================");

				String resultMsg = "";

				resultMsg = product01.getMemberId() + "님\n등록 성공";

				Message message = new Message();
				message.setMsgId("1");
				message.setMsgContents(resultMsg);

			
	
		
	}
	
	@Test
	//@Ignore
	public void doUpdate() throws Exception {
		product01.setTitle("test01_UU");
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/product/do_update.do")
				.param("storeSeq", product01.getStoreSeq()).param("modId", product01.getMemberId()).param("imgId", product01.getImgId())
				.param("title", product01.getTitle()).param("contents", product01.getContents()).param("company", product01.getCompany())
				.param("category", product01.getCategory()).param("tag", product01.getTag()).param("price", product01.getPrice()+"")
				.param("quantity", product01.getQuantity()+"").param("refund", product01.getRefund());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		// 출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("=========================================");
		LOG.debug("======result======" + result);
		LOG.debug("=========================================");

		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);

		LOG.debug("=========================================");
		LOG.debug("======getMessage======" + getMessage);
		LOG.debug("=========================================");
		
	}
	
	@Test
	@Ignore
	public void doDelete() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/product/do_delete.do").param("storeSeq", product01.getStoreSeq());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());	
		
		// 출력 결과 요약
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("=========================================");
		LOG.debug("======result======" + result);
		LOG.debug("=========================================");

		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);

		LOG.debug("=========================================");
		LOG.debug("======getMessage======" + getMessage);
		LOG.debug("=========================================");

		String resultMsg = "";

		resultMsg = product01.getMemberId() + "님\n 삭제 성공";

		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
	}
	
	
	
	
	
	
	@Test
	@Ignore
	public void doRetrieve() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/product/do_retrieve.do")
				.param("searchDiv", search02.getSearchDiv())
				.param("searchWord", search02.getSearchWord())
				.param("orderDiv", search02.getOrderDiv())
				.param("pageSize", String.valueOf(search02.getPageSize()))
				.param("pageNum",  String.valueOf(search02.getPageNum()))			
				;
		ResultActions resultActions = mockMvc.perform(createMessage).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk());
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		//gson -> List
		Gson gson = new Gson();
		List<Product> list = gson.fromJson(result, new TypeToken<List<Product>>() {}.getType());
		for(Product vo:list) {
			LOG.debug("vo:"+vo);
		}
		
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/product/do_selectone.do").param("storeSeq",
				product01.getStoreSeq());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		Gson gson = new Gson();
		Product outVO = gson.fromJson(result, Product.class);
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
