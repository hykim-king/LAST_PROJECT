package com.sist.last;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.SearchOrder;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.ProductDaoImpl;
import com.sist.last.vo.Product;


//메소드 수행 순서: method ASCENDING ex)a~z
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) //스프링 테스트 컨텍스트 프레임의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})//bean들을 다 올려놓음
public class JTestProductDao {
	
	final static Logger LOG = LoggerFactory.getLogger(JTestProductDao.class);
	
	@Autowired
	ApplicationContext  context;//테스트 오브젝트가 만들어 지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	
	@Autowired
	private ProductDaoImpl dao;
		
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
		LOG.debug("=================================");
		LOG.debug("=@Before=setUp()=");
		LOG.debug("=================================");
		LOG.debug("=context:"+context);

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
		//1. 기존데이터 삭제
		//2. 신규데이터 입력
		//3. 데이터 수정 + update
		//*  비교
		
		//1.기존데이터 삭제
		dao.doDelete(product01);
		dao.doDelete(product02);
		dao.doDelete(product03);
		
		//2.새 데이터 입력
		int flag = dao.doInsert(product01);
		assertThat(flag, is(1));
		
		flag += dao.doInsert(product02);
		assertThat(flag, is(2));	
		
		flag += dao.doInsert(product03);
		assertThat(flag, is(3));
		
		//3.데이터 수정 + update
		product03.setImgId(product03.getImgId()+"_U");
		product03.setTitle(product03.getTitle()+"_U");
		product03.setContents(product03.getContents()+"_U");
		product03.setCompany(product03.getCompany()+"_U");
		product03.setCategory(product03.getCategory()+"_U");
		product03.setTag(product03.getTag()+"_U");
		product03.setPrice(product03.getPrice()+99);
		product03.setQuantity(product03.getQuantity()+99);
		product03.setRefund(product03.getRefund()+"_U");
		product03.setModId(product03.getModId()+"_U");
		product03.setStoreSeq(product03.getStoreSeq());
		LOG.debug("product03:"+product03);
		
		flag = dao.doUpdate(product03);
		assertThat(flag, is(1));
		
	}
	

	@Test
	@Ignore
	public void doDelete() throws SQLException, ClassNotFoundException {
		dao.doDelete(product01);
		dao.doDelete(product02);
		dao.doDelete(product03);
		dao.doDelete(product04);
		dao.doDelete(product05);
		dao.doDelete(product06);
		
	}
	

	@Test
	//@Ignore
	public void doInsert() throws SQLException, ClassNotFoundException {
		
		product01.setStoreSeq(StringUtil.getPK(""));
		dao.doInsert(product01);

//		dao.doInsert(product02);
//		dao.doInsert(product03);
//		dao.doInsert(product04);
//		dao.doInsert(product05);
//		dao.doInsert(product06);
		
		
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws SQLException {
		dao.doSelectOne(product01);
		dao.doSelectOne(product02);
		dao.doSelectOne(product03);
	}
	
	@Test
	@Ignore
	public void doRetrieve() throws SQLException {
		dao.doRetrieve(search01);
		dao.doRetrieve(search02);
		dao.doRetrieve(search03);
		dao.doRetrieve(search04);
		dao.doRetrieve(search05);
		dao.doRetrieve(search06);
		dao.doRetrieve(search07);
		dao.doRetrieve(search08);
	}
	
	
	

	
	private void checkProduct(Product vsProduct, Product product01) {
		//비교
		assertThat(vsProduct.getStoreSeq(), is(product01.getStoreSeq()));
		assertThat(vsProduct.getMemberId(), is(product01.getMemberId()));
		assertThat(vsProduct.getImgId(),    is(product01.getImgId()));	
		assertThat(vsProduct.getTitle(), 	is(product01.getTitle()));
		assertThat(vsProduct.getContents(), is(product01.getContents()));
		assertThat(vsProduct.getCompany(),	is(product01.getCompany()));
		assertThat(vsProduct.getCategory(),	is(product01.getCategory()));
		assertThat(vsProduct.getTag(),		is(product01.getTag()));
		assertThat(vsProduct.getPrice(),	is(product01.getPrice()));
		assertThat(vsProduct.getQuantity(),	is(product01.getQuantity()));
		assertThat(vsProduct.getRefund(),	is(product01.getRefund()));
	//	assertThat(vsProduct.getRegDt(),	is(product01.getRegDt()));
		assertThat(vsProduct.getModId(),	is(product01.getModId()));
	//	assertThat(vsProduct.getModDt(),	is(product01.getModDt()));
	}
	
	@After
	public void tearDown() throws Exception {
		LOG.debug("=======================");
		LOG.debug("=@After=");
		LOG.debug("=======================");		
	}

	@Test
	@Ignore
	public void test() {
		LOG.debug("=======================");
		LOG.debug("=@Test=");
		LOG.debug("=======================");
	}

}
