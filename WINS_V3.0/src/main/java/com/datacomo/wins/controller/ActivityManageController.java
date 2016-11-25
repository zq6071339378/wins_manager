package com.datacomo.wins.controller;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.ActivityManageService;
import com.datacomo.wins.service.CustomerManageService;
import com.datacomo.wins.service.SysLogRecordService;
import com.datacomo.wins.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/10/12.
 */
@Controller
@RequestMapping(value="/activity")//url映射方法前缀
public class ActivityManageController extends BaseController {
    private static Logger logger = Logger.getLogger(ActivityManageController.class.getName());
    @Autowired
    private ActivityManageService activityManageService;
    @Autowired
    private CustomerManageService customerManageService;
    @Autowired
    private SysLogRecordService sysLogRecordService;
    @RequestMapping(value = "/createActivity.html")
    public String toCreateActivityView(){
        logger.info("toCreateActivityView method start");
        //广告主
        logger.info("toCreateActivityView method end");
        return "activity/createActivity";
    }

    @ResponseBody
    @RequestMapping(value = "/getCustomerInfo")
    public ResultModel getCustomerInfo(){
        logger.info("getCustomerInfo method start");
        int userId = Integer.parseInt(String.valueOf(this.getSession().getAttribute("userId")));
        this.getCondition().put("userId",userId);
        Map<String,Object> result= null;
        try {
            this.getCondition().remove("page");
            result = customerManageService.findCustomersByCondtion(userId,userId,this.getCondition());
            this.getModel().setResult(result);
            this.getModel().setCode(ResultCodeConstants.SUCCESS);
        } catch (ServiceException e) {
            e.printStackTrace();
            this.getModel().setResult(null);
            this.getModel().setCode(ResultCodeConstants.ERROR);
            return this.getModel();
        }
        logger.info("getCustomerInfo method end");
        return this.getModel();
    }
   /* @RequestMapping(value = "/checkCustomer.html")
    public String toCheckCustomerView(int customerId){
        logger.info("toCheckCustomerView method start");
        this.getCondition().put("customerId",customerId);
        Map<String,Object> result = null;
        try {
            result = customerManageService.getCustomerInfo(this.getVisitId(),this.getCondition());
            this.getRequest().setAttribute("result",result);
        } catch (ServiceException e) {
            e.printStackTrace();
            logger.error(e);
            this.getRequest().setAttribute("result",null);
        }
        logger.info("toCheckCustomerView method end");
        return "customer/checkCustomer";
    }*/

    @RequestMapping(value = "/editActivity.html")
    public String toEditAvtivityView(int activityId){
        logger.info("toEditAvtivityView method start");
        this.getCondition().put("activityId",activityId);
        Map<String,Object> result = null;
        try {
            result = activityManageService.getActivityInfo(this.getVisitId(),this.getCondition());
            this.getRequest().setAttribute("result",result);
        } catch (ServiceException e) {
            e.printStackTrace();
            logger.error(e);
            this.getRequest().setAttribute("result",null);
        }
        logger.info("toEditAvtivityView method end");
        return "activity/editActivity";
    }

