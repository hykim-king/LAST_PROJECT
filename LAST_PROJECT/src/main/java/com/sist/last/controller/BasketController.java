package com.sist.last.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.last.cmn.Message;
import com.sist.last.cmn.Search;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.BasketService;
import com.sist.last.vo.Basket;

@Controller
public class BasketController {
	
	final Logger LOG = LoggerFactory.getLogger(BasketController.class);
	final String VIEW_NAME = "basket/basket_mng";
	
	@Autowired
	
	BasketService basketService;
	
	
	public BasketController() {}
	
	
	/**
	 * 장바구니 목록 조회
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="basket/do_retrieve.do", method=RequestMethod.GET
			,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doRetrieve(Search search)throws SQLException{
		LOG.debug("================================");
		LOG.debug("=param:" + search);
		LOG.debug("================================");
		LOG.debug("================================");
		
		///NVL처리 
		//검색구분
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
		//검색어
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		
		//pageNum
		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}
		
		//pageSize
		if(search.getPageSize()==0) {
			search.setPageSize(10);
		}
		
		LOG.debug("================================");
		LOG.debug("=param_init:" + search);
		LOG.debug("================================");
		
		List<Basket> list = (List<Basket>) this.basketService.doRetrieve(search);
		for(Basket vo: list) {
			LOG.debug(vo.toString());
		}
		
		//List to Json
		Gson gson = new Gson();
		
		String jsonList = gson.toJson(list);
		LOG.debug("================================");
		LOG.debug("=jsonList:" + jsonList);
		LOG.debug("================================");
		
		
		
		return jsonList;
	}
	
	
	
	/**
	 * 장바구니 상품 수정
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="basket/do_update.do", method=RequestMethod.POST
					,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doUpdate(Basket basket) throws SQLException {
		LOG.debug("================================");
		LOG.debug("=param:" + basket);
		LOG.debug("================================");
		
		int flag = this.basketService.doUpdate(basket);
		String resultMsg = "";
		if(1==flag) {
			resultMsg = basket.getTitle() + "가 장바구니에서 수정 되었습니다.";
		}else {
			resultMsg = basket.getTitle() + " 수정 실패.";
		}
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);  //메세지를 json으로 
		LOG.debug("================================");
		LOG.debug("=jsonStr:" + jsonStr);
		LOG.debug("================================");
		
		return jsonStr;
		
	}
	
	/**
	 * 장바구니 상품 단건 조회
	 * @param dto
	 * @return JSON(Basket)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="basket/do_selectone.do", method=RequestMethod.GET
			,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Basket basket) throws SQLException {
		LOG.debug("================================");
		LOG.debug("=param:" + basket);
		LOG.debug("================================");
		
		Basket outVO = (Basket) this.basketService.doSelectOne(basket);
		LOG.debug("================================");
		LOG.debug("=outVO:" + outVO);
		LOG.debug("================================");
		
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);  //vo를 json으로 
		LOG.debug("================================");
		LOG.debug("=jsonStr:" + jsonStr);
		LOG.debug("================================");
		return jsonStr;
	}
	
	
	/**
	 * 장바구니 상품 삭제
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="basket/do_delete.do", method=RequestMethod.POST
			,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String doDelete(Basket basket) throws SQLException {
		LOG.debug("================================");
		LOG.debug("=param:" + basket);
		LOG.debug("================================");
		int flag = this.basketService.doDelete(basket);
		String resultMsg="";
		if(1==flag) {
			resultMsg = basket.getTitle() + "이(가) 장바구니에서 삭제되었습니다.";
		}else {
			resultMsg = "삭제 실패.";
		}
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);  //메세지를 json으로 
		LOG.debug("================================");
		LOG.debug("=jsonStr:" + jsonStr);
		LOG.debug("================================");
		
		return jsonStr;
	}
	
	/**
	 * 장바구니 상품 등록
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="basket/do_insert.do", method=RequestMethod.POST
					,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String doInsert(Basket basket) throws SQLException {
		LOG.debug("================================");
		LOG.debug("=param:" + basket);
		LOG.debug("================================");
		
		int flag = basketService.doInsert(basket);
		
		String resultMsg="";
		if(1==flag) {
			resultMsg = basket.getTitle() + "이(가) 장바구니에 등록되었습니다.";
			
		}else {
			resultMsg = "장바구니 등록 실패"; 
		}

		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);  //메세지를 json으로 
		LOG.debug("================================");
		LOG.debug("=jsonStr:" + jsonStr);
		LOG.debug("================================");
		
		
		return jsonStr;
		
	}
		
}
