package com.datacomo.wins.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.datacomo.wins.base.socket.im.proto.IMProtobuf;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.push.bean.UrlFilter;
import com.datacomo.wins.push.bean.UserFilter;
import com.datacomo.wins.service.SysRoleInfoService;
import com.datacomo.wins.util.DataUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.BlacklistService;
import com.datacomo.wins.service.SysLogRecordService;
import com.datacomo.wins.util.DateUtil;
import com.datacomo.wins.util.InvalidTimeUtil;

/**
 * 黑名单管理控制器
 * @author liwenjie
 *
 */
@Controller
@RequestMapping(value="/blacklist")
public class BlacklistController extends BaseController {
	private static Logger logger = Logger.getLogger(BlacklistController.class.getName());

	@Autowired
	BlacklistService blacklistService;
	@Autowired
	SysLogRecordService sysLogRecordService;
	@Autowired
	private SysRoleInfoService sysRoleInfoService;
	@Autowired
	private HttpServletRequest request;
	//用户黑名单文件上传地址
    @Value("#{configProperties['uploadBlackUserTxtDir']}")
    private String uploadBlackUserDir;
    //网址黑名单文件上传地址
    @Value("#{configProperties['uploadBlackUrlTxtDir']}")
    private String uploadBlackUrlDir;
/**
 * 	
 * @return 用户黑名单总信息
 */
	@RequestMapping(value="userBlacklist")
	public String userBlacklist(){
		logger.info("BlacklistController userBlacklist method start");
		//查询条件
		String a=uploadBlackUrlDir;
		int showStatus = Integer.parseInt(String.valueOf(this.getSession().getAttribute("showStatus")));
		this.getCondition().put("showStatus",showStatus);
		String FilterUser=this.getRequest().getParameter("FilterUser");
		String cityId = "";
		if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
			cityId = this.getSession().getAttribute("cityId").toString();
			this.getCondition().put("cityId", cityId);
		}
		try {
			if(StringUtils.isNotBlank(FilterUser)){
				FilterUser=new String(FilterUser.getBytes("ISO-8859-1"), "utf-8");
				String fu=FilterUser.replaceAll(" ", "");
				this.getCondition().put("FilterUser",fu);
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String UserStatus=this.getRequest().getParameter("UserStatus")==""?null:this.getRequest().getParameter("UserStatus");
		if(UserStatus!=null){
			int user=Integer.parseInt(UserStatus);
			this.getCondition().put("UserStatus", user);
		}
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			Map<String, Object> result = blacklistService.userBlacklist(this.getVisitId(),this.getCondition());
			result.put("showStatus",showStatus);
			this.getRequest().setAttribute("result", result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("System exception, please try again later.");
		}
		
		logger.info("BlacklistController userBlacklist method end");
		return "blacklist/blackUser";
	}

	/**
	 * 添加IP段 用户黑名单
	 * @return
     */
	@ResponseBody
	@RequestMapping(value = "/createBlackIPs")
	public ResultModel createBlackUserByIPs(){
		logger.info("createBlackUserByIPs method start");
		String logIp=request.getRemoteAddr();
		int userId = this.getVisitId();
		String startIP=this.getRequest().getParameter("startIP");
		String endIP=this.getRequest().getParameter("endIP");
		String UserStatus=this.getRequest().getParameter("UserStatus");
		String PolicyId=this.getRequest().getParameter("PolicyId");
		String[] sIPs = startIP.split("\\.");
		String[] eIPs = endIP.split("\\.");
		int[] startIPInt = DataUtil.stringToInt(sIPs);
		int[] endIPInt = DataUtil.stringToInt(eIPs);
		List<Map<String,Object>> ipList = new ArrayList<>();
		int result = 0;
		if(startIPInt[2]==endIPInt[2]){
			for(int i=0;i<(endIPInt[3]-startIPInt[3]+1);i++){
				Map<String,Object> condition = new HashMap<>();
				String IP = endIPInt[0]+"."+endIPInt[1]+"."+endIPInt[2]+"."+(startIPInt[3]+i);
				condition.put("Filter_User",IP);
				ipList.add(condition);
			}
		}else{
			for(int i=0;i<(endIPInt[2]-startIPInt[2]+1);i++){
				if(i==0){
					for(int j=0;j<255-startIPInt[3]+1;j++){
						Map<String,Object> condition = new HashMap<>();
						String IP = endIPInt[0]+"."+endIPInt[1]+"."+startIPInt[2]+"."+(startIPInt[3]+j);
						condition.put("Filter_User",IP);
						ipList.add(condition);
					}
				}else if(i==(endIPInt[2]-startIPInt[2])){
					for(int j=0;j<endIPInt[3];j++){
						Map<String,Object> condition = new HashMap<>();
						String IP = endIPInt[0]+"."+endIPInt[1]+"."+endIPInt[2]+"."+(j+1);
						condition.put("Filter_User",IP);
						ipList.add(condition);
					}
				}else{
					for(int j=0;j<255;j++){
						Map<String,Object> condition = new HashMap<>();
						String IP = endIPInt[0]+"."+endIPInt[1]+"."+i+"."+(j+1);
						condition.put("Filter_User",IP);
						ipList.add(condition);
					}
				}
				for(int j=0;j<((endIPInt[3]-startIPInt[3])+1);j++){
					Map<String,Object> condition = new HashMap<>();
					String IP = endIPInt[0]+"."+endIPInt[1]+"."+(startIPInt[2]+i)+"."+(startIPInt[3]+j);
					condition.put("Filter_User",IP);
					ipList.add(condition);
				}
			}
		}
		this.getCondition().put("User_Status",UserStatus);
		this.getCondition().put("Policy_Id", PolicyId);
		this.getCondition().put("Invalid_Time",InvalidTimeUtil.getEndTimeStr(Integer.parseInt(UserStatus)));//失效时间
		this.getCondition().put("Create_User",userId);
		this.getCondition().put("Create_Time", DateUtil.getDateString());
		this.getCondition().put("Effect_Time", DateUtil.getDateString());
		this.getCondition().put("LIST", ipList);
		try {
			result = blacklistService.insertBlackUsers(this.getVisitId(),this.getCondition());//创建黑名单
			if(result>=1){
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
				this.getModel().setDesc("succeed");
				sysLogRecordService.insertLogInfo(userId, "add", 1, "Add blacklist by IP success ", logIp, DateUtil.getDateString());
			}else{
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
				this.getModel().setDesc("failed");
				sysLogRecordService.insertLogInfo(userId, "add", 2, "Add blacklist by IP failed", logIp, DateUtil.getDateString());
			}
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("System exception, please try again later.");
			e.printStackTrace();
			logger.error(e);
			try {
				sysLogRecordService.insertLogInfo(userId, "add", 2, "Add blacklist by IP failed", logIp, DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("createBlackUserByIPs method end");
		return this.getModel();
	}

	/**
	 *
	 * @return	创建用户黑名单
	 */
	@ResponseBody
	@RequestMapping(value="createBlackUser")
    public ResultModel createBlackUser(){
        logger.info("createBlackUser method start");
        String ip=request.getRemoteAddr();
        int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
        String FilterUser=this.getRequest().getParameter("FilterUser");
        int UserStatus=Integer.parseInt(this.getRequest().getParameter("UserStatus"));
        int PolicyId=Integer.parseInt(this.getRequest().getParameter("PolicyId"));
		UserFilter userFilter = new UserFilter();
		userFilter.setFilterUser(FilterUser);
		userFilter.setUserStatus(UserStatus);
		userFilter.setPolicyId(PolicyId);
		userFilter.setCreateUser(userId);
		userFilter.setCreateTime(DateUtil.getDateString());
		userFilter.setInvalidTime(InvalidTimeUtil.getEndTimeStr(UserStatus));
		Map condition = new HashMap<String,Object>();
		condition.put("Filter_User",FilterUser);
		condition.put("User_Status",UserStatus);
		condition.put("Policy_Id", PolicyId);
		condition.put("Invalid_Time", InvalidTimeUtil.getEndTimeStr(UserStatus));//失效时间
		condition.put("Create_User",userId);
		condition.put("Create_Time", DateUtil.getDateString());
		condition.put("Effect_Time", DateUtil.getDateString());
		this.getCondition().put("condition", condition);
		int result = 0;
        try {
			result = blacklistService.insertBlackUser(this.getVisitId(),userFilter,this.getCondition());
			if(result==1){
				sysLogRecordService.insertLogInfo(userId, "add", 1, "create the black list successfully", ip, DateUtil.getDateString());
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
				this.getModel().setDesc("succeed");
			}else{
				sysLogRecordService.insertLogInfo(userId, "add", 2, "create the black list failed", ip, DateUtil.getDateString());
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("failed");
			}
        } catch (ServiceException e) {
        	try {
				sysLogRecordService.insertLogInfo(userId, "add", 2, "create the black list failed", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("System exception, please try again later.!");
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("createBlackUser method end");
        return this.getModel();
    }

/**
 * 
 * @return 删除用户黑名单
 */
@ResponseBody
@RequestMapping(value="delUser")
	public ResultModel delUser(){
	logger.info("BlacklistController delUser method start");
	String ip=request.getRemoteAddr();
	int UserId=Integer.parseInt(this.getRequest().getParameter("UserId").toString());
	String filterUser = this.getRequest().getParameter("filterUser");
	int policyId = Integer.parseInt(this.getRequest().getParameter("policyId").toString());
	int userStatus = Integer.parseInt(this.getRequest().getParameter("userStatus").toString());
	UserFilter userFilter = new UserFilter();
	userFilter.setFilterUser(filterUser);
	userFilter.setPolicyId(policyId);
	userFilter.setUserStatus(userStatus);
	userFilter.setCreateTime(DateUtil.getDateString());
	int result=0;
	try {
		result = blacklistService.delUser(this.getVisitId(),userFilter,UserId);
		if(result==1){
			sysLogRecordService.insertLogInfo(UserId, "delete", 1, "delete the black list", ip, DateUtil.getDateString());
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		}else{
			sysLogRecordService.insertLogInfo(UserId, "delete", 2, "failed", ip, DateUtil.getDateString());
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("failed");
		}
	} catch (Exception e) {
		try {
			sysLogRecordService.insertLogInfo(UserId, "delete", 2, "abnormal operation", ip, DateUtil.getDateString());
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.getModel().setDesc("System exception, please try again later.");
	}
	logger.info("BlacklistController delUser method end");
	return this.getModel();
	}
/**
 * 	
 * @return 获取策略信息
 */
@ResponseBody
@RequestMapping(value="showPolicy")
	public Map<String, Object> showPolicy(){
	logger.info("BlacklistController showPolicy method start");
	int showStatus = Integer.parseInt(String.valueOf(this.getSession().getAttribute("showStatus")));
	this.getCondition().put("showStatus",showStatus);
	Map<String, Object> result=null;
	try {
		result = blacklistService.showPolicy(this.getVisitId(),this.getCondition());
		this.getRequest().setAttribute("result", result);
		this.getModel().setDesc("succeed");
	} catch (ServiceException e) {
		this.getModel().setCode(ResultCodeConstants.ERROR);
		this.getModel().setDesc("System exception, please try again later.");
	}
	
	logger.info("BlacklistController showPolicy method end");
	return result;
	}
	/**
	 * 导入txt文件创建用户黑名单
	 * @param blackfile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "createBlackUsers")
	public ResultModel  createBlackUser(@RequestParam MultipartFile blackfile){
		logger.info("createBlackList method start");
		String ip=request.getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		int UserStatus=Integer.parseInt(this.getRequest().getParameter("UserStatus"));
		int PolicyId=Integer.parseInt(this.getRequest().getParameter("PolicyId"));
		if(PolicyId==0){
			PolicyId = -1;
		}
		this.getCondition().put("User_Status",UserStatus);
		this.getCondition().put("Policy_Id", PolicyId);
		this.getCondition().put("Invalid_Time", InvalidTimeUtil.getEndTimeStr(UserStatus));//失效时间
		this.getCondition().put("Create_User",this.getSession().getAttribute("userId"));
		this.getCondition().put("Create_Time", DateUtil.getDateString());
		this.getCondition().put("Effect_Time", DateUtil.getDateString());

		List<String> userList = new ArrayList<String>();
		InputStreamReader isr= null;
		String value=null;
		int isUser = 0;
		int result = 0;
		try {
			isr = new InputStreamReader(blackfile.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			while((value=br.readLine()) !=null){
				if(value.indexOf("'")!=-1 || value.indexOf("%")!=-1 || value.indexOf("\"")!=-1){
					continue;
				}
				if(value.indexOf("；")!=-1){
					value = value.substring(0,value.indexOf("；"));
				}
				if(value.indexOf(";")!=-1){
					value = value.substring(0,value.indexOf(";"));
				}
				if(value.trim()!="" && value.trim()!=null){
					userList.add(value.trim());
				}
			}
			isr.close();
			List<Map<String, Object>> lis=new ArrayList<Map<String, Object>>();
			for(String user:userList){
				Map<String,Object> condition = new HashMap<String,Object>();
				condition.put("Filter_User", user);
				lis.add(condition);
				isUser ++;
			}
			this.getCondition().put("LIST", lis);
			result = blacklistService.insertBlackUsers(this.getVisitId(),this.getCondition());//创建黑名单
			if(result>=1){
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
				this.getModel().setDesc("succeed");
				sysLogRecordService.insertLogInfo(userId, "add", 1, "Import the black list successfully", ip, DateUtil.getDateString());
			}else{
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
				this.getModel().setDesc("failed");
				sysLogRecordService.insertLogInfo(userId, "add", 2, "Import the black list failed", ip, DateUtil.getDateString());
			}
		} catch (Exception e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("System exception, please try again later.");
			e.printStackTrace();
			logger.error(e);
			try {
				sysLogRecordService.insertLogInfo(userId, "add", 2, "System exception, please try again later.", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("createBlackList method end");
		return this.getModel();
	}

	/**
	 *
	 * @return 网址黑名单总信息
	 */
	@RequestMapping(value="urlBlacklist")
	public String urlBlacklist(){
		logger.info("BlacklistController urlBlacklist method start");
		//查询条件
		int showStatus = Integer.parseInt(String.valueOf(this.getSession().getAttribute("showStatus")));
		this.getCondition().put("showStatus",showStatus);
		String UrlDomain=this.getRequest().getParameter("UrlDomain");
		try {
			if(StringUtils.isNotBlank(UrlDomain)){
				UrlDomain=new String(UrlDomain.getBytes("ISO-8859-1"), "utf-8");
				this.getCondition().put("UrlDomain",UrlDomain);
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			Map<String, Object> result = blacklistService.urlBlacklist(this.getVisitId(),this.getCondition());
			result.put("showStatus",showStatus);
			this.getRequest().setAttribute("result", result);
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("System exception, please try again later.");
		}
		logger.info("BlacklistController urlBlacklist method end");
		return "blacklist/blackUrl";
	}
	/**
	 * 	
	 * @return	创建网址黑名单
	 */
		@ResponseBody
		@RequestMapping(value="createBlackUrl")
	    public ResultModel createBlackUrl(){
	        logger.info("createBlackUrl method start");
	        String ip=request.getRemoteAddr();
	        int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
	        String UrlDomain=this.getRequest().getParameter("UrlDomain");
	        String UrlPath=this.getRequest().getParameter("UrlPath");
			int index = UrlDomain.indexOf("*");
			if(index!=-1){
				if(UrlDomain.indexOf("/*")!=-1){
					UrlDomain = UrlDomain.substring(0,UrlDomain.indexOf("/*"));
				}else if(UrlDomain.indexOf(".*")!=-1){
					UrlDomain = UrlDomain.substring(0,UrlDomain.indexOf(".*"));
				}else{
					UrlDomain=UrlDomain.replace("*","");
				}
			}
			Map condition = new HashMap<String,Object>();
			condition.put("Url_Domain",UrlDomain);
	        UrlFilter urlFilter = new UrlFilter();
			urlFilter.setUrlDomain(UrlDomain);
	        try {
	        	if(StringUtils.isNotBlank(UrlPath)){
	        		UrlPath=new String(UrlPath.getBytes("ISO-8859-1"),"utf-8");
					urlFilter.setUrlPath(UrlPath);
					condition.put("Url_Path",UrlPath);
				}else{
					urlFilter.setUrlPath("");
					condition.put("Url_Path","");
				}
	        } catch (UnsupportedEncodingException e1) {
	        	// TODO Auto-generated catch block
	        	e1.printStackTrace();
	        }
			urlFilter.setUrlStatus(1);
	        condition.put("Url_Status", 1);
			urlFilter.setCreateUser(userId);
	        condition.put("Create_User",userId);
			urlFilter.setCreateTime(DateUtil.getDateString());
	        condition.put("Create_Time", DateUtil.getDateString());
			urlFilter.setUrlId(0);
			int count = -1;
			try {
				count = blacklistService.getBlackUrlcount(condition);
			} catch (MapperException e) {
				e.printStackTrace();
				logger.error(e);
			}
			if(count ==0){
				this.getCondition().put("condition", condition);
				int result = 0;
				try {
					result = blacklistService.insertBlackUrl(this.getVisitId(),urlFilter,this.getCondition());
					if(result==1){
						sysLogRecordService.insertLogInfo(userId, "add", 1, "Create the website black list successfully", ip, DateUtil.getDateString());
						this.getModel().setCode(ResultCodeConstants.SUCCESS);
						this.getModel().setDesc("succeed");
					}else{
						sysLogRecordService.insertLogInfo(userId, "add", 2, "Create the website black list failed", ip, DateUtil.getDateString());
						this.getModel().setCode(ResultCodeConstants.ERROR);
						this.getModel().setDesc("failed");
					}
				} catch (ServiceException e) {
					try {
						sysLogRecordService.insertLogInfo(userId, "add", 2, "abnormal operation", ip, DateUtil.getDateString());
					} catch (ServiceException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.getModel().setCode(ResultCodeConstants.ERROR);
					this.getModel().setDesc("System exception, please try again later.");
					e.printStackTrace();
					logger.error(e);
				}
			}else{
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("already existed");
			}
	        logger.info("createBlackUrl method end");
	        return this.getModel();
	    }

	/**
	 * 创建网站IP黑名单
	 * @return
     */
	@ResponseBody
	@RequestMapping(value = "createBlackUrlIP")
	public ResultModel createBlackUrlIP(){
		logger.info("createBlackUrlIP method start");
		String ip=request.getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		String UrlIP=this.getRequest().getParameter("UrlIP")==null?"":this.getRequest().getParameter("UrlIP");
		if(UrlIP==""){
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("System exception, please try again later.");
			return this.getModel();
		}
		Map condition = new HashMap<String,Object>();
		UrlFilter urlFilter = new UrlFilter();
		condition.put("Url_Domain",UrlIP);
		condition.put("Url_Path","");
		urlFilter.setUrlDomain(UrlIP);
		urlFilter.setUrlStatus(1);
		condition.put("Url_Status",1);
		urlFilter.setCreateUser(userId);
		condition.put("Create_User",userId);
		urlFilter.setCreateTime(DateUtil.getDateString());
		condition.put("Create_Time", DateUtil.getDateString());
		urlFilter.setUrlId(0);
		int count = -1;
		try {
			count = blacklistService.getBlackUrlcount(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
		}
		if(count ==0){
			this.getCondition().put("condition", condition);
			int result = 0;
			try {
				result = blacklistService.insertBlackUrl(this.getVisitId(),urlFilter,this.getCondition());
				if(result==1){
					sysLogRecordService.insertLogInfo(userId, "add", 1, "Create the website black list successfully", ip, DateUtil.getDateString());
					this.getModel().setCode(ResultCodeConstants.SUCCESS);
					this.getModel().setDesc("succeed");
				}else{
					sysLogRecordService.insertLogInfo(userId, "add", 2, "Create the website black list failed", ip, DateUtil.getDateString());
					this.getModel().setCode(ResultCodeConstants.ERROR);
					this.getModel().setDesc("failed");
				}
			} catch (ServiceException e) {
				try {
					sysLogRecordService.insertLogInfo(userId, "add", 2, "abnormal operation", ip, DateUtil.getDateString());
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("System exception, please try again later.");
				e.printStackTrace();
				logger.error(e);
			}
		}else{
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("already existed");
		}

		logger.info("createBlackUrlIP method end");
		return this.getModel();
	}

	@ResponseBody
	@RequestMapping(value = "createBlackUrlIPs")
	public ResultModel createBlackUrlIPs(@RequestParam MultipartFile urlIPfile){
		logger.info("createBlackUrlIPs method start");
		String ip=request.getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		this.getCondition().put("Url_Status", 1);
		this.getCondition().put("Create_User",userId);
		this.getCondition().put("Create_Time", DateUtil.getDateString());
		List<String> urlList = new ArrayList<String>();
		InputStreamReader isr= null;
		String value=null;
		int isExist = 0;
		int result = 0;
		try {
			isr = new InputStreamReader(urlIPfile.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			while((value=br.readLine()) !=null){
				if(value.indexOf("'")!=-1 || value.indexOf("%")!=-1 || value.indexOf("\"")!=-1){
					continue;
				}
				if(value.indexOf(",")!=-1){
					value = value.substring(0,value.indexOf(","));
				}
				if(value.indexOf("，")!=-1){
					value = value.substring(0,value.indexOf("，"));
				}
				if(value.indexOf("；")!=-1){
					value = value.substring(0,value.indexOf("；"));
				}
				if(value.indexOf(";")!=-1){
					value = value.substring(0,value.indexOf(";"));
				}
				if(value!="" && value!=null){
					urlList.add(value);
				}
			}
			isr.close();
			List<Map<String, Object>> lis=new ArrayList<Map<String, Object>>();
			for(String IP:urlList){
				Map<String,Object> condition = new HashMap<String,Object>();
				if(IP==""){
					continue;
				}
				condition.put("Url_Domain",IP);
				condition.put("Url_Path","");
				condition.put("Url_Status",1);
				int count = -1;
				count = blacklistService.getBlackUrlcount(condition);
				if(count == 0){
					lis.add(condition);
				}else if(count>0){
					isExist ++ ;
				}
			}
			if (!lis.isEmpty()){
				this.getCondition().put("LIST", lis);
				result = blacklistService.insertBlackUrls(this.getVisitId(),this.getCondition());//创建黑名单
			}
			if(result>=1){
				sysLogRecordService.insertLogInfo(userId, "add", 1, "Import the website black list", ip, DateUtil.getDateString());
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
				this.getModel().setDesc("succeed！");
			}else {
				sysLogRecordService.insertLogInfo(userId, "add", 2, "Import the website black list failed", ip, DateUtil.getDateString());
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("already existed！");
			}
		} catch (Exception e) {
			try {
				sysLogRecordService.insertLogInfo(userId, "add", 2, "Import the website black list failed", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("System exception, please try again later.");
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("createBlackUrlIPs method end");
		return this.getModel();
	}



	/**
	 * 导入txt文件创建网址黑名单
	 * @param blackfile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "createBlackUrls")
	public ResultModel  createBlackUrls(@RequestParam MultipartFile blackfile){
		logger.info("createBlackUrls method start");
		String ip=request.getRemoteAddr();
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		this.getCondition().put("Url_Status", 1);
		this.getCondition().put("Create_User",this.getSession().getAttribute("userId"));
		this.getCondition().put("Create_Time", DateUtil.getDateString());

		//String reg= "([\\w\\d]+\\.)+\\w{2,}(\\/[\\d\\w\\.\\?%=&]+)*";
		List<String> urlList = new ArrayList<String>();
		InputStreamReader isr= null;
		String value=null;
		int isExist = 0;
		int result = 0;
		try {
			isr = new InputStreamReader(blackfile.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			while((value=br.readLine()) !=null){
				if(value.indexOf("'")!=-1 || value.indexOf("%")!=-1 || value.indexOf("\"")!=-1){
					continue;
				}
				if(value.indexOf(",")!=-1){
					value = value.substring(0,value.indexOf(","));
				}
				if(value.indexOf("，")!=-1){
					value = value.substring(0,value.indexOf("，"));
				}
				if(value.indexOf("；")!=-1){
					value = value.substring(0,value.indexOf("；"));
				}
				if(value.indexOf(";")!=-1){
					value = value.substring(0,value.indexOf(";"));
				}
				if(value!="" && value!=null){
					urlList.add(value);
				}
			}
			isr.close();
			List<Map<String, Object>> lis=new ArrayList<Map<String, Object>>();
			for(String url:urlList){
				if(url==""){
					continue;
				}
				Map<String,Object> condition = new HashMap<String,Object>();
				if(url.indexOf("/") != -1){
					condition.put("Url_Domain",url.substring(0,url.indexOf("/")));
					condition.put("Url_Path",url.substring(url.indexOf("/")));
				}else{
					condition.put("Url_Domain",url);
					condition.put("Url_Path","");
				}
				condition.put("Url_Status",1);
				int count = -1;
				count = blacklistService.getBlackUrlcount(condition);
				if(count == 0){
					lis.add(condition);
				}else if(count>0){
					isExist ++ ;
				}
			}
			if (!lis.isEmpty()){
				this.getCondition().put("LIST", lis);
				result = blacklistService.insertBlackUrls(this.getVisitId(),this.getCondition());//创建黑名单
			}
			if(result>=1){
				sysLogRecordService.insertLogInfo(userId, "add", 1, "Import the website black list", ip, DateUtil.getDateString());
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
				this.getModel().setDesc("succeed！");
			}else {
				sysLogRecordService.insertLogInfo(userId, "add", 2, "Import the website black list failed", ip, DateUtil.getDateString());
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("already existed！");
			}
		} catch (Exception e) {
			try {
				sysLogRecordService.insertLogInfo(userId, "add", 2, "Import the website black list failed", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("System exception, please try again later.");
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("createBlackUrls method end");
		return this.getModel();
	}

/**
 * 	
 * @return 删除网址黑名单
 */
@ResponseBody
@RequestMapping(value="delUrl")
	public ResultModel delUrl(){
	logger.info("BlacklistController delUser method start");
    String ip=request.getRemoteAddr();
    int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
	int urlId=Integer.parseInt(this.getRequest().getParameter("urlId").toString());
	String urlDomain = this.getRequest().getParameter("urlDomain");
	String urlPath = this.getRequest().getParameter("urlPath");
	String urlStatus = this.getRequest().getParameter("urlStatus");
	UrlFilter urlFilter = new UrlFilter();
	urlFilter.setUrlId(urlId);
	urlFilter.setUrlDomain(urlDomain);
	if(!urlPath.equals("/")){
		urlFilter.setUrlPath(urlPath);
	}
	urlFilter.setUrlStatus(Integer.parseInt(urlStatus));
	int result=0;
	try {
		result = blacklistService.delUrl(this.getVisitId(),urlFilter,urlId);
		if(result==1){
			sysLogRecordService.insertLogInfo(userId, "delete", 1, "delete the black list successfully", ip, DateUtil.getDateString());
			this.getModel().setDesc("succeed");
		}else{
			sysLogRecordService.insertLogInfo(userId, "delete", 2, "delete the black list failed", ip, DateUtil.getDateString());
			this.getModel().setDesc("failed");
		}
	} catch (Exception e) {
		try {
			sysLogRecordService.insertLogInfo(userId, "delete", 2, "delete the black list failed", ip, DateUtil.getDateString());
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.getModel().setDesc("System exception, please try again later.！");
	}
	logger.info("BlacklistController delUser method end");
	return this.getModel();
	}
}
