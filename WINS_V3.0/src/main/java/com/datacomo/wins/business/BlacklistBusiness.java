package com.datacomo.wins.business;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;

/**
 * 
 * @author liwenjie
 *
 */
public interface BlacklistBusiness extends BaseBusiness {

	public List<Map<String, Object>> userBlacklist(int visitId,Map<String, Object> condition)throws BusinessException;

	public int userBlacklistcount(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> urlBlacklist(int visitId,Map<String, Object> condition)throws BusinessException;

	public int urlBlacklistcount(Map<String, Object> condition)throws BusinessException;

	public int delUser(int visitId, int userId)throws BusinessException;

	public int delUrl(int visitId, int urlId)throws BusinessException;

	public List<Map<String, Object>> showPolicy(int visitId,Map<String, Object> condition)throws BusinessException;

	public int insertBlackUser(Map<String, Object> condition)throws BusinessException;

	public int insertBlackUrl(Map<String, Object> condition)throws BusinessException;

	public int insertBlackUsers(Map<String, Object> condition)throws BusinessException;

	public int insertBlackUrls(Map<String, Object> condition)throws BusinessException;

	public int getBlackUrlcount(Map<String, Object> condition) throws MapperException;

}
