package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.ReviewDaoImpl;
import com.sist.last.dao.StarDaoImpl;
import com.sist.last.vo.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

	@Autowired
	private ReviewDaoImpl reviewDao;

	@Autowired
	private StarDaoImpl starDao;

	public ReviewServiceImpl() {

	}

	// setter를 통한 DI
	public void setReviewDao(ReviewDaoImpl reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	@Override
	public List<?> reviewStarList(DTO dto) throws SQLException {
		
		return this.reviewDao.reviewStarList(dto);
	}
	
	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
				
		return this.reviewDao.doRetrieve(dto);
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {

		return this.reviewDao.doSelectOne(dto);
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {

		return this.reviewDao.doUpdate(dto);
	}

	@Override
	public int doDelete(DTO dto, DTO dto2) throws SQLException {

		starDao.doDelete(dto2);
		
		return this.reviewDao.doDelete(dto);
	}

	@Override
	public int doInsert(DTO dto, DTO dto2) throws SQLException {

		Review review = (Review) dto;
		review.setReviewSeq(StringUtil.getPK("yyyyMMddHHmmss"));

		starDao.doInsert(dto2);

		return this.reviewDao.doInsert(review);
	}

}
