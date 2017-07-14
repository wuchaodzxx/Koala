package com.wuchao.utils.interceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.wuchao.blog.system.bo.intf.IsystemBo;
import com.wuchao.blog.system.po.Isystem;
import com.wuchao.blog.user.controller.LoginController;
import com.wuchao.utils.config.SpringContextHolder;

public class RequestInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = Logger.getLogger(LoginController.class);  
	
	@Resource(name="isystemBo")
	public IsystemBo isystemBo;	
	@Resource(name="springContextHolder")
	SpringContextHolder  springContextHolder;
	
	public static Isystem isystem;

	//请求控制器前，处理请求
	@Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
		try {
			log.info("RequestInterceptor");
			//网站访问量+1
			if(isystem==null) {
				log.info("isystem==null");
				isystem = isystemBo.getIsystemByDefault();
			}
			if(isystem!=null) {
				//每一个session理论上只能记一次访问，因此在session里面存一个访问标记，如果存在标记，则不再计算此次访问
				String accessedFlag = "accessedFlag";
				if(request.getSession().getAttribute("accessedFlag")==null) {
					//同步锁
					synchronized(this) {
						log.info("网站访问量+1,存入session");
						isystem.setAmountOfAccess(isystem.getAmountOfAccess()+1);
						request.getSession().setAttribute("Isystem", isystem);
						request.getSession().setAttribute("accessedFlag", accessedFlag);
					}
					
				}
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

        return true;
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	/*
	 * 实例化时执行的操作
	 */
	@PostConstruct  
    public void initMethod() throws Exception {  
        log.info("initMethod 被执行");  
        //加载isystem对象
		if(isystem==null) {
			log.info("加载isystem");
			isystem = isystemBo.getIsystemByDefault();
		}
    } 
	/*
	 * 销毁前执行的操作
	 */
    @PreDestroy  
    public void destroyMethod() throws Exception {  
    	log.info("destroyMethod 被执行");  
    	//保存isystem
    	if(isystem!=null) {
    		isystemBo.saveIsystem(isystem);
    	}
    }  
}
