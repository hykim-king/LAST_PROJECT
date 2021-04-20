package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.ProductDao;

public class ProductServiceImpl implements ProductService {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private ProductDao productDao;
	
	public ProductServiceImpl() {}
	
	
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}



	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		
		return this.productDao.doSelectOne(dto);
	}

	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		return this.productDao.doRetrieve(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		
		return this.productDao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		
		return this.productDao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return this.productDao.doInsert(dto);
	}
	
}
