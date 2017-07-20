package com.wuchao.blog.comment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
import com.wuchao.blog.comment.bo.intf.IarticleCommentBo;
import com.wuchao.blog.comment.po.IarticleComment;
import com.wuchao.blog.user.bo.intf.IuserBo;
import com.wuchao.blog.user.controller.ManagerController;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.constant.SystemConstant;
import com.wuchao.utils.exception.DAOException;

@Controller
public class CommentController {
	private static Logger log = Logger.getLogger(ManagerController.class);  
	
	@Resource(name="iarticleBo")
	public IarticleBo iarticleBo;
	
	@Resource(name="iuserBo")
	public IuserBo iuserBo;
	
	@Resource(name="iarticleLabelBo")
	public IarticleLabelBo iarticleLabelBo;
	
	@Resource(name="iarticleCommentBo")
	public IarticleCommentBo iarticleCommentBo;
	
	/*
	 * 提交评论
	 */
	@RequestMapping(value="/articleCommentSubmit", method = {RequestMethod.POST, RequestMethod.GET})
	public void home(HttpServletResponse response, HttpServletRequest request,
			HttpSession session) throws DAOException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 访问主页面，先判断是否传入userID参数，如果传入了，则访问指定用户的index.jsp，否则默认访问本人的index.jsp
		log.info("提交评论");
		Iuser user = (Iuser) session.getAttribute("user");
		PrintWriter out = response.getWriter();
    	//用户不存在
    	if(user==null) {
    		log.info("评论提交失败，用户未登录！");
    		out.print("用户未登录，请登录后再执行此操作！");
    		out.flush();
    		out.close();
    		return;
    	}
		String articleId = request.getParameter("articleId");
		String content = request.getParameter("content");
		String commentTargetUserId = request.getParameter("commentTargetUserId");//可能为空

		try {
			Iarticle article = iarticleBo.getIarticleById(Integer.valueOf(articleId));
			article.setComments(article.getComments()+1);
			
			IarticleComment comment = new IarticleComment();
			comment.setArticleId(Integer.valueOf(articleId));
			comment.setCommentUserId(user.getId());
			comment.setContent(content);
			comment.setCommentDate(new Date());
			comment.setCommentUserNickName(user.getNickname());
			if(commentTargetUserId!=null) {
				Iuser targetUser = iuserBo.getIuserByUserId(Integer.valueOf(commentTargetUserId));
				comment.setCommentTargetUserId(Integer.valueOf(commentTargetUserId));
				comment.setCommentTargetUserNickName(targetUser.getNickname());
			}
			iarticleBo.updateArticle(article);
			iarticleCommentBo.addIarticleComment(comment);
		}catch(Exception e) {
			e.printStackTrace();
			out.print("内部错误");
			out.flush();
			out.close();
			log.info("提交评论失败");
			return;
		}
		log.info("提交评论成功");
		out.print("success");
		out.flush();
		out.close();

	}
}
