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

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datacomo.wins.business.NavInfoBusiness;
import com.datacomo.wins.business.PushSummaryBusiness;
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.NavInfoMapper;
import com.datacomo.wins.mapper.PushSummaryMapper;
import com.datacomo.wins.mapper.TestMapper;

/**
 * @author gongkaihui
 *
 */
@Repository("pushSummaryBusiness")
public class PushSummaryBusinessImp implements PushSummaryBusiness{

	private static Logger logger = Logger.getLogger(PushSummaryBusinessImp.class.getName());
	@Autowired
	private PushSummaryMapper pushSummaryMapper;
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
	public Map<String, Object> getPushBaseDataByCondition(int visitId,
			Map<String, Object> condition) {
		  logger.info("getPushBaseDataByCondition method start");
	        if (logger.isDebugEnabled()){
	            logger.debug("visitId:"+visitId);
	            logger.debug("condition:"+condition);
	        }
	        Map<String,Object> result = null;
	        try {
	            result = pushSummaryMapper.getPushBaseDataByCondition(condition);
	        }
	        catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				result=null;
			}
	        logger.info("getPushBaseDataByCondition method start");
	        return result;
	}
	@Override
	public List<Map<String, Object>> findPushAndShowAndClickNumByCondition(
			int visitId, Map<String, Object> condition) {
		logger.info(PushSummaryBusinessImp.class.getName()+" findPushAndShowAndClickNumByCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = pushSummaryMapper.findPushAndShowAndClickNumByCondition(condition);
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
			for(int i=0;i<result.size();i++){
				Map<String, Object> map=result.get(i);
			System.out.println(	map.get("policyName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PushSummaryBusinessImp.class.getName()+" findPushAndShowAndClickNumByCondition end");
		
		return result;
	}

}
