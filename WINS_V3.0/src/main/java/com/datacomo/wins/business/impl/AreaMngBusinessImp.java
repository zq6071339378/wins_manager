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

import com.datacomo.wins.business.AreaMngBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.AreaMngMapper;

/**
 * @author gongkaihui
 *
 */
@Repository("areaMngBusiness")
public class AreaMngBusinessImp implements AreaMngBusiness{

	private static Logger logger = Logger.getLogger(AreaMngBusinessImp.class.getName());
	@Autowired
	private AreaMngMapper areaMngMapper;
	
	@Override
	public int insertInfo(Map<String, Object> condition)
			throws BusinessException {
		logger.info(AreaMngBusinessImp.class.getName()+" insertInfo start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		try {
			areaMngMapper.insertInfo(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(AreaMngBusinessImp.class.getName()+" insertInfo end");
		return 0;
		
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#deleteById(int, int)
	 */
	@Override
	public int deleteById(int visitId, int id) throws BusinessException {
		logger.info(AreaMngBusinessImp.class.getName()+" deleteById start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
		}
		try {
			areaMngMapper.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(AreaMngBusinessImp.class.getName()+" deleteById end");
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
		logger.info(AreaMngBusinessImp.class.getName()+" getInfo start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("id:"+id);
		}
		Map<String, Object> result=new HashMap<String, Object>();
		try {
			if(id>0){
				result = areaMngMapper.getInfo(id);
			}else{
				result=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(AreaMngBusinessImp.class.getName()+" getInfo end");
		
		return result;
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
		logger.info(AreaMngBusinessImp.class.getName()+" findByCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = areaMngMapper.findByCondition(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(AreaMngBusinessImp.class.getName()+" findByCondition end");
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#updateInfo(int, java.util.Map, int)
	 */
	@Override
	public boolean updateInfo(int visitId, Map<String, Object> condition, int id)
			throws BusinessException {
		try {
			areaMngMapper.updateInfo(condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		// TODO Auto-generated method stub
		try {
			return areaMngMapper.countByCondition(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#isExist(java.util.Map)
	 */
	@Override
	public int isExist(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

}
