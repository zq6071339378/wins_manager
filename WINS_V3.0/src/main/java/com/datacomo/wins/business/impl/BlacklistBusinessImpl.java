package com.datacomo.wins.business.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datacomo.wins.business.BlacklistBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.BlacklistMapper;
/**
 * 
 * @author liwenjie
 *
 */
@Repository(value="blacklistBusiness")
public class BlacklistBusinessImpl implements BlacklistBusiness {
	private static Logger logger = Logger.getLogger(BlacklistBusinessImpl.class.getName());
	@Autowired
	private BlacklistMapper blacklistMapper;

	@Override
	public int insertInfo(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int visitId, int id) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCondition(int visitId, Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> getInfo(int visitId, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getInfoByCondition(int visitId,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findByCondition(int visitId,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateInfo(int visitId, Map<String, Object> condition, int id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return false;
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
	public List<Map<String, Object>> userBlacklist(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(BlacklistBusinessImpl.class.getName()+" userBlacklist start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = blacklistMapper.userBlacklist(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(BlacklistBusinessImpl.class.getName()+" userBlacklist end");
		return result;
	}

	@Override
	public int userBlacklistcount(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result = blacklistMapper.userBlacklistcount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> urlBlacklist(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(BlacklistBusinessImpl.class.getName()+" urlBlacklist start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = blacklistMapper.urlBlacklist(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(BlacklistBusinessImpl.class.getName()+" urlBlacklist end");
		return result;
	}

	@Override
	public int urlBlacklistcount(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result = blacklistMapper.urlBlacklistcount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delUser(int visitId, int userId) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(BlacklistBusinessImpl.class.getName()+" delUser start");
		if(logger.isDebugEnabled()){
			logger.debug("userId:"+userId);
		}
		int result =0;
		try {
			result = blacklistMapper.delUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(BlacklistBusinessImpl.class.getName()+" delUser end");
		return result;
	}

	@Override
	public int delUrl(int visitId, int urlId) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(BlacklistBusinessImpl.class.getName()+" delUrl start");
		if(logger.isDebugEnabled()){
			logger.debug("userId:"+urlId);
		}
		int result =0;
		try {
			result = blacklistMapper.delUrl(urlId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(BlacklistBusinessImpl.class.getName()+" delUrl end");
		return result;
	}

	@Override
	public List<Map<String, Object>> showPolicy(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(BlacklistBusinessImpl.class.getName()+" showPolicy start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = blacklistMapper.showPolicy(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(BlacklistBusinessImpl.class.getName()+" showPolicy end");
		return result;
	}

	@Override
	public int insertBlackUser(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
        logger.info("insertBlackUser method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result=0;
        try {
            result = blacklistMapper.insertBlackUser(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("insertBlackUser method end");
        return result;
	}

	@Override
	public int insertBlackUrl(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
        logger.info("insertBlackUrl method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result=0;
        try {
            result = blacklistMapper.insertBlackUrl(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("insertBlackUrl method end");
        return result;
	}

	@Override
	public int insertBlackUsers(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
        logger.info("insertBlackUsers method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result=0;
        try {
            result = blacklistMapper.insertBlackUsers(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("insertBlackUsers method end");
        return result;
	}

	@Override
	public int insertBlackUrls(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
        logger.info("insertBlackUrls method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result=0;
        try {
            result = blacklistMapper.insertBlackUrls(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("insertBlackUrls method end");
        return result;
	}

	@Override
	public int getBlackUrlcount(Map<String, Object> condition){
		logger.info("getBlackUrlcount method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition: "+condition);
		}
		int result = 0;
		try {
			result = blacklistMapper.getBlackUrlcount(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("getBlackUrlcount method start");
		return result;
	}

}
