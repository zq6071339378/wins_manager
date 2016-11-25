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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.AreaMngService;
import com.datacomo.wins.service.SysCityInfoService;
import com.datacomo.wins.service.SysDistrictInfoService;
import com.datacomo.wins.service.SysHotpointInfoService;
import com.datacomo.wins.service.SysLogRecordService;
import com.datacomo.wins.service.SysProvinceInfoService;
import com.datacomo.wins.service.TestService;
import com.datacomo.wins.util.DateUtil;
import com.datacomo.wins.util.IpConvertUtil;

/**
 * @author gongkaihui
 *
 *	用户基本信息控制类
 */

@Controller
@RequestMapping(value="/area")//url映射方法前缀
public class AreaMngController extends BaseController{
	private static Logger logger = Logger.getLogger(AreaMngController.class.getName());
	@Autowired
	SysProvinceInfoService sysProvinceInfoService;
	@Autowired
	SysCityInfoService sysCityInfoService;
	@Autowired
	SysDistrictInfoService sysDistrictInfoService;
	@Autowired
	SysHotpointInfoService sysHotpointInfoService;
	@Autowired
	AreaMngService areaMngService;
	@Autowired
	SysLogRecordService sysLogRecordService;
	
	@ResponseBody
	@RequestMapping(value="province")
	public ResultModel getProvincelist(String provinceId,String cityId,String districtId) {
		logger.info("AreaMngController getProvincelist method start");
		try {
			Map m=new HashMap();
			Map<String,Object> result = sysProvinceInfoService.findProvinceList(this.getVisitId(), this.getVisitId(), this.getCondition());
			m.put("pro", result);
			/*if(result != null){
					List list=(List)result.get("provinces");
					Map map=(Map)list.get(0);
					this.getCondition().put("provinceId", map.get("provinceId"));
			}*/
			if(StringUtils.isNotBlank(provinceId)){
				this.getCondition().put("provinceId", provinceId);
			}
			else{
				this.getCondition().put("provinceId", 1);	
			}
			Map<String,Object> city = sysCityInfoService.findCityList(this.getVisitId(), this.getVisitId(), this.getCondition());
			m.put("city", city);
			if(StringUtils.isNotBlank(cityId)){
				this.getCondition().put("cityId", cityId);
			}
			else{
				this.getCondition().put("cityId", 1);	
			}
			Map<String,Object> district = sysDistrictInfoService.findDistrictList(this.getVisitId(), this.getVisitId(), this.getCondition());
			m.put("district", district);
			if(StringUtils.isNotBlank(districtId)){
				this.getCondition().put("districtId", districtId);
			}
			else{
				this.getCondition().put("districtId", 1);	
			}
			Map<String,Object> hotpoint =sysHotpointInfoService.findHotpointList(this.getVisitId(), this.getVisitId(), this.getCondition());
			m.put("hotpoint", hotpoint);
			this.getModel().setResult(m);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("AreaMngController getProvincelist method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="AllPro")
	public ResultModel AllPro() {
		logger.info("AreaMngController AllPro method start");
		try {
			Map<String,Object> result = sysProvinceInfoService.findProvinceList(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		
		logger.info("AreaMngController AllPro method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="delete")
	public ResultModel delete(int areaId) {
		logger.info("AreaMngController delete method start");
		String ip=this.getRequest().getRemoteAddr();
        int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try {
			areaMngService.deleteAreaById(areaId);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
			sysLogRecordService.insertLogInfo(userId, "delete", 1, "delete the area", ip, DateUtil.getDateString());
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
			try {
				sysLogRecordService.insertLogInfo(userId, "delete", 2, "abnormal operation", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		logger.info("AreaMngController delete method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="add")
	public ResultModel addArea() {
		logger.info("AreaMngController addArea method start");
		String ip=this.getRequest().getRemoteAddr();
        int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try {
			this.getCondition().put("provinceId", this.getRequest().getParameter("provinceId"));
			this.getCondition().put("cityId", this.getRequest().getParameter("cityId")==""?0:this.getRequest().getParameter("cityId"));
			this.getCondition().put("districtId", this.getRequest().getParameter("districtId")==""?0:this.getRequest().getParameter("districtId"));
			this.getCondition().put("hotpointId", this.getRequest().getParameter("hotpointId")==""?0:this.getRequest().getParameter("hotpointId"));
			this.getCondition().put("areaName", this.getRequest().getParameter("areaName"));
			this.getCondition().put("areaAddr", this.getRequest().getParameter("areaAddr"));
			String startIp=this.getRequest().getParameter("startIp");
			long sip=IpConvertUtil.ipToLong(startIp);
			this.getCondition().put("startIp", sip);
			String endIp= this.getRequest().getParameter("endIp");
			long eip=IpConvertUtil.ipToLong(endIp);
			this.getCondition().put("endIp",eip);
			this.getCondition().put("areaType",2);
			this.getCondition().put("createUser", this.getSession().getAttribute("userId"));
			this.getCondition().put("createTime", new Date());
			areaMngService.insertArea(this.getCondition());
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed!");
			sysLogRecordService.insertLogInfo(userId, "add", 1, "create the area", ip, DateUtil.getDateString());
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
			try {
				sysLogRecordService.insertLogInfo(userId, "add", 2, "abnormal operation", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		logger.info("AreaMngController addArea method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="updateArea")
	public ResultModel updataeArea() {
		logger.info("AreaMngController addArea method start");
		String ip=this.getRequest().getRemoteAddr();
        int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("Province_Id", this.getRequest().getParameter("provinceId"));
			params.put("City_Id", this.getRequest().getParameter("cityId")==""?0:this.getRequest().getParameter("cityId"));
			params.put("District_Id", this.getRequest().getParameter("districtId")==""?0:this.getRequest().getParameter("districtId"));
			params.put("Hotpoint_Id", this.getRequest().getParameter("hotpointId")==""?0:this.getRequest().getParameter("hotpointId"));
			params.put("Area_Name", this.getRequest().getParameter("areaName"));
			params.put("Area_Addr", this.getRequest().getParameter("areaAddr"));
			String startIp=this.getRequest().getParameter("startIp");
			long sip=IpConvertUtil.ipToLong(startIp);
			String endIp= this.getRequest().getParameter("endIp");
			long eip=IpConvertUtil.ipToLong(endIp);
			params.put("Start_Ip", sip);
			params.put("End_Ip", eip);
			this.getCondition().put("areaId", this.getRequest().getParameter("areaId"));
			this.getCondition().put("condition", params);
			areaMngService.updateAreaById(this.getVisitId(), this.getCondition(), this.getVisitId());
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed!");
			sysLogRecordService.insertLogInfo(userId, "edit", 1, "edit the group", ip, DateUtil.getDateString());
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
			try {
				sysLogRecordService.insertLogInfo(userId, "edit", 2, "abnormal operation", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		logger.info("AreaMngController addArea method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="/getAreaById")
	public ResultModel getAreaById(Integer areaId,ModelMap model) {
		logger.info("index  getAreaById method start");
		try {
			Map m=new HashMap();
			Map<String, Object> area = areaMngService.getAreaById(this.getVisitId(), areaId);
			m.put("area", area);
			Map<String,Object> result = sysProvinceInfoService.findProvinceList(this.getVisitId(), this.getVisitId(), this.getCondition());
			m.put("pro", result);
			
			if(area != null){
			this.getCondition().put("provinceId", area.get("provinceId"));
			this.getCondition().put("cityId", area.get("cityId"));
			this.getCondition().put("districtId", area.get("districtId"));
			}
			Map<String,Object> city = sysCityInfoService.findCityList(this.getVisitId(), this.getVisitId(), this.getCondition());
			m.put("citys", city);
			Map<String,Object> district = sysDistrictInfoService.findDistrictList(this.getVisitId(), this.getVisitId(), this.getCondition());
			m.put("districts", district);
			Map<String,Object> hotpoint =sysHotpointInfoService.findHotpointList(this.getVisitId(), this.getVisitId(), this.getCondition());
			m.put("hotpoints", hotpoint);
//			if(StringUtils.isNotBlank(cityId)){
//				this.getCondition().put("cityId", cityId);
//			}districtId
//			else{
//				this.getCondition().put("cityId", 1);	
//			}
//			
			this.getModel().setResult(m);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("index getAreaById method end");
		return  this.getModel();
	}
}
