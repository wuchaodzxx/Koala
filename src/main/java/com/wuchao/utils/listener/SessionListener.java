package com.wuchao.utils.listener;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wuchao.blog.system.bo.intf.IsystemBo;
import com.wuchao.blog.system.po.Isystem;
import com.wuchao.blog.user.controller.LoginController;
import com.wuchao.utils.config.SpringContextHolder;
import com.wuchao.utils.exception.DAOException;

public class SessionListener implements HttpSessionListener {

	public SpringContextHolder  springContextHolder;
	

	
	private static Logger log = Logger.getLogger(LoginController.class);  
	
	private HttpSession session;
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.info("sessionCreated");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		log.info("sessionDestroyed 被执行");  	
	}
	/**
	 * 通过WebApplicationContextUtils 得到Spring容器的实例。根据bean的名称返回bean的实例。
	 * 
	 * @param servletContext
	 *            ：ServletContext上下文。
	 * @param beanName
	 *            :要取得的Spring容器中Bean的名称。
	 * @return 返回Bean的实例。
	 */
	private static Object getObjectFromApplication(ServletContext servletContext, String beanName) {
		// 通过WebApplicationContextUtils 得到Spring容器的实例。
		ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		// 返回Bean的实例。
		return application.getBean(beanName);
	}

}
