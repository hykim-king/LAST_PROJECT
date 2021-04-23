package com.sist.last;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
import com.sist.last.dao.QnaDao;
import com.sist.last.vo.Qna;


//메소드 수행 순서: method ASCENDING EX)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								  "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class JTestQnaDao {
	final static Logger LOG = LoggerFactory.getLogger(JTestQnaDao.class);
	
	@Autowired
	ApplicationContext context; //테스트 오브젝트 만들어 지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.

	@Autowired
	private QnaDao dao;
	
	Qna qna01;
	Qna qna02;
	Qna qna03;
	Qna qnaUp;
	
	Search search01;
	Search search02;
	Search search03;
	Search search04;
	Search search05;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=======================");		
		LOG.debug("setUp");
		LOG.debug("=======================");
		
		qna01 =  new Qna("qnaSeqTest01", "down8325", "qnaImageTest01", "qnaTitleTest01", "qnaContentsTest01", "qnaTagTest01", "", "", "");
		qna02 =  new Qna("qnaSeqTest02", "down8325", "qnaImageTest02", "qnaTitleTest02", "qnaContentsTest02", "qnaTagTest02", "", "", "");
		qna03 =  new Qna("qnaSeqTest03", "down8325", "qnaImageTest03", "qnaTitleTest03", "qnaContentsTest03", "qnaTagTest03", "", "", "");
		
		qnaUp = new Qna("qnaSeqTest01", "down8325", "qnaImageTest01_U", "qnaTitleTest01_U", "qnaContentsTest01_U", "qnaTagTest01", "", "down8325", "");
		
		//검색조건: 제목(10), 태그(20), 제목+태그(30)
		search01 =  new Search("10", "Test01", 		10, 1);
		search02 =  new Search("20", "TagTest03",	10, 1);
		search03 =  new Search("30", "Title", 2, 1);
		search04 =  new Search("30", "Tag",   10, 1);
		search05 =  new Search("",   "",      10, 1);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=======================");		
		LOG.debug("tearDown");
		LOG.debug("=======================");
	}

	
	@Test
	//@Ignore
	public void doInsert() throws SQLException {
		dao.doInsert(qna01);
		dao.doInsert(qna02);
		dao.doInsert(qna03);
	}
	
	@Test
	//@Ignore
	public void doSelectOne() throws SQLException {
		dao.doSelectOne(qna01);
		dao.doSelectOne(qna02);
		dao.doSelectOne(qna03);
	}
	
	@Test
	//@Ignore
	public void doRetrieve() throws SQLException {
		dao.doRetrieve(search01);
		dao.doRetrieve(search02);
		dao.doRetrieve(search03);
		dao.doRetrieve(search04);
		dao.doRetrieve(search05);
	}
	
	@Test
	//@Ignore
	public void doDelete() throws SQLException {
		dao.doDelete(qna01);
		dao.doDelete(qna02);
		dao.doDelete(qna03);
	}
	
	@Test
	//@Ignore
	public void doUpdate() throws SQLException {
		//dao.doInsert(qna01);
		dao.doUpdate(qnaUp);
	}
	
	@Test
	//@Ignore
	public void beans() {
		LOG.debug("context: "+context);
		assertThat(context, is(notNullValue()));
	}

}
