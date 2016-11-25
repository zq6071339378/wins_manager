package com.datacomo.wins.service;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.push.bean.UrlFilter;
import com.datacomo.wins.push.bean.UserFilter;
import org.springframework.web.multipart.MultipartFile;

import com.datacomo.wins.exception.ServiceException;

/**
 * 
 * @author liwenjie
 *
 */
public interface BlacklistService extends BaseService {

	public Map<String, Object> userBlacklist(int visitId, Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> urlBlacklist(int visitId,Map<String, Object> condition)throws ServiceException;

	public int delUser(int visitId, UserFilter userFilter ,int userId)throws ServiceException;

	public int delUrl(int visitId, UrlFilter urlFilter, int urlId)throws ServiceException;

	public Map<String, Object> showPolicy(int visitId,Map<String, Object> condition)throws ServiceException;

	public int insertBlackUser(int visitId, UserFilter userFilter,Map<String,Object> condition)throws ServiceException;

	public int insertBlackUrl(int visitId, UrlFilter urlFilter , Map<String, Object> condition)throws ServiceException;
	
    /**
     * 上传黑名单txt
     * @param blackfile
     * @return 返回上传之后的txt文件名
     * @throws ServiceException
     */
	public String uploadBlackUserTxt(MultipartFile blackfile, String uploadUrl)throws ServiceException;
    /**
     * 读取txt文件数据存为List<Map<>>集合
     * @param txtUrl
     * @return
     * @throws ServiceException
     */
	public List<Map<String, Object>> readBlackUserTxt(String txtUrl)throws ServiceException;

	public int insertBlackUsers(int visitId, Map<String, Object> condition)throws ServiceException;

	public int insertBlackUrls(int visitId, Map<String, Object> condition)throws ServiceException;

	public int getBlackUrlcount(Map<String, Object> condition) throws MapperException;
	
}
