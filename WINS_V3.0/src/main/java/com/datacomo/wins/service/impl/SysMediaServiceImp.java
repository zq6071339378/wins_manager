package com.datacomo.wins.service.impl;

import com.datacomo.wins.business.SysMediaBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysMediaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangxiongbin on 2016/9/22.
 */
@Service("sysMediaService")
public class SysMediaServiceImp implements SysMediaService {
    private static Logger logger = Logger.getLogger(SysMediaServiceImp.class.getName());

    @Autowired
    private SysMediaBusiness sysMediaBusiness;
    
    @Override
	public Map<String, Object> searchMedia(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("sysCityList method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
        try {
            list = sysMediaBusiness.searchMedia(visitId,condition);
            result.putAll(condition);
            result.put("LIST", list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("sysCityList method end");
        return result;
	}
	
    //行业标签URL价格查询
    @Override
   	public Map<String, Object> searchPrice(int visitId,
   			Map<String, Object> condition) throws ServiceException {
   		// TODO Auto-generated method stub
   		logger.info("searchPrice method start");
           if (logger.isDebugEnabled()) {
               logger.debug("visitId:"+visitId);
               logger.debug("condition:"+condition);
           }
           Map<String, Object> result = new HashMap<String,Object>();
        List<Map<String,Object>> list = null;
           try {
               list = sysMediaBusiness.searchPrice(visitId,condition);
               result.putAll(condition);
               result.put("LIST", list);
           } catch (Exception e) {
               e.printStackTrace();
               logger.error(e);
           }
           logger.info("searchPrice method end");
           return result;
   	}

    @Override
    public List<Map<String, Object>> findAllMedia(int visitId, Map<String, Object> condition){
        logger.info("findAllMedia method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        List<Map<String,Object>> result = null;
        try {
            result = sysMediaBusiness.searchMedia(visitId,condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("findAllMedia method end");
        return result;
    }

    @Override
    public Map<String, Object> findMedilInfo(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("findMedilInfo method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result=null;
        try {
            result = sysMediaBusiness.getInfoByCondition(visitId,condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("findMedilInfo method end");
        return result;
    }


}
