package com.datacomo.wins.controller;

import com.datacomo.wins.service.SysTaskService;
import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by duanlinzhuo on 2016/5/16.
 */
@Controller
@RequestMapping(value = "/systasks")
public class SysTasksController extends BaseController {
    private static Logger logger=Logger.getLogger(SysTasksController.class.getName());
    @Autowired
    private SysTaskService sysTaskService;

   /**
    * 跳转监控报表
    * @return
    */
    @RequestMapping(value = "reportshow.html")
    public String directReportList(){
        logger.info("directReportList method start");
//        Properties pro = new Properties();
//		try {
//			InputStream inStr = Thread.currentThread().getContextClassLoader().getResourceAsStream("serverconfig.properties");
//			pro.load(inStr);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Iterator it = pro.entrySet().iterator();
		List<Map<String, Object>> serverResult = null;
		try {
			serverResult = sysTaskService.getServerInfo();
		} catch (ServiceException e1) {
			e1.printStackTrace();
			logger.error(e1);
		}
		List<String> serverList = new ArrayList<String>();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String key = (String) entry.getKey();
//			serverList.add(key);
//		}
		for (Map<String, Object> m : serverResult) {
			String servername = (String) m.get("Server_Name");
			serverList.add(servername);
		}
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("Server_List", serverList);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
			date = dateFormat.parse(this.getRequest().getParameter("Create_Time"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        result.put("Create_Time", date);
        result.put("Report_Date", this.getRequest().getParameter("Report_Date"));
        result.put("Server_Ip", this.getRequest().getParameter("Server_Ip"));
        result.put("Server_Name", this.getRequest().getParameter("Server_Name"));
        result.put("Cpu_Usage", this.getRequest().getParameter("Cpu_Usage"));
        result.put("Memory_Usage", this.getRequest().getParameter("Memory_Usage"));
        result.put("Harddisk_Usage", this.getRequest().getParameter("Harddisk_Usage"));
        result.put("Network_Uplink", this.getRequest().getParameter("Network_Uplink"));
        result.put("Network_Downlink", this.getRequest().getParameter("Network_Downlink"));
        this.getRequest().setAttribute("result", result);
        logger.info("directReportList method end");
        return "monitor/monitorReport";
    }
    
    @ResponseBody
	@RequestMapping(value="monitorlistJson")
	public ResultModel monitorlistJson() {
		logger.info("monitorlistJson method start");
		String Report_Date = this.getRequest().getParameter("Report_Date");
		String Server_Name = this.getRequest().getParameter("Server_Name");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(Report_Date==null||Server_Name==null){
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("Query date or the server name is empty");
			return this.getModel();
		}
		condition.put("Report_Date", Report_Date);
		condition.put("Server_Name", Server_Name);
		try {
			Map<String,Object> result = sysTaskService.getTasksByCondition(this.getVisitId(), this.getVisitId(), condition);
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("monitorlistJson method end");
		return this.getModel();
	}
    
    @ResponseBody
	@RequestMapping(value="monitorAlarmJson")
	public ResultModel monitorAlarmJson() {
		logger.info("monitorAlarmJson method start");
		try {
			Map<String,Object> result = sysTaskService.getMonitorAlarmInfo();
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("monitorAlarmJson method end");
		return this.getModel();
	}
    
    @ResponseBody
	@RequestMapping(value="updateAlarm")
	public ResultModel updateAlarm() {
		logger.info("updateAlarm method start");
		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String, Object> userCondition = new HashMap<String, Object>();
		String Cpu_Limit = this.getRequest().getParameter("Cpu_Limit");
		String Memory_Limit = this.getRequest().getParameter("Memory_Limit");
		String Harddisk_Limit = this.getRequest().getParameter("Harddisk_Limit");
		String Receive_Name = this.getRequest().getParameter("Receive_Name");
//		String User_Id = this.getRequest().getParameter("User_Id");
		if(Cpu_Limit==null||Memory_Limit==null||Harddisk_Limit==null||Receive_Name==null) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("reset！");
			return this.getModel();
		}
		int User_Id = 0;
		userCondition.put("User_Name", Receive_Name);
		try {
			User_Id = sysTaskService.getUserId(userCondition);
		} catch (ServiceException e1) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("failed");
			return this.getModel();
		}
		if (User_Id==0) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("failed");
			return this.getModel();
		}
		condition.put("Cpu_Limit", Cpu_Limit);
		condition.put("Memory_Limit", Memory_Limit);
		condition.put("Harddisk_Limit", Harddisk_Limit);
		condition.put("Receive_Name", Receive_Name);
		condition.put("User_Id", User_Id);
		try {
			sysTaskService.updateMonitorAlarmInfo(condition);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("failed");
		}
		logger.info("updateAlarm method end");
		return this.getModel();
	}
    
    @RequestMapping("reportExport")
	public void reportExport() throws ServiceException{
		try {
			logger.info("reportExport method start");
			Map<String,Object> result =null;
			Map<String, Object> condition = new HashMap<String, Object>();
			
			String Report_Date=this.getRequest().getParameter("Report_Date");
			try {
				if(StringUtils.isNotBlank(Report_Date)){
					Report_Date=new String(Report_Date.getBytes("ISO-8859-1"), "utf-8");
					condition.put("Report_Date", Report_Date);
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			result = sysTaskService.getTasksByCondition(this.getVisitId(), this.getVisitId(), condition);
			
			
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("Monitor_report.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 11);//起始行号，结束行号，起始列号，结束列号
			
			//1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			
			//1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("Monitor report");
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
			cell1.setCellValue("Monitor report");
			
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			
			String[] titles = {"sequence","Device name","IP","CPU usage","Memory usage","Hard disk usage","Network uplink","Network down","Monitoring time"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			//4、操作单元格；将合同台账列表写入excel
			if(result != null){
					List list=(List)result.get("list");
					for(int j=0;j<list.size();j++){
						Map map=(Map)list.get(j);
						HSSFRow row = sheet.createRow(j+2);
						HSSFCell cell = row.createCell(0);
						cell.setCellValue(j+1);
						HSSFCell cell2 = row.createCell(1);
						cell2.setCellValue(map.get("Server_Name")+"");
						HSSFCell cell3 = row.createCell(2);
						cell3.setCellValue(map.get("Server_Ip")+"");
						HSSFCell cell4 = row.createCell(3);
						cell4.setCellValue(map.get("Cpu_Usage")+"%"+"");
						HSSFCell cell5 = row.createCell(4);
						cell5.setCellValue(map.get("Memory_Usage")+"%"+"");
						HSSFCell cell6 = row.createCell(5);
						cell6.setCellValue(map.get("Harddisk_Usage")+"%"+"");
						HSSFCell cell7 = row.createCell(6);
						cell7.setCellValue(map.get("Network_Uplink")+"kbps"+"");
						HSSFCell cell8 = row.createCell(7);
						cell8.setCellValue(map.get("Network_Downlink")+"kbps"+"");
						HSSFCell cell9 = row.createCell(8);
						cell9.setCellValue(map.get("Report_Date")+"  "+map.get("Report_Period")+":00:00"+"");
					}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
			logger.info("reportExport method end");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
