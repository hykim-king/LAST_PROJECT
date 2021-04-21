package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.OptDao;
import com.sist.last.vo.Opt;

@Service
public class OptServiceImpl implements OptService {

	final static Logger LOG = LoggerFactory.getLogger("OptServiceImpl");
	
	private OptDao optDao;
	
	public OptServiceImpl() {}
	
	@Override
	public List<Opt> doRetrieve(DTO dto) throws SQLException {

		return this.optDao.doRetrieve(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		
		return this.optDao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		
		return this.optDao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {

		return this.optDao.doInsert(dto);
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {

		return this.optDao.doSelectOne(dto);
	}

}
