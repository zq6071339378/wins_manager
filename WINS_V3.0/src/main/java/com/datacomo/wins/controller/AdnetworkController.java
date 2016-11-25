package com.datacomo.wins.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.AdnetworkService;
import com.datacomo.wins.service.SysLogRecordService;
import com.datacomo.wins.util.DateUtil;

@Controller
@RequestMapping(value="/ADnetwork")
public class AdnetworkController extends BaseController{
   
	 private static Logger logger = Logger.getLogger(AdnetworkController.class.getName());
	  @Autowired
	    private AdnetworkService adnetworkService;
	    @Autowired
	    private SysLogRecordService sysLogRecordService;
	    @RequestMapping(value = "/createAdnetwork.html")
	    public String toCreateCustomerView(){
	        logger.info("toCreateAdnetworkView method start");
	        logger.info("toCreateAdnetworkView method end");
	        return "ADnetwork/createAdnetwork";
	    }
	    /**
	     * 添加ADnetwork
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value = "/add")
	    public ResultModel addCustomer(){
	        logger.info("addAdnetwork method start");
	        String Ip = this.getRequest().getRemoteAddr();
	        int createUser=this.getVisitId();
	        int result = 0;
	        try {
	            String networkId = this.getRequest().getParameter("networkId")==null?"":this.getRequest().getParameter("networkId");
	            String networkName = this.getRequest().getParameter("AdnetworkName")==null?"":this.getRequest().getParameter("AdnetworkName");
	            String buyType = this.getRequest().getParameter("buyType")==null?"":this.getRequest().getParameter("buyType");
	            String totalPrice = this.getRequest().getParameter("totalPrice")==null?"":this.getRequest().getParameter("totalPrice");
	            String exportFlux = this.getRequest().getParameter("exportFlux")==null?"":this.getRequest().getParameter("exportFlux");
	            String flowRadio = this.getRequest().getParameter("flowRadio")==null?"":this.getRequest().getParameter("flowRadio");
	            String startTime = this.getRequest().getParameter("startTime")==null?"":this.getRequest().getParameter("startTime");
	            String endTime = this.getRequest().getParameter("endTime")==null?"":this.getRequest().getParameter("endTime");
	            this.getCondition().put("networkName",networkName);
	            this.getCondition().put("buyType",buyType);
	            this.getCondition().put("totalPrice",totalPrice);
	            this.getCondition().put("exportFlux",exportFlux);
	            this.getCondition().put("flowRadio",flowRadio);
	            this.getCondition().put("startTime",startTime);
	            this.getCondition().put("endTime",endTime);
	            this.getCondition().put("networkId",networkId);
	            this.getCondition().put("createUser",this.getVisitId());
	            this.getCondition().put("createTime",DateUtil.getDateString());
	            result = adnetworkService.insertAdnetworkInfo(this.getVisitId(),this.getCondition());
	            if(result==0){
	                this.getModel().setCode(ResultCodeConstants.ERROR);
	                this.getModel().setDesc("abnormal operation！");
	                sysLogRecordService.insertLogInfo(createUser,"Add",2,"Add customer failed",Ip,DateUtil.getDateString());
	            }else{
	                this.getModel().setCode(ResultCodeConstants.SUCCESS);
	                this.getModel().setDesc("succeed！");
	                sysLogRecordService.insertLogInfo(createUser,"Add",1,"Add customer successfully ",Ip,DateUtil.getDateString());
	            }
	        } catch (ServiceException e) {
	            this.getModel().setCode(ResultCodeConstants.ERROR);
	            this.getModel().setDesc("Add customer failed");
	            try {
	                sysLogRecordService.insertLogInfo(createUser,"Add",2,"Add customer failed",Ip,DateUtil.getDateString());
	            } catch (ServiceException e1) {
	                e1.printStackTrace();
	            }
	            e.printStackTrace();
	            logger.error(e);
	        }
	        logger.info("addCustomer method end");
	        return this.getModel();
	    }
	    /**
	     * 根据用户ID删除客户
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value = "/delete")
	    public ResultModel deleteCustomer(){
	        logger.info("deleteCustomer method start");
	        String Ip = this.getRequest().getRemoteAddr();
	        int customerId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("networkId")));
	        int createUser =this.getVisitId();
	        int code=0;
	        try {
	            code= adnetworkService.deleteAdnetworkById(this.getVisitId(),customerId);
	            if (code==1){
	                this.getModel().setCode(ResultCodeConstants.SUCCESS);
	                this.getModel().setDesc("Delete successfully");
	                sysLogRecordService.insertLogInfo(createUser,"delete",1,"delete customer successfully",Ip, DateUtil.getDateString());
	            }else{
	                this.getModel().setCode(ResultCodeConstants.REQUEST_METHOD_ERROR);
	                this.getModel().setDesc("Delete failed");
	                sysLogRecordService.insertLogInfo(createUser,"delete",2,"delete customer failed",Ip,DateUtil.getDateString());
	            }
	        } catch (ServiceException e) {
	            this.getModel().setCode(ResultCodeConstants.ERROR);
	            this.getModel().setDesc("abnormal operation！");
	            try {
	                sysLogRecordService.insertLogInfo(createUser,"delete",2,"delete customer failed",Ip,DateUtil.getDateString());
	            } catch (ServiceException e1) {
	                e1.printStackTrace();
	            }
	            e.printStackTrace();
	            logger.error(e);
	        }
	        logger.info("deleteCustomer method end");
	        return this.getModel();
	    }
 
	    @RequestMapping(value = "/checkAdnetwork.html")
	    public String toCheckAdnetworkView(int networkId){
	        logger.info("toCheckAdnetworkView method start");
	        this.getCondition().put("networkId",networkId);
	        Map<String,Object> result = null;
	        try {
	            result = adnetworkService.getAdnetworkInfo(this.getVisitId(),this.getCondition());
	            this.getRequest().setAttribute("result",result);
	        } catch (ServiceException e) {
	            e.printStackTrace();
	            logger.error(e);
	            this.getRequest().setAttribute("result",null);
	        }
	        logger.info("toCheckAdnetworkView method end");
	        return "ADnetwork/checkAdnetwork";
	    }
	    @RequestMapping(value = "/editAdnetwork.html")
	    public String toEditAdnetworkView(int networkId){
	        logger.info("toEditAdnetworkView method start");
	        this.getCondition().put("networkId",networkId);
	        Map<String,Object> result = null;
	        try {
	            result = adnetworkService.getAdnetworkInfo(this.getVisitId(),this.getCondition());
	            this.getRequest().setAttribute("result",result);
	        } catch (ServiceException e) {
	            e.printStackTrace();
	            logger.error(e);
	            this.getRequest().setAttribute("result",null);
	        }
	        logger.info("toEditAdnetworkView method end");
	        return "ADnetwork/editAdnetwork";
	    }
	    /**
	     * 修改Adnetwork
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping(value = "/edit")
	    public ResultModel editAdnetwork(){
	        logger.info("editAdnetwork method start");
	        String Ip = this.getRequest().getRemoteAddr();
	        int createUser=this.getVisitId();
	        boolean result = false;
	        try {
	            String networkId = this.getRequest().getParameter("networkId");
	            String networkName = this.getRequest().getParameter("networkName")==null?"":this.getRequest().getParameter("networkName");
	            String buyType = this.getRequest().getParameter("buyType")==null?"":this.getRequest().getParameter("buyType");
	            String totalPrice = this.getRequest().getParameter("totalPrice")==null?"":this.getRequest().getParameter("totalPrice");
	            String exportFlux = this.getRequest().getParameter("exportFlux")==null?"":this.getRequest().getParameter("exportFlux");
	            String flowRadio = this.getRequest().getParameter("flowRadio")==null?"":this.getRequest().getParameter("flowRadio");
	            String startTime = this.getRequest().getParameter("startTime")==null?"":this.getRequest().getParameter("startTime");
	            String endTime = this.getRequest().getParameter("endTime")==null?"":this.getRequest().getParameter("endTime");
	            this.getCondition().put("networkId",networkId);
	            this.getCondition().put("networkName",networkName);
	            this.getCondition().put("buyType",buyType);
	            this.getCondition().put("totalPrice",totalPrice);
	            this.getCondition().put("exportFlux",exportFlux);
	            this.getCondition().put("flowRadio",flowRadio);
	            this.getCondition().put("startTime",startTime);
	            this.getCondition().put("endTime",endTime);
	        //    this.getCondition().put("remarks",remarks);
	            result = adnetworkService.updateAdnetworkInfo(this.getVisitId(),this.getCondition(),this.getVisitId());
	            if(!result){
	                this.getModel().setCode(ResultCodeConstants.ERROR);
	                this.getModel().setDesc("abnormal operation！");
	                sysLogRecordService.insertLogInfo(createUser,"Edit",2,"Edit customer failed",Ip,DateUtil.getDateString());
	            }else{
	                this.getModel().setCode(ResultCodeConstants.SUCCESS);
	                this.getModel().setDesc("succeed！");
	                sysLogRecordService.insertLogInfo(createUser,"Edit",1,"Edit customer successfully ",Ip,DateUtil.getDateString());
	            }
	        } catch (ServiceException e) {
	            this.getModel().setCode(ResultCodeConstants.ERROR);
	            this.getModel().setDesc("Edit customer failed");
	            try {
	                sysLogRecordService.insertLogInfo(createUser,"Edit",2,"Edit customer failed",Ip,DateUtil.getDateString());
	            } catch (ServiceException e1) {
	                e1.printStackTrace();
	            }
	            e.printStackTrace();
	            logger.error(e);
	        }
	        logger.info("editAdnetwork method end");
	        return this.getModel();
	    }

}
