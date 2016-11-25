package com.datacomo.wins.business.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datacomo.wins.business.SysTaskBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.SysTaskMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by duanlinzhuo on 2016/3/16.
 */
@Repository("sysTaskBusiness")
public class SysTaskBusinessImpl implements SysTaskBusiness {
    private static Logger logger=Logger.getLogger(SysTaskBusinessImpl.class.getName());
    @Autowired
    private SysTaskMapper sysTaskMapper;

    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result=0;
        try {
            result = sysTaskMapper.insertInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
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
            code=sysTaskMapper.deleteById(id);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteById method  end");
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
        return null;
    }

    @Override
    public List<Map<String, Object>> findByCondition(int visitId, Map<String, Object> condition) {
        logger.info("findByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        List<Map<String,Object>> result=null;
        try {
            result=sysTaskMapper.findByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
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
            result = sysTaskMapper.countByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("countByCondition method end");
        return result;
    }

    @Override
    public int isExist(Map<String, Object> condition) {
        return 0;
    }

    @Override
    public int getNotReadCountByCondition(Map<String, Object> condition) throws BusinessException {
        logger.info("getNotReadCountByCondition method start");
        if(logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysTaskMapper.getNotReadCountByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("getNotReadCountByCondition method end");
        return result;
    }

    @Override
    public List<Map<String, Object>> getNotReadInfoByCondition(Map<String, Object> condition) throws BusinessException {
        logger.info("getNotReadCountByCondition method start");
        if(logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        List<Map<String,Object>> result = null;
        try {
            result = sysTaskMapper.getNotReadInfoByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getNotReadCountByCondition method end");
        return result;
    }

    @Override
    public int deleteByUserId(int id) throws BusinessException {
        logger.info("deleteByUserId method start");
        if (logger.isDebugEnabled()){
            logger.debug("id:"+id);
        }
        int result = 0;
        try {
            result = sysTaskMapper.deleteByUserId(id);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteByUserId method end");
        return result;
    }

	@Override
	public Map<String, Object> getMonitorAlarmInfo() throws BusinessException {
		logger.info("getMonitorAlarmInfo method start");
        Map<String,Object> result = null;
        try {
        	result = sysTaskMapper.getMonitorAlarmInfo();
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("getMonitorAlarmInfo method end");
        return result;
	}

	@Override
	public int updateMonitorAlarmInfo(Map<String,Object> condition) throws BusinessException {
		logger.info("updateMonitorAlarmInfo method start");
        int result = 0;
        try {
        	result = sysTaskMapper.updateMonitorAlarmInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("updateMonitorAlarmInfo method end");
        return result;
	}

	@Override
	public int getUserId(Map<String, Object> condition)
			throws BusinessException {
		logger.info("getUserId method start");
		int result = 0;
		try {
			result = sysTaskMapper.getUserId(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("getUserId method end");
		return result;
	}

	@Override
	public List<Map<String, Object>> getServerInfo() throws BusinessException {
		logger.info("getServerInfo method start");
		List<Map<String, Object>> result = null;
		try {
			result = sysTaskMapper.getServerInfo();
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("getServerInfo method end");
		return result;
	}
}
