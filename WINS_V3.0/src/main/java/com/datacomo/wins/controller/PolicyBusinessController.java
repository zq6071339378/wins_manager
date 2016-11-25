package com.datacomo.wins.controller;


import java.text.SimpleDateFormat;
import java.util.*;

import com.datacomo.wins.service.*;
import com.datacomo.wins.util.DateUtil;
import com.datacomo.wins.util.EmailUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.MaterialService;
import com.datacomo.wins.service.PolicyBusinessService;
import com.datacomo.wins.service.PolicyGroupMngService;
import com.datacomo.wins.service.PolicySynchStatusService;
import com.datacomo.wins.service.SysRoleInfoService;
import com.datacomo.wins.util.MD5;
/**
 * @author zhangming
 *
 *	策略控制类
 */

@Controller
@RequestMapping(value="/policy")//url映射方法前缀
public class PolicyBusinessController extends BaseController {
	private static Logger logger = Logger.getLogger(TestController.class.getName());
	@Autowired
	MaterialService materialService;
	@Autowired
	PolicyBusinessService policyBusinessService;
	@Autowired
	PolicyGroupMngService policyGroupMngService;
	@Autowired
	PolicySynchStatusService policySynchStatusService;
	 @Autowired
	SysRoleInfoService sysRoleInfoService;
	@Autowired
	private SysLogRecordService sysLogRecordService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	SysIndustryService sysIndustryService;
	@Autowired
	SysMediaService sysMediaService;
	@Autowired
	CustomerManageService customerManageService;
	@Autowired
	ActivityManageService activityManageService;

	@Value("#{configProperties['provincialId']}")
	private String provincialId;
	
	@ResponseBody
	@RequestMapping(value="policyListjson")
	public ResultModel policyListjson() {
		logger.info("PolicyBusinessController list method start");
		try {
			Map<String,Object> result = policyBusinessService.findPolicyList(this.getVisitId(), this.getCondition());
			System.out.println("result------------------------------------------------: "+result);
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		
		logger.info("PolicyBusinessController list method end");
		return this.getModel();
	}
	/**
	 * 获取策略列表
	 * @return
	 */
	@RequestMapping(value="policyList")
	public String policyList() {
		logger.info("PolicyBusinessController list method start");
		try {
			int policyType=this.getRequest().getParameter("policyType")==null?0:Integer.parseInt(this.getRequest().getParameter("policyType").toString());
			String searchPolicyName=this.getRequest().getParameter("searchPolicyName")==null?"":this.getRequest().getParameter("searchPolicyName");
			Map<String, Object> mapParm=new HashMap<String, Object>();
			String searchType=this.getRequest().getParameter("searchType")==null?"0":this.getRequest().getParameter("searchType");
			if(searchPolicyName.length()!=0){
				this.getCondition().put("searchName", searchPolicyName);
				if(!searchType.equals("undefined")){
					this.getCondition().put("searchType", searchType);
				}
			}
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int cityId=this.getSession().getAttribute("cityId")==null?0:Integer.parseInt(this.getSession().getAttribute("cityId").toString());
			if(cityId!=0){
				this.getCondition().put("cityId",cityId);
			}
			this.getCondition().put("policyType", policyType);
			if(policyType==1){
				mapParm.put("c.Policy_Status", 1);
				mapParm.put("c.Check_Status", 1);
				this.getCondition().put("nowTime", time.format( new Date()));
				this.getCondition().put("condition", mapParm);
			}else if(policyType==2){
				mapParm.put("c.Policy_Status", 1);
				mapParm.put("c.Check_Status", 1);
				this.getCondition().put("nowTime", time.format( new Date()));
				this.getCondition().put("condition", mapParm);
			}else if(policyType==3){
				this.getCondition().put("nowTime", time.format( new Date()));
				mapParm.put("c.Check_Status", 0);
				this.getCondition().put("condition", mapParm);
			}else if(policyType==4){
				mapParm.put("c.Policy_Status", 2);
				mapParm.put("c.Check_Status", 1);
				this.getCondition().put("nowTime", time.format( new Date()));
				this.getCondition().put("condition", mapParm);
			}
			int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString()); 
			int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus",showStatus);
			this.getCondition().put("userId",userId);
			Map<String,Object> result = policyBusinessService.findPolicyList(this.getVisitId(), this.getCondition());
			Map<String,Object> resultGlobal = policyBusinessService.getGlobalInfo(this.getVisitId(), this.getCondition());
		    //权限
			int roleId =this.getSession().getAttribute("roleId")==null?1:Integer.parseInt(this.getSession().getAttribute("roleId").toString());
		    this.getCondition().clear();
	        this.getCondition().put("roleId",roleId);
	        Map<String,Object> roleresult = null;
	        try {
	        	roleresult = sysRoleInfoService.getRoleInfo(this.getVisitId(),this.getCondition());
	            this.getRequest().setAttribute("roleresult",roleresult);
	        } catch (ServiceException e) {
	            e.printStackTrace();
	        }
			this.getRequest().setAttribute("resultGlobal", resultGlobal);
			this.getRequest().setAttribute("result", result);
			this.getRequest().setAttribute("policyType", policyType);
		} catch (ServiceException e) {
			this.getRequest().setAttribute("result", null);
		}
		
		logger.info("PolicyBusinessController list method end");
		boolean isEnglish=Config.message.get("isEnglish")==null?false:Boolean.valueOf(Config.message.get("isEnglish").toString());
		if(isEnglish){
			return "push/policylist_en";
		}
		return "push/policylist";
	}
	/**
	 * 获取策略列表
	 * @return
	 */
	@RequestMapping(value="policyListEn")
	public String policyListEn() {
		logger.info("PolicyBusinessController list method start");
		try {
			int policyType=this.getRequest().getParameter("policyType")==null?0:Integer.parseInt(this.getRequest().getParameter("policyType").toString());
			String searchPolicyName=this.getRequest().getParameter("searchPolicyName")==null?"":this.getRequest().getParameter("searchPolicyName");
			Map<String, Object> mapParm=new HashMap<String, Object>();
			String searchType=this.getRequest().getParameter("searchType")==null?"0":this.getRequest().getParameter("searchType");
			if(searchPolicyName.length()!=0){
				this.getCondition().put("searchName", searchPolicyName);
				if(!searchType.equals("undefined")){
					this.getCondition().put("searchType", searchType);
				}
			}
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int cityId=this.getSession().getAttribute("cityId")==null?0:Integer.parseInt(this.getSession().getAttribute("cityId").toString());
			if(cityId!=0){
				this.getCondition().put("cityId",cityId);
			}
			this.getCondition().put("policyType", policyType);
			if(policyType==1){
				mapParm.put("c.Policy_Status", 1);
				mapParm.put("c.Check_Status", 1);
				this.getCondition().put("nowTime", time.format( new Date()));
				this.getCondition().put("condition", mapParm);
			}else if(policyType==2){
				mapParm.put("c.Policy_Status", 1);
				mapParm.put("c.Check_Status", 1);
				this.getCondition().put("nowTime", time.format( new Date()));
				this.getCondition().put("condition", mapParm);
			}else if(policyType==3){
				this.getCondition().put("nowTime", time.format( new Date()));
				mapParm.put("c.Check_Status", 0);
				this.getCondition().put("condition", mapParm);
			}else if(policyType==4){
				mapParm.put("c.Policy_Status", 2);
				mapParm.put("c.Check_Status", 1);
				this.getCondition().put("nowTime", time.format( new Date()));
				this.getCondition().put("condition", mapParm);
			}else if(policyType==5){
				mapParm.put("c.Policy_Status", 2);
				mapParm.put("c.Check_Status", 1);
				this.getCondition().put("nowTime", time.format( new Date()));
				this.getCondition().put("condition", mapParm);
			}
			int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString()); 
			int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus",showStatus);
			this.getCondition().put("userId",userId);
			Map<String,Object> result = policyBusinessService.findPolicyListEn(this.getVisitId(), this.getCondition());
			Map<String,Object> resultGlobal = policyBusinessService.getGlobalInfo(this.getVisitId(), this.getCondition());
		    //权限
			int roleId =this.getSession().getAttribute("roleId")==null?1:Integer.parseInt(this.getSession().getAttribute("roleId").toString());
		    this.getCondition().clear();
	        this.getCondition().put("roleId",roleId);
	        Map<String,Object> roleresult = null;
	        try {
	        	roleresult = sysRoleInfoService.getRoleInfo(this.getVisitId(),this.getCondition());
	            this.getRequest().setAttribute("roleresult",roleresult);
	        } catch (ServiceException e) {
	            e.printStackTrace();
	        }
			this.getRequest().setAttribute("resultGlobal", resultGlobal);
			this.getRequest().setAttribute("result", result);
			this.getRequest().setAttribute("policyType", policyType);
		} catch (ServiceException e) {
			this.getRequest().setAttribute("result", null);
		}
		
