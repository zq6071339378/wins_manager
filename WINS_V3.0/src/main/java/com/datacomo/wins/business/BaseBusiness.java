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
import com.datacomo.wins.exception.ServiceException;


/**
 * @author zhaizhanpo
 *
 */
public interface BaseBusiness{
		
		/**
		 * 创建信息
		 * @param
		 * @return
		 */
		public int insertInfo(Map<String,Object> condition) throws BusinessException;
		
		/**
		 * 删除单个
		 * @param id
		 * @return
		 */
		public int deleteById(int visitId,int id)throws BusinessException;
		
		
		
		/**
		 * 条件删除
		 * @param condition
		 * @return
		 */
		public int deleteByCondition(int visitId,Map<String,Object> condition)throws BusinessException;
		
		/**
		 * 获取全量信息
		 * @param id
		 * @return
		 */
		public Map<String,Object> getInfo(int visitId,int id);
		/**
		 * 条件获取单个信息
		 * @param condition
		 * @return
		 */
		public Map<String,Object> getInfoByCondition(int visitId,Map<String,Object> condition);
		
		/**
		 * 条件获取列表信息
		 * @param condition
		 * @return
		 */
		public List<Map<String,Object>> findByCondition(int visitId,Map<String,Object> condition);
		
		/**
		 * 修改基本信息
		 * @param condition
		 */
		public boolean updateInfo(int visitId,Map<String,Object> condition,int id) throws BusinessException;
		
		/**
		 * 获取总数
		 * @return
		 */
		public int count() throws BusinessException;
		
		/**
		 * 获取条件总数
		 * @param condition
		 * @return
		 */
		public int countByCondition(int visitId,Map<String,Object> condition);
		/**
		 * 条件下信息是否存在
		 * @param condition
		 * @return
		 */
		public int isExist(Map<String,Object> condition);
		
}
