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

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.Search;
import com.sist.last.vo.Scrap;

public class ScrapDaoImpl implements ScrapDao {
	final static Logger LOG = LoggerFactory.getLogger(StarDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.scrap";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//Spring JdbcTemplate
	JdbcTemplate jdbcTemplate;
	
	DataSource dataSource;
	
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
	
	//setter를 통한 주입
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}

		@Override
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

	@Override
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

	@Override
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

	@Override
	public List<Scrap> doRetrieve(DTO dto) throws SQLException {
		Search  param = (Search) dto;
		
		StringBuffer sb=new StringBuffer(1000);
		sb.append(" SELECT A.*,B.*                                                                                      \n");
		sb.append(" FROM(                                                                                               \n");
		sb.append("     SELECT t2.rnum,                                                                                 \n");
		sb.append("            t2.scrap_seq,                                                                                 \n");
		sb.append("            t2.houses_seq,                                                                                 \n");
		sb.append("            t2.member_id,                                                                                 \n");
		sb.append("       CASE WHEN TO_CHAR(SYSDATE,'YYYY/MM/DD')=TO_CHAR(t2.reg_dt,'YYYY/MM/DD')             \n");
		sb.append("       THEN TO_CHAR(t2.reg_dt,'HH24:MI')                                                   \n");
		sb.append(" 	   ELSE TO_CHAR(t2.reg_dt,'YYYY/MM/DD')                                               \n");
		sb.append(" 	   END reg_dt,                                                                       \n");
		sb.append(" 	   t2.mod_id,                                                                         \n");
		sb.append("       CASE WHEN TO_CHAR(SYSDATE,'YYYY/MM/DD')=TO_CHAR(t2.mod_dt,'YYYY/MM/DD')             \n");
		sb.append(" 	   THEN TO_CHAR(t2.mod_dt,'HH24:MI')                                                  \n");
		sb.append(" 	   ELSE TO_CHAR(t2.mod_dt,'YYYY/MM/DD')                                               \n");
		sb.append(" 	   END mod_dt                                                                      \n");
		sb.append("     FROM(                                                                                           \n");
		sb.append("         SELECT ROWNUM rnum,t1.*                                                                     \n");
		sb.append("         FROM (                                                                                      \n");
		sb.append("             SELECT *                                                                                \n");
		sb.append("             FROM scrap                                                                          \n");
		sb.append("             ORDER BY reg_dt desc                                                                    \n");
		sb.append("         )t1                                                                                         \n");
		sb.append("     )t2                                                                                             \n");
		sb.append("     WHERE rnum BETWEEN (? * (?-1) + 1) AND (? * (?-1) + ?)                                          \n");
		sb.append(" )A CROSS JOIN                                                                                       \n");
		sb.append("     (SELECT COUNT(*) total_cnt                                                                      \n");
		sb.append("      FROM scrap                                                                                 \n");
		sb.append("     )B                                                                                              \n");
		
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param=\n"+param);
		
		//query에 파라메터 set
		List<Object>   listArg = new ArrayList<Object>();
		
			//페이징 정보
			listArg.add(param.getPageSize());
			listArg.add(param.getPageNum());
			listArg.add(param.getPageSize());
			listArg.add(param.getPageNum());
			listArg.add(param.getPageSize());		
			
//		LOG.debug("1=listArg="+listArg.toArray());
//		for(Object ob:listArg) {
//			LOG.debug("=ob="+ob.toString());
//		}
		List<Scrap> list = jdbcTemplate.query(sb.toString(), listArg.toArray(), row);
		LOG.debug("2=param=");
		return list;
	}

}
