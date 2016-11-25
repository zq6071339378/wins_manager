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
 * @author zhaizhanpo
 *
 */
public interface BaseMapper {
	/**
	 * 创建信息
	 * @param condition
	 * @return
	 */
	public int insertInfo(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 删除单个
	 * @param id
	 * @return
	 */
	public int deleteById(int id) throws MapperException;
	
	/**
	 * 条件删除
	 * @param condition
	 * @return
	 */
	public int deleteByCondition(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 获取全量信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> getInfo(int id) throws MapperException;
	/**
	 * 条件获取单个信息
	 * @param condition
	 * @return
	 */
	public Map<String,Object> getInfoByCondition(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 条件获取列表信息
	 * @param condition
	 * @return
	 */
	public List<Map<String,Object>> findByCondition(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 修改基本信息
	 * @param condition
	 */
	public void updateInfo(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 获取总数
	 * @return
	 */
	public int count() throws MapperException;
	
	/**
	 * 获取条件总数
	 * @param condition
	 * @return
	 */
	public int countByCondition(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 条件下信息是否存在
	 * @param condition
	 * @return
	 */
	public int isExist(Map<String,Object> condition) throws MapperException;
	
}
