package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.SysLogRecordBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.SysLogRecordMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
@Repository("sysLogRecordBusiness")
public class SysLogRecordBusinessImp implements SysLogRecordBusiness {
    private static Logger logger = Logger.getLogger(SysLogRecordBusinessImp.class.getName());
    @Autowired
    private SysLogRecordMapper sysLogRecordMapper;

    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result = sysLogRecordMapper.insertInfo(condition);
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
            result=sysLogRecordMapper.findByCondition(condition);
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
        logger.info("countByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysLogRecordMapper.countByCondition(condition);
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
    public int insertLogInfo(int userId, String logTitle, int logType, String logCont, String logIp, String logTime) throws BusinessException {
        logger.info("insertLogInfo method start");
        Map<String,Object> condition = new HashMap<>();
        condition.put("userId",userId);
        condition.put("logTitle",logTitle);
        condition.put("logType",logType);
        condition.put("logCont",logCont);
        condition.put("logIp",logIp);
        condition.put("logTime",logTime);
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysLogRecordMapper.insertInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertLogInfo method start");
        return result;
    }

}
