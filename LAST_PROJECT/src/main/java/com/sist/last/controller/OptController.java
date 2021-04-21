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
import com.sist.last.cmn.DTO;
import com.sist.last.cmn.SearchPay;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.OptService;
import com.sist.last.vo.Opt;

@Controller
public class OptController {
	
	final Logger LOG = LoggerFactory.getLogger(OptController.class);
	final String VIEW_NAME = "opt/opt_mng";
	
	@Autowired
	OptService optService;
	
	public OptController() {}
	
	/**
	 * 목록조회
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "opt/do_retrieve.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(SearchPay search) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+search);
		LOG.debug("===================================");
		
		//NVL 처리
		//검색구분 
		search.setMemberId(StringUtil.nvl(search.getMemberId(), ""));
		
		//페이지 넘
		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}
		
		//페이지 사이즈
		if(search.getPageSize()==0) {
			search.setPageSize(10);
		}
		
		LOG.debug("===================================");
		LOG.debug("=param_init:"+search);
		LOG.debug("===================================");
		
		List<Opt> list = this.optService.doRetrieve(search);
		
		for(Opt vo:list) {
			LOG.debug(vo.toString());
		}
		
		//List to Json
		Gson gson = new Gson();
		
		String jsonList = gson.toJson(list);
		LOG.debug("===================================");
		LOG.debug("=jsonList:"+jsonList);
		LOG.debug("===================================");
		
		return jsonList;
	}
	
	/**
	 * 회원 수정
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	
}
