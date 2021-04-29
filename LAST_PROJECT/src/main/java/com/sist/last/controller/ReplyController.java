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
import com.sist.last.cmn.SearchReview;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.ReplyService;
import com.sist.last.service.ReplyServiceImpl;
import com.sist.last.vo.Reply;


@Controller
public class ReplyController {
	
	final Logger LOG = LoggerFactory.getLogger(ReplyController.class);
	final String VIEW_NAME = "reply/reply_mng";
	
	
	@Autowired
	ReplyServiceImpl replyService;
	

	public ReplyController() {
		
	}
	
	@RequestMapping(value = "reply/do_retrieve.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(SearchReview search) throws SQLException {
		//NVL처리
//		//검색구분
//		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
//		//검색어
//		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		
		//페이지 넘
		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}
		
		//페이지 사이즈
		if(search.getPageSize()==0) {
			search.setPageSize(10);
		}
		
		//reviewFk
		search.setReviewFk(StringUtil.nvl(search.getReviewFk(), ""));
		
		List<Reply> list = (List<Reply>) this.replyService.doRetrieve(search);
		
		for(Reply vo: list) {
			LOG.debug(vo.toString());
		}
		
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		LOG.debug("=============================");
		LOG.debug("jsonList:"+jsonList);
		LOG.debug("=============================");
		
		return jsonList;
	}
	
	@RequestMapping(value = "reply/do_selectone.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Reply reply) throws SQLException{
		
		Reply outVO = (Reply) this.replyService.doSelectOne(reply);
		LOG.debug("=============================");
		LOG.debug("outVO:"+outVO);
		LOG.debug("=============================");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO); //outVO를 던져줌
		LOG.debug("=============================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("=============================");
		
		return jsonStr;
	}
	
	@RequestMapping(value = "reply/do_delete.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Reply reply) throws SQLException{
		
		int flag = this.replyService.doDelete(reply);
		String resultMsg = "";
		if(flag==1) {
			resultMsg = reply.getMemberId() + "님 댓글 삭제 성공.";
		}else {
			resultMsg = "댓글 삭제 실패.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("=============================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("=============================");
		
		return jsonStr;	
	}
	
	
	@RequestMapping(value = "reply/do_update.do", method = RequestMethod.POST
			,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doUpdate(Reply reply) throws SQLException {
		
		int flag = this.replyService.doUpdate(reply);
		String resultMsg = "";
		if(flag==1) {
			resultMsg = reply.getMemberId() + "님 댓글 수정 성공.";
		}else {
			resultMsg = "댓글 수정 실패.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("=============================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("=============================");
		
		return jsonStr;
	}
	
	
	@RequestMapping(value = "reply/do_insert.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Reply reply) throws SQLException {
		
		int flag = this.replyService.doInsert(reply);
		String resultMsg = "";
		if(flag==1) {
			resultMsg = reply.getMemberId() + "님 댓글 등록 성공.";
		}else {
			resultMsg = "댓글 등록 실패.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("=============================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("=============================");
		
		return jsonStr;
	}
	
}
