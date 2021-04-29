package com.sist.last;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

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

import com.sist.last.dao.MemberDaoImpl;
import com.sist.last.vo.Grade;
import com.sist.last.vo.Member;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestMemberDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestMemberDao.class);
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	private MemberDaoImpl dao;
	
	Member member01;
	Member member02;
	Member member03;
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("================");
		LOG.debug("==@before==");
		LOG.debug("================");
		
		LOG.debug("=context="+context);
		member01 = new Member("L_100_01","img123","","","1234","L01","안녕하세요",Grade.NEW,1,1,1,"","L01","");
		member02 = new Member("L_100_02","img456","","","1234","L02","HI",Grade.SILVER,1,1,1,"","L02","");
		member03 = new Member("L_100_03","img789","","","1234","L03","Hello",Grade.GOLD,1,1,1,"","L03","");
	
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("================");
		LOG.debug("==@After==");
		LOG.debug("================");	
	}

	@Test
	public void getAll() throws ClassNotFoundException, SQLException {
		//삭제
		dao.doDelete(member01);
		dao.doDelete(member02);
		dao.doDelete(member03);
		
		//데이터 추가
		int flag = 0;
		flag = dao.doInsert(member01);
		flag += dao.doInsert(member02);
		flag += dao.doInsert(member03);
		assertThat(flag,is(3));
		
		Member member09 = new Member();
		member09.setMemberId("L_100");
		List<Member> list = dao.getAll(member09);
		assertThat(list.size(),is(3));
	}
	
	@Test
	@Ignore
	public void doInsert() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doInsert==");
		LOG.debug("================");
		
		//검색용도
		Member member09 = new Member();
		member09.setMemberId("L_100");
		
		int flag = dao.doInsert(member01);
		//assertThat(flag, is(dao.count(member09))); //flag(actual)과 is(예상) 비교
		
		flag += dao.doInsert(member02);
		//assertThat(flag, is(dao.count(member09))); //flag(actual)과 is(예상) 비교
		
		flag += dao.doInsert(member03);
		//assertThat(flag, is(dao.count(member09))); //flag(actual)과 is(예상) 비교
		
	}
	
	
	@Test
	@Ignore
	public void doDelete() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doDelete==");
		LOG.debug("================");
		
		//삭제
		dao.doDelete(member01);
		dao.doDelete(member02);
		dao.doDelete(member03);
	}
	
	@Test
	public void doUpdate() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doUpdate==");
		LOG.debug("================");
		
		//1.기존데이터 삭제
		//2.신규데이터 입력
		//3.데이터수정 + update
		
		//1.
		dao.doDelete(member01);
		dao.doDelete(member02);
		dao.doDelete(member03);
		
		//2.
		int flag = dao.doInsert(member01);
		assertThat(flag, is(1)); //flag(actual)과 is(예상) 비교
		flag += dao.doInsert(member02);
		assertThat(flag, is(2)); //flag(actual)과 is(예상) 비교
		flag += dao.doInsert(member03);
		assertThat(flag, is(3)); //flag(actual)과 is(예상)
		
		//3.
		member01.setPasswd(member01.getPasswd()+"_U");
		member01.setNickname(member01.getNickname()+"_U");
		member01.setIntroduce(member01.getIntroduce()+"_U");
		member01.setGrade(Grade.VIP);
		member01.setDiv(member01.getDiv()+1);
		member01.setScrap(member01.getScrap()+1);
		member01.setLogin(member01.getLogin()+1);
		member01.setModId(member01.getModId()+"_U");
		LOG.debug("member01:"+member01);
		
		flag = dao.doUpdate(member01);
		assertThat(flag, is(1));
		
		//3.1 수정 데이터 조회
		Member changeMember = (Member) dao.doSelectOne(member01);
		LOG.debug("changeMember"+changeMember);
		checkUser(changeMember,member01);
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doSelectOne==");
		LOG.debug("================");
		
		Member vsMember01 = (Member) dao.doSelectOne(member01);
		Member vsMember02 = (Member) dao.doSelectOne(member02);
		Member vsMember03 = (Member) dao.doSelectOne(member03);
		
		//비교
		checkUser(vsMember01, member01);
		checkUser(vsMember02, member02);
		checkUser(vsMember03, member03);
		
	}
	
	private void checkUser(Member vsMember, Member member) {
		//비교
		assertThat(vsMember.getMemberId(), is(member.getMemberId()));
		assertThat(vsMember.getImgId(), is(member.getImgId()));
		assertThat(vsMember.getPasswd(), is(member.getPasswd()));
	}
	

}
