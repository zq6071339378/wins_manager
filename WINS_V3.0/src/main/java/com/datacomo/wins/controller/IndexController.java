/**
 * 
 */
package com.datacomo.wins.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.*;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.DocFlavor;

/**
 * @author zhaizhanpo
 *
 */
@Controller
public class IndexController extends BaseController {
	private static Logger logger = Logger.getLogger(IndexController.class.getName());
	@Autowired
	NavInfoService navInfoService;
	@Autowired
	PolicyGroupMngService policyGroupMngService;
	@Autowired
	AccountManageService accountManageService;
	@Autowired
	SysRoleInfoService sysRoleInfoService;
	@Autowired
	PushSummaryService pushSummaryService;
	@Autowired
	SysLogRecordService sysLogRecordService;
	@Autowired
	SysNewsInfoService sysNewsInfoService;
	@Autowired
	SysTaskService sysTaskService;
	@Autowired
	AreaMngService areaMngService;
	@Autowired
	SysProvinceInfoService sysProvinceInfoService;
	@Autowired
	CustomerManageService customerManageService;
	@Autowired
	ActivityManageService activityManageService;
	@Autowired
	IPSManageService ipsManageService;
	@Autowired
	AdnetworkService adnetworkService;


	@RequestMapping(value="/index.html")//实际访问 
	public String index() {
		logger.info("index method start");
		try {
			logger.info("index  method start");
			int userId = Integer.parseInt(String.valueOf(this.getSession().getAttribute("userId")));
			Map<String,Object> condition=new HashMap<String,Object>();
			condition.put("roleId", this.getRequest().getSession().getAttribute("roleId"));
			condition.put("roleType", this.getRequest().getSession().getAttribute("roleType"));
			condition.put("parentId", "0");
			Map<String,Object> navResult = new HashMap<String,Object>();
			boolean isEnglish=Boolean.valueOf(Config.message.get("isEnglish").toString());
			if(isEnglish){
				navResult = navInfoService.findNavByRoleIdAndParentId(0, 0, condition);
			}else{
				navResult = navInfoService.findNavByRoleIdAndParentIdZh(0, 0, condition);
			}
			this.getRequest().setAttribute("navResult", navResult);
			//获取未读消息
			Map<String,Object> notReadNews = sysNewsInfoService.getNotReadInfos(this.getVisitId(),this.getCondition());
			this.getRequest().setAttribute("notReadNews",notReadNews);
			//获取用户归属地市信息
			this.getCondition().put("userId",userId);
			Map<String,Object> belongCityInfo = accountManageService.getBelongCityInfo(userId,this.getCondition());
			this.getRequest().setAttribute("belongCityInfo",belongCityInfo);
		}catch (Exception e) {
		}
		logger.info("index list method end");
		return "index";
	}
	//跳转到登录
	@RequestMapping(value="/login")
	public String login(){
		logger.info("SysUserInfoController login method start");
		logger.info("SysUserInfoController login method end");
		return "user/login";
	}
	@RequestMapping(value="/policyGroup.html")//实际访问 
	public String policyGroupList(ModelMap model) {
		logger.info("index  policyGroupList method start");
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		this.getCondition().put("userId", userId);
		String groupName=this.getRequest().getParameter("groupName");
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		String cityId = "";
		if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
			cityId = this.getSession().getAttribute("cityId").toString();
			this.getCondition().put("cityId", cityId);
		}
		try {
			if(StringUtils.isNotBlank(groupName)){
				this.getCondition().put("groupName", groupName);
				model.addAttribute("groupName", groupName);
			}
			this.getCondition().put("showStatus", this.getSession().getAttribute("showStatus"));
			Map<String,Object> result = policyGroupMngService.findGroupList(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("result", result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (Exception e) {
			this.getRequest().setAttribute("result", null);
		}
		logger.info("index policyGroupList method end");
		return "push/groupList";
	}
	@RequestMapping(value="/area.html")//实际访问 
	public String findpolicyAreaList(ModelMap model) {
		logger.info("index  findpolicyAreaList method start");
		String areaName=this.getRequest().getParameter("areaName");
		String provinceId=this.getRequest().getParameter("provinceId");
		String provinceName=this.getRequest().getParameter("provinceName");
		String cityId=this.getRequest().getParameter("cityId");
		String cityName=this.getRequest().getParameter("cityName");
		String areaId=this.getRequest().getParameter("areaId");
		String aName=this.getRequest().getParameter("areaName");
		String hotpointId=this.getRequest().getParameter("hotpointId");
		String hotpointName=this.getRequest().getParameter("hotpointName");
		String role_Id = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",role_Id);
		Map<String,Object> MenuRelation = null;
		try {
			if(StringUtils.isNotBlank(areaName)){
				this.getCondition().put("areaName", areaName);
				model.addAttribute("areaName", areaName);
			}
			if(StringUtils.isNotBlank(provinceId)){
				this.getCondition().put("provinceId", provinceId);
				model.addAttribute("provinceId", provinceId);
			}
			if(StringUtils.isNotBlank(provinceName)){
				this.getCondition().put("provinceName", provinceName);
				model.addAttribute("provinceName", provinceName);
			}
			if(StringUtils.isNotBlank(cityId)){
				this.getCondition().put("cityId", cityId);
				model.addAttribute("cityId", cityId);
			}
			if(StringUtils.isNotBlank(cityName)){
				this.getCondition().put("cityName", cityName);
				model.addAttribute("cityName", cityName);
			}
			if(StringUtils.isNotBlank(areaId)){
				this.getCondition().put("areaId", areaId);
				model.addAttribute("areaId", areaId);
			}
			if(StringUtils.isNotBlank(aName)){
				this.getCondition().put("aName", aName);
				model.addAttribute("aName", aName);
			}
			if(StringUtils.isNotBlank(hotpointId)){
				this.getCondition().put("hotpointId", hotpointId);
				model.addAttribute("hotpointId", hotpointId);
			}
			if(StringUtils.isNotBlank(hotpointName)){
				this.getCondition().put("hotpointName", hotpointName);
				model.addAttribute("hotpointName", hotpointName);
			}
			this.getCondition().put("showStatus", this.getSession().getAttribute("showStatus"));
			Map<String,Object> result = areaMngService.findAreaList(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("result", result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result",null);
			logger.error(e);
		}
		logger.info("index findpolicyAreaList method end");
		return "push/area";
	}
	@RequestMapping(value="/survey.html")//实际访问 
	public String survey() {
		logger.info("survey method start");
			logger.info("survey  method start");
		logger.info("survey method end");
		return "index/survey";
	}

	@RequestMapping(value="/accountsManage.html")
	public String toAccountListView(String userName){
		logger.info("toAccountListView method strat");
		String cityId = "";
		String provinceId ="";
		this.getCondition().put("userName", userName);
		if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
			cityId = this.getSession().getAttribute("cityId").toString();
			this.getCondition().put("cityId", cityId);
		}
		if(this.getSession().getAttribute("provinceId")!=null && this.getSession().getAttribute("provinceId")!=""){
			provinceId = this.getSession().getAttribute("provinceId").toString();
			this.getCondition().put("provinceId", provinceId);
		}
		int showStatus=this.getSession().getAttribute("showStatus")==null?0:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
		this.getCondition().put("showStatus",showStatus);
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			Map<String,Object> result= accountManageService.findAccountsByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
			result.put("userName",userName);
			result.put("cityId",cityId);
			result.put("showStatus",showStatus);
			this.getRequest().setAttribute("result",result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result",null);
			logger.error(e);
		}
		logger.info("toAccountListView method end");
		return "accountmanage/accountsManage";
	}

	/**
	 * 去财务管理页面
	 * @return
     */
	@RequestMapping(value="/financialManage.html")
	public String toFinancialManagementView(String contractCode){
		logger.info("toFinancialManagementView method strat");
		this.getCondition().put("contractCode", contractCode);
		int userId = Integer.parseInt(String.valueOf(this.getSession().getAttribute("userId")));
		this.getCondition().put("userId",userId);
		String cityId = "";
		String provinceId ="";
		if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
			cityId = this.getSession().getAttribute("cityId").toString();
			this.getCondition().put("cityId", cityId);
		}
		if(this.getSession().getAttribute("provinceId")!=null && this.getSession().getAttribute("provinceId")!=""){
			provinceId = this.getSession().getAttribute("provinceId").toString();
			this.getCondition().put("provinceId", provinceId);
		}
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			Map<String,Object> result = customerManageService.findCustomersFinanceByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
			result.put("contractCode",contractCode);
			this.getRequest().setAttribute("result",result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.info("toFinancialManagementView method end");
		return "customer/financeManage";
	}

	@RequestMapping(value="/customer.html")
	public String toCustomerListView(String searchCustomerName){
		logger.info("toCustomerListView method strat");
		this.getCondition().put("customerName", searchCustomerName);
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		int userId = Integer.parseInt(String.valueOf(this.getSession().getAttribute("userId")));
		this.getCondition().put("userId",userId);
		Map<String,Object> MenuRelation = null;
		try {
			Map<String,Object> result = customerManageService.findCustomersByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
			result.put("searchCustomerName",searchCustomerName);
			this.getRequest().setAttribute("result",result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.info("toCustomerListView method end");
		return "customer/customersManage";
	}

	@RequestMapping(value="/Adnetwork.html")
	public String toAdnetworkListView(String networkName){
		logger.info("toAdnetworkListView method strat");
		this.getCondition().put("networkName", networkName);
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			Map<String,Object> result = adnetworkService.findadnetworkByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
			result.put("networkName",networkName);
			this.getRequest().setAttribute("result",result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.info("toAdnetworkListView method end");
		return "ADnetwork/adnetworkInfo";
	}

	
	@RequestMapping(value="/activityManage.html")
	public String toActivityListView(String activityName){
		logger.info("toActivityListView method strat");
		if(activityName!=null){
			try {
				activityName = new String(activityName.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			this.getCondition().put("activityName",activityName);
		}
		int userId = Integer.parseInt(String.valueOf(this.getSession().getAttribute("userId")));
		this.getCondition().put("userId",userId);
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			Map<String,Object> result = activityManageService.findActivityByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
			result.put("activityName",activityName);
			this.getRequest().setAttribute("result",result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.info("toActivityListView method end");
		return "activity/activityManage";
	}

	@RequestMapping(value="/ipsManage.html")
	public String toIPSListView(){
		logger.info("toIPSListView method strat");
		int userId = Integer.parseInt(String.valueOf(this.getSession().getAttribute("userId")));
		this.getCondition().put("userId",userId);
		int showStatus=this.getSession().getAttribute("showStatus")==null?0:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
		this.getCondition().put("showStatus",showStatus);
		if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
			String cityId = this.getSession().getAttribute("cityId").toString();
			this.getCondition().put("cityId", cityId);
		}
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			Map<String,Object> result = ipsManageService.findIpsByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
			result.put("showStatus",showStatus);
			if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
				String cityId = this.getSession().getAttribute("cityId").toString();
				result.put("cityId", cityId);
			}
			this.getRequest().setAttribute("result",result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.info("toIPSListView method end");
		return "deviceIPS/IPSManage";
	}

	@RequestMapping(value="/roleManage.html")
	public String toRoleListView(){
		logger.info("toRoleListView method strat");
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		String cityId = "";
		if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
			cityId = this.getSession().getAttribute("cityId").toString();
			this.getCondition().put("cityId", cityId);
		}
		Map<String,Object> MenuRelation = null;
		try {
			int showStatus=this.getSession().getAttribute("showStatus")==null?0:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus",showStatus);
			String roleName = this.getRequest().getParameter("roleName");
			if(StringUtils.isNotBlank(roleName)){
				//roleName = new String(roleName.getBytes("ISO-8859-1"),"UTF-8");
				this.getCondition().put("roleName", roleName);
			}
			Map<String,Object> result= sysRoleInfoService.findRolesByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
			result.put("roleName",roleName);
			result.put("showStatus",showStatus);
			this.getRequest().setAttribute("result",result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result",null);
			logger.error(e);
		}
		logger.info("toRoleListView method end");
		return "rolemanage/roleManage";
	}

	@RequestMapping(value="/journalManage.html")//实际访问
	public String logListView(ModelMap model) {
		logger.info("logListView method start");
		String logType = this.getRequest().getParameter("logType");
		String userName = this.getRequest().getParameter("userName");
		String logTime = this.getRequest().getParameter("logTime");
		String cityId = "";
		String provinceId = "";
		Map<String,Object> result = null;
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time=df.format(date);
		//时间为空时，赋予当前时间
		if(logTime==null){
			logTime=time;
		}
		try {
			int showStatus=this.getSession().getAttribute("showStatus")==null?0:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus",showStatus);
			if(StringUtils.isNotBlank(logType)){
				this.getCondition().put("logType", logType);
				model.addAttribute("logType", logType);
			}
			if(StringUtils.isNotBlank(userName)){
				this.getCondition().put("userName", userName);
				model.addAttribute("userName", userName);
			}
			if(StringUtils.isNotBlank(logTime)){
				this.getCondition().put("logTime", logTime);
				model.addAttribute("logTime", logTime);
			}

			if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
				cityId = this.getSession().getAttribute("cityId").toString();
				this.getCondition().put("cityId", cityId);
			}
			if(this.getSession().getAttribute("provinceId")!=null && this.getSession().getAttribute("provinceId")!=""){
				provinceId = this.getSession().getAttribute("provinceId").toString();
				this.getCondition().put("provinceId", provinceId);
			}
			result = sysLogRecordService.findLogsByCondition(this.getVisitId(),this.getVisitId(),this.getCondition());
			result.put("logType",logType);
			result.put("userName",userName);
			result.put("logTime",logTime);
			result.put("cityId",cityId);
			result.put("showStatus",showStatus);
			this.getRequest().setAttribute("result",result);
		} catch (ServiceException e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result",null);
			logger.error(e);
		}
		logger.info("logListView method end");
		return "journal/journalmanage";
	}

	@RequestMapping("/accountInfo.html")
	public String accountInfoPage(){
		logger.info("accountInfoPage method start");
		int userId = Integer.parseInt(String.valueOf(this.getSession().getAttribute("userId")));
		this.getCondition().put("userId",userId);
		Map<String,Object> result = null;
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			result = accountManageService.getInfoByCondition(this.getVisitId(),this.getCondition());
			this.getRequest().setAttribute("result",result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error(e);
			this.getRequest().setAttribute("result",null);
		}
		logger.info("accountInfoPage method end");
		return "accountmanage/accountInfo";
	}
	
	@RequestMapping(value="/sysNews.html")
	public String toNewsListView(String userName){
		logger.info("toNewsListView method strat");
		Map<String, Object> userCondition = new HashMap<String, Object>();
		userCondition.put("userId", this.getVisitId());
		Map<String, Object> userInfo = new HashMap<String, Object>();
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			userInfo = accountManageService.getInfoByCondition(this.getVisitId(), userCondition);
		} catch (ServiceException e1) {
			this.getRequest().setAttribute("result",null);
			e1.printStackTrace();
			logger.error(e1);
		}
		String cityId = "";
		if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
			cityId = this.getSession().getAttribute("cityId").toString();
			this.getCondition().put("cityId", cityId);
		}
		int Show_Status = (int) userInfo.get("showStatus");
		String news_content = this.getRequest().getParameter("news_content");
		String news_type = this.getRequest().getParameter("news_type");
		this.getCondition().put("Show_Status", Show_Status);
		this.getCondition().put("News_Content", news_content);
		if(news_type!=null){
			this.getCondition().put("News_Type",news_type);
		}
		this.getRequest().setAttribute("news_content",news_content);
		this.getRequest().setAttribute("news_type",news_type);
		try {
			Map<String,Object> result=sysNewsInfoService.findNewsByCondition(this.getVisitId(),this.getVisitId(),this.getCondition());
			this.getRequest().setAttribute("result",result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (Exception e) {
			this.getRequest().setAttribute("result",null);
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("toNewsListView method end");
		return "system/msgManagement";
	}
	
	@RequestMapping(value="/accountNews.html")
	public String toAccountNewsListView(String userName){
		logger.info("toAccountNewsListView method strat");
		String news_content = this.getRequest().getParameter("news_content");
		String news_type = this.getRequest().getParameter("news_type");
		String news_id = this.getRequest().getParameter("news_id");
		String operation = this.getRequest().getParameter("operation");
		String Create_Date = this.getRequest().getParameter("Create_Date");
		if(Create_Date==null||Create_Date==""){
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Create_Date = dateFormat.format(date);
		}
		this.getCondition().put("Create_Date", Create_Date);
		this.getRequest().setAttribute("Create_Date", Create_Date);
		
		this.getCondition().put("News_Content", news_content);
		this.getCondition().put("User_Id", this.getVisitId());
		if(operation!=null){
			try {
				this.getCondition().put("Create_Date", null);
				this.getRequest().setAttribute("Create_Date", null);
				this.getCondition().put("Is_Read", 1);
				Map<String,Object> result=sysNewsInfoService.findNewsByCondition(this.getVisitId(),this.getVisitId(),this.getCondition());
				this.getRequest().setAttribute("result",result);
				sysNewsInfoService.updateNews(this.getCondition());
			} catch (ServiceException e) {
				this.getRequest().setAttribute("result",null);
				e.printStackTrace();
				logger.error(e);
			}
			logger.info("toAccountNewsListView method end");
			return "system/accountMsg";
		}
		if(news_type!=null){
			this.getCondition().put("News_Type",news_type);
		}
		if(news_id!=null){
			this.getCondition().put("News_Id",news_id);
			try {
				sysNewsInfoService.updateNews(this.getCondition());
			} catch (ServiceException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		try {
			Map<String,Object> result=sysNewsInfoService.findNewsByCondition(this.getVisitId(),this.getVisitId(),this.getCondition());
			this.getRequest().setAttribute("result",result);
		} catch (Exception e) {
			this.getRequest().setAttribute("result",null);
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("toAccountNewsListView method end");
		return "system/accountMsg";
	}
	
	@RequestMapping(value="/monitorList.html")
	public String toMonitorListView(String userName){
		logger.info("toMonitorListView method strat");
		String Report_Date = this.getRequest().getParameter("Report_Date");
		if(Report_Date==null){
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Report_Date = dateFormat.format(date);
		}
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (Report_Date.equals(today)){
			@SuppressWarnings("deprecation")
			int Report_Period = new Date().getHours();
			this.getCondition().put("Report_Period", Report_Period);
		}else {
			this.getCondition().put("Report_Period", 22);
		}
		this.getCondition().put("Report_Date", Report_Date);
		this.getRequest().setAttribute("Report_Date",Report_Date);
		try {
			Map<String,Object> result=sysTaskService.findTasksByCondition(this.getVisitId(),this.getVisitId(),this.getCondition());
			this.getRequest().setAttribute("result",result);
		} catch (Exception e) {
			this.getRequest().setAttribute("result",null);
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("toMonitorListView method end");
		return "monitor/monitorList";
	}

	@RequestMapping(value="/operationLog.html")//实际访问
	public String operationLogView(String logTime,ModelMap model ) {
		logger.info("operationLogView method start");
		/*Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(d);*/
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time=df.format(date);
		//时间为空时，赋予当前时间
		if(logTime==null){
			logTime=time;
		}
		
		Map<String,Object> result=null;
		int userId = Integer.parseInt(this.getRequest().getSession().getAttribute("userId").toString());
		if(StringUtils.isNotBlank(logTime)){
			this.getCondition().put("logTime", logTime);
			model.addAttribute("logTime", logTime);
		}
		this.getCondition().put("logTime",logTime);
		this.getCondition().put("userId",userId);
		try {
			result = sysLogRecordService.findLogsByCondition(this.getVisitId(),this.getVisitId(),this.getCondition());
			if(StringUtils.isNotBlank(logTime)){
				result.put("logTime",logTime);
			}/*else{
				result.put("logTime",today);
			}*/
			this.getRequest().setAttribute("result",result);
		} catch (ServiceException e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result",null);
			logger.error(e);
		}
		logger.info("operationLogView method end");
		return "journal/operationLog";
	}
}
