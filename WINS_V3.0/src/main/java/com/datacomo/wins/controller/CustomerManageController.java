package com.datacomo.wins.controller;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.CustomerManageService;
import com.datacomo.wins.service.SysLogRecordService;
import com.datacomo.wins.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/10/12.
 */
@Controller
@RequestMapping(value="/customer")//url映射方法前缀
public class CustomerManageController extends BaseController {
    private static Logger logger = Logger.getLogger(CustomerManageController.class.getName());
    @Autowired
    private CustomerManageService customerManageService;
    @Autowired
    private SysLogRecordService sysLogRecordService;
    
    @RequestMapping(value = "/createCustomer.html")
    public String toCreateCustomerView(){
        logger.info("toCreateCustomerView method start");
        logger.info("toCreateCustomerView method end");
        String temp=this.getRequest().getParameter("temp")==null?"":this.getRequest().getParameter("temp");
        this.getRequest().setAttribute("pagetemp", temp);
        return "customer/createCustomer";
    }
    
/*    @RequestMapping(value = "/rechargeCustomer.html")
    public String torechargeCustomerView(){
        logger.info("torechargeCustomerView method start");
        logger.info("torechargeCustomerView method end");
        String temp=this.getRequest().getParameter("temp")==null?"":this.getRequest().getParameter("temp");
        this.getRequest().setAttribute("pagetemp", temp);
        return "customer/rechargeCustomer";
    }*/

