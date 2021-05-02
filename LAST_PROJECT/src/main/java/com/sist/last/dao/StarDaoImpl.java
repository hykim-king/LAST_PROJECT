package com.sist.last.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.SearchReview;
import com.sist.last.vo.Star;

@Repository
public class StarDaoImpl extends DTO {
	final static Logger LOG = LoggerFactory.getLogger(StarDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.star";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//Spring JdbcTemplate
	JdbcTemplate jdbcTemplate;
	
	public StarDaoImpl() {}
	
	
	/**
	 * 별점 목록 조회
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public List<?> doRetrieve(DTO dto) throws SQLException {

		SearchReview param = (SearchReview) dto;

		String statement = this.NAMESPACE+".doRetrieve";

		LOG.debug("=================");
		LOG.debug("=param="+param);
		LOG.debug("=statement="+statement);
		LOG.debug("=================");
		
		List<Star> list = sqlSessionTemplate.selectList(statement, param);
		
		for(Star vo : list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}

	/**
	 * 별점 등록
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * 별점 삭제
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * 별점 수정
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
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
