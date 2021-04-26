package com.sist.last.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sist.last.cmn.DTO;
import com.sist.last.service.HousesServiceImpl;
import com.sist.last.vo.Houses;





@Controller
public class houses {
	
	final Logger LOG = LoggerFactory.getLogger(houses.class);
	
	@Autowired
	HousesServiceImpl housesService;
	
	@RequestMapping(value="houses/home_view.do",method= RequestMethod.GET)
	public String homeView(Model model) throws SQLException {
		
		
	
		return "houses/Community_Home";
	}
	
	
	
	
	@RequestMapping(value="houses/list_view.do",method= RequestMethod.GET)
	public String listView(Model model) throws SQLException {
		
		
	
		return "houses/Community_List";
	}
	
	
	@RequestMapping(value="houses/test_view.do",method= RequestMethod.GET)
	public String testView(Model model) throws SQLException {

		
		return "houses/home_test";
	}
	
	
	public List<?> getCodedopageRetrieve(List<DTO> codeList) throws SQLException {
		
		Map<String, DTO> codeMap =  new HashMap<String, DTO>();
		codeMap.put("codeList", (DTO) codeList);
		
		return housesService.doRetrieve((DTO) codeMap);
	}
	

	
}
