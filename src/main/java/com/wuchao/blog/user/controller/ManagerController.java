package com.wuchao.blog.user.controller;

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


@Controller
public class ManagerController{	
	private static Logger log = Logger.getLogger(ManagerController.class);  
	
	@Resource(name="iuserBo")
	public IuserBo iuserBo;
	//用户管理请求
    @RequestMapping("/manager")
    public String manager(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	log.info("用户管理请求");
    	Iuser user = (Iuser) session.getAttribute("user");
    	
    	//用户不存在
    	if(user==null) {
    		log.info("用户管理请求失败：用户未登录，跳转登陆页面");
    		return "redirect:/login";
    	}
    	//用户存在
    	log.info("用户管理请求成功，跳转管理页面");
    	return "/manager/manager";
    }

}