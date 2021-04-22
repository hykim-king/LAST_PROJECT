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
import com.sist.last.cmn.SearchPay;
import com.sist.last.cmn.StringUtil;
import com.sist.last.vo.Payment;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestPaymentController {
	
	final Logger LOG = LoggerFactory.getLogger(JTestPaymentController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대신할 Mock
	MockMvc mockMvc;
		
	Payment payment01;
	Payment payment02;
	Payment payment03;
	
	SearchPay search01;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("setUp()");
		
		payment01 = new Payment("20210414", "1111", "solshine", "캔들워머", "S size", "화이트", 1, 13200, 2500, 1, "", "", "");
		payment02 = new Payment("20210415", "1111", "solshine", "캔들워머", "M size", "블랙", 1, 13200, 2500, 1, "", "", "");
		payment03 = new Payment("20210416", StringUtil.getPK("yyyyMMddHH24mmss"), "sunny", "소파", "L size", "브라운", 1, 13200, 2500, 1, "", "", "");
		
		search01 = new SearchPay("solshine",10, 1);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("tearDown()");
	}

	@Test
	public void view() throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/payment/payment_view.do");
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().isOk());
				
		String result =  resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("result:"+result);
				
	}
	
	@Test
	@Ignore
	public int doInsert(Payment payment01) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/payment/do_insert.do")
										.param("paySeq", payment01.getPaySeq())
										.param("storeSeq", payment01.getStoreSeq())
										.param("memberId", payment01.getMemberId())
										.param("title", payment01.getTitle())
										.param("optone", payment01.getOptone())
										.param("opttwo", payment01.getOpttwo())
										.param("quantity" ,payment01.getQuantity()+"")
										.param("price", payment01.getPrice()+"")
										.param("shipfee", payment01.getShipfee()+"")
										.param("status", payment01.getStatus()+"")
										.param("modId", payment01.getModId());
										
		
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
		resultMsg = payment01.getMemberId() + "님 결제 성공.";
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
	
		return Integer.parseInt(getMessage.getMsgId());

	}
	
	@Test
	@Ignore
	public void doDelete(Payment payment01) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/payment/do_delete.do")
				.param("paySeq", payment01.getPaySeq())
				.param("memberId", payment01.getMemberId());
		
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
		resultMsg = payment01.getMemberId() + "님 결제 삭제 성공.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
//		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
//		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
		
	}
	
	@Test
	@Ignore
	public int doUpdate(Payment payment01) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/payment/do_update.do")
										.param("paySeq", payment01.getPaySeq())
										.param("storeSeq", payment01.getStoreSeq())
										.param("memberId", payment01.getMemberId())
										.param("title", payment01.getTitle())
										.param("optone", payment01.getOptone())
										.param("opttwo", payment01.getOpttwo())
										.param("quantity" ,payment01.getQuantity()+"")
										.param("price", payment01.getPrice()+"")
										.param("shipfee", payment01.getShipfee()+"")
										.param("status", payment01.getStatus()+"")
										.param("modId", payment01.getModId());
																
		
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
		resultMsg = payment01.getMemberId() + "님 결제 수정 성공.";
	
		return Integer.parseInt(getMessage.getMsgId());
		
	}
	
	@Test
	@Ignore
	public Payment doSelectOne(Payment payment01) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/payment/do_selectone.do")
				.param("paySeq", payment01.getPaySeq())
				.param("memberId", payment01.getMemberId());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		String result =  resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		Gson gson = new Gson();
		Payment outVO = gson.fromJson(result, Payment.class);
		
		LOG.debug("----------------------------------------");
		LOG.debug("outVO:"+outVO);
		LOG.debug("----------------------------------------");
		
		checkUser(payment01, outVO);
		
		return outVO;
	}
	
	private void checkUser(Payment vPayment, Payment payment01) {
		//비교
		assertThat(vPayment.getPaySeq(), is(payment01.getPaySeq()));
		assertThat(vPayment.getStoreSeq(), is(payment01.getStoreSeq()));
		assertThat(vPayment.getMemberId(), is(payment01.getMemberId()));
		assertThat(vPayment.getTitle(), is(payment01.getTitle()));
		assertThat(vPayment.getOptone(), is(payment01.getOptone()));
		assertThat(vPayment.getOpttwo(), is(payment01.getOpttwo()));
		assertThat(vPayment.getQuantity(), is(payment01.getQuantity()));
		assertThat(vPayment.getPrice(), is(payment01.getPrice()));
		assertThat(vPayment.getShipfee(), is(payment01.getShipfee()));
		assertThat(vPayment.getStatus(), is(payment01.getStatus()));
		assertThat(vPayment.getModId(), is(payment01.getModId()));
		
	}
	
	@Test
	@Ignore
	public void doRetrieve() throws Exception {
		
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/payment/do_retrieve.do")
				.param("memberId", payment01.getMemberId())
				.param("pageSize", String.valueOf(search01.getPageSize()))
				.param("pageNum", String.valueOf(search01.getPageNum()));
	
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	
		String result =  resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
	
		Gson gson = new Gson();
		List<Payment> list = gson.fromJson(result, new TypeToken<List<Payment>>() {}.getType());
	
		for(Payment vo: list) {
			LOG.debug("vo:"+vo);
		}
		
	}
	
	
	
	
}
