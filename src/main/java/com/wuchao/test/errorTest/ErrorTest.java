package com.wuchao.test.errorTest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuchao.test.product.controller.ProductController;
import com.wuchao.utils.exception.DAOException;

@Controller
public class ErrorTest {
	private static Logger log = Logger.getLogger(ProductController.class);  
    @RequestMapping("/errorException")
    public String errorTest() throws Exception {
    	log.info("Exception():");    	
    	throw new Exception("Exception");
    }
    @RequestMapping("/errorDAO")
    public String errorDAO() throws DAOException {
    	log.info("errorDAO():");    	
    	throw new DAOException();
    }
}
