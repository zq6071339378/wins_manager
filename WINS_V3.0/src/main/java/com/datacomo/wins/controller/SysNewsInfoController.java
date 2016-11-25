package com.datacomo.wins.controller;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.AccountManageService;
import com.datacomo.wins.service.SysNewsInfoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by duanlinzhuo on 2016/3/16.
 */
@Controller
@RequestMapping(value = "/sysnews")
public class SysNewsInfoController extends BaseController {
    private static Logger logger=Logger.getLogger(SysNewsInfoController.class.getName());
    @Autowired
    private SysNewsInfoService sysNewsInfoService;
    @Autowired
    private AccountManageService accountManageService;
    /**
     * 根据id删除系统消息
     * @param news_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete")
    public ResultModel deleteSysNews(int news_id){
        logger.info("deleteSysNewsManager method start");
        try {
            sysNewsInfoService.deleteById(this.getVisitId(),news_id);
            this.getModel().setCode(ResultCodeConstants.SUCCESS);
            this.getModel().setDesc("delete！");
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation！");
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteSysNewsManager method end");
        return this.getModel();
    }

    /**
     * 发送消息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "broadcast")
    public ResultModel SendMassages(){
        logger.info("SendMassages method start");
        Map<String, Object> userCondition = new HashMap<String, Object>();
		userCondition.put("userId", this.getVisitId());
		Map<String, Object> userInfo = new HashMap<String, Object>();
		try {
			userInfo = accountManageService.getInfoByCondition(this.getVisitId(), userCondition);
		} catch (ServiceException e1) {
			this.getRequest().setAttribute("result",null);
			e1.printStackTrace();
			logger.error(e1);
		}
		int Show_Status = (int) userInfo.get("showStatus");
        String create_name = this.getSession().getAttribute("userName").toString();
        String  role_type = this.getRequest().getParameter("role_type");
        String news_type = this.getRequest().getParameter("news_type");
        String news_content = this.getRequest().getParameter("news_content");
        boolean result = false;
        try {
            result = sysNewsInfoService.sendMassages(this.getVisitId(),role_type,news_type,news_content,create_name,Show_Status);
            if(result == true){
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("succeed！");
            }else{
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("failed！");
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("failed！");
            e.printStackTrace();
        }
        logger.info("SendMassages method end");
        return this.getModel();
    }
    /**
     * 发送消息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sendMessageTo")
    public ResultModel sendMessageTo(){
        logger.info("sendMessageToId method start");
        String create_name = this.getSession().getAttribute("userName").toString();
        String userName = this.getRequest().getParameter("userName").toString();
        String  user_id = this.getRequest().getParameter("userId");
        String news_type = this.getRequest().getParameter("newsType");
        String activityName = this.getRequest().getParameter("activityName");
        String news_content = "["+activityName+"]"+this.getRequest().getParameter("newsContent");
        
        boolean result = false;
        try {
        	Map<String,Object> con =new HashMap<String,Object>();
        	con.put("create_name", create_name);
        	con.put("news_type", news_type);
        	con.put("news_content", news_content);
        	con.put("user_id", user_id);
        	con.put("receive_name", userName);
            result = sysNewsInfoService.sendMassages(this.getVisitId(),con);
            if(result == true){
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("succeed！");
            }else{
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("failed！");
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("failed！");
            e.printStackTrace();
        }
        logger.info("sendMessageToId method end");
        return this.getModel();
    }
    
    @ResponseBody
    @RequestMapping(value = "refNewsSts")
    public ResultModel refNewsSts(){
        logger.info("refNewsSts method start");
        try {
        	Map<String,Object> notReadNews = sysNewsInfoService.getNotReadInfos(this.getVisitId(),this.getCondition());
        	this.getModel().setResult(notReadNews);
            this.getModel().setCode(ResultCodeConstants.SUCCESS);
            this.getModel().setDesc("succeed！");
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("failed！");
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("refNewsSts method end");
        return this.getModel();
    }
}
