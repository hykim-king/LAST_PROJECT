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

import com.sist.last.dao.OptDaoImpl;
import com.sist.last.vo.Opt;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //bean들을 다 올려놓음
public class JTestOptDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestOptDao.class);
	
	@Autowired //이런 타입이 있는지 확인하고 주입
	ApplicationContext context; //테스트 오브젝트가 만들어 지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	
	@Autowired //이런 타입이 있는지 확인하고 주입
	private OptDaoImpl dao;
	
	Opt opt01;
	Opt opt02;
	Opt opt03;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@Before=");
		LOG.debug("=======================");
		
		LOG.debug("=context="+context);

		opt01 = new Opt("20210414", "12345", "solshine", "레드", 12000, 1, "", "", "");
		opt02 = new Opt("20210415", "12345", "solshine", "블루", 12000, 1, "", "", "");
		opt03 = new Opt("20210416", "12345", "sunny", "S", 12000, 2, "", "", "");
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@tearDown=");
		LOG.debug("=======================");
	}
	
	/**
	 * 삭제/등록/수정/단건조회
	 * @throws SQLException
	 */
	@Test
	@Ignore
	public void doUpdate() throws SQLException {
		//1. 기존데이터 삭제
		//2. 신규데이터 입력
		//3. 데이터 수정 + update
		//* 비교
		
		//1.
		dao.doDelete(opt01);
		dao.doDelete(opt02);
		dao.doDelete(opt03);
		
		//2.
		int flag = dao.doInsert(opt01);
		assertThat(flag, is(1));
		
		flag += dao.doInsert(opt02);
		assertThat(flag, is(2));
		
		flag += dao.doInsert(opt03);
		assertThat(flag, is(3));
		
		//3.
		opt01.setTitle(opt01.getTitle()+"_U");
		opt01.setModId("sunny");
		LOG.debug("opt01:"+opt01);
		
		flag = dao.doUpdate(opt01);
		assertThat(flag, is(1));
		
		//3.1. 수정데이터 조회
		Opt changeOpt = (Opt) dao.doSelectOne(opt01);
		LOG.debug("changeOpt:"+changeOpt);
		
		checkOpt(changeOpt, opt01);
		
		//3.2. update 하지 않은 데이터 비교
		Opt unChangeOpt = (Opt) dao.doSelectOne(opt02);
		checkOpt(unChangeOpt, opt02);
	}
	
	/**
	 * 삭제/등록/목록조회
	 * @throws SQLException
	 */
	@Test
	public void doRetrieve() throws SQLException {
		//데이터 삭제
		dao.doDelete(opt01);
		dao.doDelete(opt02);
		dao.doDelete(opt03);
				
		//데이터 추가
		int flag = 0;
		flag = dao.doInsert(opt01);
		flag += dao.doInsert(opt02);
		flag += dao.doInsert(opt03);
		assertThat(flag,is(3));
		
		//데이터 전체 조회
		//검색용도
		Opt opt09 = new Opt();
		opt09.setStoreSeq("12345");
		opt09.setDiv(2);
		List<Opt> list = dao.doRetrieve(opt09);
		assertThat(list.size(),is(1));
	}
	
	/**
	 * 삭제/등록
	 * @throws SQLException
	 */
	@Test
	@Ignore
	public void addAndGet() throws SQLException {
		LOG.debug("=======================");
		LOG.debug("=@addAndGet=");
		LOG.debug("=======================");
		
		//삭제
		dao.doDelete(opt01);
		//assertThat(flag,is(dao.count(payment01)));
		
		//등록
		int flag = dao.doInsert(opt01);
		//assertThat(flag,is(dao.count(payment01)));
		LOG.debug("flag:"+flag);

	}
	
	/**
	 * 수정 전-수정 후 비교
	 * @param vsOpt
	 * @param opt01
	 */
	private void checkOpt(Opt vsOpt, Opt opt01) {
		//비교
		assertThat(vsOpt.getOptSeq(), is(opt01.getOptSeq()));
		assertThat(vsOpt.getStoreSeq(), is(opt01.getStoreSeq()));
		assertThat(vsOpt.getMemberId(), is(opt01.getMemberId()));
		assertThat(vsOpt.getTitle(), is(opt01.getTitle()));
		assertThat(vsOpt.getPrice(), is(opt01.getPrice()));
		assertThat(vsOpt.getDiv(), is(opt01.getDiv()));

	}

	@Test
	public void beans() {
		LOG.debug("=======================");
		LOG.debug("=test=");
		LOG.debug("=======================");
		
		LOG.debug("=context="+context);
	}

}
