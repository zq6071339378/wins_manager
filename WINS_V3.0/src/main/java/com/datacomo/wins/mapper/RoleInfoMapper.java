package com.datacomo.wins.mapper;

import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
public interface RoleInfoMapper extends BaseMapper {
    /**
     * 根据条件查询条数
     * @param condition
     * @return
     * @throws MapperException
     */
    public int getCountByCondition(Map<String, Object> condition) throws MapperException;
    
    /**
     * 根据roleType查询所有的roleId
     * @param Role_Type
     * @return
     * @throws MapperException
     */
    public List getRoleIds(String Role_Type)throws MapperException;
}
