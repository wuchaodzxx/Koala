package com.wuchao.utils.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

/*
 * 
 * 获取全局对象ApplicationContext
 */
@Repository("springContextHolder")
public class SpringContextHolder implements ApplicationContextAware {
	private static ApplicationContext applicationContext;  
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;  
	}
	public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  

}
