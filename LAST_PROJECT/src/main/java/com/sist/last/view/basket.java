package com.sist.last.view;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class basket {
	
	
	@RequestMapping(value="houses/home_view.do",method= RequestMethod.GET)
	public String loginView(Model model) throws SQLException {
		
		
	
		return "houses/Community_Home";
	}
	
}
