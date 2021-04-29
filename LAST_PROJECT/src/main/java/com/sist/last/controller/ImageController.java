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

@Controller
public class ImageController {

	final Logger LOG = LoggerFactory.getLogger(ImageController.class);
	final String UPLOAD_MEMBER_IMG_DIR = "C:\\20201123_eClass\\04_SPRING\\workspace\\LAST_PAYMENT\\src\\main\\webapp\\resources\\img\\member";
	final String VIEW_NAME = "image/image_mng";

	@Autowired
	ImageService imageService;

	public ImageController() {

	}
	
	@RequestMapping(value = "image/do_upload.do", method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpload(MultipartHttpServletRequest request) throws Exception {
		
		List<MultipartFile> fileList = request.getFiles("file_list");
		
		Gson gson = new Gson();
		
		//파일이 업로드 될 경로
		String path = "C:\\Users\\SIST\\git\\LAST_PROJECT\\LAST_PROJECT\\src\\main\\webapp\\resources";
		String simplePath = "/resources/img/";
		
		
		//위에서 설정한 경로의 폴더가 없을 경우 생성
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		
		List<Image> imageList = new ArrayList<Image>();
		
		for(MultipartFile file : fileList){
			if(!file.isEmpty()) {
				String orgName = file.getOriginalFilename();
				String saveName = StringUtil.getPK("yyyyMMddHHmmss");
				long fileSize = (long) file.getSize();
				String fileExt = orgName.substring(orgName.lastIndexOf("."));
				
				Image image = new Image();
				image.setOrgName(orgName);
				image.setSaveName(saveName);
				image.setSavePath(simplePath);
				image.setImgSize(fileSize);
				image.setImgExt(fileExt);
				
				imageList.add(image);
				
				file.transferTo(new File(path, saveName));
			}
		}
		
		
		String jsonStr = gson.toJson(imageList.toArray());
		
		LOG.debug(jsonStr);
		
		return jsonStr;
		
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
