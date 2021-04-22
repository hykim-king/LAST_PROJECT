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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.sist.last.cmn.Message;
import com.sist.last.cmn.Search;
import com.sist.last.vo.Basket;



//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                           		   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class JTestBasketController {

	final Logger LOG = LoggerFactory.getLogger(JTestBasketController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저를 대신할 객체 Mock ==>브라우저 없이, 화면없이 테스트해 볼 수 있음!
	//MockMvc: Main entry point for server-side Spring MVC test support. 
	MockMvc mockMvc;
	
	Basket basket01;
	Basket basket02;
	Basket basket03;
	
	//수정용
	Basket basketUp;
	
	//검색
	Search search01;
	
	@Before
	public void setUp() throws Exception {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); //build까지 해서 mockMvc객체 만듦.
					  //Basket(basketSeq, storeSeq, memberId, title, optone, opttwo, quantity, shipfee, price, regDt, modId, modDt)
		basket01 =  new Basket("basketSeqTest01", "storeSeqTest01", "down8325", "침대", "optOneTest01", "optTwoTest01", 3, 1500,50000,"","","");
		basket02 =  new Basket("basketSeqTest02", "storeSeqTest01", "down8325", "책상", "optOneTest02", "optTwoTest02", 4, 2000,24000,"","","");
		basket03 =  new Basket("basketSeqTest03", "storeSeqTest01", "anga8325", "옷장", "optOneTest03", "optTwoTest03", 5, 3000,35000,"","","");
		
		//수정용
		basketUp =  new Basket("basketSeqTest01", "storeSeqTest01", "down8325_U", "침대_U", "optOneTest01_U", "optTwoTest01_U", 3, 1500,50000,"","","");
		
		
		search01= new Search("", "", 10, 1);
		
	}

	@Test
	//@Ignore
	public void doRetrieve()throws Exception{
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/basket/do_retrieve.do")
			.param("searchDiv", search01.getSearchDiv())
			.param("searchWord", search01.getSearchWord())
			.param("pageSize", search01.getPageSize()+"")
			.param("pageNum", search01.getPageNum()+"");
		ResultActions resultActions = mockMvc.perform(createMessage)
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))		
			.andExpect(status().isOk());
		String result = resultActions.andDo(print())
		 				.andReturn()
						.getResponse().getContentAsString();
				
		//gson -> List 
		Gson gson = new Gson();
		List<Basket> list = gson.fromJson(result, new TypeToken<List<Basket>>() {}.getType());

		for(Basket vo: list) {
			LOG.debug("vo:" + vo);
		}
				
	}
	
	
	@Test
	//@Ignore
	public void doUpdateTest() throws Exception {
		//1. 기존데이터 삭제 
		//2. 신규데이터 입력 
		//3. 데이터수정 + update
		
		//1.
		doDelete(basket01);
		doDelete(basket02);
		doDelete(basket03);
		
		
		//2.
		int flag = doInsert(basket01);
		assertThat(flag, is(1));//flag(actual)과 is(예상) 비교
				
				
		flag += doInsert(basket02);
		assertThat(flag, is(2));//flag(actual)과 is(예상) 비교
				

		flag += doInsert(basket03);
		assertThat(flag, is(3));//flag(actual)과 is(예상) 비교
		
		//3.수정
		basket01.setTitle(basketUp.getTitle());
		basket01.setOptone(basketUp.getOptone());
		basket01.setOpttwo(basketUp.getOpttwo());
		basket01.setQuantity(basketUp.getQuantity());
		basket01.setShipfee(basketUp.getShipfee());
		basket01.setPrice(basketUp.getPrice());

		LOG.debug("basket01:"+basket01);
		
		flag = doUpdate(basket01);
		assertThat(flag, is(1));
		
		//3.1. 수정데이터 조회
		Basket changeBasket = (Basket)doSelectOne(basket01);
		LOG.debug("changeBasket:"+changeBasket);
		checkUser(changeBasket, basket01);
	}
	
	

	public int doUpdate(Basket basket) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/basket/do_update.do")
				.param("basketSeq", basket.getBasketSeq())
				.param("storeSeq", basket.getStoreSeq())
				.param("memberId", basket.getMemberId())
				.param("title", basket.getTitle())
				.param("optone", basket.getOptone())
				.param("opttwo", basket.getOpttwo())
				.param("quantity", basket.getQuantity()+"")
				.param("shipfee", basket.getShipfee()+"")
				.param("price", basket.getPrice()+"")
				.param("modId", basket.getModId());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))		
				.andExpect(status().isOk());
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("-------------------");
		LOG.debug("-result-" + result);
		LOG.debug("-------------------");
			
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
			
		LOG.debug("-------------------");
		LOG.debug("-getMessage:" + getMessage);
		LOG.debug("-------------------");
			
			
		return Integer.parseInt(getMessage.getMsgId());
			
	}
	
	
	@Test
	//@Ignore
	public void addAndGet()throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@addAndGet=");
		LOG.debug("=======================");

		
		//검색용도
		Basket basket09=new Basket();
		basket09.setBasketSeq("basketSeqTest99");
		
		//삭제
		doDelete(basket01);
		doDelete(basket02);
		doDelete(basket03);
		
		//등록
		int flag = doInsert(basket01);
		assertThat(flag, is(1));//flag(actual)과 is(예상) 비교
				
				
		flag += doInsert(basket02);
		assertThat(flag, is(2));//flag(actual)과 is(예상) 비교
				

		flag += doInsert(basket03);
		assertThat(flag, is(3));//flag(actual)과 is(예상) 비교
		
		//단건조회:basket01  
		Basket vsBasket01 = doSelectOne(basket01);
		
		//단건조회:basket02
		Basket vsBasket02 = doSelectOne(basket02);
		
		//단건조회:basket03
		Basket vsBasket03 = doSelectOne(basket03);
		
		//비교 
		checkUser(vsBasket01, basket01);
		checkUser(vsBasket02, basket02);
		checkUser(vsBasket03, basket03);
	}
	
	
	public Basket doSelectOne(Basket basket) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/basket/do_selectone.do")
				.param("basketSeq", basket.getBasketSeq());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))	
				.andExpect(status().isOk());
				
		//출력 결과 요약 
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
				
		Gson gson = new Gson();
		Basket outVO = gson.fromJson(result, Basket.class);
				
		LOG.debug("-------------------");
		LOG.debug("-outVO-" + outVO);
		LOG.debug("-------------------");
				
		checkUser(basket,outVO);
				
		return outVO;
	}
	
	private void checkUser(Basket vsBasket, Basket basket) {
		//비교
		assertThat(vsBasket.getBasketSeq(), is(basket.getBasketSeq()));
		assertThat(vsBasket.getStoreSeq(), is(basket.getStoreSeq()));
		assertThat(vsBasket.getMemberId(), is(basket.getMemberId()));	
		assertThat(vsBasket.getTitle(), is(basket.getTitle()));	
		assertThat(vsBasket.getOptone(), is(basket.getOptone()));	
		assertThat(vsBasket.getOpttwo(), is(basket.getOpttwo()));	
		assertThat(vsBasket.getQuantity(), is(basket.getQuantity()));	
		assertThat(vsBasket.getShipfee(), is(basket.getShipfee()));	
		assertThat(vsBasket.getPrice(), is(basket.getPrice()));	
	}
	

	public void doDelete(Basket basket) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/basket/do_delete.do")
				.param("basketSeq", basket.getBasketSeq());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))	
				.andExpect(status().isOk());
				
		//출력 결과 요약 
		String result = resultActions.andDo(print())
			.andReturn()
			.getResponse().getContentAsString();
		LOG.debug("-------------------");
		LOG.debug("-result-" + result);
		LOG.debug("-------------------");
		
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
		
		String resultMsg = "";
		resultMsg = basket.getTitle() + "이(가) 장바구니에서 삭제되었습니다.";
		
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		
	}
	

	public int doInsert(Basket basket) throws Exception {
		//url호출, param전달
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/basket/do_insert.do")
				.param("basketSeq", basket.getBasketSeq())
				.param("storeSeq", basket.getStoreSeq())
				.param("memberId", basket.getMemberId())
				.param("title", basket.getTitle())
				.param("optone", basket.getOptone())
				.param("opttwo", basket.getOpttwo())
				.param("quantity", basket.getQuantity()+"")
				.param("shipfee", basket.getShipfee()+"")
				.param("price", basket.getPrice()+"");
	
		
		//ResultActions: Allows applying actions, such as expectations, on the result of an executedrequest.
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().isOk());
						
		//출력 결과 요약 
		String result = resultActions.andDo(print())
			.andReturn()
			.getResponse().getContentAsString();
		LOG.debug("-------------------");
		LOG.debug("-result-" + result);
		LOG.debug("-------------------");
				
				
		Gson gson = new Gson();
		Message getMessage = gson.fromJson(result, Message.class);
				
		LOG.debug("-------------------");
		LOG.debug("-getMessage:" + getMessage);
		LOG.debug("-------------------");
				
		String resultMsg = "";
		resultMsg = basket.getTitle() + "이(가) 장바구니에 등록되었습니다.";
				
				
		Message message = new Message();
		message.setMsgId("1");
		message.setMsgContents(resultMsg);
		assertThat(getMessage.getMsgId(), is(message.getMsgId()));
		assertThat(getMessage.getMsgContents(), is(message.getMsgContents()));
				
		return Integer.parseInt(getMessage.getMsgId());
	}
	
	
	
	@After
	public void tearDown() throws Exception {
		LOG.debug("tearDown()");
	}

	@Test
	public void beans() {
		LOG.debug("webApplicationContext"+webApplicationContext);
		LOG.debug("mockMvc"+mockMvc);
		assertThat(webApplicationContext, is(notNullValue()));
		assertThat(mockMvc, is(notNullValue()));
	}

}
