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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.ServerIdHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sendmessage.Utils;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysSmsService;
import com.datacomo.wins.service.SysUserInfoService;
import com.datacomo.wins.util.Client;
import com.datacomo.wins.util.CookiesUtil;
import com.datacomo.wins.util.MD5;

/**
 * @author gongkaihui
 *
 *	用户基本信息控制类
 */

@Controller
@RequestMapping(value="/user")//url映射方法前缀
public class SysUserInfoController extends BaseController{
	private static Logger logger = Logger.getLogger(SysUserInfoController.class.getName());
	
	@Autowired
	SysUserInfoService sysUserInfoService;
	@Autowired
	private Client client;
	@Autowired
	private SysSmsService sysSmsService;
	@Value("#{configProperties['provincialId']}")
	private String provincialId;
	private int count=0;
	
	//用户登录
		@RequestMapping(value="login",method=RequestMethod.POST)
		@ResponseBody
		public ResultModel login(){
			logger.info("SysUserInfoController login method start");
			String username = this.getRequest().getParameter("username") !=null ? this.getRequest().getParameter("username").trim():this.getRequest().getParameter("username");
			int logoTemp = this.getRequest().getParameter("logoTemp") ==null ? 1:Integer.parseInt(this.getRequest().getParameter("logoTemp"));
			//username="admin";
			String pwd = this.getRequest().getParameter("password");
			//pwd="admin";
			String code = this.getRequest().getParameter("code");
			String msgcode = this.getRequest().getParameter("msgcode");
			
			String remerberPwd = this.getRequest().getParameter("remerberPwd");
			
			String remaddr=this.getRequest().getRemoteAddr();
			code="1234";
			String dynMsg = (String) this.getSession().getAttribute("dynMsg");
			Map<String,String> map =new HashMap<String,String>();
			if(StringUtils.isNotBlank(username)){
				if(StringUtils.isNotBlank(pwd)){
					String dyncode="1234";
					
						try {
							this.getCondition().put("userName", username);
							
							Map<String, Object> result1 = sysUserInfoService.findSysUserInfoByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
							String ip=null;
							if(result1!=null){
								 ip=(String) result1.get("loginIp");
							}
							
							if(ip==null){
								ip="";
							}
							String[] ips=ip.split(";");
							boolean flags=false;
							for(int i=0;i<ips.length;i++){
								if(remaddr.equals(ips[i])){
									flags=true;
									break;
								}
							}
							boolean isEnglish = Boolean.valueOf(Config.message.get("isEnglish").toString());
							if(!isEnglish){
								if(!StringUtils.isNotBlank(msgcode)){
									if(!flags &&"19".equals(provincialId)&&logoTemp==1){
										this.getModel().setCode(ResultCodeConstants.SINGLE_LOGIN);
										if(result1!=null){
											this.getModel().setResult(result1.get("userPhone"));
										}
										return this.getModel();
									}
								}
							}
							this.getCondition().put("password", MD5.Md5(pwd));
							String serviceIp= Config.message.get("serviceIp")==null?"":Config.message.get("serviceIp").toString();
							this.getSession().setAttribute("serviceIp", serviceIp);
							Map<String, Object> result = sysUserInfoService.findSysUserInfoByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
								if(result != null && result.size()>0){
									count=0;
									this.getSession().setAttribute("userId",result.get("userId"));
									this.getSession().setAttribute("userName",result.get("userName"));
									this.getSession().setAttribute("roleType",result.get("roleType"));
									this.getSession().setAttribute("username", result);
									this.getSession().setAttribute("roleId", result.get("roleId"));
									this.getSession().setAttribute("provinceId",result.get("provinceId"));
									this.getSession().setAttribute("cityId", result.get("cityId"));
									this.getSession().setAttribute("showStatus", result.get("showStatus"));
									if(!isEnglish){
										if(StringUtils.isNotBlank(msgcode)&&"19".equals(provincialId)&&logoTemp==1){
											if(StringUtils.isNotBlank(msgcode) &&msgcode.equalsIgnoreCase(dynMsg)){ //删除失败，请先删除此账户创建的角色或账户
											}else{
												this.getModel().setCode(-1);
												map.put("errorMsg", "message code error");
												return this.getModel();
											}
										}
									}
									this.getModel().setCode(ResultCodeConstants.SUCCESS);
									this.getModel().setDesc("succeed");
									if("true".equalsIgnoreCase(remerberPwd)){
										CookiesUtil.setSingleCookie(this.getRequest(), this.getReponse(), "WIN_LOGIN_NAME", username);
										CookiesUtil.setSingleCookie(this.getRequest(), this.getReponse(), "WIN_LOGIN_PASS", MD5.Md5(pwd));
									}
									this.getCondition().put("loginTime", new Date());
									sysUserInfoService.updateInfo(this.getVisitId(), this.getCondition(), this.getVisitId());
									return this.getModel();
								}
								else{
									this.getModel().setDesc("failed");
									map.put("errorMsg", "The user name or password error");
									count++;
									if(count>=4){
										this.getModel().setCode(ResultCodeConstants.REQUEST_PARAM_ERROR);								}
									else{
										this.getModel().setCode(ResultCodeConstants.ERROR);
									}
									return this.getModel();
								}

						} catch (ServiceException e) {
							e.printStackTrace();
						}
					
				}
			}
			else{
				map.put("errorMsg", "The user name error");
			}
			logger.info("SysUserInfoController login method end");
			return this.getModel();
		}
		
	
		@ResponseBody
		@RequestMapping("logout")
		public ResultModel logout(){
			logger.info("com.hotdata.dsp.controller.SysUserInfoController logout method start");
			try {
				this.getSession().invalidate();
				CookiesUtil.removeCookice(this.getRequest(), this.getReponse(), "WIN_LOGIN_NAME");
				CookiesUtil.removeCookice(this.getRequest(), this.getReponse(), "WIN_LOGIN_PASS");
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
				 this.getModel().setDesc("succeed");
			} catch (Exception e) {
				this.getModel().setCode(ResultCodeConstants.ERROR);
				 this.getModel().setDesc("failed");
			}
			logger.info("com.hotdata.dsp.controller.SysUserInfoController logout method end");
			return this.getModel();
		}
	
