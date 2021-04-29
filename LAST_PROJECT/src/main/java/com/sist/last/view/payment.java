package com.sist.last.view;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class payment {

	//스토어 카테고리
	@RequestMapping(value="store/store_category.do",method= RequestMethod.GET)
	public String categoryView(Model model) throws SQLException {
	
		return "store/store_category";
	}
		
	//스토어 홈
	@RequestMapping(value="store/store_view.do",method= RequestMethod.GET)
	public String homeView(Model model) throws SQLException {
		
		return "store/store_home";
	}

}
