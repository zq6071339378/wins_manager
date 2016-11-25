package com.datacomo.wins.mapper;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.MapperException;

/**
 * Created by duanlinzhuo on 2016/5/16.
 */
public interface SysNewsInfoMapper extends BaseMapper {
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
     * 更新已读消息
     * @param condition
     * @return
     * @throws MapperException
     */
    public int updateNews(Map<String,Object> condition) throws MapperException;
    /**
     * 根据角色ID查询所有角色用户名
     * @param Role_Id
     * @return
     * @throws MapperException
     */
    public List<Map<String,Object>> getInfoByRoleId(Map<String,Object> condition) throws MapperException;
}
