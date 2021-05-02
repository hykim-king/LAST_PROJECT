package com.sist.last.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.sist.last.service.ImageServiceImpl;
import com.sist.last.service.QnaService;
import com.sist.last.service.QnaServiceImpl;
import com.sist.last.vo.Qna;

@Controller
@RequestMapping("qna")
public class qna {
	
	final Logger LOG = LoggerFactory.getLogger(qna.class);
	
	
	@Autowired
	QnaServiceImpl qnaService;
	
	//@Autowired
	//ImageServiceImpl imageService;
	
	public qna() { }
	
	/**
	 * 질문과 답변 단건조회
	 * @param model
	 * @return qna/qna_detail
	 * @throws SQLException
	 */
	@RequestMapping(value = "/qna_detail.do")
	public String qnaDetailView(Model model,Qna qna) throws Exception{
		LOG.debug("=================");
		LOG.debug("=qnaDetailView()=");
		LOG.debug("=================");
		

		Qna outVO = (Qna) this.qnaService.doSelectOne(qna);
		
		model.addAttribute("vo", outVO);
		
		return "qna/qna_detail";
		
	}//--qnaDetailView
	
	/**
	 * 질문과 답변 목록조회
	 * @param model
	 * @return qna/qna_list
	 * @throws SQLException
	 */
	//http://localhost:8080/last/qna/qna_view.do
	@RequestMapping(value = "/qna_view.do", method = RequestMethod.GET)
	public String qnaListView(Model model) throws SQLException{
		
		LOG.debug("=================");
		LOG.debug("==qnaListView==");
		LOG.debug("=================");
		

		return "qna/qna_list";	
	}//--qnaListView
	
	
	

	
	
	
	
	/**
	 * 결제페이지
	 * @param model
	 * @return qna/payment
	 * @throws SQLException
	 */
	@RequestMapping(value = "/payment.do", method = RequestMethod.GET)
	public String paymentView(Model model) throws SQLException{
		LOG.debug("=================");
		LOG.debug("==paymentView==");
		LOG.debug("=================");
		
		return "qna/payment";
	}//--paymentView
	
	
	

}//--class
