package com.sist.last.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.last.cmn.Message;
import com.sist.last.cmn.SearchOrder;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.ProductServiceImpl;
import com.sist.last.vo.Product;



@Controller
public class ProductController {
	
	final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	
	@Autowired
	ProductServiceImpl productService;
	
	public ProductController() {
		
	}
	
	@RequestMapping(value = "product/do_insert.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Product param) throws SQLException {
		LOG.debug("=========================================");
		LOG.debug("======param======"+param);
		LOG.debug("=========================================");
		
		int flag = productService.doInsert(param);
		
		String resultMsg = "";
		if(flag==1) {
			resultMsg = param.getTitle()+"프로젝트\n등록 성공";
		}else {
			resultMsg = "등록 실패";
			
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("=========================================");
		LOG.debug("======jsonStr======"+jsonStr);
		LOG.debug("=========================================");
		
		return jsonStr;
	}
	
	@RequestMapping(value = "product/do_delete.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Product param) throws SQLException {
		LOG.debug("=========================================");
		LOG.debug("======param======"+param);
		LOG.debug("=========================================");
		
		int flag = this.productService.doDelete(param);
		String resultMsg = "";
		if(flag==1) {
			resultMsg = param.getTitle()+"게시물\n삭제 성공";
		}else {
			resultMsg = "삭제 실패";
			
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("=========================================");
		LOG.debug("======jsonStr======"+jsonStr);
		LOG.debug("=========================================");
		
		return jsonStr;
	}
	
	
	@RequestMapping(value = "product/do_update.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(Product param) throws SQLException {
		LOG.debug("=========================================");
		LOG.debug("======param======"+param);
		LOG.debug("=========================================");
		
		int flag = this.productService.doUpdate(param);
	
		String resultMsg = "";
		if(flag==1) {
			resultMsg = param.getMemberId()+"님\n수정 성공";
		}else {
			resultMsg = "님\n수정 실패";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("=========================================");
		LOG.debug("======jsonStr======"+jsonStr);
		LOG.debug("=========================================");
		
		return jsonStr;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "product/do_selectone.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Product param) throws SQLException {
		LOG.debug("=========================================");
		LOG.debug("======param======"+param);
		LOG.debug("=========================================");
		
		Product outVO =  (Product) this.productService.doSelectOne(param);
	
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);
		LOG.debug("=========================================");
		LOG.debug("======jsonStr======"+jsonStr);
		LOG.debug("=========================================");
		
		return jsonStr;
	}
	
	
	
	
	/**
	 * 회원 목록 조회
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws SQLException
	 */
	@RequestMapping(value = "product/do_retrieve.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(SearchOrder search) throws SQLException {
		LOG.debug("=========================================");
		LOG.debug("======param======"+search);
		LOG.debug("=========================================");
		
		//nvl처리
		//검색구분
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
		//검색어
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		search.setOrderDiv(StringUtil.nvl(search.getOrderDiv(), "10"));
		
		//페이지 num
		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}
		
		//페이지 사이즈
		if(search.getPageSize()==0) {
			search.setPageSize(10);
		}
		
		LOG.debug("=========================================");
		LOG.debug("======param======"+search);
		LOG.debug("=========================================");

		List<Product> list = (List<Product>) this.productService.doRetrieve(search);
		for(Product vo: list) {
			LOG.debug(vo.toString());
		}
		
		// List to Json
		Gson gson = new Gson();

		String jsonList = gson.toJson(list);
		LOG.debug("=========================================");
		LOG.debug("======jsonList======" + jsonList);
		LOG.debug("=========================================");
		
				
		return jsonList;
	}
}
