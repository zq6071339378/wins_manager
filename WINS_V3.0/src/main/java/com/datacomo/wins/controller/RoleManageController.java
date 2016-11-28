package com.datacomo.wins.controller;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.business.AccountManageBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.AccountManageService;
import com.datacomo.wins.service.SysLogRecordService;
import com.datacomo.wins.service.SysRoleInfoService;
import com.datacomo.wins.service.SysRoleMenuRelationService;
import com.datacomo.wins.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
@Controller
@RequestMapping(value="/role")//url映射方法前缀
public class RoleManageController extends BaseController {
    private static Logger logger = Logger.getLogger(RoleManageController.class.getName());
    @Autowired
    private SysRoleInfoService sysRoleInfoService;
    @Autowired
    private SysRoleMenuRelationService sysRoleMenuRelationService;
    @Autowired
    private AccountManageBusiness accountManageBusiness;
    @Autowired
    private SysLogRecordService sysLogRecordService;

    private static final int PUSH_VIEW_MENU_ID = 1;             //推送概览
    private static final int PUSH_MANAGE_MENU_ID = 2;           //推送管理
    private static final int POLICY_MANAGE_MENU_ID = 3;         //策略管理
    private static final int MATERIAL_MANAGE_MENU_ID = 4;       //素材管理
    private static final int GROUP_MANAGE_MENU_ID = 5;          //群组管理
    private static final int AREA_MANAGE_MENU_ID = 6;           //地域管理
    private static final int TEMPLATE_MANAGE_MENU_ID = 7;       //模板管理
    private static final int PAGE_MANAGE_MENU_ID = 8;           //页面管理
    private static final int OPERATE_MANAGE_MENU_ID = 9;        //运营管理
    private static final int PUSH_REPORT_MANAGE_MENU_ID = 10;   //推送报表管理
    private static final int USER_COMPLAINT_MANAGE_MENU_ID = 11;//用户投诉管理
    private static final int BLACK_MANAGE_MENU_ID = 12;         //黑名单管理
    private static final int USER_BLACK_MANAGE_MENU_ID = 13;    //用户黑名单管理
    private static final int URL_BLACK_MANAGE_MENU_ID = 14;     //网址黑名单管理
    private static final int SYSTEM_MANAGE_MENU_ID = 15;        //系统管理
    private static final int ROLE_MANAGE_MENU_ID = 16;          //角色管理
    private static final int ACCOUNT_MANAGE_MENU_ID = 17;       //账号管理
    private static final int MONITOR_MANAGE_MENU_ID = 18;       //监控管理
    private static final int CUSTOMER_MANAGE_MENU_ID = 19;      //客户管理
    private static final int LOG_MANAGE_MENU_ID = 20;           //日志管理
    private static final int NEWS_MANAGE_MENU_ID = 21;          //消息管理
    private static final int ACCOUNT_SET_MENU_ID = 22;          //账户设置
    private static final int ACCOUNT_INFO_MENU_ID = 23;         //账户信息
    private static final int SYS_NEWS_MENU_ID = 24;             //系统消息
    //private static final int ACCOUNT_SAFE_MENU_ID = 24;         //账户安全
    private static final int OPERATE_RECORD_MENU_ID = 25;       //操作记录

