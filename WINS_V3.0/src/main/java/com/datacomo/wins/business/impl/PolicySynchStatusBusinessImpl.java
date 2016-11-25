package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.PolicySynchStatusBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.PolicySynchStatusMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/7/6.
 */
@Repository("policySynchStatusBusiness")
public class PolicySynchStatusBusinessImpl implements PolicySynchStatusBusiness {
    private static Logger logger = Logger.getLogger(PolicySynchStatusBusinessImpl.class.getName());
    @Autowired
    private PolicySynchStatusMapper policySynchStatusMapper;
    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        return 0;
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
            result = policySynchStatusMapper.getInfoByCondition(condition);
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
        logger.info("updateInfo menthod start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId"+visitId);
            logger.debug("id"+id);
            logger.debug("condition"+condition);
        }
        boolean result=false;
        try {
            policySynchStatusMapper.updateInfo(condition);
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
        return 0;
    }

    @Override
    public int isExist(Map<String, Object> condition) {
        return 0;
    }
}
