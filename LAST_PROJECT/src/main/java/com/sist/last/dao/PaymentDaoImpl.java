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

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.SearchPay;
import com.sist.last.vo.Payment;

public class PaymentDaoImpl implements PaymentDao {
	final static Logger LOG = LoggerFactory.getLogger(PaymentDaoImpl.class);

	final String NAMESPACE = "com.sist.last.payment"; //com.sist.ehr.member.doDelete -> 점 붙어야 함
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//Spring이 제공해주는 JdbcTemplate
	JdbcTemplate jdbcTemplate;
	
	DataSource dataSource;
	
	//ResultSet이 들어가 있음. 조회해온 데이터를 넣음
	RowMapper<Payment> row = new RowMapper<Payment>() {
		@Override
		public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Payment paymentVO = new Payment();
			
			paymentVO.setPaySeq(  rs.getString("pay_seq"));
			paymentVO.setStoreSeq(rs.getString("store_seq"));
			paymentVO.setMemberId(rs.getString("member_id"));
			paymentVO.setTitle(   rs.getString("title"));
			paymentVO.setOptone(  rs.getString("optone"));
			paymentVO.setOpttwo(  rs.getString("opttwo"));
			paymentVO.setQuantity(rs.getInt("quantity"));
			paymentVO.setPrice(   rs.getInt("price"));
			paymentVO.setShipfee( rs.getInt("shipfee"));
			paymentVO.setStatus(  rs.getInt("status"));
			paymentVO.setRegDt(   rs.getString("reg_dt"));
			
			paymentVO.setNum(rs.getInt("rnum"));
			paymentVO.setTotalCnt(rs.getInt("total_cnt"));
			
			
			return paymentVO;	
		}
	};
	
	public PaymentDaoImpl() {
		
	}
	
	//setter를 통한 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
	
	/**
	 * 결제 목록조회
	 * @param dto
	 * @return List<?>
	 * @throws SQLException
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
