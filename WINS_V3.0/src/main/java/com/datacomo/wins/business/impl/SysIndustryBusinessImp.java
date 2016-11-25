package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.SysIndustryBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.SysIndustryLabelMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
@Repository("sysIndustryBusiness")
public class SysIndustryBusinessImp implements SysIndustryBusiness {
    private static Logger logger = Logger.getLogger(SysIndustryBusinessImp.class.getName());
    @Autowired
    private SysIndustryLabelMapper sysIndustryLabelMapper;

    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result = sysIndustryLabelMapper.insertInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertInfo method end");
        return result;
    }

    @Override
    public int deleteById(int visitId, int id) throws BusinessException {
        return 0;
    }

    @Override
    public int deleteByCondition(int visitId, Map<String, Object> condition) throws BusinessException {
        return 0;
    }

    @Override
    public Map<String, Object> getInfo(int visitId, int id) {
        return null;
    }

    @Override
    public Map<String, Object> getInfoByCondition(int visitId, Map<String, Object> condition) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findByCondition(int visitId, Map<String, Object> condition) {
        logger.info("findByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition"+condition);
        }
        List<Map<String,Object>> result=null;
        try {
            result=sysIndustryLabelMapper.findByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("findByCondition method end");
        return result;
    }

    @Override
    public boolean updateInfo(int visitId, Map<String, Object> condition, int id) throws BusinessException {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int countByCondition(int visitId, Map<String, Object> condition) {
        return 0;
    }

    @Override
    public int isExist(Map<String, Object> condition) {
        return 0;
    }

    @Override
    public int deleteAll() throws BusinessException {
        logger.info("deleteAll method start");
        int result = 0;
        try {
            result = sysIndustryLabelMapper.deleteAll();
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteAll method end");
        return result;
    }
    
    @Override
    public List<Map<String, Object>> findAll() throws BusinessException {
        logger.info("findAll method start");
        List<Map<String,Object>> result = null;
        try {
            result = sysIndustryLabelMapper.findAll();
        } catch (MapperException e) {
            e.printStackTrace();
            return null;
        }
        logger.info("findAll method end");
        return result;
    }
    @Override
    public List<Map<String, Object>> findByIndustry(int visitId,
			Map<String, Object> condition) throws BusinessException {
        logger.info("findAll method start");
        List<Map<String , Object>> result = null;
        try {
            result = sysIndustryLabelMapper.findByIndustry(condition);
        } catch (MapperException e) {
            e.printStackTrace();
        
        }
        logger.info("findAll method end");
        return result;
    }
    @Override
	public List<Map<String, Object>> findFirstIndustry(int visitId,
			Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" findFirstIndustry start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		 List<Map<String, Object>> result =null;
		try {
			result = sysIndustryLabelMapper.findFirstIndustry(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" findFirstIndustry end");
		return result;
	
    }
    @Override
  	public List<Map<String, Object>> findChildIndustry(int visitId,
  			Map<String, Object> condition) throws BusinessException {
  		// TODO Auto-generated method stub
  		 logger.info("findChildIndustry method start");
  	        if (logger.isDebugEnabled()) {
  	            logger.debug("visitId:"+visitId);
  	            logger.debug("condition:"+condition);
  	        }
  	        List<Map<String, Object>> result =null;
  	        try {
  	            result = sysIndustryLabelMapper.findChildIndustry(condition);
  	        } catch (Exception e) {
  	            e.printStackTrace();
  	            logger.error(e);
  	            return null;
  	        }
  	        logger.info("findChildIndustry method end");
  	        return result;
  	}
}
