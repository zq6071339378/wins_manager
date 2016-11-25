/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2006-2016 DataComo Communications Technology INC.
 * 
 * This source file is a part of DSPV1.0 project. 
 * date: 2016-02-25
 *
 */
package com.datacomo.wins.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.PolicyGroupMngService;
import com.datacomo.wins.service.SysLogRecordService;
import com.datacomo.wins.util.DateUtil;
import com.datacomo.wins.util.MD5;


/**
 * @author gongkaihui
 *
 *	群组管理控制类
 */

@Controller
@RequestMapping(value="/group")//url映射方法前缀
public class PolicyGroupMngController extends BaseController{
	private static Logger logger = Logger.getLogger(PolicyGroupMngController.class.getName());
	@Autowired
	PolicyGroupMngService policyGroupMngService;
	@Autowired
	SysLogRecordService sysLogRecordService;
	@Value("#{configProperties['groupDir']}")
	private String groupDir;
	@Value("#{configProperties['uploadAddUserTxtDir']}")
	private String uploadAddUserTxtDir;
	@Value("#{configProperties['hostUri']}")
	private String hostUri;
	@ResponseBody
	@RequestMapping(value="list")
	public ResultModel list() {
		logger.info("PolicyGroupMngController list method start");
		try {
			
			Map<String,Object> result = policyGroupMngService.findGroupList(this.getVisitId(), 0, this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		
		logger.info("PolicyGroupMngController list method end");
		return this.getModel();
	}
	//解压读取文件，插入数据
	@ResponseBody
	@RequestMapping(value="insertData")
	public void insertData() {
		logger.info("PolicyGroupMngController add method start");
        	String fileTempPath = uploadAddUserTxtDir;
    		String TemePath = fileTempPath+"blank/";
    		File file = null;
    		file = new File(uploadAddUserTxtDir);
    		if(!file .exists()  && !file .isDirectory()){
    			file.mkdir();
    		}
    		file = new File(TemePath);
    		if(!file .exists()  && !file .isDirectory()){     
    			file .mkdir();    
    		}
    		FileInputStream fis = null;
    		ZipFile zin = null;
    		FileOutputStream fos = null;
    		OutputStream out = null;
    		InputStream is = null;
    		BufferedReader br = null;
    		FileInputStream fs =null;
    		file = new File(fileTempPath);
    		File[] bs = file.listFiles(); 
    		for (File b : bs) {
            	if(b.isFile() && b.getAbsolutePath().endsWith(".zip") ){
            		String t = b.getName();
            		file=new File(fileTempPath+t);
            		try {
            			fis = new FileInputStream(file);
      	              	fos = new FileOutputStream(TemePath+t);
            				
      	              	int line = fis.read();        
      	              	while(line != -1){
            	      	  	fos.write(line);
            	      	  	line=fis.read();
      	              	}
            			String ing = file.getName();
            			String ingName = ing.substring(0,ing.lastIndexOf("."));
            			String name = ing.substring(ing.indexOf("_")+1,ing.lastIndexOf("."));//1
            			String strName = ing.substring(0,ing.lastIndexOf("_"));//11
            			zin = new ZipFile(file);
            			Enumeration enu = zin.entries();
            			while (enu.hasMoreElements()) {
            				  ZipEntry entry = (ZipEntry) enu.nextElement();
            	              String path =fileTempPath+ ingName+".txt";
            	              file = new File(path);
            	              is = zin.getInputStream(entry);
            	              byte[] buf1 = new byte[1024];
            	              int len;
            	              if (file.exists()) {
            	                  file.delete();
            	              }
            	              file.getParentFile().mkdirs();
            	              file.createNewFile();
            	              out = new FileOutputStream(file);
            	              while ((len = is.read(buf1)) > 0) {
            	                  String buf = new String(buf1, 0, len);
            	                  out.write(buf1, 0, len);
            	              }
            			}
            			file = new File(fileTempPath+ingName+".txt");
        				fs = new FileInputStream(file);
        				br =new BufferedReader(new InputStreamReader(fs,"GBK"));
        		        String d = null;
        		        int count=0;
        		        List list = new ArrayList();
        		       
    		            Map<String,Object> condition = new HashMap<String, Object>();
    		            int g = Integer.parseInt(strName);
        		        while ((d = br.readLine()) !=null) {
        		        	Map<String, Object> map=new HashMap<String, Object>();
        		        	String str = d.substring(0,d.indexOf(","));
        		        	String str1 = d.substring(d.indexOf(",")+1);
        		        	String str2=str1.replaceAll(",", " ");
        		            Date date=new Date();
        		            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		            String date1=sf.format(date);
        		            
        		            map.put("Group_Id",g++);
            		        map.put("User_Id", str);
        		            map.put("Create_User", name);
        		            map.put("Create_Time", date1);
        		            map.put("Dynamic_Des", str2);
        		            list.add(map);
        		            condition.put("users",list);
        		            count++;
        		            if(count==5000){
        		            	 policyGroupMngService.insertData(condition);
        		            	 count=0;
        		            	 list.clear();
        		            }
        		        }
        		        if(count !=0){
        		        	policyGroupMngService.insertData(condition);
        		        }
            		} catch (Exception e){
            			e.printStackTrace();
            		} finally {
            			if(zin != null){
            				try {
            					zin.close();
            				} catch (IOException e) {
            					e.printStackTrace();
            				}
            			}
            			if(fis != null){
            				try {
    							fis.close();
    						} catch (IOException e) {
    							e.printStackTrace();
    						}
            			}
            			if(fos != null){
            				try {
    							fos.close();
    						} catch (IOException e) {
    							e.printStackTrace();
    						}
            			}
            			if(is != null){
            				try {
    							is.close();
    						} catch (IOException e) {
    							e.printStackTrace();
    						}
            			}
            			if(out != null){
            				try {
    							out.close();
    						} catch (IOException e) {
    							e.printStackTrace();
    						}
            			}
            			if(br != null){
            				try {
    							br.close();
    						} catch (IOException e) {
    							e.printStackTrace();
    						}
            			}
            			if(fs != null){
            				try {
            					fs.close();
            				} catch (IOException e) {
            					e.printStackTrace();
            				}
            			}
            		}
            	}
            }
            file = new File(fileTempPath);
    		File[] subs = file.listFiles(); 
            for (File sub : subs) {
            	if(sub.exists() && !sub.isDirectory()){
            		if(sub.delete()){
                		System.out.println(sub.getName()+"delete successful");
                	}else {
                		System.out.println(sub.getName()+"delete faily");
                	}
            	}
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            logger.info("PolicyGroupMngController add method end");
	}
	@ResponseBody
	@RequestMapping(value="delete")
	public ResultModel delete(int groupId) {
		logger.info("PolicyGroupMngController delete method start");
		String ip=this.getRequest().getRemoteAddr();
        int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try {
			policyGroupMngService.deleteById(groupId);
			policyGroupMngService.deleteGroupById(groupId);
			sysLogRecordService.insertLogInfo(userId, "delete", 1, "Deleted group successfully", ip, DateUtil.getDateString());
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
		} catch (ServiceException e) {
			try {
				sysLogRecordService.insertLogInfo(userId, "删除", 2, "Deleted group failed", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		logger.info("PolicyGroupMngController delete method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="addGroup")
	public ResultModel addGroup(@RequestParam(required=false) MultipartFile multiImport,String groupName,String userAccount,String path) {
		logger.info("PolicyGroupMngController addGroup method start");
		String ip=this.getRequest().getRemoteAddr();
		int userId=this.getVisitId();
		String createTime=DateUtil.getDateString();
		try {
			if(userAccount!=null){
				userAccount=userAccount.trim();
			}
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("groupName", groupName);
			map.put("groupType", 1);
			map.put("logoPath", path);
			map.put("createUser", this.getSession().getAttribute("userId"));
			map.put("createTime", createTime);
			int userNumber=0;
			map.put("groupUsers",0);
			policyGroupMngService.insertInfo(map);
			String groupId= String.valueOf(map.get("groupId"));
			this.getCondition().put("",groupId);
			List list =new ArrayList<String>();
			if(multiImport==null){
				if(userAccount!=""){
					userAccount=userAccount.replace("，", ",").replace(",", ",").replace(System.getProperty("line.separator"), ",");
					for(int i=0;i<userAccount.split(",").length;i++){
						if(!StringUtils.isNotBlank( userAccount.split(",")[i].trim())){
							continue;
						}
						Map<String,Object> condition = new HashMap<String,Object>();
						condition.put("Group_Id",groupId);
						condition.put("User_Id", userAccount.split(",")[i].trim());
						condition.put("Create_User", userId);
						condition.put("Create_Time",createTime);
						condition.put("Dynamic_Des", "");
						list.add(condition);
					}
				}
			}else{
				String extension = FilenameUtils.getExtension(multiImport.getOriginalFilename());
				if("txt".equalsIgnoreCase(extension)){
					InputStreamReader read=new InputStreamReader(multiImport.getInputStream());
					BufferedReader br=new BufferedReader(read);
					String line=null;
					while((line=br.readLine()) !=null){
						line=line.replace("，", ",").replace(",", ",");
						String str=line.replaceAll(" ","");
						String str1="";
						if(line.contains(",")){
							str = line.substring(0,line.indexOf(","));
							str1 = line.substring(line.indexOf(",")+1);
						}
						Map<String,Object> condition = new HashMap<String,Object>();
						condition.put("Group_Id", map.get("groupId"));
						condition.put("User_Id", str);
						condition.put("Create_User", userId);
						condition.put("Create_Time",createTime);
						condition.put("Dynamic_Des", str1);
						list.add(condition);
					}
				}else{
					this.getModel().setResult("-1");
				}
			}
			this.getCondition().put("LIST",list);
			this.getCondition().put("flag",2);
			policyGroupMngService.insertGroupMember(this.getCondition());
			this.getCondition().put("GroupId_Id",groupId);
			userNumber=policyGroupMngService.getGroupUsers(this.getCondition());
			map.put("groupUsers",userNumber);
			policyGroupMngService.updateGroupById(map);
			sysLogRecordService.insertLogInfo(userId, "增加", 1, "创建群组成功", ip, DateUtil.getDateString());
			this.getModel().setResult(map.get("groupId"));
			this.getModel().setVersion(map.get("groupUsers").toString());
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
		} catch (Exception e) {
			try {
				sysLogRecordService.insertLogInfo(userId, "增加", 2, "创建群组异常", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		logger.info("PolicyGroupMngController addGroup method end");
		return this.getModel();
	}
	
	@RequestMapping(value="uploadPic")
	@ResponseBody
	public ResultModel uploadPic(@RequestParam(required=false) MultipartFile pic,HttpServletRequest request) {
		logger.info("PolicyGroupMngController uploadPic method start");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			String format = sdf.format(new Date());
			Random random = new Random();
			for(int i=0;i<3;i++){
				format+=random.nextInt(10);
			}
	//		String realpath=this.getSession().getServletContext().getRealPath("/groupImgs/");
			File f=new File(groupDir);
			if(!f.exists()){
				f.mkdirs();
			}
			
			//扩展名
			String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
			String path=groupDir+"/"+format+"."+extension;
			//String url=realpath+path;
			String url=path;
			FileUtils.copyInputStreamToFile(pic.getInputStream(), new File(url));
			Map<String, String> map=new HashMap<String, String>();
			map.put("path",path);
			url=hostUri+path;
			map.put("url",url);
			this.getModel().setResult(map);
		}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		logger.info("PolicyGroupMngController uploadPic method end");
		return this.getModel();
	}
	
	@RequestMapping(value="downloadExcel")
	public void downloadPic() {
		logger.info("PolicyGroupMngController downloadExcel method start");
		try {
			this.getReponse().setHeader("Content-Disposition", "attachment;filename="+"1.xlsx");
			String path="F:\\公司资料\\toolbar\\导入群组.xlsx";
			InputStream in=new FileInputStream(path);
			OutputStream out=this.getReponse().getOutputStream();
			int len=-1;
			byte[] b=new byte[1024];
			while((len=in.read(b))!=-1){
				out.write(b, 0, len);
			}
			out.write("Download successfully".getBytes("UTF-8"));
			out.flush();
			in.close();
			out.close();
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
		} catch (Exception e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		logger.info("PolicyGroupMngController downloadExcel method end");
	}
	
	/*@RequestMapping(value="exportGroupMem")
	public void exportGroupMem() throws IOException{
		logger.info("PolicyGroupMngController exportGroupMem method start");
		try {
			this.getCondition().put("groupId", this.getRequest().getParameter("groupId"));
			Map<String,Object> result = policyGroupMngService.findGroupMemByGroupId(this.getVisitId(), this.getVisitId(), this.getCondition());
			
			this.getCondition().put("flag", 1);
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("账号列表.xls".getBytes(), "ISO-8859-1"));
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
			HSSFSheet sheet = workbook.createSheet("账号列表");
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
			
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			
			String[] titles = {"账号"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			//4、操作单元格；将合同台账列表写入excel
			if(result != null){
					List list=(List)result.get("memberLists");
					for(int j=0;j<list.size();j++){
						Map map=(Map)list.get(j);
						HSSFRow row = sheet.createRow(j+2);
						HSSFCell cell = row.createCell(0);
						cell.setCellValue(map.get("userId")+"");
					}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
			
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		
		logger.info("PolicyGroupMngController exportGroupMem method end");
	}*/
	@RequestMapping(value="exportGroupMem")
	public void exportGroupMem() throws IOException{
		logger.info("PolicyGroupMngController exportGroupMem method start");
		String ip=this.getRequest().getRemoteAddr();
	    int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		try {
			this.getCondition().put("groupId", this.getRequest().getParameter("groupId"));
			Map<String,Object> result = policyGroupMngService.findGroupMemByGroupId(this.getVisitId(), this.getVisitId(), this.getCondition());
			
			this.getCondition().put("flag", 1);
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("Group_account.txt".getBytes(), "ISO-8859-1"));
			OutputStream os=new BufferedOutputStream(response.getOutputStream());
			StringBuffer sb=new StringBuffer();
		
			if(result != null){
				List list=(List)result.get("memberLists");
				for(int j=0;j<list.size();j++){
					Map map=(Map)list.get(j);
					if(map.get("dynamicDes")!=null){
						sb.append(map.get("userId")+","+map.get("dynamicDes")+"\r\n");
					}else{
						sb.append(map.get("userId")+"\r\n");
					}
				}
			}
			os.write(sb.toString().getBytes());
			os.flush();
			os.close();
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
			sysLogRecordService.insertLogInfo(userId, "Export report", 1, "Export report successfully", ip, DateUtil.getDateString());
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
			try {
				sysLogRecordService.insertLogInfo(userId, "Export report", 2, "Export report failed", ip, DateUtil.getDateString());
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		logger.info("PolicyGroupMngController exportGroupMem method end");
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
