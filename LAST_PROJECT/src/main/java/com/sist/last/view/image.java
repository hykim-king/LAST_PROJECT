package com.sist.last.view;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sist.last.cmn.StringUtil;
import com.sist.last.service.ImageServiceImpl;
import com.sist.last.service.QnaService;
import com.sist.last.vo.Image;
import com.sist.last.vo.Qna;

@Controller
public class image {

	final Logger LOG = LoggerFactory.getLogger(image.class);

	// 절대 경로 상수로 올리기
	// qna
	final String UPLOAD_IMG_DIR = "C:\\Users\\SIST\\git\\LAST_PROJECT\\LAST_PROJECT\\src\\main\\webapp\\resources\\img";
	
	@Autowired
	QnaService qnaService;

	public image() {

	}
	

	
	// 화면 띄우기
	@RequestMapping(value = "store/product_regist.do")
	public String storeView(Model model) throws SQLException {

		LOG.debug("registView");

		return "regist/product_upload";
	}		
	
	// 화면 띄우기
	@RequestMapping(value = "houses/houses_regist.do")
	public String houseView(Model model) throws SQLException {

		LOG.debug("registView");

		return "regist/houses_upload";
	}	
	
	@RequestMapping(value = "houses/houses_upload.do", method = RequestMethod.POST)
	public String housesUpload(MultipartHttpServletRequest mReg, ModelAndView modelAndView)
			throws IllegalStateException, IOException {
		
		LOG.debug("------------------------");
		LOG.debug("-----housesUpload()-----");
		LOG.debug("------------------------");	
	

		
		return "houses/Community_List";
		
	}
	
	// 화면 띄우기
	@RequestMapping(value = "qna/qna_regist.do")
	public String registView(Model model) throws SQLException {

		LOG.debug("registView");

		return "regist/qna_upload";
	}

	@RequestMapping(value = "qna/qna_upload.do", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest mReg, ModelAndView modelAndView)
			throws IllegalStateException, IOException {

		LOG.debug("------------------");
		LOG.debug("-----upload()-----");
		LOG.debug("------------------");

		

		return "qna/qna_list";

	}

}
