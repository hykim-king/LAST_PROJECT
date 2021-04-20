package com.sist.last;


import static org.junit.Assert.*;

import java.sql.SQLException;

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

import com.sist.last.cmn.Search;
import com.sist.last.dao.ReplyDao;
import com.sist.last.dao.ReplyDaoImpl;
import com.sist.last.vo.Reply;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestReplyDao {
	
	final static Logger LOG = LoggerFactory.getLogger(JTestReplyDao.class);
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	private ReplyDao dao;
	
	Reply reply01;
	Reply reply02;
	Reply reply03;
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("================");
		LOG.debug("==@before==");
		LOG.debug("================");
		
		LOG.debug("=context="+context);
		reply01 = new Reply("R_01", "L_100_01", "1234", "reply01~~", "", "L_100_01", "");
		reply02 = new Reply("R_02", "L_100_02", "1234", "reply02~~", "", "L_100_02", "");
		reply03 = new Reply("R_03", "L_100_03", "1234", "reply03~~", "", "L_100_03", "");
	
		dao = context.getBean("replyDao", ReplyDaoImpl.class);
	
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("================");
		LOG.debug("==@After==");
		LOG.debug("================");	
	}

	@Test
	@Ignore
	public void doInsert() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doInsert==");
		LOG.debug("================");
		
		int flag = dao.doInsert(reply01);
		flag += dao.doInsert(reply02);
		flag += dao.doInsert(reply03);
	}
	
	@Test
	@Ignore
	public void doDelete() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doDelete==");
		LOG.debug("================");
		
		
		dao.doDelete(reply01);
		dao.doDelete(reply02);
		dao.doDelete(reply03);
		
	}
	
	@Test
//	@Ignore
	public void doUpdate() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doUpdate==");
		LOG.debug("================");
		
		//삭제
		dao.doDelete(reply01);
		dao.doDelete(reply02);
		dao.doDelete(reply03);
		
		//입력
		int flag = dao.doInsert(reply01);
		flag += dao.doInsert(reply02);
		flag += dao.doInsert(reply03);
		
		//수정
		reply01.setContents(reply01.getContents()+"_U");
		reply01.setModId(reply01.getModId()+"_U");
		LOG.debug("reply01:"+reply01);
		
		flag = dao.doUpdate(reply01);
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doSelectOne==");
		LOG.debug("================");
		
		Reply vsReply01 = (Reply) dao.doSelectOne(reply01);
		Reply vsReply02 = (Reply) dao.doSelectOne(reply02);
		Reply vsReply03 = (Reply) dao.doSelectOne(reply03);
		
		
		
	}
	
	@Test
	@Ignore
	public void doRetrieve() throws SQLException {
		LOG.debug("================");
		LOG.debug("==@doRetrieve==");
		LOG.debug("================");
		
		//검색
		Search search01 = new Search(10,1); 
		
		dao.doRetrieve(search01);
		
		
	}
	

}