    /**
     * 根据用户ID删除客户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultModel deleteActivity(){
        logger.info("deleteActivity method start");
        String Ip = this.getRequest().getRemoteAddr();
        int activityId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("activityId")));
        int createUser =this.getVisitId();
        int result=0;
        try {
            result= activityManageService.deleteActivityById(this.getVisitId(),activityId);
            if (result>0){
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("Delete successfully");
                sysLogRecordService.insertLogInfo(createUser,"delete",1,"delete activity successfully",Ip, DateUtil.getDateString());
            }else{
                this.getModel().setCode(ResultCodeConstants.REQUEST_METHOD_ERROR);
                this.getModel().setDesc("Delete failed");
                sysLogRecordService.insertLogInfo(createUser,"delete",2,"delete activity failed",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation!");
            try {
                sysLogRecordService.insertLogInfo(createUser,"delete",2,"delete activity failed",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteActivity method end");
        return this.getModel();
    }

    /**
     * 添加客户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public ResultModel addActivity(){
        logger.info("addActivity method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser=this.getVisitId();
        int result = 0;
        try {
            String activityName = this.getRequest().getParameter("activityName")==null?"":this.getRequest().getParameter("activityName");
            String data_Id = this.getRequest().getParameter("data_Id")==null?"":this.getRequest().getParameter("data_Id");
            String activityDesc = this.getRequest().getParameter("activityDesc")==null?"":this.getRequest().getParameter("activityDesc");
            Map<String, Object> mapParm=new HashMap<String, Object>();
            mapParm.put("Activity_Name",activityName);
            mapParm.put("Activity_Desc",activityDesc);
            mapParm.put("Create_User",this.getVisitId());
            mapParm.put("Create_Time",DateUtil.getDateString());
            this.getCondition().put("condition",mapParm);
            result = activityManageService.insertActivityInfo(this.getVisitId(),this.getCondition());
            this.getModel().setResult(result);
            if(result>0){
                data_Id.substring(0,data_Id.length());
                String[] customerIds=data_Id.split(",");
                List<Map<String, Object>> lis=new ArrayList<Map<String, Object>>();
                for (String str:customerIds){
                    Map<String,Object> condition = new HashMap<String,Object>();
                    condition.put("Customer_Id",str);
                    condition.put("Activity_Id",result);
                    condition.put("Create_User",this.getVisitId());
                    condition.put("Create_Time",DateUtil.getDateString());
                    lis.add(condition);
                }
                this.getCondition().put("LIST",lis);
                result=activityManageService.insertActivityCustomerRelation(this.getVisitId(),this.getCondition());
                if(result>0){
                    this.getModel().setCode(ResultCodeConstants.SUCCESS);
                    this.getModel().setDesc("succeed！");
                    sysLogRecordService.insertLogInfo(createUser,"Add",1,"Add activity successfully ",Ip,DateUtil.getDateString());
                }
            }else{
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("abnormal operation！");
                sysLogRecordService.insertLogInfo(createUser,"Add",2,"Add activity failed",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("Add customer failed");
            try {
                sysLogRecordService.insertLogInfo(createUser,"Add",2,"Add activity failed",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("addActivity method end");
        return this.getModel();
    }

    /**
     * 修改客户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit")
    public ResultModel editActivity(){
        logger.info("editActivity method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser=this.getVisitId();
        boolean result = false;
        int code = 0;
        try {
            int activityId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("activityId")));
            String activityName = this.getRequest().getParameter("activityName")==null?"":this.getRequest().getParameter("activityName");
            String data_Id = this.getRequest().getParameter("data_Id")==null?"":this.getRequest().getParameter("data_Id");
            String activityDesc = this.getRequest().getParameter("activityDesc")==null?"":this.getRequest().getParameter("activityDesc");
            this.getCondition().put("activityId",activityId);
            this.getCondition().put("activityName",activityName);
            this.getCondition().put("activityDesc",activityDesc);
            result = activityManageService.updateActivityInfo(this.getVisitId(),this.getCondition(),this.getVisitId());
            if(result){
                code=activityManageService.deleteActivityCustomerRelation(this.getVisitId(),activityId);
                data_Id.substring(0,data_Id.length());
                String[] customerIds=data_Id.split(",");
                List<Map<String, Object>> lis=new ArrayList<Map<String, Object>>();
                for (String str:customerIds){
                    Map<String,Object> condition = new HashMap<String,Object>();
                    condition.put("Customer_Id",str);
                    condition.put("Activity_Id",activityId);
                    condition.put("Create_User",this.getVisitId());
                    condition.put("Create_Time",DateUtil.getDateString());
                    lis.add(condition);
                }
                this.getCondition().put("LIST",lis);
                code=activityManageService.insertActivityCustomerRelation(this.getVisitId(),this.getCondition());
                if(code>0){
                    this.getModel().setCode(ResultCodeConstants.SUCCESS);
                    this.getModel().setDesc("Edit activity succeed！");
                    sysLogRecordService.insertLogInfo(createUser,"Edit",1,"Edit activity successfully ",Ip,DateUtil.getDateString());
                }
            }else{
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("Edit activity failed");
                sysLogRecordService.insertLogInfo(createUser,"Edit",2,"Edit activity failed",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation!");
            try {
                sysLogRecordService.insertLogInfo(createUser,"Edit",2,"Edit activity failed",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("editActivity method end");
        return this.getModel();
    }


}
