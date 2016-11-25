package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.ActivityManageBusiness;
import com.datacomo.wins.business.IPSManageBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.PolicyActivityMapper;
import com.datacomo.wins.mapper.PolicyIPSMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/10/12.
 */
@Repository("iPSManageBusiness")
public class IPSManageBusinessImpl implements IPSManageBusiness {
    private static Logger logger = Logger.getLogger(IPSManageBusinessImpl.class.getName());
    @Autowired
    private PolicyIPSMapper policyIPSMapper;
    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result=policyIPSMapper.insertInfo(condition);
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
            result=policyIPSMapper.deleteById(id);
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
            result = policyIPSMapper.getInfoByCondition(condition);
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
            result=policyIPSMapper.findByCondition(condition);
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
            policyIPSMapper.updateInfo(condition);
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
        logger.info("count method start");
        int result=0;
        try {
            result=policyIPSMapper.count();
        } catch (MapperException e) {
            e.printStackTrace();
            return -1;
        }
        logger.info("count method end");
        return result;
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
            result = policyIPSMapper.countByCondition(condition);
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
    public int getMaxValue() throws BusinessException {
        logger.info("getMaxValue method start");
        int result=0;
        try {
            result=policyIPSMapper.getMaxValue();
        } catch (MapperException e) {
            e.printStackTrace();
            return -1;
        }
        logger.info("getMaxValue method end");
        return result;
    }
}
