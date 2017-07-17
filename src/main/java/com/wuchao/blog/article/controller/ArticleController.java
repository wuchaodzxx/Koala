package com.wuchao.blog.article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wuchao.blog.article.bo.intf.IarticleBo;
import com.wuchao.blog.article.po.Iarticle;
import com.wuchao.blog.category.bo.intf.IarticleLabelBo;
import com.wuchao.blog.category.po.IarticleLabel;
import com.wuchao.blog.user.bo.intf.IuserBo;
import com.wuchao.blog.user.controller.ManagerController;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.constant.SystemConstant;
import com.wuchao.utils.exception.DAOException;

@Controller
public class ArticleController {

	private static Logger log = Logger.getLogger(ManagerController.class);  
	
	@Resource(name="iarticleBo")
	public IarticleBo iarticleBo;
	
	@Resource(name="iuserBo")
	public IuserBo iuserBo;
	
	@Resource(name="iarticleLabelBo")
	public IarticleLabelBo iarticleLabelBo;
	
	/*
	 * 接受ajax请求 查询用户记录总数
	 */
	@RequestMapping(value="/{userName}/getPages", method = {RequestMethod.POST, RequestMethod.GET})
	public void home(@PathVariable String userName, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws DAOException {
		// 访问主页面，先判断是否传入userID参数，如果传入了，则访问指定用户的index.jsp，否则默认访问本人的index.jsp
		log.info("接受ajax请求，查询用户" + userName + "记录总数");
		
		//每页记录数
		int pageSize = SystemConstant.PAGE_SIZE;
		//总记录数
		int totalRowCount = iarticleBo.getAllRowCount(userName);
		//页数
		int pages=1;
		if(pageSize>0) {
			pages = (int) Math.ceil(totalRowCount/(double)pageSize);//向上取整
		}

		try {
			// 设置页面不缓存
			//response.setContentType("application/text");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(pages);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    //${TargetUserName}/browser?articleId
    /*
     * 请求指定article页面
     */
    @RequestMapping("/{userName}/browser")
    public String browser(@PathVariable String userName,
    					HttpServletResponse response,
    					HttpServletRequest request,
    					HttpSession session) throws DAOException {
    	log.info("browser 请求目标用户："+userName);
    	int articleId;
    	String articleIdStr = request.getParameter("articleId");
    	try {
    		articleId = Integer.valueOf(articleIdStr);
    	}catch(Exception e) {
    		return "errors/articleNotFound";
    	}
    	Iarticle  article = iarticleBo.getIarticleById(articleId);
        
    	//获取目标用户信息，便于index.jsp展示
    	Iuser targetUser = iuserBo.getIuserByUsername(userName);
        
    	//获取目标用户的标签列表
    	List<IarticleLabel> articleLabelList = iarticleLabelBo.getIarticleLabelListByUserId(targetUser.getId());
    	
    	
    	request.setAttribute("Article", article);
    	request.setAttribute("TargetUserName", userName);
        request.setAttribute("TargetUser", targetUser);       
        request.setAttribute("ArticleLabelList", articleLabelList);
    	return "article/showArticle";
    }
    //${TargetUserName}/browserByLabel
    /*
     * 请求标签页
     */
    @RequestMapping("/{userName}/browserByLabel")
    public String browserByLabel(@PathVariable String userName,
    					HttpServletResponse response,
    					HttpServletRequest request,
    					HttpSession session) throws DAOException {
    	log.info("browser 请求目标用户："+userName);
    	String labelId = request.getParameter("articleLabelId");
    	int articleLabelId = Integer.valueOf(labelId);
    	List<Iarticle> iarticleList =  iarticleBo.getArticleListByArticleLabelId(articleLabelId);
    	IarticleLabel articleLabel = iarticleLabelBo.getArticleLabel(articleLabelId);
    	//获取目标用户信息，便于index.jsp展示
    	Iuser targetUser = iuserBo.getIuserByUsername(userName);
        
    	//获取目标用户的标签列表
    	List<IarticleLabel> articleLabelList = iarticleLabelBo.getIarticleLabelListByUserId(targetUser.getId());
    	
    	request.setAttribute("TargetUserName", userName);
    	request.setAttribute("IarticleList", iarticleList);
    	
        request.setAttribute("TargetUser", targetUser);
        
        request.setAttribute("ArticleLabelList", articleLabelList);
        
        request.setAttribute("ArticleLabel", articleLabel);
        
    	return "article/showArticleLabel";
    }
    
	//创建新博客
    @RequestMapping("/newArticle")
    public String newArticle(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	log.info("创建新博客请求");
    	Iuser user = (Iuser) session.getAttribute("user");
    	
    	//用户不存在
    	if(user==null) {
    		log.info("创建新博客请求失败：用户未登录，跳转登陆页面");
    		return "redirect:/login";
    	}
    	//用户存在
    	log.info("创建新博客请求成功，跳转创建新博客页面");
    	return "/article/newArticle";
    }
}
