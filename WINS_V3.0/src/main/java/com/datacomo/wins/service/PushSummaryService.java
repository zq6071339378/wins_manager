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
 * Service 接口类
 * @author gongkaihui
 *
 */
public interface PushSummaryService extends BaseService{

	/**
	 * 根据条件获取推送概览基本信息
	 * @param condition
	 * @return
	 */
	public Map<String,Object> getPushBaseDataByCondition(int visitId,Map<String,Object> condition) throws ServiceException;
	
	public Map<String,Object> findPushAndShowAndClickNumByCondition(int visitId,Map<String,Object> condition) throws ServiceException;
	
	
}
