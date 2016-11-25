package com.datacomo.wins.mapper;

import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface AccountManageMapper extends BaseMapper {
    /**
     * 根据条件查询条数
     * @param condition
     * @return
     * @throws MapperException
     */
    public int getCountByCondition(Map<String, Object> condition) throws MapperException;
    
    /**
     * 根据角色ID查询所有角色用户名
     * @param Role_Id
     * @return
     * @throws MapperException
     */
    public List<Map<String,Object>> getInfoByRoleId(int Role_Id) throws MapperException;

    /**
     * 获取所有信息
     * @return
     * @throws MapperException
     */
    public List<Map<String,Object>> getAllInfo(int Show_Status) throws MapperException;

    /**
     * 获取账户归属地市信息
     * @param condition
     * @return
     * @throws MapperException
     */
    public Map<String,Object> getBelongCityInfo(Map<String, Object> condition) throws MapperException;
}
