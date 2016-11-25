package com.datacomo.wins.controller;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.AccountManageService;
import com.datacomo.wins.service.SysLogRecordService;
import com.datacomo.wins.util.DateUtil;
import com.datacomo.wins.util.EmailUtil;
import com.datacomo.wins.util.MD5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
@Controller
@RequestMapping(value="/account")//url映射方法前缀
public class AccountManageController extends BaseController {
    private static Logger logger = Logger.getLogger(AccountManageController.class.getName());

    @Autowired
    private AccountManageService accountManageService;
    @Autowired
    private SysLogRecordService sysLogRecordService;

    @RequestMapping("/createAccount.html")
    public String toCreateAccountView(){
        logger.info("toCreateAccountView method start");
        int userId = this.getVisitId();
        this.getCondition().put("userId",userId);
        Map<String,Object> result = null;
        try {
            int showStatus = Integer.parseInt(String.valueOf(this.getSession().getAttribute("showStatus")));
            this.getCondition().put("showStatus",showStatus);
            result = accountManageService.getAccountInfo(userId,this.getCondition());
            this.getRequest().setAttribute("result",result);
        } catch (ServiceException e) {
            e.printStackTrace();
            logger.error(e);
            this.getRequest().setAttribute("result",null);
        }
        logger.info("toCreateAccountView method end");
        return "accountmanage/createAccount";
    }

    @RequestMapping("/checkAccount.html")
    public String toCheckAccountView(int userId){
        logger.info("toCheckAccountView method start");
        this.getCondition().put("userId",userId);
        Map<String,Object> result = null;
        try {
            result = accountManageService.getInfoByCondition(this.getVisitId(),this.getCondition());
            this.getRequest().setAttribute("result",result);
        } catch (ServiceException e) {
            e.printStackTrace();
            logger.error(e);
            this.getRequest().setAttribute("result",null);
        }
        logger.info("toCheckAccountView method end");
        return "accountmanage/checkAccount";
    }

