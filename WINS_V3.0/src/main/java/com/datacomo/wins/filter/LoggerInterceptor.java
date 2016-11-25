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
package com.datacomo.wins.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 日志管理，方法执行时间
 * @author zhaizhanpo
 * @version v1.0.0
 */
public class LoggerInterceptor implements HandlerInterceptor {

	private static Logger logger = Logger.getLogger(LoggerInterceptor.class.getName());
	/**
	 * 开始时间
	 */
	private long startTime;
	/**
	 * 结束时间
	 */
	private long endTime;
	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		startTime = System.currentTimeMillis();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		endTime = System.currentTimeMillis();
		//记录日志
		@SuppressWarnings("unused")
		Map<String,String []> map = request.getParameterMap();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("ip="+request.getRemoteAddr());
		sb.append("|");
		sb.append("excute_time=" + (double)(endTime-startTime)/1000);
		
		logger.info(sb.toString());
		
	}
}
