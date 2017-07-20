package com.wuchao.blog.article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wuchao.blog.article.bo.intf.IarticleBo;
import com.wuchao.blog.article.po.Iarticle;
import com.wuchao.blog.category.bo.intf.IarticleLabelBo;
import com.wuchao.blog.category.po.IarticleLabel;
import com.wuchao.blog.comment.bo.intf.IarticleCommentBo;
import com.wuchao.blog.comment.po.IarticleComment;
import com.wuchao.blog.file.bo.intf.FileUpLoadService;
import com.wuchao.blog.user.bo.intf.IuserBo;
import com.wuchao.blog.user.controller.ManagerController;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.constant.SystemConstant;
import com.wuchao.utils.exception.DAOException;
import com.wuchao.utils.string.StringUtils;

@Controller
public class ArticleController {

	private static Logger log = Logger.getLogger(ManagerController.class);  
	
	@Resource(name="iarticleBo")
	public IarticleBo iarticleBo;
	
	@Resource(name="iuserBo")
	public IuserBo iuserBo;
	
	@Resource(name="iarticleLabelBo")
	public IarticleLabelBo iarticleLabelBo;
	
	@Resource(name="fileUpLoadService")
	public FileUpLoadService fileUpLoadService;
	
	@Resource(name="iarticleCommentBo")
	public IarticleCommentBo iarticleCommentBo;
	
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
        if(article==null) {
        	return "errors/articleNotFound";
        }
        //新博客浏览人数+1
        article.setBrowsers(article.getBrowsers()+1);
        iarticleBo.updateArticle(article);
        
        List<IarticleComment> iarticleCommentList= iarticleCommentBo.getIarticleCommentList(article.getId());
        
        //评论按照时间排序
        Collections.sort(iarticleCommentList, new Comparator<IarticleComment>() {  
        	//按照时间，时间越早，排在前面
            public int compare(IarticleComment o1, IarticleComment o2) {  
                if(o1.getCommentDate().getTime()>o1.getCommentDate().getTime()) {
                	return -1;
                }else {
                    return 1;
                }
            }  
        }); 
        
    	//获取目标用户信息，便于index.jsp展示
    	Iuser targetUser = iuserBo.getIuserByUsername(userName);
        
    	//获取目标用户的标签列表
    	List<IarticleLabel> articleLabelList = iarticleLabelBo.getIarticleLabelListByUserId(targetUser.getId());
    	
    	
    	request.setAttribute("Article", article);
    	request.setAttribute("TargetUserName", userName);
        request.setAttribute("TargetUser", targetUser);       
        request.setAttribute("ArticleLabelList", articleLabelList);
        request.setAttribute("ArticleCommentList", iarticleCommentList);
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
    	List<IarticleLabel> iarticleLabelList = iarticleLabelBo.getIarticleLabelListByUserId(user.getId());
    	
    	request.setAttribute("IarticleLabelList", iarticleLabelList);
    	
