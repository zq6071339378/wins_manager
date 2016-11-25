package com.datacomo.wins.service;

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by duanlinzhuo on 2016/5/15.
 */
public interface SysTaskService extends BaseService {

    /**
     * 根据条件获取monitor列表
     * @param condition
     * @return
     */
    public Map<String,Object> findTasksByCondition(int visitId, int id, Map<String, Object> condition) throws ServiceException;
    
    /**
     * 根据条件获取monitor列表,无分页
     * @param condition
     * @return
     */
    public Map<String,Object> getTasksByCondition(int visitId, int id, Map<String, Object> condition) throws ServiceException;


    /**
     * 根据id删除消息
     * @param visitId
     * @param id
     * @return
     * @throws ServiceException
     */
    public int deleteById(int visitId, int id) throws ServiceException;

    /**
     * 发送消息
     * @param id
     * @param role_type
     * @param type
     * @param content
     * @param create_name
     * @throws ServiceException
     */
    public boolean sendMassages(int id,String role_type, String type, String content,String create_name) throws ServiceException;
    /**
     * 发送消息
     * @param id
     * @param role_type
     * @param type
     * @param content
     * @param create_name
     * @throws ServiceException
     */
    public boolean sendMassages(int visitId,Map<String,Object> condition) throws ServiceException;

    /**
     * 获取未读消息，通知
     * @param visitId
     * @return
     * @throws ServiceException
     */
    public Map<String,Object> getNotReadInfos(int visitId,Map<String,Object> condition) throws ServiceException;
    
    /**
     * 插入监控数据
     * @param condition
     * @return
     * @throws ServiceException
     */
	int insertInfo(Map<String, Object> condition) throws ServiceException;
	/**
	 * 获取告警设置信息
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, Object> getMonitorAlarmInfo() throws ServiceException;
	/**
	 * 更新告警设置
	 * @param condition
	 * @return
	 * @throws ServiceException
	 */
	public int updateMonitorAlarmInfo(Map<String,Object> condition) throws ServiceException;
	/**
	 * 根据用户账号获取用户ID
	 * @param condition
	 * @return
	 * @throws ServiceException
	 */
	public int getUserId(Map<String,Object> condition) throws ServiceException;
	/**
	 * 获取服务器登录信息
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getServerInfo() throws ServiceException;
}
