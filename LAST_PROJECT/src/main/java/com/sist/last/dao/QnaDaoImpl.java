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
import com.sist.last.cmn.StringUtil;
import com.sist.last.vo.Basket;
import com.sist.last.vo.Qna;

@Repository
public class QnaDaoImpl implements QnaDao {

	final static Logger LOG = LoggerFactory.getLogger(QnaDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.qna";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	

	RowMapper<Qna> row =  new RowMapper<Qna>() {
		public Qna mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Qna qnaVO = new Qna();
			qnaVO.setQnaSeq(rs.getString("qna_seq"));
			qnaVO.setMemberId(rs.getString("member_id"));
			qnaVO.setImgId(rs.getString("img_id"));
			qnaVO.setTitle(rs.getString("title"));
			qnaVO.setContents(rs.getString("contents"));
			qnaVO.setTag(rs.getString("tag"));
			qnaVO.setRegDt(rs.getString("reg_dt"));
//			qnaVO.setModId(rs.getString("mod_id"));
//			qnaVO.setModDt(rs.getString("mod_dt"));
			
			qnaVO.setNum(rs.getInt("rnum"));
			qnaVO.setTotalCnt(rs.getInt("total_cnt"));
			
			return qnaVO;
			
		}
	};
	
	public QnaDaoImpl() {}
	
	@Override
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
	@Override
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

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		Qna inVO = (Qna) dto;
		Qna outVO = null;
		
		String statement = this.NAMESPACE+".doSelectOne";
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
			
		LOG.debug("=======================================");
		LOG.debug("=outVO=" + outVO);
		LOG.debug("=======================================");

		if (outVO == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return outVO;
	}

	@Override
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

	@Override
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
