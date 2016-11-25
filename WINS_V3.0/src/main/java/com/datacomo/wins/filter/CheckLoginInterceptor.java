/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2010-2013 DataComo Communications Technology INC.
 * 
 * This source file is a part of wins-api project. 
 * date: 2013-11-12
 *
 */
package com.datacomo.wins.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.datacomo.wins.service.SysUserInfoService;
import com.datacomo.wins.util.CookiesUtil;
import com.datacomo.wins.util.MD5;

/**
 * 验证是否登录拦截器
 * 
 * @author zhaizhanpo
 * @update developer zhaizhanpo
 * @version v1.0.0
 */
public class CheckLoginInterceptor implements HandlerInterceptor{

	private static Logger logger = Logger.getLogger(CheckLoginInterceptor.class.getName());
	@Autowired
	private SysUserInfoService sysUserInfoService;
	@Override
	public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		logger.info("login method start");
		boolean flag=true;
		//用户登陆拦截
		
		String uri=request.getRequestURI();
		String cokLoginName  = CookiesUtil.getOtherCookie(request, response,"WIN_LOGIN_NAME");
		String cokLoginPwd  = CookiesUtil.getOtherCookie(request, response,"WIN_LOGIN_PASS");
		/*if(uri.indexOf("resetPwdUI") !=-1 || uri.indexOf("forgetPwdUI") !=-1 || uri.indexOf("login") !=-1){
			return true;
		}*/
		if(uri.indexOf("resetPwdUI") !=-1 || uri.indexOf("forgetPwdUI") !=-1|| uri.indexOf("login") !=-1){
			return true;
		}
		if(StringUtils.isNotBlank(cokLoginName) && StringUtils.isNotBlank(cokLoginName) && uri.indexOf("index.html")!=-1){
			return true;
		}
		if(request.getSession().getAttribute("userId") ==null){
			 if(autoLogin(request, response)==1){
				 return true;
			 }else{
				 response.sendRedirect("login");
				 return false;
			 }
		}
		return flag;
	}
	public int autoLogin(HttpServletRequest request, HttpServletResponse response){
		String userName  = CookiesUtil.getOtherCookie(request, response,"WIN_LOGIN_NAME");
		String userPwd  = CookiesUtil.getOtherCookie(request, response,"WIN_LOGIN_NAME");
		if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(userPwd)){
			if(userName.length()==0||userPwd.length()==0){
				return 0;
			}
		}
		
		if(userName !=null && userPwd!=null){
			try {
					Map<String,Object> map=new HashMap<String,Object>();
					Map<String,Object> login=new HashMap<String,Object>();
					map.put("username", userName);
					map.put("password", MD5.Md5(userPwd));
					Map<String, Object> result=sysUserInfoService.findSysUserInfoByCondtion(0, 0, map);
					if(result != null && result.size()>0){
						request.getSession().setAttribute("userId",result.get("userId"));
						request.getSession().setAttribute("roleType",result.get("roleType"));
						request.getSession().setAttribute("userName",result.get("userName"));
						request.getSession().setAttribute("username", result);
						request.getSession().setAttribute("provinceId",result.get("provinceId"));
						request.getSession().setAttribute("roleId", result.get("roleId"));
						request.getSession().setAttribute("showStatus", result.get("showStatus"));
						login.put("loginTime", new Date());
						sysUserInfoService.updateInfo(0, login, 0);
						request.getSession().setAttribute("cityId", result.get("cityId"));
						String uri=request.getRequestURI();
						if(uri.indexOf("login") !=-1){
							response.sendRedirect("index.html");
						}
						return 1;
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
}
