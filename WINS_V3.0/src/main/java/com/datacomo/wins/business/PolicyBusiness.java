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

/**
 * @author zhangming
 *
 */
public interface PolicyBusiness  extends BaseBusiness{
	/**
	 * 条件获取成员列表信息
	 * @param condition
	 * @return
	 */
	public List<Map<String,Object>> findPolicyList(int visitId,Map<String,Object> condition);
	public List<Map<String,Object>> findPolicyListEn(int visitId,Map<String,Object> condition);
	/**
	 * 条件获取成员列表信息
	 * @param condition
	 * @return
	 */
	public Map findAllIndustry(int visitId,Map<String,Object> condition);
	/**
	 * 查看单个策略
	 * @param condition
	 * @return
	 */
	public Map singlePolicyInfo(int visitId,Map<String,Object> condition);
	/**
	 * 查看推送时间群组
	 * @param condition
	 * @return
	 */
	public Map singlePolicyPushTime(int visitId,Map<String,Object> condition);
	/**
	 * 查看单个策略群组
	 * @param condition
	 * @return
	 */
	
	public Map singlePolicyOutlineWeb(int visitId,Map<String,Object> condition);
	/**
	 * 查看单个策略群组
	 * @param condition
	 * @return
	 */
	public Map singlePolicyGroup(int visitId,Map<String,Object> condition);
	/**
	 * 查看单个策略群组
	 * @param condition
	 * @return
	 */
	public Map singlePolicyIPS(int visitId,Map<String,Object> condition);
	/**
	 * 查看单个策略群组
	 * @param condition
	 * @return
	 */
	public Map singlePolicyCustomer(int visitId,Map<String,Object> condition);
	/**
	 * 查看单个策略群组
	 * @param condition
	 * @return
	 */
	public Map singlePolicyArea(int visitId,Map<String,Object> condition);
	/**
	 * 查看单个策略群组
	 * @param condition
	 * @return
	 */
	public Map singlePolicyKewWord(int visitId,Map<String,Object> condition);
	/**
	 * 查看单个策略群组
	 * @param condition
	 * @return
	 */
	public Map singlePolicyUrlDomain(int visitId,Map<String,Object> condition);
	/**
	 * 全局策略信息
	 * @param visitId
	 * @param condition
	 * @return
	 */
	public Map getGlobalInfo(int visitId,Map<String,Object> condition);
	
	public int updateGlobalInfo(Map<String,Object> condition) throws BusinessException;
	
	public int creatPolicy(Map<String,Object> condition) throws BusinessException;
	public int updataPolicyOne(Map<String,Object> condition) throws BusinessException;
	public int updataPolicyTwo(Map<String,Object> condition) throws BusinessException;
	public int updataPolicyThree(Map<String,Object> condition) throws BusinessException;
	
	
	public int policyAddGroup(Map<String,Object> condition) throws BusinessException;
	public int policyDeleteGroup(Map<String,Object> condition) throws BusinessException;
	public int policyAddCity(Map<String,Object> condition) throws BusinessException;
	public int policyDeleteCity(Map<String,Object> condition) throws BusinessException;
	public int policyAddTime(Map<String,Object> condition) throws BusinessException;
	public int policyDeleteTime(Map<String,Object> condition) throws BusinessException;
	public int policyAddUrlDomain(Map<String,Object> condition) throws BusinessException;
	public int policyAddOtherUrl(Map<String,Object> condition) throws BusinessException;
	public int policyDeleteOtherUrl(Map<String,Object> condition) throws BusinessException;
	public int policyDeleteUrlDomain(Map<String,Object> condition) throws BusinessException;
	
	public int policyAddKeyWord(Map<String,Object> condition) throws BusinessException;
	public int policyAddCustomer(Map<String,Object> condition) throws BusinessException;
	public int policyDeleteKeyWord(Map<String,Object> condition) throws BusinessException;
	public int deletePolicy(Map<String,Object> condition) throws BusinessException;
	
	public int updateStatus(Map<String,Object> condition) throws BusinessException;
	
	
	public int countByPolicyCondition(Map<String,Object> condition) throws BusinessException;
	public int countByPolicyConditionEn(Map<String,Object> condition) throws BusinessException;
	public int deletePolicyById(int id) throws BusinessException;
	public List<Map<String, Object>> sysCityList(int visitId,Map<String, Object> condition)throws BusinessException;
	public List<Map<String, Object>> sysCityDistrict(int visitId,Map<String, Object> condition) throws BusinessException;
	public List<Map<String, Object>> sysIpsList(int visitId,Map<String, Object> condition) throws BusinessException;

	public List<Map<String, Object>> sysIpsCityList(int visitId,Map<String, Object> condition) throws BusinessException;

	public int insertIpsInfo(Map<String,Object> condition) throws BusinessException;
	public int queryCustomerActivity(Map<String,Object> condition) throws BusinessException;
	public int deleteIpsRelation(Map<String,Object> condition) throws BusinessException;
	public int insertCustomer(Map<String,Object> condition) throws BusinessException;
	public int insertActivity(Map<String,Object> condition) throws BusinessException;


}
