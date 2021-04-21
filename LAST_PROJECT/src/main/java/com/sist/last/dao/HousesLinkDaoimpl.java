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
import com.sist.last.cmn.StringUtil;
import com.sist.last.vo.HousesLink;


public class HousesLinkDaoimpl implements HousesLinkDao {
	
	final static Logger LOG  = LoggerFactory.getLogger(HousesLinkDaoimpl.class);
	
	final String NAMESPACE = "com.sist.last";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	DataSource dataSource;//spring에 있는 data connection 사용
	
	JdbcTemplate jdbcTemplate;//DAO객체에서 DB와 연동하기 위해 SQL 연산들을 수행 할 수 있도록 도와주는 기술
	
	public HousesLinkDaoimpl() { }
	
	//setter DI
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	//RowMapper---------------------------
	RowMapper<HousesLink> row  = new RowMapper<HousesLink>() {
		
		@Override
		public HousesLink mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			HousesLink linkVO = new HousesLink();

			linkVO.setLinkSeq(rs.getString("link_seq"));
			linkVO.setHousesSeq(rs.getString("houses_seq"));
			linkVO.setMemberId(rs.getString("member_id"));
			linkVO.setLink(rs.getString("link"));
			linkVO.setDiv(rs.getInt("div"));
			linkVO.setRegDt(rs.getString("reg_dt"));
			
			linkVO.setModId(rs.getString("mod_id"));
			linkVO.setModDt(rs.getString("mod_dt"));
			
			LOG.debug("=rowNum="+rowNum);
			
			return linkVO;
		}
		
	};
	//RowMapper---------------------------
	
	
	

	/**
	 * 집들이 링크 업데이트
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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

	@Override
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
