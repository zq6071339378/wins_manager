package com.datacomo.wins.service;

import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface CustomerManageService extends BaseService {
    public Map<String,Object> findCustomersByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException;

    public Map<String,Object> getCustomerInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public int deleteCustomerById(int visitId, int id) throws ServiceException;

    public int insertCustomerInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public boolean updateCustomerInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException;
    
    public int insertRechargenfo(int visitId, Map<String, Object> condition, int id) throws ServiceException;

	/*
	 * 查询广告主名
	 * */
	public List<Map<String, Object>> searchCustomer(int visitId, Map<String, Object> condition) throws ServiceException;

    public Map<String,Object> findCustomersFinanceByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException;

    public Map<String,Object> findCustomerTradeInfoByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException;

}
