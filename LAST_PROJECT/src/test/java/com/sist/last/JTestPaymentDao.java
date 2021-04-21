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

import com.sist.last.cmn.SearchPay;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.PaymentDao;
import com.sist.last.vo.Payment;

//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //bean들을 다 올려놓음
public class JTestPaymentDao {

	final static Logger LOG = LoggerFactory.getLogger(JTestPaymentDao.class);
	
	@Autowired //이런 타입이 있는지 확인하고 주입
	ApplicationContext context; //테스트 오브젝트가 만들어 지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	
	@Autowired //이런 타입이 있는지 확인하고 주입
	private PaymentDao dao; //서로 다른 클래스 간에 공유가 됨
	
	Payment payment01;
	Payment payment02;
	Payment payment03;
	
	SearchPay search01;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@Before=");
		LOG.debug("=======================");
		
		LOG.debug("=context="+context);
		 
		payment01 = new Payment("20210414", "1111", "solshine", "캔들워머", "S size", "화이트", 1, 13200, 2500, 1, "", "", "");
		payment02 = new Payment("20210415", "1111", "solshine", "캔들워머", "M size", "블랙", 1, 13200, 2500, 1, "", "", "");
		payment03 = new Payment("20210416", StringUtil.getPK("yyyyMMddHH24mmss"), "sunny", "소파", "L size", "브라운", 1, 13200, 2500, 1, "", "", "");
		
		search01 = new SearchPay("solshine",1, 2);
		
	}
	
	@Test
	@Ignore
	public void doUpdate() throws SQLException {
		//1. 기존데이터 삭제
		//2. 신규데이터 입력
		//3. 데이터 수정 + update
		//* 비교
		
		//1.
		dao.doDelete(payment01);
		dao.doDelete(payment02);
		dao.doDelete(payment03);
		
		//2.
		int flag = dao.doInsert(payment01);
		assertThat(flag, is(1));
		
		flag += dao.doInsert(payment02);
		assertThat(flag, is(2));
		
		payment01.setTitle(payment01.getTitle()+"_U");
		payment01.setOptone(payment01.getOptone()+"_U");
		payment01.setOpttwo(payment01.getOpttwo()+"_U");
		payment01.setQuantity(5);
		LOG.debug("user01:"+payment01);
		
		flag = dao.doUpdate(payment01);
		assertThat(flag, is(1));
		
		//3.1. 수정데이터 조회
		Payment changePayment = (Payment) dao.doSelectOne(payment01);
		LOG.debug("changePayment:"+changePayment);
		checkUser(changePayment, payment01);
		
		//3.2. update 하지 않은 데이터 비교
		Payment unChangePayment = (Payment) dao.doSelectOne(payment02);
		checkUser(unChangePayment, payment02);	
	}
	
	@Test
	public void doRetrieve() throws ClassNotFoundException, SQLException {
		//데이터 3건 삭제
		dao.doDelete(payment01);
		dao.doDelete(payment02);
		dao.doDelete(payment03);

		//데이터 추가
		int flag = 0;
		flag = dao.doInsert(payment01);
		flag += dao.doInsert(payment02);
		flag += dao.doInsert(payment03);
		assertThat(flag,is(3));

		//데이터 전체 조회
		//검색용도
		Payment payment09 = new Payment();
		payment09.setMemberId("solshine");
		List<Payment> list = (List<Payment>) dao.doRetrieve(search01);
		assertThat(list.size(),is(1));
		for(Payment vo :list) {
			LOG.debug("vo:"+vo);
		}
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@tearDown=");
		LOG.debug("=======================");
	}
	
	@Test
	@Ignore
	public void addAndGet() throws ClassNotFoundException, SQLException {
		LOG.debug("=======================");
		LOG.debug("=@addAndGet=");
		LOG.debug("=======================");
		
		dao.doDelete(payment01);
		dao.doDelete(payment02);
		
		//등록
		int flag = dao.doInsert(payment01);
		flag += dao.doInsert(payment02);
		assertThat(flag, is(2));
		//assertThat(flag,is(dao.count(payment01)));
		LOG.debug("flag:"+flag);
		
	}
	
	private void checkUser(Payment vsPayment, Payment payment01) {
		//비교
		assertThat(vsPayment.getPaySeq(), is(payment01.getPaySeq()));
		assertThat(vsPayment.getStoreSeq(), is(payment01.getStoreSeq()));
		assertThat(vsPayment.getMemberId(), is(payment01.getMemberId()));
		assertThat(vsPayment.getTitle(), is(payment01.getTitle()));
		assertThat(vsPayment.getOptone(), is(payment01.getOptone()));
		assertThat(vsPayment.getOpttwo(), is(payment01.getOpttwo()));
		assertThat(vsPayment.getQuantity(), is(payment01.getQuantity()));
		assertThat(vsPayment.getPrice(), is(payment01.getPrice()));
		
	}

	@Test
	public void beans() {
		LOG.debug("=======================");
		LOG.debug("=test=");
		LOG.debug("=======================");
		
		LOG.debug("=context="+context);
	}

}
