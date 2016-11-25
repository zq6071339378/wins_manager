package com.datacomo.wins.business.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datacomo.wins.business.AdnetworkBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.AdnetworkMapper;

@Repository("AdnetworkBusiness")
public class AdnetworkBusinessImpl  implements  AdnetworkBusiness{
    private static Logger logger = Logger.getLogger(AdnetworkBusinessImpl.class.getName());
    @Autowired
    private AdnetworkMapper adnetworkMapper;
	@Override
	public int insertInfo(Map<String, Object> condition) throws BusinessException {
	
		        logger.info("insertInfo method start");
		        if (logger.isDebugEnabled()){
		            logger.debug("condition:"+condition);
		        }
		        int result= 0;
		        try {
		            result = adnetworkMapper.insertInfo(condition);
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
        logger.info("deleteById method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId"+visitId);
            logger.debug("id"+id);
        }
        int result=0;
        try {
            result=adnetworkMapper.deleteById(id);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteById method end");
        return result;
    }

	@Override
	public int deleteByCondition(int visitId, Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> getInfo(int visitId, int id) {
		// TODO Auto-generated method stub
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
	            result = adnetworkMapper.getInfoByCondition(condition);
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
	        logger.info("findByCondition method start");
	        if (logger.isDebugEnabled()){
	            logger.debug("visitId:"+visitId);
	            logger.debug("condition"+condition);
	        }
	        List<Map<String,Object>> result=null;
	        try {
	            result=adnetworkMapper.findByCondition(condition);
	        } catch (MapperException e) {
	            e.printStackTrace();
	            logger.error(e);
	            return null;
	        }
	        logger.info("findByCondition method end");
	        return result;
	    }
	

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByCondition(int visitId, Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isExist(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, Object>> searchAdnetwork(int visitId, Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
 		 logger.info("sysCityList method start");
 	        if (logger.isDebugEnabled()) {
 	            logger.debug("visitId:"+visitId);
 	            logger.debug("condition:"+condition);
 	        }
 	        List<Map<String, Object>> result =null;
 	        try {
 	            result = adnetworkMapper.searchAdnetwork(condition);
 	        } catch (Exception e) {
 	            e.printStackTrace();
 	            logger.error(e);
 	            return null;
 	        }
 	        logger.info("sysCityList method end");
 	        return result;
 	}
	
	 @Override
	    public boolean updateInfo(int visitId, Map<String, Object> condition, int id) throws BusinessException {
	        logger.info("updateInfo menthod start");
	        if (logger.isDebugEnabled()){
	            logger.debug("visitId"+visitId);
	            logger.debug("id"+id);
	            logger.debug("condition"+condition);
	        }
	        boolean result=false;
	        try {
	        	adnetworkMapper.updateInfo(condition);
	            result=true;
	        } catch (MapperException e) {
	            e.printStackTrace();
	            logger.error(e);
	            return false;
	        }
	        logger.info("updateInfo method end");
	        return result;
	    }
}
