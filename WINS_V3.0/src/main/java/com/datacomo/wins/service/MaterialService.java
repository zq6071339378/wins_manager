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

public interface MaterialService extends BaseService{

	/**
	 * 根据条件获取MaterialTemplate列表
	 * @param condition
	 * @return
	 */
	public Map<String,Object> getMaterialTemplateById(int visitId,int id) throws ServiceException;
	
	
	/**
	 * 插入模板
	 * @param visitId
	 * @param id
	 * @return
	 */
	public int insertMaterialTemplate(int visitId,Map<String,Object> condition) throws ServiceException;
	
	/**
	 * 模板生成页面
	 * @param visitId
	 * @param id
	 * @return
	 */
	public int insertMaterialPage(int visitId,Map<String,Object> condition) throws ServiceException;
	
	/**
	 * 根据ID删除单个MaterialTemplate实体
	 * @param visitId
	 * @param id
	 * @return
	 */
	public int deleteMaterialTemplateById(int visitId,int id);
	
	/**
	 * 根据ID删除单个MaterialPage实体
	 * @param visitId
	 * @param id
	 * @return
	 */
	public int deleteMaterialPageById(int visitId,int id);
	
	/**
	 * 根据条件获取MaterialTemplate列表
	 * @param condition
	 * @return
	 */
	public Map<String,Object> findMaterialTemplateByCondtion(int visitId,int id,Map<String,Object> condition) throws ServiceException;
	
	/**
	 * 根据条件获取MaterialPage列表
	 * @param condition
	 * @return
	 */
	public Map<String,Object> findMaterialPageByCondtion(int visitId,int id,Map<String,Object> condition) throws ServiceException;


	public Map<String, Object> findPage(int visitId, int visitId2,Map<String, Object> condition)throws ServiceException;
	
	
}
