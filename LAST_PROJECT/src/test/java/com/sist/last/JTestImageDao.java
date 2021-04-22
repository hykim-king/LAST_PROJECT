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

import com.sist.last.dao.ImageDaoImpl;
import com.sist.last.vo.Image;

//메소드 수행 순서(method ASCENDING)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//스프링 테스트 컨텍스트 프레임의 JUnit 기능 확장
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class JTestImageDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestImageDao.class);

	@Autowired
	ApplicationContext context;// 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.

	@Autowired
	private ImageDaoImpl dao;

	Image image01;
	Image image02;
	Image image03;
	
	@Test
	public void doSelectOne() throws SQLException {
		// 단건조회
		dao.doSelectOne(image01);	
		// 목록조회
		dao.doRetrieve(image01);		
	}

	@Test
	//@Ignore
	public void doUpdate() throws SQLException {
		// 사진 삭제
		dao.doDelete(image01);
		dao.doDelete(image02);
		dao.doDelete(image03);

		// 사진 등록
		int flag = dao.doInsert(image01);
		flag += dao.doInsert(image02);
		flag += dao.doInsert(image03);
		
		// 사진 수정
		image01.setOrgName(image01.getOrgName()+"_up");
		image01.setSaveName(image01.getSaveName()+"_up");
		
		LOG.debug("user01: "+image01);
		
		flag = dao.doUpdate(image01);
		assertThat(flag, is(1));
		
		// 단건조회
		dao.doSelectOne(image01);	
		
		// 목록조회
		dao.doRetrieve(image01);
		dao.doRetrieve(image02);
		dao.doRetrieve(image03);
				
	}

	@Before
	public void setUp() throws Exception {

		LOG.debug("context: " + context);

		image01 = new Image("20210415", 1, "spring.jpg", "spring.jpg", "last/upload", "12.345", "");
		image02 = new Image("20210416", 2, "summer.jpg", "summer.jpg", "last/upload", "12.345", "");
		image03 = new Image("20210417", 3, "fall.jpg", "fall.jpg", "last/upload", "12.345", "");

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		LOG.debug("-@Test test02-");
	}

}
