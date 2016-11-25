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

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import com.datacomo.wins.push.bean.HtmlADModelInfo;
import com.datacomo.wins.service.SysRoleInfoService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.datacomo.wins.util.HtmlTemplateParseUtil;
import com.datacomo.wins.util.zipNewName;

/**
 * @author zhaizhanpo
 *
 *	素材页面
 */

@Controller
@RequestMapping(value="/material/page")//url映射方法前缀
public class MaterialPageController extends BaseController{
	private static Logger logger = Logger.getLogger(MaterialPageController.class.getName());
	@Autowired
	MaterialService materialService;
	@Autowired
	private SysRoleInfoService sysRoleInfoService;
	@Value("${head_uri}}")
	private String jdbc;
	@Value("#{configProperties['material_copy_path']}")
	private String material_copy_path;
	@Value("#{configProperties['material_upload_path']}")
	private String material_upload_path;
	@Value("#{configProperties['material_copy_url']}")
	private String material_copy_url;
	@Value("#{configProperties['zipFile']}")
	private String zipFile;
	@Value("#{configProperties['tb_path']}")
	private String tb_path;
	
	public final static String HTML_DYNAMIC_TAGATTRIBUTE = "adModel";
	static final int BUFFER = 8192;
	
	@ResponseBody
	@RequestMapping(value="list") 
	public ResultModel list() {
		logger.info(MaterialPageController.class.getName()+" list method start");
		try {
			logger.info("jdbc:"+jdbc);
			Map<String,Object> result = materialService.findMaterialTemplateByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getModel().setResult(result);
			this.getModel().setCode(ResultCodeConstants.SUCCESS);
			this.getModel().setDesc("方法调用成功");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		
		logger.info("TestController list method end");
		return this.getModel();
	}
	
	@ResponseBody
	@RequestMapping(value="delete")
	public ResultModel delete() {
		logger.info(MaterialPageController.class.getName()+"  delete method start");
		try {
			String pageId=this.getRequest().getParameter("pageId");
			if(StringUtils.isNotEmpty(pageId)){
				int flag = materialService.deleteMaterialPageById(this.getVisitId(), Integer.parseInt(pageId.toString()));
				if(flag>0){
					this.getModel().setCode(ResultCodeConstants.SUCCESS);
					this.getModel().setDesc("方法调用成功");
				}else{
					this.getModel().setCode(ResultCodeConstants.ERROR);
				}
				this.getModel().setResult(flag);
			}
		} catch (Exception e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		
		logger.info(MaterialPageController.class.getName()+"  delete method end");
		return this.getModel();
	}
	
	
	@RequestMapping(value="listView")
	public String listView() {
		logger.info(this.getClass().getName()+" listView  method start");
		
		int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString()); 
		int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
		this.getCondition().put("showStatus",showStatus);
		this.getCondition().put("userId",userId); 
		String roleId = this.getRequest().getSession().getAttribute("roleId").toString();
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("roleId",roleId);
		Map<String,Object> MenuRelation = null;
		
		
		try {
			String pageName=this.getRequest().getParameter("pageName");
			String tType=this.getRequest().getParameter("tType");
			try {
				if(StringUtils.isNotBlank(pageName)){
					pageName=new String(pageName.getBytes("ISO-8859-1"), "utf-8");
					this.getCondition().put("pageName",pageName);
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(StringUtils.isNotBlank(tType)){
				this.getCondition().put("tType", tType);
			}
			Map<String,Object> result = materialService.findMaterialPageByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("result", result);
			this.getRequest().setAttribute("condition", this.getCondition());
			MenuRelation = sysRoleInfoService.getRoleInfo(this.getVisitId(),condition);
			this.getRequest().setAttribute("MenuRelation",MenuRelation);
		} catch (Exception e) {
			this.getRequest().setAttribute("result", null);
		}
		
		logger.info(this.getClass().getName()+" listView  method end");
		return "material/page";
	}
	@RequestMapping(value="createPage")
	public String createPage() {
		logger.info(this.getClass().getName()+" createPage  method start");
		try {
//			String pageName=this.getRequest().getParameter("pageName");
			int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString()); 
			int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus",showStatus);
			this.getCondition().put("userId",userId);
			String tType=this.getRequest().getParameter("tType")==null?"1":this.getRequest().getParameter("tType");
			if(StringUtils.isNotBlank(tType)){
				this.getCondition().put("tType", tType);
			}
//			if(StringUtils.isNotBlank(pageName)){
//				this.getCondition().put("pageName", pageName);
//			}
			
			Map<String,Object> result = materialService.findMaterialTemplateByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("result", result);
			this.getRequest().setAttribute("condition", this.getCondition());
		} catch (Exception e) {
			this.getRequest().setAttribute("result", null);
		}
		
		logger.info(this.getClass().getName()+" createPage  method end");
		return "material/createPage";
	}
	
	@ResponseBody
	@RequestMapping(value="createNewPage")
	public ResultModel createNewPage() {
		logger.info(this.getClass().getName()+" createPage  method start");
		try {
//			String pageName=this.getRequest().getParameter("pageName");
			int userId=Integer.parseInt(this.getSession().getAttribute("userId").toString()); 
			int showStatus=this.getSession().getAttribute("showStatus")==null?1:Integer.parseInt(this.getSession().getAttribute("showStatus").toString());
			this.getCondition().put("showStatus",showStatus);
			this.getCondition().put("userId",userId);
			String tType=this.getRequest().getParameter("tType")==null?"1":this.getRequest().getParameter("tType");
			if(StringUtils.isNotBlank(tType)){
				this.getCondition().put("tType", tType);
			}
//			if(StringUtils.isNotBlank(pageName)){
//				this.getCondition().put("pageName", pageName);
//			}
			
			Map<String,Object> result = materialService.findMaterialTemplateByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getModel().setResult(result);
		} catch (Exception e) {
			this.getRequest().setAttribute("result", null);
		}
		
		logger.info(this.getClass().getName()+" createPage  method end");
		return this.getModel();
	}
	
	@RequestMapping(value="list2")
	public String list2() {
		logger.info(this.getClass().getName()+" list2  method start");
		try {
			String tType=this.getRequest().getParameter("tType");
			if(StringUtils.isNotBlank(tType)){
				this.getCondition().put("tType", tType);
			}
			
			Map<String,Object> result = materialService.findMaterialTemplateByCondtion(this.getVisitId(), this.getVisitId(), this.getCondition());
			this.getRequest().setAttribute("result", result);
			this.getRequest().setAttribute("condition", this.getCondition());
		} catch (Exception e) {
			this.getRequest().setAttribute("result", null);
		}
		
		logger.info(this.getClass().getName()+" list2  method end");
		return "material/pop/page";
	}
	
	@ResponseBody
	@RequestMapping(value="create")
	public ResultModel create() {///wins/matertail/template/create
		logger.info(MaterialPageController.class.getName()+" create method start");
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			
			String pageName=this.getRequest().getParameter("pageName");
			String pageRemark=this.getRequest().getParameter("pageRemark");
			String templateId=this.getRequest().getParameter("templateId");
			if(StringUtils.isEmpty(templateId)){
				this.getModel().setCode(ResultCodeConstants.ERROR);
				return this.getModel();
			}else{
				params.put("Template_Id", templateId);
			}
			//获取模板详情
			Map<String,Object> matertialTemplateInfo = materialService.getMaterialTemplateById(this.getVisitId(),Integer.parseInt(templateId));
			
			if(matertialTemplateInfo==null){
				this.getModel().setCode(ResultCodeConstants.ERROR);
			}
			
//			path = "C:/Users/Administrator/Desktop/test/center-pop8/web/tb.html";
//
//			String target = "C:/Users/Administrator/Desktop/test/center-pop8/web/toolbar.js";
//
//			HtmlTemplateParseUtil.tbHtmlToJS(path, target);
			
			String url = this.tb_path+this.getRequest().getParameter("pagePath");
			
			params.put("page_URL", url);
			this.getModel().setDesc(url);
			if(StringUtils.isNotEmpty(pageRemark)){
				params.put("Page_Remark", pageRemark);
			}
			if(StringUtils.isNotEmpty(pageName)){
				params.put("page_Name", pageName);
			}else{
				this.getModel().setCode(ResultCodeConstants.ERROR);
				return this.getModel();
			}
			
			int createUser=this.getVisitId(); 
			params.put("create_user", createUser);
			this.getCondition().put("condition", params);
			int result = this.materialService.insertMaterialPage(this.getVisitId(), this.getCondition());
			if(result>0){
				this.getModel().setCode(ResultCodeConstants.SUCCESS);
			}else{
				this.getModel().setCode(ResultCodeConstants.ERROR);
			}
		} catch (Exception e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("方法调用异常");
		}
		logger.info(MaterialPageController.class.getName()+" create method end");
		return this.getModel();
	}
	
	
	
	/**
	 * 读取模板文件可编辑的模板标签 ,根据group 分组显示
	 * @param path  模板绝对路径
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="pathGroup")
	public ResultModel getAllEditHtmlElements(){	
		//读取模板html
		Document doc=null;
		List<HtmlADModelInfo> list=null;
		Map<String, List<HtmlADModelInfo>> admap = new LinkedHashMap<String, List<HtmlADModelInfo>>();
		int groupother = 0;
		
		//path 模板url   target 文件名称
		String target=this.getRequest().getParameter("target");
		String path=this.material_upload_path+target;
		
		//创建页面前先拷贝模板
		try {
			File[] files=(new File(path)).listFiles();
			File copy_path=new File(material_copy_path+target);
			copy(files, copy_path);
			
		
			//读取html
			File input = new File(this.material_copy_path+target+"/web/tb.html");
			doc=Jsoup.parse(input, "UTF-8");
			Elements es=doc.getElementsByAttribute(HTML_DYNAMIC_TAGATTRIBUTE);
			list=new ArrayList<HtmlADModelInfo>();
			String default_value="";
			String admodel = "";
			String imgw="40",imgh="40",mw="",mh="";
			HtmlADModelInfo adInfo  = null;
			String addesc="";
			String group="";
			int sort=0;
			String nodeName = "";
			for (Element e : es) {
				nodeName = e.nodeName();
			    adInfo = new HtmlADModelInfo();
				admodel = e.attr("admodel").length() > 0 ? e.attr("admodel").toUpperCase() : e.attr("admodel");
				group = e.attr("group");
				if(e.attr("sort")!=null && !"".equals(e.attr("sort"))) sort=Integer.parseInt(e.attr("sort"));
				
				if(group == null || "".equals(group))	{
					group = "Configuration";
					groupother = 1;
				}
				
				adInfo.setGroup(group);
				adInfo.setSort(sort);
				adInfo.setAdid(e.attr("id"));
				adInfo.setAdTitle(e.attr("adtitle"));
				addesc = e.attr("addesc");
				
				//adInfo.setAdDesc(e.attr("addesc"));
				adInfo.setAdModel(admodel);
				//adInfo.setComponents(e.attr("components"));
				adInfo.setListtype(e.attr("listtype").length() > 0 ? e.attr("listtype").toUpperCase() : e.attr("listtype"));

				
				if("TEXT".equals(admodel)  ){
					default_value = e.html();
					if(default_value != null && default_value.length()>0){
						adInfo.setValue(default_value);
					}
				}
				
				if("LINK".equals(admodel)){

					default_value = e.attr("href");
					if(default_value != null && default_value.length()>1){
						adInfo.setValue(default_value);
					}
					if("a".equals(nodeName)){
						default_value = e.html();
						if(default_value != null && default_value.length()>0){
							adInfo.setComponents(default_value);
						}
					}else{
						adInfo.setComponents("null");
					}
				}
				if("IMG".equals(admodel)){
					imgw="0";
					imgh="0";

					mw = e.attr("width") ;
					mh = e.attr("height") ;
					if(mw == null || "".equals(mw) || "100%".equals(mw) || "0px".equals(mw)){
						imgw="0";
					}else{
						imgw=mw.replace("px", "");
					}
					
					if(mh == null || "".equals(mh) || "100%".equals(mh) || "0px".equals(mh) ){
						imgh="0";
					}else{
						imgh=mh.replace("px", "");
					}
					
					if(!"0".equals(imgw) && !"0".equals(imgh)){
						adInfo.setImgsize("@!size-"+imgw+"-"+imgh+"-jpg");
					}
					
					if("0".equals(imgw)){
						addesc = "100%";
					}else{
						addesc = imgw;
					}
					if("0".equals(imgh)){
						addesc += " x 100%";
					}else{
						addesc += " x "+imgh;
					}
					
					//addesc = imgw+"x"+imgh;
					if("0".equals(imgw) || "0".equals(imgh)){
						imgw="50";
						imgh="50";
					}
	 
					
					adInfo.setImgurl("http://placehold.it/"+imgw+"x"+imgh);
					
				}
 
				
				adInfo.setAdDesc(addesc);
				
				if(admap.get(group) == null){
					admap.put(group, new ArrayList<HtmlADModelInfo>());
				}
				
				admap.get(group).add(adInfo);
				
				//list.add(adInfo);
			}
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(admap.size() >1 && groupother ==1){
			list =  admap.get("Configuration");
			admap.put("OtherConfiguration", list);
			admap.remove("Configuration");
		}
		
		
		this.getModel().setResult(admap);

		System.out.println(admap);
		System.out.println(this.getModel());
		return this.getModel();
	}

	
	/**
	 * 文件拷贝
	 * @param files 文件路径
	 * @param copy_path 拷贝路径
	 * @throws IOException
	 */
	private void copy(File[] files, File copy_path)throws IOException{
		// TODO Auto-generated method stub
		if(!copy_path.exists()){
			copy_path.mkdirs();
		}
		
		for (int i = 0; i < files.length; i++) { //循环遍历要复制的文件夹
			if (files[i].isFile()) { //如果文件夹中是文件

			FileInputStream fis = new FileInputStream(files[i]); //创建FileInputStream对象
			FileOutputStream fos = new FileOutputStream(new File(copy_path.getPath()+ File.separator + files[i].getName())); //复制后文件的保存路径
			int len = 0;
			byte[] buf = new byte[1024*5];
			while ((len = fis.read(buf)) != -1) {
			    fos.write(buf, 0, len);
			} 
			fos.flush();
			fos.close(); //关闭流
			fis.close();
			}
			if (files[i].isDirectory()) { //如果文件夹中是一个路径
			File des = new File(copy_path.getPath() + File.separator+ files[i].getName()); //在复制后路径中创建子文件夹
			des.mkdir();
			copy(files[i].listFiles(), des); //再次调用本方法
			} 
			}
		
	}
	
/**
 * 修改html页面	
 * @return
 */
	@ResponseBody
	@RequestMapping(value="changeHtml")
	public ResultModel changeHtml(){
		logger.info(" changeHtml method start");
		
		String adid=this.getRequest().getParameter("adid");//标签id
		String adval=this.getRequest().getParameter("adval");//标签val
		String target=this.getRequest().getParameter("target");//文件名称
		String adModel=this.getRequest().getParameter("adModel");//标签类型
		
		String _adval=this.getRequest().getParameter("_adval");//宽度高度对应文本框值
		
		
		try {
			
			Document doc=null;
			File input = new File(this.material_copy_path+target+"/web/tb.html");
			doc=Jsoup.parse(input, "UTF-8");
			//基本设置
			//宽度
			if(adModel.equals("width")){
				if(_adval.equals("")){
					doc.select("#wins_toolbar_html").attr("style"," width:"+adval+"px;");
				}else{
					doc.select("#wins_toolbar_html").attr("style"," width:"+adval+"px;"+" height:"+_adval+"px;");
				}
			}
			//高度
			if(adModel.equals("heigth")){
				if(_adval.equals("")){
					doc.select("#wins_toolbar_html").attr("style"," height:"+adval+"px;");	
				}else{
					doc.select("#wins_toolbar_html").attr("style"," height:"+adval+"px;"+" width:"+_adval+"px;");
				}
			}
			//关闭按钮
			if(adModel.equals("closeButton")){
				if(adval.equals("1")){
					doc.select("#closeButton").attr("style","display:block;");	
				}
				if(adval.equals("2")){
					doc.select("#closeButton").attr("style","display:none;");	
				}
			}
			//是否隐藏
			if(adModel.equals("showTime")){
				if(adval.equals("")){
				doc.select("#showTime_js").html("");
				}else{
				doc.select("#showTime_js").html("window.onload =function(){"+                    
											"var div1=document.getElementById('main_body');"+
											"var i="+adval+";"+
												"setInterval(function(){"+
													"i--;"+             
													"if(i == 0){"+
															"div1.style.display='none';}"+
													"},1000);"+
											"};");
				}
			}
			//是否虚化
			if(adModel.equals("opacity")){
				if(adval.equals("1")){
					doc.select("#main_body").attr("style","opacity:0.3;");
				}
				if(adval.equals("2")){
					doc.select("#main_body").attr("style","opacity:1.0;");
				}
			}
			//引入js脚本
			if(adModel.equals("add_js")){
				doc.select("#add_js").html(adval);
			}
			
			//文本
			if(adModel.equals("TEXT")){
				
				doc.select("#"+adid).html(adval);//修改html对应标签内容
			}
			//链接
			if(adModel.equals("LINK")){
				doc.select("#"+adid).attr("href",adval);//修改html对应标签内容
			}
			//图片
			if(adModel.equals("IMG")){
				doc.select("#"+adid).attr("src",adval);
			}
			//视频
			if(adModel.equals("VIDEO")){
				doc.select("#"+adid).attr("src",adval);
			}
			
			//清空html内容
			FileWriter fw= new FileWriter(input);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write("");
			//bw.write(new String(doc.html().getBytes("utf-8"),"GBK"));
			
			//写入修改后html，bufferwrite乱码
			 OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(input),"UTF-8");
	            out.write(doc.html());
	            out.flush();
	            out.close();
			String copy_url=this.material_copy_url+target;
			String copy_path=this.material_copy_path+target;
	            this.getModel().setDesc(copy_url);
	            this.getModel().setResult(copy_path);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("changeHtml method end");
		return this.getModel();
	}
/**
 * 将图片上传到images文件夹
 * @return
 */
	@ResponseBody
	@RequestMapping(value="updateImage")
	public ResultModel updateImage(@RequestParam MultipartFile upload){
		logger.debug("updateImage mothod start");
		String target=this.getRequest().getParameter("targetname");
		String path=material_copy_path+target;
		String fileNewName = "0";

		try {
			if(upload!=null){
				InputStream infile=upload.getInputStream();
				Date date=new Date();
			    SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss");  
			    Random random = new Random();
			    int k = random.nextInt(9000)+1000;
			    String time=formatter.format(date)+k; 
			    String fileName=upload.getOriginalFilename();
			    String fileFormat=fileName.substring(fileName.lastIndexOf("."),fileName.length()).toLowerCase();
			    	String filePath=path;
			    	File f =new File(filePath);
			    	if(!f.exists()){
			    		f.mkdirs();
			    	}
			    	fileName=time+fileFormat;
					OutputStream outfile=new FileOutputStream(filePath+"/web/images/"+fileName);
					int len=0;
					byte[] buff=new byte[8192];
					while ((len=infile.read(buff))>0) {
						outfile.write(buff, 0, len);
					}
					fileNewName="images/"+fileName;
					String fileNewurl=material_copy_url+target+"/web/images/"+fileName;
					this.getModel().setVersion(fileNewName);
					this.getModel().setDesc(fileNewurl);
					infile.close();
					outfile.close();
			}
		} catch (Exception e) {
			fileNewName="4";
			System.out.println("上传素材出现异常");
			e.printStackTrace();
		}
		return this.getModel();
	}
	

	/**
	 * 将TB的HTML模板转换成js脚本，存放到js/toolbar.js中
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="tbHtmljs")
	public ResultModel tbHtmljs(){
		
		String copy_target=this.getRequest().getParameter("target");
		String path=this.material_copy_path+copy_target+"/web/tb.html";
		String target=this.material_copy_path+copy_target+"/web/query.js";
		
		
		String script_top = "";
		String script_bottom = "";
		String body = "";
		String script_showTime="";
		String script_add="";
		StringBuilder scriptStr_top = new StringBuilder();
		StringBuilder scriptStr_bottom = new StringBuilder();
		StringBuilder scriptStr_body = new StringBuilder();
		StringBuilder scriptStr_showTime = new StringBuilder();
		StringBuilder scriptStr_add = new StringBuilder();

		Document doc = null;
		File input = new File(path);
		String _html ="";
		try {
			doc = Jsoup.parse(input, "UTF-8");
			
			
			// 提取内容
			if(doc.getElementById("top_js") != null){
				script_top = doc.getElementById("top_js").html();
			}
			if(doc.getElementById("showTime_js") != null){
				script_showTime = doc.getElementById("showTime_js").html();
			}
			if(doc.getElementById("add_js") != null){
				script_add = doc.getElementById("add_js").html();
			}
			
			if(doc.getElementById("bottom_js") != null){
				script_bottom = doc.getElementById("bottom_js").html();
			}
			//body = doc.body().html();
			body = doc.getElementById("main_body").html();
			
			
			// js 内容过滤
			if (script_top.length() > 0) {
				script_top = script_top.replace("	", "");
				String[] list = script_top.split("\n");

				for (String str1 : list) {
					str1 = str1.trim();
					if (str1.indexOf("//") != 0) {
						scriptStr_top.append(str1 + " ");
					}
				}
			}

			if (script_bottom.length() > 0) {
				script_bottom = script_bottom.replace("	", "");
				String[] list = script_bottom.split("\n");

				for (String str1 : list) {
					str1 = str1.trim();
					if (str1.indexOf("//") != 0) {
						scriptStr_bottom.append(str1 + " ");
					}
				}
			}
			if (script_showTime.length() > 0) {
				script_showTime = script_showTime.replace("	", "");
				String[] list = script_showTime.split("\n");

				for (String str1 : list) {
					str1 = str1.trim();
					if (str1.indexOf("//") != 0) {
						scriptStr_showTime.append(str1 + " ");
					}
				}
			}
			if (script_add.length() > 0) {
				script_add = script_add.replace("	", "");
				String[] list = script_add.split("\n");

				for (String str1 : list) {
					str1 = str1.trim();
					if (str1.indexOf("//") != 0) {
						scriptStr_add.append(str1 + " ");
					}
				}
			}

			// body 过滤
			if (body.length() > 0) {
				body = body.replaceAll("(?s)<!--.*?-->", "");
				body = body.replace("	", "");
				body = body.replace("'", "\\'");
				body = body.replace("href=\"..", "href=\"\'+$$$wins_pm.tUrl+\'");
				body = body.replace("src=\"images/", "src=\"\'+$$$wins_pm.tUrl+\'/images/");
				body = body.replace("poster=\"images/", "poster=\"\'+$$$wins_pm.tUrl+\'/images/");
				String[] list = body.split("\n");
				for (String str1 : list) {
					str1 = str1.trim();
					if (str1.indexOf("//") != 0) {
						scriptStr_body.append(str1 + " ");
					}
				}
			}
			
			
			if(scriptStr_top.length()>0 ) _html += scriptStr_top.toString()+"\n";
			if(scriptStr_showTime.length()>0 ) _html += scriptStr_showTime.toString()+"\n";
			if(scriptStr_add.length()>0 ) _html += scriptStr_add.toString()+"\n";
			if(scriptStr_body.length()>0 ){
				_html += "(function(){var __tb_html='" +scriptStr_body.toString()+"';"+"\n"+"document.getElementById('wins_toolbar').innerHTML=__tb_html;})(); ";
			}
			
			if(scriptStr_bottom.length()>0 ) _html += scriptStr_bottom.toString();
			
			FileUtils.writeStringToFile(new File(target),  _html, "UTF-8");
			this.getModel().setCode(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.getModel().setCode(0);
			e.printStackTrace();
		} 

		return this.getModel();
	}
	/** 
     * 执行压缩操作 
     * @param srcPathName 被压缩的文件/文件夹 
     */	
	@ResponseBody
	@RequestMapping(value="zipFile")
	public ResultModel zipFile(){
		String newFileName=newFileName();
		String target=this.getRequest().getParameter("target");
		String path=this.material_copy_path+target+"/web";//压缩文件路径
		
		//修改压缩文件名称   年月日时分秒
		String pathWeb=this.material_copy_path+target+"/"+newFileName;  //压缩文件修改文件夹名称后路径
		File[] copyFile=(new File(path)).listFiles();
		File pathWebFile=new File(pathWeb);
		try {
			//打包前修改文件名
			zipNewName.copy(copyFile, pathWebFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//删除修改后的剩余文件
			zipNewName.deletefile(path);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		  
		File zipFilePath=new File(this.zipFile);
		if(!zipFilePath.exists()){
			zipFilePath.mkdirs();
		}
		String zipPath=this.zipFile+newFileName+".zip";//压缩路径
		
		File file = new File(pathWeb);    
        if (!file.exists()){  
            throw new RuntimeException(path + "不存在！");    
        }  
        try {    
            FileOutputStream fileOutputStream = new FileOutputStream(zipPath);    
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());    
            ZipOutputStream out = new ZipOutputStream(cos);    
            String basedir = "";    
            compressByType(file, out, basedir);    
            out.close(); 
            this.getModel().setDesc(newFileName);
        } catch (Exception e) {   
            e.printStackTrace();  
            logger.error("执行压缩操作时发生异常:"+e);  
            throw new RuntimeException(e);    
        }
		
		return this.getModel();
	}
	/** 
     * 判断是目录还是文件，根据类型（文件/文件夹）执行不同的压缩方法 
     * @param file  
     * @param out 
     * @param basedir 
     */
	private void compressByType(File file, ZipOutputStream out, String basedir) {
		 /* 判断是目录还是文件 */    
        if (file.isDirectory()) {    
            logger.info("压缩：" + basedir + file.getName());    
            this.compressDirectory(file, out, basedir);    
        } else {    
            logger.info("压缩：" + basedir + file.getName());    
            this.compressFile(file, out, basedir);    
        }
		
	}
	/** 
     * 压缩一个文件 
     * @param file 
     * @param out 
     * @param basedir 
     */ 
	private void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {    
            return;    
        }    
        try {    
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));    
            ZipEntry entry = new ZipEntry(basedir + file.getName());    
            out.putNextEntry(entry);    
            int count;    
            byte data[] = new byte[BUFFER];    
            while ((count = bis.read(data, 0, BUFFER)) != -1) {    
                out.write(data, 0, count);    
            }    
            bis.close();    
        } catch (Exception e) {    
            throw new RuntimeException(e);    
        }
		
	}
	/** 
     * 压缩一个目录 
     * @param dir 
     * @param out 
     * @param basedir 
     */ 
	private void compressDirectory(File dir, ZipOutputStream out,
			String basedir) {
		if (!dir.exists()){  
            return;    
       }  
            
       File[] files = dir.listFiles();    
       for (int i = 0; i < files.length; i++) {    
           /* 递归 */    
           compressByType(files[i], out, basedir + dir.getName() + "/");    
       } 
		
	}
	
	//年月日时分秒   新文件名
	public String newFileName(){
		Date date = new Date();
		//转换提日期输出格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName=dateFormat.format(date);
		
		return newFileName;
	}
}
