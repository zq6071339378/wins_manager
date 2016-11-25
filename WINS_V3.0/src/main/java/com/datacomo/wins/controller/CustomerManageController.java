package com.datacomo.wins.controller;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.CustomerManageService;
import com.datacomo.wins.service.SysLogRecordService;
import com.datacomo.wins.util.DateUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        this.getCondition().put("customerId",customerId);
        Map<String,Object> result = null;
        try {
            result=customerManageService.getCustomerInfo(this.getVisitId(),this.getCondition());
            result.put("customerId",customerId);
            this.getRequest().setAttribute("result",result);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        logger.info("torechargeCustomerView method end");
        return "customer/rechargeCustomer";
    }

    @RequestMapping(value = "/exportRecord")
    public void exportTransactionRecord(){
        logger.info("toTransactionRecordView method start");
        //查询条件
        String customerId=this.getRequest().getParameter("customerId");
        String startTime=this.getRequest().getParameter("startTime");
        String endTime=this.getRequest().getParameter("endTime");
        this.getCondition().remove("page");
        this.getCondition().put("startTime",startTime);
        this.getCondition().put("endTime",endTime);
        this.getCondition().put("customerId",customerId);
        Map<String,Object> result = null;
        try {
            //导出用户
            result = customerManageService.findCustomerTradeInfoByCondtion(this.getVisitId(),this.getVisitId(),this.getCondition());
            HttpServletResponse response = this.getResponse();
            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("交易明细.xls".getBytes(), "ISO-8859-1"));
            ServletOutputStream outputStream = response.getOutputStream();
            //1、创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //1.1、创建合并单元格对象
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 8);//起始行号，结束行号，起始列号，结束列号
            //1.2、头标题样式
            HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
            //1.3、列标题样式
            HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
            //2、创建工作表
            HSSFSheet sheet = workbook.createSheet("交易明细");
            //2.1、加载合并单元格对象
            sheet.addMergedRegion(cellRangeAddress);
            //设置默认列宽
            sheet.setDefaultColumnWidth(15);
            //3、创建行
            //3.1、创建头标题行；并且设置头标题
            HSSFRow row1 = sheet.createRow(0);
            HSSFCell cell1 = row1.createCell(0);
            //加载单元格样式
            cell1.setCellStyle(style1);
            cell1.setCellValue("交易明细");
            //3.2、创建列标题行；并且设置列标题
            HSSFRow row2 = sheet.createRow(1);
            String[] titles = {"Sort","合同编号","公司名称","交易类型","交易金额","交易后余额","交易说明","操作账户","交易日期"};
            for(int i = 0; i < titles.length; i++){
                HSSFCell cell2 = row2.createCell(i);
                //加载单元格样式
                cell2.setCellStyle(style2);
                cell2.setCellValue(titles[i]);
            }
            //4、操作单元格；将合同台账列表写入excel
            if(result != null){
                //去重
                List list=(List)result.get("list");
                for(int j=0;j<list.size();j++){
                    Map map=(Map)list.get(j);
                    HSSFRow row = sheet.createRow(j+2);
                    HSSFCell cell = row.createCell(0);
                    cell.setCellValue(j+1);
                    HSSFCell cell2 = row.createCell(1);
                    cell2.setCellValue(map.get("contractCode")+"");
                    HSSFCell cell3 = row.createCell(2);
                    cell3.setCellValue(map.get("customerCompany")+"");
                    HSSFCell cell4 = row.createCell(3);
                    cell4.setCellValue(String.valueOf(map.get("tradeType")).equals("1")?"支出":"收入"+"");
                    HSSFCell cell5 = row.createCell(4);
                    cell5.setCellValue(map.get("tradeAmount")+"");
                    HSSFCell cell6 = row.createCell(5);
                    cell6.setCellValue(map.get("accountBalance")+"");
                    HSSFCell cell7 = row.createCell(6);
                    cell7.setCellValue(map.get("tradeRemark")+"");
                    HSSFCell cell8 = row.createCell(7);
                    cell8.setCellValue(map.get("createName")+"");
                    HSSFCell cell9 = row.createCell(8);
                    cell9.setCellValue(map.get("tradeTime")+"");
                }
            }
            //5、输出
            workbook.write(outputStream);
            workbook.close();
            //导出明细
            logger.info("PolicyinfoController reportExcel method end");
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("toTransactionRecordView method end");
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

    /**
     * 创建单元格样式
     * @param workbook 工作簿
     * @param fontSize 字体大小
     * @return 单元格样式
     */
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //创建字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
        font.setFontHeightInPoints(fontSize);
        //加载字体
        style.setFont(font);
        return style;
    }
}
