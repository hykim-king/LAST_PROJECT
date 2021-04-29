package com.sist.last.view;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class product {
	
	final Logger LOG = LoggerFactory.getLogger(product.class);
	
	@RequestMapping(value="store/store_detail.do",method= RequestMethod.GET)
	public String productView(Model model) throws SQLException {
		
		return "store/Store_Detail_Info";
	}
	
	@RequestMapping(value="member/basket_list.do",method= RequestMethod.GET)
	public String basketView(Model model) throws SQLException {
		
		return "cmn/Member_Shopping_Basket";
	}
	
}