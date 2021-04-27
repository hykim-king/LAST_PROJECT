package com.sist.last;

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

import com.sist.last.cmn.Search;
import com.sist.last.dao.ScrapDaoImpl;
import com.sist.last.vo.Scrap;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //bean들을 다 올려놓음
public class JtestScrapDao {
	
	final static Logger LOG = Logger.getLogger(JtestScrapDao.class);
	
	@Autowired
	ApplicationContext  context;//테스트 오브젝트가 만들어 지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	
	@Autowired
	private ScrapDaoImpl dao;
	
	Scrap scrap01;
	Scrap scrap02;
	Scrap scrap03;
	
	Scrap check01;
	Scrap check02;
	Scrap check03;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@Before=");
		LOG.debug("=======================");
		
		LOG.debug("=context="+context);
		
		scrap01=new Scrap("13","1","1","","","");
		check01= new Scrap("","2222","yeonsu22","","","");
		check02= new Scrap("","2221","yeonsu22","","","");
		check03= new Scrap("","2221","yeonsu21","","","");
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@tearDown=");
		LOG.debug("=======================");
	}
	
	
	@Test
	public void doScrapCheck() throws SQLException {
		dao.scrapCheck(check01);
		dao.scrapCheck(check02);
		dao.scrapCheck(check03);
	}
	
	@Test
	@Ignore
	public void doInsert() throws SQLException {
		dao.doInsert(scrap01);
		
	}
	
	@Test
	@Ignore
	public void doDelete() throws SQLException {
		dao.doDelete(scrap01);
		
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws SQLException {
		dao.doSelectOne(scrap01);
	}
	
	
	@Test
	@Ignore
	public void doRetrieve() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doRetrieve==");
		LOG.debug("================");
		
		Search search01 = new Search("","",0,0); 
		
		dao.doRetrieve(search01);
		
		
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
