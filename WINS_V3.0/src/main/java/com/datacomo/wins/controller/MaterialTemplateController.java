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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.datacomo.wins.service.SysRoleInfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.bean.ResultModel;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.MaterialService;
import com.datacomo.wins.util.CompressBook;
import com.datacomo.wins.util.MartixToImageUtil;
/**
 * @author zhaizhanpo
 *
 *	素材模板
 */

@Controller
@RequestMapping(value="/material/template")//url映射方法前缀
public class MaterialTemplateController extends BaseController{
	private static Logger logger = Logger.getLogger(MaterialTemplateController.class.getName());
	@Autowired
	MaterialService materialService;
	@Autowired
	private SysRoleInfoService sysRoleInfoService;
	@Value("#{configProperties['material_upload_path']}")
	private String material_upload_path; 
	@Value("#{configProperties['material_url']}")
	private String material_url; 
	
	@ResponseBody
	@RequestMapping(value="list")
	public ResultModel list() {
		logger.info(MaterialTemplateController.class.getName()+" list method start");
		try {
			Map<String,Object> result = materialService.findMaterialTemplateByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		
		logger.info(MaterialTemplateController.class.getName()+" list method end");
		return this.getModel();
	}
	
	
	@ResponseBody
	@RequestMapping(value="create")
	public ResultModel create() {///wins/matertail/template/create
		logger.info(MaterialTemplateController.class.getName()+" create method start");
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			
			String templateName=this.getRequest().getParameter("templateName");
			if(StringUtils.isNotEmpty(templateName)){
				params.put("template_name", templateName);
			}else{
				this.getModel().setCode(ResultCodeConstants.ERROR);
				return this.getModel();
			}
			String templateUrl=this.getRequest().getParameter("templateUrl");
			if(StringUtils.isNotEmpty(templateUrl)){
				params.put("template_url", templateUrl);
			}
			String templateImage=this.getRequest().getParameter("templateImage");
			if(StringUtils.isNotEmpty(templateImage)){
				params.put("template_image", templateImage);
			}
			
			String templateType=this.getRequest().getParameter("templateType");
			if(StringUtils.isNotEmpty(templateType)){
				params.put("template_type", templateType);
			}else{
				params.put("template_type", 1);
			}
			String terminalType=this.getRequest().getParameter("terminalType");
			params.put("terminal_type", terminalType);
			String showType=this.getRequest().getParameter("showType");
			params.put("show_type", showType);
			String templateRemark=this.getRequest().getParameter("templateRemark");
			if(StringUtils.isNotEmpty(templateRemark)){
				params.put("template_remark", templateRemark);
			}
			String martixPath=this.getRequest().getParameter("martixPath");
			if(StringUtils.isNotEmpty(martixPath)){
				params.put("martix_path", martixPath);
			}
			params.put("Template_State", 1);
			
			int createUser=this.getVisitId();
			params.put("create_user", createUser);
			this.getCondition().put("condition", params);
			int result = this.materialService.insertMaterialTemplate(this.getVisitId(), this.getCondition());
			if(result>0){
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
			}else{
				this.getModel().setCode(ResultCodeConstants.ERROR);
			}
		} catch (Exception e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info(MaterialTemplateController.class.getName()+" create method end");
		return this.getModel();
	}

	
	@ResponseBody
	@RequestMapping(value="delete")
	public ResultModel delete() {
		logger.info(MaterialTemplateController.class.getName()+" delete method start");
		try {
			String templateId=this.getRequest().getParameter("templateId");
			if(StringUtils.isNotEmpty(templateId)){
				int flag = materialService.deleteMaterialTemplateById(this.getVisitId(), Integer.parseInt(templateId.toString()));
				if(flag>0){
					this.getModel().setCode(ResultCodeConstants.SUCCESS);
					this.getModel().setDesc("succeed");
				}else{
					this.getModel().setCode(ResultCodeConstants.ERROR);
				}
				this.getModel().setResult(flag);
			}
		} catch (Exception e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operations");
		}
		
		logger.info(MaterialTemplateController.class.getName()+" delete method end");
		return this.getModel();
	}
	
	@RequestMapping(value="listView")
	public String listView() {
		logger.info(MaterialTemplateController.class.getName()+" list method start");
		Map<String, Object> result =null;
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString());
		int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
		this.getCondition().put("showStatus",showStatus);
		this.getCondition().put("userId",userId);
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			String templateName=this.getRequest().getParameter("templateName");
			try {
				if(StringUtils.isNotBlank(templateName)){
					templateName=new String(templateName.getBytes("ISO-8859-1"), "utf-8");
					this.getCondition().put("templateName",templateName);
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String tType=this.getRequest().getParameter("tType");
			if(StringUtils.isNotBlank(tType)){
				this.getCondition().put("tType", tType);
			}
			
			result = materialService.findMaterialTemplateByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
		} catch (ServiceException e) {
			e.printStackTrace();
			result = null;
		}
		this.getRequest().setAttribute("result", result);
		this.getRequest().setAttribute("MenuRelation",MenuRelation);
		this.getRequest().setAttribute("condition", this.getCondition());
		logger.info(MaterialTemplateController.class.getName()+" list method end");
		return "material/template";
	}
	
	@ResponseBody
	@RequestMapping(value="upload")
	private ResultModel upload(@RequestParam MultipartFile upload){//http://localhost:8080/wins/material/template/upload
		//创建目录
		File upload_path=new File(this.material_upload_path);
		if(!upload_path.exists()){
			upload_path.mkdirs();
		}
		
		
		InputStream is= null;
		FileOutputStream fos = null;
		try {
			if(upload!=null){
				is = upload.getInputStream();
				
				if(is!=null){
					//获取文件名字
					String orf = upload.getOriginalFilename();
					//判断文件格式是否正确
					if(StringUtils.isNotEmpty(orf) && !orf.endsWith(".zip")){
						this.getModel().setCode(ResultCodeConstants.ERROR);
						this.getModel().setResult(0);
						return this.getModel();
					}
					//解压缩、分析文件目录名称
					String randomName = ""+new Date().getTime()+new Random().nextInt(1000);
					//生成新的文件目录
					String zipFileName = this.material_upload_path+"/"+randomName+".zip";
					logger.info(zipFileName);
					//写入本地文件
					fos = new FileOutputStream(zipFileName);//需要解压缩脚本进行解压缩和文件转移
					byte[] buff=new byte[2048];
					int len=0;
					while ((len=is.read(buff))>0) {
						fos.write(buff, 0, len);
					}
					fos.flush();
					//解压缩
					String filePath=this.material_upload_path;
					CompressBook.unZip(zipFileName, filePath);
					//对解压后文件进行重命名
					
					ZipFile zf = new ZipFile(zipFileName);
					String newName=null;
				    for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
				      newName = ((ZipEntry) entries.nextElement()).getName();
				    }
				    String a=newName.substring(0,newName.indexOf("/"));
				    
				    File lastfile=new File(this.material_upload_path+a);
				    File lastName=new File(this.material_upload_path+randomName);
				    lastfile.renameTo(lastName);
					
					//生成图片访问二维码内容
					String content = this.material_url+randomName+"/cover.png";
					//二维码图片地址
					String martixPath = this.material_upload_path+randomName+"/"+randomName+".jpg";
					MartixToImageUtil.writeToFile(content, "jpg", martixPath, 100, 100);
					this.getModel().setCode(ResultCodeConstants.SUCCESS);
					Map<String,String> resultMap=new HashMap<String,String>();
					resultMap.put("templateImage", content);
					resultMap.put("templateUrl", this.material_url+randomName);
					resultMap.put("martixPath", this.material_url+randomName+"/"+randomName+".jpg");
					this.getModel().setResult(resultMap);
				}else{
					this.getModel().setCode(ResultCodeConstants.ERROR);
					this.getModel().setResult(0);
				}
			}
		} catch (Exception e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setResult(0);
		}finally{
			if(is!=null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return this.getModel();
	}
	
	
	@RequestMapping(value="addListView")
	public String addListView() {
		logger.info(MaterialTemplateController.class.getName()+" list method start");
		Map<String, Object> result =null;

		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		try {
			String tType=this.getRequest().getParameter("tType");
			if(StringUtils.isNotBlank(tType)){
				this.getCondition().put("tType", tType);
			}
			result = materialService.findMaterialTemplateByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
		} catch (ServiceException e) {
			e.printStackTrace();
			result = null;
		}
		this.getRequest().setAttribute("result", result);
		this.getRequest().setAttribute("MenuRelation",MenuRelation);
		this.getRequest().setAttribute("condition", this.getCondition());
		logger.info(MaterialTemplateController.class.getName()+" list method end");
		return "material/createPage";
	}
	
}
