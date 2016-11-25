package com.datacomo.wins.util;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取requestAPI.properties配置文件
 * Created by yangxiongbin on 2016/9/20.
 */
public class ApiConfigUtil {
    public static Map<String,Object> message = new HashMap<String,Object>();
    public static ObjectMapper objectMapper = null;
    static {
        String path = ApiConfigUtil.class.getResource("/").getPath() + "requestAPI.properties";
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(path);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
            properties.load(bf);
            inputStream.close();
            for (Object key : properties.keySet()) {
                message.put(String.valueOf(StringUtils.trim(String.valueOf(key))), StringUtils.trim(String.valueOf(properties.get(key))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试
    public static void main(String args[]){
        String parames = String.valueOf(ApiConfigUtil.message.get("searchUserInfoApi"));
        //Map<String,Object> ss= ApiRequestUtil.searchMediaByIndustryLabel(ADD_URL,"");
        System.out.println(parames);

    }
}
