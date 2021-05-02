package com.sist.last.view;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sist.last.controller.MemberController;
import com.sist.last.service.MemberService;

@Controller
@RequestMapping("member")
public class member {
	final Logger LOG = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	public member() {}
	
	@RequestMapping(value = "/callback_view.do",method = RequestMethod.GET)
	public String naverCallbackView(Model model) throws SQLException {
		LOG.debug("callback_view.do");
		return "login/naver_callback";
	}
	
	@RequestMapping(value = "/mng_view.do",method = RequestMethod.GET)
	public String memberMngView(Model model) throws SQLException {
		LOG.debug("mng_view.do");
		return "login/Member_Edit";
	}
	
	@RequestMapping(value = "/passwd_view.do",method = RequestMethod.GET)
	public String forgotPasswdView(Model model) throws SQLException {
		LOG.debug("passwd_view.do");
		return "login/Member_Forgot_Passwd";
	}
	
	@RequestMapping(value = "/reg_view.do",method = RequestMethod.GET)
	public String memberRegView(Model model) throws SQLException {
		LOG.debug("reg_view.do");
		return "login/Member_SignUp";
	}
	
	@RequestMapping(value = "/login_view.do",method = RequestMethod.GET)
	public String loginView(Model model) throws SQLException {
		LOG.debug("login_view.do");
		return "login/Member_Login";
	}
}