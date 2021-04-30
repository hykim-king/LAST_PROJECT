package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.StringUtil;
import com.sist.last.dao.HousesDaoImpl;
import com.sist.last.dao.HousesLinkDaoImpl;
import com.sist.last.dao.ImageDaoImpl;
import com.sist.last.dao.MemberDaoImpl;
import com.sist.last.dao.QnaDaoImpl;
import com.sist.last.vo.Houses;
import com.sist.last.vo.HousesLink;
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
	
	@Autowired
	private HousesDaoImpl housesDao;
	
	@Autowired
	private HousesLinkDaoImpl housesLinkDao;

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

	@Override
	public int doInsertQnaImg(DTO image, DTO qna) throws SQLException {

		int flag = 0;
		
		Image imageVO = (Image) image;
		Qna qnaVO = (Qna) qna;
		
		Random random = new Random();
		int pk = random.nextInt(8) + 1;
		LOG.debug("pk: "+pk);
		
		// 파일아이디(pk값)
		String imgId = StringUtil.getPK("yyyyMMddHHmmss");
		LOG.debug("imgId: " + imgId);
		
		qnaVO.setQnaSeq(StringUtil.getPK("yyyyMMdd24mmss"));
		
//		// 사진 첨부 안하면 바로 insert
//		if(null == imageVO.getOrgName() || imageVO.getOrgName().equals("")) {
//			
//			flag = flag += this.qnaDao.doInsert(qnaVO);
//			
//		} else {
		
		imageVO.setImgNum(pk);
		imageVO.setImgId(imgId);
		qnaVO.setImgId(imgId);
		
		LOG.debug("imageVO: "+imageVO);
		LOG.debug("qnaVO: "+qnaVO);

		flag = this.imageDao.doInsert(imageVO);
		flag += this.qnaDao.doInsert(qnaVO);
		
//		}
			
		return flag;
		
		}

	@Override
	public int doInsertHousesImg(DTO image, DTO houses, DTO housesLink) throws SQLException {

		Image imageVO = (Image) image;
		Houses housesVO = (Houses) houses;
		HousesLink housesLinkVO = (HousesLink) housesLink;
		
		// 이미지 아이디--------------------------------------------
		String imgId = StringUtil.getPK("yyyyMMddHHmmss");
		LOG.debug("imgId: " + imgId);
		
		imageVO.setImgId(imgId);
		housesVO.setImgId(imgId);
				
		// 집들이 seq 값--------------------------------------------
		String housesSeq = StringUtil.getPK("yyyyMMddHHmmss");
		
		housesVO.setHousesSeq(housesSeq);
		housesLinkVO.setHousesSeq(housesSeq);
		
		// 집들이링크 seq 값--------------------------------------------
		housesLinkVO.setLinkSeq(StringUtil.getPK("")); 
		
		LOG.debug("imageVO: " + imageVO);		
		LOG.debug("housesVO: " + housesVO);
		LOG.debug("housesLinkVO: " + housesLinkVO);
		
		int flag = this.imageDao.doInsert(imageVO);
		flag += this.housesDao.doInsert(housesVO);
		flag += this.housesLinkDao.doInsert(housesLinkVO);	
		
		return flag;
		
		}

	
	}
