package com.wuchao.blog.user.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wuchao.blog.user.bo.intf.IuserBo;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.exception.DAOException;
import com.wuchao.utils.security.VerifyCodeUtil;

@Controller
public class RegisterController{	
	private static Logger log = Logger.getLogger(RegisterController.class);  
	
	@Resource(name="iuserBo")
	public IuserBo iuserBo;
	
    @RequestMapping("/registerRequest")
    public String logout(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	return "/register";
    }
    
	//注册
    @RequestMapping("/register")
    public String register(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	
    	return "";
    }

}