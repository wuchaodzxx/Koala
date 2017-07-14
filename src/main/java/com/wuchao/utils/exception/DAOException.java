package com.wuchao.utils.exception;

import org.apache.log4j.Logger;

public class DAOException extends BaseException {
	/**
	 * 
	 */
	public DAOException() {
		super();
	}

	/**
	 * @param e
	 */
	public DAOException(Exception e) {
		if (e instanceof BaseException) {
			this.setSuperException((BaseException) e);
		} else {
			setException(e);
			setErrorLevel(DAOLEVEL);
			setDetailMessage(DAOERRORINFO);
			log = Logger.getLogger(DAOException.class);
			printErrorStackTrace(log, e);
		}
	}

	/**
	 * @param e
	 * @param className
	 */
	public DAOException(Exception e, String className) {
		if (e instanceof BaseException) {
			this.setSuperException((BaseException) e);
		} else {
			setException(e);
			setErrorLevel(DAOLEVEL);
			setDetailMessage(DAOERRORINFO);
			log = Logger.getLogger(className);
			printErrorStackTrace(log, e);
		}
		// log.error(e.getStackTrace().toString());
	}

	/**
	 * @param e
	 * @param c
	 */
	public DAOException(Exception e, Class c) {
		if (e instanceof BaseException) {
			this.setSuperException((BaseException) e);
		} else {
			setException(e);
			setErrorLevel(DAOLEVEL);
			setDetailMessage(DAOERRORINFO);
			log = Logger.getLogger(c);
			printErrorStackTrace(log, e);
		}
		// log.error(e.getStackTrace().toString());
	}

}

