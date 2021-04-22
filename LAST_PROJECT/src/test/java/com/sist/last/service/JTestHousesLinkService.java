package com.sist.last.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

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

import com.sist.last.dao.HousesLinkDao;
import com.sist.last.dao.HousesLinkDaoImpl;
import com.sist.last.vo.HousesLink;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =  {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestHousesLinkService {
	
	final  Logger LOG  = Logger.getLogger(this.getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	HousesLinkService linkService;
	
	@Autowired
	HousesLinkDaoImpl linkDao;
	
	@Autowired
	DataSource dataSource;
	
	//데이터 3건 전역변수
	private List<HousesLink> links;
	
	HousesLink link01;
	HousesLink link02;

	@Before
	public void setUp() throws Exception {
		LOG.debug("@Before-setUp()");
		
		link01 = new HousesLink("NANA_133_1", "1111", "nana222", "https://ohou.se/productions/585078", 1, "", "nana", "");
		link02 = new HousesLink("NANA_133_2", "1111", "nana211", "https://ohou.se/productions/585079", 2, "", "haha", "");
		
		links = Arrays.asList(
				 new HousesLink("HAHA_133_1", "2222", "haha333", "https://ohou.se/productions/585011", 1, "", "lala", "") 
				,new HousesLink("HAHA_133_2", "2222", "haha113", "https://ohou.se/productions/585022", 2, "", "haho", "") 
				,new HousesLink("HAHA_133_3", "2222", "haha666", "https://ohou.se/productions/585044", 3, "", "hoha", "") 
				);
	}//--setUp

	@After
	public void tearDown() throws Exception {
		LOG.debug("@After-tearDown()");
	}
	
	@Test
	public void doRetrieve() throws SQLException {
		LOG.debug("=================");
		LOG.debug("==doRetrieve()==");
		LOG.debug("=================");
		
		//데이터 삭제
		for(HousesLink link:links) {  
			linkDao.doDelete(link);   
		}     
		
		//데이터 건수
		int flag = 0;
		
        //데이터 입력
		for(HousesLink link:links) {  
			flag += linkDao.doInsert(link); 
		} 
		
		//데이터 입력건수확인
		assertThat(flag, is(3));
		
		//검색용도 
		links.get(0).setHousesSeq("2222");
		this.linkService.doRetrieve(links.get(0));
	
		
		
	}//--doRetrieve
	
	@Test
	@Ignore
	public void doUpdate() throws SQLException {
		LOG.debug("=================");
		LOG.debug("==doUpdate()==");
		LOG.debug("=================");
		
		//데이터 삭제
		for(HousesLink link:links) {  
			linkDao.doDelete(link);   
		}     
		
		//데이터 건수
		int flag = 0;
		
        //데이터 입력
		for(HousesLink link:links) {  
			flag += linkDao.doInsert(link); 
		} 
		
		//데이터 입력건수확인
		assertThat(flag, is(3));
		
		//데이터 업데이트
		links.get(0).setLink(links.get(0).getLink()+"_**");
		links.get(0).setDiv(links.get(0).getDiv()+6);
		links.get(0).setModId(links.get(0).getModId()+"_**");
		
		int flagUp = this.linkService.doUpdate(links.get(0));
		
		//데이터 업데이트 건수확인
		assertThat(flagUp, is(1));	
		
		//업데이트 링크 단건조회
		HousesLink changeLink = (HousesLink) this.linkService.doSelectOne(links.get(0));
		
		LOG.debug("=================");
		LOG.debug("changeLink:"+changeLink);
		LOG.debug("=================");
		
	}//--doUpdate
	
	
	@Test
	@Ignore
	//==addAndGet
	public void doSelectOne() throws SQLException {
		LOG.debug("=================");
		LOG.debug("==doSelectOne()==");
		LOG.debug("=================");
		
		//데이터 삭제
		for(HousesLink link:links) {  
			linkDao.doDelete(link);   
		}     
		
		//데이터 건수
		int flag = 0;
		
        //데이터 입력
		for(HousesLink link:links) {  
			flag += linkDao.doInsert(link); 
		} 
		
		//데이터 건수확인
		assertThat(flag, is(3));
		
		//데이터 단건조회
		this.linkService.doSelectOne(links.get(0));

			
	}//--doSelectOne
	

	
	@Test
	@Ignore
	public void beans() {
		LOG.debug("context:"+context);
		LOG.debug("linkService:"+linkService);
		LOG.debug("linkDao:"+linkDao);
		LOG.debug("dataSource:"+dataSource);
		
		assertThat(this.context, is(notNullValue()));
		assertThat(this.linkService, is(notNullValue()));
		assertThat(this.linkDao, is(notNullValue()));
		assertThat(this.dataSource, is(notNullValue()));
		
		
	}//--beans

}//--class
