package com.datacomo.wins.business.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datacomo.wins.business.ComplaintBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.ComplaintMapper;
/**
 * 
 * @author liwenjie
 *
 */
@Repository("complaintBusiness")
public class ComplaintBusinessImpl implements ComplaintBusiness {
	private static Logger logger = Logger.getLogger(PolicyinfoBusinessImpl.class.getName());
	@Autowired
	private ComplaintMapper complaintMapper;
	
	
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
	public List<Map<String, Object>> showComplaint(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(ComplaintBusinessImpl.class.getName()+" showComplaint start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = complaintMapper.showComplaint(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(ComplaintBusinessImpl.class.getName()+" showComplaint end");
		return result;
	}

	@Override
	public int showComplaintcount(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result = complaintMapper.showComplaintcount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> showPush(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(ComplaintBusinessImpl.class.getName()+" showPush start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = complaintMapper.showPush(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(ComplaintBusinessImpl.class.getName()+" showPush end");
		return result;
	}

	@Override
	public int showPushcount(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result = complaintMapper.showPushcount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> showClick(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(ComplaintBusinessImpl.class.getName()+" showClick start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = complaintMapper.showClick(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(ComplaintBusinessImpl.class.getName()+" showClick end");
		return result;
	}

	@Override
	public int showClickcount(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result = complaintMapper.showClickcount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> showOrder(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(ComplaintBusinessImpl.class.getName()+" showOrder start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = complaintMapper.showOrder(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(ComplaintBusinessImpl.class.getName()+" showOrder end");
		return result;
	}

	@Override
	public int showOrdercount(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result = complaintMapper.showOrdercount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
