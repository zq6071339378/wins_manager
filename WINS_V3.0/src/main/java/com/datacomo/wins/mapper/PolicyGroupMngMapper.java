/**
] * The contents of this file are subject to the terms
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
public interface PolicyGroupMngMapper extends BaseMapper {
	/**
	 * 条件获取列表信息
	 * @param condition
	 * @return
	 */
	public List<Map<String,Object>> findGroupMemByGroupId(Map<String,Object> condition) throws MapperException;
	
	public int insertGroupMember(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 删除群组管理
	 * @param id
	 * @return
	 */
	public int deleteGroupById(int id) throws MapperException;


	public int insertData(Map<String, Object> condition) throws MapperException;
	
	public void updateGroupById(Map<String, Object> condition) throws MapperException;
	
}
