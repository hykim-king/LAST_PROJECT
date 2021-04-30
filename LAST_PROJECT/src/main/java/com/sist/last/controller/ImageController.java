package com.sist.last.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sist.last.cmn.Message;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.ImageService;
import com.sist.last.vo.Grade;
import com.sist.last.vo.Image;
import com.sist.last.vo.Member;
import com.sist.last.vo.Qna;

@Controller
public class ImageController {

	final Logger LOG = LoggerFactory.getLogger(ImageController.class);
	
	final String UPLOAD_QNA_IMG_DIR = "C:\\Users\\SIST\\git\\LAST_PROJECT\\LAST_PROJECT\\src\\main\\webapp\\resources\\img\\qna";
	
	final String UPLOAD_MEMBER_IMG_DIR = "C:\\20201123_eClass\\04_SPRING\\workspace\\LAST_PAYMENT\\src\\main\\webapp\\resources\\img\\member";
	final String VIEW_NAME = "image/image_mng";

	@Autowired
	ImageService imageService;

	public ImageController() {

	}
	
	@RequestMapping(value = "image/qna_upload.do", method = RequestMethod.POST)
	public ModelAndView qnaImgUpload(MultipartHttpServletRequest mReg, ModelAndView modelAndView) throws IllegalStateException, IOException, SQLException {
		
		File fileRootDir = new File(UPLOAD_QNA_IMG_DIR);
		if (fileRootDir.isDirectory() == false) {
			boolean flag = fileRootDir.mkdir();
			LOG.debug("upload() 생성여부: " + flag);
		}
		
		// 년도
		String year = StringUtil.formatDate("yyyy");
		// 월
		String month = StringUtil.formatDate("MM");
		LOG.debug("year: " + year);
		LOG.debug("month: " + month);
	
		String datePath = UPLOAD_QNA_IMG_DIR+File.separator+year+File.separator+month;
		
		File dateImgPath = new File(datePath);
		if(dateImgPath.isDirectory() == false) {
			boolean flag = dateImgPath.mkdirs();
			
			LOG.debug("upload() dateFilePath: " + flag);  
		}

		Image imageVO = new Image();
		Qna qnaVO = new Qna();
		
		Iterator<String> image = mReg.getFileNames();

		while (image.hasNext()) {
			
			String upImgNm = image.next();
			LOG.debug("upImgNm: " + upImgNm);

			// file 정보
			MultipartFile mFile = mReg.getFile(upImgNm);

			// 원본 파일
			String orgName = mFile.getOriginalFilename();
			LOG.debug("orgName: " + orgName);

			if (null == orgName || "".equals(orgName))
				continue;

			imageVO.setOrgName(orgName);
			imageVO.setImgSize(mFile.getSize());// bytes

			// 2021042314241453c8dc1edb95784a98b249b5fd9ce4edf3
			String saveName = StringUtil.getPK("yyyyMMddHH24mmss");
			LOG.debug("saveName: " + saveName);

			String ext = "";
			if (orgName.indexOf(".") > -1) {
				ext = orgName.substring(orgName.indexOf(".") + 1);
				saveName += "." + ext;
			}

			LOG.debug("ext: " + ext);
			
			imageVO.setSaveName(saveName);
			imageVO.setImgExt(ext);		
			
			// server에 저장(저장파일명으로 저장)
			File renameImg = new File(datePath, imageVO.getSaveName());			
			LOG.debug("renameFile.getAbsolutePath(): " + renameImg.getAbsolutePath());// 파일 절대경로
			// fileVO.setSaveFileNm(renameFile.getAbsolutePath());// savePath FileVO 추가 전
			datePath = datePath.replaceAll("\\\\", "/");
			imageVO.setSavePath(datePath);// 파일경로
			
			LOG.debug("imageVO: " + imageVO);
			
			// file을 server에 저장
			mFile.transferTo(new File(imageVO.getSavePath() + File.separator + imageVO.getSaveName()));
			LOG.debug("mFile: " + mFile);
			
			imageVO.setSavePath(datePath);
			LOG.debug("datePath: " + datePath);

		} // --while
		
		String savePath;
		String saveName;
		
		if(null==imageVO.getSavePath() || imageVO.getSavePath().equals("")) {
			savePath = mReg.getParameter("savePath");
			saveName = mReg.getParameter("saveName");
		} else {
			savePath = imageVO.getSavePath();
			saveName = imageVO.getSaveName();
		}		
		
		String qnaSeq = mReg.getParameter("qnaSeq");
		String memberId = mReg.getParameter("memberId");
		String imgId = mReg.getParameter("imgId");
		String title = mReg.getParameter("title");
		String contents = mReg.getParameter("contents");
		String tag = mReg.getParameter("tag");
		String regDt = mReg.getParameter("regDt");
		String modId = mReg.getParameter("modId");
		String modDt = mReg.getParameter("modDt");
				
		Qna qna = new Qna(qnaSeq, memberId, imgId, title, contents, tag, regDt, modId, modDt);
		
		int flag = imageService.doInsertQnaImg(imageVO, qna);
		
		modelAndView.addObject("imageVO", imageVO);
		modelAndView.addObject("qna", qna);
		//modelAndView.addObject("qnaVO", qnaVO);
		modelAndView.setViewName("qna/qna_list");

		return modelAndView;

	}
	
