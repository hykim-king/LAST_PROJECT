package com.sist.last.view;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class payment {

	final Logger LOG = LoggerFactory.getLogger(payment.class);
	
	//스토어 카테고리
	@RequestMapping(value="store/store_category.do",method= RequestMethod.GET)
	public String categoryView(@RequestParam("searchDiv") String searchDiv, Model model) throws SQLException {
		LOG.debug("=============================");
		LOG.debug("searchDiv:"+searchDiv);
		LOG.debug("=============================");
		
		model.addAttribute("searchDiv", searchDiv);
		
		return "store/store_category";
	}
		
	//스토어 홈
	@RequestMapping(value="store/store_view.do",method= RequestMethod.GET)
	public String homeView(Model model) throws SQLException {
		
		return "store/store_home";
	}

}
