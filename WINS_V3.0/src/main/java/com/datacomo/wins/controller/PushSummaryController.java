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

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.PushSummaryService;
import com.datacomo.wins.service.TestService;

/**
 * @author gongkaihui
 *
 *	
 */

@Controller
@RequestMapping(value="/summary")//url映射方法前缀
public class PushSummaryController extends BaseController{
	private static Logger logger = Logger.getLogger(PushSummaryController.class.getName());
	@Autowired
	PushSummaryService pushSummaryService;
	@Value("${head_uri}}")
	private String jdbc;
	
	@ResponseBody
	@RequestMapping(value="getBaseIndex")
	public ResultModel getBaseIndex(String day) {
		logger.info("PushSummaryController getBaseIndex method start");
		try {
			this.getCondition().put("reportDate", day);
			this.getCondition().put("showStatus", this.getSession().getAttribute("showStatus"));
			this.getCondition().put("provinceId", this.getSession().getAttribute("provinceId"));
			this.getCondition().put("cityId", this.getSession().getAttribute("cityId"));
			Map<String,Object> result = pushSummaryService.getPushBaseDataByCondition(this.getVisitId(), this.getCondition());
			System.out.println("result-------------------------------getData----------------------------: "+result);
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		
		logger.info("PushSummaryController getBaseIndex method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="fundReport")
	public ResultModel findPushAndShowAndClickNumByCondition(String day) {
		logger.info("PushSummaryController findPushAndShowAndClickNumByCondition method start");
		try {
			this.getCondition().put("reportDate", day);
			this.getCondition().put("showStatus", this.getSession().getAttribute("showStatus"));
			this.getCondition().put("provinceId", this.getSession().getAttribute("provinceId"));
			this.getCondition().put("cityId", this.getSession().getAttribute("cityId"));
			Map<String,Object> result = pushSummaryService.findPushAndShowAndClickNumByCondition(this.getVisitId(), this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("PushSummaryController findPushAndShowAndClickNumByCondition method end");
		return this.getModel();
	}
	
	
}