	@RequestMapping(value = "image/member_upload.do", method = RequestMethod.POST)
	public ModelAndView imageInsert(MultipartHttpServletRequest mReg,ModelAndView modelAndView) throws IllegalStateException, IOException, SQLException {
		LOG.debug("======================");
		LOG.debug("=upload()=");
		LOG.debug("======================");
		
		File imgRootDir = new File(UPLOAD_MEMBER_IMG_DIR);
		if(imgRootDir.isDirectory()==false) {
			boolean flag = imgRootDir.mkdir();
			LOG.debug("=upload() 생성여부="+flag);  
		}
		//년도
		String year = StringUtil.formatDate("yyyy");
		//월
		String month = StringUtil.formatDate("MM");
		LOG.debug("=year="+year);
		LOG.debug("=month="+month);
		
		String datePath = UPLOAD_MEMBER_IMG_DIR+File.separator+year+File.separator+month;;
		LOG.debug("=datePath="+datePath);
		File dateImgPath = new File(datePath);
		if(dateImgPath.isDirectory()==false) {
			boolean flag =dateImgPath.mkdirs();
			LOG.debug("=upload() dateFilePath="+flag);  
		}
		//--2021/04
		
		Image image = new Image();
		
		Iterator<String> images = mReg.getFileNames();
		while(images.hasNext()) {
			
			String upImageNm = images.next();
			LOG.debug("=upImageNm="+upImageNm);
			
			//파일정보
			MultipartFile mFile = mReg.getFile(upImageNm);
			
			//원본파일
			String orgName = mFile.getOriginalFilename();
			LOG.debug("=orgName="+orgName);
			
			if(null == orgName || "".equals(orgName))continue;
			
			image.setOrgName(orgName);
			image.setImgSize(mFile.getSize()); //bytes
			
			//202104231424+uuid
			//2021042314241445c634a525064f4faba151d7bd8d365de7
			String saveName = StringUtil.getPK("yyyyMMddHH24mmss");
			LOG.debug("=saveName="+saveName);
			
			String ext = "";
			if(orgName.indexOf(".")>-1) {
				ext = orgName.substring(orgName.indexOf(".")+1);
				saveName+="."+ext;
			}
			LOG.debug("=ext="+ext);
			LOG.debug("=saveName+ext="+saveName);
			image.setSaveName(saveName);
			image.setImgExt(ext);
			
			LOG.debug("=image="+image);
			
			//Server에 저장: 저장 파일명으로 저장
			File renameImg = new File(datePath, image.getSaveName());
			LOG.debug("=renameFile.getAbsolutePath()="+renameImg.getAbsolutePath());
			datePath = datePath.replaceAll("\\\\", "/");
			image.setSavePath(datePath);
			//fileVO.setSaveFileNm(renameFile.getAbsolutePath());
			
			LOG.debug("=image="+image);
			
			//file server로 저장
			mFile.transferTo(new File(image.getSavePath()+File.separator+
					                  image.getSaveName()));
			LOG.debug("mFile:"+mFile);
			
			int idx = datePath.indexOf("/resources");
			datePath = datePath.substring(idx);
			image.setSavePath(datePath);
			LOG.debug("idx:"+idx);
			LOG.debug("datePath:"+datePath);
			
		}//--while
		
		String savePath;
		String saveName;
		
		if(null==image.getSavePath() || image.getSavePath().equals("")) {
			savePath = mReg.getParameter("savePath");
			saveName = mReg.getParameter("saveName");
		} else {
			savePath = image.getSavePath();
			saveName = image.getSaveName();
		}
		
		String imgId = mReg.getParameter("imgId");
		String passwd = mReg.getParameter("passwd");
		String nickname = mReg.getParameter("nickname");
		String introduce = mReg.getParameter("introduce");
		String grade = mReg.getParameter("grade");
		int div = Integer.parseInt(mReg.getParameter("div"));
		int scrap = Integer.parseInt(mReg.getParameter("scrap"));
		int login = Integer.parseInt(mReg.getParameter("login"));
		String regDt = mReg.getParameter("regDt");
		String modId = mReg.getParameter("modId");
		String memberId = mReg.getParameter("memberId");
		
		Member member = new Member(memberId, imgId, savePath, saveName ,passwd, nickname, introduce, Grade.valueOf(grade), div, scrap, login, regDt, modId, modId);
		LOG.debug("=passwd="+passwd);
		
		int flag = this.imageService.doInsertImgMember(image, member);
		LOG.debug("=flag="+flag);
		
		modelAndView.addObject("image",image);
		modelAndView.addObject("member",member);
		modelAndView.setViewName("login/Member_Edit");
		
		return modelAndView;
	}
	

