package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.ReplyDao;
import com.sist.last.vo.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private ReplyDao dao;
	
	public ReplyServiceImpl() {}
	
	@Override 
	public int doUpdate(DTO dto) throws SQLException {

		return this.dao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {

		return this.dao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		Reply reply = (Reply) dto;
		reply.setReplySeq(StringUtil.getPK("yyyyMMdd24mmss")); 
		
		return this.dao.doInsert(reply);
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
	
		return this.dao.doSelectOne(dto);
	}

	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		return this.dao.doRetrieve(dto);
	}

}
