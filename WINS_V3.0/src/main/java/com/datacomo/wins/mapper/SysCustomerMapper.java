package com.datacomo.wins.mapper;

import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
public interface SysCustomerMapper extends BaseMapper {
	
	/**
	 * 获取广告主名
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String, Object>> searchCustomer(Map<String, Object> condition) throws MapperException;
	
	public int deleteInfoById(int id) throws MapperException;
	
	public void updateRechargenfo(Map<String,Object> condition) throws MapperException;

	public int insertTradeInfo(Map<String,Object> condition) throws MapperException;

	public List<Map<String,Object>> findAllByCondition(Map<String,Object> condition) throws MapperException;

	public int countAllByCondition(Map<String,Object> condition) throws MapperException;

	public List<Map<String,Object>> selectAllInfo(Map<String,Object> condition) throws MapperException;

	public int countAllInfo(Map<String,Object> condition) throws MapperException;


}