    @RequestMapping("/editAccount.html")
    public String toEditAccountView(int userId){
        logger.info("toEditAccountView method start");
        this.getCondition().put("userId",userId);
        Map<String,Object> result = null;
        try {
            int showStatus = Integer.parseInt(String.valueOf(this.getSession().getAttribute("showStatus")));
            this.getCondition().put("showStatus",showStatus);
            result = accountManageService.getAccountInfo(this.getVisitId(),this.getCondition());
            this.getRequest().setAttribute("result",result);
        } catch (ServiceException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("toEditAccountView method end");
        return "accountmanage/editAccount";
    }

    @ResponseBody
    @RequestMapping(value = "/checkUserPwd")
    public ResultModel checkUserPwd(String userPwd,String userId){
        logger.info("checkUserPwd method start");
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("userPwd",MD5.Md5(userPwd));
        condition.put("userId",userId);
        int result;
        try {
            result = accountManageService.getCount(condition);
            if(result==0){
                this.getModel().setCode(ResultCodeConstants.SUCCESS); //1表示密码与原密码不同
            }
            if (result > 0){
                this.getModel().setCode(ResultCodeConstants.ERROR);  //-1表示新密码与原密码相同
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("checkUserPwd method end");
        return this.getModel();
    }

    /**
     * 效验账号唯一
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/check")
    public ResultModel validetaAccount(String userName){
        logger.info("validetaAccount method start");
        int showStatus = Integer.parseInt(String.valueOf(this.getSession().getAttribute("showStatus")));
        this.getCondition().put("showStatus",showStatus);
        this.getCondition().put("userName",userName);
        int result;
        try {
            result = accountManageService.getCount(this.getCondition());
            if(result==0){
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
            }else{
                this.getModel().setCode(ResultCodeConstants.ERROR);
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("validetaAccount method end");
        return this.getModel();
    }

    /**
     * 根据用户ID删除用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultModel deleteAccount(){
        logger.info("deleteAccount method start");
        String Ip = this.getRequest().getRemoteAddr();
        int userId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("userId")));
        int createUser =this.getVisitId();
        int code=0;
        try {
            code= accountManageService.deleteAccountById(this.getVisitId(),userId);
            if (code==1){
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("Delete");
                sysLogRecordService.insertLogInfo(createUser,"delete",1,"Delete account successfully",Ip,DateUtil.getDateString());
            }else if(code == -1){
                this.getModel().setCode(ResultCodeConstants.REQUEST_METHOD_ERROR);
                this.getModel().setDesc("Delete failed, please delete the account to create characters or account first！");
                sysLogRecordService.insertLogInfo(createUser,"delete",1,"failed",Ip,DateUtil.getDateString());
            } else{
                this.getModel().setCode(ResultCodeConstants.REQUEST_METHOD_ERROR);
                this.getModel().setDesc("abnormal operation！");
                sysLogRecordService.insertLogInfo(createUser,"delete",2,"Delete account failed",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation！");
            try {
                sysLogRecordService.insertLogInfo(createUser,"delete",2,"Delete account failed",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteAccount method end");
        return this.getModel();
    }

    /**
     * 创建账户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create")
    public ResultModel createAccount(){
        logger.info("createAccount method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser=this.getVisitId();
        int result = 0;
        try {
            String userName = this.getRequest().getParameter("userName");
            String realName = this.getRequest().getParameter("realName");
            String userEmail = this.getRequest().getParameter("userEmail")==null?"":this.getRequest().getParameter("userEmail");
            String userOffice = this.getRequest().getParameter("userOffice")==null?"":this.getRequest().getParameter("userOffice");
            String userAddr = this.getRequest().getParameter("userAddr")==null?"":this.getRequest().getParameter("userAddr");
            String roleId = this.getRequest().getParameter("roleId");
            String cityId = this.getRequest().getParameter("cityId");
            String provinceId = this.getRequest().getParameter("provinceId");
            String userPwd = this.getRequest().getParameter("userPwd");
            String userPhone = this.getRequest().getParameter("userPhone");
            String loginIp = this.getRequest().getParameter("loginIp");
            String userStatus = "1";
            String createTime = DateUtil.getDateString();
            Map<String,Object> condition = new HashMap<String, Object>();
            condition.put("userName",userName);
            condition.put("realName",realName);
            condition.put("userEmail",userEmail);
            condition.put("userOffice",userOffice);
            condition.put("userAddr",userAddr);
            condition.put("roleId",roleId);
            if (cityId!=""){
                condition.put("cityId",cityId);
            }
            condition.put("provinceId",provinceId);
            condition.put("userPwd", MD5.Md5(userPwd));
            condition.put("userPhone",userPhone);
            condition.put("loginIp",loginIp);
            condition.put("userStatus",userStatus);
            condition.put("createUser",createUser);
            condition.put("createTime",createTime);
            int showStatus = Integer.parseInt(String.valueOf(this.getSession().getAttribute("showStatus")));
            condition.put("showStatus",showStatus);
            result = accountManageService.insertInfo(this.getVisitId(),condition);
            if(result==0){
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("abnormal operation！");
                sysLogRecordService.insertLogInfo(createUser,"create",2,"abnormal operation",Ip,DateUtil.getDateString());
            }else{
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("succeed！");
                sysLogRecordService.insertLogInfo(createUser,"create",1,"create account",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation！");
            try {
                sysLogRecordService.insertLogInfo(createUser,"create",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("createAccount method end");
        return this.getModel();
    }

    /**
     * 修改账户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultModel updateAccount(){
        logger.info("updateAccount method start");
        String Ip = this.getRequest().getRemoteAddr();
        int userId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("userId")));
        String userName = this.getRequest().getParameter("userName");
        String realName = this.getRequest().getParameter("realName");
        String userEmail = this.getRequest().getParameter("userEmail")==null?"":this.getRequest().getParameter("userEmail");
        String userOffice = this.getRequest().getParameter("userOffice")==null?"":this.getRequest().getParameter("userOffice");
        String userAddr = this.getRequest().getParameter("userAddr")==null?"":this.getRequest().getParameter("userAddr");
        String roleId = this.getRequest().getParameter("roleId");
        String cityId = this.getRequest().getParameter("cityId");
        if ("".equals(cityId)){
            cityId = null;
        }
        String provinceId = this.getRequest().getParameter("provinceId");
        String newPwd = this.getRequest().getParameter("newPwd");
        String oldPwd = this.getRequest().getParameter("oldPwd");
        String userPhone = this.getRequest().getParameter("userPhone");
        String loginIp = this.getRequest().getParameter("loginIp");
        int updateUser  = this.getVisitId();
        String updateTime = DateUtil.getDateString();
        this.getCondition().put("userId",userId);
        this.getCondition().put("userName",userName);
        this.getCondition().put("userEmail",userEmail);
        this.getCondition().put("userOffice",userOffice);
        this.getCondition().put("userAddr",userAddr);
        this.getCondition().put("realName",realName);
        this.getCondition().put("roleId",roleId);
        this.getCondition().put("cityId",cityId);
        this.getCondition().put("provinceId",provinceId);
        if (!newPwd.equals(oldPwd)){
            this.getCondition().put("userPwd", MD5.Md5(newPwd));
        }
        this.getCondition().put("userPhone",userPhone);
        this.getCondition().put("loginIp",loginIp);
        this.getCondition().put("updateUser",updateUser);
        this.getCondition().put("updateTime",updateTime);
        boolean result = false;
        try {
            result = accountManageService.updateInfo(this.getVisitId(),this.getCondition(),this.getVisitId());
            if(!result){
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("abnormal operation！");
                sysLogRecordService.insertLogInfo(updateUser,"edit",2,"abnormal operation",Ip,DateUtil.getDateString());
            }else{
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("succeed！");
                sysLogRecordService.insertLogInfo(updateUser,"edit",1,"edit the account",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation！");
            try {
                sysLogRecordService.insertLogInfo(updateUser,"edit",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("updateAccount method end");
        return this.getModel();
    }

    /**
     * 修改账户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit")
    public ResultModel editAccount(){
        logger.info("editAccount method start");
        String Ip = this.getRequest().getRemoteAddr();
        int userId = Integer.parseInt(this.getRequest().getParameter("userId"));
        //String userName = this.getRequest().getParameter("userName");
        String realName = this.getRequest().getParameter("realName");
        String userEmail = this.getRequest().getParameter("userEmail")==null?"":this.getRequest().getParameter("userEmail");
        String userOffice = this.getRequest().getParameter("userOffice")==null?"":this.getRequest().getParameter("userOffice");
        String userAddr = this.getRequest().getParameter("userAddr")==null?"":this.getRequest().getParameter("userAddr");
        String userPhone = this.getRequest().getParameter("userPhone");
        String userPwd = this.getRequest().getParameter("userPwd");
        String oldPwd = this.getRequest().getParameter("oldPwd");
        this.getCondition().put("userId",userId);
        //this.getCondition().put("userName",userName);
        this.getCondition().put("realName",realName);
        this.getCondition().put("userEmail",userEmail);
        this.getCondition().put("userOffice",userOffice);
        this.getCondition().put("userAddr",userAddr);
        this.getCondition().put("userPhone",userPhone);
        if(!userPwd.equals(oldPwd)){
            this.getCondition().put("userPwd",MD5.Md5(userPwd));
        }
        this.getCondition().put("updateUser",userId);
        this.getCondition().put("updateTime",DateUtil.getDateString());
        boolean result = false;
        try {
            result = accountManageService.updateInfo(this.getVisitId(),this.getCondition(),this.getVisitId());
            if(!result){
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("abnormal operation！");
                sysLogRecordService.insertLogInfo(userId,"edit",2,"abnormal operation",Ip,DateUtil.getDateString());
            }else{
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("succeed！");
                sysLogRecordService.insertLogInfo(userId,"edit",1,"edit",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation！");
            try {
                sysLogRecordService.insertLogInfo(userId,"edit",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("editAccount method start");
        return this.getModel();
    }

    @RequestMapping("/sendCode")
    public ResultModel sendVerification(){
        logger.info("sendVerification method start");
        boolean result= false;
        String userEmail = this.getRequest().getParameter("userEmail");
        String verifyCode = this.getRequest().getParameter("verifyCode")==null?"":this.getRequest().getParameter("verifyCode");

        String emailSubject = "You receive a verification code from datacomo";
        String emailConcent = "<div><span style=\"font: '微软雅黑'; color: #555555; font-size: 20px;\">Your verification code is </span><span style=\"color: #4CAE4C; font-size: 30px;\">"+verifyCode+"</span><span style=\"font: '微软雅黑'; color: #555555; font-size: 20px;\">，valid for 2 minutes, failure reacquires.</span></div>";
        String attachmentName = ""; //没有附件为空
        result = EmailUtil.sendHtmlEmail(userEmail,emailSubject,emailConcent,attachmentName);
        if(result){
            this.getModel().setCode(ResultCodeConstants.SUCCESS);
        }else{
            this.getModel().setCode(ResultCodeConstants.ERROR);
        }
        logger.info("sendVerification method end");
        return this.getModel();
    }


    /*@ResponseBody
    @RequestMapping("/belongCity")
    public void getBelongCityInfo(){
        logger.info("getBelongCityInfo method start");
        int userId = Integer.parseInt(String.valueOf(this.getSession().getAttribute("userId")));
        this.getCondition().put("userId",userId);
        Map<String,Object> result = null;
        try {
            result = accountManageService.getBelongCityInfo(userId,this.getCondition());
            System.out.println("result-----------------------------------------------------: "+result);
            this.getRequest().setAttribute("result",result);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("getBelongCityInfo method end");
    }*/

}
