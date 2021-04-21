package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.HousesLinkDao;
import com.sist.last.vo.HousesLink;

public class HousesLinkServiceimpl implements HousesLinkService {
	
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private HousesLinkDao housesLinkDao;//interface
	
	public HousesLinkServiceimpl() { }
	
	//setter통한 DI
	public void setHousesLinkDao(HousesLinkDao housesLinkDao) {
		this.housesLinkDao = housesLinkDao;
	}


	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		return this.housesLinkDao.doRetrieve(dto);
	}//--doRetrieve

	
	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {

		return this.housesLinkDao.doSelectOne(dto);
	}//--doSelectOne

	@Override
	public int doUpdate(DTO dto) throws SQLException {

		return this.housesLinkDao.doUpdate(dto);
	}//--doUpdate

	@Override
	public int doDelete(DTO dto) throws SQLException {

		return this.housesLinkDao.doDelete(dto);
	}//--doDelete

	@Override
	public int doInsert(DTO dto) throws SQLException {
		
		return this.housesLinkDao.doInsert(dto);
	}

}//--class
