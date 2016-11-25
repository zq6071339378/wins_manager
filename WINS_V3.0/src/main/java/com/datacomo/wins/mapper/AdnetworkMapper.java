package com.datacomo.wins.mapper;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.MapperException;

public interface AdnetworkMapper extends BaseMapper{
	/**
	 * 获取Adnetwork名
	 * @param condition
	 * @return
	 * @throws MapperException
	 */
	public List<Map<String, Object>> searchAdnetwork(Map<String, Object> condition) throws MapperException;
	
	public int deleteInfoById(int id) throws MapperException;
	
}
