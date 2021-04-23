package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.HousesLinkDao;
import com.sist.last.dao.HousesLinkDaoImpl;
import com.sist.last.vo.HousesLink;

@Service
public class HousesLinkServiceImpl implements HousesLinkService {
	
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HousesLinkDaoImpl housesLinkDao;
	
	public HousesLinkServiceImpl() { }
	

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
		
		HousesLink link = (HousesLink) dto;
		link.setLinkSeq(StringUtil.getPK(""));
		
		return this.housesLinkDao.doInsert(link);
	}

}//--class
