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
public interface AreaMngBusiness  extends BaseBusiness{
	/**
	 * 条件获取列表信息
	 * @param condition
	 * @return
	 */
	public List<Map<String,Object>> findByCondition(int visitId,Map<String,Object> condition);
	
}
