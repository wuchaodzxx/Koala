package com.wuchao.blog.user.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

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
	//注册
    @RequestMapping("/register")
    public String register(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	String username = request.getParameter("username");
    	String nickname = request.getParameter("nickname");
    	String password = request.getParameter("password");
    	String varifycode = request.getParameter("varifycode");
    	String formResult=null;//用于返回表单验证信息
    	log.info("接收到注册请求:username="+username+",password="+password+",varifycode="+varifycode);
    	//校验注册请求是否携带所需要的请求参数参数
    	if(username==null || username.trim().equals("")
    			||nickname==null || nickname.trim().equals("")
    			||password==null || password.trim().equals("")
    			||varifycode==null || varifycode.trim().equals("")) {
    		request.setAttribute("formResult", null);
    		return "register";
    	}
    	
    	//获取seeion中的验证码
    	String VerifyCode = (String) session.getAttribute("VerifyCode");
    	
    	//获取session中的验证码失败
    	if(VerifyCode==null) {
    		return "errors/500";
    	}
    	
    	//如果验证码不正确
    	if(!VerifyCode.equalsIgnoreCase(varifycode)) {
    		log.error("验证码不正确");
    		formResult="验证码不正确";
    		request.setAttribute("formResult", formResult);
    		return "register";
    	}
    	Iuser user = new Iuser();
    	user.setUsername(username);
    	user.setNickname(nickname);
    	user.setPassword(password);
    	user.setCreatedate(new Date());
    	user.setModifydate(new Date());
    	
    	iuserBo.addIuser(user);
    	return "redirect:/registerSuccsess";
    }
    @RequestMapping("/registerSuccsess")
    public String registerSuccsess(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	return "registerSuccsess";
    }

}