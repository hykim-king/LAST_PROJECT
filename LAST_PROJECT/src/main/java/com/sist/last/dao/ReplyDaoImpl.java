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
import com.sist.last.vo.Reply;

@Repository
public class ReplyDaoImpl implements ReplyDao{
	final static Logger LOG = LoggerFactory.getLogger(ReplyDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.reply";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
//	JdbcTemplate jdbcTemplate;
//	DataSource dataSource;
	
	RowMapper<Reply> row = new RowMapper<Reply>() {
		@Override
		public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
			Reply replyVO = new Reply();
			replyVO.setReplySeq(rs.getString("reply_seq"));
			replyVO.setMemberId(rs.getString("member_id"));
			replyVO.setReviewSeq(rs.getString("review_seq"));
			replyVO.setContents(rs.getString("contents"));
			replyVO.setRegDt(rs.getString("reg_dt"));
			replyVO.setModId(rs.getString("mod_id"));
			replyVO.setModDt(rs.getString("mod_dt"));
			
			replyVO.setNum(rs.getInt("rnum"));
			replyVO.setTotalCnt(rs.getInt("total_cnt"));
			
			return replyVO;
		}
	};
	
	public ReplyDaoImpl() {}

//	//setter를 통한 주입
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}


	@Override
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Reply reply = (Reply) dto;
	
		String statement = this.NAMESPACE+".doUpdate";
		
		LOG.debug("=================");
		LOG.debug("==reply=="+reply);
		LOG.debug("==statement=="+statement);
		LOG.debug("=================");
		
		flag = this.sqlSessionTemplate.insert(statement, reply);
		
		return flag;
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Reply reply = (Reply) dto;
		
		String statement = this.NAMESPACE+".doDelete";
		
		LOG.debug("=================");
		LOG.debug("==reply=="+reply);
		LOG.debug("==statement=="+statement);
		LOG.debug("=================");
		
		
		flag = this.sqlSessionTemplate.delete(statement, reply);
		return flag;
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Reply reply = (Reply) dto;
		
		String statement = this.NAMESPACE+".doInsert";
		
		LOG.debug("=================");
		LOG.debug("==reply=="+reply);
		LOG.debug("==statement=="+statement);
		LOG.debug("=================");
		
		flag = this.sqlSessionTemplate.insert(statement, reply);
		
		return flag;
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		Reply inVO = (Reply) dto;
		Reply outVO = null;
		
		String statement = this.NAMESPACE+".doSelectOne";
		LOG.debug("=================");
		LOG.debug("==Reply=="+inVO);
		LOG.debug("==statement=="+statement);
		LOG.debug("=================");
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);

		LOG.debug("==================================");
		LOG.debug("==outVO=="+outVO);
		LOG.debug("==================================");
		
		if(null == outVO) {
			LOG.debug("==================================");
			LOG.debug("==NULL outVO=="+outVO);
			LOG.debug("==================================");
			throw new EmptyResultDataAccessException(1);
		}
		
		return outVO;
	}

	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {

		Search param = (Search) dto;
		
		String statement = this.NAMESPACE+".doRetrieve";
		
		LOG.debug("=================");
		LOG.debug("==param=="+param);
		LOG.debug("==statement=="+statement);
		LOG.debug("=================");
		
		List<Reply> list = sqlSessionTemplate.selectList(statement, param);
		
		for(Reply vo:list) {
			LOG.debug("vo:"+vo);
		}
		
		return list;
	}
	

}
