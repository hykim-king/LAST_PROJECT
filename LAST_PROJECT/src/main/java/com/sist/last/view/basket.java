package com.sist.last.view;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sist.last.service.BasketServiceImpl;

@Controller
public class basket {

	final Logger LOG = LoggerFactory.getLogger(image.class);

	@Autowired
	BasketServiceImpl basketService;

	public basket() {}
	
	/**
	 * 마이페이지
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	//http://localhost:8080/last/mypage/Member_MyPage.do
	@RequestMapping(value = "mypage/Member_MyPage.do" ,method= RequestMethod.GET)
	public String myPage(Model model) throws SQLException {

		return "mypage/Member_MyPage";
	}
	
	//http://localhost:8080/last/mypage/mypage.do
	@RequestMapping(value = "mypage/mypage.do" ,method= RequestMethod.GET)
	public String profile(Model model) throws SQLException {

		return "mypage/mypage";
	}
	
	
	/**
	 * 헤더
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	//http://localhost:8080/last/cmn/header.do
	@RequestMapping(value = "cmn/header.do" ,method= RequestMethod.GET)
	public String header(Model model) throws SQLException {

		return "cmn/header";
	}

}
