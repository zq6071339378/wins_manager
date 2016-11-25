/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2010-2014 Datacomo Communications Technology INC.
 * 
 * This source file is a part of hirouter project. 
 * date: 2014年11月18日
 *
 */
package com.datacomo.wins.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author zhujigao
 * @date 2014年11月18日 上午9:12:27
 * @update developer zhujigao
 * @update date 2014年11月18日 上午9:12:27
 * @version v1.0.0
 */
public class HtmlTemplateParseUtil {

	public final static String HTML_DYNAMIC_TAGATTRIBUTE = "adModel";

	
	/**
	 * 获取模板图片路径
	 * @param modlepath
	 * @param imgurl
	 * @return
	 */
	public static String getModelImgPath(String modlepath,String imgurl){
		if(imgurl.indexOf("../")>=0){
			modlepath = modlepath.substring(0, modlepath.lastIndexOf("/"));
			while(imgurl.indexOf("../")>=0){
				modlepath = modlepath.substring(0, modlepath.lastIndexOf("/"));
				imgurl = imgurl.replaceFirst("../", "");
			}
			return modlepath+"/"+imgurl;
		}else{
			imgurl = imgurl.replace("./", "");
			return modlepath.substring(0, modlepath.lastIndexOf("/"))+"/"+imgurl;
		}
		
	}
 
