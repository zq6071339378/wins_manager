/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2006-2016 DataComo Communications Technology INC.
 * 
 * This source file is a part of DSPV1.0 project. 
 * date: 2016-02-25
 *
 */
package com.datacomo.wins.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 异常处理类 Service 层
 * 
 * @author zhaizhanpo
 *
 */
public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	Log logger = LogFactory.getLog(ServiceException.class);

	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(){
		super();
	}
	
	public ServiceException(Throwable cause){
		super(cause);
	}
	
	public ServiceException(String message,Throwable cause){
		super(message);
	}
	
}
