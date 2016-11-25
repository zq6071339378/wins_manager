package com.datacomo.wins.controller;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.ActivityManageService;
import com.datacomo.wins.service.CustomerManageService;
import com.datacomo.wins.service.IPSManageService;
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
@RequestMapping(value="/ips")//url映射方法前缀
public class IPSManageController extends BaseController {
    private static Logger logger = Logger.getLogger(IPSManageController.class.getName());
    @Autowired
    private IPSManageService ipsManageService;
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
    @RequestMapping(value = "/getIPSInfo")
    public ResultModel getIPSInfo(){
        logger.info("getIPSInfo method start");
        int ipsId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("ipsId")));
        this.getCondition().put("ipsId",ipsId);
        Map<String,Object> result= null;
        try {
            result = ipsManageService.getIpsInfo(ipsId,this.getCondition());
            this.getModel().setResult(result);
            this.getModel().setCode(ResultCodeConstants.SUCCESS);
        } catch (ServiceException e) {
            e.printStackTrace();
            this.getModel().setResult(null);
            this.getModel().setCode(ResultCodeConstants.ERROR);
            return this.getModel();
        }
        logger.info("getIPSInfo method end");
        return this.getModel();
    }

    @ResponseBody
    @RequestMapping(value = "/getProvinceCityInfo")
    public ResultModel getProvinceCityInfo(){
        logger.info("getProvinceCityInfo method start");
        String provinceId = String.valueOf(this.getSession().getAttribute("provinceId"));
        this.getCondition().put("provinceId",provinceId);
        if(this.getSession().getAttribute("cityId")!=null){
            String cityId = String.valueOf(this.getSession().getAttribute("cityId"));
            this.getCondition().put("cityId",cityId);
        }
        Map<String,Object> result= null;
        try {
            result = ipsManageService.findProvinceCityInfo(this.getCondition());
            this.getModel().setResult(result);
            this.getModel().setCode(ResultCodeConstants.SUCCESS);
        } catch (ServiceException e) {
            e.printStackTrace();
            this.getModel().setResult(null);
            this.getModel().setCode(ResultCodeConstants.ERROR);
            return this.getModel();
        }
        logger.info("getProvinceCityInfo method end");
        return this.getModel();
    }

    /**
     * 根据用户ID删除客户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultModel deleteIPS(){
        logger.info("deleteIPS method start");
        String Ip = this.getRequest().getRemoteAddr();
        int ipsId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("ipsId")));
        int createUser =this.getVisitId();
        int result=0;
        try {
            result= ipsManageService.deleteIpsById(this.getVisitId(),ipsId);
            if (result>0){
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("Delete successfully");
                sysLogRecordService.insertLogInfo(createUser,"delete",1,"delete ips successfully",Ip, DateUtil.getDateString());
            }else{
                this.getModel().setCode(ResultCodeConstants.REQUEST_METHOD_ERROR);
                this.getModel().setDesc("Delete failed");
                sysLogRecordService.insertLogInfo(createUser,"delete",2,"delete ips failed",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("abnormal operation!");
            try {
                sysLogRecordService.insertLogInfo(createUser,"delete",2,"delete ips failed",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteIPS method end");
        return this.getModel();
    }

    /**
     * 添加IPS
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public ResultModel addIPS(){
        logger.info("addIPS method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser=this.getVisitId();
        int result = 0;
        int count=0;
        int firstIpsId=0;
        int ipsId=-1;
        try {
            String ipsName = this.getRequest().getParameter("ipsName")==null?"":this.getRequest().getParameter("ipsName");
            String provinceId = this.getRequest().getParameter("provinceId")==null?"":this.getRequest().getParameter("provinceId");
            String cityId = this.getRequest().getParameter("cityId")==null?"":this.getRequest().getParameter("cityId");
            String ipsIp = this.getRequest().getParameter("ipsIp")==null?"":this.getRequest().getParameter("ipsIp");
            count=ipsManageService.count();
            if(count==0){
                ipsId=0;
            }else if(count==-1){
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("System exception,try again later!");
                return this.getModel();
            }else{
                firstIpsId = ipsManageService.getMaxValue();
                ipsId=firstIpsId+1;
            }
            Map<String, Object> mapParm=new HashMap<String, Object>();
            mapParm.put("Ips_Id",ipsId);
            mapParm.put("Province_Id",provinceId);
            mapParm.put("City_Id",cityId);
            mapParm.put("Ips_Name",ipsName);
            mapParm.put("Ips_Ip",ipsIp);
            mapParm.put("Ips_Status",1);
            mapParm.put("Create_User",this.getVisitId());
            mapParm.put("Create_Time",DateUtil.getDateString());
            this.getCondition().put("condition",mapParm);
            result = ipsManageService.insertIpsInfo(this.getVisitId(),this.getCondition());
            if(result>0){
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("succeed！");
                sysLogRecordService.insertLogInfo(createUser,"Add",1,"Add ips successfully ",Ip,DateUtil.getDateString());
            }else{
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("Add ips failed");
                sysLogRecordService.insertLogInfo(createUser,"Add",2,"Add ips failed",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("Add ips failed");
            try {
                sysLogRecordService.insertLogInfo(createUser,"Add",2,"Add ips failed",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("addIPS method end");
        return this.getModel();
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus")
    public ResultModel updateIPSStatus(){
        logger.info("updateIPSStatus method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser=this.getVisitId();
        boolean result = false;
        int code = 0;
        try {
            String ipsId= this.getRequest().getParameter("ipsId")==null?"":this.getRequest().getParameter("ipsId");
            String ipsStatus = this.getRequest().getParameter("ipsStatus")==null?"":this.getRequest().getParameter("ipsStatus");
            if(ipsId!="" && ipsStatus!=""){
                this.getCondition().put("ipsId",ipsId);
                this.getCondition().put("ipsStatus",ipsStatus);
                result = ipsManageService.updateIpsInfo(this.getVisitId(),this.getCondition(),this.getVisitId());
                if(result){
                    code =ipsManageService.synchronzeIpsStatusToICS(Integer.parseInt(ipsId),Integer.parseInt(ipsStatus));
                    System.out.println("同步结果---------------------------------------------------： "+code);
                    this.getModel().setCode(ResultCodeConstants.SUCCESS);
                    this.getModel().setDesc("Synchronize data to ICS successfully.");
                }
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("System exception, please try again later");
            try {
                sysLogRecordService.insertLogInfo(createUser,"Edit",2,"Edit ips failed",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("updateIPSStatus method end");
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
            String ipsId= this.getRequest().getParameter("ipsId")==null?"":this.getRequest().getParameter("ipsId");
            String ipsName = this.getRequest().getParameter("ipsName")==null?"":this.getRequest().getParameter("ipsName");
            String provinceId = this.getRequest().getParameter("provinceId")==null?"":this.getRequest().getParameter("provinceId");
            String cityId = this.getRequest().getParameter("cityId")==null?"":this.getRequest().getParameter("cityId");
            String ipsIp = this.getRequest().getParameter("ipsIp")==null?"":this.getRequest().getParameter("ipsIp");
            this.getCondition().put("ipsId",ipsId);
            this.getCondition().put("ipsName",ipsName);
            this.getCondition().put("provinceId",provinceId);
            this.getCondition().put("cityId",cityId);
            this.getCondition().put("ipsIp",ipsIp);

            result = ipsManageService.updateIpsInfo(this.getVisitId(),this.getCondition(),this.getVisitId());
            if(result){
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("Edit ips succeed!");
                sysLogRecordService.insertLogInfo(createUser,"Edit",1,"Edit ips successfully ",Ip,DateUtil.getDateString());
            }else{
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("Edit ips failed!");
                sysLogRecordService.insertLogInfo(createUser,"Edit",2,"Edit ips failed",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("Edit ips failed!");
            try {
                sysLogRecordService.insertLogInfo(createUser,"Edit",2,"Edit ips failed",Ip,DateUtil.getDateString());
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
