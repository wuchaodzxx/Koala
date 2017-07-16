package com.wuchao.blog.article.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.wuchao.blog.user.controller.ManagerController;
import com.wuchao.utils.constant.SystemConstant;
import com.wuchao.utils.exception.DAOException;

@Controller
public class ArticleController {

	private static Logger log = Logger.getLogger(ManagerController.class);  
	
	@Resource(name="iarticleBo")
	public IarticleBo iarticleBo;
	
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
}
