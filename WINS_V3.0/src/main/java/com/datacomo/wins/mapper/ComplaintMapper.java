package com.datacomo.wins.mapper;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.MapperException;

/**
 * 
 * @author liwenjie
 *
 */
public interface ComplaintMapper extends BaseMapper {

	public List<Map<String, Object>> showComplaint(Map<String, Object> condition)throws MapperException;

	public int showComplaintcount(Map<String, Object> condition)throws MapperException;

	public List<Map<String, Object>> showPush(Map<String, Object> condition)throws MapperException;

	public int showPushcount(Map<String, Object> condition)throws MapperException;

	public List<Map<String, Object>> showClick(Map<String, Object> condition)throws MapperException;

	public int showClickcount(Map<String, Object> condition)throws MapperException;

	public List<Map<String, Object>> showOrder(Map<String, Object> condition)throws MapperException;

	public int showOrdercount(Map<String, Object> condition)throws MapperException;

}
