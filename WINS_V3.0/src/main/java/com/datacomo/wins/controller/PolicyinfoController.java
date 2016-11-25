package com.datacomo.wins.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


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

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.PolicyinfoService;


/**
 * 策略信息控制器
 * @author liwenjie
 *
 */
@Controller
@RequestMapping(value="/policyinfo")//url映射方法前缀
public class PolicyinfoController extends BaseController {
	private static Logger logger = Logger.getLogger(PolicyinfoController.class.getName());
	@Autowired
	PolicyinfoService policyinfoService;

	@ResponseBody
	@RequestMapping(value = "policyEveryData.json")
	public ResultModel policyEveryData(){
		logger.info(" policyEveryData method start");
		String startTime=this.getRequest().getParameter("startTime");
		String endTime=this.getRequest().getParameter("endTime");
		String policyId=this.getRequest().getParameter("policyId");
		if(startTime.equals(endTime)){
			Map condition = new HashMap<String,Object>();
			int PolicyId=Integer.parseInt(policyId);
			condition.put("Policy_Id", PolicyId);
			condition.put("Report_Date", startTime);
			this.getCondition().put("condition", condition);
			Map<String, Object> result=null;
			try{
				result = policyinfoService.singlePolicyShow(this.getVisitId(),this.getCondition());
				this.getModel().setResult(result);
				this.getModel().setDesc("succeed");
			}catch(ServiceException e){
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("abnormal operation");
			}
		}else{
			this.getCondition().clear();
			this.getCondition().put("startTime",startTime);
			this.getCondition().put("endTime",endTime);
			this.getCondition().put("policyId",policyId);
			Map<String, Object> result=null;
			try {
				result=policyinfoService.policyEveryData(this.getVisitId(),this.getCondition());
				int maxDay= Integer.parseInt(endTime.substring(8,10));
				result.put("maxDay",maxDay);
				this.getModel().setResult(result);
				this.getModel().setDesc("succeed");
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		logger.info(" policyEveryData method end");
		return this.getModel();
	}

	/**
	 * 策略数据统计
	 * @return
     */
	@ResponseBody
	@RequestMapping(value="policyTotalData.json")
	public ResultModel policyTotalData(){
		logger.info(" policyTotalData method start");
		String startTime=this.getRequest().getParameter("startTime");
		String endTime=this.getRequest().getParameter("endTime");
		String policyId=this.getRequest().getParameter("policyId");
		if(startTime.equals(endTime)){ //单个策略一天的数据统计
			this.getCondition().put("create_time",startTime);
			this.getCondition().put("PolicyId", policyId);
			Map<String, Object> result=null;
			try{
				result = policyinfoService.nameListBak(this.getVisitId(),this.getCondition());
				this.getModel().setResult(result);
				this.getModel().setDesc("方法调用成功");
			} catch (ServiceException e) {
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("abnormal operation");
			}
		}else{ //单个策略多天的数据统计
			this.getCondition().clear();
			this.getCondition().put("startTime",startTime);
			this.getCondition().put("endTime",endTime);
			this.getCondition().put("policyId",policyId);
			Map<String, Object> result=null;
			try {
				result=policyinfoService.policyShowTotalData(this.getVisitId(),this.getCondition());
				this.getModel().setResult(result);
				this.getModel().setDesc("succeed");
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		logger.info(" policyTotalData method end");
		return this.getModel();
	}

	@RequestMapping(value = "toView")
	public String toPolicyView(){
		logger.info(" toPolicyView method start");
		String startTime=this.getRequest().getParameter("startTime");
		String endTime=this.getRequest().getParameter("endTime");
		String policyId=this.getRequest().getParameter("policyId");
		Map<String,Object> result=new HashMap<>();
		result.put("startTime",startTime);
		result.put("endTime",endTime);
		result.put("policyId",policyId);
		this.getRequest().setAttribute("result",result);
		logger.info("toPolicyView method end");
		return "policyinfo/reportView";
	}

	/**
	 * 数据对比
	 * @return
     */
	@RequestMapping(value = "dataCompare")
	public String toDataCompare(){
		logger.info("PolicyinfoController toDataCompare method start");
		String startTime=this.getRequest().getParameter("startTime");
		String endTime=this.getRequest().getParameter("endTime");
		String policyIds=this.getRequest().getParameter("policyIds");
		Map<String,Object> result =null;
		List<Map<String,Object>> lis=new ArrayList<>();
		if(policyIds.indexOf(",")!=-1){
			String[] policyId=policyIds.split(",");
			for(String str:policyId){
				Map<String,Object> con = new HashMap<>();
				con.put("policyIds",str);
				lis.add(con);
			}
		}else{ //只有一条
			Map<String,Object> con = new HashMap<>();
			con.put("policyIds",policyIds);
			lis.add(con);
		}
		this.getCondition().put("LIST",lis);
		String cityId = "";
		if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
			cityId = this.getSession().getAttribute("cityId").toString();
			this.getCondition().put("cityId", cityId);
		}
		this.getCondition().put("startTime",startTime);
		this.getCondition().put("endTime",endTime);
		try {
			result = policyinfoService.reportDataExcel(this.getVisitId(),this.getCondition());
			result.put("startTime",startTime);
			result.put("endTime",endTime);
			result.put("policyIds",policyIds);
			this.getRequest().setAttribute("result",result);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			e.printStackTrace();
			this.getRequest().setAttribute("result",null);
			this.getModel().setDesc("error");
		}
		logger.info("PolicyinfoController toDataCompare method end");
		return "policyinfo/reportDataCompare";
	}

	/**
	 * 数据比较时趋势图数据
	 * @return
     */
	@ResponseBody
	@RequestMapping(value="trendData")
	public ResultModel getTrendData(){
		logger.info("getTrendData toDataCompare method start");
		String startTime=this.getRequest().getParameter("startTime");
		String endTime=this.getRequest().getParameter("endTime");
		String policyIds=this.getRequest().getParameter("policyIds");
		Map<String,Object> result =null;
		List<Map<String,Object>> lis=new ArrayList<>();
		if(policyIds.indexOf(",")!=-1){
			String[] policyId=policyIds.split(",");
			for(String str:policyId){
				Map<String,Object> con = new HashMap<>();
				con.put("policyIds",str);
				lis.add(con);
			}
		}else{ //只有一条
			Map<String,Object> con = new HashMap<>();
			con.put("policyIds",policyIds);
			lis.add(con);
		}
		this.getCondition().put("LIST",lis);
		this.getCondition().put("startTime",startTime);
		this.getCondition().put("endTime",endTime);
		try {
			if(startTime.equals(endTime)){
				result=policyinfoService.policyOneDayInfo(this.getCondition());
			}else{
				result=policyinfoService.policyEveryDayInfo(this.getCondition());
				int maxDay= Integer.parseInt(endTime.substring(8,10));
				result.put("maxDay",maxDay);
			}
			this.getModel().setResult(result);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.info("getTrendData toDataCompare method end");
		return this.getModel();
	}
/**
 * 	
 * @return 报表管理总信息
 */
	@RequestMapping(value="showReport")
	public String showReport(){
		logger.info("PolicyinfoController showReport method start");
		try {
			int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
			this.getCondition().put("userId", userId);
			int showStatus=Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus", showStatus);
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
			String selectValue = this.getRequest().getParameter("selectValue");
			if(selectValue==null){
				selectValue="0";
				this.getCondition().put("selectValue",selectValue);
			}
			String selectName=this.getRequest().getParameter("selectName");
			if(selectName!=null){
				try {
					selectName = new String(selectName.getBytes("iso-8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if(selectName!="" && selectName!=null){
				if(selectValue.equals("0")){
					String policyName = selectName;
					this.getCondition().put("policyName",policyName);
				}else if(selectValue.equals("1")){
					String activityName = selectName;
					this.getCondition().put("activityName",activityName);
				}else{
					String customerName = selectName;
					this.getCondition().put("customerName",customerName);
				}
			}
			Map<String, Object> result = policyinfoService.showReport(this.getVisitId(),this.getCondition());
			result.put("startTime",startTime);
			result.put("endTime",endTime);
			result.put("selectValue",selectValue);
			if(selectName!="" && selectName!=null){
				result.put("selectName",selectName);
			}
			this.getRequest().setAttribute("result", result);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("PolicyinfoController showReport method end");
		return "policyinfo/reportManage";
	}
	//导出
	@RequestMapping("reportExcel")
	public void reportExcel() throws ServiceException{
		try {
			logger.info("PolicyinfoController reportExcel method start");
			Map<String,Object> result =null;
			int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
			this.getCondition().put("userId", userId);
			if(this.getSession().getAttribute("showStatus")!=null&&this.getSession().getAttribute("showStatus")!=""){
				int showStatus=Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
				this.getCondition().put("showStatus", showStatus);
			}
			String policyIds=this.getRequest().getParameter("policyIds");
			List<Map<String,Object>> lis=new ArrayList<>();
			if(policyIds.indexOf(",")!=-1){
				String[] policyId=policyIds.split(",");
				for(String str:policyId){
					Map<String,Object> con = new HashMap<>();
					con.put("policyIds",str);
					lis.add(con);
				}
			}else{ //只有一条
				Map<String,Object> con = new HashMap<>();
				con.put("policyIds",policyIds);
				lis.add(con);
			}
			this.getCondition().put("LIST",lis);
			String cityId = "";
			if(this.getSession().getAttribute("cityId")!=null && this.getSession().getAttribute("cityId")!=""){
				cityId = this.getSession().getAttribute("cityId").toString();
				this.getCondition().put("cityId", cityId);
			}
			String startTime=this.getRequest().getParameter("startTime");
			String endTime=this.getRequest().getParameter("endTime");
			this.getCondition().put("startTime",startTime);
			this.getCondition().put("endTime",endTime);
			result = policyinfoService.reportDataExcel(this.getVisitId(),this.getCondition());
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("策略推送详细数据.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			boolean isEnglish = Boolean.valueOf(String.valueOf(Config.message.get("isEnglish")));
			if(isEnglish){
				//1、创建工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();
				//1.1、创建合并单元格对象
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 19);//起始行号，结束行号，起始列号，结束列号

				//1.2、头标题样式
				HSSFCellStyle style1 = createCellStyle(workbook, (short)16);

				//1.3、列标题样式
				HSSFCellStyle style2 = createCellStyle(workbook, (short)13);

				//2、创建工作表
				HSSFSheet sheet = workbook.createSheet("策略推送详细数据");
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
				cell1.setCellValue("策略推送详细数据");

				//3.2、创建列标题行；并且设置列标题
				HSSFRow row2 = sheet.createRow(1);

				String[] titles = {"策略名称","所属活动","所属广告主","推送时间","目标用户","目标上网用户数","推送用户数","推送次数","到达用户数","到达次数","展现用户数","展现次数","点击用户数","点击次数","关闭用户数","关闭次数","到达率","展现率","点击率","关闭率"};
				for(int i = 0; i < titles.length; i++){
					HSSFCell cell2 = row2.createCell(i);
					//加载单元格样式
					cell2.setCellStyle(style2);
					cell2.setCellValue(titles[i]);
				}
				//4、操作单元格；将合同台账列表写入excel
				if(result != null){
					List list=(List)result.get("LIST");
					for(int j=0;j<list.size();j++){
						Map map=(Map)list.get(j);
						HSSFRow row = sheet.createRow(j+2);
						HSSFCell cell = row.createCell(0);
						cell.setCellValue(map.get("PolicyName")+"");
						HSSFCell cell0=row.createCell(1);
						cell0.setCellValue(map.get("activityName")+"");
						HSSFCell cell01=row.createCell(2);
						cell01.setCellValue(map.get("customerName")+"");
						HSSFCell cell2 = row.createCell(3);
						cell2.setCellValue(map.get("startTime")+"--"+map.get("endTime"));
						HSSFCell cell3 = row.createCell(4);
						cell3.setCellValue(map.get("TargetUsers")==null?"--":map.get("TargetUsers")+"");
						HSSFCell cell4 = row.createCell(5);
						cell4.setCellValue("--"+"");
						HSSFCell cell5 = row.createCell(6);
						cell5.setCellValue(map.get("PushUsers")+"");
						HSSFCell cell6 = row.createCell(7);
						cell6.setCellValue(map.get("PushNum")+"");
						HSSFCell cell7 = row.createCell(8);
						cell7.setCellValue(map.get("ReceiveUsers")+"");
						HSSFCell cell8 = row.createCell(9);
						cell8.setCellValue(map.get("ReceiveNum")+"");
						HSSFCell cell9 = row.createCell(10);
						cell9.setCellValue(map.get("ShowUsers")+"");
						HSSFCell cell10 = row.createCell(11);
						cell10.setCellValue(map.get("ShowNum")+"");
						HSSFCell cell11 = row.createCell(12);
						cell11.setCellValue(map.get("ClickUsers")+"");
						HSSFCell cell12 = row.createCell(13);
						cell12.setCellValue(map.get("ClickNum")+"");
						HSSFCell cell13 = row.createCell(14);
						cell13.setCellValue(map.get("CloseUsers")+"");
						HSSFCell cell14 = row.createCell(15);
						cell14.setCellValue(map.get("CloseNum")+"");
						HSSFCell cell15 = row.createCell(16);
						String a1 = String.valueOf(map.get("ReceiveRatio")==null?"0":map.get("ReceiveRatio"));
						String b1 = String.valueOf(map.get("ShowRatio")==null?"0":map.get("ShowRatio"));
						String c1 = String.valueOf(map.get("ClickRatio")==null?"0":map.get("ClickRatio"));
						String d1 = String.valueOf(map.get("CloseRatio")==null?"0":map.get("CloseRatio"));
					/*float a=(float) map.get("ReceiveRatio")*100;
					float b=(float) map.get("ShowRatio")*100;
					float c=(float) map.get("ClickRatio")*100;
					float d=(float) map.get("CloseRatio")*100;*/

					/*BigDecimal   a1   =   new   BigDecimal(a);
					BigDecimal   b1   =   new   BigDecimal(b);
					BigDecimal   c1   =   new   BigDecimal(c);
					BigDecimal   d1   =   new   BigDecimal(d);*/
					/*float ReceiveRatio=a1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					float ShowRatio=b1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					float ClickRatio=c1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					float CloseRatio=d1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();*/
						cell15.setCellValue(a1+"%"+"");
						HSSFCell cell16 = row.createCell(17);
						cell16.setCellValue(b1+"%"+"");
						HSSFCell cell17 = row.createCell(18);
						cell17.setCellValue(c1+"%"+"");
						HSSFCell cell18 = row.createCell(19);
						cell18.setCellValue(d1+"%"+"");
					}
				}
				//5、输出
				workbook.write(outputStream);
				workbook.close();
			}else{
				//1、创建工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();
				//1.1、创建合并单元格对象
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 17);//起始行号，结束行号，起始列号，结束列号

				//1.2、头标题样式
				HSSFCellStyle style1 = createCellStyle(workbook, (short)16);

				//1.3、列标题样式
				HSSFCellStyle style2 = createCellStyle(workbook, (short)13);

				//2、创建工作表
				HSSFSheet sheet = workbook.createSheet("策略推送详细数据");
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
				cell1.setCellValue("策略推送详细数据");

				//3.2、创建列标题行；并且设置列标题
				HSSFRow row2 = sheet.createRow(1);

				String[] titles = {"策略名称","推送时间","目标用户","目标上网用户数","推送用户数","推送次数","到达用户数","到达次数","展现用户数","展现次数","点击用户数","点击次数","关闭用户数","关闭次数","到达率","展现率","点击率","关闭率"};
				for(int i = 0; i < titles.length; i++){
					HSSFCell cell2 = row2.createCell(i);
					//加载单元格样式
					cell2.setCellStyle(style2);
					cell2.setCellValue(titles[i]);
				}
				//4、操作单元格；将合同台账列表写入excel
				if(result != null){
					List list=(List)result.get("LIST");
					for(int j=0;j<list.size();j++){
						Map map=(Map)list.get(j);
						HSSFRow row = sheet.createRow(j+2);
						HSSFCell cell = row.createCell(0);
						cell.setCellValue(map.get("PolicyName")+"");
						HSSFCell cell2 = row.createCell(1);
						cell2.setCellValue(map.get("startTime")+"--"+map.get("endTime"));
						HSSFCell cell3 = row.createCell(2);
						cell3.setCellValue(map.get("TargetUsers")==null?"--":map.get("TargetUsers")+"");
						HSSFCell cell4 = row.createCell(3);
						cell4.setCellValue("--"+"");
						HSSFCell cell5 = row.createCell(4);
						cell5.setCellValue(map.get("PushUsers")+"");
						HSSFCell cell6 = row.createCell(5);
						cell6.setCellValue(map.get("PushNum")+"");
						HSSFCell cell7 = row.createCell(6);
						cell7.setCellValue(map.get("ReceiveUsers")+"");
						HSSFCell cell8 = row.createCell(7);
						cell8.setCellValue(map.get("ReceiveNum")+"");
						HSSFCell cell9 = row.createCell(8);
						cell9.setCellValue(map.get("ShowUsers")+"");
						HSSFCell cell10 = row.createCell(9);
						cell10.setCellValue(map.get("ShowNum")+"");
						HSSFCell cell11 = row.createCell(10);
						cell11.setCellValue(map.get("ClickUsers")+"");
						HSSFCell cell12 = row.createCell(11);
						cell12.setCellValue(map.get("ClickNum")+"");
						HSSFCell cell13 = row.createCell(12);
						cell13.setCellValue(map.get("CloseUsers")+"");
						HSSFCell cell14 = row.createCell(13);
						cell14.setCellValue(map.get("CloseNum")+"");
						HSSFCell cell15 = row.createCell(14);
						String a1 = String.valueOf(map.get("ReceiveRatio")==null?"0":map.get("ReceiveRatio"));
						String b1 = String.valueOf(map.get("ShowRatio")==null?"0":map.get("ShowRatio"));
						String c1 = String.valueOf(map.get("ClickRatio")==null?"0":map.get("ClickRatio"));
						String d1 = String.valueOf(map.get("CloseRatio")==null?"0":map.get("CloseRatio"));
					/*float a=(float) map.get("ReceiveRatio")*100;
					float b=(float) map.get("ShowRatio")*100;
					float c=(float) map.get("ClickRatio")*100;
					float d=(float) map.get("CloseRatio")*100;*/

					/*BigDecimal   a1   =   new   BigDecimal(a);
					BigDecimal   b1   =   new   BigDecimal(b);
					BigDecimal   c1   =   new   BigDecimal(c);
					BigDecimal   d1   =   new   BigDecimal(d);*/
					/*float ReceiveRatio=a1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					float ShowRatio=b1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					float ClickRatio=c1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					float CloseRatio=d1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();*/
						cell15.setCellValue(a1+"%"+"");
						HSSFCell cell16 = row.createCell(15);
						cell16.setCellValue(b1+"%"+"");
						HSSFCell cell17 = row.createCell(16);
						cell17.setCellValue(c1+"%"+"");
						HSSFCell cell18 = row.createCell(17);
						cell18.setCellValue(d1+"%"+"");
					}
				}
				//5、输出
				workbook.write(outputStream);
				workbook.close();
			}
			logger.info("PolicyinfoController reportExcel method end");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * 
 * @return 展现用户数详情
 */
	@RequestMapping(value="showDetails")
	public String showDetails(){
		logger.info("PolicyinfoController showDetails method start");
		String backType=this.getRequest().getParameter("backType");
		String policyIds=this.getRequest().getParameter("policyIds")==null?"":this.getRequest().getParameter("policyIds");
		String policyId=this.getRequest().getParameter("policyId");
		this.getCondition().put("policyId",policyId);
		//推送时间
		String startTime=this.getRequest().getParameter("startTime");
		String endTime=this.getRequest().getParameter("endTime");
		//获取表名
		this.getCondition().put("startTime",startTime);
		this.getCondition().put("endTime",endTime);
		String[] time=startTime.split("-");
		String newtime=time[0]+time[1];
		String pushtableName="push_record_t_"+newtime;
		//表名
		this.getCondition().put("pushtableName", pushtableName);
		String pushUser=this.getRequest().getParameter("pushUser");
		if(pushUser!=null && pushUser!=""){
			this.getCondition().put("pushUser",pushUser);
		}
		try {
			Map<String, Object> result = policyinfoService.showDetails(this.getVisitId(),this.getCondition());
			result.put("policyId",policyId);
			result.put("startTime",startTime);
			result.put("endTime",endTime);
			if(pushUser==null){
				pushUser="";
			}
			result.put("pushUser",pushUser);
			result.put("backType",backType);
			result.put("policyIds",policyIds);
			this.getRequest().setAttribute("result", result);
			this.getModel().setDesc("方法调用成功");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		logger.info("PolicyinfoController showDetails method end");
		return "policyinfo/showUsers";
	}
	//导出
	@RequestMapping("detailsExcel")
	public void detailsExcel() throws ServiceException{
		try {
			logger.info("PolicyinfoController reportExcel method start");
			Map<String,Object> result =null;
			String policyId=this.getRequest().getParameter("policyId");
			this.getCondition().put("policyId",policyId);
			//推送时间
			String startTime=this.getRequest().getParameter("startTime");
			String endTime=this.getRequest().getParameter("endTime");
			//获取表名
			this.getCondition().put("startTime",startTime);
			this.getCondition().put("endTime",endTime);
			String[] time=startTime.split("-");
			String newtime=time[0]+time[1];
			String pushtableName="push_record_t_"+newtime;
			//表名
			this.getCondition().put("pushtableName", pushtableName);
			String pushUser=this.getRequest().getParameter("pushUser");
			if(pushUser!=null && pushUser!=""){
				this.getCondition().put("pushUser",pushUser);
			}
			int excel=Integer.parseInt(this.getRequest().getParameter("excel"));
			//导出用户
			if(excel==1){
				result = policyinfoService.detailsExcelUser(this.getVisitId(),this.getCondition());
				HttpServletResponse response = this.getResponse();
				response.setContentType("application/x-excel");
				response.setHeader("Content-Disposition", "attachment;filename=" + new String("展现用户列表.xls".getBytes(), "ISO-8859-1"));
				ServletOutputStream outputStream = response.getOutputStream();
				
				//1、创建工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();
				//1.1、创建合并单元格对象
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 1);//起始行号，结束行号，起始列号，结束列号
				
				//1.2、头标题样式
				HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
				
				//1.3、列标题样式
				HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
				
				//2、创建工作表
				HSSFSheet sheet = workbook.createSheet("展现用户列表");
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
				cell1.setCellValue("展现用户列表");
				
				//3.2、创建列标题行；并且设置列标题
				HSSFRow row2 = sheet.createRow(1);
				
				String[] titles = {"序号","账号"};
				for(int i = 0; i < titles.length; i++){
					HSSFCell cell2 = row2.createCell(i);
					//加载单元格样式
					cell2.setCellStyle(style2);
					cell2.setCellValue(titles[i]);
				}
				//4、操作单元格；将合同台账列表写入excel
				if(result != null){
						List list=(List)result.get("LIST");
						for(int j=0;j<list.size();j++){
							Map map=(Map)list.get(j);
							HSSFRow row = sheet.createRow(j+2);
							HSSFCell cell = row.createCell(0);
							cell.setCellValue(j+1);
							HSSFCell cell2 = row.createCell(1);
							cell2.setCellValue(map.get("PushUser")+"");
							
						}
				}
				//5、输出
				workbook.write(outputStream);
				workbook.close();
			}
			//导出明细
			if(excel==2){
				result = policyinfoService.detailsExcel(this.getVisitId(),this.getCondition());
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("展现用户明细列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);//起始行号，结束行号，起始列号，结束列号
			
			//1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			
			//1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("展现用户明细列表");
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
			cell1.setCellValue("展现用户明细列表");
			
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			
			String[] titles = {"序号","账号","展现时间","用户IP","用户UA"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			//4、操作单元格；将合同台账列表写入excel
			if(result != null){
					List list=(List)result.get("LIST");
					for(int j=0;j<list.size();j++){
						Map map=(Map)list.get(j);
						HSSFRow row = sheet.createRow(j+2);
						HSSFCell cell = row.createCell(0);
						cell.setCellValue(j+1);
						HSSFCell cell2 = row.createCell(1);
						cell2.setCellValue(map.get("PushUser")+"");
						HSSFCell cell3 = row.createCell(2);
						cell3.setCellValue(map.get("ShowTime")+"");
						HSSFCell cell4 = row.createCell(3);
						cell4.setCellValue(map.get("PushIp")+"");
						HSSFCell cell5 = row.createCell(4);
						cell5.setCellValue(map.get("PushUa")+"");
						
					}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
		}
			logger.info("PolicyinfoController reportExcel method end");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * 
 * @return 点击用户数详情
 */
	@RequestMapping(value="clickDetails")
	public String clickDetails(){
		logger.info("PolicyinfoController clickDetails method start");
		String backType=this.getRequest().getParameter("backType");
		String policyIds=this.getRequest().getParameter("policyIds")==null?"":this.getRequest().getParameter("policyIds");
		String policyId=this.getRequest().getParameter("policyId");
		this.getCondition().put("policyId",policyId);
		//推送时间
		String startTime=this.getRequest().getParameter("startTime");
		String endTime=this.getRequest().getParameter("endTime");
		//获取表名
		this.getCondition().put("startTime",startTime);
		this.getCondition().put("endTime",endTime);
		String[] time=startTime.split("-");
		String newtime=time[0]+time[1];
		String pushtableName="push_record_t_"+newtime;
		String clicktableName="click_record_t_"+newtime;
		//表名
		this.getCondition().put("pushtableName", pushtableName);
		this.getCondition().put("clicktableName", clicktableName);
		String pushUser=this.getRequest().getParameter("pushUser");
		if(pushUser!=null && pushUser!=""){
			this.getCondition().put("pushUser",pushUser);
		}
		try {
			Map<String, Object> result = policyinfoService.clickDetails(this.getVisitId(),this.getCondition());
			result.put("policyId",policyId);
			result.put("startTime",startTime);
			result.put("endTime",endTime);
			if(pushUser==null){
				pushUser="";
			}
			result.put("pushUser",pushUser);
			result.put("backType",backType);
			result.put("policyIds",policyIds);
			this.getRequest().setAttribute("result", result);
			this.getModel().setDesc("方法调用成功");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		logger.info("PolicyinfoController clickDetails method end");
		return "policyinfo/clickUsers";
	}
	//导出
	@RequestMapping("clickExcel")
	public void clickExcel() throws ServiceException{
		try {
			logger.info("PolicyinfoController reportExcel method start");
			Map<String,Object> result =null;
			String policyId=this.getRequest().getParameter("policyId");
			this.getCondition().put("policyId",policyId);
			//推送时间
			String startTime=this.getRequest().getParameter("startTime");
			String endTime=this.getRequest().getParameter("endTime");
			//获取表名
			this.getCondition().put("startTime",startTime);
			this.getCondition().put("endTime",endTime);
			String[] time=startTime.split("-");
			String newtime=time[0]+time[1];
			String pushtableName="push_record_t_"+newtime;
			String clicktableName="click_record_t_"+newtime;
			//表名
			this.getCondition().put("pushtableName", pushtableName);
			this.getCondition().put("clicktableName", clicktableName);
			String pushUser=this.getRequest().getParameter("pushUser");
			if(pushUser!=null && pushUser!=""){
				this.getCondition().put("pushUser",pushUser);
			}
			int excel=Integer.parseInt(this.getRequest().getParameter("excel"));
			//导出用户
			if(excel==1){
				result = policyinfoService.clickExcelUser(this.getVisitId(),this.getCondition());
				HttpServletResponse response = this.getResponse();
				response.setContentType("application/x-excel");
				response.setHeader("Content-Disposition", "attachment;filename=" + new String("点击用户列表.xls".getBytes(), "ISO-8859-1"));
				ServletOutputStream outputStream = response.getOutputStream();
				
				//1、创建工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();
				//1.1、创建合并单元格对象
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 1);//起始行号，结束行号，起始列号，结束列号
				
				//1.2、头标题样式
				HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
				
				//1.3、列标题样式
				HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
				
				//2、创建工作表
				HSSFSheet sheet = workbook.createSheet("点击用户列表");
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
				cell1.setCellValue("点击用户列表");
				
				//3.2、创建列标题行；并且设置列标题
				HSSFRow row2 = sheet.createRow(1);
				
				String[] titles = {"序号","账号"};
				for(int i = 0; i < titles.length; i++){
					HSSFCell cell2 = row2.createCell(i);
					//加载单元格样式
					cell2.setCellStyle(style2);
					cell2.setCellValue(titles[i]);
				}
				//4、操作单元格；将合同台账列表写入excel
				if(result != null){
						//去重
						List list=(List)result.get("LIST");
						for(int j=0;j<list.size();j++){
							Map map=(Map)list.get(j);
							HSSFRow row = sheet.createRow(j+2);
							HSSFCell cell = row.createCell(0);
							cell.setCellValue(j+1);
							HSSFCell cell2 = row.createCell(1);
							cell2.setCellValue(map.get("PushUser")+"");
							
						}
				}
				//5、输出
				workbook.write(outputStream);
				workbook.close();
			}
			//导出明细
			if(excel==2){
				result = policyinfoService.clickExcel(this.getVisitId(),this.getCondition());
				HttpServletResponse response = this.getResponse();
				response.setContentType("application/x-excel");
				response.setHeader("Content-Disposition", "attachment;filename=" + new String("点击用户明细列表.xls".getBytes(), "ISO-8859-1"));
				ServletOutputStream outputStream = response.getOutputStream();

				//1、创建工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();
				//1.1、创建合并单元格对象
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);//起始行号，结束行号，起始列号，结束列号

				//1.2、头标题样式
				HSSFCellStyle style1 = createCellStyle(workbook, (short)16);

				//1.3、列标题样式
				HSSFCellStyle style2 = createCellStyle(workbook, (short)13);

				//2、创建工作表
				HSSFSheet sheet = workbook.createSheet("点击用户明细列表");
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
				cell1.setCellValue("点击用户明细列表");

				//3.2、创建列标题行；并且设置列标题
				HSSFRow row2 = sheet.createRow(1);
				String[] titles = {"序号","账号","点击时间","点击URL","点击页面"};
				for(int i = 0; i < titles.length; i++){
					HSSFCell cell2 = row2.createCell(i);
					//加载单元格样式
					cell2.setCellStyle(style2);
					cell2.setCellValue(titles[i]);
				}
				//4、操作单元格；将合同台账列表写入excel
				if(result != null){
					List list=(List)result.get("LIST");
					for(int j=0;j<list.size();j++){
						Map map=(Map)list.get(j);
						HSSFRow row = sheet.createRow(j+2);
						HSSFCell cell = row.createCell(0);
						cell.setCellValue(j+1);
						HSSFCell cell2 = row.createCell(1);
						cell2.setCellValue(map.get("PushUser")+"");
						HSSFCell cell3 = row.createCell(2);
						cell3.setCellValue(map.get("ClickTime")+"");
						HSSFCell cell4 = row.createCell(3);
						cell4.setCellValue(map.get("ClickUrl")+"");
						HSSFCell cell5 = row.createCell(4);
						cell5.setCellValue(map.get("ClickName")+"");
					}
				}
				//5、输出
				workbook.write(outputStream);
				workbook.close();
			}
			logger.info("PolicyinfoController reportExcel method end");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * 
 * @return 关闭用户数详情
 */
	@RequestMapping(value="closeDetails")
	public String closeDetails(){
		logger.info("PolicyinfoController closeDetails method start");
		String backType=this.getRequest().getParameter("backType");
		String policyIds=this.getRequest().getParameter("policyIds")==null?"":this.getRequest().getParameter("policyIds");
		String policyId=this.getRequest().getParameter("policyId");
		this.getCondition().put("policyId",policyId);
		//推送时间
		String startTime=this.getRequest().getParameter("startTime");
		String endTime=this.getRequest().getParameter("endTime");
		//获取表名
		this.getCondition().put("startTime",startTime);
		this.getCondition().put("endTime",endTime);
		String pushUser=this.getRequest().getParameter("pushUser");
		if(pushUser!=null && pushUser!=""){
			this.getCondition().put("pushUser",pushUser);
		}
		try {
			Map<String, Object> result = policyinfoService.closeDetails(this.getVisitId(),this.getCondition());
			result.put("policyId",policyId);
			result.put("startTime",startTime);
			result.put("endTime",endTime);
			if(pushUser==null){
				pushUser="";
			}
			result.put("pushUser",pushUser);
			result.put("backType",backType);
			result.put("policyIds",policyIds);
			this.getRequest().setAttribute("result", result);
			this.getModel().setDesc("方法调用成功");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		logger.info("PolicyinfoController closeDetails method end");
		return "policyinfo/closeUsers";
	}
	//导出
	@RequestMapping("closeExcel")
	public void closeExcel() throws ServiceException{
		logger.info("PolicyinfoController reportExcel method start");
		try {
			Map<String,Object> result =null;
			String policyId=this.getRequest().getParameter("policyId");
			this.getCondition().put("policyId",policyId);
			//推送时间
			String startTime=this.getRequest().getParameter("startTime");
			String endTime=this.getRequest().getParameter("endTime");
			//获取表名
			this.getCondition().put("startTime",startTime);
			this.getCondition().put("endTime",endTime);
			String pushUser=this.getRequest().getParameter("pushUser");
			if(pushUser!=null && pushUser!=""){
				this.getCondition().put("pushUser",pushUser);
			}
			int excel=Integer.parseInt(this.getRequest().getParameter("excel"));
			//导出用户
			if(excel==1){
				result = policyinfoService.closeExcelUser(this.getVisitId(),this.getCondition());
				HttpServletResponse response = this.getResponse();
				response.setContentType("application/x-excel");
				response.setHeader("Content-Disposition", "attachment;filename=" + new String("关闭用户列表.xls".getBytes(), "ISO-8859-1"));
				ServletOutputStream outputStream = response.getOutputStream();
				
				//1、创建工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();
				//1.1、创建合并单元格对象
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 1);//起始行号，结束行号，起始列号，结束列号
				
				//1.2、头标题样式
				HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
				
				//1.3、列标题样式
				HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
				
				//2、创建工作表
				HSSFSheet sheet = workbook.createSheet("关闭用户列表");
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
				cell1.setCellValue("关闭用户列表");
				
				//3.2、创建列标题行；并且设置列标题
				HSSFRow row2 = sheet.createRow(1);
				
				String[] titles = {"序号","账号"};
				for(int i = 0; i < titles.length; i++){
					HSSFCell cell2 = row2.createCell(i);
					//加载单元格样式
					cell2.setCellStyle(style2);
					cell2.setCellValue(titles[i]);
				}
				//4、操作单元格；将合同台账列表写入excel
				if(result != null){
						//去重
						List list=(List)result.get("LIST");
						for(int j=0;j<list.size();j++){
							Map map=(Map)list.get(j);
							HSSFRow row = sheet.createRow(j+2);
							HSSFCell cell = row.createCell(0);
							cell.setCellValue(j+1);
							HSSFCell cell2 = row.createCell(1);
							cell2.setCellValue(map.get("FilterUser")+"");
						}
				}
				//5、输出
				workbook.write(outputStream);
				workbook.close();
			}
			//导出明细
			if(excel==2){
				result = policyinfoService.closeExcel(this.getVisitId(),this.getCondition());
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("关闭用户明细列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 3);//起始行号，结束行号，起始列号，结束列号
			
			//1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			
			//1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("关闭用户明细列表");
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
			cell1.setCellValue("关闭用户明细列表");
			
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			
			String[] titles = {"序号","账号","关闭时间","关闭周期"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			//4、操作单元格；将合同台账列表写入excel
			if(result != null){
					List list=(List)result.get("LIST");
					for(int j=0;j<list.size();j++){
						Map map=(Map)list.get(j);
						HSSFRow row = sheet.createRow(j+2);
						HSSFCell cell = row.createCell(0);
						cell.setCellValue(j+1);
						HSSFCell cell2 = row.createCell(1);
						cell2.setCellValue(map.get("FilterUser")+"");
						HSSFCell cell3 = row.createCell(2);
						cell3.setCellValue(map.get("EffectTime")+"");
						HSSFCell cell4 = row.createCell(3);
						cell4.setCellValue(map.get("UserStatus")+"");
					}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
		}
			logger.info("PolicyinfoController reportExcel method end");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 * @return 查看详情（报表）
	 */
	@RequestMapping(value="reportDetails")
	public String reportDetails(){
		logger.info("PolicyinfoController reportDetails method start");
		String temp=this.getRequest().getParameter("temp");
		this.getRequest().setAttribute("temp", temp);
		String PolicyName=this.getRequest().getParameter("PolicyName");
		try {
			if(StringUtils.isNotBlank(PolicyName)){
				PolicyName=new String(PolicyName.getBytes("ISO-8859-1"), "utf-8");
				this.getRequest().setAttribute("PolicyName", PolicyName);
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time=df.format(date);
		try {
			//String ReportId = this.getRequest().getParameter("ReportId");
			String PolicyId=this.getRequest().getParameter("PolicyId");
			String ReportDate=this.getRequest().getParameter("ReportDate");
			//时间为空时，赋予当前时间
			if(ReportDate==null){
				ReportDate=time;
			}
			this.getRequest().setAttribute("PolicyId", PolicyId);
			this.getRequest().setAttribute("ReportDate", ReportDate);
			//this.getRequest().setAttribute("ReportId", ReportId);
		}catch(Exception e){
			
		}finally{
			
		}
		logger.info("PolicyinfoController reportDetails method end");
		return "policyinfo/policyReport";
	}
/**
 * 	
 * @return 查询左侧导航栏策略名称
 */
	@ResponseBody
	@RequestMapping(value="nameList.json")
	public ResultModel nameList(){
		logger.info("PolicyinfoController nameList method start");
		String time=this.getRequest().getParameter("time");
		this.getCondition().put("create_time", time);
		String PolicyId=this.getRequest().getParameter("PolicyId");
		if(PolicyId!=null){
			this.getCondition().put("PolicyId", PolicyId);
		}
		Map<String, Object> result=null;
		try{
			result = policyinfoService.nameListBak(this.getVisitId(),this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setDesc("方法调用成功");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("PolicyinfoController nameList method end");
		return this.getModel();
	}
	
	/**
	 *
	 * @return 获取对应推送策略详情(报表)
	 */
	@ResponseBody
	@RequestMapping(value="singlePolicyShow.json")
	public ResultModel singlePolicyShow(){
		logger.info("PolicyinfoController singlePolicyShow method end");
		Map condition = new HashMap<String,Object>();
		int PolicyId=Integer.parseInt(this.getRequest().getParameter("PolicyId"));
		String ReportDate=this.getRequest().getParameter("ReportDate");
		condition.put("Policy_Id", PolicyId);
		condition.put("Report_Date", ReportDate);
		this.getCondition().put("condition", condition);
		Map<String, Object> result=null;
		try{
			result = policyinfoService.singlePolicyShow(this.getVisitId(),this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setDesc("succeed");
		}catch(ServiceException e){
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("PolicyinfoController singlePolicyShow method end");
		return this.getModel();
	}

	@ResponseBody
	@RequestMapping(value="reportTotalData.json")
	public void reportTotalData(){
		logger.info(" reportTotalData method start");
		String startTime=this.getRequest().getParameter("startTime");
		String endTime=this.getRequest().getParameter("endTime");
		String policyId=this.getRequest().getParameter("policyId");
		Map<String, Object> result=null;
		if(startTime.equals(endTime)){
			this.getCondition().put("create_time",startTime);
			this.getCondition().put("PolicyId", policyId);
			try{
				result = policyinfoService.nameListBak(this.getVisitId(),this.getCondition());
				this.getModel().setResult(result);
				this.getModel().setDesc("方法调用成功");
			} catch (ServiceException e) {
				this.getModel().setCode(ResultCodeConstants.ERROR);
				this.getModel().setDesc("abnormal operation");
			}
		}else{
			this.getCondition().clear();
			this.getCondition().put("startTime",startTime);
			this.getCondition().put("endTime",endTime);
			this.getCondition().put("policyId",policyId);
			try {
				result=policyinfoService.policyEveryDayData(this.getCondition());
				this.getModel().setResult(result);
				this.getModel().setDesc("succeed");
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		try {
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("策略推送详细数据.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();

			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 17);//起始行号，结束行号，起始列号，结束列号

			//1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);

			//1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);

			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("策略推送详细数据");
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
			cell1.setCellValue("策略推送详细数据");

			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);

			String[] titles = {"策略名称","推送时间","目标用户","目标上网用户数","推送用户数","推送次数","到达用户数","到达次数","展现用户数","展现次数","点击用户数","点击次数","关闭用户数","关闭次数","到达率","展现率","点击率","关闭率"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			//4、操作单元格；将合同台账列表写入excel
			if(result != null){
				List list=(List)result.get("LIST");
				for(int j=0;j<list.size();j++){
					Map map=(Map)list.get(j);
					HSSFRow row = sheet.createRow(j+2);
					HSSFCell cell = row.createCell(0);
					cell.setCellValue(map.get("PolicyName")+"");
					HSSFCell cell2 = row.createCell(1);
					cell2.setCellValue(map.get("ReportDate")+"");
					HSSFCell cell3 = row.createCell(2);
					cell3.setCellValue(map.get("TargetUsers")+"");
					HSSFCell cell4 = row.createCell(3);
					cell4.setCellValue("--"+"");
					HSSFCell cell5 = row.createCell(4);
					cell5.setCellValue(map.get("PushUsers")+"");
					HSSFCell cell6 = row.createCell(5);
					cell6.setCellValue(map.get("PushNum")+"");
					HSSFCell cell7 = row.createCell(6);
					cell7.setCellValue(map.get("ReceiveUsers")+"");
					HSSFCell cell8 = row.createCell(7);
					cell8.setCellValue(map.get("ReceiveNum")+"");
					HSSFCell cell9 = row.createCell(8);
					cell9.setCellValue(map.get("ShowUsers")+"");
					HSSFCell cell10 = row.createCell(9);
					cell10.setCellValue(map.get("ShowNum")+"");
					HSSFCell cell11 = row.createCell(10);
					cell11.setCellValue(map.get("ClickUsers")+"");
					HSSFCell cell12 = row.createCell(11);
					cell12.setCellValue(map.get("ClickNum")+"");
					HSSFCell cell13 = row.createCell(12);
					cell13.setCellValue(map.get("CloseUsers")+"");
					HSSFCell cell14 = row.createCell(13);
					cell14.setCellValue(map.get("CloseNum")+"");
					HSSFCell cell15 = row.createCell(14);
					float a=(float) map.get("ReceiveRatio")*100;
					float b=(float) map.get("ShowRatio")*100;
					float c=(float) map.get("ClickRatio")*100;
					float d=(float) map.get("CloseRatio")*100;

					BigDecimal   a1   =   new   BigDecimal(a);
					BigDecimal   b1   =   new   BigDecimal(b);
					BigDecimal   c1   =   new   BigDecimal(c);
					BigDecimal   d1   =   new   BigDecimal(d);
					float ReceiveRatio=a1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					float ShowRatio=b1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					float ClickRatio=c1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					float CloseRatio=d1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
					cell15.setCellValue(ReceiveRatio+"%"+"");
					HSSFCell cell16 = row.createCell(15);
					cell16.setCellValue(ShowRatio+"%"+"");
					HSSFCell cell17 = row.createCell(16);
					cell17.setCellValue(ClickRatio+"%"+"");
					HSSFCell cell18 = row.createCell(17);
					cell18.setCellValue(CloseRatio+"%"+"");
				}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
			logger.info("PolicyinfoController reportExcel method end");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(" reportTotalData method end");
	}

/**
 * 	导出策略报表详细数据
 * @throws ServiceException
 */
	@RequestMapping(value="importTable.json")
	public void importTable() throws ServiceException{
		try {
			logger.info("PolicyinfoController reportExcel method start");
			Map<String,Object> result =null;
			
			String time=this.getRequest().getParameter("time");
			this.getCondition().put("create_time", time);
			
			String PolicyId=this.getRequest().getParameter("PolicyId");
			if(PolicyId!=null){
				this.getCondition().put("PolicyId", PolicyId);
			}
			result = policyinfoService.nameListBak(this.getVisitId(),this.getCondition());
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("策略推送详细数据.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 17);//起始行号，结束行号，起始列号，结束列号
			
			//1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			
			//1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("策略推送详细数据");
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
			cell1.setCellValue("策略推送详细数据");
			
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			
			String[] titles = {"策略名称","推送时间","目标用户","目标上网用户数","推送用户数","推送次数","到达用户数","到达次数","展现用户数","展现次数","点击用户数","点击次数","关闭用户数","关闭次数","到达率","展现率","点击率","关闭率"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			//4、操作单元格；将合同台账列表写入excel
			if(result != null){
					List list=(List)result.get("LIST");
					for(int j=0;j<list.size();j++){
						Map map=(Map)list.get(j);
						HSSFRow row = sheet.createRow(j+2);
						HSSFCell cell = row.createCell(0);
						cell.setCellValue(map.get("PolicyName")+"");
						HSSFCell cell2 = row.createCell(1);
						cell2.setCellValue(map.get("ReportDate")+"");
						HSSFCell cell3 = row.createCell(2);
						cell3.setCellValue(map.get("TargetUsers")+"");
						HSSFCell cell4 = row.createCell(3);
						cell4.setCellValue("--"+"");
						HSSFCell cell5 = row.createCell(4);
						cell5.setCellValue(map.get("PushUsers")+"");
						HSSFCell cell6 = row.createCell(5);
						cell6.setCellValue(map.get("PushNum")+"");
						HSSFCell cell7 = row.createCell(6);
						cell7.setCellValue(map.get("ReceiveUsers")+"");
						HSSFCell cell8 = row.createCell(7);
						cell8.setCellValue(map.get("ReceiveNum")+"");
						HSSFCell cell9 = row.createCell(8);
						cell9.setCellValue(map.get("ShowUsers")+"");
						HSSFCell cell10 = row.createCell(9);
						cell10.setCellValue(map.get("ShowNum")+"");
						HSSFCell cell11 = row.createCell(10);
						cell11.setCellValue(map.get("ClickUsers")+"");
						HSSFCell cell12 = row.createCell(11);
						cell12.setCellValue(map.get("ClickNum")+"");
						HSSFCell cell13 = row.createCell(12);
						cell13.setCellValue(map.get("CloseUsers")+"");
						HSSFCell cell14 = row.createCell(13);
						cell14.setCellValue(map.get("CloseNum")+"");
						HSSFCell cell15 = row.createCell(14);
						float a=(float) map.get("ReceiveRatio")*100;
						float b=(float) map.get("ShowRatio")*100;
						float c=(float) map.get("ClickRatio")*100;
						float d=(float) map.get("CloseRatio")*100;
						
						BigDecimal   a1   =   new   BigDecimal(a); 
						BigDecimal   b1   =   new   BigDecimal(b);
						BigDecimal   c1   =   new   BigDecimal(c);
						BigDecimal   d1   =   new   BigDecimal(d);
						float ReceiveRatio=a1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
						float ShowRatio=b1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
						float ClickRatio=c1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
						float CloseRatio=d1.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
						cell15.setCellValue(ReceiveRatio+"%"+"");
						HSSFCell cell16 = row.createCell(15);
						cell16.setCellValue(ShowRatio+"%"+"");
						HSSFCell cell17 = row.createCell(16);
						cell17.setCellValue(ClickRatio+"%"+"");
						HSSFCell cell18 = row.createCell(17);
						cell18.setCellValue(CloseRatio+"%"+"");
					}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
			logger.info("PolicyinfoController reportExcel method end");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
//===========================================================================================================================
//===========================================================================================================================
//===========================================================================================================================
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
