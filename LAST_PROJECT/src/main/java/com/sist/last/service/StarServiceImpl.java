package com.sist.last.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.StarDaoImpl;

@Service
public class StarServiceImpl implements StarService {
	
	final Logger LOG = LoggerFactory.getLogger(StarServiceImpl.class);
	
	@Autowired
	private StarDaoImpl starDao;
	
	public StarServiceImpl() {
		
	}
	

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		return this.starDao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		return this.starDao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		return this.starDao.doInsert(dto);
	}

}
