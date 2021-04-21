package com.sist.last.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNotNull;

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

import com.sist.last.cmn.SearchOrder;
import com.sist.last.cmn.StringUtil;
import com.sist.last.vo.Product;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		  						  "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class JTestProductService {

	final Logger LOG = Logger.getLogger(JTestProductService.class);

	@Autowired
	ApplicationContext context;
	
	@Autowired
	ProductServiceImpl productService;
	
	Product product01;
	Product product02;
	Product product03;
	Product product04;
	Product product05;
	Product product06;
	
	
	SearchOrder search01;
	SearchOrder search02;
	SearchOrder search03;
	SearchOrder search04;	
	SearchOrder search05;
	SearchOrder search06;
	SearchOrder search07;
	SearchOrder search08;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=================================");
		LOG.debug("=@Before=setUp()=");
		LOG.debug("=================================");
		
		product01 = new Product("1", "test01", "img01", "title01", "contents01", "company01", "10", "tag01", 1, 1, "refund01", "", "test01", "");
		product02 = new Product("2", "test02", "img02", "title02", "contents02", "company02", "20", "tag02", 2, 2, "refund02", "", "test02", "");
		product03 = new Product("3", "test03", "img03", "title03", "contents03", "company03", "30", "tag03", 3, 3, "refund03", "", "test03", "");
		product04 = new Product("4", "test01", "img01", "title01", "contents01", "company01", "10", "tag01", 1, 1, "refund01", "", "test01", "");
		product05 = new Product("5", "test02", "img02", "title02", "contents02", "company02", "20", "tag02", 2, 2, "refund02", "", "test02", "");
		product06 = new Product("6", "test03", "img03", "title03", "contents03", "company03", "30", "tag03", 3, 3, "refund03", "", "test03", "");
	
		search01 = new SearchOrder("10", "tle01", 10, 1, "10");
		search02 = new SearchOrder("20", "tle02", 10, 1, "20");
		search03 = new SearchOrder("30", "ag01", 10, 1, "10");
		search04 = new SearchOrder("40", "ag02", 10, 1, "20");
		search05 = new SearchOrder("50", "", 10, 1, "20");
		search06 = new SearchOrder("", "", 10, 1, "10");
		search07 = new SearchOrder("", "", 10, 1, "20");
		search08 = new SearchOrder("", "", 10, 1, "");
		
	}

	
	
	
	@Test
	@Ignore
	public void doUpdate() throws SQLException {

		
	}
	
	@Test
	@Ignore
	public void doDelete() throws SQLException, ClassNotFoundException {
		
	}
	
	@Test
	//@Ignore
	public void doInsert() throws SQLException, ClassNotFoundException {
		product01.setStoreSeq(StringUtil.getPK(""));
		productService.doInsert(product01);
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws SQLException {
		
	}
	
	@Test
	@Ignore
	public void doRetrieve() throws SQLException {
		
	}
	

	@Test
	public void beans() {
		
		LOG.debug("context"+context);
		assertThat(context, is(notNullValue()));
		
		LOG.debug("productService"+productService);
		assertThat(productService, is(notNullValue()));
	}

}
