package com.wuchao.utils.exception;

import org.apache.log4j.Logger;

public class BaseException extends Exception {
	protected Logger log;
	private Exception exception;
	private String detailMessage = null;
	private int errorLevel = 0;
	
	protected static final int BASELEVEL = 0;
	protected static final int DAOLEVEL = 1;
	protected static final int SERVICELEVEL = 2;
	protected static final int UILEVEL = 3;
	protected static final int UTILLEVEL = 4;
	
	protected static final String BASEERRORINFO = "基础错误！";
	protected static final String DAOERRORINFO = "数据库操作错误！";
	protected static final String SERVICEERRORINFO = "业务逻辑处理错误！";
	protected static final String UIERRORINFO = "表现层错误！";
	protected static final String UTILERRORINFO = "公用组件访问错误！";
	
	
	public String getMessage(){
		return detailMessage;
	}
	/**
	 * ����
	 * @param e
	 */
	public void setSuperException(BaseException e){
		setException(e.getException());
		setErrorLevel(e.getErrorLevel());
		switch (e.getErrorLevel()) {
			case BASELEVEL :
				setDetailMessage(BASEERRORINFO);
				break;
			case DAOLEVEL :
				setDetailMessage(DAOERRORINFO);
				break;
			case SERVICELEVEL :
				setDetailMessage(SERVICEERRORINFO);
				break;
			case UILEVEL :
				setDetailMessage(UIERRORINFO);
				break;
			case UTILLEVEL :
				setDetailMessage(UTILERRORINFO);
				break;
		}
	}
	
	protected void printErrorStackTrace(Logger log,Exception e){
		log.error(e.toString());
		StackTraceElement[] stackTrace = e.getStackTrace();
		for(int i = 0;i < stackTrace.length;i++){
			log.error(stackTrace[i].toString());
		}
	}

	/**
	 * @return Returns the errorLevel.
	 */
	public final int getErrorLevel() {
		return errorLevel;
	}
	/**
	 * @param errorLevel The errorLevel to set.
	 */
	public final void setErrorLevel(int errorLevel) {
		this.errorLevel = errorLevel;
	}
	/**
	 * @return Returns the exception.
	 */
	public final Exception getException() {
		return exception;
	}
	/**
	 * @param exception The exception to set.
	 */
	public final void setException(Exception exception) {
		this.exception = exception;
	}
	/**
	 * @return Returns the detailMessage.
	 */
	public final String getDetailMessage() {
		return detailMessage;
	}
	/**
	 * @param detailMessage The detailMessage to set.
	 */
	public final void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}
	public static void main(String[] args) {
	}
}
