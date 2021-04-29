package com.sist.last.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import com.sist.last.dao.MemberDaoImpl;
import com.sist.last.vo.Grade;
import com.sist.last.vo.Member;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //bean들을 다 올려놓음
public class JTestMemberService {
	final Logger LOG = LoggerFactory.getLogger(JTestMemberService.class);
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberDaoImpl memberDao;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	private List<Member> members;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("setUp()");
		// -사용자 레벨은 : NEW, SILVER, GOLD, VIP
		// -사용자가 처음 가입하면 : NEW
		// -가입 이후 30회 이상 로그인 하면 : SILVER
		// -SILVER 등급이면서 작성 게시글이 20번 이상 스크랩 되면 GOLD로 등급 up
		// -사용자 등급의 변경 작업은 일정한 주기를 가지고 일괄처리(트랜잭션 관리)
		//등업 대상이면 : 해당 등급으로 등업한다.
		members = Arrays.asList(
						new Member("L_100_01","img111","","","1234","L01","안녕하세요",Grade.NEW,1,0,0,"","L01","")
					   ,new Member("L_100_02","img222","","","1234","L02","HI",Grade.NEW,1,19,30,"","L02","")
					   ,new Member("L_100_03","img333","","","1234","L03","Hello",Grade.SILVER,1,20,30,"","L03","")
					   ,new Member("L_100_04","img444","","","1234","L03","Hello",Grade.GOLD,1,40,30,"","L03","")
					   ,new Member("L_100_05","img555","","","1234","L03","Hello",Grade.GOLD,1,50,30,"","L03","")
				  );
	}
	
	@Test
	@Ignore
	public void doInsert() throws SQLException {
		//* Grade가 Null이면 : Grade.NEW처리
		//1. 기존데이터 삭제
		//2. Level이 Null + 데이터 등록
		//3. 조회 + 등급 == Grade.NEW
		
		//1.
		for(Member member: members) {
			memberDao.doDelete(member);
		}
		
		//2.
		Member memberWithoutGrade = members.get(0); //Grade.NEW
		Member memberWithGrade = members.get(4);    //Grade.GOLD
		memberWithoutGrade.setGrade(null);
		
		this.memberService.doInsert(memberWithoutGrade);
		this.memberService.doInsert(memberWithGrade);
		
		Member memberWithoutGradeRead = (Member) memberDao.doSelectOne(memberWithoutGrade);
		Member memberWithGradeRead = (Member) memberDao.doSelectOne(memberWithGrade);

		//Grade == null -> Grade.NEW
		assertThat(memberWithoutGradeRead.getGrade(), is(Grade.NEW));
		//Grade.GOLD == Grade.GOLD
		assertThat(memberWithGradeRead.getGrade(), is(memberWithGrade.getGrade()));
	}
	
	@Test
	@Ignore
	public void upgradeGrades() throws SQLException, IllegalAccessException {
		//1. 데이터 삭제
		//2. 데이터 입력
		
		//1.
		for(Member member: members) {
			memberDao.doDelete(member);
		}
		
		int flag = 0;
		
		//2.
		for(Member member: members) {
			flag += memberDao.doInsert(member);
		}
		
		assertThat(flag, is(5));
		
		//등업
		this.memberService.upgradeGrades();
		
		//등업 로직 check
		checkGrade(members.get(0), Grade.NEW);
		checkGrade(members.get(1), Grade.SILVER);
		checkGrade(members.get(2), Grade.GOLD);
		checkGrade(members.get(3), Grade.VIP);
		checkGrade(members.get(4), Grade.VIP);
	}
	
	/**
	 * 등업한 Grade와 예상 Grade 비교
	 * @param member
	 * @param expectedLevel
	 * @throws SQLException
	 */
	private void checkGrade(Member member, Grade expectedGrade) throws SQLException {
		Member memberUpGrade = (Member) this.memberDao.doSelectOne(member);
		assertThat(memberUpGrade.getGrade(), is(expectedGrade));
	}
	
	//Transaction 테스트
	@Test
	@Ignore
	public void upgradeAllOrNothing() throws Exception {
		//1. 데이터 삭제
		//2. 데이터 입력
		//3. 등업
		
		//1.
		for(Member member: members) {
			memberDao.doDelete(member);
		}
		
		int flag = 0;
		
		//2.
		for(Member member: members) {
			flag += memberDao.doInsert(member);
		}
		
		assertThat(flag, is(5));
		
		try {
			memberService.upgradeGrades();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(RuntimeException e) {
			LOG.debug("=========================");
			LOG.debug("=RuntimeException="+e.getMessage());
			LOG.debug("=========================");
			//e.printStackTrace();
		}
		
		//강제 예외 발생 코드를 제외하였으므로, 정상 등업 된다.
		checkGrade(members.get(1),Grade.SILVER); //Transaction 처리 되면 Rollback->
		
	}
	
	@After
	public void tearDown() throws Exception {
		LOG.debug("tearDown()");
	}

	@Test
	public void beans() {
		LOG.debug("context:"+context);
		LOG.debug("memberService:"+memberService);
		LOG.debug("memberDao:"+memberDao);
		LOG.debug("dataSource:"+dataSource);
		LOG.debug("transactionManager:"+transactionManager);
		
		assertThat(this.transactionManager, is(notNullValue()));
		assertThat(this.dataSource, is(notNullValue()));
		assertThat(this.memberDao, is(notNullValue()));
		assertThat(this.memberService, is(notNullValue()));
		assertThat(this.context, is(notNullValue()));
	}

}
