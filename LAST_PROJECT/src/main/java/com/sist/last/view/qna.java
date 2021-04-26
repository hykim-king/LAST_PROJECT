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
	

	
	
	
	
	
	
	
	

}//--class
