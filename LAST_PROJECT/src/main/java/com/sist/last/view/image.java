package com.sist.last.view;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sist.last.cmn.StringUtil;
import com.sist.last.service.QnaService;
import com.sist.last.vo.Image;

@Controller
public class image {

	final Logger LOG = LoggerFactory.getLogger(image.class);

	// 절대 경로 상수로 올리기
	final String UPLOAD_IMG_DIR = "C:\\Users\\SIST\\git\\LAST_PROJECT\\LAST_PROJECT\\src\\main\\webapp\\resources\\img";

	@Autowired
	QnaService qnaService;

	public image() {

	}
	
	// 화면 띄우기
	@RequestMapping(value = "houses/houses_regist.do")
	public String houseView(Model model) throws SQLException {

		LOG.debug("registView");

		return "regist/houses_upload";
	}	
	
	

	// 화면 띄우기
	@RequestMapping(value = "qna/qna_regist.do")
	public String registView(Model model) throws SQLException {

		LOG.debug("registView");

		return "regist/qna_upload";
	}

	@RequestMapping(value = "qna/qna_upload.do", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest mReg, ModelAndView modelAndView)
			throws IllegalStateException, IOException {

		LOG.debug("------------------");
		LOG.debug("-----upload()-----");
		LOG.debug("------------------");

		// 폴더당 생성 파일 수 limit 존재
		// 2021/04
		File fileRootDir = new File(UPLOAD_IMG_DIR);
		if (fileRootDir.isDirectory() == false) {
			boolean flag = fileRootDir.mkdir();
			LOG.debug("upload() 생성여부" + flag);
		}

		// 년도
		String year = StringUtil.formatDate("yyyy");
		// 월
		String month = StringUtil.formatDate("MM");
		LOG.debug("year: " + year);
		LOG.debug("month: " + month);

		String datePath = UPLOAD_IMG_DIR + File.separator + year + File.separator + month;
		LOG.debug("datePath: " + datePath);

		File dateFilePath = new File(datePath);
		if (dateFilePath.isDirectory() == false) {
			boolean flag = dateFilePath.mkdirs();

			LOG.debug("upload() dateFilePath: " + flag);
		}

		List<Image> list = new ArrayList<Image>();

		String title = mReg.getParameter("title");
		String tag = mReg.getParameter("tag");
		LOG.debug("title: " + title);
		LOG.debug("tag: " + tag);
		
		Iterator<String> files = mReg.getFileNames();

		while (files.hasNext()) {
			Image image = new Image();
			String upFileNm = files.next();
			LOG.debug("upFileNm: " + upFileNm);

			// file 정보
			MultipartFile mFile = mReg.getFile(upFileNm);

			// 원본파일
			String orgName = mFile.getOriginalFilename();
			LOG.debug("orgName: " + orgName);

			if (null == orgName || "".equals(orgName))
				continue;

			image.setOrgName(orgName);
			image.setImgSize(mFile.getSize());
			
			// 파일아이디(pk값)
			String imgId = StringUtil.getPK("yyyyMMddHHmmss");
			LOG.debug("imgId: " + imgId);

			// 저장파일명
			String saveName = StringUtil.getPK("yyyyMMddHHmmss");
			LOG.debug("saveName: " + saveName);

			// 확장자
			String ext = "";
			if (orgName.indexOf(".") > -1) {
				ext = orgName.substring(orgName.indexOf(".") + 1);
				saveName += "." + ext;
			}

			LOG.debug("ext: " + ext);

			image.setImgId(imgId);
			image.setSaveName(saveName);
			image.setImgExt(ext);

			// 저장파일명으로 server에 저장
			File renameFile = new File(datePath, image.getSaveName());
			// 파일 절대경로
			LOG.debug("renameFile.getAbsolutePath(): " + renameFile.getAbsolutePath());

			// 파일 경로
			image.setSavePath(datePath);
			LOG.debug("image: " + image);

			list.add(image);

			// 파일을 server에 저장
			mFile.transferTo(new File(image.getSavePath() + File.separator + image.getSaveName()));

		} // --while

		modelAndView.addObject("list", list);
		modelAndView.setViewName("regist/upload");

		return "qna/qna_list";

	}

}
