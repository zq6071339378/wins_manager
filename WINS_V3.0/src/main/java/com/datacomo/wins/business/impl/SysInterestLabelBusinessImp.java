package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.SysInterestLabelBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.SysInterestLabelMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/22.
 */
@Repository("sysInterestLabelBusiness")
public class SysInterestLabelBusinessImp implements SysInterestLabelBusiness {
    private static Logger logger = Logger.getLogger(SysInterestLabelBusinessImp.class.getName());

    @Autowired
    private SysInterestLabelMapper sysInterestLabelMapper;

    @Override
    public int deleteAll() throws BusinessException {
        logger.info("deleteAll method start");
        int result = 0;
        try {
            result = sysInterestLabelMapper.deleteAll();
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("deleteAll method end");
        return result;
    }


    @Override
    public int deleteAllInfo() throws BusinessException {
        logger.info("deleteAllInfo method start");
        int result = 0;
        try {
            result = sysInterestLabelMapper.deleteAllInfo();
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("deleteAllInfo method end");
        return result;
    }

    @Override
    public int insertOneInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertOneInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result = sysInterestLabelMapper.insertOneInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertOneInfo method end");
        return result;
    }

    @Override
    public int insertInfo(Map<String, Object> condition){
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result = sysInterestLabelMapper.insertInfo(condition);
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
            result=sysInterestLabelMapper.findByCondition(condition);
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
}
