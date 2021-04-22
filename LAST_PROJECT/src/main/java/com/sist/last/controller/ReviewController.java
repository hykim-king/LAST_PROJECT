package com.sist.last.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.last.cmn.Search;
import com.sist.last.cmn.StringUtil;
import com.sist.last.cmn.Message;
import com.sist.last.service.ReviewService;
import com.sist.last.vo.Review;

@Controller
public class ReviewController {

	final Logger LOG = LoggerFactory.getLogger(ReviewController.class);
	final String VIEW_NAME = "review/review_mng";

	@Autowired
	ReviewService reviewService;

	public ReviewController() {

	}
	
	@RequestMapping(value = "review/do_retrieve.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doRetrieve(Search search) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + search);
		LOG.debug("----------------");

		// review fk값 nvl 처리
		search.setReviewFk(StringUtil.nvl(search.getReviewFk(), "-1"));

		// 페이지 num
		if (search.getPageNum() == 0) {
			search.setPageNum(1);
		}

		// 페이지 size
		if (search.getPageSize() == 0) {
			search.setPageSize(10);
		}

		LOG.debug("----------------");
		LOG.debug("param_init: " + search);
		LOG.debug("----------------");

		List<Review> list = (List<Review>) this.reviewService.doRetrieve(search);

		for (Review vo : list) {
			LOG.debug(vo.toString());
		}

		// List to Json
		Gson gson = new Gson();

		String jsonList = gson.toJson(list);
		LOG.debug("jsonList: " + jsonList);

		return jsonList;

	}

	@RequestMapping(value = "review/do_update.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doUpdate(Review review) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + review);
		LOG.debug("----------------");

		int flag = this.reviewService.doUpdate(review);

		String resultMsg = "";

		if (1 == flag) {
			resultMsg = review.getMemberId() + "님 \n 리뷰 수정 성공";

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

	@RequestMapping(value = "review/do_selectone.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(Review review) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + review);
		LOG.debug("----------------");

		Review outVO = (Review) this.reviewService.doSelectOne(review);

		LOG.debug("----------------");
		LOG.debug("outVO: " + outVO);
		LOG.debug("----------------");

		Gson gson = new Gson();
		String jsonString = gson.toJson(outVO);

		LOG.debug("jsonString: " + jsonString);

		return jsonString;

	}

	@RequestMapping(value = "review/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(Review review) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + review);
		LOG.debug("----------------");

		int flag = this.reviewService.doDelete(review);

		String resultMsg = "";

		if (1 == flag) {
			resultMsg = "리뷰 삭제 성공";

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

	@RequestMapping(value = "review/do_insert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(Review review) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + review);
		LOG.debug("----------------");

		int flag = reviewService.doInsert(review);

		String resultMsg = "";

		if (1 == flag) {
			resultMsg = review.getMemberId() + "님\n리뷰가 등록되었습니다.";

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
