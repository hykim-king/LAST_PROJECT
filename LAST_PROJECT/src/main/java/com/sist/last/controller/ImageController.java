package com.sist.last.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.last.cmn.Message;
import com.sist.last.service.ImageService;
import com.sist.last.vo.Image;

@Controller
public class ImageController {

	final Logger LOG = LoggerFactory.getLogger(ImageController.class);
	final String VIEW_NAME = "image/image_mng";

	@Autowired
	ImageService imageService;

	public ImageController() {

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
