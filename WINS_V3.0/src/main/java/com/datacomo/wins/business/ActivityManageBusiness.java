package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface ActivityManageBusiness extends BaseBusiness {

	/**
	 * 根据ID删除活动-广告主关系
	 * @param id
	 * @return
	 * @throws BusinessException
     */
	public int deleteInfoById(int id)throws BusinessException;

	public int insertActivityCustomerRelation(Map<String,Object> condition) throws BusinessException;
}
