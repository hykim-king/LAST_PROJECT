/**
 * 
 */
package com.sist.last;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

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

/**
 * @author yeonsu
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	                           	   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class JTestHousesDao {

	final static Logger LOG = Logger.getLogger(JTestHousesDao.class);

	@Autowired
	ApplicationContext context; 
	
	@Autowired
	private HousesDaoImpl dao;
	
	Houses houses01;
	Houses houses02;
	Houses houses03;
		
	SearchOrder search;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("================");
		LOG.debug("=@Before setUp()=");
		LOG.debug("================");

		houses01 = new Houses("1111", "yeonsu11", "1111", "test11", "test11", "test11", "", "yeonsu11", "");
		houses02 = new Houses("2222", "yeonsu22", "2222", "test22", "test22", "test22", "", "yeonsu22", "");
		houses03 = new Houses("3333", "yeonsu33", "3333", "test33", "test33", "test33", "", "yeonsu33", "");
		
		                         //검색구분, 검색어, 페이지size, 페이지num, 정렬구분
		search = new SearchOrder("10", "t", 10, 1, "20");
	}
	
	@After
	public void tearDown() throws Exception {
		LOG.debug("================");
		LOG.debug("=@After tearDown()=");
		LOG.debug("================");
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
		
		List<Houses> list = (List<Houses>) dao.doRetrieve(search);
		
		for(Houses vo : list) {
			LOG.debug(vo.toString());
		}
	}

	@Test
	@Ignore
	public void doUpdate() throws SQLException { 
		//삭제
		dao.doDelete(houses01); 
		dao.doDelete(houses02); 
		dao.doDelete(houses03); 

		// 등록
		int flag = dao.doInsert(houses01); 
		assertThat(flag, is(1)); 
		
		flag += dao.doInsert(houses02); 
		assertThat(flag, is(2)); 
		
		flag += dao.doInsert(houses03);
		assertThat(flag, is(3));  
		
		//수정 + update
		houses01.setImgId(houses01.getImgId()+"_U");
		houses01.setTitle(houses01.getTitle()+"_U");
		houses01.setContents(houses01.getContents()+"_U");
		houses01.setTag(houses01.getTag()+"_U");
		houses01.setModId(houses01.getModId()+"_U");
		LOG.debug(houses01);
		
		flag = dao.doUpdate(houses01);
		assertThat(flag, is(1));

		//비교
		Houses changeHouses = (Houses) dao.doSelectOne(houses01);
		LOG.debug("changeHouses : "+changeHouses);
		checkHouses(changeHouses, houses01);
	}
	
	@Test
	@Ignore
	public void addAndGet() throws ClassNotFoundException, SQLException {
		LOG.debug("================");
		LOG.debug("=@Test addAndGet=");
		LOG.debug("================");
		
		//검색 용도
		Houses houses09 = new Houses();
		houses09.setMemberId("yeonsu");

		//삭제
		dao.doDelete(houses01); 
		dao.doDelete(houses02); 
		dao.doDelete(houses03); 

		// 등록
		int flag = dao.doInsert(houses01); 
		assertThat(flag, is(1)); 
		
		flag += dao.doInsert(houses02); 
		assertThat(flag, is(2)); 
		
		flag += dao.doInsert(houses03);
		assertThat(flag, is(3));  
		
		//조회
		Houses vsHouses01 = (Houses) dao.doSelectOne(houses01); 
		Houses vsHouses02 = (Houses) dao.doSelectOne(houses02); 
		Houses vsHouses03 = (Houses) dao.doSelectOne(houses03); 
		
		checkHouses(vsHouses01, houses01);
		checkHouses(vsHouses02, houses02);
		checkHouses(vsHouses03, houses03);
	}
	
	private void checkHouses(Houses vsHouses, Houses houses) {
		//비교
		assertThat(vsHouses.getHousesSeq(), is(houses.getHousesSeq()));
		assertThat(vsHouses.getMemberId(), is(houses.getMemberId()));
		assertThat(vsHouses.getImgId(), is(houses.getImgId()));
		assertThat(vsHouses.getTitle(), is(houses.getTitle()));
		assertThat(vsHouses.getContents(), is(houses.getContents()));
		assertThat(vsHouses.getTag(), is(houses.getTag()));
		assertThat(vsHouses.getModId(), is(houses.getModId()));
	}

	@Test
	@Ignore
	public void beans() {
		LOG.debug("=@Test beans()=");
		LOG.debug("context : " + context);
		LOG.debug("dao : " + dao);
		assertThat(context, is(notNullValue()));
		assertThat(dao, is(notNullValue()));
	}

}
