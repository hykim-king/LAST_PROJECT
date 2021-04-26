package com.sist.last.view;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.sist.last.cmn.Search;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.QnaService;
import com.sist.last.vo.Qna;

@Controller
@RequestMapping("qna")
public class qna {
	
	final Logger LOG = LoggerFactory.getLogger(qna.class);
	final String VIEW_NAME ="qna/qna_list";
	
	@Autowired
	QnaService qnaService;
	
	public qna() { }
	
	//http://localhost:8080/last/qna/qna_view.do
	@RequestMapping(value = "/qna_view.do", method = RequestMethod.GET)
	public String view(Model model) throws SQLException{
		

		
		return VIEW_NAME;
		
	}//--view//////
	
	@RequestMapping(value = "/qna_detail.do", method = RequestMethod.GET)
	public String view2(Model model) throws SQLException{
		

		
		return "qna/qna_detail";
		
	}//--view
	

	
	
	
	/**
	 * Q&A 목록 조회
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="/do_retrieve.do", method=RequestMethod.GET
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
	 * Q&A 단건 조회
	 * @param dto
	 * @return JSON(User)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value="/do_selectone.do", method=RequestMethod.GET
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
	
	
	
	
	
	

}//--class
