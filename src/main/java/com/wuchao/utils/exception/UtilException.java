package com.wuchao.utils.exception;

import org.apache.log4j.Logger;

import com.wuchao.test.product.controller.ProductController;

public class UtilException extends BaseException {
	public UtilException(){
	}
	public UtilException(Exception e){
		if(e instanceof BaseException){
			this.setSuperException((BaseException)e);
		}else{
			setException(e);
			setErrorLevel(UTILLEVEL);
			setDetailMessage(UTILERRORINFO);
			log = Logger.getLogger(UtilException.class);
			printErrorStackTrace(log,e);
		}
	}
	public UtilException(Exception e, String className){
		if(e instanceof DAOException){
			this.setSuperException((BaseException)e);
		}else{
			setException(e);
			setErrorLevel(UTILLEVEL);
			setDetailMessage(UTILERRORINFO);
			log = Logger.getLogger(className);
			printErrorStackTrace(log,e);
//			log.error(e.getStackTrace().toString());
		}
	}
	public UtilException(Exception e, Class c){
		if(e instanceof DAOException){
			this.setSuperException((BaseException)e);
		}else{
			setException(e);
			setErrorLevel(UTILLEVEL);
			setDetailMessage(UTILERRORINFO);
			log = Logger.getLogger(c);
			printErrorStackTrace(log,e);
		}
	}
}
