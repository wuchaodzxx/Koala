package com.wuchao.blog.user.controller;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wuchao.blog.article.bo.intf.IarticleBo;
import com.wuchao.blog.article.po.Iarticle;
import com.wuchao.blog.category.bo.intf.IarticleLabelBo;
import com.wuchao.blog.category.po.IarticleLabel;
import com.wuchao.blog.user.bo.intf.IuserBo;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.constant.SystemConstant;
import com.wuchao.utils.exception.DAOException;
import com.wuchao.utils.page.Page;

@Controller
public class BrowserController {
	private static Logger log = Logger.getLogger(ManagerController.class);  
	
	@Resource(name="iuserBo")
	public IuserBo iuserBo;
	
	@Resource(name="iarticleBo")
	public IarticleBo iarticleBo;
	
	@Resource(name="iarticleLabelBo")
	public IarticleLabelBo iarticleLabelBo;
    /*
     * 请求index.jsp页面
     */
    @RequestMapping("/{userName}/home")
    public String home(@PathVariable String userName,
    					HttpServletResponse response,
    					HttpServletRequest request,
    					HttpSession session) throws DAOException {

    	log.info("请求目标用户："+userName);
    	
    	int pageSize = SystemConstant.PAGE_SIZE;//每页显示数目
    	int currentPage = 1;//默认当前页
    	int totalPages = 1;//默认总页数
    	
    	if(request.getParameter("currentPage")!=null) {
    		currentPage = Integer.parseInt((String) request.getParameter("currentPage"));
    	}
    	if(request.getParameter("totalPages")!=null) {
    		totalPages = Integer.parseInt((String) request.getParameter("totalPages"));
    	}else {
    		int totalRowCount = iarticleBo.getAllRowCount(userName);
    		totalPages = (int) Math.ceil(totalRowCount/(double)pageSize);//向上取整
    	}
    	log.info("请求参数：totalPages="+totalPages+",currentPage="+currentPage+",pageSize="+pageSize);
    	
    	Page page = iarticleBo.queryPageByUserName(userName,Integer.valueOf(currentPage), pageSize);
    	
    	//获取目标用户信息，便于index.jsp展示
    	Iuser targetUser = iuserBo.getIuserByUsername(userName);
        
    	//获取目标用户的标签列表
    	List<IarticleLabel> articleLabelList = iarticleLabelBo.getIarticleLabelListByUserId(targetUser.getId());
    	
        List<Iarticle> articleList = page.getList();
        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("articleList", articleList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        
        
    	//把目标用户名放入request,目标用户是指第三方访问的页面所属用户
        request.setAttribute("TargetUserName", userName);
        
        request.setAttribute("TargetUser", targetUser);
        
        request.setAttribute("ArticleLabelList", articleLabelList);
        
    	return "index";
    }
}