    @RequestMapping(value = "/checkCustomer.html")
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
    }

    @RequestMapping(value = "/editCustomer.html")
    public String toEditCustomerView(int customerId){
        logger.info("toEditCustomerView method start");
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
        logger.info("toEditCustomerView method end");
        return "customer/editCustomer";
    }

    /**
     * 去充值页面
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/rechargeCustomer.html")
    public String torechargeCustomerView(int customerId){
        logger.info("torechargeCustomerView method start");
        Map<String,Object> result = new HashMap<>();
        result.put("customerId",customerId);
        this.getRequest().setAttribute("result",result);
        logger.info("torechargeCustomerView method end");
        return "customer/rechargeCustomer";
    }

    /**
     * 去资金记录页面
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/transactionRecord.html")
    public String toTransactionRecordView(int customerId){
        logger.info("toTransactionRecordView method start");
        this.getCondition().put("customerId",customerId);
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today=df.format(date);
        //查询条件
        String cityId = "";
        if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
            cityId = this.getSession().getAttribute("cityId").toString();
            this.getCondition().put("cityId",cityId);
        }
        String startTime=this.getRequest().getParameter("startTime")==null?today:this.getRequest().getParameter("startTime");
        String endTime=this.getRequest().getParameter("endTime")==null?today:this.getRequest().getParameter("endTime");
        this.getCondition().put("startTime",startTime);
        this.getCondition().put("endTime",endTime);
        Map<String,Object> result = null;
        try {
            result = customerManageService.findCustomerTradeInfoByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
            this.getRequest().setAttribute("result",result);
        } catch (ServiceException e) {
            e.printStackTrace();
            logger.error(e);
            this.getRequest().setAttribute("result",null);
        }
        logger.info("toTransactionRecordView method end");
        return "customer/transactionRecord";
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
        int customerId = Integer.parseInt(String.valueOf(this.getRequest().getParameter("customerId")));
        int createUser =this.getVisitId();
        int code=0;
        try {
            code= customerManageService.deleteCustomerById(this.getVisitId(),customerId);
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

    /**
     * 添加客户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public ResultModel addCustomer(){
        logger.info("addCustomer method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser=this.getVisitId();
        int result = 0;
        try {
            String contractCode = this.getRequest().getParameter("contractCode")==null?"":this.getRequest().getParameter("contractCode");
            String customerName = this.getRequest().getParameter("customerName")==null?"":this.getRequest().getParameter("customerName");
            String customerPhone = this.getRequest().getParameter("customerPhone")==null?"":this.getRequest().getParameter("customerPhone");
            String customerEmail = this.getRequest().getParameter("customerEmail")==null?"":this.getRequest().getParameter("customerEmail");
            String customerCompany = this.getRequest().getParameter("customerCompany")==null?"":this.getRequest().getParameter("customerCompany");
            String customerOffice = this.getRequest().getParameter("customerOffice")==null?"":this.getRequest().getParameter("customerOffice");
            String customerAddress = this.getRequest().getParameter("customerAddress")==null?"":this.getRequest().getParameter("customerAddress");
            String remarks = this.getRequest().getParameter("remarks")==null?"":this.getRequest().getParameter("remarks");
            String  accountBalance= this.getRequest().getParameter("accountBalance")==null?"":this.getRequest().getParameter("accountBalance");
            this.getCondition().put("contractCode",contractCode);
            this.getCondition().put("customerName",customerName);
            this.getCondition().put("customerPhone",customerPhone);
            this.getCondition().put("customerEmail",customerEmail);
            this.getCondition().put("customerEmail",customerEmail);
            this.getCondition().put("customerCompany",customerCompany);
            this.getCondition().put("customerOffice",customerOffice);
            this.getCondition().put("customerAddress",customerAddress);
            this.getCondition().put("remarks",remarks);
            this.getCondition().put("createUser",this.getVisitId());
            this.getCondition().put("createTime",DateUtil.getDateString());
            result = customerManageService.insertCustomerInfo(this.getVisitId(),this.getCondition());
            if(result<=0){
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("Add customer failed");
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
     * 修改客户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit")
    public ResultModel editCustomer(){
        logger.info("editCustomer method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser=this.getVisitId();
        boolean result = false;
        try {
            String customerId = this.getRequest().getParameter("customerId");
            String contractCode = this.getRequest().getParameter("contractCode")==null?"":this.getRequest().getParameter("contractCode");
            String customerName = this.getRequest().getParameter("customerName")==null?"":this.getRequest().getParameter("customerName");
            String customerPhone = this.getRequest().getParameter("customerPhone")==null?"":this.getRequest().getParameter("customerPhone");
            String customerEmail = this.getRequest().getParameter("customerEmail")==null?"":this.getRequest().getParameter("customerEmail");
            String customerCompany = this.getRequest().getParameter("customerCompany")==null?"":this.getRequest().getParameter("customerCompany");
            String customerOffice = this.getRequest().getParameter("customerOffice")==null?"":this.getRequest().getParameter("customerOffice");
            String customerAddress = this.getRequest().getParameter("customerAddress")==null?"":this.getRequest().getParameter("customerAddress");
            String remarks = this.getRequest().getParameter("remarks")==null?"":this.getRequest().getParameter("remarks");
            this.getCondition().put("customerId",customerId);
            this.getCondition().put("contractCode",contractCode);
            this.getCondition().put("customerName",customerName);
            this.getCondition().put("customerPhone",customerPhone);
            this.getCondition().put("customerEmail",customerEmail);
            this.getCondition().put("customerCompany",customerCompany);
            this.getCondition().put("customerOffice",customerOffice);
            this.getCondition().put("customerAddress",customerAddress);
            this.getCondition().put("remarks",remarks);
            result = customerManageService.updateCustomerInfo(this.getVisitId(),this.getCondition(),this.getVisitId());
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
        logger.info("editCustomer method end");
        return this.getModel();
    }

    /**
     * 客户充值
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recharge")
    public ResultModel rechargeCustomer(){
        logger.info("rechargeCustomer method start");
        String Ip = this.getRequest().getRemoteAddr();
        int createUser=this.getVisitId();
        int result = 0;
        try {
            String customerId = this.getRequest().getParameter("customerId");
            String  tradeAmount= this.getRequest().getParameter("tradeAmount")==null?"":this.getRequest().getParameter("tradeAmount");
            this.getCondition().put("customerId",customerId);
            this.getCondition().put("tradeType","2");
            this.getCondition().put("tradeAmount",tradeAmount);
            this.getCondition().put("tradeRemark","recharged");
            this.getCondition().put("tradeTime",DateUtil.getDateString());
            this.getCondition().put("createUser",createUser);
            this.getCondition().put("createTime",DateUtil.getDateString());
            result = customerManageService.insertRechargenfo(this.getVisitId(),this.getCondition(),this.getVisitId());
            if(result<=0){
                this.getModel().setCode(ResultCodeConstants.ERROR);
                this.getModel().setDesc("Recharge failed");
                sysLogRecordService.insertLogInfo(createUser,"Recharge",2,"Recharge failed",Ip,DateUtil.getDateString());
            }else{
                this.getModel().setCode(ResultCodeConstants.SUCCESS);
                this.getModel().setDesc("Recharge successfully！");
                sysLogRecordService.insertLogInfo(createUser,"Recharge",1,"Recharge successfully ",Ip,DateUtil.getDateString());
            }
        } catch (ServiceException e) {
            this.getModel().setCode(ResultCodeConstants.ERROR);
            this.getModel().setDesc("Recharge failed");
            try {
                sysLogRecordService.insertLogInfo(createUser,"Recharge",2,"Recharge failed",Ip,DateUtil.getDateString());
            } catch (ServiceException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("rechargeCustomer method end");
        return this.getModel();
    }

}
