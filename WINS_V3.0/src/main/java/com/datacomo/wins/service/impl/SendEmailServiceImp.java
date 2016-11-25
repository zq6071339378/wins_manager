package com.datacomo.wins.service.impl;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.business.AccountManageBusiness;
import com.datacomo.wins.business.SysLogRecordBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.service.SendEmailService;
import com.datacomo.wins.util.DateUtil;
import com.datacomo.wins.util.EmailUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/26.
 */
@Service("sendEmailService")
public class SendEmailServiceImp implements SendEmailService {
    private static Logger logger = Logger.getLogger(SendEmailServiceImp.class.getName());
    @Autowired
    private AccountManageBusiness accountManageBusiness;
    @Autowired
    private SysLogRecordBusiness sysLogRecordBusiness;

    @Override
    public boolean sendPolicyAuditeSuccessEmail(int userId,String Ip,String policyName) {
        logger.info("sendPolicyOperateEmail method start");
        if (logger.isDebugEnabled()){
            logger.debug("userId: "+userId);
            logger.debug("Ip: "+Ip);
            logger.debug("policyName: "+policyName);
        }
        try {
            Map<String,Object> cond = new HashMap<>();
            cond.put("userId",userId);
            Map<String,Object> user = accountManageBusiness.getInfoByCondition(userId,cond);
            String userEmail = String.valueOf(user.get("userEmail"));
            if(userEmail!="" && userEmail!=null){
                String emailSubject = "Receive a message from datacomo";
                String emailConcent = "<div><span style=\"font: '微软雅黑'; color: #555555; font-size: 30px;\">Policy to audit successed,policy name is :</span><span style=\"color: #4CAE4C; font-size: 20px;\">"+policyName+"</span></div>";
                String attachmentName = ""; //没有附件为空
                boolean flag = EmailUtil.sendHtmlEmail(userEmail,emailSubject,emailConcent,attachmentName);
                if(flag){ //邮件发送成功
                    sysLogRecordBusiness.insertLogInfo(userId,"Mail notification",1,"Email sent successfully for message \"Audit policy success\"",Ip, DateUtil.getDateString());
                }else{ //邮件发送失败
                    sysLogRecordBusiness.insertLogInfo(userId,"Mail notification",2,"Email sent failed for message \"Audit policy success\"",Ip, DateUtil.getDateString());
                }
            }else{
                logger.debug("Email address is empty");
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            try {
                sysLogRecordBusiness.insertLogInfo(userId,"Mail notification",2,"Email sent failed for message \"Audit policy success\"",Ip, DateUtil.getDateString());
            } catch (BusinessException e1) {
                e1.printStackTrace();
            }
            return false;
        }
        logger.info("sendPolicyOperateEmail method end");
        return true;
    }

    @Override
    public boolean sendPolicyWaitToAuditeEmail(int userId, String Ip, String policyName) {
        logger.info("sendPolicyWaitToAuditeEmail method start");
        if (logger.isDebugEnabled()){
            logger.debug("userId: "+userId);
            logger.debug("Ip: "+Ip);
            logger.debug("policyName: "+policyName);
        }
        try {
            Map<String,Object> cond = new HashMap<>();
            cond.put("userId",userId);
            Map<String,Object> user = accountManageBusiness.getInfoByCondition(userId,cond);
            String userEmail = String.valueOf(user.get("userEmail"));
            if(userEmail!="" && userEmail!=null){
                String emailSubject = "Receive a message from datacomo";
                String emailConcent = "<div><span style=\"font: '微软雅黑'; color: #555555; font-size: 30px;\">You have a policy need to audit,policy name is：</span><span style=\"color: #4CAE4C; font-size: 30px;\">"+policyName+"</span><span style=\"font: '微软雅黑'; color: #555555; font-size: 30px;\">。</span></div>";
                String attachmentName = ""; //没有附件为空
                boolean flag = false;
                flag = EmailUtil.sendHtmlEmail(userEmail,emailSubject,emailConcent,attachmentName);
                if(flag){ //邮件发送成功
                    sysLogRecordBusiness.insertLogInfo(userId,"Mail notification",1,"Email sent successfully for message \"You have a policy need to audit \"",Ip, DateUtil.getDateString());
                }else{ //邮件发送失败
                    sysLogRecordBusiness.insertLogInfo(userId,"Mail notification",2,"Email sent failed for message \"You have a policy need to audit \" ",Ip, DateUtil.getDateString());
                }
            }else{
                logger.debug("Email address is empty");
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            try {
                sysLogRecordBusiness.insertLogInfo(userId,"Mail notification",2,"Email sent failed for message \"You have a policy need to audit \" ",Ip, DateUtil.getDateString());
            } catch (BusinessException e1) {
                e1.printStackTrace();
            }
            return false;
        }
        logger.info("sendPolicyWaitToAuditeEmail method end");
        return true;
    }

    @Override
    public boolean sendPolicyStartOrStopEmail(int userId, String Ip, String policyName, boolean bool) {
        logger.info("sendPolicyStartOrStopEmail method start");
        if (logger.isDebugEnabled()){
            logger.debug("userId: "+userId);
            logger.debug("Ip: "+Ip);
            logger.debug("policyName: "+policyName);
        }
        try {
            Map<String,Object> cond = new HashMap<>();
            cond.put("userId",userId);
            Map<String,Object> user = accountManageBusiness.getInfoByCondition(userId,cond);
            String userEmail = String.valueOf(user.get("userEmail"));
            if(userEmail!="" && userEmail!=null){
                String emailSubject = "Receive a message from datacomo";
                String emailConcent="";
                if(bool){
                    emailConcent = "<div><span style=\"font: '微软雅黑'; color: #555555; font-size: 30px;\">Your policy has already started to run,the policy name is：</span><span style=\"color: #4CAE4C; font-size: 30px;\">"+policyName+"</span><span style=\"font: '微软雅黑'; color: #555555; font-size: 30px;\">.</span></div>";
                }else{
                    emailConcent = "<div><span style=\"font: '微软雅黑'; color: #555555; font-size: 30px;\">Your policy has stopped,the policy name is：</span><span style=\"color: #4CAE4C; font-size: 30px;\">"+policyName+"</span><span style=\"font: '微软雅黑'; color: #555555; font-size: 30px;\">.</span></div>";
                }
                String attachmentName = ""; //没有附件为空
                boolean flag = false;
                flag = EmailUtil.sendHtmlEmail(userEmail,emailSubject,emailConcent,attachmentName);
                if(flag){ //邮件发送成功
                    sysLogRecordBusiness.insertLogInfo(userId,"Mail notification",1,"Email sent successfully for message \"Policy run or stop\"",Ip, DateUtil.getDateString());
                }else{ //邮件发送失败
                    sysLogRecordBusiness.insertLogInfo(userId,"Mail notification",2,"Email sent failed for message \"Policy run or stop\" ",Ip, DateUtil.getDateString());
                }
            }else{
                logger.debug("Email address is empty");
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            try {
                sysLogRecordBusiness.insertLogInfo(userId,"Mail notification",2,"Email sent failed for message \"Policy run or stop\" ",Ip, DateUtil.getDateString());
            } catch (BusinessException e1) {
                e1.printStackTrace();
            }
            return false;
        }
        logger.info("sendPolicyStartOrStopEmail method end");
        return true;
    }

}
