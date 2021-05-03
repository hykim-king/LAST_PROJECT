package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.StarDaoImpl;
import com.sist.last.vo.Star;

@Service
public class StarServiceImpl implements StarService {
	
	final Logger LOG = LoggerFactory.getLogger(StarServiceImpl.class);
	
	@Autowired
	private StarDaoImpl starDao;
	
	public StarServiceImpl() {
		
	}
	
	public int doUpdate(DTO dto) throws SQLException {
		return this.starDao.doUpdate(dto);
	}
	
	public int doDelete(DTO dto) throws SQLException {
		return this.starDao.doDelete(dto);
	}
	
	public int doInsert(DTO dto) throws SQLException {
		
		Star star = (Star) dto;
		star.setStarSeq(StringUtil.getPK("yyyyMMdd24mmss")); 
		
		return this.starDao.doInsert(dto);
	}

	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		return this.starDao.doRetrieve(dto);
	}

}