    	log.info("创建新博客请求成功，跳转创建新博客页面");
    	return "/article/newArticle";
    }
	//创建新博客
    @RequestMapping("/newArticleSubmit")
    public void newArticleSubmit(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException, NumberFormatException, DAOException {
    	log.info("提交新博客请求");
    	Iuser user = (Iuser) session.getAttribute("user");
    	
    	//用户存在
		String content = request.getParameter("content");
		String articleTitle = request.getParameter("articleTitle");
		String articleLabelId = request.getParameter("articleLabelId");
		
		log.info("articleTitle:"+articleTitle+"\n articleLabelId:"+articleLabelId+"\n content:"+content);
    	
		//新博客需要更新 Iarticale和IarticleLabel两个表
		IarticleLabel articleLabel = iarticleLabelBo.getArticleLabel(Integer.valueOf(articleLabelId));
		articleLabel.setArticleNum(articleLabel.getArticleNum()+1);
		
		//删除文章html标签，并截取前150个字符
		String abstractsDesc = StringUtils.deleHTML(content);
		if(abstractsDesc.length()>150) {
			abstractsDesc=abstractsDesc.substring(0, 149);
		}
		Iarticle article = new Iarticle();
		article.setAbstractsDesc(abstractsDesc);
		article.setBrowsers(0);
		article.setComments(0);
		article.setCreateDate(new Date());
		article.setModifyDate(new Date());
		article.setLabelId(Integer.valueOf(articleLabelId));
		article.setTitle(articleTitle);
		article.setUserId(user.getId());
		article.setUsername(user.getUsername());
		article.setContent(content);
		
		try {
			iarticleBo.addIarticle(article);
			iarticleLabelBo.updateArticleLabel(articleLabel);
		}catch(Exception e) {
			e.printStackTrace();
			PrintWriter writer = response.getWriter();
			writer.print(e.getMessage());
			writer.flush();
			writer.close();
		}

		
		PrintWriter writer = response.getWriter();
		writer.print("successful");
		writer.flush();
		writer.close();
    }
    
    ///Koala/editArticle?articleId
	//编辑博客请求
    @RequestMapping("/editArticle")
    public String editArticle(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws DAOException {
    	log.info("编辑博客请求");
    	Iuser user = (Iuser) session.getAttribute("user");
    	
    	//用户不存在
    	if(user==null) {
    		log.info("编辑博客请求失败：用户未登录，跳转登陆页面");
    		return "redirect:/login";
    	}
    	
    	//用户存在
    	String articleId = request.getParameter("articleId");
    	Iarticle  article = null;
    	try {
    		article = iarticleBo.getIarticleById(Integer.valueOf(articleId));
    	}catch(Exception e) {
    		return "errors/articleNotFound";
    	}
    	
        if(article==null) {
        	return "errors/articleNotFound";
        }
    	List<IarticleLabel> iarticleLabelList = iarticleLabelBo.getIarticleLabelListByUserId(user.getId());
    	
    	request.setAttribute("IarticleLabelList", iarticleLabelList);
    	request.setAttribute("Article", article);
    	
    	log.info("编辑博客请求成功，跳转创建新博客页面");
    	return "/article/editArticle";
    }
 
	//编辑博客
    @RequestMapping("/editArticleSubmit")
    public void editArticleSubmit(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException, NumberFormatException, DAOException {
    	log.info("提交编辑博客请求");
    	Iuser user = (Iuser) session.getAttribute("user");
    	
    	//用户存在
		String content = request.getParameter("content");
		String articleTitle = request.getParameter("articleTitle");
		String articleLabelId = request.getParameter("articleLabelId");
		String articleId = request.getParameter("articleId");
		
		
		log.info("articleId:"+articleId+"\narticleTitle:"+articleTitle+"\n articleLabelId:"+articleLabelId+"\n content:"+content);
		
		//删除文章html标签，并截取前150个字符
		String abstractsDesc = StringUtils.deleHTML(content);
		if(abstractsDesc.length()>150) {
			abstractsDesc=abstractsDesc.substring(0, 149);
		}
		
		Iarticle article = iarticleBo.getIarticleById(Integer.valueOf(articleId));
		
		//判断laeblId是否改变，如果改变了，需要更新label表
		if(Integer.valueOf(articleId)!=article.getLabelId()) {
			IarticleLabel articleLabel0 = iarticleLabelBo.getArticleLabel(Integer.valueOf(articleLabelId));
			IarticleLabel articleLabel1 = iarticleLabelBo.getArticleLabel(article.getLabelId());
			articleLabel1.setArticleNum(articleLabel1.getArticleNum()-1);
			articleLabel0.setArticleNum(articleLabel0.getArticleNum()+1);
			iarticleLabelBo.updateArticleLabel(articleLabel0);
			iarticleLabelBo.updateArticleLabel(articleLabel1);
		}
		
		article.setAbstractsDesc(abstractsDesc);
		article.setModifyDate(new Date());
		article.setLabelId(Integer.valueOf(articleLabelId));
		article.setTitle(articleTitle);
		article.setContent(content);
		
		try {
			iarticleBo.updateArticle(article);
		}catch(Exception e) {
			e.printStackTrace();
			PrintWriter writer = response.getWriter();
			writer.print(e.getMessage());
			writer.flush();
			writer.close();
		}

		
		PrintWriter writer = response.getWriter();
		writer.print("successful");
		writer.flush();
		writer.close();
    }
    
	@RequestMapping(value = "/imageUpload", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String imageUpLoad(@RequestParam("upload") CommonsMultipartFile[] attachmentArray,
			@RequestParam("CKEditorFuncNum") String callback, 
			HttpServletResponse res, 
			HttpServletRequest req,HttpSession session)
			throws Exception {
		log.info("图片上传请求：");
		CommonsMultipartFile attachment = attachmentArray[0];
		// 找到已登陆用户
		Iuser user = (Iuser) session.getAttribute("user");
		if(user==null) {
			throw new Exception("用户未登录");
		}
		if (attachment.getOriginalFilename().length() < 1) {
			String returnString = "<script type=\"text/javascript\">" + "window.parent.CKEDITOR.tools.callFunction("
					+ callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');" + "</script>";
			return returnString;
		} else {
			// 文件上传并获取地址
			String attachmentUrl = fileUpLoadService.uploadAttachment(attachment, user);
			///
			// String url =
			/// "http://documents-oss.oss-cn-qingdao.aliyuncs.com/11/chat2you.club_cert.jpg";
			String returnString = "<script type=\"text/javascript\">" + "window.parent.CKEDITOR.tools.callFunction("
					+ callback + ",'" + attachmentUrl + "','')" + "</script>";
			return returnString;
		}

	}
	//editArticleLabel
	//分类编辑页面请求
    @RequestMapping("/editArticleLabelRequest")
    public String editArticleLabelRequest(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws Exception {
    	log.info("分类编辑页面请求");
    	Iuser user = (Iuser) session.getAttribute("user");
		if(user==null) {
			log.info("分类编辑页面请求失败，用户未登录");
			return "redirect:/login";
		}
		
    	//用户存在
    	List<IarticleLabel> iarticleLabelList = iarticleLabelBo.getIarticleLabelListByUserId(user.getId());	
    	request.setAttribute("IarticleLabelList", iarticleLabelList);
		return "/label/editArticleLabel";
    }	
	//editArticleLabel
	//分类编辑
	/*
	 * editType:add:添加，change：更改，delete：删除
	 */
    @RequestMapping("/editArticleLabel")
    public void editArticleLabel(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws Exception {
    	log.info("分类编辑请求");
    	Iuser user = (Iuser) session.getAttribute("user");
		if(user==null) {
			throw new Exception("用户未登录");
		}
		
    	//用户存在
		
		//
		String editType = request.getParameter("editType");
    	log.info("请求类型："+editType);
		//添加
		if("add".equals(editType)) {
			//获取标签名
			String articleLabelName = request.getParameter("articleLabelName");
			
			IarticleLabel iarticleLabel = new IarticleLabel();
			iarticleLabel.setArticleNum(0);
			iarticleLabel.setName(articleLabelName);
			iarticleLabel.setUserId(user.getId());
			iarticleLabelBo.addArticleLabel(iarticleLabel);
			log.info("新加标签写入数据库："+iarticleLabel.getName());
		}else if("change".equals(editType)) {
			//更改
			//获取原标签ID，和要改的标签名
			String articleLabelId = request.getParameter("articleLabelId");
			String newArticleLabelName = request.getParameter("newArticleLabelName");
			
			IarticleLabel iarticleLabel = iarticleLabelBo.getArticleLabel(Integer.valueOf(articleLabelId));
			iarticleLabel.setName(newArticleLabelName);
			iarticleLabelBo.updateArticleLabel(iarticleLabel);
			log.info("更新加标签写入数据库："+iarticleLabel.getName());
		}else if("delete".equals(editType)) {
			//删除
			//获取标签ID
			String articleLabelId = request.getParameter("articleLabelId");
			iarticleLabelBo.deletArticleLabel(Integer.valueOf(articleLabelId));
		}else {
			PrintWriter writer = response.getWriter();
			writer.print("未知的请求参数类型editType："+editType);
			writer.flush();
			writer.close();
		}

    	
		PrintWriter writer = response.getWriter();
		writer.print("successful");
		writer.flush();
		writer.close();
    }
}
