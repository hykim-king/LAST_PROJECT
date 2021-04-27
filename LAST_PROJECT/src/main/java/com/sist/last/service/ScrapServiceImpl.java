package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.ScrapDaoImpl;
import com.sist.last.vo.Scrap;

@Service
public class ScrapServiceImpl {
	
	final Logger LOG = LoggerFactory.getLogger(ScrapServiceImpl.class);
	
	@Autowired
	private ScrapDaoImpl scrapDao;
	
	public ScrapServiceImpl() {
		
	}


	public DTO doSelectOne(DTO dto) throws SQLException {
		return this.scrapDao.doSelectOne(dto);
	}


	public List<?> doRetrieve(DTO dto) throws SQLException {
		return this.scrapDao.doRetrieve(dto);
	}


	public int doDelete(DTO dto) throws SQLException {
		return this.scrapDao.doDelete(dto);
	}


	public int doInsert(DTO dto) throws SQLException {
		Scrap scrap = (Scrap) dto;
		scrap.setScrapSeq(StringUtil.getPK(""));
		
		int flag = scrapDao.scrapCheck(scrap);
		if(flag>0) {
			flag=2;
			return flag;
		}else {
		
			flag = this.scrapDao.doInsert(scrap);
		}
		return flag;
	}

}
