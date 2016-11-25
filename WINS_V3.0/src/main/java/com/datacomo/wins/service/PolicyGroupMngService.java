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
package com.datacomo.wins.service;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;

/**
 * Service 接口类
 * @author gongkaihui
 *
 */
public interface PolicyGroupMngService extends BaseService{

	/**
	 * 根据条件获取群组列表
	 * @param condition
	 * @return
	 */
	public Map<String,Object> findGroupList(int visitId,int id,Map<String,Object> condition) throws ServiceException;
	/**
	 * 根据条件获取指定群组成员列表
	 * @param condition
	 * @return
	 */
	public Map<String,Object> findGroupMemByGroupId(int visitId,int id,Map<String,Object> condition) throws ServiceException;
	
	public int insertGroupMember(Map<String,Object> condition) throws ServiceException;
	
	public int insertInfo(Map<String,Object> condition) throws ServiceException;
	
	public int deleteGroupById(int id) throws ServiceException;
	
	public int deleteById(int id) throws ServiceException;
	
	public int insertData(Map<String, Object> condition) throws ServiceException;
	
	public void updateGroupById(Map<String, Object> condition) throws ServiceException;

	public int getGroupUsers(Map<String,Object> condition) throws ServiceException;
	
}
