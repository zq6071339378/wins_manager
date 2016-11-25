/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2006-2016 DataComo Communications Technology INC.
 * 
 * This source file is a part of DSPV1.0 project. 
 * date: 2016-02-25
 *
 */
package com.datacomo.wins.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datacomo.wins.business.NavInfoBusiness;
import com.datacomo.wins.business.PolicyGroupMngBusiness;
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.NavInfoMapper;
import com.datacomo.wins.mapper.PolicyGroupMngMapper;
import com.datacomo.wins.mapper.SysLogRecordMapper;
import com.datacomo.wins.mapper.TestMapper;

/**
 * @author gongkaihui
 *
 */
@Repository("policyGroupMngBusiness")
public class PolicyGroupMngBusinessImp implements PolicyGroupMngBusiness{

	private static Logger logger = Logger.getLogger(PolicyGroupMngBusinessImp.class.getName());
	@Autowired
	private PolicyGroupMngMapper policyGroupMngMapper;
	@Autowired
	private SysLogRecordMapper sysLogRecordMapper;
	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#insertInfo(java.util.Map)
	 */
	@Override
	public int insertInfo(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" insertInfo start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int i=0;
		try {
			i=policyGroupMngMapper.insertInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" insertInfo end");
		
		return i;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#deleteById(int, int)
	 */
	@Override
	public int deleteById(int visitId, int id) throws BusinessException {
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" deleteById start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
		}
		try {
			policyGroupMngMapper.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" deleteById end");
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#deleteByCondition(int, java.util.Map)
	 */
	@Override
	public int deleteByCondition(int visitId, Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#getInfo(int, int)
	 */
	@Override
	public Map<String, Object> getInfo(int visitId, int id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#getInfoByCondition(int, java.util.Map)
	 */
	@Override
	public Map<String, Object> getInfoByCondition(int visitId,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#findByCondition(int, java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> findByCondition(int visitId,
			Map<String, Object> condition) {
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" findByCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyGroupMngMapper.findByCondition(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" findByCondition end");
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#updateInfo(int, java.util.Map, int)
	 */
	@Override
	public boolean updateInfo(int visitId, Map<String, Object> condition, int id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#count()
	 */
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#countByCondition(int, java.util.Map)
	 */
	@Override
	public int countByCondition(int visitId, Map<String, Object> condition) {
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" countByCondition start");
		int result=0;
		try {
			result= policyGroupMngMapper.countByCondition(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			return 0;
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" countByCondition end");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#isExist(java.util.Map)
	 */
	@Override
	public int isExist(Map<String, Object> condition) {
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" isExist start");
		int result=0;
		try {
			result= policyGroupMngMapper.isExist(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			return 0;
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" isExist end");
		return result;
	}

	@Override
	public List<Map<String, Object>> findGroupMemByGroupId(int visitId,
			Map<String, Object> condition) {
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" findGroupMemByGroupId start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyGroupMngMapper.findGroupMemByGroupId(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" findGroupMemByGroupId end");
		return result;
	}

	@Override
	public int insertGroupMember(Map<String, Object> condition) {
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" insertGroupMember start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int i=0;
		try {
			i=policyGroupMngMapper.insertGroupMember(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" insertGroupMember end");
		return i;
	}

	@Override
	public int deleteGroupById(int id) throws BusinessException {
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" deleteGroupById start");
		try {
			policyGroupMngMapper.deleteGroupById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" deleteGroupById end");
		return 0;
	}

	@Override
	public int insertData(Map<String, Object> condition) throws BusinessException {
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" add start");
		try {
			policyGroupMngMapper.insertData(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" add end");
		return 0;
	}

	@Override
	public void updateGroupById(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" updateGroupById start");
		try {
			policyGroupMngMapper.updateGroupById(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyGroupMngBusinessImp.class.getName()+" updateGroupById end");
		
	}
}
