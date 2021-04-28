package com.sist.last.view;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class scrap {
	
	final Logger LOG = LoggerFactory.getLogger(scrap.class);
	
	@RequestMapping(value="houses/houses_detail.do") //url에 이 경로치면 
	public String housesDetail() {
		LOG.debug("=================");
		LOG.debug("=housesDetail()=");
		LOG.debug("=================");
		
		return "houses/Community_Detail_Info";              //이 페이지 띄워주는거 같음!!!!
	}
	
	@RequestMapping(value="mypage/scrap_list.do")
	public String scrapView() {
		LOG.debug("=================");
		LOG.debug("=scrapView()=");
		LOG.debug("=================");
		
		return "mypage/Member_ScrapBook";           
	}
}
