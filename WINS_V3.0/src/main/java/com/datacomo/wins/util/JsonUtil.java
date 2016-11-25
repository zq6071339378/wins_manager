package com.datacomo.wins.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/19.
 */
public class JsonUtil {
    /**
     * 将简单json字符串转为map集合
     * json字符串格式：{"性别":"AND_女","职业":"OR_编辑"}
     * @param parames
     * @return
     */
    public static Map<String, Object> SimpleJsonStrToMap(String parames) {
        JSONArray jsonArray = JSONArray.fromObject(parames);
        List<Map<String, Object>> mapListJson = (List) jsonArray;
        Map<String, Object> mapValue = new HashMap<String, Object>();
        for (int i = 0; i < mapListJson.size(); i++) {
            Map<String, Object> map = mapListJson.get(i);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = (String) entry.getValue();
                mapValue.put(key, value);
            }
        }
        return mapValue;
    }

    /**
     * 将简单json字符串里的price字段取出
     * json字符串格式：{"price":213,"success":true}
     * @param parames
     * @return
     */
    public static String JsonStrToString(String parames) {
        JSONObject jsonObject = JSONObject.fromObject(parames);
        Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
        String result = null;
        if(mapJson.get("success")!=null && mapJson.get("success").toString().equals("true")) {
            result= String.valueOf(mapJson.get("price"));
        }
        return result;
    }

    /**
     * json字符串转为list集合
     * 以List<Map<String,Object>>格式返回json字符串里的data数据
     * json字符串格式：{"data":[{"chName":"根目录"},{"enName":"news","ynName":"","chName":"新闻"},{"enName":"search","ynName":"","chName":"搜索"},{"enName":"webs","ynName":"","chName":"网页"}],"success":"true"}
     * @param parames
     * @return
     */
    public static List<Map<String,Object>> JsonDataToListMap(String parames) {
        JSONObject jsonObject = JSONObject.fromObject(parames);
        Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
        List<Map<String,Object>> result = null;
        if(mapJson.get("success")!=null && mapJson.get("success").toString().equals("true")) {
            result = new ArrayList<>();
            String datastr = mapJson.get("data").toString();
            if(datastr.indexOf("[")!=-1){
                if(datastr.indexOf("{")!=-1){
                    JSONArray jsonArray = JSONArray.fromObject(datastr);
                    List<Map<String, Object>> mapListJson = (List) jsonArray;
                    for (int i = 0; i < mapListJson.size(); i++) {
                        Map<String,Object> resultMap = new HashMap<>();
                        Map<String, Object> obj = mapListJson.get(i);
                        for (Map.Entry<String, Object> entry : obj.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            resultMap.put(key,value);
                        }
                        result.add(resultMap);
                    }
                }
            }
        }
        return result;
    }

    /**
     * json字符串转为list集合
     * 以List<String>格式返回json字符串里的data数据
     * json字符串格式：{"data":["111","222","333","444","555","666"],"success":"true"}
     * @param parames
     * @return
     */
    public static List<String> JsonDataToList(String parames) {
        JSONObject jsonObject = JSONObject.fromObject(parames);
        Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
        List<String> result = null;
        if(mapJson.get("success")!=null && mapJson.get("success").toString().equals("true")) {
            String datastr = mapJson.get("data").toString();
            if(datastr.indexOf("[")!=-1 && datastr.indexOf("{")==-1){
                result = new ArrayList<>();
                datastr = datastr.substring(1,datastr.length()-1);
                datastr = datastr.replace("\"","");
                String[] datastrArray = datastr.split(",");
                for(int i=0;i<datastrArray.length;i++){
                    result.add(datastrArray[i]);
                }
            }
        }
        return result;
    }

    //测试
    public static void main(String[] args) {
        //String  parames = "{\"data\":[{\"chName\":\"根目录\"},{\"enName\":\"news\",\"ynName\":\"\",\"chName\":\"新闻\"},{\"enName\":\"search\",\"ynName\":\"\",\"chName\":\"搜索\"},{\"enName\":\"webs\",\"ynName\":\"\",\"chName\":\"网页\"}],\"success\":\"true\"}";
        //String  parames = "{\"data\":[\"111\",\"222\",\"333\",\"444\",\"555\",\"666\"],\"success\":\"true\"}";
        //String  parames = "{\"data\":[],\"success\":\"true\"}";
        //String parames="{\"data\":[{\"id\":\"1\",\"chName\":\"根目??},{\"enName\":\"news\",\"pid\":\"37d6ea5a0d2b40e1815730d1206212e9\",\"id\":\"2f07270aef9a4fd6b43621da3934f989\",\"ynName\":\"news3\",\"chName\":\"新闻\"},{\"enName\":\"search\",\"pid\":\"1\",\"id\":\"37d6ea5a0d2b40e1815730d1206212e9\",\"ynName\":\"search3\",\"chName\":\"搜索\"},{\"enName\":\"page\",\"pid\":\"37d6ea5a0d2b40e1815730d1206212e9\",\"id\":\"3846a8a836564679ac4df8af827f55d1\",\"ynName\":\"pageyl\",\"chName\":\"网页\"},{\"enName\":\"baidu\",\"pid\":\"3846a8a836564679ac4df8af827f55d1\",\"id\":\"6596d99cd46541ec9b173a38d2ce6914\",\"ynName\":\"baiduyl\",\"chName\":\"百度\"},{\"enName\":\"A6\",\"pid\":\"8e6b1a6add9649a8b45eac3be72eaa8c\",\"id\":\"747c212f94ec44928f9b46a106ef97d8\",\"ynName\":\"\",\"chName\":\"A6\"},{\"enName\":\"Agricultural Bank of China\",\"pid\":\"b28c033cb70340128c3953a7a7525747\",\"id\":\"7dd8e53748b441e7ac3d9d84672a534d\",\"ynName\":\"\",\"chName\":\"中国农行\"},{\"enName\":\"car\",\"pid\":\"1\",\"id\":\"858e3ec2c4a147c284e8b1d4bf93dbbd\",\"ynName\":\"\",\"chName\":\"汽车\"},{\"enName\":\"Audi\",\"pid\":\"858e3ec2c4a147c284e8b1d4bf93dbbd\",\"id\":\"8e6b1a6add9649a8b45eac3be72eaa8c\",\"ynName\":\"\",\"chName\":\"奥迪\"},{\"enName\":\"bank\",\"pid\":\"1\",\"id\":\"9537705bcb244c68b0a2b55763a68285\",\"ynName\":\"\",\"chName\":\"银行\"},{\"enName\":\"China industry and Commerce\",\"pid\":\"9a089d36103142c2b413ed315a381e28\",\"id\":\"97b4ca6f15954d04aa501fc73132ea78\",\"ynName\":\"\",\"chName\":\"中国工商\"},{\"enName\":\"Business circles\",\"pid\":\"9537705bcb244c68b0a2b55763a68285\",\"id\":\"9a089d36103142c2b413ed315a381e28\",\"ynName\":\"\",\"chName\":\"工商\"},{\"enName\":\"agricultural bank\",\"pid\":\"9537705bcb244c68b0a2b55763a68285\",\"id\":\"b28c033cb70340128c3953a7a7525747\",\"ynName\":\"\",\"chName\":\"农行\"},{\"enName\":\"F4\",\"pid\":\"e56363adbf324b44b5537e93ee87c905\",\"id\":\"dbddfd3da4e74978a73e70e91938e607\",\"ynName\":\"\",\"chName\":\"F4\"},{\"enName\":\"Benz\",\"pid\":\"858e3ec2c4a147c284e8b1d4bf93dbbd\",\"id\":\"e56363adbf324b44b5537e93ee87c905\",\"ynName\":\"\",\"chName\":\"奔驰\"}],\"success\":true}";
        //JsonUtil.JsonDataToListMap(parames);
        String par = "{\"success\":true,\"price\":213}";
        System.out.println("取出json中的price___________________________________________ : "+ JsonUtil.JsonStrToString(par));
    }
}