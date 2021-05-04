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
import com.sist.last.cmn.SearchOrder;
import com.sist.last.service.HousesService;
import com.sist.last.service.HousesServiceImpl;
import com.sist.last.service.OptServiceImpl;
import com.sist.last.service.ProductServiceImpl;
import com.sist.last.vo.Houses;
import com.sist.last.vo.Opt;
import com.sist.last.vo.Product;





@Controller
public class houses {
	
	final Logger LOG = LoggerFactory.getLogger(houses.class);
	
	@Autowired
	HousesService housesService;
	
	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	OptServiceImpl optService;
	
	@RequestMapping(value="houses/home_view.do",method= RequestMethod.GET)
	public String homeView(Model model) throws SQLException {
		SearchOrder search = new SearchOrder();
		
		search.setSearchDiv("");
		search.setOrderDiv("20");
		search.setPageSize(8);
		search.setPageNum(1);
		search.setSearchWord("");
		List<DTO> list =  (List<DTO>) housesService.doRetrieve(search);
		
		
		
		
		model.addAttribute("list",list);
		
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
	
//	@RequestMapping(value="qna/qna_list.do",method= RequestMethod.GET)
//	public String qnaView(Model model) throws SQLException {
//
//		
//		return "qna/qna_list";
//	}
	
	//opt/opt_view.do?storeSeq=TESTNOTDEL
	
	@RequestMapping(value="opt/opt_view.do",method= RequestMethod.GET)
	public String optView(Product product, Model model) throws SQLException {
		
		Product outVO = (Product) productService.doSelectOne(product);
		
		Opt optOne = new Opt();
		
		optOne.setStoreSeq(outVO.getStoreSeq());
		optOne.setDiv(1);
		
		List<Opt> outOne = (List<Opt>) optService.doRetrieve(optOne);
		
		model.addAttribute("outOne",outOne);
		model.addAttribute("vo",outVO);
		
		
		return "houses/producOptOne";
	}
	
	public List<?> getCodedopageRetrieve(List<DTO> codeList) throws SQLException {
		
		Map<String, DTO> codeMap =  new HashMap<String, DTO>();
		codeMap.put("codeList", (DTO) codeList);
		
		return housesService.doRetrieve((DTO) codeMap);
	}
	

	
}
