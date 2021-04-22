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
import com.sist.last.cmn.Message;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.HousesLinkService;
import com.sist.last.vo.HousesLink;


@Controller
public class HousesLinkController {
	
	final Logger LOG = LoggerFactory.getLogger(HousesLinkController.class);
	
	final String VIEW_NAME = "houseslink/houseslink_mng";
	
	@Autowired
	HousesLinkService linkService;
	
	public HousesLinkController() {
		
	}
	
	/**
	 * 집들이 링크 전체조회
	 * @param dto
	 * @return List<?>
	 * @throws SQLException
	 */
	@RequestMapping(value = "houseslink/do_retrieve.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(HousesLink link) throws SQLException{
		
		LOG.debug("===========");
		LOG.debug("##HousesLinkController-doRetrieve##");
		LOG.debug("param:"+link);
		LOG.debug("===========");
		
		//NVL 처리
		link.setHousesSeq(StringUtil.nvl(link.getHousesSeq(), ""));
		
		LOG.debug("===========");
		LOG.debug("param_init:"+link);
		LOG.debug("===========");
		
		List<HousesLink> list = (List<HousesLink>) this.linkService.doRetrieve(link);
		
		for(HousesLink vo:list) {
			LOG.debug(vo.toString());
		}
		
		//List to Json
		Gson gson  = new Gson();
		
		String jsonList = gson.toJson(list);
		LOG.debug("===========");
		LOG.debug("jsonList:"+jsonList);
		LOG.debug("===========");		
		
		return jsonList;
	}//--doRetrieve
	
	/**
	 * 집들이 링크 수정
	 * @param dto
	 * @return int (1:성공/0:실패)
	 * @throws SQLException
	 */
	@RequestMapping(value = "houseslink/do_update.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(HousesLink link) throws SQLException{
		
		LOG.debug("===========");
		LOG.debug("##HousesLinkController-doUpdate##");
		LOG.debug("param:"+link);
		LOG.debug("===========");
		
		int flag = this.linkService.doUpdate(link);
		
		String resultMsg = "";
		if(1==flag) {
			resultMsg = link.getMemberId()+"님 ("+link.getLinkSeq()+") 링크 업데이트성공";
		}else {
			resultMsg = "링크 업데이트 실패";
		}
		//메세지 읽고 
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		//메세지 json으로 변경
		Gson gson = new Gson();
		String jsonstr = gson.toJson(message);
		LOG.debug("===========");
		LOG.debug("jsonstr:"+jsonstr);
		LOG.debug("===========");
	
		return jsonstr;		
	}//--doUpdate
	
	/**
	 * 집들이 링크 단건조회
	 * @param dto
	 * @return DTO
	 * @throws SQLException
	 */
	@RequestMapping(value = "houseslink/do_selectone.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(HousesLink link) throws SQLException{
		
		LOG.debug("===========");
		LOG.debug("##HousesLinkController-doSelectOne##");
		LOG.debug("param:"+link);
		LOG.debug("===========");
		
		HousesLink outVO = (HousesLink) this.linkService.doSelectOne(link);
		LOG.debug("===========");
		LOG.debug("outVO:"+outVO);
		LOG.debug("===========");
		
		//message to json
		Gson gson = new Gson();
		String jsonstr = gson.toJson(outVO);
		LOG.debug("===========");
		LOG.debug("jsonstr:"+jsonstr);
		LOG.debug("===========");
		
		return jsonstr;
	}//--doSelectOne
	
	/**
	 * 집들이 링크 삭제
	 * @param dto
	 * @return  int (1:성공/0:실패)
	 * @throws SQLException
	 */
	@RequestMapping(value = "houseslink/do_delete.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(HousesLink link) throws  SQLException{
		
		LOG.debug("===========");
		LOG.debug("##HousesLinkController-doDelete##");
		LOG.debug("param:"+link);
		LOG.debug("===========");
		
		int flag = this.linkService.doDelete(link);
		
		String resultMsg = "";
		if(1==flag) {
			resultMsg = link.getMemberId()+"님 ("+link.getLink()+") 링크 삭제성공";
		}else {
			resultMsg = "링크 삭제실패";
		}
		//메세지 읽고 
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		//메세지 json으로 변경
		Gson gson = new Gson();
		String jsonstr = gson.toJson(message);
		LOG.debug("===========");
		LOG.debug("jsonstr:"+jsonstr);
		LOG.debug("===========");
	
		return jsonstr;
	}//--doDelete
	
	/**
	 * 집들이 링크 등록
	 * @param dto
	 * @return  int (1:성공/0:실패)
	 * @throws SQLException
	 */
	@RequestMapping(value = "houseslink/do_insert.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(HousesLink link) throws  SQLException{
		
		LOG.debug("===========");
		LOG.debug("##HousesLinkController-doInsert##");
		LOG.debug("param:"+link);
		LOG.debug("===========");
		
		int flag = linkService.doInsert(link);
		
		String resultMsg = "";
		if(1==flag) {
			resultMsg = link.getMemberId()+"님 ("+link.getLink()+") 링크 등록성공";
		}else {
			resultMsg = "링크 등록실패";
		}
		
		//메세지 읽고 
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		//메세지 json으로 변경
		Gson gson = new Gson();
		String jsonstr = gson.toJson(message);
		LOG.debug("===========");
		LOG.debug("jsonstr:"+jsonstr);
		LOG.debug("===========");
	
		return jsonstr;
	}//--doInsert

}//--class
