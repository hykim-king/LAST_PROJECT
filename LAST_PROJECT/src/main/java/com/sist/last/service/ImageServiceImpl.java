package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.dao.ImageDaoImpl;

@Service
public class ImageServiceImpl implements ImageService {

	final Logger LOG = LoggerFactory.getLogger(ImageServiceImpl.class);

	@Autowired
	private ImageDaoImpl imageDao;

	public ImageServiceImpl() {

	}

	public void setImageDao(ImageDaoImpl imageDao) {
		this.imageDao = imageDao;
	}

	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {

		return this.imageDao.doRetrieve(dto);
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {

		return this.imageDao.doSelectOne(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {

		return this.imageDao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {

		return this.imageDao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {

		return this.imageDao.doInsert(dto);
	}

}
