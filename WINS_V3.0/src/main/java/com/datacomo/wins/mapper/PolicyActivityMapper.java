package com.datacomo.wins.mapper;

import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
public interface PolicyActivityMapper extends BaseMapper {

	public int deleteInfoById(int id) throws MapperException;

	public int insertActivityCustomerRelation(Map<String, Object> condition) throws MapperException;

	public List<Map<String,Object>> searchActivityCustomer(Map<String,Object> condition) throws MapperException;


}
