package com.sist.last;

import static org.hamcrest.CoreMatchers.is;
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

import com.sist.last.cmn.SearchReview;
import com.sist.last.dao.ReviewDaoImpl;
import com.sist.last.vo.Review;

//메소드 수행 순서(method ASCENDING)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//스프링 테스트 컨텍스트 프레임의 JUnit 기능 확장
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestReviewDao {
	
	final static Logger LOG = LoggerFactory.getLogger(JTestReviewDao.class);

	@Autowired
	ApplicationContext context;// 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	
	@Autowired
	private ReviewDaoImpl dao;
	
	Review review01;
	Review review02;
	Review review03;
	
	// 페이징
	SearchReview search01;
	
	@Before
	public void setUp() throws Exception {
		// LOG.debug("-@setUp-");
		
		// LOG.debug("context: " + context);
		
		review01 = new Review("1", "tjdus", "1", "0", "test1", "", "", "");
		review02 = new Review("2", "tjdus", "1", "1", "test2", "", "", "");
		review03 = new Review("3", "tjdus", "1", "2", "test3", "", "", "");
	
		search01 = new SearchReview(2, 1, "1","");
		
	}

	@After
	public void tearDown() throws Exception {
		// LOG.debug("-@tearDown-");

	}
	
	@Test
	public void doRetrieve() throws ClassNotFoundException, SQLException {
		dao.doRetrieve(search01);
	}
	
	@Test
	@Ignore
	public void getAll() throws ClassNotFoundException, SQLException {
		//리뷰 삭제
		dao.doDelete(review01);
		dao.doDelete(review02);
		dao.doDelete(review03);
		
		// 리뷰 등록
		int flag = dao.doInsert(review01);
		flag += dao.doInsert(review02);
		flag += dao.doInsert(review03);
		
		assertThat(flag, is(3));
		
	}
		
	@Test
	public void doUpdate() throws ClassNotFoundException, SQLException {
		//리뷰 삭제
		dao.doDelete(review01);
		dao.doDelete(review02);
		dao.doDelete(review03);
		
		// 리뷰 등록
		int flag = dao.doInsert(review01);
		flag += dao.doInsert(review02);
		flag += dao.doInsert(review03);
		
		// 리뷰 수정
		review01.setContents(review01.getContents()+"_up");
		review01.setModId(review01.getModId()+"tjdus1");
		review01.setModDt("");
		
		LOG.debug("review01: "+review01);
		
		flag = dao.doUpdate(review01);
		assertThat(flag, is(1));
		
		// 데이터 단건조회
		Review vsReview01 = (Review) dao.doSelectOne(review01);
		Review vsReview02 = (Review) dao.doSelectOne(review02);
		Review vsReview03 = (Review) dao.doSelectOne(review03);
		
	}
	
	@Test
	public void test() {
		// LOG.debug("--------------");
		// LOG.debug("-@test-");
		// LOG.debug("--------------");
	}

}
