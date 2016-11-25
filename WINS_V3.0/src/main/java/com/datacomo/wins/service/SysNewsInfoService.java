package com.datacomo.wins.service;

import com.datacomo.wins.exception.ServiceException;
import java.util.Map;

/**
 * Created by duanlinzhuo on 2016/5/15.
 */
public interface SysNewsInfoService extends BaseService {

    /**
     * 根据条件获取Logs列表
     * @param condition
     * @return
     */
    public Map<String,Object> findNewsByCondition(int visitId, int id, Map<String, Object> condition) throws ServiceException;


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
    public boolean sendMassages(int id,String role_type, String type, String content,String create_name,int Show_Status) throws ServiceException;
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
     * 更新已读消息
     * @param condition
     * @return
     * @throws ServiceException
     */
    public int updateNews(Map<String,Object> condition) throws ServiceException;

}
