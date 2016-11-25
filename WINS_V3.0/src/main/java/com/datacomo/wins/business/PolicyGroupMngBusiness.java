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

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
/**
 * @author gongkaihui
 *
 */
public interface PolicyGroupMngBusiness  extends BaseBusiness{
	/**
	 * 条件获取成员列表信息
	 * @param condition
	 * @return
	 */
	public List<Map<String,Object>> findGroupMemByGroupId(int visitId,Map<String,Object> condition);
	
	public int insertGroupMember(Map<String,Object> condition) throws BusinessException;
	
	public int deleteGroupById(int id) throws BusinessException;
	
	public int insertData(Map<String,Object> condition) throws BusinessException;
	
	public void updateGroupById(Map<String, Object> condition) throws BusinessException;
	
}
