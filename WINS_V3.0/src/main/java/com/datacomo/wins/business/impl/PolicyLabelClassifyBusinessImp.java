package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.PolicyLabelClassifyBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.PolicyLabelClassifyMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/22.
 */
@Repository("policyLabelClassifyBusiness")
public class PolicyLabelClassifyBusinessImp implements PolicyLabelClassifyBusiness {
    private static Logger logger = Logger.getLogger(PolicyLabelClassifyBusinessImp.class.getName());

    @Autowired
    private PolicyLabelClassifyMapper policyLabelClassifyMapper;


    @Override
    public List<Map<String, Object>> findAllInfo() {
        logger.info("findAll method start");
        List<Map<String,Object>> result = null;
        try {
            result = policyLabelClassifyMapper.findAllInfo();
        } catch (MapperException e) {
            e.printStackTrace();
            return null;
        }
        logger.info("findAll method end");
        return result;
    }

    @Override
    public int insertAllInfo(Map<String, Object> condition) {
        logger.info("insertAllInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result = policyLabelClassifyMapper.insertAllInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertAllInfo method end");
        return result;
    }

    @Override
    public int deleteAllInfo() {
        logger.info("deleteAllInfo method start");
        int result = 0;
        try {
            result = policyLabelClassifyMapper.deleteAllInfo();
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteAllInfo method end");
        return result;
    }

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
        return null;
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
