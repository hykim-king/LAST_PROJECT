package com.sist.last.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.last.cmn.Message;
import com.sist.last.cmn.Search;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.ImageService;
import com.sist.last.service.ImageServiceImpl;
import com.sist.last.service.QnaService;
import com.sist.last.vo.Qna;

@Controller
public class QnaController {
	
	final Logger LOG = LoggerFactory.getLogger(QnaController.class);
	final String VIEW_NAME = "qna/qna_mng"; //이 위치에 화면
	
	@Autowired
	QnaService qnaService;
	
	@Autowired
	ImageServiceImpl imageService;
	
	public QnaController() {}
	

	
	
	/**
	 * Q&A 목록 조회
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="qna/do_retrieve.do", method=RequestMethod.GET
			,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doRetrieve(Search search)throws SQLException{
		LOG.debug("================================");
		LOG.debug("=param:" + search);
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
		
		List<Qna> list = (List<Qna>) this.qnaService.doRetrieve(search);
		for(Qna vo: list) {
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
	 * Q&A 수정
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="qna/do_update.do", method=RequestMethod.POST
					,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doUpdate(Qna qna) throws SQLException {
		LOG.debug("================================");
		LOG.debug("=param:" + qna);
		LOG.debug("================================");
		
		int flag = this.qnaService.doUpdate(qna);
		String resultMsg = "";
		if(1==flag) {
			resultMsg = qna.getMemberId() + "님 질문이 수정되었습니다.";
		}else {
			resultMsg = qna.getMemberId() + "님 수정이 실패하였습니다.";
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
	 * Q&A 단건 조회
	 * @param dto
	 * @return JSON(User)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="qna/do_selectone.do", method=RequestMethod.GET
			,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Qna qna) throws SQLException {
		LOG.debug("================================");
		LOG.debug("=param:" + qna);
		LOG.debug("================================");
		
		Qna outVO = (Qna) this.qnaService.doSelectOne(qna);
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
	 * Q&A 삭제
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="qna/do_delete.do", method=RequestMethod.POST
			,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String doDelete(Qna qna) throws SQLException {
		LOG.debug("================================");
		LOG.debug("=param:" + qna);
		LOG.debug("================================");
		int flag = this.qnaService.doDelete(qna);
		String resultMsg="";
		if(1==flag) {
			resultMsg = qna.getQnaSeq() + "가 QnA에서 삭제 되었습니다.";
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
	 * Q&A 등록
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="qna/do_insert.do", method=RequestMethod.POST
					,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String doInsert(Qna qna) throws SQLException {
		LOG.debug("================================");
		LOG.debug("=param:" + qna);
		LOG.debug("================================");
		
		int flag =qnaService.doInsert(qna);

		String resultMsg="";
		if(1==flag) {
			resultMsg = qna.getMemberId() + "님 QnA가 등록되었습니다.";
		}else {
			resultMsg = "QnA 등록에 실패하였습니다."; 
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