    /**
     * 自定义方法---创建权限
     * @param menuName
     * @param menuId
     */
    private void createMenuRelation (String [] menuName,final int menuId){
        String Ip = this.getRequest().getRemoteAddr();
        int createUser = this.getVisitId();
        int code = 0;
        int sum=0;
        if(menuName!=null){
            for(String p:menuName){
                sum+=Integer.parseInt(p);
            }
            this.getCondition().put("menuId",menuId);
            this.getCondition().put("roleValue",sum);
            try {
                code = sysRoleMenuRelationService.insertInfoByCondition(this.getVisitId(),this.getCondition());
                if(code==0){
                    this.getModel().setCode(ResultCodeConstants.ERROR);
                    this.getModel().setDesc("abnormal operation");
                    sysLogRecordService.insertLogInfo(createUser,"create",2,"abnormal operation",Ip,DateUtil.getDateString());
                }
            } catch (ServiceException e) {
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("abnormal operation");
                try {
                    sysLogRecordService.insertLogInfo(createUser,"create",2,"abnormal operation",Ip,DateUtil.getDateString());
                } catch (ServiceException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    /**
     * 自定义方法---创建父级菜单权限
     * @param value
     * @param menuId
     */
    private void createParentMenuRelation (final int value,final int menuId){
        String Ip = this.getRequest().getRemoteAddr();
        int createUser = this.getVisitId();
        int code = 0;
        this.getCondition().put("menuId",menuId);
        this.getCondition().put("roleValue",value);
        try {
            code = sysRoleMenuRelationService.insertInfoByCondition(this.getVisitId(),this.getCondition());
            if(code==0){
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("abnormal operation");
                sysLogRecordService.insertLogInfo(createUser,"create",2,"abnormal operation",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation");
            try {
                sysLogRecordService.insertLogInfo(createUser,"create",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
    }

    /**
     * 创建角色页面
     * @return
     */
    @RequestMapping("/createRole.html")
    public String toCreateRoleView(){
        logger.info("toCreateRoleView method start");
        logger.info("toCreateRoleView method end");
        return "rolemanage/createRole";
    }

    /**
     * 查看角色页面
     * @return
     */
    @RequestMapping("/checkRole.html")
    public String toCheckRoleView(){
        logger.info("toCheckRoleView method start");
        int roleId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("roleId")));
        this.getCondition().put("roleId",roleId);
        Map<String,Object> result = null;
        try {
            result = sysRoleInfoService.getRoleInfo(this.getVisitId(),this.getCondition());
            this.getRequest().setAttribute("result",result);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        logger.info("toCheckRoleView method end");
        return "rolemanage/checkRole";
    }

    /**
     * 修改角色页面
     * @return
     */
    @RequestMapping("/editRole.html")
    public String toEditRoleView(){
        logger.info("toEditRoleView method start");
        int roleId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("roleId")));
        this.getCondition().put("roleId",roleId);
        Map<String,Object> result = null;
        try {
            result = sysRoleInfoService.getRoleInfo(this.getVisitId(),this.getCondition());
            this.getRequest().setAttribute("result",result);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        logger.info("toEditRoleView method end");
        return "rolemanage/editRole";
    }

    /**
     * 效验角色唯一
     * @param roleName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/check")
    public ResultModel validetaRole(String roleName){
        logger.info("validetaRole method start");
        this.getCondition().put("roleName",roleName);
        int result;
        try {
            int showStatus = Integer.parseInt(String.valueOf(this.getSession().getAttribute("showStatus")));
            this.getCondition().put("showStatus",showStatus);
            result = sysRoleInfoService.getCount(this.getCondition());
            if(result == 0){
                this.getModel().setCode(ResultCodeConstants.SUCCESS);  //1
            }else if(result > 0 ){
                this.getModel().setCode(ResultCodeConstants.ERROR);  //0
            }else{
                this.getModel().setCode(ResultCodeConstants.SINGLE_LOGIN);  //2
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.SINGLE_LOGIN); //2
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("validetaRole method end");
        return this.getModel();
    }

    /**
     * 更改角色的停用和启用
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateRoleStatus")
    public ResultModel updateRoleStatus() {
        logger.info("updateRoleStatus method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser = this.getVisitId();
        String roleId = this.getRequest().getParameter("roleId");
        String roleStatus = this.getRequest().getParameter("roleStatus");

        this.getCondition().put("roleId", roleId);
        this.getCondition().put("roleStatus", roleStatus);
        boolean bool = false;
        try {
            bool = sysRoleInfoService.updateRoleState(this.getVisitId(), this.getCondition(), this.getVisitId());
            if (bool == true) {
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("succeed");
                sysLogRecordService.insertLogInfo(createUser,"modified",1,"stop/start",Ip,DateUtil.getDateString());
            } else {
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("failed");
                sysLogRecordService.insertLogInfo(createUser,"modified",2,"stop/abnormal operation",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation");
            try {
                sysLogRecordService.insertLogInfo(createUser,"modified",2,"stop/abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("updateRoleStatus method end");
        return this.getModel();
    }

    /**
     * 根据角色ID删除用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultModel deleteRole(){
        logger.info("deleteRole method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser = this.getVisitId();
        int roleId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("roleId")));
        int code=0;
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("roleId",roleId);
        List<Map<String,Object>> users = accountManageBusiness.findByCondition(roleId,condition);
        try {
            if(users.isEmpty()){
                code= sysRoleInfoService.deleteRoleById(this.getVisitId(),roleId);
                if (code==1){
                    this.getModel().setCode(ResultCodeConstants.SUCCESS);
                    this.getModel().setDesc("delete！");
                    sysLogRecordService.insertLogInfo(createUser,"delete",1,"delete",Ip,DateUtil.getDateString());
                }else{
                    this.getModel().setCode(ResultCodeConstants.REQUEST_METHOD_ERROR);
                    this.getModel().setDesc("abnormal operation！");
                    sysLogRecordService.insertLogInfo(createUser,"delete",2,"abnormal operation",Ip,DateUtil.getDateString());
                }
            }else{
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("failed");
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation！");
            try {
                sysLogRecordService.insertLogInfo(createUser,"delete",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteRole method end");
        return this.getModel();
    }

    /**
     * 创建角色
     * @return
     */
    @ResponseBody
    @RequestMapping("/create")
    public ResultModel createRole(){
        logger.info("createRole method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser = this.getVisitId();
        //int parentId = Integer.parseInt(this.getRequest().getParameter("parentId"));
        String roleName = this.getRequest().getParameter("roleName");
        String roleDesc = this.getRequest().getParameter("roleDesc");
        this.getCondition().put("roleName",roleName);
        this.getCondition().put("roleDesc",roleDesc);
        this.getCondition().put("createUser",createUser);
        this.getCondition().put("createTime", DateUtil.getDateString());
        this.getCondition().put("roleStatus","2");
        this.getCondition().put("roleType",0);
        // this.getCondition().put("parentId",parentId);
        int roleId = 0;
        //先创建角色基本信息
        try {
            int code = 0;
            code = sysRoleInfoService.insertInfo(this.getVisitId(),this.getCondition());
            if(code!=0){
                long l = (long) this.getCondition().get("role_id");
                roleId = (int) l;
                this.getCondition().put("roleId",roleId);
                this.getCondition().put("roleType",roleId);
                //同步role_type 与 roleId 一致
                sysRoleInfoService.updateInfo(this.getVisitId(),this.getCondition(),this.getVisitId());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation");
            try {
                sysLogRecordService.insertLogInfo(createUser,"create",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        //再创建角色菜单关系
        if(roleId != 0){
            String [] pushView = this.getRequest().getParameterValues("pushView");
            createMenuRelation(pushView,PUSH_VIEW_MENU_ID);

            //String [] pushManage = this.getRequest().getParameterValues("pushManage");
            String [] policyManage = this.getRequest().getParameterValues("policyManage");
            String [] materialManage = this.getRequest().getParameterValues("materialManage");
            String [] groupManage = this.getRequest().getParameterValues("groupManage");
            String [] areaManage = this.getRequest().getParameterValues("areaManage");
            //这两个在素材管理下
            String [] templateManage = this.getRequest().getParameterValues("templateManage");
            String [] pageManage = this.getRequest().getParameterValues("pageManage");
            int pushValue=0;
            if(policyManage!=null || materialManage!=null || groupManage!=null || templateManage!=null || pageManage!=null){
                pushValue=1;
                createParentMenuRelation(pushValue,PUSH_MANAGE_MENU_ID);
            }
            createMenuRelation(policyManage,POLICY_MANAGE_MENU_ID);
            int materialValue=0;
            if(templateManage!=null || pageManage!=null){
                materialValue = 1;
                createParentMenuRelation(materialValue,MATERIAL_MANAGE_MENU_ID);
            }
            createMenuRelation(groupManage,GROUP_MANAGE_MENU_ID);
            createMenuRelation(areaManage,AREA_MANAGE_MENU_ID);
            createMenuRelation(templateManage,TEMPLATE_MANAGE_MENU_ID);
            createMenuRelation(pageManage,PAGE_MANAGE_MENU_ID);
            //String [] operateManage = this.getRequest().getParameterValues("operateManage");
            String [] pushReportManage = this.getRequest().getParameterValues("pushReportManage");
            String [] userComplaintManage = this.getRequest().getParameterValues("userComplaintManage");
            //String [] blackManage = this.getRequest().getParameterValues("blackManage");
            //这两个在黑名单管理下
            String [] userBlackManage = this.getRequest().getParameterValues("userBlackManage");
            String [] urlBlackManage = this.getRequest().getParameterValues("urlBlackManage");
            int operateValue = 0;
            if (pushReportManage!=null || userComplaintManage!=null || userBlackManage!=null || urlBlackManage!=null){
                operateValue =1;
                createParentMenuRelation(operateValue,OPERATE_MANAGE_MENU_ID);
            }
            createMenuRelation(pushReportManage,PUSH_REPORT_MANAGE_MENU_ID);
            createMenuRelation(userComplaintManage,USER_COMPLAINT_MANAGE_MENU_ID);
            int blackValue=0;
            if(userBlackManage!=null || urlBlackManage!=null){
                blackValue = 1;
                createParentMenuRelation(blackValue,BLACK_MANAGE_MENU_ID);
            }
            createMenuRelation(userBlackManage,USER_BLACK_MANAGE_MENU_ID);
            createMenuRelation(urlBlackManage,URL_BLACK_MANAGE_MENU_ID);

            //String [] sysManage = this.getRequest().getParameterValues("sysManage");
            String [] roleManage = this.getRequest().getParameterValues("roleManage");
            String [] accountManage = this.getRequest().getParameterValues("accountManage");
            String [] monitorManage = this.getRequest().getParameterValues("monitorManage");
            String [] customerManage = this.getRequest().getParameterValues("customerManage");
            String [] logManage = this.getRequest().getParameterValues("logManage");
            String [] newsManage = this.getRequest().getParameterValues("newsManage");
            int sysValue = 0;
            if (roleManage!=null || accountManage!=null || monitorManage!=null || logManage!=null || newsManage!=null || customerManage!=null){
                sysValue = 1;
                createParentMenuRelation(sysValue,SYSTEM_MANAGE_MENU_ID);
            }
            createMenuRelation(roleManage,ROLE_MANAGE_MENU_ID);
            createMenuRelation(accountManage,ACCOUNT_MANAGE_MENU_ID);
            createMenuRelation(monitorManage,MONITOR_MANAGE_MENU_ID);
            //createMenuRelation(customerManage,CUSTOMER_MANAGE_MENU_ID);
            createMenuRelation(logManage,LOG_MANAGE_MENU_ID);
            createMenuRelation(newsManage,NEWS_MANAGE_MENU_ID);
            //String [] accountSet = this.getRequest().getParameterValues("accountSet");
            String [] accountInfo = this.getRequest().getParameterValues("accountInfo");
            String [] sysNews = this.getRequest().getParameterValues("sysNews");
            //String [] accountSafe = this.getRequest().getParameterValues("accountSafe");
            String [] operateRecord = this.getRequest().getParameterValues("operateRecord");
            int accountSetValue = 0;
            if (accountInfo!=null || sysNews!=null || operateRecord!=null){
                accountSetValue = 1;
                createParentMenuRelation(accountSetValue,ACCOUNT_SET_MENU_ID);
            }
            createMenuRelation(accountInfo,ACCOUNT_INFO_MENU_ID);
            createMenuRelation(sysNews,SYS_NEWS_MENU_ID);
            //createMenuRelation(accountSafe,ACCOUNT_SAFE_MENU_ID);
            createMenuRelation(operateRecord,OPERATE_RECORD_MENU_ID);
            try {
                sysLogRecordService.insertLogInfo(createUser,"create",1,"create account",Ip,DateUtil.getDateString());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }else{
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation");
            try {
                sysLogRecordService.insertLogInfo(createUser,"create",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        logger.info("createRole method end");
        return this.getModel();
    }

    /**
     * 修改角色
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultModel updateRole(){
        logger.info("updateRole method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createId = this.getVisitId();
        int oldRoleId = Integer.parseInt(this.getRequest().getParameter("roleId"));
        Map<String,Object> condition = new HashMap<String, Object>();
        condition.put("roleId",oldRoleId);
        List<Map<String,Object>> users = accountManageBusiness.findByCondition(oldRoleId,condition);
        Map<String,Object> oldRole = null;
        try {
            oldRole = sysRoleInfoService.getRoleInfo(oldRoleId,condition);
        } catch (ServiceException e) {
            try {
                sysLogRecordService.insertLogInfo(createId,"modify",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        int createUser = Integer.parseInt(String.valueOf(oldRole.get("createUser")));
        String createTime = String.valueOf(oldRole.get("createTime"));
        //int parentId = Integer.parseInt(this.getRequest().getParameter("parentId"));
        String roleName = this.getRequest().getParameter("roleName");
        String roleDesc = this.getRequest().getParameter("roleDesc");
        this.getCondition().put("roleName",roleName);
        this.getCondition().put("roleDesc",roleDesc);
        this.getCondition().put("createUser",createUser);
        this.getCondition().put("createTime", createTime);
        this.getCondition().put("roleStatus","2");
        this.getCondition().put("roleType",0);
        int roleId = 0;
        //先创建角色基本信息
        try {
            int code = 0;
            code = sysRoleInfoService.insertInfo(this.getVisitId(),this.getCondition());
            if(code!=0){
                long l = (long) this.getCondition().get("role_id");
                roleId = (int) l;
                this.getCondition().put("roleId",roleId);
                this.getCondition().put("roleType",roleId);
                //同步role_type 与 roleId 一致
                sysRoleInfoService.updateInfo(this.getVisitId(),this.getCondition(),this.getVisitId());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation");
            e.printStackTrace();
            logger.error(e);
            try {
                sysLogRecordService.insertLogInfo(createId,"modify",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
        }
        //再创建角色菜单关系
        if(roleId != 0){
            String [] pushView = this.getRequest().getParameterValues("pushView");
            createMenuRelation(pushView,PUSH_VIEW_MENU_ID);

            //String [] pushManage = this.getRequest().getParameterValues("pushManage");
            String [] policyManage = this.getRequest().getParameterValues("policyManage");
            String [] materialManage = this.getRequest().getParameterValues("materialManage");
            String [] groupManage = this.getRequest().getParameterValues("groupManage");
            String [] areaManage = this.getRequest().getParameterValues("areaManage");
            //这两个在素材管理下
            String [] templateManage = this.getRequest().getParameterValues("templateManage");
            String [] pageManage = this.getRequest().getParameterValues("pageManage");
            int pushValue=0;
            if(policyManage!=null || materialManage!=null || groupManage!=null || templateManage!=null || pageManage!=null){
                pushValue=1;
                createParentMenuRelation(pushValue,PUSH_MANAGE_MENU_ID);
            }
            createMenuRelation(policyManage,POLICY_MANAGE_MENU_ID);
            int materialValue=0;
            if(templateManage!=null || pageManage!=null){
                materialValue = 1;
                createParentMenuRelation(materialValue,MATERIAL_MANAGE_MENU_ID);
            }
            createMenuRelation(groupManage,GROUP_MANAGE_MENU_ID);
            createMenuRelation(areaManage,AREA_MANAGE_MENU_ID);
            createMenuRelation(templateManage,TEMPLATE_MANAGE_MENU_ID);
            createMenuRelation(pageManage,PAGE_MANAGE_MENU_ID);
            //String [] operateManage = this.getRequest().getParameterValues("operateManage");
            String [] pushReportManage = this.getRequest().getParameterValues("pushReportManage");
            String [] userComplaintManage = this.getRequest().getParameterValues("userComplaintManage");
            //String [] blackManage = this.getRequest().getParameterValues("blackManage");
            //这两个在黑名单管理下
            String [] userBlackManage = this.getRequest().getParameterValues("userBlackManage");
            String [] urlBlackManage = this.getRequest().getParameterValues("urlBlackManage");
            int operateValue = 0;
            if (pushReportManage!=null || userComplaintManage!=null || userBlackManage!=null || urlBlackManage!=null){
                operateValue =1;
                createParentMenuRelation(operateValue,OPERATE_MANAGE_MENU_ID);
            }
            createMenuRelation(pushReportManage,PUSH_REPORT_MANAGE_MENU_ID);
            createMenuRelation(userComplaintManage,USER_COMPLAINT_MANAGE_MENU_ID);
            int blackValue=0;
            if(userBlackManage!=null || urlBlackManage!=null){
                blackValue = 1;
                createParentMenuRelation(blackValue,BLACK_MANAGE_MENU_ID);
            }
            createMenuRelation(userBlackManage,USER_BLACK_MANAGE_MENU_ID);
            createMenuRelation(urlBlackManage,URL_BLACK_MANAGE_MENU_ID);

            //String [] sysManage = this.getRequest().getParameterValues("sysManage");
            String [] roleManage = this.getRequest().getParameterValues("roleManage");
            String [] accountManage = this.getRequest().getParameterValues("accountManage");
            String [] monitorManage = this.getRequest().getParameterValues("monitorManage");
            String [] customerManage = this.getRequest().getParameterValues("customerManage");
            String [] logManage = this.getRequest().getParameterValues("logManage");
            String [] newsManage = this.getRequest().getParameterValues("newsManage");
            int sysValue = 0;
            if (roleManage!=null || accountManage!=null || monitorManage!=null || logManage!=null || newsManage!=null){
                sysValue = 1;
                createParentMenuRelation(sysValue,SYSTEM_MANAGE_MENU_ID);
            }
            createMenuRelation(roleManage,ROLE_MANAGE_MENU_ID);
            createMenuRelation(accountManage,ACCOUNT_MANAGE_MENU_ID);
            createMenuRelation(monitorManage,MONITOR_MANAGE_MENU_ID);
            //createMenuRelation(customerManage,CUSTOMER_MANAGE_MENU_ID);
            createMenuRelation(logManage,LOG_MANAGE_MENU_ID);
            createMenuRelation(newsManage,NEWS_MANAGE_MENU_ID);
            //String [] accountSet = this.getRequest().getParameterValues("accountSet");
            String [] accountInfo = this.getRequest().getParameterValues("accountInfo");
            String [] sysNews = this.getRequest().getParameterValues("sysNews");
            //String [] accountSafe = this.getRequest().getParameterValues("accountSafe");
            String [] operateRecord = this.getRequest().getParameterValues("operateRecord");
            int accountSetValue = 0;
            if (accountInfo!=null || sysNews!=null || operateRecord!=null){
                accountSetValue = 1;
                createParentMenuRelation(accountSetValue,ACCOUNT_SET_MENU_ID);
            }
            createMenuRelation(accountInfo,ACCOUNT_INFO_MENU_ID);
            createMenuRelation(sysNews,SYS_NEWS_MENU_ID);
            //createMenuRelation(accountSafe,ACCOUNT_SAFE_MENU_ID);
            createMenuRelation(operateRecord,OPERATE_RECORD_MENU_ID);
            try {
                if(!users.isEmpty()){
                    for(Map map : users){
                        condition.clear();
                        String userId = String.valueOf(map.get("userId"));
                        condition.put("userId",userId);
                        condition.put("roleId",roleId);
                        accountManageBusiness.updateInfo(roleId,condition,roleId);
                    }
                }
                //删除之前被修改的那个角色
                sysRoleInfoService.deleteRoleById(oldRoleId,oldRoleId);
                sysLogRecordService.insertLogInfo(createUser,"modify",1,"modify",Ip,DateUtil.getDateString());
            } catch (Exception e) {
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("abnormal operation");
                e.printStackTrace();
                logger.error(e);
                try {
                    sysLogRecordService.insertLogInfo(createId,"modify",2,"abnormal operation",Ip,DateUtil.getDateString());
                } catch (ServiceException e1) {
                    e1.printStackTrace();
                }
            }
        }else{
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation");
            try {
                sysLogRecordService.insertLogInfo(createId,"modify",2,"abnormal operation",Ip,DateUtil.getDateString());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        logger.info("updateRole method end");
        return this.getModel();
    }


}
