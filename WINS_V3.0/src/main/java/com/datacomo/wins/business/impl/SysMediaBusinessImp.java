package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.SysMediaBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.SysMediaLabelMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/22.
 */

@Repository("sysMediaBusiness")
public class SysMediaBusinessImp implements SysMediaBusiness {
    private static Logger logger = Logger.getLogger(SysMediaBusinessImp.class.getName());

    @Autowired
    private SysMediaLabelMapper sysMediaLabelMapper;

    @Override
    public int deleteAll() throws BusinessException {
        logger.info("deleteAll method start");
        int result = 0;
        try {
            result = sysMediaLabelMapper.deleteAll();
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteAll method end");
        return result;
    }

    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= -2;
        try {
            result = sysMediaLabelMapper.insertInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("insertInfo method end");
        return result;
    }

    @Override
	public List<Map<String, Object>> searchMedia(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		 logger.info("sysCityList method start");
	        if (logger.isDebugEnabled()) {
	            logger.debug("visitId:"+visitId);
	            logger.debug("condition:"+condition);
	        }
	        List<Map<String, Object>> result =null;
	        try {
	            result = sysMediaLabelMapper.searchMedia(condition);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e);
	            return null;
	        }
	        logger.info("sysCityList method end");
	        return result;
	}

    //行业标签URL价格查询 
    @Override
  	public List<Map<String, Object>> searchPrice(int visitId,
  			Map<String, Object> condition) throws BusinessException {
  		// TODO Auto-generated method stub
  		 logger.info("sysCityList method start");
  	        if (logger.isDebugEnabled()) {
  	            logger.debug("visitId:"+visitId);
  	            logger.debug("condition:"+condition);
  	        }
  	        List<Map<String, Object>> result =null;
  	        try {
  	            result = sysMediaLabelMapper.searchPrice(condition);
  	        } catch (Exception e) {
  	            e.printStackTrace();
  	            logger.error(e);
  	            return null;
  	        }
  	        logger.info("sysCityList method end");
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
        logger.info("getInfoByCondition method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String,Object> result=null;
        try {
            result = sysMediaLabelMapper.getInfoByCondition(condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getInfoByCondition method end");
        return result;
    }

    @Override
    public List<Map<String, Object>> findByCondition(int visitId, Map<String, Object> condition) {
        return null;
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
}
