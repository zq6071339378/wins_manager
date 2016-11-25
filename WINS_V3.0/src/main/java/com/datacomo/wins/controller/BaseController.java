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
package com.datacomo.wins.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.bean.ResultModel;

/**
 * 基础控制类
 * 
 * @author zhaizhanpo
 * @update developer zhaizhanpo
 * @version v1.0.0
 */
public class BaseController {
	
	private static Logger logger = Logger.getLogger(BaseController.class.getName());

	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse reponse;
	private int visitId;
	private Map<String,Object> condition;
	private ResultModel model;
	
	/**
	 * @return the model
	 */
	public ResultModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(ResultModel model) {
		this.model = model;
	}

	@ModelAttribute
	public void beforeInvokingHandHttplerMethod(HttpServletRequest request,HttpServletResponse reponse,HttpSession session) {
		if(logger.isDebugEnabled()){
			logger.debug("BaseController start");
		}
		this.reponse = reponse;
		this.request = request;
		this.session = session;
		
		//分页
		int limit = this.getRequest().getParameter("limit") == null ? 10 : Integer.parseInt(this.getRequest().getParameter("limit"));
		int start = this.getRequest().getParameter("start") == null ? 0 : Integer.parseInt(this.getRequest().getParameter("start"));
		model = new ResultModel();
		pagination = new Pagination();
		condition = new HashMap<String,Object>();
		this.pagination.setStart(start);
		this.pagination.setLimit(limit);
		condition.put("page", pagination);
		condition.put("visitId", visitId);
		if(logger.isDebugEnabled()){
			logger.debug("BaseController end");
		}
		Object userId = session.getAttribute("userId");
		if(userId!=null){
			this.visitId=Integer.parseInt(userId.toString());
		}
	}

	/**
	 * @return the condition
	 */
	public Map<String, Object> getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	/**
	 * @return the visitId
	 */
	public int getVisitId() {
		return visitId;
	}

	/**
	 * @param visitId the visitId to set
	 */
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	
	/**
	 * page
	 */
	private Pagination pagination;
	
	
	
	/**
	 * @return the reponse
	 */
	public HttpServletResponse getReponse() {
		return reponse;
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}
	
	
	/** 获取HttpServletResponse */  
	public  HttpServletResponse getResponse() {  
	    return this.reponse;  
	}  
	  
	/** 获取HttpServletRequest */  
	public  HttpServletRequest getRequest() {  
	    return this.request;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	

}
