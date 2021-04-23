package com.sist.last.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
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

import com.sist.last.dao.ReviewDaoImpl;

//메소드 수행 순서(method ASCENDING)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//스프링 테스트 컨텍스트 프레임의 JUnit 기능 확장
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestReviewService {
	
	final Logger LOG = LoggerFactory.getLogger(JTestReviewService.class);

	@Autowired
	ApplicationContext context;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ReviewDaoImpl reviewDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void beans() {
		LOG.debug("context: " + context);
		LOG.debug("reviewService: " + reviewService);
		LOG.debug("reviewDao: " + reviewDao);


		assertThat(this.context, is(notNullValue()));
		assertThat(this.reviewService, is(notNullValue()));
		assertThat(this.reviewDao, is(notNullValue()));

	}

}
