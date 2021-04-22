package com.sist.last.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sist.last.cmn.DTO;
import com.sist.last.vo.Star;

public class StarDaoImpl implements StarDao {
	final static Logger LOG = LoggerFactory.getLogger(StarDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.star";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//Spring JdbcTemplate
	JdbcTemplate jdbcTemplate;
	
	DataSource dataSource;
	
	public StarDaoImpl() {}
	
	//setter를 통한 주입
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Star user = (Star) dto;
		
		//mybatis sql: NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doInsert";
		LOG.debug("=============================");
		LOG.debug("=user="+user);
		LOG.debug("=statement="+statement);
		LOG.debug("=============================");	
		
		flag = sqlSessionTemplate.insert(statement, user);
		
		return flag;
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Star   user = (Star)dto;
		
		//mybatis sql: NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doDelete";
		
		LOG.debug("=============================");
		LOG.debug("=user="+user);
		LOG.debug("=statement="+statement);
		LOG.debug("=============================");		
		
		//mybatis오류 확인
		//1. NAMESPACE+.+id확인
		//2. query오류 확인
		//3. resultType확인
		flag = this.sqlSessionTemplate.delete(statement, user);
		return flag;
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Star star = (Star) dto;

		String statement = this.NAMESPACE+".doUpdate";
			
		
		Object[]  args = {
		          star.getStarScore(),
		          star.getStarSeq()};
		LOG.debug("=star=\n"+star);
		flag = sqlSessionTemplate.update(statement, star);

		return flag;
	}

}
