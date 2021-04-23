package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.BasketDaoImpl;
import com.sist.last.vo.Basket;

@Service
public class BasketServiceImpl implements BasketService {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BasketDaoImpl basketDao;

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
		
		Basket basket= (Basket) dto;
		basket.setBasketSeq(StringUtil.getPK(""));
		
		return this.basketDao.doInsert(basket);
	}

}
