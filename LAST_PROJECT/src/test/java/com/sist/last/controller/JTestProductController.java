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
import com.sist.last.cmn.SearchOrder;
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
	
	
	
	SearchOrder search01;
	SearchOrder search02;
	
	@Before
	public void setUp() throws Exception {
		
		
		search01 = new SearchOrder("10", "tle01", 10, 1, "10");
		search02 = new SearchOrder("", "ag01", 20, 1, "10");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}
	
	
	@Test
	//@Ignore
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
