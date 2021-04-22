package com.sist.last.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.vo.HousesLink;

@Repository
public class HousesLinkDaoImpl {
	
	final static Logger LOG  = LoggerFactory.getLogger(HousesLinkDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.houseslink";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public HousesLinkDaoImpl() { }
	


	/**
	 * 집들이 링크 업데이트
	 */

	public int doUpdate(DTO dto) throws SQLException {
		int flag  = 0;
		HousesLink link = (HousesLink) dto;
		
		//mybatis sql : NAMESPACE+.id;
		String statement = this.NAMESPACE+".doUpdate";
				
		LOG.debug("============================");
		LOG.debug("--link--"+link);
		LOG.debug("--statement--"+statement);
		LOG.debug("============================");
		
		flag = this.sqlSessionTemplate.update(statement, link);
		
		return flag;
	}//--doUpdate

	
	/**
	 * 집들이 링크 삭제
	 */

	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		HousesLink link = (HousesLink) dto;
		
		//mybatis sql : NAMESPACE+.id;
		String statement = this.NAMESPACE+".doDelete";
		
		LOG.debug("============================");
		LOG.debug("--link--"+link);
		LOG.debug("--statement--"+statement);
		LOG.debug("============================");
		
		
		flag = this.sqlSessionTemplate.delete(statement, link);
		//mybatis 오류확인
		//1.NAMESPACE+.id 확인 :(.)조심!!!!!!
		//2.query 오류확인
		//3.resultType 확인
		
		return flag;
	}//--doDelete
	
	
	/**
	 * 집들이 링크 삽입
	 */

	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		HousesLink link = (HousesLink) dto;
		
		
		//mybatis sql : NAMESPACE+.id;
		String statement = this.NAMESPACE+".doInsert";
		
		LOG.debug("============================");
		LOG.debug("--link--"+link);
		LOG.debug("--statement--"+statement);
		LOG.debug("============================");
		
		
		flag = this.sqlSessionTemplate.insert(statement, link);
		
		return flag;
		
	}//--doInsert

	
	/**
	 * 집들이 게시물 별 링크조회(단건조회)
	 */	

	public DTO doSelectOne(DTO dto) throws SQLException {
		
		//return data
		HousesLink inLink  = (HousesLink) dto;
		HousesLink outLink  = null;
		
		//mybatis sql : NAMESPACE+.id;
		String statement = this.NAMESPACE+".doSelectOne";
				
		LOG.debug("============================");
		LOG.debug("--link--"+inLink);
		LOG.debug("--statement--"+statement);
		LOG.debug("============================");
				
				
			
		outLink = this.sqlSessionTemplate.selectOne(statement, inLink);
		LOG.debug("==================");
		LOG.debug("--outLink--"+outLink);
		LOG.debug("==================");
		
		
		//예외:데이터가 없으면 
		if(null == outLink) {
			LOG.debug("--null outLink--"+outLink);
			LOG.debug("==================");
			throw new EmptyResultDataAccessException(1);
		}
		
		return outLink;
	}//--doSelectOne

	
	/**링크등록건수 구하기**/

	public int count(HousesLink link) throws ClassNotFoundException, SQLException {
		int cnt = 0;
		
		//mybatis sql : NAMESPACE+.id;
		String statement = this.NAMESPACE+".count";
				
		LOG.debug("============================");
		LOG.debug("--link--"+link);
		LOG.debug("--statement--"+statement);
		LOG.debug("============================");
		
		cnt = this.sqlSessionTemplate.selectOne(statement, link);
		LOG.debug("============");
		LOG.debug("--cnt--:"+cnt);
		LOG.debug("=============");
		
		return cnt;
	}//--count


	public List<HousesLink> doRetrieve(DTO dto) throws SQLException {
		
		HousesLink list = (HousesLink) dto;
		
		//mybatis sql : NAMESPACE+.id;
		String statement = this.NAMESPACE+".doRetrieve";
						
		LOG.debug("============================");
		LOG.debug("--link--"+list);
		LOG.debug("--statement--"+statement);
		LOG.debug("============================");
		
		
		List<HousesLink> outList = this.sqlSessionTemplate.selectList(statement, list);
		
		for(HousesLink vo:outList) {
			LOG.debug(vo.toString());
		}
		
		return outList;

	}//--doRetrieve
		
		


}//--class