	/**
	 * 将TB的HTML模板转换成js脚本，存放到js/toolbar.js中
	 *  update developer wangyanjie 
	 * @param path
	 * @param target
	 * @return
	 */
	public static boolean tbHtmlToJS(String path, String target) {
		String script_top = "";
		String script_bottom = "";
		String body = "";
		StringBuilder scriptStr_top = new StringBuilder();
		StringBuilder scriptStr_bottom = new StringBuilder();
		StringBuilder scriptStr_body = new StringBuilder();

		Document doc = null;
		File input = new File(path);
		String _html ="";
		try {
			doc = Jsoup.parse(input, "UTF-8");
			
			
			// 提取内容
			script_top = doc.getElementById("top_js").html();
			script_bottom = doc.getElementById("bottom_js").html();
			//body = doc.body().html();
			if(doc.getElementById("main_body")== null){
				body = doc.body().html();
			}else{
				body = doc.getElementById("main_body").html();
			}
			
			
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

			// body 过滤
			if (body.length() > 0) {
				body = body.replaceAll("(?s)<!--.*?-->", "");
				body = body.replace("	", "");
				body = body.replace("'", "\\'");
				body = body.replace("href=\"..", "href=\"\'+$$$hirouter_pm.url+\'");
				body = body.replace("src=\"..", "src=\"\'+$$$hirouter_pm.url+\'");
				String[] list = body.split("\n");
				for (String str1 : list) {
					str1 = str1.trim();
					if (str1.indexOf("//") != 0) {
						scriptStr_body.append(str1 + " ");
					}
				}
			}
			
			
			if(scriptStr_top.length()>0 ) _html += scriptStr_top.toString()+"\n";
			if(scriptStr_body.length()>0 ){
				_html += "(function(){var __tb_html='" +scriptStr_body.toString()+"';"+"\n"+"document.getElementById('hirouter_toolbar').innerHTML=__tb_html;})(); ";
			}
			
			if(scriptStr_bottom.length()>0 ) _html += scriptStr_bottom.toString();
			
			FileUtils.writeStringToFile(new File(target),  _html, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} 

	
		//System.out.println(_html);

		return true;
	}

	public static Document pathswitch (Document doc) {
		String tmp="";
		for(Element e:doc.getElementsByTag("img")){
			tmp = e.attr("src");
			tmp = tmp.replace("../", "../#releaseid#/");
			e.attr("src", tmp);
		}
		for(Element e:doc.getElementsByTag("script")){
			tmp = e.attr("src");
			if("".equals(tmp)) continue;
			tmp = tmp.replace("../", "../#releaseid#/");
			e.attr("src", tmp);
		}
		for(Element e:doc.getElementsByTag("link")){
			tmp = e.attr("href");
			tmp = tmp.replace("../", "../#releaseid#/");
			e.attr("href", tmp);
		}
		return doc;
	}
	
	public static boolean tbHtmlToJSForWins(String path, String target) {
		String script_top = "";
		String script_bottom = "";
		String body = "";
		StringBuilder scriptStr_top = new StringBuilder();
		StringBuilder scriptStr_bottom = new StringBuilder();
		StringBuilder scriptStr_body = new StringBuilder();

		Document doc = null;
		File input = new File(path);
		String _html ="";
		try {
			doc = Jsoup.parse(input, "UTF-8");
			
			
			// 提取内容
			if(doc.getElementById("top_js") != null){
				script_top = doc.getElementById("top_js").html();
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

			// body 过滤
			if (body.length() > 0) {
				body = body.replaceAll("(?s)<!--.*?-->", "");
				body = body.replace("	", "");
				body = body.replace("'", "\\'");
				body = body.replace("href=\"..", "href=\"\'+$$$wins_pm.tUrl+\'");
				body = body.replace("src=\"..", "src=\"\'+$$$wins_pm.tUrl+\'");
				String[] list = body.split("\n");
				for (String str1 : list) {
					str1 = str1.trim();
					if (str1.indexOf("//") != 0) {
						scriptStr_body.append(str1 + " ");
					}
				}
			}
			
			
			if(scriptStr_top.length()>0 ) _html += scriptStr_top.toString()+"\n";
			if(scriptStr_body.length()>0 ){
				_html += "(function(){var __tb_html='" +scriptStr_body.toString()+"';"+"\n"+"document.getElementById('wins_toolbar').innerHTML=__tb_html;})(); ";
			}
			
			if(scriptStr_bottom.length()>0 ) _html += scriptStr_bottom.toString();
			
			FileUtils.writeStringToFile(new File(target),  _html, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	
		//System.out.println(_html);

		return true;
	}
	
	
	/**
	 * 将TB的HTML模板转换成js脚本，存放到js/toolbar.js中
	 * 
	 * update developer zhujigao {If not update ,please delete it}update date
	 * 2014年11月28日 下午4:53:28
	 * 
	 * @param path
	 * @return
	 */
	public static boolean tbHtmlToJS_old(String path, String target) {

		// 区分script，body标签

		StringBuilder scriptStr = new StringBuilder();
		StringBuilder bodyStr = new StringBuilder();

		boolean scriptTag = false;
		boolean bodyTag = false;

		File file = new File(path);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;

				// 组装JS标签
				if (tempString.contains("</script>")) {
					String s = tempString.replace("</script>", "");
					scriptStr.append(s);
					scriptTag = false;
				}
				if (scriptTag) {
					scriptStr.append(tempString);
				}
				if (tempString.contains("<script type=\"text/javascript\">")) {
					scriptTag = true;
					String s = tempString.replace("<script type=\"text/javascript\">", "");
					scriptStr.append(s.trim());
				}

				// 组装HTML标签
				if (tempString.contains("</body>")) {
					String s = tempString.replace("</body>", "");
					bodyStr.append(s.trim());
					bodyTag = false;
				}
				if (bodyTag) {
					bodyStr.append(tempString.trim().replaceAll("\"", "\\\""));
				}
				if (tempString.contains("<body>")) {
					bodyTag = true;
					String s = tempString.replace("<body>", "");
					bodyStr.append(s.trim());
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		System.out.println("script str:" + scriptStr.toString());
		System.out.println("body str:" + bodyStr.toString());

		// html
		String _html = "var _html='" + bodyStr.toString().replaceAll("'", "\\'") + "';";

		// 拼接JS方法
		String script = "function winsReadyfunc(){var a=document.createElement('div');a.innerHTML=_html;document.body.innerHTML+=_html;var b=window.innerHeight;wins_pop_maxtop=b*0.4;wins_pop_top=b;wins.c('.wins_pop_content').css('height',(b-wins_pop_maxtop-88)+'px')}winsReadyfunc();";

		// 生成toolbar.js文件
		// 保存到本地
		try {
			FileUtils.writeStringToFile(new File(target), scriptStr.toString() + _html + script, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	// test
	public static void main(String[] args) {
		
//		HtmlTemplateParseUtil h = new HtmlTemplateParseUtil();
//		System.out.println(h.getModelImgPath("resource/html_template/wins_toolbar/web/tb.html", "./images/icon_4g.png"));
		
		String path = "C:/Users/Administrator/Desktop/test/center-pop8/web/tb.html";
		path = "C:/Users/Administrator/Desktop/test/center-pop8/web/tb.html";

		String target = "C:/Users/Administrator/Desktop/test/center-pop8/web/toolbar.js";

		HtmlTemplateParseUtil.tbHtmlToJS(path, target);

	}
	

}
