package com.sist.last.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sist.last.cmn.Message;
import com.sist.last.cmn.SearchReview;
import com.sist.last.cmn.StringUtil;
import com.sist.last.service.ReviewService;
import com.sist.last.service.StarServiceImpl;
import com.sist.last.vo.Review;
import com.sist.last.vo.ReviewStar;
import com.sist.last.vo.Star;

@Controller
public class ReviewController {

	final Logger LOG = LoggerFactory.getLogger(ReviewController.class);
	final String VIEW_NAME = "review/review_mng";

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	StarServiceImpl starService;

	public ReviewController() {

	}
	
	@RequestMapping(value = "review/review_star_list.do", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String reviewStarList(SearchReview search) throws SQLException {

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

		List<ReviewStar> list = (List<ReviewStar>) this.reviewService.reviewStarList(search);

		for (ReviewStar vo : list) {
			LOG.debug(vo.toString());
		}
		
		
		int totalCnt = 0;
		if(list != null && list.size()>0) {
			ReviewStar totalVO = list.get(0);
			totalCnt = totalVO.getTotalReviews();
		}
			
		ModelAndView model = new ModelAndView();
		
		model.addObject("list",list); 
		model.addObject("totalCnt",totalCnt);
		model.setViewName("store/Store_Detail_Info");
		
		// List to Json
		Gson gson = new Gson();

		String jsonList = gson.toJson(list);
		LOG.debug("jsonList: " + jsonList);

		return jsonList;

	}
	
	@RequestMapping(value = "review/do_retrieve.do", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String doRetrieve(SearchReview search) throws SQLException {

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
	public String doDelete(Review review, Star star) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + review);
		LOG.debug("----------------");

		int flag = this.reviewService.doDelete(review, star);

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
	public String doInsert(Review review, Star star) throws SQLException {

		LOG.debug("----------------");
		LOG.debug("param: " + review);
		LOG.debug("----------------");
		
		int flag = reviewService.doInsert(review, star);
		
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
