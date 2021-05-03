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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.last.cmn.SearchOrder;
import com.sist.last.service.ImageServiceImpl;
import com.sist.last.service.OptServiceImpl;
import com.sist.last.service.ProductServiceImpl;
import com.sist.last.vo.Image;
import com.sist.last.vo.Opt;
import com.sist.last.vo.Product;

@Controller
public class product {
	
	final Logger LOG = LoggerFactory.getLogger(product.class);
	
	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	ImageServiceImpl imageService;
	
	@Autowired
	OptServiceImpl optionService;
	
	@RequestMapping(value="store/store_detail.do",method= RequestMethod.GET)
	public String productView(Product product, Model model) throws SQLException {
		
		Product outVO = (Product) this.productService.doSelectOne(product);
		
		LOG.debug("==============");
		LOG.debug("=outVO="+outVO);
		LOG.debug("==============");
		
		model.addAttribute("vo", outVO);

		Image imageVO = new Image();
		imageVO.setImgId(outVO.getImgId());
		
		List<Image> imageList = (List<Image>) this.imageService.doRetrieve(imageVO);
		
		for(Image image : imageList) {
			LOG.debug("=image="+image.toString());
			LOG.debug("=image="+image.getSaveName());
			LOG.debug("=image="+image.getSavePath());
			
			String imagePath = image.getSavePath() + "/" + image.getSaveName();
			
			LOG.debug("=imagePath="+imagePath);

			model.addAttribute("imagePath", imagePath);
	
		}
		
		model.addAttribute("imageList", imageList);
		
		return "store/Store_Detail_Info";
	}
	
	@RequestMapping(value="member/basket_list.do",method= RequestMethod.GET)
	public String basketView(Model model) throws SQLException {
		
		return "cmn/Member_Shopping_Basket";
	}
	
	@RequestMapping(value="member/option_update.do",method= RequestMethod.GET)
	public String optionUpdate(Model model) throws SQLException {
		
		return "cmn/Member_Shopping_Basket_Edit";
	}
	
	@RequestMapping(value = "member/option_pop_up.do", method = RequestMethod.GET)
    public String optionPopUp(Opt opt, Model model) throws SQLException {


		opt.setDiv(1);
		
		
		List<Opt> list = this.optionService.doRetrieve(opt);
		
		for(Opt vo : list) {
			LOG.debug(vo.toString());
		}
		
		model.addAttribute("list", list);
		
        return "cmn/Member_Shopping_Basket_Edit";
    }

}