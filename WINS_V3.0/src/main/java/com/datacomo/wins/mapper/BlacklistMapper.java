package com.datacomo.wins.mapper;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.MapperException;

/**
 * 
 * @author liwenjie
 *
 */
public interface BlacklistMapper extends BaseMapper {

	public List<Map<String, Object>> userBlacklist(Map<String, Object> condition)throws MapperException;

	public int userBlacklistcount(Map<String, Object> condition)throws MapperException;

	public List<Map<String, Object>> urlBlacklist(Map<String, Object> condition)throws MapperException;

	public int urlBlacklistcount(Map<String, Object> condition)throws MapperException;

	public int delUser(int userId)throws MapperException;

	public int delUrl(int urlId)throws MapperException;

	public List<Map<String, Object>> showPolicy(Map<String, Object> condition)throws MapperException;

	public int insertBlackUser(Map<String, Object> condition)throws MapperException;

	public int insertBlackUrl(Map<String, Object> condition)throws MapperException;

	public int insertBlackUsers(Map<String, Object> condition)throws MapperException;

	public int insertBlackUrls(Map<String, Object> condition)throws MapperException;

	public int getBlackUrlcount(Map<String, Object> condition) throws MapperException;

}
