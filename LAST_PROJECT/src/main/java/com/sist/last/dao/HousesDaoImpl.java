package com.sist.last.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.SearchOrder;
import com.sist.last.vo.Houses;

@Repository
public class HousesDaoImpl {

	final static Logger LOG = LoggerFactory.getLogger(HousesDaoImpl.class);

	final String NAMESPACE = "com.sist.last.houses";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public HousesDaoImpl() {

	}

	/**
	 * 집들이 목록 조회
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public List<?> doRetrieve(DTO dto) throws SQLException {

		SearchOrder param = (SearchOrder) dto;

		String statement = this.NAMESPACE+".doRetrieve";

		LOG.debug("=================");
		LOG.debug("=param="+param);
		LOG.debug("=statement="+statement);
		LOG.debug("=================");
		
		List<Houses> list = sqlSessionTemplate.selectList(statement, param);
		
		for(Houses vo : list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}

	/**
	 * 집들이 수정
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;

		Houses houses = (Houses) dto;

		String statement = this.NAMESPACE+".doUpdate";

		LOG.debug("=================");
		LOG.debug("=houses="+houses);
		LOG.debug("=statement="+statement);
		LOG.debug("=================");
		
		flag = this.sqlSessionTemplate.update(statement, houses);

		return flag;
	}

	/**
	 * 집들이 삭제
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;

		Houses houses = (Houses) dto;

		String statement = this.NAMESPACE+".doDelete";
		
		LOG.debug("=================");
		LOG.debug("=houses="+houses);
		LOG.debug("=statement="+statement);
		LOG.debug("=================");
		
		flag = this.sqlSessionTemplate.delete(statement, houses);		

		return flag;
	}

	/**
	 * 집들이 등록
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;

		Houses houses = (Houses) dto;

		String statement = this.NAMESPACE+".doInsert";

		LOG.debug("=================");
		LOG.debug("=houses="+houses);
		LOG.debug("=statement="+statement);
		LOG.debug("=================");
		
		flag = this.sqlSessionTemplate.insert(statement, houses);
		return flag;
	}

	/**
	 * 집들이 단건 조회
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public DTO doSelectOne(DTO dto) throws SQLException {

		Houses inVO = (Houses) dto;
		Houses outVO = null;

		String statement = this.NAMESPACE+".doSelectOne";

		LOG.debug("=================");
		LOG.debug("=houses="+inVO);
		LOG.debug("=statement="+statement);
		LOG.debug("=================");
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
				
		if(outVO == null) {
			LOG.debug("=================");
			LOG.debug("=null outVO="+outVO);
			LOG.debug("=================");
			throw new EmptyResultDataAccessException("EmptyResultDataAccessException",1);
		}

		return outVO;
	}

}
