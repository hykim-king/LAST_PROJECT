package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.BasketDaoImpl;

public class BasketServiceImpl implements BasketService {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BasketDaoImpl basketDao;
	
	public void setBasketDao(BasketDaoImpl basketDao) {
		this.basketDao = basketDao;
	}

	public BasketServiceImpl() {}
	
	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		return this.basketDao.doRetrieve(dto);
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		return this.basketDao.doSelectOne(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		return this.basketDao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		return this.basketDao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		return this.basketDao.doInsert(dto);
	}

}
