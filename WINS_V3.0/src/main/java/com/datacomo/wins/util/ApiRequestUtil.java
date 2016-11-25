package com.datacomo.wins.util;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/19.
 */
public class ApiRequestUtil {
    private static Logger logger = Logger.getLogger(ApiRequestUtil.class.getName());
    private static final String APPLICATION_JSON = "application/json";

    /**
     * 根据标签查出用户手机号
     * @param api
     * @param parame1
     * @param parame2
     * @return
     */
    public static List<String> searchUserPhoneByLabel(String api,String parame1,String parame2) {
        logger.info("searchUserPhoneByLabel method start");
        if(logger.isDebugEnabled()){
            logger.debug("api:"+api);
            logger.debug("parame1:"+parame1);
            logger.debug("parame2:"+parame2);
        }
        List<String> result = null;
        try {
            // 创建连接
            URL url = new URL(api);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type",APPLICATION_JSON);
            connection.connect();

            // POST请求参数
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String content = "{\""+URLEncoder.encode(parame1,"UTF-8")+"\":\""+URLEncoder.encode(parame2,"UTF-8")+"\"}";
            out.writeBytes(content);
            out.flush();
            out.close();

            // 读取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            reader.close();
            String str = sb.toString();
            connection.disconnect();   // 断开连接
            result = JsonUtil.JsonDataToList(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("searchUserPhoneByLabel method end");
        return result;
    }

    /**
     * 根据sim卡,手机号,或者是设备编码查询标签
     * @param api
     * @param parames
     * @return
     */
    public static List<Map<String,Object>> searchLabelByinfo(String api,String parames){
        logger.info("searchLabelByinfo method start");
        if(logger.isDebugEnabled()){
            logger.debug("api:"+api);
            logger.debug("parames:"+parames);
        }
        List<Map<String,Object>> result = null;
        try {
            // 创建连接
            URL url = new URL(api);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type",APPLICATION_JSON);
            connection.connect();
            if(parames!=null){
                // POST请求参数
                Map<String,Object> map = JsonUtil.SimpleJsonStrToMap(parames);
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                JSONObject obj = new JSONObject();
                for(String key:map.keySet()){
                    obj.element(key,map.get(key));
                }
                out.writeBytes(obj.toString());
                out.flush();
                out.close();
            }
            // 读取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            reader.close();
            String str = sb.toString();
            connection.disconnect();   // 断开连接
            result = JsonUtil.JsonDataToListMap(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("searchLabelByinfo method end");
        return result;
    }

    /**
     * 获取所有行业标签、不需要参数
     * @param api
     * @param parames
     * @return
     */
    public static List<Map<String,Object>> searchAllIndustryLabel(String api,String parames){
        logger.info("searchAllIndustryLabel method start");
        if(logger.isDebugEnabled()){
            logger.debug("api:"+api);
            logger.debug("parames:"+parames);
        }
        List<Map<String,Object>> result = null;
        try {
            // 创建连接
            URL url = new URL(api);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type",APPLICATION_JSON);
            connection.connect();
            if(parames!=null){
                // POST请求参数
                Map<String,Object> map = JsonUtil.SimpleJsonStrToMap(parames);
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                JSONObject obj = new JSONObject();
                for(String key:map.keySet()){
                    obj.element(key,map.get(key));
                }
                out.writeBytes(obj.toString());
                out.flush();
                out.close();
            }
            // 读取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            reader.close();
            String str = sb.toString();
            connection.disconnect();   // 断开连接
            result = JsonUtil.JsonDataToListMap(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("searchAllIndustryLabel method end");
        return result;
    }

    /**
     * 获取所有人群标签、不需要参数
     * @param api
     * @param parames
     * @return
     */
    public static List<Map<String,Object>> searchAllCrowdLabel(String api,String parames){
        logger.info("searchAllCrowdLabel method start");
        if(logger.isDebugEnabled()){
            logger.debug("api:"+api);
            logger.debug("parames:"+parames);
        }
        List<Map<String,Object>> result = null;
        try {
            // 创建连接
            URL url = new URL(api);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type",APPLICATION_JSON);
            connection.connect();
            // 读取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            reader.close();
            String str = sb.toString();
            connection.disconnect();   // 断开连接
            result = JsonUtil.JsonDataToListMap(str);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("searchAllCrowdLabel method end");
        return result;
    }

    /**
     * 给定行业标签列出所有媒体
     * @param api
     * @param parames
     * @return
     */
    public static List<String> searchMediaByIndustryLabel(String api, String parames){
        logger.info("searchMediaByIndustryLabel method start");
        if(logger.isDebugEnabled()){
            logger.debug("api:"+api);
            logger.debug("parames:"+parames);
        }
        List<String> result = null;
        try {
            // 创建连接
            URL url = new URL(api);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",APPLICATION_JSON);
            connection.connect();

            // POST请求参数
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String content = "{\"industry\":\""+URLEncoder.encode(parames,"UTF-8")+"\"}";
            out.writeBytes(content);
            out.flush();
            out.close();

           // POST请求参数
            /*DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            JSONObject obj = new JSONObject();
            //obj.element("industry", URLEncoder.encode(parames,"utf-8"));
            obj.element("industry", parames);
            out.writeBytes(obj.toString());
            out.flush();
            out.close();*/

            // 读取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            reader.close();
            String str = sb.toString();
            connection.disconnect();   // 断开连接
            result = JsonUtil.JsonDataToList(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("searchMediaByIndustryLabel method end");
        return result;
    }

    /**
     * 根据url查询媒体标间的价格
     * @param api
     * @param parames
     * @return
     */
    public static String searchPriceByMediaUrl(String api,String parames){
        logger.info("searchPriceByMediaUrl method start");
        if(logger.isDebugEnabled()){
            logger.debug("api:"+api);
            logger.debug("parames:"+parames);
        }
        String result = null;
        try {
            // 创建连接
            URL url = new URL(api);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type",APPLICATION_JSON);
            connection.connect();

            // POST请求参数
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            JSONObject obj = new JSONObject();
            obj.element("url", parames);
            out.writeBytes(obj.toString());
            out.flush();
            out.close();

            // 读取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            reader.close();
            String string = sb.toString();
            connection.disconnect();   // 断开连接
            result = JsonUtil.JsonStrToString(string);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        logger.info("searchPriceByMediaUrl method end");
        return result;
    }

    /**
     * 执行请求接口
     * @param api 执行请求接口url
     * @return http://115.236.45.141:38080/api/107（1：表示需要更新数据  0：每20分钟请求一次，直到=1）
     * http://115.236.45.141:38080/api/108（0：执行结束）
     */
    public static int executeRequest(String api){
        logger.info("executeRequest method start");
        if(logger.isDebugEnabled()){
            logger.debug("api:"+api);
        }
        int result = -1;
        Map<String,Object> map = null;
        try {
            // 创建连接
            URL url = new URL(api);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type",APPLICATION_JSON);
            connection.connect();
            // 读取响应数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            reader.close();
            String str = sb.toString();
            connection.disconnect();   // 断开连接
            JSONObject jsonObject = JSONObject.fromObject(str);
            Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
            if(mapJson.get("success")!=null && mapJson.get("success").toString().equals("true")) {
                result= Integer.parseInt(String.valueOf(mapJson.get("data")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        logger.info("executeRequest method end");
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //String parames = "[{\"性别\":\"AND_女\",\"职业\":\"OR_编辑\"}]";
        String api = String.valueOf(ApiConfigUtil.message.get("endExecuteApi"));
        //ApiRequestUtil.searchAllIndustryLabel(api,null);

        //String priceApi = String.valueOf(ApiConfigUtil.message.get("searchPriceByMediaUrlApi"));
        //String parames = "a.baidu.com";
        System.out.println(api);
        System.out.println(ApiRequestUtil.executeRequest(api));

        //String apisss = "http://115.236.45.141:38080/api/106";
        //System.out.println("///////////////////////----------------------： "+!apisss.substring(apisss.length()-1).equals("/"));*//*

        //System.out.println(ApiRequestUtil.searchMediaByIndustryLabel(api,"新闻"));
    }

    /*public static void main(String[] args) {

        // run in a second

        final long timeInterval = 1000;

        Runnable runnable = new Runnable() {

            public void run() {

                while (true) {

                    // ------- code for task to run

                    System.out.println("Hello !!");

                    // ------- ends here

                    try {

                        Thread.sleep(timeInterval);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                }

            }

        };

        Thread thread = new Thread(runnable);

        thread.start();

    }*/

}
