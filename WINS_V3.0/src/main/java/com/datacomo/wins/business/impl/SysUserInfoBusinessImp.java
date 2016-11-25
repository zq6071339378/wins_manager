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
import com.datacomo.wins.business.SysUserInfoBusiness;
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.NavInfoMapper;
import com.datacomo.wins.mapper.SysUserInfoMapper;
import com.datacomo.wins.mapper.TestMapper;

/**
 * @author gongkaihui
 *
 */
@Repository("sysUserInfoBusiness")
public class SysUserInfoBusinessImp implements SysUserInfoBusiness{

	private static Logger logger = Logger.getLogger(SysUserInfoBusinessImp.class.getName());
	@Autowired
	private SysUserInfoMapper sysuerInfoMapper;
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
		logger.info(SysUserInfoBusinessImp.class.getName()+" getInfoByCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		Map<String,Object> result=null;
		try {
			result=sysuerInfoMapper.getInfoByCondition(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(SysUserInfoBusinessImp.class.getName()+" getInfoByCondition end");
		return result;
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
		  logger.info("updateInfo menthod start");
	        if (logger.isDebugEnabled()){
	            logger.debug("visitId"+visitId);
	            logger.debug("id"+id);
	            logger.debug("condition"+condition);
	        }
	        boolean result=false;
	        try {
	        	sysuerInfoMapper.updateInfo(condition);
	            result=true;
	        } catch (MapperException e) {
	            e.printStackTrace();
	            logger.error(e);
	            return false;
	        }
	        logger.info("updateInfo method end");
	        return result;
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
	

}
