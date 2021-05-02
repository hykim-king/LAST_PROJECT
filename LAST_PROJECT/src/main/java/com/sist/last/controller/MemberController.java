package com.sist.last.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

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
import com.sist.last.service.MemberService;
import com.sist.last.vo.Member;

@Controller
@RequestMapping("member")
public class MemberController {
	final Logger LOG = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	public MemberController() {}
	
	@RequestMapping(value = "/forgot_passwd.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String forgotPasswd(String email) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+email);
		LOG.debug("===================================");
		
		String passwd = this.memberService.sendPasswdEmail(email);
		
		return passwd;
	}
	
	@RequestMapping(value = "/send_email.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String sendAuthEmail(String email) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+email);
		LOG.debug("===================================");
		
		String auth = this.memberService.sendAuthEmail(email);
		
		return auth;
	}
	
	/**
	 * 닉네임 중복 체크
	 * @param member
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/nick_check.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Message nickCheck(Member member) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+member);
		LOG.debug("===================================");
		
		int flag = this.memberService.nickCheck(member);
		
		Message nickCheckMessage = new Message();
		nickCheckMessage.setMsgId(flag+"");
		
		
		if(1==flag) {//닉네임 중복
			nickCheckMessage.setMsgContents(member.getNickname()+"은(는) 이미 있는 닉네임입니다.");
		}else {
			nickCheckMessage.setMsgContents(member.getNickname()+"은(는) 사용하실 수 있습니다.");
		}
		
		return nickCheckMessage;
	}
	
	/**
	 * 아이디 중복 체크
	 * @param member
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/id_check.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Message idCheck(Member member) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+member);
		LOG.debug("===================================");
		
		int flag = this.memberService.idCheck(member);
		
		Message idCheckMessage = new Message();
		idCheckMessage.setMsgId(flag+"");
		
		if(1==flag) {//ID있음
			idCheckMessage.setMsgContents(member.getMemberId()+"은(는) 이미 있는 ID입니다.");
		}else {
			idCheckMessage.setMsgContents(member.getMemberId()+"은(는) 사용하실 수 있습니다.");
		}
		
		return idCheckMessage;
	}
	
	/**
	 * jackson : Message to json으로 변환
	 * http://localhost:8080/last/member/do_login.do?memberId=L_100_01&passwd=1234
	 * @param member
	 * @param session
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/update_session.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdateSession(Member member, HttpSession session) throws SQLException {
		LOG.debug("1===================================");
		LOG.debug("=param:"+member);
		LOG.debug("1===================================");
		
		Message loginMessage = memberService.loginCheck(member);
		Member loginMember = (Member) memberService.doSelectOne(member);
		
		//아이디, 비번 확인
		if("30".equals(loginMessage.getMsgId())) {
			
			loginMember = (Member) memberService.doSelectOne(member);
			if(null !=loginMember) {
				loginMessage.setMsgContents(loginMember.getMemberId()+"님 로그인 되었습니다.");
			}
			LOG.debug("2===================================");
			LOG.debug("=loginMember:"+loginMember);
			LOG.debug("2===================================");
			
			session.setAttribute("member", loginMember);
		}
		//Gson 불필요.
		
		LOG.debug("=loginMessage:"+loginMessage);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(loginMember);
		LOG.debug("===================================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("===================================");
		
		return jsonStr;
		
	}
	
	@RequestMapping(value = "/do_logoff.do",method = RequestMethod.GET)
	public String doLogOff(HttpSession session) {
		String returnUrl = "houses/Community_Home";
		
		LOG.debug("===================================");
		LOG.debug("=doLogOff:");
		LOG.debug("===================================");
		
		if(null != session.getAttribute("member")) {
			session.removeAttribute("member");
			session.invalidate();
		}
		
		return returnUrl;
	}
	
	/**
	 * jackson : Message to json으로 변환
	 * @param member
	 * @param session
	 * @return
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "/do_sns_login.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Message doKakaoLogin(Member member, HttpSession session) throws SQLException, IllegalAccessException {
		LOG.debug("===================================");
		LOG.debug("=param:"+member);
		LOG.debug("===================================");
		
		Message loginMessage = memberService.loginCheck(member);
		Member loginMember = new Member();
		
		//아이디 존재 확인
		if("20".equals(loginMessage.getMsgId())) {
			
			//1.
			loginMember = (Member) memberService.doSelectOne(member);
			LOG.debug("loginMember:"+loginMember);
			
			if(null !=loginMember) {
				loginMessage.setMsgContents(loginMember.getMemberId()+"님 로그인 되었습니다.");
				LOG.debug("loginMember:"+loginMember);
				int flag = memberService.doLoginCnt(loginMember);
				LOG.debug("doLoginCnt() flag:"+flag);
				memberService.upgradeGrades(loginMember);
			}
			
		} else {
			loginMember.setMemberId(member.getMemberId());
			loginMember.setNickname(member.getNickname());
		}
		
		session.setAttribute("member", loginMember);
		
		return loginMessage;
	}
	
	/**
	 * jackson : Message to json으로 변환
	 * http://localhost:8080/last/member/do_login.do?memberId=L_100_01&passwd=1234
	 * @param member
	 * @param session
	 * @return
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "/do_login.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Message doLogin(Member member, HttpSession session) throws SQLException, IllegalAccessException {
		LOG.debug("===================================");
		LOG.debug("=param:"+member);
		LOG.debug("===================================");
		
		Message loginMessage = memberService.loginCheck(member);
		
		//아이디, 비번 확인
		if("30".equals(loginMessage.getMsgId())) {
			
			//1. 단건조회
			//2. session에 member
			
			//1.
			Member loginMember = (Member) memberService.doSelectOne(member);
			LOG.debug("loginMember.getSavePath"+loginMember.getSavePath());
			LOG.debug("loginMember.getSaveName"+loginMember.getSaveName());
			
			if(null !=loginMember) {
				loginMessage.setMsgContents(loginMember.getMemberId()+"님 로그인 되었습니다.");
				LOG.debug("loginMember:"+loginMember);
				int flag = memberService.doLoginCnt(loginMember);
				LOG.debug("doLoginCnt() flag:"+flag);
				memberService.upgradeGrades(loginMember);
			}
			
			session.setAttribute("member", loginMember);
		}
		//Gson 불필요.
		
		return loginMessage;
	}
	
	/**
	 * 회원 단건 조회
	 * @param dto
	 * @return JSON(member)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/do_selectone.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Member member) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+member);
		LOG.debug("===================================");
		
		Member outVO = (Member) this.memberService.doSelectOne(member);
		LOG.debug("===================================");
		LOG.debug("=outVO:"+outVO);
		LOG.debug("===================================");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(outVO);
		LOG.debug("===================================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("===================================");
		
		return jsonStr;
	}
	
	/**
	 * 회원 수정
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/do_update.do",method = RequestMethod.POST
			,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doUpdate(Member member) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+member);
		LOG.debug("===================================");
		
		int flag = this.memberService.doUpdate(member);
		String resultMsg = "";
		if(1 == flag) {
			resultMsg = member.getMemberId()+"님\n수정 성공.";
		}else {
			resultMsg = member.getMemberId()+"님\n수정 실패.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("===================================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("===================================");
		
		return jsonStr;
	}
	
	/**
	 * 회원 삭제
	 * @param dto
	 * @return JSON(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/do_delete.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Member member) throws SQLException {
		LOG.debug("===================================");
		LOG.debug("=param:"+member);
		LOG.debug("===================================");
		int flag = this.memberService.doDelete(member);
		String resultMsg = "";
		if(1==flag) {
			resultMsg = member.getMemberId()+"님\n삭제 되었습니다.";
		} else {
			resultMsg = "삭제 실패.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("===================================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("===================================");
		
		return jsonStr;
	}
	
	/**
	 * 회원 등록
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws RuntimeException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/do_insert.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Member member) throws SQLException {
		
		LOG.debug("===================================");
		LOG.debug("=param:"+member);
		LOG.debug("===================================");
		
		if(null == member.getMemberId() || member.getMemberId().equals("")) {
			LOG.debug(member.getMemberId());
			throw new NullPointerException("아이디를 입력하세요.");
		}
		
		int flag = memberService.doInsert(member);
		String resultMsg = "";
		if(1==flag) {
			resultMsg = member.getMemberId()+"님 환영합니다.";
		} else {
			resultMsg = "등록 실패.";
		}
		
		Message message = new Message();
		message.setMsgId(flag+"");
		message.setMsgContents(resultMsg);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(message);
		LOG.debug("===================================");
		LOG.debug("jsonStr:"+jsonStr);
		LOG.debug("===================================");
		
		return jsonStr;
	}
}
