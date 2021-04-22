package com.sist.last.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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

import com.sist.last.cmn.SearchOrder;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.HousesDaoImpl;
import com.sist.last.vo.Houses;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	                           	   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestHousesService {

	final Logger LOG = Logger.getLogger(JTestHousesService.class);

	@Autowired
	ApplicationContext context;
	
	@Autowired
	HousesService housesService;
	
	@Autowired
	HousesDaoImpl housesDao;
	
	private List<Houses> houses;
	
	SearchOrder search;

	@Before
	public void setUp() throws Exception {
		LOG.debug("=setUp()=");
		
		houses = Arrays.asList(
					new Houses("4444", "yeonsu44", "4444", "test44", "test44", "test44", "", "yeonsu44", ""),
					new Houses("5555", "yeonsu55", "5555", "test55", "test55", "test55", "", "yeonsu55", ""),
					new Houses("6666", "yeonsu66", "6666", "test66", "test66", "test66", "", "yeonsu66", "")
			);
		
		search = new SearchOrder("20", "tag", 10, 1, "10");

	}

	@After
	public void tearDown() throws Exception {
	}
	
	//검색 :	제목	태그 제목+태그
	//정렬 : 최신순 인기순
	@Test
	public void doRetrieve() throws SQLException { 
		//NVL 처리 (초기화)
		//검색 구분
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv(),""));
		//검색어
		search.setSearchWord(StringUtil.nvl(search.getSearchWord(),""));
		//페이지 size
		if(search.getPageSize()==0) {
			search.setPageSize(10);
		}
		//페이지 num
		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}
		
		//정렬 구분
		search.setOrderDiv(StringUtil.nvl(search.getOrderDiv(), "10"));
		
		LOG.debug("=====================");
		LOG.debug("=param_init="+search);
		LOG.debug("=====================");
		
		List<Houses> list = (List<Houses>) this.housesService.doRetrieve(search);
		
		for(Houses vo : list) {
			LOG.debug(vo.toString());
		}
	}
	
	@Test
	@Ignore
	public void doSelectOne() throws SQLException {
		Houses houses01 = houses.get(0);
		Houses houses02 = houses.get(1);
		Houses houses03 = houses.get(2);

		this.housesService.doSelectOne(houses01);
		this.housesService.doSelectOne(houses02);
		this.housesService.doSelectOne(houses03);
	}
	
	@Test
	@Ignore
	public void doUpdate() throws SQLException {
		int flag = 0;
		
		Houses houses01 = houses.get(0);
		houses01.setImgId(houses01.getImgId()+"_U");
		houses01.setTitle(houses01.getTitle()+"_U");
		houses01.setContents(houses01.getContents()+"_U");
		houses01.setTag(houses01.getTag()+"_U");
		houses01.setModId(houses01.getModId()+"_U");
		LOG.debug(houses01);
		
		flag = this.housesService.doUpdate(houses01);
		assertThat(flag, is(1));
	}
	
	@Test
	@Ignore
	public void doInsert() throws SQLException {
		int flag = 0;
		//입력
		for(Houses houses : houses) {
			flag += this.housesService.doInsert(houses);
		}
		assertThat(flag, is(3));
	}
	
	@Test
	@Ignore
	public void doDelete() throws SQLException {
		for(Houses houses : houses) {
			this.housesService.doDelete(houses);
		}
	}
	
	//---------------------------------------------
	
	@Test
	@Ignore
	public void addAndUpGet() throws SQLException {
		//삭제
		for(Houses houses : houses) {
			this.housesService.doDelete(houses);
		}
		
		int flag = 0;
		//입력
		for(Houses houses : houses) {
			flag += this.housesService.doInsert(houses);
		}
		assertThat(flag, is(3));
				
		//수정 + update
		Houses houses01 = houses.get(0);
		houses01.setImgId(houses01.getImgId()+"_U");
		houses01.setTitle(houses01.getTitle()+"_U");
		houses01.setContents(houses01.getContents()+"_U");
		houses01.setTag(houses01.getTag()+"_U");
		houses01.setModId(houses01.getModId()+"_U");
		LOG.debug(houses01);
		
		flag = this.housesService.doUpdate(houses01);
		assertThat(flag, is(1));

		//단건 조회
		Houses vsHouses01 = (Houses) this.housesService.doSelectOne(houses01);

	}

	@Test
	@Ignore
	public void beans() {
		LOG.debug("=@Test beans()=");
		LOG.debug("context : " + context);
		LOG.debug("housesService : " + housesService);
		LOG.debug("housesDao : " + housesDao);
		assertThat(context, is(notNullValue()));
		assertThat(housesService, is(notNullValue()));
		assertThat(housesDao, is(notNullValue()));
	}

}
