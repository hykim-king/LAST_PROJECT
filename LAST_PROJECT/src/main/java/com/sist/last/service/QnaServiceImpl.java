package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.QnaDaoImpl;

public class QnaServiceImpl implements QnaService {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private QnaDaoImpl QnaDao;
	
	public void setQnaDao(QnaDaoImpl qnaDao) {
		QnaDao = qnaDao;
	}

	public QnaServiceImpl() {}
	
	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		return this.QnaDao.doRetrieve(dto);
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		return this.QnaDao.doSelectOne(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		return this.QnaDao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		return this.QnaDao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		return this.QnaDao.doInsert(dto);
	}

}
