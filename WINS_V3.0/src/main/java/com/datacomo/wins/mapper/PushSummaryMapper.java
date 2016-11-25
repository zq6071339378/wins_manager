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
package com.datacomo.wins.mapper;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.MapperException;

/**
 * @author gongkaihui
 *
 */
public interface PushSummaryMapper extends BaseMapper {
	/**
	 * 获取推送概览基本指标数据
	 * @param condition
	 * @return
	 */
	public Map<String,Object> getPushBaseDataByCondition(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 条件获取推送用户数，展现用户数，点击用户数列表信息
	 * @param condition
	 * @return
	 */
	public List<Map<String,Object>> findPushAndShowAndClickNumByCondition(Map<String,Object> condition) throws MapperException;
}
