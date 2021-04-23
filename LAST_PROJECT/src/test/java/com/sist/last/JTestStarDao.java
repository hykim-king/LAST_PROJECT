package com.sist.last;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sist.last.dao.StarDaoImpl;
import com.sist.last.vo.Star;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //bean들을 다 올려놓음
public class JTestStarDao {
	
	final static Logger LOG = Logger.getLogger(JTestStarDao.class);
	
	@Autowired
	ApplicationContext  context;//테스트 오브젝트가 만들어 지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	
	@Autowired
	private StarDaoImpl dao;
		
	Star star01;
	Star star02;
	Star star03;

	@Before
	public void setUp() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@Before=");
		LOG.debug("=======================");
		
		LOG.debug("=context="+context);
		
		star01=new Star("AAAA","1","JJ",5);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@tearDown=");
		LOG.debug("=======================");
	}
	
	@Test
	@Ignore
	public void doInsert() throws SQLException {
		dao.doInsert(star01);
		
	}
	
	@Test
	@Ignore
	public void doDelete() throws SQLException {
		dao.doDelete(star01);
		
	}
	
	@Test
//	@Ignore
	public void doUpdate() throws SQLException {
		//1. 기존데이터 삭제
		//2. 신규데이터 입력
		//3. 데이터 수정 + update
		
		//1.
		dao.doDelete(star01);
		
		//2.
		int flag = dao.doInsert(star01);
		assertThat(flag, is(1));
		
		//3.
		star01.setStarScore(3);
		LOG.debug("star01:"+star01);
		
		flag = dao.doUpdate(star01);
		assertThat(flag, is(1));
	}
	

	@Test
	@Ignore
	public void beans() {
		LOG.debug("=======================");
		LOG.debug("=test=");
		LOG.debug("=======================");
		
		LOG.debug("=context="+context);
	}

}
