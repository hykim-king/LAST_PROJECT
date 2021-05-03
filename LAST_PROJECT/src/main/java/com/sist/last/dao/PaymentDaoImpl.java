package com.sist.last.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.sist.last.cmn.SearchPay;
import com.sist.last.vo.Payment;

@Repository
public class PaymentDaoImpl  {
	final static Logger LOG = LoggerFactory.getLogger(PaymentDaoImpl.class);

	final String NAMESPACE = "com.sist.last.payment"; //com.sist.ehr.member.doDelete -> 점 붙어야 함
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	
	public PaymentDaoImpl() {}
	
	/**
	 * 결제 목록조회//
	 * @param dto
	 * @return List<?>
	 * @throws SQLException
	 */

	public List<?> doRetrieve(DTO dto) throws SQLException {
		SearchPay param = (SearchPay) dto;
		String statement = this.NAMESPACE+".doRetrieve";
		
		LOG.debug("==================================");
		LOG.debug("=param=\n"+param);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		List<Payment> list = this.sqlSessionTemplate.selectList(statement, param);
		for(Payment vo:list) {
			LOG.debug(vo.toString());
		}
		
		return list;
	}
	
	/**
	 * 결제 수정
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */

	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Payment payment = (Payment) dto;
		
		String statement = this.NAMESPACE+".doUpdate";
		LOG.debug("==================================");
		LOG.debug("=payment="+payment);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		//update대신 insert 넣어도 동작O
		flag = this.sqlSessionTemplate.update(statement, payment);
		
		return flag;
	}

	/**
	 * 결제 취소
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Payment payment = (Payment) dto;
		
		//mybatis sql : NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doDelete";
		
		LOG.debug("==================================");
		LOG.debug("=payment="+payment);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		flag = this.sqlSessionTemplate.delete(statement, payment);
		return flag;
	}

	/**
	 * 결제 등록
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Payment payment = (Payment) dto;
		String statement = this.NAMESPACE+".doInsert";
		LOG.debug("==================================");
		LOG.debug("=payment="+payment);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		flag = this.sqlSessionTemplate.insert(statement, payment);
		
		return flag;
	}

	/**
	 * 결제 단건조회
	 * @param DTO
	 * @return DTO
	 * @throws SQLException
	 */
	public DTO doSelectOne(DTO dto) throws SQLException {
		Payment inVO = (Payment) dto;
		Payment outVO = null;
		
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
	

}
