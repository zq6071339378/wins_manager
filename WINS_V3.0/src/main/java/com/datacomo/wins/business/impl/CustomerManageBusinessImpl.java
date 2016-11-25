package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.CustomerManageBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.SysCustomerMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/10/12.
 */
@Repository("customerManageBusiness")
public class CustomerManageBusinessImpl implements CustomerManageBusiness {
    private static Logger logger = Logger.getLogger(CustomerManageBusinessImpl.class.getName());
    @Autowired
    private SysCustomerMapper sysCustomerMapper;
    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result = sysCustomerMapper.insertInfo(condition);
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
            result=sysCustomerMapper.deleteById(id);
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
            result = sysCustomerMapper.getInfoByCondition(condition);
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
            result=sysCustomerMapper.findByCondition(condition);
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
        logger.info("updateInfo menthod start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId"+visitId);
            logger.debug("id"+id);
            logger.debug("condition"+condition);
        }
        boolean result=false;
        try {
            sysCustomerMapper.updateInfo(condition);
            result=true;
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateInfo method end");
        return result;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int countByCondition(int visitId, Map<String, Object> condition) {
        logger.info("countByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysCustomerMapper.countByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("countByCondition method end");
        return result;
    }

    @Override
    public int isExist(Map<String, Object> condition) {
        return 0;
    }
    @Override
  	public List<Map<String, Object>> searchCustomer(int visitId,
  			Map<String, Object> condition) throws BusinessException {
  		// TODO Auto-generated method stub
  		 logger.info("sysCityList method start");
  	        if (logger.isDebugEnabled()) {
  	            logger.debug("visitId:"+visitId);
  	            logger.debug("condition:"+condition);
  	        }
  	        List<Map<String, Object>> result =null;
  	        try {
  	            result = sysCustomerMapper.searchCustomer(condition);
  	        } catch (Exception e) {
  	            e.printStackTrace();
  	            logger.error(e);
  	            return null;
  	        }
  	        logger.info("sysCityList method end");
  	        return result;
  	}

    @Override
    public int deleteInfoById(int id) throws BusinessException {
        logger.info("deleteInfoById method start");
        if (logger.isDebugEnabled()){
            logger.debug("id"+id);
        }
        int result=0;
        try {
            result=sysCustomerMapper.deleteInfoById(id);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("deleteInfoById method end");
        return result;
    }

    @Override
    public int insertTradeInfo(Map<String, Object> condition) {
        logger.info("insertTradeInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result = sysCustomerMapper.insertTradeInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertTradeInfo method end");
        return result;
    }

    @Override
    public List<Map<String, Object>> findAllByCondition(Map<String, Object> condition) throws BusinessException {
        logger.info("findAllByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition"+condition);
        }
        List<Map<String,Object>> result=null;
        try {
            result=sysCustomerMapper.findAllByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("findAllByCondition method end");
        return result;
    }

    @Override
    public int countAllByCondition(Map<String, Object> condition) throws BusinessException {
        logger.info("countAllByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysCustomerMapper.countAllByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("countAllByCondition method end");
        return result;
    }

    @Override
    public List<Map<String, Object>> selectAllInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("selectAllInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition"+condition);
        }
        List<Map<String,Object>> result=null;
        try {
            result=sysCustomerMapper.selectAllInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("selectAllInfo method end");
        return result;
    }

    @Override
    public int countAllInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("countAllInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysCustomerMapper.countAllInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("countAllInfo method end");
        return result;
    }
}
