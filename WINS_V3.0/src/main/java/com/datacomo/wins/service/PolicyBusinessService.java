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

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.ServiceException;

/**
 * Service 接口类
 * @author zhangming
 *
 */
public interface PolicyBusinessService extends BaseService{
	/*
	 * 查询策略列表
	 */
	public Map<String,Object> findPolicyList(int visitId,Map<String,Object> condition) throws ServiceException;
	public Map<String,Object> findPolicyListEn(int visitId,Map<String,Object> condition) throws ServiceException;
	/*
	 * 查询广告属性
	 */
	public Map<String,Object> findAllIndustry(int visitId,Map<String,Object> condition) throws ServiceException;
	/*
	 * 查询单个策略
	 */
	public Map<String,Object> singlePolicyInfo(int visitId,Map<String,Object> condition) throws ServiceException;
	/*
	 * 查询单个策略的群组
	 */
	public Map<String,Object> singlePolicyGroup(int visitId,Map<String,Object> condition) throws ServiceException;
	/*
	 * 查询单个策略的IPS
	 */
	public Map<String,Object> singlePolicyIPS(int visitId,Map<String,Object> condition) throws ServiceException;
	/*
	 * 查询单个策略的地区
	 */
	public Map<String,Object> singlePolicyArea(int visitId,Map<String,Object> condition) throws ServiceException;
	/*
	 * 查询单个策略的关键字
	 */
	public Map<String,Object> singlePolicyKeyWord(int visitId,Map<String,Object> condition) throws ServiceException;
	/*
	 * 查询单个策略的定向网址
	 */
	public Map<String,Object> singlePolicyUrlDomain(int visitId,Map<String,Object> condition) throws ServiceException;
	/*
	 * 查询推送时间
	 */
	public Map<String, Object> singlePolicyOutlineWeb(int id, Map<String, Object> condition) throws ServiceException;
	
	public Map<String,Object> singlePolicyPushTime(int visitId,Map<String,Object> condition) throws ServiceException;

	/**
	 * 查询单个策略的关联广告主
	 * @param visitId
	 * @param condition
	 * @return
	 * @throws ServiceException
     */
	public Map<String,Object> singlePolicyCustomer(int visitId,Map<String,Object> condition) throws ServiceException;
	/**
	 * 
	 * 
	 * 查询全局策略配置
	 * @param visitId
	 * @param condition
	 * @return
	 * @throws ServiceException
	 */
	public Map<String,Object> getGlobalInfo(int visitId,Map<String,Object> condition) throws ServiceException;
	/**
	 * 修改全局策略
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public int updateGlobalInfo(Map<String, Object> condition) throws ServiceException;
	/**
	 * 创建策略
	 * @param condition
	 * @return
	 * @throws ServiceException
	 */
	public int creatPolicy(Map<String, Object> condition) throws ServiceException;
	public int updataPolicyOne(Map<String, Object> condition) throws ServiceException;
	public int updataPolicyTwo(Map<String, Object> condition) throws ServiceException;
	public int updataPolicyThree(Map<String, Object> condition) throws ServiceException;
	/**
	 * 创建策略添加群组
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public int policyAddGroup(Map<String, Object> condition) throws ServiceException;
	/**
	 * 删除策略添加群组
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public int policyDeleteGroup(Map<String, Object> condition) throws ServiceException;
	/**
	 * 创建策略添加城市
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public int policyAddCity(Map<String, Object> condition) throws ServiceException;
	public int policyDeleteCity(Map<String, Object> condition) throws ServiceException;
	/**
	 * 创建策略添加城市
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public int policyAddTime(Map<String, Object> condition) throws ServiceException;
	public int policyDeleteTime(Map<String, Object> condition) throws ServiceException;
	/**
	 * 创建策略添加定向网址
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public int policyAddUrlDomain(Map<String, Object> condition) throws ServiceException;
	public int policyDeleteUrlDomain(Map<String, Object> condition) throws ServiceException;
	
	public int policyAddOtherUrl(Map<String, Object> condition) throws ServiceException;
	public int policyDeleteOtherUrl(Map<String, Object> condition) throws ServiceException;
	
	/**
	 * 创建策略添加关键字
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public int policyAddKeyWord(Map<String, Object> condition) throws ServiceException;
	
	/**
	 * 创建策略添加广告主
	 * @param
	 * @return
	 * @throws ServiceException
	 */
	public int policyAddCustomer(Map<String, Object> condition,int policyId) throws ServiceException;

	public int policyDeleteCustomer(int policyId) throws ServiceException;

	public int policyDeleteKeyWord(Map<String, Object> condition) throws ServiceException;
	/**
	 * 更新策略状态
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public int updateStatus(Map<String, Object> condition) throws ServiceException;
	/**
	 * 删除策略
	 * @param condition
	 * @return
	 * @throws ServiceException
	 */
	public int deletePolicy(Map<String, Object> condition) throws ServiceException;
	
	public int deletePolicyById(int id) throws ServiceException;
	public int quiteChangePllicy() throws ServiceException;
	public List sysCityList(int visitId,Map<String, Object> condition) throws ServiceException;
	public List sysCityDistrict(int visitId,Map<String, Object> condition) throws ServiceException;
	public List sysIpsList(int visitId,Map<String, Object> condition) throws ServiceException;
	public List sysIpsCityList(int visitId,Map<String, Object> condition) throws ServiceException;
	public int insertIpsInfo(int visitId,Map<String,Object> condition) throws ServiceException;
	public int insertCustomer(int visitId,Map<String,Object> condition) throws ServiceException;
	public int queryCustomerActivity(int visitId,Map<String,Object> condition) throws ServiceException;
	public int insertActivity(int visitId,Map<String,Object> condition) throws ServiceException;
}
