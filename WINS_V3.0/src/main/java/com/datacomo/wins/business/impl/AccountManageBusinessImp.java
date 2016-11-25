package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.AccountManageBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.AccountManageMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
@Repository("accountManageBusiness")
public class AccountManageBusinessImp implements AccountManageBusiness {
    private static Logger logger = Logger.getLogger(AccountManageBusinessImp.class.getName());

    @Autowired
    private AccountManageMapper accountManageMapper;

    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result = accountManageMapper.insertInfo(condition);
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
        int code=0;
        try {
            code=accountManageMapper.deleteById(id);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteById method end");
        return code;
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
            result = accountManageMapper.getInfoByCondition(condition);
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
            result=accountManageMapper.findByCondition(condition);
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
            accountManageMapper.updateInfo(condition);
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
            result = accountManageMapper.countByCondition(condition);
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
    public int getCountByCondition(Map<String, Object> condition) throws BusinessException {
        logger.info("getCountByCondition method start");
        int result = 0;
        try {
            result = accountManageMapper.getCountByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("getCountByCondition method end");
        return result;
    }
    
    @Override
    public List<Map<String,Object>> getInfoByRoleId(int Role_Id) throws BusinessException {
        logger.info("getInfoByRoleId method start");
        List<Map<String,Object>> result = null;
        try {
            result = accountManageMapper.getInfoByRoleId(Role_Id);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("getInfoByRoleId method end");
        return result;
    }

    @Override
    public List<Map<String,Object>> getAllInfo(int Show_Status) throws BusinessException {
        logger.info("getAllInfo method start");
        List<Map<String,Object>> result = null;
        try {
            result = accountManageMapper.getAllInfo(Show_Status);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("getAllInfo method end");
        return result;
    }

    @Override
    public Map<String, Object> getBelongCityInfo(Map<String, Object> condition) throws MapperException {
        logger.info("getBelongCityInfo method start");
        if (logger.isDebugEnabled()) {
            logger.debug("condition:"+condition);
        }
        Map<String,Object> result=null;
        try {
            result = accountManageMapper.getBelongCityInfo(condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getBelongCityInfo method end");
        return result;
    }


}
