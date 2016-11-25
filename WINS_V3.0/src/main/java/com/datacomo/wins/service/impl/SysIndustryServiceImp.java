package com.datacomo.wins.service.impl;

import com.datacomo.wins.business.SysIndustryBusiness;
import com.datacomo.wins.business.SysMediaBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysIndustryService;
import com.datacomo.wins.util.ApiConfigUtil;
import com.datacomo.wins.util.ApiRequestUtil;
import com.datacomo.wins.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
@Service("sysIndustryService")
public class SysIndustryServiceImp implements SysIndustryService {
    private static Logger logger = Logger.getLogger(SysIndustryServiceImp.class.getName());

    @Autowired
    private SysIndustryBusiness sysIndustryBusiness;
    @Autowired
    private SysMediaBusiness sysMediaBusiness;


    @Override
    public int startExcuteRequest() {
        logger.info("startExcuteRequest method start");
        int result = -1;
        String api = String.valueOf(ApiConfigUtil.message.get("startExecuteApi"));
        if(logger.isDebugEnabled()){
            logger.debug("api "+api);
        }
        result = ApiRequestUtil.executeRequest(api);
        logger.info("startExcuteRequest method end");
        return result;
    }

    @Override
    public int updateIndustryLabel() throws ServiceException {
        logger.info("timedUpdateIndustryLabel method start");
        String api = String.valueOf(ApiConfigUtil.message.get("searchAllIndustryLabelApi"));
        String parames = null;
        List<Map<String,Object>> listMap = ApiRequestUtil.searchAllIndustryLabel(api,parames);
        int result = 0;
        if(listMap.size()>0) {
            List<Map<String, Object>> list = new ArrayList<>();
            for (Map<String, Object> map : listMap) {
                if (map.size() < 3) {
                    continue;
                }
                Map<String, Object> conditionData = new HashMap<String, Object>();
                conditionData.put("uuid", map.get("id"));
                conditionData.put("puuid", map.get("pid"));
                conditionData.put("enName", map.get("enName"));
                conditionData.put("ynName", map.get("ynName"));
                conditionData.put("chName", map.get("chName"));
                list.add(conditionData);
            }
            Map<String, Object> condition = new HashMap<>();
            condition.put("createTime", DateUtil.getDateString());
            condition.put("list", list);
            try {
                sysIndustryBusiness.deleteAll();
                result = sysIndustryBusiness.insertInfo(condition);
            } catch (BusinessException e) {
                e.printStackTrace();
                logger.error(e);
                return -1;
            }
        }
        logger.info("timedUpdateIndustryLabel method start");
        return result;
    }

    @Override
    public int updateMediaLabel(){
        logger.info("timedUpdateMediaLabel method start");
        String mediaApi = String.valueOf(ApiConfigUtil.message.get("searchMediaByIndustryLabelApi"));
        String priceApi = String.valueOf(ApiConfigUtil.message.get("searchPriceByMediaUrlApi"));
        String parames = null;
        int result = -2;
        List<Map<String,Object>> industry = null;
        try {
            industry =sysIndustryBusiness.findAll();
            if(industry.size()>0){
                Map<String,Object> condition = new HashMap<>();  //条件
                List<Map<String,Object>> list = new ArrayList<>(); //媒体标签集合
                for (Map<String,Object> map : industry){
                    String industryId = String.valueOf(map.get("industryId"));
                    String enName = String.valueOf(map.get("enName"));
                    String ynName = String.valueOf(map.get("ynName"));
                    String chName = String.valueOf(map.get("chName"));

                    //具体看以哪个标签的名字去查url
                    parames = enName;
                    List<String> mediaUrl =ApiRequestUtil.searchMediaByIndustryLabel(mediaApi,parames);
                    if(mediaUrl==null){
                        continue;
                    }
                    for(String url:mediaUrl){
                        String urlStr = url;
                        String price = ApiRequestUtil.searchPriceByMediaUrl(priceApi,urlStr);
                        if(price==null || price==""){
                            continue;
                        }
                        Map<String,Object> media = new HashMap<>();
                        media.put("industryId",industryId);
                        media.put("mediaUrl",urlStr);
                        media.put("mediaPrice",price);
                        list.add(media);
                    }
                }
                condition.put("list",list);
                condition.put("createTime",DateUtil.getDateString());
                sysMediaBusiness.deleteAll();
                result = sysMediaBusiness.insertInfo(condition);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            return -1;
        }
        logger.info("timedUpdateMediaLabel method end");
        return result;
    }
    @Override
   	public Map<String, Object> findChildIndustry(int visitId,
   			Map<String, Object> condition) throws ServiceException {
   		// TODO Auto-generated method stub
   		logger.info("findChildIndustry method start");
           if (logger.isDebugEnabled()) {
               logger.debug("visitId:"+visitId);
               logger.debug("condition:"+condition);
           }
           Map<String, Object> result = new HashMap<String,Object>();
        List<Map<String,Object>> list = null;
           try {
               list = sysIndustryBusiness.findChildIndustry(visitId,condition);
               result.putAll(condition);
               result.put("LIST", list);
           } catch (Exception e) {
               e.printStackTrace();
               logger.error(e);
           }
           logger.info("findChildIndustry method end");
           return result;
   	}
    @Override
   	public Map<String, Object> findByIndustry(int visitId,
   			Map<String, Object> condition) throws ServiceException {
   		logger.info("findByIndustry method start");
   		if(logger.isDebugEnabled()){
   			logger.debug("args:visitId="+visitId);
   			logger.debug("condition："+condition);
   		}
   		Map<String, Object> result = new HashMap<String,Object>();
   		List<Map<String , Object>> list=null;
   		try {
   			list = sysIndustryBusiness.findByIndustry(visitId, condition);
   			result.put("LIST", list);
   		} catch (Exception e) {
   			e.printStackTrace();
   			logger.error(e);
   		}
   		logger.info("findByIndustry method end");
   		return result;
   	}
    @Override
	public Map<String, Object> findFirstIndustry(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findFirstIndustry method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = sysIndustryBusiness.findFirstIndustry(visitId, condition);
				result.put("LIST", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findGroupList method end"); 
		return result;
	}

   }