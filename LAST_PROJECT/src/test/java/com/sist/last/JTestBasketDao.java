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
import com.sist.last.dao.BasketDao;
import com.sist.last.vo.Basket;
import com.sist.last.vo.Qna;


//메소드 수행 순서: method ASCENDING EX)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								  "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class JTestBasketDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestBasketDao.class);
	
	@Autowired
	ApplicationContext context; //테스트 오브젝트 만들어 지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.

	@Autowired
	private BasketDao dao;
	
	Basket basket01;
	Basket basket02;
	Basket basket03;
	Basket basketUp;
	
	Search search01;
	Search search02;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=======================");		
		LOG.debug("setUp");
		LOG.debug("=======================");
		
		basket01 =  new Basket("basketSeqTest01", "storeSeqTest01", "down8325", "qnaTitleTest01", "optOneTest01", "optTwoTest01", 3, 1500,50000,"","","");
		basket02 =  new Basket("basketSeqTest02", "storeSeqTest01", "down8325", "qnaTitleTest01", "optOneTest02", "optTwoTest02", 4, 2000,24000,"","","");
		basket03 =  new Basket("basketSeqTest03", "storeSeqTest01", "ang8325", "qnaTitleTest01", "optOneTest03", "optTwoTest03", 5, 3000,35000,"","","");

		basketUp =  new Basket("basketSeqTest01", "storeSeqTest01", "down8325", "qnaTitleTest01_U", "optOneTest01", "optTwoTest01", 3, 1500,50000,"","","");
		
		search01 =  new Search("", "", 10, 1);
		search02 =  new Search("", "", 10, 1);
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=======================");		
		LOG.debug("tearDown");
		LOG.debug("=======================");
	}

	@Test
	@Ignore
	public void doInsert() throws SQLException {
		dao.doInsert(basket01);
		dao.doInsert(basket02);
		dao.doInsert(basket03);
	}
	
	@Test
//	@Ignore
	public void doSelectOne() throws SQLException {
		dao.doSelectOne(basket01);
		dao.doSelectOne(basket02);
		dao.doSelectOne(basket03);
	}
	
	@Test
	public void doRetrieve() throws SQLException {
		dao.doRetrieve(search01);
	}
	
	@Test
	@Ignore
	public void doDelete() throws SQLException {
		dao.doDelete(basket01);
		dao.doDelete(basket02);
		dao.doDelete(basket03);
	}
	
	@Test
	@Ignore
	public void doUpdate() throws SQLException {
		dao.doDelete(basket01);
		dao.doInsert(basket01);
		dao.doUpdate(basketUp);
	}
	
	@Test
	public void beans() {
		LOG.debug("context"+context);
		assertThat(context, is(notNullValue()));
	}

}
