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
import com.sist.last.cmn.Search;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.PaymentServiceImpl;
import com.sist.last.vo.Payment;

@Controller
public class PaymentController {
	
	final Logger LOG = LoggerFactory.getLogger(PaymentController.class);
	final String VIEW_NAME = "payment/payment_mng";
	
	@Autowired
	PaymentServiceImpl paymentService;
	
	public PaymentController() {}
	
	@RequestMapping(value = "payment/do_insert.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Payment payment) throws SQLException {
		
		int flag = this.paymentService.doInsert(payment);
		String resultMsg = "";
		if(flag==1) {
			resultMsg = payment.getMemberId() + "님 결제 성공.";
		}else {
			resultMsg = "결제 실패.";
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
	
	@RequestMapping(value = "payment/do_update.do", method = RequestMethod.POST
			,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doUpdate(Payment payment) throws SQLException {
		
		int flag = this.paymentService.doUpdate(payment);
		String resultMsg = "";
		if(flag==1) {
			resultMsg = payment.getMemberId() + "님 결제 수정 성공.";
		}else {
			resultMsg = "결제 수정 실패.";
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
	
	@RequestMapping(value = "payment/do_delete.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Payment payment) throws SQLException{
		
		int flag = this.paymentService.doDelete(payment);
		String resultMsg = "";
		if(flag==1) {
			resultMsg = payment.getMemberId() + "님 결제 삭제 성공.";
		}else {
			resultMsg = "결제 삭제 실패.";
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
	
	@RequestMapping(value = "payment/do_selectone.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Payment payment) throws SQLException{
		
		Payment outVO = (Payment) this.paymentService.doSelectOne(payment);
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
	
	@RequestMapping(value = "payment/do_retrieve.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(Search search) throws SQLException {
		//NVL처리
		//검색구분
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(), ""));
		//검색어
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(), ""));
		
		//페이지 넘
		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}
		
		//페이지 사이즈
		if(search.getPageSize()==0) {
			search.setPageSize(10);
		}
		
		List<Payment> list = (List<Payment>) this.paymentService.doRetrieve(search);
		
		for(Payment vo: list) {
			LOG.debug(vo.toString());
		}
		
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		LOG.debug("=============================");
		LOG.debug("jsonList:"+jsonList);
		LOG.debug("=============================");
		
		return jsonList;
	}
	
	
}
