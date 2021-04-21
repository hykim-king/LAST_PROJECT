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
	public String doRetrieve(Opt opt) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+opt);
		LOG.debug("===================================");
		
		List<Opt> list = this.optService.doRetrieve(opt);
		
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
	 * 옵션 단건조회
	 * @param dto
	 * @return JSON(User)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value = "opt/do_selectone.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Opt opt) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+opt);
		LOG.debug("===================================");
		
		Opt outVO = (Opt) this.optService.doSelectOne(opt);
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
	 * 옵션 수정
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "opt/do_update.do",method = RequestMethod.POST
			,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doUpdate(Opt opt) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+opt);
		LOG.debug("===================================");
		
		int flag = this.optService.doUpdate(opt);
		String resultMsg = "";
		if(1 == flag) {
			resultMsg = opt.getOptSeq()+"\n수정 성공.";
		}else {
			resultMsg = opt.getOptSeq()+"\n수정 실패.";
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
	 * 옵션 삭제
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "opt/do_delete.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Opt opt) throws SQLException {
		
		LOG.debug("===================================");
		LOG.debug("=param:"+opt);
		LOG.debug("===================================");
		
		int flag = this.optService.doDelete(opt);
		String resultMsg = "";
		if(1==flag) {
			resultMsg ="옵션 삭제되었습니다.";
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
	 * 옵션 등록
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "opt/do_insert.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Opt opt) throws SQLException {
		
		LOG.debug("===================================");
		LOG.debug("=param:"+opt);
		LOG.debug("===================================");
		
		int flag = this.optService.doInsert(opt);
		String resultMsg = "";
		if(1==flag) {
			resultMsg ="옵션 등록 성공.";
		} else {
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
}
