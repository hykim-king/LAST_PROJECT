package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.HousesDaoImpl;

@Service
public class HousesServiceImpl implements HousesService {

	final Logger LOG = LoggerFactory.getLogger(HousesServiceImpl.class);

	@Autowired
	private HousesDaoImpl housesDao;
	
	public HousesServiceImpl() {
		
	}
	
	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		return this.housesDao.doRetrieve(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		return this.housesDao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		return this.housesDao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		return this.housesDao.doInsert(dto);
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		return this.housesDao.doSelectOne(dto);
	}

}
