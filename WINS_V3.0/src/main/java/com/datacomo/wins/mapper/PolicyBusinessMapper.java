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

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import org.apache.tools.ant.types.Mapper;

/**
 * @author zhangming
 *
 */
public interface PolicyBusinessMapper extends BaseMapper {
	/**
	 * 条件获取列表信息
	 * @param condition
	 * @return
	 */
	public List<Map<String,Object>> findPolicyList(Map<String,Object> condition) throws MapperException;
	public List<Map<String,Object>> findPolicyListEn(Map<String,Object> condition) throws MapperException;
	/**
	 * 单个策略
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public Map<String,Object> singlePolicyInfo(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 获取推送时间
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String,Object>> singlePolicyPushTime(Map<String,Object> condition) throws MapperException;
	/**
	 * 获取单个策略群组
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String,Object>> singlePolicyOutlineWeb(Map<String,Object> condition) throws MapperException;
	/**
	 * 获取单个策略群组
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String,Object>> singlePolicyGroup(Map<String,Object> condition) throws MapperException;
	/**
	 * 获取单个策略地区
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String,Object>> findAllIndustry(Map<String,Object> condition) throws MapperException;
	/**
	 * 获取行业属性
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String,Object>> singlePolicyIPS(Map<String,Object> condition) throws MapperException;
	/**
	 * 获取行业属性
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String,Object>> singlePolicyCustomer(Map<String,Object> condition) throws MapperException;
	/**
	 * 获取行业属性
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String,Object>> singlePolicyArea(Map<String,Object> condition) throws MapperException;

	/**
	 * 获取单个策略关键字
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String,Object>> singlePolicyKewWord(Map<String,Object> condition) throws MapperException;
	/**
	 * 获取单个策略定向网址
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String,Object>> singlePolicyUrlDomain(Map<String,Object> condition) throws MapperException;

	
	
	public Map<String,Object> getGlobalInfo(Map<String,Object> condition) throws MapperException;
	/**
	 * 策略数量
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	public int countByPolicyCondition(Map<String,Object> condition) throws MapperException;
	public int countByPolicyConditionEn(Map<String,Object> condition) throws MapperException;
	/**
	 * 更新全局策略
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int updateGlobalInfo(Map<String,Object> condition) throws MapperException;
	/**
	 * 创建策略
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int creatPolicy(Map<String,Object> condition) throws MapperException;
	public int updataPolicyOne(Map<String,Object> condition) throws MapperException;
	public int updataPolicyTwo(Map<String,Object> condition) throws MapperException;
	public int updataPolicyThree(Map<String,Object> condition) throws MapperException;
	/**
	 * 创建策略添加群组
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyAddGroup(Map<String,Object> condition) throws MapperException;
	/**
	 * 创建策略添加城市
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyAddCity(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 创建策略添加时间
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyAddTime(Map<String,Object> condition) throws MapperException;
	
	public int policyAddOtherUrl(Map<String,Object> condition) throws MapperException;
	
	
	public int policyDeleteOtherUrl(Map<String,Object> condition) throws MapperException;
	/**
	 * 添加定向网址
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyAddUrlDomain(Map<String,Object> condition) throws MapperException;
	/**
	 * 添加关键字
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyAddKeyWord(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 添加广告主
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyAddCustomer(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 创建策略添加群组
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyDeleteGroup(Map<String,Object> condition) throws MapperException;
	/**
	 * 创建策略添加城市
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyDeleteCity(Map<String,Object> condition) throws MapperException;
	
	/**
	 * 创建策略添加时间
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyDeleteTime(Map<String,Object> condition) throws MapperException;
	/**
	 * 添加定向网址
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyDeleteUrlDomain(Map<String,Object> condition) throws MapperException;
	/**
	 * 添加关键字
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int policyDeleteKeyWord(Map<String,Object> condition) throws MapperException;
	/**
	 * 删除策略
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int deletePolicy(Map<String,Object> condition) throws MapperException;
	
	
	/**
	 * 创建策略状态
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int updateStatus(Map<String,Object> condition) throws MapperException;
	/**
	 * cheack password
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public int checkPass(Map<String,Object> condition) throws MapperException;
	
	public int deletePolicyById(int id) throws MapperException;
	public List<Map<String,Object>> sysCityList(Map<String, Object> condition)throws MapperException;
	public List<Map<String,Object>> sysCityDistrict(Map<String, Object> condition)throws MapperException;
	public List<Map<String,Object>> sysIpsList(Map<String, Object> condition)throws MapperException;
	public List<Map<String,Object>> sysIpsCityList(Map<String, Object> condition)throws MapperException;
	public int insertIpsInfo(Map<String,Object> condition) throws MapperException;
	public int queryCustomerActivity(Map<String,Object> condition) throws MapperException;
	public int deleteIpsRelation(Map<String,Object> condition) throws MapperException;
	public int insertCustomer(Map<String,Object> condition) throws MapperException;
	public int insertActivity(Map<String,Object> condition) throws MapperException;
}
