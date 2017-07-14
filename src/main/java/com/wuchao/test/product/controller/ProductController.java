package com.wuchao.test.product.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.maven.wagon.events.SessionListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuchao.test.product.bo.intf.ProductBO;
import com.wuchao.test.product.po.Product;
import com.wuchao.utils.config.SysConfig;
import com.wuchao.utils.exception.DAOException;
import com.wuchao.utils.exception.UtilException;

@Controller
public class ProductController {
	private static Logger log = Logger.getLogger(ProductController.class);  
	  
	@Resource(name="productBO")
	public ProductBO productBO;
    @RequestMapping("/addproduct")
    public @ResponseBody String addProduct() throws Exception {
    	log.info("addProduct():");    	
    	Product product = new Product();
    	product.setProductName("p1");
    	product.setPrice(100);
    	productBO.addProduct(product);
    
        return "add product successfull!";
    }
}
