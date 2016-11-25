/**
 * 
 */
package com.datacomo.wins.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * @author zhaizhanpo
 *
 */
@SuppressWarnings("unchecked")
public class Config {
	public static Map<String,Object> message = new HashMap<String,Object>();
	public static ObjectMapper objectMapper = null;
	static{
		String path = Config.class.getResource("/").getPath()+"message.properties";
		String jsonpath = Config.class.getResource("/").getPath()+"config.json";
		Properties properties = new Properties();
		try {
			InputStream in = new FileInputStream(path);
			properties.load(in);
			in.close();
			for (Object key : properties.keySet()) {
				message.put(String.valueOf(StringUtils.trim(String.valueOf(key))), StringUtils.trim(String.valueOf(properties.get(key))));
		    }
			
			objectMapper = new ObjectMapper();
			Map<String,String> map = objectMapper.readValue(new File(jsonpath),Map.class);
			message.putAll(map);
			List<Map<String,String>> list = (List<Map<String,String>>)message.get("provincial");
			
			for(Map<String,String> map2 : list){
				if(map2.get("provincialId").equals(message.get("provincialId"))){
					message.putAll(map2);
					break;
				}
			}
			//System.out.println("aaaa:"+Config.message.get("isEnglish").toString());
			boolean isEnglish=Boolean.valueOf(Config.message.get("isEnglish").toString());
			String globalzh = Config.class.getResource("/").getPath()+"globalzh.properties";
			if(isEnglish){
				globalzh = Config.class.getResource("/").getPath()+"globalen.properties";
			}
			InputStream inType = new FileInputStream(globalzh);
			properties.load(inType);
			inType.close();
			for (Object key : properties.keySet()) {
				//System.out.println(String.valueOf(StringUtils.trim(String.valueOf(key)))+"="+StringUtils.trim(String.valueOf(properties.get(key))));
				message.put(String.valueOf(StringUtils.trim(String.valueOf(key))), StringUtils.trim(String.valueOf(properties.get(key))));
		    }
			//System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadZhEn(){
	}
	
	public static void main(String args[]){
		
		String str ="{\"userHeadImage\":[{\"width\":\"36\",\"height\":\"36\"},{\"width\":\"160\",\"height\":\"120\"}]}" ;
		ObjectMapper  objectMapper = new ObjectMapper();
		try {
//			Map<String,String> map = objectMapper.readValue(str,new TypeReference<Map<String,String>>() {});
		//	System.out.println(map.get("userHeadImage"));
			
			
			System.out.println(Config.message.get("pagePolicytest").toString());
			//List<Map<String,String>> list = (List<Map<String,String>>)map.get("userHeadImage");
//
//			Map<String, String> packlist= (Map<String,String>)Config.message.get("hitbutton");
//			System.out.println(packlist.get("close"));
//			Map<String,Object> map = objectMapper.readValue(Config.message.get("provincialName").toString(),Map.class);
			//System.out.println(map.get("001"));
//			Config.message.get("packlist").toString();
			// TODO Auto-generated catch block
			//e.printStackTrace();
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//System.out.println(message);
	}
	
	
}
