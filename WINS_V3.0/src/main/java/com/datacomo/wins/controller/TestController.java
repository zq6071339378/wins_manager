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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.datacomo.wins.service.SysIndustryService;
import com.datacomo.wins.service.SysInterestLabelService;
import com.datacomo.wins.util.ApiConfigUtil;
import com.datacomo.wins.util.ApiRequestUtil;
import com.datacomo.wins.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.TestService;

/**
 * @author zhaizhanpo
 *
 *	用户基本信息控制类
 */

@Controller
@RequestMapping(value="/test")//url映射方法前缀
public class TestController extends BaseController{
	private static Logger logger = Logger.getLogger(TestController.class.getName());
	@Autowired
	TestService testService;
	@Value("${head_uri}}")
	private String jdbc;
	@Autowired
	SysIndustryService sysIndustryService;
	@Autowired
	SysInterestLabelService sysInterestLabelService;

	@ResponseBody
	@RequestMapping(value="list")//实际访问/http://localhost:8080/DSP_V1.0/test/list
	public ResultModel list() {
		logger.info("TestController list method start");
		try {
			logger.info("jdbc:"+jdbc);
			Map<String,Object> result = testService.findTestsByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		
		logger.info("TestController list method end");
		return this.getModel();
	}
	
	/*@RequestMapping(value="listView")//实际访问 http://localhost:8080/DSP_V1.0/test/listView
	public String listView() {
		logger.info("TestController list method start");
		try {
			Map<String,Object> result = testService.findTestsByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("result", result);
		} catch (ServiceException e) {
			this.getRequest().setAttribute("result", null);
		}
		
		logger.info("TestController list method end");
		return "user/list";
	}*/

	/**
	 * 行业标签入库测试
	 */
	/*@RequestMapping(value="industry")
	public void testIndustry(){
			int result = 0;
			try {
				result = sysIndustryService.updateIndustryLabel();
			} catch (ServiceException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}*/



	/**
	 * 媒体标签入库测试
	 */
	/*@RequestMapping(value="media")
	public void testMedia(){
		int result = 0;
		try {
			result = sysIndustryService.updateMediaLabel();
			System.out.println("test---result----------------------------: "+result);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}*/

	@RequestMapping(value="interest")
	public void testInterest() {
		int result = 0;
		try {
			result = sysInterestLabelService.updateAllInterestLabel();
			System.out.println("test---result----------------------------: " + result);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}


	@RequestMapping(value="aaaaaa")
	public void testUsers(){
		int result = 0;
		try {
			result = sysInterestLabelService.updateAllUserByInterestLabel();
			System.out.println("test---result----------------------------: "+result);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

}


