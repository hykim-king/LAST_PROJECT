package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.ScrapDaoImpl;
import com.sist.last.vo.Scrap;

@Service
public class ScrapServiceImpl implements ScrapService {
	
	final Logger LOG = LoggerFactory.getLogger(ScrapServiceImpl.class);
	
	@Autowired
	private ScrapDaoImpl scrapDao;
	
	public ScrapServiceImpl() {
		
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		return this.scrapDao.doSelectOne(dto);
	}

	@Override
	public List<Scrap> doRetrieve(DTO dto) throws SQLException {
		return this.scrapDao.doRetrieve(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		return this.scrapDao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		return this.scrapDao.doInsert(dto);
	}

}
