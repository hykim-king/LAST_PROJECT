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
import com.sist.last.cmn.Search;
import com.sist.last.vo.Scrap;

@Repository
public class ScrapDaoImpl extends DTO {
	final static Logger LOG = LoggerFactory.getLogger(StarDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.scrap";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//Spring JdbcTemplate
	JdbcTemplate jdbcTemplate;
	
	
	RowMapper<Scrap> row = new RowMapper<Scrap>() {
		@Override
		public Scrap mapRow(ResultSet rs, int rowNum) throws SQLException {
			Scrap scrapVO = new Scrap();
			
			scrapVO.setScrapSeq(rs.getString("scrap_seq"));
			scrapVO.setHousesSeq(rs.getString("houses_seq"));
			scrapVO.setMemberId(rs.getString("member_id"));
			scrapVO.setRegDt(rs.getString("reg_dt"));
			scrapVO.setModId(rs.getString("mod_id"));
			scrapVO.setModDt(rs.getString("mod_dt"));
			
			return scrapVO;
		}
		
	};
	
	public ScrapDaoImpl() {}
	
	public int scrapCheck(DTO dto) {
		int flag = 0;
		
		Scrap scrap = (Scrap) dto;
		String statement = this.NAMESPACE+".idCheck";
		LOG.debug("=======================================");
		LOG.debug("====user=====" + scrap);
		LOG.debug("====statement=====" + statement);
		LOG.debug("=======================================");
		
		flag=this.sqlSessionTemplate.selectOne(statement, scrap);
		LOG.debug("flag"+flag);
		return flag;
	}

		public DTO doSelectOne(DTO dto) throws SQLException {
			Scrap inVO = (Scrap) dto;
			Scrap outVO = null;
			
			String statement = this.NAMESPACE+".doSelectOne";
			LOG.debug("==================================");
			LOG.debug("=inVO="+inVO);
			LOG.debug("=statement="+statement);
			LOG.debug("==================================");
			
			outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
			
			LOG.debug("==================================");
			LOG.debug("=outVO="+outVO);
			LOG.debug("==================================");
			
			if(null == outVO) {
				LOG.debug("==================================");
				LOG.debug("=null outVO="+outVO);
				LOG.debug("==================================");
				throw new EmptyResultDataAccessException(1);
			}
			
			return outVO;
		}


	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Scrap user = (Scrap) dto;
		
		//mybatis sql: NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doInsert";
		LOG.debug("=============================");
		LOG.debug("=user="+user);
		LOG.debug("=statement="+statement);
		LOG.debug("=============================");	
		
		flag = sqlSessionTemplate.insert(statement, user);
		
		return flag;
	}


	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Scrap   user = (Scrap)dto;
		
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


	public List<?> doRetrieve(DTO dto) throws SQLException {

		Search param = (Search) dto;
		
		LOG.debug("1=param====="+param);

		String statement = this.NAMESPACE + ".doRetrieve";
		
		LOG.debug("====statement=====" + statement);
		LOG.debug("=======================================");
		
		List<Scrap> list = sqlSessionTemplate.selectList(statement, param);
		
		for (Scrap vo : list) {
			LOG.debug(vo.toString());
		}

		return list;
		
		
		
	}

}
