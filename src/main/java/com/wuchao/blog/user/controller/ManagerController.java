package com.wuchao.blog.user.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuchao.blog.article.bo.intf.IarticleBo;
import com.wuchao.blog.article.po.Iarticle;
import com.wuchao.blog.category.bo.intf.IarticleLabelBo;
import com.wuchao.blog.category.po.IarticleLabel;
import com.wuchao.blog.user.bo.intf.IuserBo;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.exception.DAOException;


@Controller
public class ManagerController{	
	private static Logger log = Logger.getLogger(ManagerController.class);  
	
	@Resource(name="iarticleBo")
	public IarticleBo iarticleBo;
	
	@Resource(name="iuserBo")
	public IuserBo iuserBo;
	
	@Resource(name="iarticleLabelBo")
	public IarticleLabelBo iarticleLabelBo;
	
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
    	//查看是否有请求参数articleLabelId
    	String articleLabelId = request.getParameter("articleLabelId");
    	List<Iarticle> articleList = null;
    	List<IarticleLabel> articleLabelList = iarticleLabelBo.getIarticleLabelListByUserId(user.getId());;
    	if(articleLabelId!=null) {
    		articleList = iarticleBo.getArticleListByArticleLabelId(Integer.valueOf(articleLabelId));
    	}else {
    		if(articleLabelList!=null && articleLabelList.size()>0) {
    			articleList = iarticleBo.getArticleListByArticleLabelId(articleLabelList.get(0).getId());
    		}
    	}
    	
    	if(articleLabelList!=null && !articleLabelList.isEmpty()) {
    		request.setAttribute("ArticleLabelList", articleLabelList);
    	}
    	if(articleList!=null&&!articleList.isEmpty()) {
    		request.setAttribute("ArticleList", articleList);
    	}
    	
    	log.info("用户管理请求成功，跳转管理页面");
    	return "/manager/manager";
    }
    
	//用户管理请求
    @RequestMapping("/deleteArticle")
    public String deleteArticle(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	log.info("用户管理请求");
    	Iuser user = (Iuser) session.getAttribute("user");
    	
    	//用户不存在
    	if(user==null) {
    		log.info("用户管理请求失败：用户未登录，跳转登陆页面");
    		return "redirect:/login";
    	}
    	
    	String articleId = request.getParameter("articleId");
    	Iarticle article = iarticleBo.getIarticleById(Integer.valueOf(articleId));
    	IarticleLabel articleLabel = iarticleLabelBo.getArticleLabel(article.getLabelId());
    	
    	//删除文章
    	iarticleBo.deleteIarticle(Integer.valueOf(articleId));
    	//更新标签
    	articleLabel.setArticleNum(articleLabel.getArticleNum()-1);
    	iarticleLabelBo.updateArticleLabel(articleLabel);
    	
    	return "redirect:/manager?articleLabelId="+articleLabel.getId();
    }
    
	//用户密码修改请求
    @RequestMapping("/changePasswordRequest")
    public String changePasswordRequest(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	log.info("用户密码修改请求");
    	Iuser user = (Iuser) session.getAttribute("user");
    	
    	//用户不存在
    	if(user==null) {
    		log.info("用户密码修改请求失败：用户未登录，跳转登陆页面");
    		return "redirect:/login";
    	}
    	return "manager/changePassword";
    }
	//用户密码修改提交
    @RequestMapping("/changePasswordSubmit")
    public String changePasswordSubmit(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	log.info("用户密码修改提交");

    	String formResult=null;//用于返回表单验证信息
    	
    	//获取seeion中的验证码
    	String VerifyCode = (String) session.getAttribute("VerifyCode");
    	//获取session中的验证码失败
    	if(VerifyCode==null) {
    		log.error("服务器session获取不到验证码");
    		formResult="验证码不正确";
    		request.setAttribute("formResult", formResult);
    		return "manager/changePassword";
    	}
    	//
    	String oldpassword = request.getParameter("oldpassword");
    	String newpassword = request.getParameter("newpassword");
    	String username = request.getParameter("username");
    	log.info("username:"+username+",oldpassword:"+oldpassword+",newpassword:"+newpassword);
    	Iuser user = iuserBo.getIuserByUsername(username);
    	//如果用户不存在或者密码不正确
    	if(user==null || !user.getPassword().equals(oldpassword)) {
    		log.error("用户不存在或密码不正确");
    		formResult="用户不存在或密码不正确";
    		request.setAttribute("formResult", formResult);
    		session.setAttribute("VerifyCode", null);//验证码清除，只能使用一次
    		return "manager/changePassword";
    	}
    	user.setPassword(newpassword);
    	user.setModifydate(new Date());
    	iuserBo.updateIuser(user);
    	
    	//注销用户状态
    	session.setAttribute("user", null);
    	log.info("密码修改成功，跳转登录页面");
    	return "/manager/changePasswordSuccsess";
    }
	//用户密码修改请求
    @RequestMapping("/changeUserInfo")
    public String changeUserInfo(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	log.info("资料修改请求");
    	Iuser user = (Iuser) session.getAttribute("user");
    	
    	//用户不存在
    	if(user==null) {
    		log.info("资料修改请求失败：用户未登录，跳转登陆页面");
    		return "redirect:/login";
    	}
    	return "manager/changeUserInfo";
    }
	//关于
    @RequestMapping("/about")
    public String about(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	log.info("关于");
    
    	return "about/about";
    }
}