	@RequestMapping(value = "image/do_retrieve.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(Image image) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + image);
		LOG.debug("----------------");

		List<Image> list = (List<Image>) this.imageService.doRetrieve(image);

		for (Image vo : list) {
			LOG.debug(vo.toString());
		}

		// List to Json
		Gson gson = new Gson();

		String jsonList = gson.toJson(list);
		LOG.debug("jsonList: " + jsonList);

		return jsonList;

	}

	@RequestMapping(value = "image/do_update.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(Image image) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + image);
		LOG.debug("----------------");

		int flag = this.imageService.doUpdate(image);

		String resultMsg = "";

		if (1 == flag) {
			resultMsg = "이미지 수정 성공";

		} else {
			resultMsg = "수정 실패";

		}

		Message message = new Message();
		message.setMsgId(flag + "");
		message.setMsgContents(resultMsg);

		Gson gson = new Gson();
		String jsonString = gson.toJson(message);

		LOG.debug("jsonString: " + jsonString);

		return jsonString;

	}

	@RequestMapping(value = "image/do_selectone.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Image image) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + image);
		LOG.debug("----------------");

		Image outVO = (Image) this.imageService.doSelectOne(image);

		LOG.debug("----------------");
		LOG.debug("outVO: " + outVO);
		LOG.debug("----------------");

		Gson gson = new Gson();
		String jsonString = gson.toJson(outVO);

		LOG.debug("jsonString: " + jsonString);

		return jsonString;

	}

	@RequestMapping(value = "image/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Image image) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + image);
		LOG.debug("----------------");

		int flag = imageService.doDelete(image);

		String resultMsg = "";

		if (1 == flag) {
			resultMsg = "이미지 삭제 성공";

		} else {
			resultMsg = "삭제 실패";

		}

		Message message = new Message();
		message.setMsgId(flag + "");
		message.setMsgContents(resultMsg);

		Gson gson = new Gson();
		String jsonString = gson.toJson(message);

		LOG.debug("jsonString: " + jsonString);

		return jsonString;

	}

	@RequestMapping(value = "image/do_insert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Image image) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + image);
		LOG.debug("----------------");

		int flag = imageService.doInsert(image);

		String resultMsg = "";

		if (1 == flag) {
			resultMsg = "이미지 등록 성공";

		} else {
			resultMsg = "등록 실패";

		}

		Message message = new Message();
		message.setMsgId(flag + "");
		message.setMsgContents(resultMsg);

		Gson gson = new Gson();
		String jsonString = gson.toJson(message);

		LOG.debug("jsonString: " + jsonString);

		return jsonString;

	}

}
