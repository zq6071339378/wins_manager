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

import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.TestMapper;

/**
 * @author zhaizhanpo
 *
 */
@Repository("testBusiness")
public class TestBusinessImp implements TestBusiness{

	private static Logger logger = Logger.getLogger(TestBusinessImp.class.getName());
	@Autowired
	private TestMapper testMapper;
	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#insertInfo(java.util.Map)
	 */
	@Override
	public int insertInfo(Map<String, Object> condition)
			throws BusinessException {
		logger.info(TestBusinessImp.class.getName()+" insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result=0;//方法未执行
        try {
        	if(condition!=null){
        		testMapper.insertInfo(condition);
        		  if(condition.containsKey("activity_id")){
        			  result = Integer.valueOf(condition.get("activity_id").toString());
        		  }else{
        			  result = -2;//参数错误
        		  }
        	}
        } catch (MapperException e) {
        	result=-1;//方法异常
            e.printStackTrace();
            logger.error(e);
        }
        if (logger.isDebugEnabled()){
            logger.debug("result:"+result);
        }
        logger.info(TestBusinessImp.class.getName()+" insertInfo method end");
       
        return result;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#deleteById(int, int)
	 */
	@Override
	public int deleteById(int visitId, int id) throws BusinessException {
		logger.info(TestBusinessImp.class.getName()+" deleteById start");
		if(logger.isDebugEnabled()){
			logger.debug("id:"+id);
		}
		int result = 0;
		try {
			if(visitId>0 && id>0){
				result = testMapper.deleteById(id);
			}else{
				result= -2;
			}
				
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			result=-1;
		}
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		logger.info(TestBusinessImp.class.getName()+" deleteById end");
		return result;
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
		logger.info(TestBusinessImp.class.getName()+" getInfo start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("id:"+id);
		}
		Map<String, Object> result =null;
		try {
			if(id>0){
				result = testMapper.getInfo(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info(TestBusinessImp.class.getName()+" getInfo end");
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
		logger.info(TestBusinessImp.class.getName()+" findByCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = testMapper.findByCondition(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(TestBusinessImp.class.getName()+" findByCondition end");
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#updateInfo(int, java.util.Map, int)
	 */
	@Override
	public boolean updateInfo(int visitId, Map<String, Object> condition, int id)
			throws BusinessException {
		logger.info(TestBusinessImp.class.getName()+" updateInfo start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		boolean  result = false;
		try {
			if(id>0 && condition!=null){
				testMapper.updateInfo(condition);
				result =true;
			}else{
				result =false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=false;
		}
		logger.info(TestBusinessImp.class.getName()+" updateInfo end");
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		return result;
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
		logger.info(TestBusinessImp.class.getName()+" countByCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result = 0;
		try {
			result = testMapper.countByCondition(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(TestBusinessImp.class.getName()+" countByCondition end");
		return result;
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
