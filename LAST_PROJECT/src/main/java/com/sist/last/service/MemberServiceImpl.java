package com.sist.last.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.Message;
import com.sist.last.dao.MemberDaoImpl;
import com.sist.last.vo.Grade;
import com.sist.last.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	final Logger LOG = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	public final static int MIN_LOGIN_COUNT_FOR_SILVER = 30; //SILVER로 등업하기 위한 최소 로그인 횟수
	public final static int MIN_SCRAP_COUNT_FOR_GOLD = 20;   //GOLD로 등업하기 위한 최소 스크랩 수
	public final static int MIN_SCRAP_COUNT_FOR_VIP = 40;    //VIP로 등업하기 위한 최소 스크랩 수
	
	@Autowired
	private MemberDaoImpl memberDao;
	
	@Autowired
	@Qualifier("mailSenderImpl")
	private MailSender mailSender;
	
	public MemberServiceImpl() {}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {

		return this.memberDao.doSelectOne(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {

		return this.memberDao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {

		return this.memberDao.doDelete(dto);
	}

	/**
	 * 최초 회원가입 시 등급: NEW
	 * Grade가 Null이면 NEW 처리
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		
		if(null == member.getGrade()) {
			member.setGrade(Grade.NEW);
		}
		
		flag = this.memberDao.doInsert(member);
		
		return flag;
	}
	
	//회원 데이터를 모두 조회한다.
	//회원 데이터를 1건 조회 후 등업 대상인지 확인한다.
	// -사용자 레벨은 : NEW, SILVER, GOLD, VIP
	// -사용자가 처음 가입하면 : NEW
	// -가입 이후 30회 이상 로그인 하면 : SILVER
	// -SILVER 등급이면서 작성 게시글이 20번 이상 스크랩 되면 GOLD로 등급 up
	// -사용자 등급의 변경 작업은 일정한 주기를 가지고 일괄처리(트랜잭션 관리)
	//등업 대상이면 : 해당 등급으로 등업한다.
	@Override
	public void upgradeGrades(DTO dto) throws SQLException, IllegalAccessException {

		Member member = (Member) dto;

		if(canUpgradeGrade(member)==true) {
				upgradeGrade(member);
		}
		
	}//--upgradeLevels
	
	//등업처리
	protected void upgradeGrade(Member member) throws SQLException {
//		LOG.debug("upgradeLevel:"+member.getMemberId());
//		if("L_100_02".equals(member.getMemberId())){
//			//사용자 정의 예외 발생! -> TestUserServiceException
//			throw new TestMemberServiceException("트랜잭션 테스트:"+member.getMemberId());
//		}
		
		member.upgradeGrade();
		this.memberDao.doUpdate(member);
	}
	
	//업그레이드 가능 확인 메소드
	private boolean canUpgradeGrade(Member member) throws IllegalAccessException {
		Grade currGrade = member.getGrade();
		//등급별로 구분해서 조건 판단
		switch(currGrade) {
			case NEW:    return (member.getLogin() >= MIN_LOGIN_COUNT_FOR_SILVER);
			case SILVER: return (member.getScrap() >= MIN_SCRAP_COUNT_FOR_GOLD);
			case GOLD:   return (member.getScrap() >= MIN_SCRAP_COUNT_FOR_VIP);
			case VIP:    return false;
			default: throw new IllegalAccessException("Unknown Grade");
		}
	}
	
	//email전송
	public String sendAuthEmail(String email) throws SQLException {
		//MailSender 구현체. 외부에서 주입
		/*
		POP 서버명 : pop.naver.com
		SMTP 서버명 : smtp.naver.com
		POP 포트 : 995, 보안연결(SSL) 필요
		SMTP 포트 : 465, 보안 연결(SSL) 
		필요아이디 : morningstar_0921
		비밀번호 : 네이버 로그인 비밀번호
		*/
		int auth = (int)(Math.random() * (999999 - 100000 + 1)) + 100000;
		LOG.debug("auth:"+auth);
		
		String from = "morningstar_0921@naver.com";  //from. 무조건 Naver아이디
		String title = "Intery 회원가입 인증번호 안내"; //제목
		String contents = "본인확인 인증번호 ["+auth+"]를 화면에 입력하세요."; //내용
		String recAddr = email; //받는 사람
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		
		simpleMessage.setFrom(from);
		simpleMessage.setTo(recAddr);
		simpleMessage.setSubject(title);
		simpleMessage.setSentDate(new Date());
		simpleMessage.setText(contents);
		
		mailSender.send(simpleMessage);
		
		return auth+"";
		
	}

	@Override
	public int idCheck(DTO dto) throws SQLException {

		return this.memberDao.idCheck(dto);
	}

	@Override
	public int nickCheck(DTO dto) throws SQLException {

		return this.memberDao.nickCheck(dto);
	}

	/**
	 * message.setMsgId =10 -> 아이디를 확인하세요.
	 * message.setMsgId =20 -> 비밀번호를 확인하세요.
	 * message.setMsgId =30 -> 아이디, 비밀번호가 확인되었습니다.
	 * 
	 */
	@Override
	public Message loginCheck(DTO dto) throws SQLException {
		//1. id가 있는지 확인
		//2. id, 비번 확인
		//3. message 전달.
		Message message = new Message();
		
		//1.
		int flag = this.memberDao.idCheck(dto);
		if(flag<1) {
			message.setMsgId("10");
			message.setMsgContents("아이디를 확인하세요.");
			return message;
		}
		
		//2.
		flag = this.memberDao.passwordCheck(dto);
		if(flag<1) {
			message.setMsgId("20");
			message.setMsgContents("비밀번호를 확인하세요.");
			return message;
		}
		
		//3.
		message.setMsgId("30");
		message.setMsgContents("아이디, 비밀번호가 확인되었습니다.");
		
		return message;
	}

	@Override
	public String sendPasswdEmail(String email) throws SQLException {
		Member member = new Member();
		member.setMemberId(email);
		
		Member outVO = (Member) this.memberDao.doSelectOne(member);
		
		String from = "morningstar_0921@naver.com";  //from. 무조건 Naver아이디
		String title = "Intery 비밀번호 안내"; //제목
		String contents = "Intery에 방문해주셔서 감사합니다.\n"+"["+outVO.getNickname()+"]"+"님의 비밀번호는 "+outVO.getPasswd()+" 입니다."; //내용
		String recAddr = outVO.getMemberId(); //받는 사람
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		
		simpleMessage.setFrom(from);
		simpleMessage.setTo(recAddr);
		simpleMessage.setSubject(title);
		simpleMessage.setSentDate(new Date());
		simpleMessage.setText(contents);
		
		mailSender.send(simpleMessage);
		
		return outVO.getPasswd();
	}

	@Override
	public int doLoginCnt(DTO dto) throws SQLException {

		return this.memberDao.doLoginCnt(dto);
	}

}
