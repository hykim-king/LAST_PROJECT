package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.ImageDaoImpl;
import com.sist.last.dao.MemberDaoImpl;
import com.sist.last.dao.QnaDaoImpl;
import com.sist.last.vo.Image;
import com.sist.last.vo.Member;
import com.sist.last.vo.Qna;

@Service
public class ImageServiceImpl implements ImageService {

	final Logger LOG = LoggerFactory.getLogger(ImageServiceImpl.class);

	@Autowired
	private MemberDaoImpl memberDao;
	
	@Autowired
	private ImageDaoImpl imageDao;
	
	@Autowired
	private QnaDaoImpl qnaDao;

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

	@Override
	public int doInsertQnaImg(List<Image> imageList, int imgNum) throws SQLException {

		int flag = 0;
		int cnt = 0;
		
		if (imageList.size() > 0) {
			
			


		}

		return flag;
	}

	@Override
	public int doInsertImgMember(DTO image, DTO member) throws SQLException {
		int flag = 0;
		Image imageVO = (Image) image;
		Member memberVO = (Member) member;
		
		String pk = StringUtil.getPK("yyyyMMddHH24mmss");

		LOG.debug("=============================");
		LOG.debug("=imageVO="+imageVO);
		LOG.debug("=memberVO="+memberVO);
		LOG.debug("=============================");
		
		if(null == imageVO.getOrgName() || imageVO.getOrgName().equals("")) {
			flag = this.memberDao.doUpdate(memberVO);
		} else {
			imageVO.setImgId(pk);
			imageVO.setImgNum(1);
			memberVO.setImgId(pk);
			
			LOG.debug("=============================");
			LOG.debug("=imageVO="+imageVO);
			LOG.debug("=memberVO="+memberVO);
			LOG.debug("=============================");
			
			flag = this.memberDao.doUpdate(memberVO);
			LOG.debug("=flag="+flag);
			flag += this.imageDao.doInsert(imageVO);
		}
				
		return flag;
	}

}
