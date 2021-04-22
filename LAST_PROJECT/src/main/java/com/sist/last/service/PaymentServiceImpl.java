package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.PaymentDao;
import com.sist.last.dao.PaymentDaoImpl;

@Service
public class PaymentServiceImpl implements PaymentService {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PaymentDaoImpl dao;
	
	public PaymentServiceImpl() {}
	
	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		return this.dao.doRetrieve(dto);
	}

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
		
		return this.dao.doInsert(dto);
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		
		return this.dao.doSelectOne(dto);
	}

}
