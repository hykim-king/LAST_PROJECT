package com.sist.last.view;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sist.last.service.HousesServiceImpl;
import com.sist.last.service.ImageServiceImpl;
import com.sist.last.vo.Houses;
import com.sist.last.vo.Image;


@Controller
public class scrap {
	
	final Logger LOG = LoggerFactory.getLogger(scrap.class);
	
	@Autowired
	HousesServiceImpl housesService;
	
	@Autowired
	ImageServiceImpl imageService;
	
	
	@RequestMapping(value="houses/houses_detail.do", method=RequestMethod.GET) //url에 이 경로
	public String housesDetail(Houses houses, Model model) throws Exception {
		LOG.debug("=================");
		LOG.debug("=housesDetail()=");
		LOG.debug("=================");
		
		Houses outVO = (Houses) this.housesService.doSelectOne(houses);
		LOG.debug("=================");
		LOG.debug("=outVO()=" + outVO);
		LOG.debug("=================");
		
		model.addAttribute("vo", outVO);

		Image imageVO = new Image();
		imageVO.setImgId(outVO.getImgId());

		List<Image> imageList = (List<Image>) this.imageService.doRetrieve(imageVO);
		
		for(Image image : imageList) {
			LOG.debug("=image=" + image);
			LOG.debug("=image.getSaveName()=" + image.getSaveName());
			LOG.debug("=image.getSavePath()=" + image.getSavePath());
		
			String imagePath = image.getSavePath() + "/" + image.getSaveName();
			
			model.addAttribute("imagePath",imagePath);
		}
		model.addAttribute("imageList", imageList);
		
		return "houses/Community_Detail_Info";//이 페이지 띄워주는거 같음!!!!
	}
	
	@RequestMapping(value="mypage/scrap_list.do")
	public String scrapView() {
		LOG.debug("=================");
		LOG.debug("=scrapView()=");
		LOG.debug("=================");
		
		return "mypage/Member_ScrapBook";           
	}
}
