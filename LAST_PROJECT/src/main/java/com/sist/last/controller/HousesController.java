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
import com.sist.last.service.HousesService;
import com.sist.last.vo.Houses;

@Controller
public class HousesController {

	final Logger LOG = LoggerFactory.getLogger(HousesController.class);
	final String VIEW_NAME = "houses/houses_mng";
	
	@Autowired
	HousesService housesService;
	
	public HousesController() {
		
	}
	
	/**
	 * 집들이 목록 조회
	 * @param dto
	 * @return JSON(User)
	 * @throws SQLException 
	 */
	@RequestMapping(value = "houses/do_retrieve.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(SearchOrder searchOrder) throws SQLException {
		LOG.debug("=====================");
		LOG.debug("=param="+searchOrder);
		LOG.debug("=====================");
		
		//NVL 처리 (초기화)
		//검색 구분
		searchOrder.setSearchDiv(StringUtil.nvl(searchOrder.getSearchDiv(),""));
		//검색어
		searchOrder.setSearchWord(StringUtil.nvl(searchOrder.getSearchWord(),""));
		//페이지 size
		if(searchOrder.getPageSize()==0) {
			searchOrder.setPageSize(10);
		}
		//페이지 num
		if(searchOrder.getPageNum()==0) {
			searchOrder.setPageNum(1);
		}
		
		//정렬 구분
		searchOrder.setOrderDiv(StringUtil.nvl(searchOrder.getOrderDiv(), "10"));
				
		LOG.debug("=====================");
		LOG.debug("=param_init="+searchOrder);
		LOG.debug("=====================");
		
		List<Houses> list = (List<Houses>) this.housesService.doRetrieve(searchOrder);
		
		for(Houses vo : list) {
			LOG.debug(vo.toString());
		}
		
		//List to Json
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		
		LOG.debug("=====================");
		LOG.debug("=jsonList="+jsonList);
		LOG.debug("=====================");
		
		return jsonList;
	}
	
	/**
	 * 집들이 수정
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws SQLException 
	 */
	@RequestMapping(value = "houses/do_update.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(Houses houses) throws SQLException {
		LOG.debug("=====================");
		LOG.debug("=param="+houses);
		LOG.debug("=====================");
		
		int flag = this.housesService.doUpdate(houses);
		String resultMsg = "";
		
		if(flag==1) {
			resultMsg = houses.getHousesSeq()+"\n 집들이 게시글을 수정하였습니다.";
		} else {
			resultMsg = "집들이 게시글 수정에 실패하였습니다.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		
		LOG.debug("=====================");
		LOG.debug("=jsonStr="+jsonStr);
		LOG.debug("=====================");
		
		return jsonStr;
	}
	
	/**
	 * 집들이 단건 조회
	 * @param dto
	 * @return JSON(User)
	 * @throws SQLException 
	 */
	@RequestMapping(value = "houses/do_selectone.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Houses houses) throws SQLException {
		LOG.debug("=====================");
		LOG.debug("=param="+houses);
		
		Houses outVO = (Houses) this.housesService.doSelectOne(houses);
		LOG.debug("=outVO="+outVO);
		LOG.debug("=====================");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);
		
		LOG.debug("=====================");
		LOG.debug("=jsonStr="+jsonStr);
		LOG.debug("=====================");
		
		return jsonStr;
	}
	
	/**
	 * 집들이 등록
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws SQLException 
	 */
	@RequestMapping(value = "houses/do_insert.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Houses houses) throws SQLException {
		LOG.debug("=====================");
		LOG.debug("=param="+houses);
		LOG.debug("=====================");
		
		int flag = this.housesService.doInsert(houses);
		String resultMsg = "";
		
		if(flag==1) {
			resultMsg = houses.getMemberId() + "님 (" + houses.getHousesSeq() + ") 집들이 게시글을 등록하였습니다.";
		} else {
			resultMsg = "집들이 게시글 등록에 실패하였습니다.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		
		LOG.debug("=====================");
		LOG.debug("=jsonStr="+jsonStr);
		LOG.debug("=====================");
		
		return jsonStr;
	}
	
	/**
	 * 집들이 삭제
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws SQLException 
	 */
	@RequestMapping(value = "houses/do_delete.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Houses houses) throws SQLException {
		LOG.debug("=====================");
		LOG.debug("=param="+houses);
		LOG.debug("=====================");
		
		int flag = this.housesService.doDelete(houses);
		String resultMsg = "";
		
		if(flag==1) {
			resultMsg = houses.getMemberId() + "님 (" + houses.getHousesSeq() + ") 집들이 게시글을 삭제하였습니다.";
		} else {
			resultMsg = "집들이 게시글 삭제에 실패하였습니다.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		
		LOG.debug("=====================");
		LOG.debug("=jsonStr="+jsonStr);
		LOG.debug("=====================");
		
		return jsonStr;
	}
	
}
