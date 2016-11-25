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

import java.util.Map;

import com.datacomo.wins.exception.ServiceException;

/**
 * Service 接口类(注意此接口中方法名字要见名知意)
 * @author zhaizhanpo
 *
 */
public interface TestService extends BaseService{

	
	/**
	 * 根据ID获取单个Test实体
	 * @param visitId
	 * @param condition
	 * @return
	 */
	public int testInfo(int visitId,int id);
	
	/**
	 * 根据ID删除单个Test实体
	 * @param visitId
	 * @param id
	 * @return
	 */
	public int deleteTestById(int visitId,int id);
	
	/**
	 * 条件删除Test实体列表
	 * @param visitId
	 * @param condition
	 * @return
	 */
	public int deleteTestByCondtion(int visitId,Map<String,Object> condition) throws ServiceException;
	
	/**
	 * 根据条件获取Test列表
	 * @param condition
	 * @return
	 */
	public Map<String,Object> findTestsByCondtion(int visitId,int id,Map<String,Object> condition) throws ServiceException;
	
	
}
