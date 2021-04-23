package com.sist.last;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.HousesLinkDao;
import com.sist.last.dao.HousesLinkDaoImpl;
import com.sist.last.vo.HousesLink;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestHousesLinkDao {
	
	final static Logger LOG = LoggerFactory.getLogger(JTestHousesLinkDao.class);
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	private HousesLinkDaoImpl linkDao;
	
	HousesLink link01;
	HousesLink link02;
	HousesLink link03;
//	HousesLink link04;
//	HousesLink link05;
//	HousesLink link06;

	@Before
	public void setUp() throws Exception {
		LOG.debug("@Before-setUp()");
		
		
		link01 = new HousesLink("NANA_133_1", "1111", "nana222", "https://ohou.se/productions/585078", 1, "", "nana", "");
		link02 = new HousesLink("NANA_133_2", "1111", "nana211", "https://ohou.se/productions/585079", 2, "", "haha", "");
		link03 = new HousesLink("NANA_133_3", "1111", "nana233", "https://ohou.se/productions/585077", 3, "", "hoho", "");
	
		
//		link04 = new HousesLink("HAHA_133_1", "2222", "haha333", "https://ohou.se/productions/585011", 1, "", "lala", "");
//		link05 = new HousesLink("HAHA_133_2", "2222", "haha113", "https://ohou.se/productions/585022", 2, "", "haho", "");
//		link06 = new HousesLink("HAHA_133_3", "2222", "haha666", "https://ohou.se/productions/585044", 3, "", "hoha", "");
	
	}//--setUp

	@After
	public void tearDown() throws Exception {
		LOG.debug("@After-tearDown()");
	}
	
	

	@Test
	//@Ignore
	public void doRetrieve() throws SQLException {
		LOG.debug("==============");
		LOG.debug("==doRetrieve()==");
		LOG.debug("==============");
		
		//삭제
		linkDao.doDelete(link01);
		linkDao.doDelete(link02);
		linkDao.doDelete(link03);
		
		//등록
		int flag = linkDao.doInsert(link01);
		flag += linkDao.doInsert(link02);
		flag += linkDao.doInsert(link03);
		assertThat(flag, is(3));
		
		//링크 전체조회
		//검색용도
		HousesLink link11 = new HousesLink();
		link11.setHousesSeq("1111");

		
		List<HousesLink> list  = linkDao.doRetrieve(link11);
		assertThat(list.size(), is(3));

	}//--doRetrieve
	
	
	@Test
	//@Ignore
	public void doUpdate() throws SQLException {
		LOG.debug("==============");
		LOG.debug("==doUpdate()==");
		LOG.debug("==============");
		
		//삭제
		linkDao.doDelete(link01);
		linkDao.doDelete(link02);
		linkDao.doDelete(link03);
		
		//등록
		int flag = linkDao.doInsert(link01);
		flag += linkDao.doInsert(link02);
		flag += linkDao.doInsert(link03);
		assertThat(flag, is(3));

		//데이터 수정 +update
		link01.setLink(link01.getLink()+"_**");
		link01.setDiv(link01.getDiv()+7);
		link01.setModId(link01.getModId()+"_**");
		LOG.debug("===================");
		LOG.debug("link01:"+link01);
		LOG.debug("===================");
		
		flag = linkDao.doUpdate(link01);
		assertThat(flag, is(1));
		
		//수정된 데이터 조회
		HousesLink changeLink = (HousesLink) linkDao.doSelectOne(link01);
		LOG.debug("===================");
		LOG.debug("changeLink:"+changeLink);
		LOG.debug("===================");
		
		//비교
		checkLink(changeLink, link01);
		
		//update 하지 않은 데이터 비교
		HousesLink unChangeLink  = (HousesLink) linkDao.doSelectOne(link02);
		checkLink(unChangeLink, link02);
		
	}//--doUpdate
	

	@Test
	//@Ignore
	public void AddAndGet() throws ClassNotFoundException, SQLException {
		LOG.debug("==============");
		LOG.debug("==AddAndGet()==");
		LOG.debug("==============");
		
		//검색용도
		HousesLink link99 = new HousesLink();
		link99.setLinkSeq("NANA_133");
	
		
		//삭제
		linkDao.doDelete(link01);
		linkDao.doDelete(link02);
		linkDao.doDelete(link03);
		
		//등록
		int flag = linkDao.doInsert(link01);
		assertThat(flag, is(linkDao.count(link99)));
		flag += linkDao.doInsert(link02);
		assertThat(flag, is(linkDao.count(link99)));
		flag += linkDao.doInsert(link03);
		assertThat(flag, is(linkDao.count(link99)));
		
		
		//단건조회
		HousesLink vsLink01 = (HousesLink) linkDao.doSelectOne(link01);
		HousesLink vsLink02 = (HousesLink) linkDao.doSelectOne(link02);
		HousesLink vsLink03 = (HousesLink) linkDao.doSelectOne(link03);
		
		//비교
		checkLink(vsLink01, link01);
		checkLink(vsLink02, link02);
		checkLink(vsLink03, link03);

	}//--AddAndGet
	
	
	private void checkLink(HousesLink vsLink, HousesLink link01) {
		//비교
		assertThat(vsLink.getLinkSeq(), is(link01.getLinkSeq()));
		assertThat(vsLink.getHousesSeq(), is(link01.getHousesSeq()));
		assertThat(vsLink.getMemberId(), is(link01.getMemberId()));
		assertThat(vsLink.getLink(), is(link01.getLink()));
		assertThat(vsLink.getDiv(), is(link01.getDiv()));
		
	}//--checkLink
	

	
	@Test
	@Ignore
	public void beans() {
		LOG.debug("context:"+context);
		LOG.debug("linkDao:"+linkDao);
		
		assertThat(context, is(notNullValue()));
		assertThat(linkDao, is(notNullValue()));
		
	}

}//--class