		logger.info("PolicyBusinessController list method end");
		boolean isEnglish=Config.message.get("isEnglish")==null?false:Boolean.valueOf(Config.message.get("isEnglish").toString());
		if(isEnglish){
			return "push/policylist_en";
		}
		return "push/policylist";
	}
	/**
	 * 更新全局策略
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateGlobalInfo")
	public ResultModel updateGlobalInfo(){
		logger.info("updateGlobalInfo delete method start");
		int checkUser=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		String Ip = this.getRequest().getRemoteAddr();
		try {
			String showNum=this.getRequest().getParameter("showNum")==null?"0":this.getRequest().getParameter("showNum");
			String showType=this.getRequest().getParameter("showType")==null?"0":this.getRequest().getParameter("showType");
			String pushInterval=this.getRequest().getParameter("pushInterval")==null?"0":this.getRequest().getParameter("pushInterval");
			String showTime=this.getRequest().getParameter("showTime")==null?"0":this.getRequest().getParameter("showTime");
			String pushStatus=this.getRequest().getParameter("pushStatus")==null?"0":this.getRequest().getParameter("pushStatus");
			String checkPass=this.getRequest().getParameter("checkPass")==null?"0":this.getRequest().getParameter("checkPass");
			String pushLimit=this.getRequest().getParameter("pushLimit")==null?"0":this.getRequest().getParameter("pushLimit");
			checkPass=MD5.Md5(checkPass);
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Map<String, Object> mapParm=new HashMap<String, Object>();
			mapParm.put("Show_Num", showNum);
			mapParm.put("checkPass", checkPass);
			mapParm.put("Show_Type", showType);
			mapParm.put("Push_Interval", pushInterval);
			mapParm.put("Show_Time", showTime);
			mapParm.put("Push_Status", pushStatus);
			mapParm.put("Push_Limit", pushLimit);
			mapParm.put("Update_User", checkUser);
			mapParm.put("Update_Time",time.format( new Date()));
			this.getCondition().put("condition", mapParm);
			int result =policyBusinessService.updateGlobalInfo(this.getCondition());
			int code= -1;
			if(result==1){
				code = policyBusinessService.quiteChangePllicy();
				if(code==0 || code==20){
					this.getModel().setCode(ResultCodeConstants.SUCCESS);
					this.getModel().setDesc("Global policy updated successfully，Synchronization successfully");
				}else{
					result = 0;
					this.getModel().setCode(ResultCodeConstants.ERROR);
					this.getModel().setDesc("Global policy updated successfully，Synchronization failed");
					sysLogRecordService.insertLogInfo(checkUser,"Synchronize global policy",2,"Global policy updated failed",Ip, DateUtil.getDateString());
				}
			}
			this.getModel().setResult(result);
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("Method invocation exception");
			try {
				sysLogRecordService.insertLogInfo(checkUser,"Configure global policies",2,"Global policy updated failed",Ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("updateGlobalInfo delete method end");
		return this.getModel();
	}
	/**
	 * 进入创建策略
	 * @return
	 */
	@RequestMapping(value="createPolicy")
	public String createpolicy() {
		logger.info("PolicyBusinessController createpolicy method start");
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try {
			this.getCondition().put("userId",userId);
			this.getCondition().remove("page");
			int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus",showStatus);
			Map<String,Object> result = materialService.findPage(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("result", result);
			Map<String,Object> groupList = policyGroupMngService.findGroupList(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("groupList",groupList);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("PolicyBusinessController createpolicy method end");
		boolean isEnglish=Config.message.get("isEnglish")==null?false:Boolean.valueOf(Config.message.get("isEnglish").toString());
		if(isEnglish){
			this.getCondition().clear();
			try {
				this.getCondition().put("userId", userId);
				Map<String,Object> resultCustomer = customerManageService.findCustomersByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
				this.getRequest().setAttribute("customerList", resultCustomer);
				Map<String,Object> activitylist = activityManageService.findActivityByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
				this.getRequest().setAttribute("activitylist", activitylist);
				Map<String,Object> groupList = policyGroupMngService.findGroupList(this.getVisitId(), this.getVisitId(), this.getCondition());
				this.getRequest().setAttribute("groupList",groupList);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "push/createpolicy_en";
		}
		return "push/createpolicy";
	}

	@ResponseBody
	@RequestMapping(value="searchIndustry")
	public ResultModel searchIndustry(){
		Map<String,Object> result= null;
		try {
			this.getCondition().remove("page");
			result = sysIndustryService.findFirstIndustry(this.getVisitId(), this.getCondition());
			this.getModel().setResult(result);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
		return this.getModel();
	}

	@ResponseBody
	@RequestMapping(value="searchGroup")
	public ResultModel searchGroup(){
		this.getCondition().remove("page");
		this.getCondition().put("userId",this.getVisitId());
		int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
		this.getCondition().put("showStatus",showStatus);
		Map<String,Object> result= null;
		try {
			this.getCondition().remove("page");
			result =  policyGroupMngService.findGroupList(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getModel().setResult(result);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
		return this.getModel();
	}

	/**
	 * 创建策略
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="addPolicy")
	public ResultModel addPolicy(){
		logger.info("updateGlobalInfo delete method start");
		String Ip = this.getRequest().getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		boolean isEnglish=Config.message.get("isEnglish")==null?false:Boolean.valueOf(Config.message.get("isEnglish").toString());
		try {
			String policyName=this.getRequest().getParameter("policyName")==null?"":this.getRequest().getParameter("policyName");
			String policyLevel=this.getRequest().getParameter("policyLevel")==null?"0":this.getRequest().getParameter("policyLevel");
			String pushInterval=this.getRequest().getParameter("pushInterval")==null?"0":this.getRequest().getParameter("pushInterval");
			String policyType=this.getRequest().getParameter("policyType")==null?"0":this.getRequest().getParameter("policyType");
			String showTime=this.getRequest().getParameter("showTime")==""?"0":this.getRequest().getParameter("showTime");
			String pushLimit=this.getRequest().getParameter("pushLimit")==""?"0":this.getRequest().getParameter("pushLimit");
			String policyDayLimit = this.getRequest().getParameter("policyDayLimit")==""?"0":this.getRequest().getParameter("policyDayLimit");
			String pushUserType = this.getRequest().getParameter("pushUserType")==""?"0":this.getRequest().getParameter("pushUserType");
			String terminalType=this.getRequest().getParameter("terminalType")==null?"0":this.getRequest().getParameter("terminalType");
			String buyType=this.getRequest().getParameter("buyType")==null?"0":this.getRequest().getParameter("buyType");
			String adType=this.getRequest().getParameter("adType")==null?"0":this.getRequest().getParameter("adType");
			String policyShow=this.getRequest().getParameter("policyShow")==null?"0":this.getRequest().getParameter("policyShow");
			String industry_bear=this.getRequest().getParameter("industry-bear")==null?"0":this.getRequest().getParameter("industry-bear");  
			String showType=this.getRequest().getParameter("showType")==null?"0":this.getRequest().getParameter("showType"); 
			String showLimit=this.getRequest().getParameter("showLimit")==""?"0":this.getRequest().getParameter("showLimit");
			String hitLimit=this.getRequest().getParameter("hitLimit")==""?"0":this.getRequest().getParameter("hitLimit");
			String showNum=this.getRequest().getParameter("showNum")==""?"0":this.getRequest().getParameter("showNum");
			String hitNum=this.getRequest().getParameter("hitNum")==""?"0":this.getRequest().getParameter("hitNum");
			String Description=this.getRequest().getParameter("Description")==null?"":this.getRequest().getParameter("Description");
			String groupIds[]=this.getRequest().getParameterValues("group");
			String pushSpeed=this.getRequest().getParameter("speedType")==null?"":this.getRequest().getParameter("speedType");
			String[] medialUrl = this.getRequest().getParameterValues("industry");
			String[] ips = this.getRequest().getParameterValues("ips");
			String[] areas=this.getRequest().getParameterValues("areas");
			String[] Ctime=this.getRequest().getParameterValues("Ctime");
			String unitPrice=this.getRequest().getParameter("newunitPrice")==""?"0":this.getRequest().getParameter("newunitPrice");
			String pushUrl=this.getRequest().getParameter("pushUrl")==null?"":this.getRequest().getParameter("pushUrl");
			String[] urlDomains=this.getRequest().getParameterValues("urlDomain");
			String[] kewWords=this.getRequest().getParameterValues("kewWord");
			String pushOtherUrl=this.getRequest().getParameter("pushOtherUrl")==null?"":this.getRequest().getParameter("pushOtherUrl");
			String timeIds=this.getRequest().getParameter("timeIds")==null?"":this.getRequest().getParameter("timeIds");
			int timeType=this.getRequest().getParameter("timeType")==null?1:Integer.parseInt(this.getRequest().getParameter("timeType"));
			String startTime=this.getRequest().getParameter("policyStartTime")==null?"":this.getRequest().getParameter("policyStartTime");
			String endTime=this.getRequest().getParameter("policyEndTime")==null?"":this.getRequest().getParameter("policyEndTime");
			//String roamType=this.getRequest().getParameter("roamType")==""?"0":this.getRequest().getParameter("roamType");
			
			SimpleDateFormat time1=new SimpleDateFormat("yyyy-MM-dd"); 
			if(startTime.trim().length()==0){
				startTime=time1.format(new Date());
				endTime=startTime+" 23:59:59";
			}else{
				endTime=endTime+" 23:59:59";
			}
			
			if(timeType==2){
				pushInterval=String.valueOf(Integer.parseInt(pushInterval)*60);
			}else if(timeType==3){
				pushInterval=String.valueOf(Integer.parseInt(pushInterval)*60*60);
			}
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			if(isEnglish){ //国外不分本地 漫游 3A认证用户 统一默认为全网流量用户 ---4
				if(pushUserType.equals("0") || pushUserType.equals("1") || pushUserType.equals("2") || pushUserType.equals("3")){
					pushUserType="4";
				}
			}
			Map<String, Object> mapParm=new HashMap<String, Object>();
			mapParm.put("Policy_Name", policyName);
			mapParm.put("Policy_Status", 1);
			mapParm.put("Policy_Level", policyLevel);
			mapParm.put("Policy_Type", policyType);
			mapParm.put("Policy_Relation", 0);///
			mapParm.put("Policy_Show",policyShow );
			mapParm.put("Show_Num", showNum);
			//mapParm.put("Roam_Type", roamType);
			mapParm.put("push_day", Ctime);
			mapParm.put("object_Id", adType);
			mapParm.put("buy_type", buyType);
			mapParm.put("Terminal_Type", terminalType);
			mapParm.put("Show_Time", showTime);
			mapParm.put("Push_Limit", pushLimit);
			mapParm.put("push_speed",pushSpeed);
			mapParm.put("PolicyDay_Limit",policyDayLimit);
			mapParm.put("Roam_Type",pushUserType);
			mapParm.put("Push_interval", pushInterval);
			mapParm.put("Show_Limit", showLimit);
			mapParm.put("Hit_Limit", hitLimit);
			mapParm.put("Hit_Num", hitNum);
			mapParm.put("Content_Type", 0);//
			mapParm.put("Push_URL", pushUrl);
			mapParm.put("Show_Type", showType);
			mapParm.put("industry_id", industry_bear);
			mapParm.put("Show_Close", 1);
			mapParm.put("Create_User", userId);
			mapParm.put("Create_Time", time.format( new Date()));
			mapParm.put("Update_Time",time.format( new Date()));
			mapParm.put("Check_Status", 0);
			mapParm.put("Show_Close", 1);
			mapParm.put("Description", Description);
			mapParm.put("unit_price",unitPrice);
			this.getCondition().put("condition", mapParm);
			int result =policyBusinessService.creatPolicy(this.getCondition());
			if(result>0){
				if(groupIds!=null){//添加群组
			
					for(String groupId:groupIds){
						Map<String, Object> mapGroup=new HashMap<String, Object>();
						mapGroup.put("Create_User", userId);
						mapGroup.put("Create_Time", time.format( new Date()));
						mapGroup.put("Policy_Id", result);
						mapGroup.put("Group_ID", groupId);
						this.getCondition().clear();
						this.getCondition().put("condition", mapGroup);
						policyBusinessService.policyAddGroup(this.getCondition());
					}
				}
				//添加IPS
				int policyId = result;
				if(ips!=null){
					this.getCondition().clear();
					List<Map<String,Object>> list = new ArrayList<>();
					for(String ipsId:ips){
						Map<String, Object> cond=new HashMap<String, Object>();
						cond.put("ipsId",ipsId);
						list.add(cond);
					}
					this.getCondition().put("policyId", policyId);
					this.getCondition().put("createUser", userId);
					this.getCondition().put("createTime",DateUtil.getDateString());
					this.getCondition().put("LIST",list);
					policyBusinessService.insertIpsInfo(this.getVisitId(),this.getCondition());
				}else{
					this.getCondition().clear();
					List<Map<String,Object>> ipslist = null;
					List<Map<String,Object>> list = new ArrayList<>();
					ipslist = policyBusinessService.sysIpsList(this.getVisitId(),this.getCondition());
					if(ipslist.size()>0){
						for (Map<String,Object> map : ipslist){
							Map<String, Object> cond=new HashMap<String, Object>();
							String ipsId = String.valueOf(map.get("ipsId"));
							cond.put("ipsId",ipsId);
							list.add(cond);
						}
						this.getCondition().put("policyId", policyId);
						this.getCondition().put("createUser", userId);
						this.getCondition().put("createTime",DateUtil.getDateString());
						this.getCondition().put("LIST",list);
						policyBusinessService.insertIpsInfo(this.getVisitId(),this.getCondition());
					}
				}
				//添加地区
				if(areas!=null){
					for(String cityId:areas){
						Map<String, Object> cityIdMap=new HashMap<String, Object>();
						cityIdMap.put("Create_User", userId);
						cityIdMap.put("Create_Time", time.format( new Date()));
						cityIdMap.put("Policy_Id", result);
						cityIdMap.put("Province_Id", provincialId);
						if(cityId.equals(provincialId)){
							cityId="0";
						}
						if(cityId.contains("_")){
							String districtId=cityId.split("_")[1];
							cityIdMap.put("District_Id", districtId);
							cityId=cityId.split("_")[0];
						}
						cityIdMap.put("City_Id", cityId);
						cityIdMap.put("Relation_Type",1);
						this.getCondition().clear();
						this.getCondition().put("condition", cityIdMap);
						policyBusinessService.policyAddCity(this.getCondition());
					}
				}else{
					if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
						int provinceId = Integer.parseInt(this.getSession().getAttribute("provinceId").toString());
						int cityId = Integer.parseInt(this.getSession().getAttribute("cityId").toString());
						Map<String, Object> cityIdMap=new HashMap<String, Object>();
						cityIdMap.put("Create_User", userId);
						cityIdMap.put("Create_Time", time.format( new Date()));
						cityIdMap.put("Policy_Id", result);
						cityIdMap.put("Province_Id", provinceId);
						cityIdMap.put("City_Id", cityId);
						cityIdMap.put("Relation_Type", 1);
						this.getCondition().clear();
						this.getCondition().put("condition", cityIdMap);
						policyBusinessService.policyAddCity(this.getCondition());
					}
				}
				//添加媒体URL
				if(medialUrl!=null){
					for(String m_Id:medialUrl){
						Map<String,Object> medialcon = new HashMap<>();
						medialcon.put("mediaId",m_Id);
						Map<String,Object> medial = sysMediaService.findMedilInfo(userId,medialcon);
						if(medial!=null && medial.get("mediaUrl")!=null){
							String url = String.valueOf(medial.get("mediaUrl"));
							Map<String, Object> cityIdMap=new HashMap<String, Object>();
							cityIdMap.put("Create_User", userId);
							cityIdMap.put("Create_Time", time.format( new Date()));
							cityIdMap.put("Policy_Id", result);
							cityIdMap.put("Url_Domain", url);
							cityIdMap.put("Url_Path", "");
							this.getCondition().clear();
							this.getCondition().put("condition", cityIdMap);
							policyBusinessService.policyAddUrlDomain(this.getCondition());
						}
					}
				}
				//添加定向网址
				if(urlDomains!=null){
					for(String urlDomain:urlDomains){
						Map<String, Object> cityIdMap=new HashMap<String, Object>();
						cityIdMap.put("Create_User", userId);
						cityIdMap.put("Create_Time", time.format( new Date()));
						cityIdMap.put("Policy_Id", result);
						cityIdMap.put("Url_Domain", urlDomain);
						cityIdMap.put("Url_Path", "");
						this.getCondition().clear();
						this.getCondition().put("condition", cityIdMap);
						policyBusinessService.policyAddUrlDomain(this.getCondition());
					}
				}
				//创建外部推送路径
				if(policyType.equals("4")){
					Map<String, Object> cityIdMap=new HashMap<String, Object>();
					cityIdMap.put("Create_Time", time.format( new Date()));
					cityIdMap.put("Policy_Id", result);
					cityIdMap.put("Web_Url", pushOtherUrl);
					this.getCondition().clear();
					this.getCondition().put("condition", cityIdMap);
					policyBusinessService.policyAddOtherUrl(this.getCondition());
				}
				//添加关键字
				if(kewWords!=null){
					for(String keyWord:kewWords){
						Map<String, Object> cityIdMap=new HashMap<String, Object>();
						cityIdMap.put("Create_User", userId);
						cityIdMap.put("Create_Time", time.format( new Date()));
						cityIdMap.put("Policy_Id", result);
						cityIdMap.put("Keyword_Name", keyWord);
						this.getCondition().clear();
						this.getCondition().put("condition", cityIdMap);
						policyBusinessService.policyAddKeyWord(this.getCondition());
					}
				}
				//添加推送时间
				String [] timesAll=timeIds.split(",");
				long times1=0,times2=0,times3=0,times4=0,times5=0,times6=0,times7=0;
				for(String timeid:timesAll){
					String [] stime=timeid.split("-");
					if(stime[0].equals("1")){
						times1 =times1+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("2")){
						times2 =times2+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("3")){
						times3 =times3+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("4")){
						times4 =times4+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("5")){
						times5 =times5+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("6")){
						times6 =times6+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("7")){
						times7 =times7+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}
				}
				System.out.println(times7);
				if(times1!=0){
					double week=Math.pow(2,0);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", result);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times1);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
				if(times2!=0){
					double week=Math.pow(2,1);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", result);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times2);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
				if(times3!=0){
					double week=Math.pow(2,2);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", result);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times3);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
				if(times4!=0){
					double week=Math.pow(2,3);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", result);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times4);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
				if(times5!=0){
					double week=Math.pow(2,4);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", result);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times5);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}

				if(times6!=0){
					double week=Math.pow(2,5);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", result);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times5);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}

				if(times7!=0){
					double week=Math.pow(2,6);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", result);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times7);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
				
			}
			//英文版的添加广告主，活动关系表
			if(isEnglish){
				int flag=0;
				String customers=this.getRequest().getParameter("customerNameList").toString();
				String activityNameList=this.getRequest().getParameter("activityNameList").toString();
				List<Map<String,Object>> listactivity = new ArrayList<>();
				for(String activityId:activityNameList.split(",")){
					Map<String, Object> cond=new HashMap<String, Object>();
					this.getCondition().clear();
					this.getCondition().put("policyId", result);
					this.getCondition().put("customers", customers);
					this.getCondition().put("createUser", userId);
					this.getCondition().put("activityId", activityId);
					//查看是否存在
					flag=policyBusinessService.queryCustomerActivity(this.getVisitId(),this.getCondition());
					cond.put("activityId",activityId);
					listactivity.add(cond);
				}
				this.getCondition().clear();
				this.getCondition().put("policyId", result);
				this.getCondition().put("customers", customers);
				this.getCondition().put("createUser", userId);
				this.getCondition().put("createTime",DateUtil.getDateString());
				this.getCondition().put("LIST",listactivity);
				if(flag==0){
					policyBusinessService.insertCustomer(this.getVisitId(),this.getCondition());
				}
				
				policyBusinessService.insertActivity(this.getVisitId(),this.getCondition());
				
				
			}
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
			sysLogRecordService.insertLogInfo(userId,"Creating a policy",1,"Create a policy success",Ip, DateUtil.getDateString());
			boolean isSend = Boolean.valueOf(EmailUtil.message.get("isSendMail").toString());
			if(isSend){
				sendEmailService.sendPolicyWaitToAuditeEmail(userId,Ip,policyName);
			}
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
			try {
				sysLogRecordService.insertLogInfo(userId,"Creating a policy",2,"Failed to create policy",Ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("updateGlobalInfo delete method end");
		return this.getModel();
	}

	/**
	 * 审核策略
	 * @return
     */
	@ResponseBody
	@RequestMapping(value="updateStatus")
	public ResultModel updateStatus(){
		String Ip = this.getRequest().getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try{
			//statusType 1:停止策略 2.审核策略
			logger.info("updateGlobalInfo updateStatus method start");
			int statusType=this.getRequest().getParameter("statusType")==null?0:Integer.parseInt(this.getRequest().getParameter("statusType").toString());
			int policyId=this.getRequest().getParameter("policyId")==null?0:Integer.parseInt(this.getRequest().getParameter("policyId").toString());
			String policyName= this.getRequest().getParameter("policyName")==null?"":String.valueOf(this.getRequest().getParameter("policyName").toString());
			int policyStatus=this.getRequest().getParameter("policyStatus")==null?0:Integer.parseInt(this.getRequest().getParameter("policyStatus").toString());
			//int pushLimit = this.getRequest().getParameter("pushLimit")==null?0:Integer.parseInt(this.getRequest().getParameter("pushLimit").toString());
			Map<String, Object> timeMap=new HashMap<String, Object>();
			boolean boo = false; //true:策略开始运行 false:策略停止运行
			if(statusType==1){
				if(policyStatus==1){
					timeMap.put("Policy_Status", 1);
					boo=true;
				}else{
					timeMap.put("Policy_Status", 2);
					boo=false;
				}
			}else if(statusType==2){
				//调用立即生效
				timeMap.put("Policy_Status", 1);
				timeMap.put("Sync_Status", 1);
				timeMap.put("Check_Status", 1);
			}
			boolean bool = Boolean.valueOf(Config.message.get("icsStatusNew").toString());
			if(bool){
				this.getCondition().put("policyId", policyId);
				this.getCondition().put("condition", timeMap);
				//this.getCondition().put("pushLimit", pushLimit);
				int  result=policyBusinessService.updateStatus(this.getCondition());
				policyBusinessService.quiteChangePllicy();
				this.getModel().setResult(result);
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
				this.getModel().setDesc("方法调用成功");
				sysLogRecordService.insertLogInfo(userId,"Audit policy",1,"Audit strategy successfully",Ip, DateUtil.getDateString());
				boolean isSend = Boolean.valueOf(EmailUtil.message.get("isSendMail").toString());
				if(isSend){
					if(statusType==1){
						sendEmailService.sendPolicyStartOrStopEmail(userId,Ip,policyName,boo);
					}else if(statusType==2){
						sendEmailService.sendPolicyAuditeSuccessEmail(userId,Ip,policyName);
					}
				}
			}
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
			try {
				sysLogRecordService.insertLogInfo(userId,"Audit policy",2,"Audit strategy failed",Ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
				logger.error(e);
			}
		}
		logger.info("policyBusiness updateStatus method end");
		return this.getModel();
	}

	/**
	 *删除策略
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deletePolicy")
	public ResultModel deletePolicy(){
		String Ip = this.getRequest().getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try{
			logger.info("updateGlobalInfo deletePolicy method start");
			int policyId=this.getRequest().getParameter("policyId")==null?0:Integer.parseInt(this.getRequest().getParameter("policyId").toString());
			this.getCondition().put("policyId", policyId);
			int result=policyBusinessService.deletePolicy(this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
			sysLogRecordService.insertLogInfo(userId,"Delete policy",1,"Deleted policy successfully",Ip, DateUtil.getDateString());
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
			try {
				sysLogRecordService.insertLogInfo(userId,"Delete policy",2,"Deleted policy failed",Ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("policyBusiness deletePolicy method end");
		return this.getModel();
	}
	/**
	 * 进入单个策略
	 * @return
	 */
	@RequestMapping(value="singlePolicyInfo")
	public String singlePolicyInfo() {
		logger.info("PolicyBusinessController createpolicy method start");
		try {
			int policyId=this.getRequest().getParameter("policyId")==null?0:Integer.parseInt(this.getRequest().getParameter("policyId"));
			this.getCondition().put("policyId", policyId);
			Map<String,Object> result = policyBusinessService.singlePolicyInfo(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfo",result);
			Map<String,Object> result1=policyBusinessService.singlePolicyGroup(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoGroup",result1);
			Map<String,Object> result2=policyBusinessService.singlePolicyArea(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoArea",result2);
			Map<String,Object> result3=policyBusinessService.singlePolicyKeyWord(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoKeyWord",result3);
			Map<String,Object> result4=policyBusinessService.singlePolicyUrlDomain(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoUrlDomain",result4);
			Map<String,Object> result5=policyBusinessService.singlePolicyPushTime(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoTimeList",result5);
			Map<String,Object> result6=policyBusinessService.singlePolicyOutlineWeb(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoWebList",result6);
			Map<String,Object> result7=policyBusinessService.singlePolicyIPS(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoIPS",result7);
			boolean isEnglish=Config.message.get("isEnglish")==null?false:Boolean.valueOf(Config.message.get("isEnglish").toString());
			if(isEnglish){
				//获取广告主和活动
				Map<String,Object> result8=policyBusinessService.singlePolicyCustomer(this.getVisitId(), this.getCondition());
				this.getRequest().setAttribute("policyCustomer",result8);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("PolicyBusinessController createpolicy method end");
		return "push/policyInfo";
	}

	private void workByKeySet(Map<String, Object> result5) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 进入编辑策略
	 * @return
	 */
	@RequestMapping(value="toPolicyEditeInfo")
	public String toPolicyEditeInfo() {
		logger.info("PolicyBusinessController createpolicy method start");
		try {
			int policyId=this.getRequest().getParameter("policyId")==null?0:Integer.parseInt(this.getRequest().getParameter("policyId"));
			this.getCondition().put("policyId", policyId);
			Map<String,Object> result = policyBusinessService.singlePolicyInfo(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfo",result);
			Map<String,Object> result1=policyBusinessService.singlePolicyGroup(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoGroup",result1);
			Map<String,Object> result2=policyBusinessService.singlePolicyArea(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoArea",result2);
			Map<String,Object> result3=policyBusinessService.singlePolicyKeyWord(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoKeyWord",result3);
			Map<String,Object> result4=policyBusinessService.singlePolicyUrlDomain(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoUrlDomain",result4);
			Map<String,Object> result5=policyBusinessService.singlePolicyPushTime(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoTimeList",result5);
			Map<String,Object> result6=policyBusinessService.singlePolicyOutlineWeb(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoWebList",result6);
			Map<String,Object> result7=policyBusinessService.singlePolicyIPS(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoIPS",result7);
			boolean isEnglish=Config.message.get("isEnglish")==null?false:Boolean.valueOf(Config.message.get("isEnglish").toString());
			if(isEnglish){
				//获取广告主和活动
				Map<String,Object> result8=policyBusinessService.singlePolicyCustomer(this.getVisitId(), this.getCondition());
				this.getRequest().setAttribute("policyCustomer",result8);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("PolicyBusinessController createpolicy method end");
		return "push/editePolicy";
	}
	/**
	 * 进入编辑策略1
	 * @return
	 */
	@RequestMapping(value="toPolicyEditeOne")
	public String toPolicyEditeOne() {
		logger.info("PolicyBusinessController toPolicyEditeOne method start");
		try {
			int policyId=this.getRequest().getParameter("policyId")==null?0:Integer.parseInt(this.getRequest().getParameter("policyId"));
			this.getCondition().put("policyId", policyId);
			Map<String,Object> result = policyBusinessService.singlePolicyInfo(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfo",result);
			Map<String,Object> result6=policyBusinessService.singlePolicyOutlineWeb(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoWebList",result6);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("PolicyBusinessController toPolicyEditeOne method end");
		return "push/editePolicy1";
	}
	/**
	 * 进入编辑策略2
	 * @return
	 */
	@RequestMapping(value="toPolicyEditeTwo")
	public String toPolicyEditeTwo() {
		logger.info("PolicyBusinessController toPolicyEditeTwo method start");
		try {
			int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
			this.getCondition().put("userId", userId);
			this.getCondition().put("showStatus", this.getSession().getAttribute("showStatus"));
			this.getCondition().remove("page");
			int policyId=this.getRequest().getParameter("policyId")==null?0:Integer.parseInt(this.getRequest().getParameter("policyId"));
			this.getCondition().put("policyId", policyId);
			Map<String,Object> result = policyBusinessService.singlePolicyInfo(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfo",result);
			int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus",showStatus);
			Map<String,Object> groupList = policyGroupMngService.findGroupList(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("groupList",groupList);
			Map<String,Object> result1=policyBusinessService.singlePolicyGroup(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoGroup",result1);
			Map<String,Object> result2=policyBusinessService.singlePolicyArea(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoArea",result2);
			Map<String,Object> result3=policyBusinessService.singlePolicyKeyWord(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoKeyWord",result3);
			Map<String,Object> result4=policyBusinessService.singlePolicyUrlDomain(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoUrlDomain",result4);
			Map<String,Object> result5=policyBusinessService.singlePolicyPushTime(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoTimeList",result5);
			Map<String,Object> result6=policyBusinessService.singlePolicyOutlineWeb(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoWebList",result6);
			Map<String,Object> result7=policyBusinessService.singlePolicyIPS(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoIPS",result7);
			Map<String,Object> result8=policyBusinessService.singlePolicyCustomer(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfoCustomer",result8);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("PolicyBusinessController toPolicyEditeTwo method end");
		return "push/editePolicy2";
	}
	/**
	 * 进入编辑策略3
	 * @return
	 */
	@RequestMapping(value="toPolicyEditeThree")
	public String toPolicyEditeThree() {
		logger.info("PolicyBusinessController toPolicyEditeThree method start");
		try {
			int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString()); 
			this.getCondition().put("userId",userId);
			int policyId=this.getRequest().getParameter("policyId")==null?0:Integer.parseInt(this.getRequest().getParameter("policyId"));
			this.getCondition().put("policyId", policyId);
			int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus",showStatus);
			Map<String,Object> pageresult = materialService.findPage(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("result", pageresult);
			Map<String,Object> result = policyBusinessService.singlePolicyInfo(this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("policyinfo",result);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("PolicyBusinessController toPolicyEditeThree method end");
		return "push/editePolicy3";
	}
	
	
	@ResponseBody
	@RequestMapping(value="editePolicyOne")
	public ResultModel editePolicyOne(){
		logger.info("updateGlobalInfo updataPolicyOne method start");
		String Ip = this.getRequest().getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try {
			String policyName=this.getRequest().getParameter("policyName")==null?"":this.getRequest().getParameter("policyName");
			String policyLevel=this.getRequest().getParameter("policyLevel")==null?"0":this.getRequest().getParameter("policyLevel");
			String showTime=this.getRequest().getParameter("showTime")==""?"0":this.getRequest().getParameter("showTime");
			String pushInterval=this.getRequest().getParameter("pushInterval")==null?"0":this.getRequest().getParameter("pushInterval");
			String policyType=this.getRequest().getParameter("policyType")==null?"0":this.getRequest().getParameter("policyType");
			String industry_bear=this.getRequest().getParameter("industry-bear")==""?"0":this.getRequest().getParameter("industry-bear");
			String pushLimit=this.getRequest().getParameter("pushLimit")==null?"0":this.getRequest().getParameter("pushLimit");
			String policyDayLimit = this.getRequest().getParameter("policyDayLimit")==""?"0":this.getRequest().getParameter("policyDayLimit");
			String terminalType=this.getRequest().getParameter("terminalType")==null?"0":this.getRequest().getParameter("terminalType");
			String showType=this.getRequest().getParameter("showType")==null?"0":this.getRequest().getParameter("showType");
			String showLimit=this.getRequest().getParameter("showLimit")==""?"0":this.getRequest().getParameter("showLimit");
			String hitLimit=this.getRequest().getParameter("hitLimit")==""?"0":this.getRequest().getParameter("hitLimit");
			String showNum=this.getRequest().getParameter("showNum")==""?"0":this.getRequest().getParameter("showNum");
			String hitNum=this.getRequest().getParameter("hitNum")==""?"0":this.getRequest().getParameter("hitNum");
			String Description=this.getRequest().getParameter("Description")==null?"":this.getRequest().getParameter("Description");
			String pushOtherUrl=this.getRequest().getParameter("pushOtherUrl")==null?"":this.getRequest().getParameter("pushOtherUrl");
			String pushSpeed=this.getRequest().getParameter("speedType")==null?"":this.getRequest().getParameter("speedType");
			String buyType=this.getRequest().getParameter("buyType")==null?"0":this.getRequest().getParameter("buyType");
			int timeType=this.getRequest().getParameter("timeType")==null?1:Integer.parseInt(this.getRequest().getParameter("timeType"));
			if(timeType==2){
				pushInterval=String.valueOf(Integer.parseInt(pushInterval)*60);
			}else if(timeType==3){
				pushInterval=String.valueOf(Integer.parseInt(pushInterval)*60*60);
			}
			int policyId=Integer.parseInt(this.getRequest().getParameter("policyId").toString());
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Map<String, Object> mapParm=new HashMap<String, Object>();
			mapParm.put("Policy_Name", policyName);
			mapParm.put("Policy_Level", policyLevel);
			mapParm.put("Policy_Type", policyType);
			mapParm.put("Policy_Relation", 0);///
			mapParm.put("Policy_Show", showNum);
			mapParm.put("Show_Num", showNum);
			mapParm.put("buy_type", buyType);
			mapParm.put("Show_Time", showTime);
			mapParm.put("Terminal_Type", terminalType);
			mapParm.put("industry_id", industry_bear);
			mapParm.put("Push_Limit", pushLimit);
			mapParm.put("push_speed", pushSpeed);
			mapParm.put("policyDay_Limit",policyDayLimit);
			mapParm.put("Push_interval", pushInterval);
			mapParm.put("Show_Limit", showLimit==""?"0":showLimit);
			mapParm.put("Hit_Limit", hitLimit);
			mapParm.put("Hit_Num", hitNum);
			mapParm.put("Content_Type", 0);//
			//mapParm.put("Push_URL", pushUrl);
			mapParm.put("Show_Type", showType);
			mapParm.put("Show_Time", showTime);
			mapParm.put("Show_Close", 1);
			mapParm.put("Create_User", userId);
			mapParm.put("Create_Time", time.format( new Date()));
			mapParm.put("Update_Time",time.format( new Date()));
			mapParm.put("Check_Status", 0);
			mapParm.put("Policy_Status", 2);
			mapParm.put("Show_Close", 1);
			mapParm.put("Description", Description);
			this.getCondition().put("policyId", policyId);
			this.getCondition().put("condition", mapParm);
			int result =policyBusinessService.updataPolicyOne(this.getCondition());
			//创建外部推送路径
			if(policyType.equals("4")){
				policyBusinessService.policyDeleteOtherUrl(this.getCondition());
				Map<String, Object> cityIdMap=new HashMap<String, Object>();
				cityIdMap.put("Create_Time", time.format( new Date()));
				cityIdMap.put("Policy_Id", policyId);
				cityIdMap.put("Web_Url", pushOtherUrl);
				this.getCondition().clear();
				this.getCondition().put("condition", cityIdMap);
				policyBusinessService.policyAddOtherUrl(this.getCondition());
			}
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
			sysLogRecordService.insertLogInfo(userId,"Modify policy",1,"Modify policy successfully",Ip,DateUtil.getDateString());
			boolean isSend = Boolean.valueOf(EmailUtil.message.get("isSendMail").toString());
			if(isSend){
				sendEmailService.sendPolicyWaitToAuditeEmail(userId,Ip,policyName);
			}
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
			try {
				sysLogRecordService.insertLogInfo(userId,"Modify policy",2,"Modify policy failed",Ip,DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("updateGlobalInfo updataPolicyOne method end");
		return this.getModel();
	}
	@ResponseBody
	@RequestMapping(value="editePolicyTwo")
	public ResultModel editePolicyTwo(){
		logger.info("updateGlobalInfo editePolicyTwo method start");
		String Ip = this.getRequest().getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		boolean isEnglish=Config.message.get("isEnglish")==null?false:Boolean.valueOf(Config.message.get("isEnglish").toString());
		try {

			String startTime=this.getRequest().getParameter("policyStartTime")==null?"":this.getRequest().getParameter("policyStartTime");
			String endTime=this.getRequest().getParameter("policyEndTime")==null?"":this.getRequest().getParameter("policyEndTime");
			SimpleDateFormat time1=new SimpleDateFormat("yyyy-MM-dd"); 
			String unitPrice=this.getRequest().getParameter("newunitPrice")==""?"0":this.getRequest().getParameter("newunitPrice");
			if(startTime.trim().length()==0){
				startTime=time1.format(new Date());
				endTime=startTime+" 23:59:59";
			}else{
				endTime=endTime+" 23:59:59";
			}
			String groupIds=this.getRequest().getParameter("groupIds")==null?"":this.getRequest().getParameter("groupIds");
			String timeIds=this.getRequest().getParameter("timeIds")==null?"":this.getRequest().getParameter("timeIds");
			String[] ips = this.getRequest().getParameterValues("ips");
			String[] medialUrl = this.getRequest().getParameterValues("industry");
			String pushUserType = this.getRequest().getParameter("pushUserType")==""?"0":this.getRequest().getParameter("pushUserType");
			String[] areas=this.getRequest().getParameterValues("areas");
			String[] urlDomains=this.getRequest().getParameterValues("urlDomain");
			String[] kewWords=this.getRequest().getParameterValues("kewWord");
			String[] customers=this.getRequest().getParameterValues("customerId");
			int policyId=Integer.parseInt(this.getRequest().getParameter("policyId").toString());
			String roamType=this.getRequest().getParameter("roamType")==""?"0":this.getRequest().getParameter("roamType");
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			if(isEnglish){ //国外不分本地 漫游 3A认证用户 统一默认为全网流量用户 ---4
				if(pushUserType.equals("0") || pushUserType.equals("1") || pushUserType.equals("2") || pushUserType.equals("3")){
					pushUserType="4";
				}
			}
			Map<String, Object> mapParm=new HashMap<String, Object>();
			mapParm.put("Policy_Status", 2);
			mapParm.put("Check_Status", 0);
			mapParm.put("Roam_Type",pushUserType);
			mapParm.put("unit_price",unitPrice);
			this.getCondition().put("condition", mapParm);
			this.getCondition().put("policyId", policyId);
			policyBusinessService.updataPolicyOne(this.getCondition());
			if(policyId>0){
				if(groupIds.length()!=0){//添加群组
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					policyBusinessService.policyDeleteGroup(this.getCondition());
					String[] groupIDs=groupIds.split(",");
					for(String groupId:groupIDs){
						Map<String, Object> mapGroup=new HashMap<String, Object>();
						mapGroup.put("Create_User", userId);
						mapGroup.put("Create_Time", time.format( new Date()));
						mapGroup.put("Policy_Id", policyId);
						mapGroup.put("Group_ID", groupId);
						this.getCondition().clear();
						this.getCondition().put("condition", mapGroup);
						this.getCondition().put("policyId", policyId);
						policyBusinessService.policyAddGroup(this.getCondition());
					}
				}
			
				//添加IPS
				if(ips!=null){
					this.getCondition().clear();
					List<Map<String,Object>> list = new ArrayList<>();
					for(String ipsId:ips){
						Map<String, Object> cond=new HashMap<String, Object>();
						cond.put("ipsId",ipsId);
						list.add(cond);
					}
					this.getCondition().put("policyId", policyId);
					this.getCondition().put("createUser", userId);
					this.getCondition().put("createTime",DateUtil.getDateString());
					this.getCondition().put("LIST",list);
					policyBusinessService.insertIpsInfo(this.getVisitId(),this.getCondition());
				}else{
					this.getCondition().clear();
					List<Map<String,Object>> ipslist = null;
					List<Map<String,Object>> list = new ArrayList<>();
					ipslist = policyBusinessService.sysIpsList(this.getVisitId(),this.getCondition());
					if(ipslist.size()>0){
						for (Map<String,Object> map : ipslist){
							Map<String, Object> cond=new HashMap<String, Object>();
							String ipsId = String.valueOf(map.get("ipsId"));
							cond.put("ipsId",ipsId);
							list.add(cond);
						}
						this.getCondition().put("policyId", policyId);
						this.getCondition().put("createUser", userId);
						this.getCondition().put("createTime",DateUtil.getDateString());
						this.getCondition().put("LIST",list);
						policyBusinessService.insertIpsInfo(this.getVisitId(),this.getCondition());
					}
				}
				//添加地区
				if(areas!=null){
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					policyBusinessService.policyDeleteCity(this.getCondition());
					for(String cityId:areas){
						Map<String, Object> cityIdMap=new HashMap<String, Object>();
						cityIdMap.put("Create_User", userId);
						cityIdMap.put("Create_Time", time.format( new Date()));
						cityIdMap.put("Province_Id", provincialId);
						if(cityId.equals(provincialId)){
							cityId="0";
						}
						if(cityId.contains("_")){
							String districtId=cityId.split("_")[1];
							cityIdMap.put("District_Id", districtId);
							cityId=cityId.split("_")[0];
						}
						cityIdMap.put("City_Id", cityId);
						cityIdMap.put("Policy_Id", policyId);
						cityIdMap.put("Relation_Type", 1);
						this.getCondition().clear();
						this.getCondition().put("condition", cityIdMap);
						policyBusinessService.policyAddCity(this.getCondition());
					}
				}

				//添加定向网址
				if(urlDomains!=null){
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					policyBusinessService.policyDeleteUrlDomain(this.getCondition());
					for(String urlDomain:urlDomains){
						Map<String, Object> cityIdMap=new HashMap<String, Object>();
						cityIdMap.put("Create_User", userId);
						cityIdMap.put("Create_Time", time.format( new Date()));
						cityIdMap.put("Url_Domain", urlDomain);
						cityIdMap.put("Policy_Id", policyId);
						cityIdMap.put("Url_Path", "");
						this.getCondition().clear();
						this.getCondition().put("condition", cityIdMap);
						this.getCondition().put("policyId", policyId);
						policyBusinessService.policyAddUrlDomain(this.getCondition());
					}
					//添加媒体URL
					if(medialUrl!=null){
						for(String m_Id:medialUrl){
							Map<String,Object> medialcon = new HashMap<>();
							medialcon.put("mediaId",m_Id);
							Map<String,Object> medial = sysMediaService.findMedilInfo(userId,medialcon);
							if(medial!=null && medial.get("mediaUrl")!=null){
								String url = String.valueOf(medial.get("mediaUrl"));
								Map<String, Object> cityIdMap=new HashMap<String, Object>();
								cityIdMap.put("Create_User", userId);
								cityIdMap.put("Create_Time", time.format( new Date()));
								cityIdMap.put("Policy_Id", policyId);
								cityIdMap.put("Url_Domain", url);
								cityIdMap.put("Url_Path", "");
								this.getCondition().clear();
								this.getCondition().put("condition", cityIdMap);
								policyBusinessService.policyAddUrlDomain(this.getCondition());
							}
						}
					}
				}
				//添加关键字
				if(kewWords!=null){
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					policyBusinessService.policyDeleteKeyWord(this.getCondition());
					for(String keyWord:kewWords){
						Map<String, Object> cityIdMap=new HashMap<String, Object>();
						cityIdMap.put("Create_User", userId);
						cityIdMap.put("Create_Time", time.format( new Date()));
						cityIdMap.put("Keyword_Name", keyWord);
						cityIdMap.put("Policy_Id", policyId);
						this.getCondition().clear();
						this.getCondition().put("condition", cityIdMap);
						this.getCondition().put("policyId", policyId);
						policyBusinessService.policyAddKeyWord(this.getCondition());
					}
				}

				//添加广告主
				if(customers!=null){
					policyBusinessService.policyDeleteCustomer(policyId);
					for(String customer:customers){
						Map<String, Object> cityIdMap=new HashMap<String, Object>();
						cityIdMap.put("Create_User", userId);
						cityIdMap.put("Create_Time", time.format( new Date()));
						cityIdMap.put("Policy_Id", policyId);
						cityIdMap.put("Customer_Id", customer);
						this.getCondition().clear();
						this.getCondition().put("condition", cityIdMap);
						policyBusinessService.policyAddCustomer(this.getCondition(),policyId);
					}
				}

				//添加推送时间
				String [] timesAll=timeIds.split(",");
				long times1=0,times2=0,times3=0,times4=0,times5=0,times6=0,times7=0;
				for(String timeid:timesAll){
					String [] stime=timeid.split("-");
					if(stime[0].equals("1")){
						times1 =times1+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("2")){
						times2 =times2+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("3")){
						times3 =times3+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("4")){
						times4 =times4+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("5")){
						times5 =times5+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("6")){
						times6 =times6+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}else if(stime[0].equals("7")){
						times7 =times7+(long)Math.pow(2, Integer.parseInt(stime[1]));
					}
				}
				System.out.println(times7);
				this.getCondition().put("policyId", policyId);
				policyBusinessService.policyDeleteTime(this.getCondition());
				if(times1!=0){
					double week=Math.pow(2,0);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", policyId);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times1);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
				if(times2!=0){
					double week=Math.pow(2,1);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", policyId);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times2);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
				if(times3!=0){
					double week=Math.pow(2,2);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", policyId);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times3);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
				if(times4!=0){
					double week=Math.pow(2,3);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", policyId);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times4);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
				if(times5!=0){
					double week=Math.pow(2,4);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", policyId);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times5);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}

				if(times6!=0){
					double week=Math.pow(2,5);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", policyId);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times5);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
                    
				if(times7!=0){
					double week=Math.pow(2,6);
					Map<String, Object> timeMap=new HashMap<String, Object>();
					timeMap.put("Create_User", userId);
					timeMap.put("Create_Time", time.format( new Date()));
					timeMap.put("Start_Time", startTime);
					timeMap.put("End_Time", endTime);
					timeMap.put("Policy_Id", policyId);
					timeMap.put("Cycle_Week", week);
					timeMap.put("Cycle_Hour", times7);
					timeMap.put("Cycle_Day", 2147483647);
					this.getCondition().clear();
					this.getCondition().put("policyId", policyId);
					this.getCondition().put("condition", timeMap);
					policyBusinessService.policyAddTime(this.getCondition());
				}
			}
			this.getModel().setResult(policyId);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
			sysLogRecordService.insertLogInfo(userId,"edit policy",1,"succeed",Ip,DateUtil.getDateString());
			Map<String,Object> con = new HashMap<>();
			con.put("policyId", policyId);
			Map<String,Object> policyInfo = policyBusinessService.singlePolicyInfo(this.getVisitId(),con);
			String policyName = String.valueOf(policyInfo.get("policyName"));
			boolean isSend = Boolean.valueOf(EmailUtil.message.get("isSendMail").toString());
			if(isSend){
				sendEmailService.sendPolicyWaitToAuditeEmail(userId,Ip,policyName);
			}
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("Abnormal operation");
			try {
				sysLogRecordService.insertLogInfo(userId,"modify the policy",2,"failed",Ip,DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("updateGlobalInfo editePolicyTwo method end");
		return this.getModel();
	}

	@ResponseBody
	@RequestMapping(value="editePolicyThree")
	public ResultModel editePolicyThree(){
		logger.info("updateGlobalInfo editePolicyThree method start");
		String Ip = this.getRequest().getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try {
			String pushUrl=this.getRequest().getParameter("pushUrl")==null?"":this.getRequest().getParameter("pushUrl");
			int policyId=Integer.parseInt(this.getRequest().getParameter("policyId").toString());
			Map<String, Object> mapParm=new HashMap<String, Object>();
			mapParm.put("Push_URL", pushUrl);
			mapParm.put("Check_Status", 0);
			mapParm.put("Policy_Status", 2);
			this.getCondition().put("condition", mapParm);
			this.getCondition().put("policyId", policyId);
			int result =policyBusinessService.updataPolicyOne(this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
			sysLogRecordService.insertLogInfo(userId,"modify policy",1,"succeed",Ip,DateUtil.getDateString());
			Map<String,Object> con = new HashMap<>();
			con.put("policyId", policyId);
			Map<String,Object> policyInfo = policyBusinessService.singlePolicyInfo(this.getVisitId(),con);
			String policyName = String.valueOf(policyInfo.get("policyName"));
			boolean isSend = Boolean.valueOf(EmailUtil.message.get("isSendMail").toString());
			if(isSend){
				sendEmailService.sendPolicyWaitToAuditeEmail(userId,Ip,policyName);
			}
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("Abnormal operation");
			try {
				sysLogRecordService.insertLogInfo(userId,"modify",2,"falied",Ip,DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("updateGlobalInfo editePolicyThree method end");
		return this.getModel();
	}

	/**
	 * 同步策略
	 * @return
     */
	/*@ResponseBody
	@RequestMapping(value = "synchronizePolicy")
	public ResultModel synchronizePolicy(){
		logger.info("synchronizePolicy method start");
		int result = -1;
		try {
			boolean bool = Boolean.valueOf(Config.message.get("icsStatusNew").toString());
			bool = true;

			if(bool){
				//result = policyBusinessService.quiteChangePllicy();
				result=0;
				if (result==0){
					this.getModel().setCode(ResultCodeConstants.SUCCESS);
					this.getModel().setDesc("同步中...");
				}else{
					this.getModel().setCode(ResultCodeConstants.SUCCESS);
					this.getModel().setDesc("同步操作异常");
				}
			}else{
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("未能发送同步请求，请检查配置（message.properties）");
			}
		} catch (Exception e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("同步操作异常");
			e.printStackTrace();
		}
		logger.info("synchronizePolicy method end");
		return this.getModel();
	}*/

	/**
	 * 查询同步进度
	 * @return
     */
	/*@ResponseBody
	@RequestMapping(value = "synchStatus")
	public ResultModel checkSynchronzeStatus(){
		logger.info("checkSynchronzeStatus method start");
		Map<String,Object> result = null;
		try {
			result = policySynchStatusService.getInfoByCondition(this.getVisitId(),this.getCondition());
			this.getModel().setResult(result);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
		logger.info("checkSynchronzeStatus method end");
		return this.getModel();
	}
	*/
	@RequestMapping(value="synch")
	public String toSynch(){
		return "accountmanage/testSynch";
	}
	/**
	 * 读取地区
	 */
	@ResponseBody
	@RequestMapping(value="sysCityList")
	public ResultModel sysCityList(){
		logger.info("sysCityList method start");
		Map<String,Object> result = new HashMap();
		try {
			if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
				int cityId = Integer.parseInt(this.getSession().getAttribute("cityId").toString());
				this.getCondition().put("cityId", cityId);
				List resultDist = policyBusinessService.sysCityDistrict(this.getVisitId(),this.getCondition());
				List citylist = policyBusinessService.sysCityList(this.getVisitId(),this.getCondition());
				result.put("CITYDIST", resultDist);
				result.put("CITYLIST", citylist);
			}else{
				int provinceId = Integer.parseInt(this.getSession().getAttribute("provinceId").toString());
				this.getCondition().put("provinceId", provinceId);
				List citylist = policyBusinessService.sysCityList(this.getVisitId(),this.getCondition());
				List resultDist = policyBusinessService.sysCityDistrict(this.getVisitId(),this.getCondition());
//				for(int i=0;i<citylist.size();i++){
//					Map map=(Map)citylist.get(i);
//					String cityId=map.get("CityId").toString();
//					this.getCondition().put("cityId", cityId);
//					
//					Map<String,Object> resultTemp = new HashMap();
//					result.put("CITY_"+cityId, resultDist);
//				}
				result.put("CITYDIST", resultDist);
				result.put("CITYLIST", citylist);
			}
			this.getModel().setResult(result);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
		logger.info("sysCityList method end");
		return this.getModel();
	}
	/**
	 * 读取地区
	 */
	@ResponseBody
	@RequestMapping(value="sysCityDistrict")
	public ResultModel sysCityDistrict(){
		logger.info("sysCityDistrict method start");
		List<Map<String,Object>> result = null;
		try {
			int cityId = Integer.parseInt(this.getRequest().getParameter("cityId").toString());
			this.getCondition().put("cityId", cityId);
			result = policyBusinessService.sysCityDistrict(this.getVisitId(),this.getCondition());
			this.getModel().setResult(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		logger.info("sysCityDistrict method end");
		return this.getModel();
	}
	/**
	 * 读取ips地区
	 */
	@ResponseBody
	@RequestMapping(value="sysIpsList")
	public ResultModel sysIpsList(){
		logger.info("sysCityDistrict method start");
		Map<String,Object> result = new HashMap<>();
		List<Map<String,Object>> ipslist = null;
		List<Map<String,Object>> ipsCity = null;
		try {
			if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
				int cityId = Integer.parseInt(this.getSession().getAttribute("cityId").toString());
				this.getCondition().put("cityId", cityId);
				ipslist = policyBusinessService.sysIpsList(this.getVisitId(),this.getCondition());
				ipsCity = policyBusinessService.sysIpsCityList(this.getVisitId(),this.getCondition());
			}else{
				int provinceId = Integer.parseInt(this.getSession().getAttribute("provinceId").toString());
				this.getCondition().put("provinceId", provinceId);
				ipslist = policyBusinessService.sysIpsList(this.getVisitId(),this.getCondition());
				ipsCity = policyBusinessService.sysIpsCityList(this.getVisitId(),this.getCondition());
			}
			result.put("IPSLIST",ipslist);
			result.put("IPSCITY",ipsCity);
			this.getModel().setResult(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		logger.info("sysCityDistrict method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="searchMedia")
	public ResultModel searchMedia(){
		logger.info("sysCityList method start");
		
		try {
			int industryId= Integer.parseInt(this.getRequest().getParameter("industryId").toString());
			this.getCondition().put("industryId", industryId);

			Map<String,Object> resultMedia= sysMediaService.searchMedia(this.getVisitId(),this.getCondition());
			this.getModel().setResult(resultMedia);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
		logger.info("sysCityList method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="searchPrice")
	public ResultModel searchPrice(){
		logger.info("sysCityList method start");
		
		try {
			int mediaId= Integer.parseInt(this.getRequest().getParameter("mediaId").toString());
			this.getCondition().put("mediaId", mediaId);
			Map<String,Object> resultMedia= sysMediaService.searchPrice(this.getVisitId(),this.getCondition());
			this.getModel().setResult(resultMedia);
			
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
		logger.info("searchPrice method end");
		return this.getModel();
	}
	@ResponseBody
	@RequestMapping(value="findChildIndusty")
	public ResultModel findChildIndusty(){
		logger.info("findChildIndusty method start");
		
		try {
			int parent_id= Integer.parseInt(this.getRequest().getParameter("parent_id").toString());
			this.getCondition().put("parent_id", parent_id);
			Map<String,Object> resultMedia= sysIndustryService.findChildIndustry(this.getVisitId(),this.getCondition());
			this.getModel().setResult(resultMedia);
			
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
		logger.info("findChildIndusty method end");
		return this.getModel();
	}

	@ResponseBody
	@RequestMapping(value="searchCustomer")
	public ResultModel searchCustomer(){
		logger.info("searchCustomer method start");
		
		try {
			this.getCondition().clear();
			List<Map<String,Object>> resultCustomer= customerManageService.searchCustomer(this.getVisitId(),this.getCondition());
			Map<String,Object> result = new HashMap<>();
			result.put("list",resultCustomer);
			this.getModel().setResult(result);
		} catch (ServiceException e) {
			e.printStackTrace();
			this.getRequest().setAttribute("customerName",null);
			logger.error(e);
		}
		logger.info("resultCustomer method end");
		return this.getModel();
	}
	@ResponseBody
	@RequestMapping(value="findByIndustry")
	public ResultModel findByIndustry() {
		logger.info("findByIndustry method start");
		try {
			Map<String,Object> result =sysIndustryService.findByIndustry(this.getVisitId(), this.getCondition());
			List<Map<String,Object>> media = sysMediaService.findAllMedia(this.getVisitId(),this.getCondition());
			result.put("MEDIA",media);
			this.getModel().setResult(result);
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("Abnormal operation");
		}
		logger.info("findByIndustry method end");
		return this.getModel();
	}
	
	
	@ResponseBody
	@RequestMapping(value="findAllIndustry")
	public ResultModel findAllIndustry() {
		logger.info("findAllIndustry method start");
		try {
			Map<String,Object> resultGlobal =policyBusinessService.findAllIndustry(this.getVisitId(), this.getCondition());
			this.getModel().setResult(resultGlobal);
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("Abnormal operation");
		}
		
		logger.info("findAllIndustry method end");
		return this.getModel();
	}

	@ResponseBody
	@RequestMapping(value="findFirstIndustry")
	public String findFirstIndustry() {
		logger.info("findFirstIndustry method start");
		try {
			Map<String,Object> resultGlobal =sysIndustryService.findFirstIndustry(this.getVisitId(), this.getCondition());
			this.getModel().setResult(resultGlobal);
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("Abnormal operation");
		}
		
		logger.info("findFirstIndustry method end");
		return  "push/createpolicy";
	}
}
