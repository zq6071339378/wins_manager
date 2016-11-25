package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface CustomerManageBusiness extends BaseBusiness {

	/**
	 * 条件获取广告主名
	 * @param condition
	 * @return
	 */
	public List<Map<String, Object>> searchCustomer(int visitId, Map<String, Object> condition) throws BusinessException;
	
	/**
	 * 根据ID删除策略-广告主关系
	 * @param id
	 * @return
	 * @throws BusinessException
     */
	public int deleteInfoById(int id)throws BusinessException;

	public int insertTradeInfo(Map<String,Object> condition) throws BusinessException;

	public List<Map<String,Object>> findAllByCondition(Map<String,Object> condition) throws BusinessException;

	public int countAllByCondition(Map<String,Object> condition) throws BusinessException;

	public List<Map<String,Object>> selectAllInfo(Map<String,Object> condition) throws BusinessException;

	public int countAllInfo(Map<String,Object> condition) throws BusinessException;
}