	@ResponseBody
	@RequestMapping(value="list")//实际访问/http://localhost:8080/DSP_V1.0/test/list
	public ResultModel list() {
		logger.info("TestController list method end");
		this.getCondition().put("roleType", 1);
		this.getCondition().put("parentId", 0);
		
		this.getModel().setCode(ResultCodeConstants.SUCCESS);
		this.getModel().setDesc("succeed");
		
		return this.getModel();
	}

	@ResponseBody
	@RequestMapping(value="addCityId")
	public ResultModel addCityIdToSession() {
		logger.info("addCityIdToSession method start");
		String cityId = this.getRequest().getParameter("cityId");
		this.getSession().setAttribute("cityId",cityId);
		logger.info("addCityIdToSession method end");
		return this.getModel();
	}

	@ResponseBody
	@RequestMapping(value="clearCityId")
	public ResultModel clearCityIdFromSession() {
		logger.info("clearCityIdFromSession method start");
		this.getSession().removeAttribute("cityId");
		logger.info("clearCityIdFromSession method end");
		return this.getModel();
	}
	
	/*//短信下发
	@RequestMapping(value="genDynMsg")
	@ResponseBody
	public ResultModel genDynMsg(HttpServletRequest request){
		logger.info("SysUserInfoController genDynMsg method start");
		try {
			String phone = this.getRequest().getParameter("phone");
			//String phone="18610625336";
			Random random = new Random();
			String content="";
			for(int i=0;i<6;i++){
				content+=random.nextInt(10);
			}
			String str="[WINS]短信验证码："+content+",5分钟内有效。如非本人操作请忽略本条短信。";
			String mt = client.mt(phone, str, "", "", "");
			
			//发送失败
			if(mt.substring(0,1).equals("-")){
				this.getCondition().put("smsPhone", phone);
				this.getCondition().put("smsMessage", str);
				this.getCondition().put("smsStatus", 0);
				this.getCondition().put("smsSeq", mt);
				this.getCondition().put("validCode", content);
				this.getCondition().put("createTime", new Date());
				Calendar nowTime = Calendar.getInstance();
				nowTime.add(Calendar.MINUTE, 5);
				Date afterDate = nowTime.getTime();
				this.getCondition().put("disableTime", afterDate);
				sysSmsService.insertSms(this.getCondition());
			}
			else{
				this.getCondition().put("smsPhone", phone);
				this.getCondition().put("smsMessage", str);
				this.getCondition().put("smsStatus", 1);
				this.getCondition().put("smsSeq", mt);
				this.getCondition().put("validCode", content);
				this.getCondition().put("createTime", new Date());
				Calendar nowTime = Calendar.getInstance();
				nowTime.add(Calendar.MINUTE, 5);
				Date afterDate = nowTime.getTime();
				this.getCondition().put("disableTime", afterDate);
				sysSmsService.insertSms(this.getCondition());
			}
			request.getSession().setAttribute("dynMsg", content);
			System.out.println(content+"##############################");
			request.getSession().setMaxInactiveInterval(300);
			 this.getModel().setCode(ResultCodeConstants.SUCCESS);
			 this.getModel().setDesc("方法调用成功");
		} catch (Exception e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
			e.printStackTrace();
		}
		logger.info("SysUserInfoController genDynMsg method end");
		return this.getModel();
	}*/
	//短信下发
		@RequestMapping(value="genDynMsg")
		@ResponseBody
		public ResultModel genDynMsg(HttpServletRequest request){
			logger.info("SysUserInfoController genDynMsg method start");
			String content="";
			String phone ="";
			String str="";
			try {
				phone = this.getRequest().getParameter("phone");
				//String phone="18610625336";
				Random random = new Random();
				for(int i=0;i<6;i++){
					content+=random.nextInt(10);
				}
				str="[WINS]SMS verification code："+content+",Valid for 5 minutes.If other operations, please ignore this message.";
				Utils u=new Utils();
				
				
				//sendmobile=18122043610&spnumber=1065946812&token=dffd0559beb61836726781965dbf14cf&smscontent=测试你好&sendDtl=%5BWINS%5D%E7%9F%AD%E4%BF%A1%E9%AA%8C%E8%AF%81%E7%A0%81%EF%BC%9A926089%2C5%E5%88%86%E9%92%9F%E5%86%85%E6%9C%89%E6%95%88%E3%80%82%E5%A6%82%E9%9D%9E%E6%9C%AC%E4%BA%BA%E6%93%8D%E4%BD%9C%E8%AF%B7%E5%BF%BD%E7%95%A5%E6%9C%AC%E6%9D%A1%E7%9F%AD%E4%BF%A1%E3%80%82&tokenkey=tyujhgyjjh21&templetId=48
				String serviceIp= Config.message.get("serviceIp").toString();
				u.sendEms(phone, content,serviceIp);
			//	String mt = client.mt(phone, str, "", "", "");
				this.getCondition().put("smsPhone", phone);
				this.getCondition().put("smsMessage", str);
				this.getCondition().put("smsStatus", 1);
				this.getCondition().put("smsSeq", 1);
				this.getCondition().put("validCode", content);
				
				this.getCondition().put("createTime", new Date());
				Calendar nowTime = Calendar.getInstance();
				nowTime.add(Calendar.MINUTE, 5);
				Date afterDate = nowTime.getTime();
				this.getCondition().put("disableTime", afterDate);
				sysSmsService.insertSms(this.getCondition());
				request.getSession().setAttribute("dynMsg", content);
				System.out.println("***************"+serviceIp);
				System.out.println(content+"###############################");
				request.getSession().setMaxInactiveInterval(300);
				 this.getModel().setCode(ResultCodeConstants.SUCCESS);
				 this.getModel().setDesc("方法调用成功");
			} catch (Exception e) {
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("The user name or password error");
				this.getCondition().put("smsPhone", phone);
				this.getCondition().put("smsMessage", str);
				this.getCondition().put("smsStatus", 0);
				this.getCondition().put("smsSeq", 0);
				this.getCondition().put("validCode", content);
				this.getCondition().put("createTime", new Date());
				Calendar nowTime = Calendar.getInstance();
				nowTime.add(Calendar.MINUTE, 5);
				Date afterDate = nowTime.getTime();
				this.getCondition().put("disableTime", afterDate);
				try {
					sysSmsService.insertSms(this.getCondition());
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			logger.info("SysUserInfoController genDynMsg method end");
			return this.getModel();
		}
}
