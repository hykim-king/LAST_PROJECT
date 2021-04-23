package com.sist.last.controller;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.last.cmn.Message;
import com.sist.last.service.StarServiceImpl;
import com.sist.last.vo.Star;

@Controller
public class StarController {

	final Logger LOG = LoggerFactory.getLogger(StarController.class);
//	final String VIEW_NAME = "star/star_mng";
	
	@Autowired
	StarServiceImpl starService;
	
	public StarController() {}
	
	/**
	 * 별점 수정
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws SQLException 
	 */
	@RequestMapping(value = "star/do_update.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(Star star) throws SQLException {
		LOG.debug("=====================");
		LOG.debug("=param="+star);
		LOG.debug("=====================");
		
		int flag = this.starService.doUpdate(star);
		String resultMsg = "";
		
		if(flag==1) {
			resultMsg = star.getStarScore()+"\n 별점을 수정하였습니다.";
		} else {
			resultMsg = "별점 수정에 실패하였습니다.";
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
	 * 별점 등록
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws SQLException 
	 */
	@RequestMapping(value = "star/do_insert.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Star star) throws SQLException {
		LOG.debug("=====================");
		LOG.debug("=param="+star);
		LOG.debug("=====================");
		
		int flag = this.starService.doInsert(star);
		String resultMsg = "";
		
		if(flag==1) {
			resultMsg = star.getMemberId() + "님 (" + star.getStarScore() + ") 별점을 등록하였습니다.";
		} else {
			resultMsg = "별점 등록에 실패하였습니다.";
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
	 * 별점 삭제
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws SQLException 
	 */
	@RequestMapping(value = "star/do_delete.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Star star) throws SQLException {
		LOG.debug("=====================");
		LOG.debug("=param="+star);
		LOG.debug("=====================");
		
		int flag = this.starService.doDelete(star);
		String resultMsg = "";
		
		if(flag==1) {
			resultMsg = star.getMemberId() + "님 (" + star.getStarScore() + ") 별점을 삭제하였습니다.";
		} else {
			resultMsg = "별점 삭제에 실패하였습니다.";
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
