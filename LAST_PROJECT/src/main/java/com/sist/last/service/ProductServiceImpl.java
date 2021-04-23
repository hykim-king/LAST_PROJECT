package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.ProductDaoImpl;
import com.sist.last.vo.Product;

@Service
public class ProductServiceImpl  {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductDaoImpl productDao;
	
	public ProductServiceImpl() {}
	
	



	
	public DTO doSelectOne(DTO dto) throws SQLException {
		
		return this.productDao.doSelectOne(dto);
	}

	
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		return this.productDao.doRetrieve(dto);
	}

	
	public int doUpdate(DTO dto) throws SQLException {
		
		return this.productDao.doUpdate(dto);
	}

	
	public int doDelete(DTO dto) throws SQLException {
		
		return this.productDao.doDelete(dto);
	}

	
	public int doInsert(DTO dto) throws SQLException {
		
		Product product = (Product) dto;
		product.setStoreSeq(StringUtil.getPK(""));
		
		return this.productDao.doInsert(dto);
	}
	
}
