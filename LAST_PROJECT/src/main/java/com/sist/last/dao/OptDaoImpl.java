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
import com.sist.last.vo.Opt;

@Repository
public class OptDaoImpl {
	final static Logger LOG = LoggerFactory.getLogger(OptDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last"; //com.sist.ehr.member.doDelete -> 점 붙어야 함
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public OptDaoImpl() {}
	
	/**
	 * 옵션 수정
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Opt opt = (Opt) dto;
		
		String statement = this.NAMESPACE+".doUpdate99";
		LOG.debug("==================================");
		LOG.debug("=opt="+opt);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		//update대신 insert 넣어도 동작O
		flag = this.sqlSessionTemplate.update(statement, opt);
		
		return flag;
	}
	
	/**
	 * 옵션 삭제
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Opt opt = (Opt) dto;
		StringBuffer sb = new StringBuffer(200);
		
		//mybatis sql : NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doDelete99";
		
		LOG.debug("==================================");
		LOG.debug("=opt="+opt);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		//mybatis오류 확인
		//1. NAMESPACE+.+id 확인 : ****
		//2. query오류 확인
		//3. resultType 확인
		flag = this.sqlSessionTemplate.delete(statement, opt);
		return flag;
	}
	
	/**
	 * 옵션 등록
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Opt opt = (Opt) dto;
		
		//mybatis sql : NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doInsert99";
		LOG.debug("==================================");
		LOG.debug("=opt="+opt);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		flag = this.sqlSessionTemplate.insert(statement, opt);

		return flag;
	}

	/**
	 * 옵션 단건조회
	 * @param DTO
	 * @return DTO
	 * @throws SQLException
	 */
	public DTO doSelectOne(DTO dto) throws SQLException {
		Opt inVO = (Opt) dto;
		Opt outVO = null;
		
		String statement = this.NAMESPACE+".doSelectOne99";
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

	/**
	 * 목록조회
	 * @param dto
	 * @return List<Opt>
	 * @throws SQLException
	 */
	public List<Opt> doRetrieve(DTO dto) throws SQLException {
		List<Opt> list = null;
		Opt opt = (Opt) dto;

		String statement = this.NAMESPACE+".doRetrieve99";
		
		LOG.debug("==================================");
		LOG.debug("=opt=\n"+opt);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		list = this.sqlSessionTemplate.selectList(statement, opt);
		
		for(Opt vo:list) {
			LOG.debug("=vo="+vo.toString());
		}
		
		return list;
	}

}
