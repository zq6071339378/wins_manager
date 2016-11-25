package com.datacomo.wins.mapper;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.MapperException;

/**
 * Created by duanlinzhuo on 2016/5/16.
 */
public interface SysTaskMapper extends BaseMapper {
    /**
     * 根据条件模糊查询
     * @param condition
     * @return
     * @throws MapperException
     */
    public List<Map<String,Object>> getInfoListByCondition(Map<String, Object> condition) throws MapperException;

    /**
     * 获取未读消息的数目
     * @param condition
     * @return
     * @throws MapperException
     */
    public int getNotReadCountByCondition(Map<String,Object> condition) throws MapperException;

    /**
     * 获取未读的消息列表
     * @param condition
     * @return
     * @throws MapperException
     */
    public List<Map<String,Object>> getNotReadInfoByCondition(Map<String,Object> condition) throws MapperException;

    /**
     * 根据用户id删除消息
     * @param id
     * @return
     * @throws MapperException
     */
    public int deleteByUserId(int id) throws MapperException;
    /**
     * 获取告警设置信息
     * @return
     * @throws MapperException
     */
    public Map<String,Object> getMonitorAlarmInfo() throws MapperException;
    /**
     * 更新告警设置
     * @return
     * @throws MapperException
     */
    public int updateMonitorAlarmInfo(Map<String,Object> condition) throws MapperException;
    /**
     * 根据用户账号获取用户ID
     * @param condition
     * @return
     * @throws MapperException
     */
    public int getUserId(Map<String,Object> condition) throws MapperException;
    /**
     * 获取服务器登录信息
     * @return
     * @throws MapperException
     */
    public List<Map<String,Object>> getServerInfo() throws MapperException; 
}
