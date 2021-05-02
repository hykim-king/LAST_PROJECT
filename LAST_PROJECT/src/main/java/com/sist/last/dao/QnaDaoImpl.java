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
import com.sist.last.cmn.Search;
import com.sist.last.vo.Qna;

@Repository
public class QnaDaoImpl {

	final static Logger LOG = LoggerFactory.getLogger(QnaDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.qna";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public QnaDaoImpl() {}
	

	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Qna qna =  (Qna) dto;
		String statement = this.NAMESPACE+".doDelete";
		
		LOG.debug("=======================================");
		LOG.debug("====qna=====" + qna);
		LOG.debug("=======================================");
		
		flag=sqlSessionTemplate.delete(statement, qna);
		
		return flag;
	}

	
	/**
	 * 등록
	 * @param qna
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Qna qna =  (Qna) dto;
		String statement = this.NAMESPACE+".doInsert";
			
		LOG.debug("=======================================");
		LOG.debug("====qna=====" + qna);
		LOG.debug("=======================================");
		
		flag=sqlSessionTemplate.insert(statement, qna);
		
		return flag;
	}


	public DTO doSelectOne(DTO dto) throws SQLException {
		Qna inVO = (Qna) dto;
		Qna outVO = null;
		
		String statement = this.NAMESPACE+".doSelectOne";
		LOG.debug("=======================================");
		LOG.debug("=qna inVO=" + inVO);
		LOG.debug("=statement=" + statement);
		LOG.debug("=======================================");
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
			
		LOG.debug("=======================================");
		LOG.debug("=outVO=" + outVO);
		LOG.debug("=======================================");

		if (outVO == null) {
			LOG.debug("=======================================");
			LOG.debug("=null outVO=" + outVO);
			LOG.debug("=======================================");
			throw new EmptyResultDataAccessException(1);
		}
		
		return outVO;
	}

	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Qna qna =  (Qna) dto;
		
		String statement = this.NAMESPACE + ".doUpdate";
		
		LOG.debug("=======================================");
		LOG.debug("====qna=====" + qna);
		LOG.debug("=======================================");
		
		flag = this.sqlSessionTemplate.insert(statement, qna); //insert넣어도 되고 update넣어도됨 들어가서 보면 어차피 다 insert로 넣게됨.

			
		return flag;
	}


	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		Search param =  (Search) dto;
		
		String statement = this.NAMESPACE+".doRetrieve";
		

		LOG.debug("1=param=====");
		
		List<Qna> list = this.sqlSessionTemplate.selectList(statement, param);

		for(Qna vo : list) {
			LOG.debug(vo.toString());
		}
		
		return list;
	}

	
	
}
