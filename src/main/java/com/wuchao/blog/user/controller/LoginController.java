package com.wuchao.blog.user.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wuchao.blog.user.bo.intf.IuserBo;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.exception.DAOException;
import com.wuchao.utils.security.VerifyCodeUtil;

@Controller
public class LoginController{	
	private static Logger log = Logger.getLogger(LoginController.class);  
	
	@Resource(name="iuserBo")
	public IuserBo iuserBo;
	
    @RequestMapping("/login")
    public String login(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String varifycode = request.getParameter("varifycode");
    	String formResult=null;//用于返回表单验证信息
    	log.info("接收到登陆请求:username="+username+",password="+password+",varifycode="+varifycode);
    	//校验登陆请求是否携带所需要的请求参数参数
    	if(username==null || username.trim().equals("")
    			||password==null || password.trim().equals("")
    			||varifycode==null || varifycode.trim().equals("")) {
    		request.setAttribute("formResult", null);
    		return "login";
    	}
    	
    	//获取seeion中的验证码
    	String VerifyCode = (String) session.getAttribute("VerifyCode");
    	Iuser user = iuserBo.getIuserByUsername(username);
    	
    	//获取session中的验证码失败
    	if(VerifyCode==null) {
    		log.error("服务器session获取不到验证码");
    		formResult="验证码不正确";
    		request.setAttribute("formResult", formResult);
    		return "login";
    	}
    	
    	//如果验证码不正确
    	if(!VerifyCode.equalsIgnoreCase(varifycode)) {
    		log.error("验证码不正确");
    		formResult="验证码不正确";
    		request.setAttribute("formResult", formResult);
    		session.setAttribute("VerifyCode", null);//验证码清除，只能使用一次
    		return "login";
    	}
    	//如果用户不存在或者密码不正确
    	if(user==null || !user.getPassword().equals(password)) {
    		log.error("用户不存在或密码不正确");
    		formResult="用户不存在或密码不正确";
    		request.setAttribute("formResult", formResult);
    		session.setAttribute("VerifyCode", null);//验证码清除，只能使用一次
    		return "login";
    	}
    	request.setAttribute("formResult", null);
    	session.setAttribute("user", user);
    	session.setAttribute("VerifyCode", null);//验证码清除，只能使用一次
    	return "redirect:/"+user.getUsername()+"/home";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	session.setAttribute("user", null);
    	return "redirect:/login";
    }
    
    /*
     * 
     *返回验证码图像
     *
     */
    @RequestMapping("/login/getVerifyCode")
    public void getVerifyCode(HttpServletResponse response,HttpSession session) throws IOException {
        response.setContentType("img/jpeg");  
        response.setCharacterEncoding("utf-8");  
        
    	String s = VerifyCodeUtil.generateVerifyCode(4);
    	session.setAttribute("VerifyCode", s);
    	OutputStream os = response.getOutputStream();
    	VerifyCodeUtil.outputImage(110, 30, os, s);
    }
}