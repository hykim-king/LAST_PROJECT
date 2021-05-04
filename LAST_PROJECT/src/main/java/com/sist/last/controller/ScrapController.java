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
import com.sist.last.cmn.DTO;
import com.sist.last.cmn.Message;
import com.sist.last.cmn.Search;
import com.sist.last.cmn.SearchScrap;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.ScrapServiceImpl;
import com.sist.last.vo.Scrap;

@Controller
public class ScrapController {
	
	final Logger LOG = LoggerFactory.getLogger(ScrapController.class);
//	final String VIEW_NAME = "scrap/scrap_mng";
	
	@Autowired
	ScrapServiceImpl scrapService;
	
	public ScrapController() {}
	
	/**
	 * 목록조회
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "scrap/do_retrieve.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(Search search) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+search);
		LOG.debug("===================================");
		
		List<Scrap> list = (List<Scrap>)this.scrapService.doRetrieve(search);
		
		for(Scrap vo:list) {
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
	 * 스크랩 단건조회
	 * @param dto
	 * @return JSON(User)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value = "scrap/do_selectone.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Scrap scrap) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+scrap);
		LOG.debug("===================================");
		
		Scrap outVO = (Scrap) this.scrapService.doSelectOne(scrap);
		LOG.debug("===================================");
		LOG.debug("=outVO:"+outVO);
		LOG.debug("===================================");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);
		LOG.debug("===================================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("===================================");
		
		return jsonStr;
	}
	
	/**
	 * 스크랩 삭제
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "scrap/do_delete.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Scrap scrap) throws SQLException {
		
		LOG.debug("===================================");
		LOG.debug("=param:"+scrap);
		LOG.debug("===================================");
		
		int flag = this.scrapService.doDelete(scrap);
		String resultMsg = "";
		if(1==flag) {
			resultMsg ="스크랩 삭제되었습니다.";
		} else {
			resultMsg = "삭제 실패.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("===================================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("===================================");
		
		return jsonStr;
	}
	
	
	/**
	 * 스크랩 등록
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "scrap/do_insert.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Scrap scrap) throws SQLException {
		
		LOG.debug("===================================");
		LOG.debug("=param:"+scrap);
		LOG.debug("===================================");
		
		
		
		int flag = this.scrapService.doInsert(scrap);
		String resultMsg = "";
		if(1==flag) {
			resultMsg ="스크랩 등록 성공.";
		}else if(2==flag) {
			resultMsg ="이미 등록한 스크랩입니다.";
		}	else {
			resultMsg = "등록 실패.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("===================================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("===================================");
		
		return jsonStr;
	}
	
	
	/**
	 * 목록조회
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "scrap/do_retrieveById.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieveById(SearchScrap search) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+search);
		LOG.debug("===================================");
		
		List<Scrap> list = (List<Scrap>)this.scrapService.doRetrieveById(search);
		
		for(Scrap vo:list) {
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
	
	
}
