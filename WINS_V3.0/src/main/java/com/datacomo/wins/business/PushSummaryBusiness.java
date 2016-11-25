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
package com.datacomo.wins.business;

import java.util.List;
import java.util.Map;

/**
 * @author gongkaihui
 *
 */
public interface PushSummaryBusiness  extends BaseBusiness{
	
	/**
	 * 获取推送概览单个信息
	 * @param condition
	 * @return
	 */
	public Map<String,Object> getPushBaseDataByCondition(int visitId,Map<String,Object> condition);
	
	/**
	 * 条件获取推送用户数，展现用户数，点击用户数列表信息
	 * @param condition
	 * @return
	 */
	public List<Map<String,Object>> findPushAndShowAndClickNumByCondition(int visitId,Map<String,Object> condition);
	
}
