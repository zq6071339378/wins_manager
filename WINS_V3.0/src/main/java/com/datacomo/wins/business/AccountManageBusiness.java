package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface AccountManageBusiness extends BaseBusiness {
    /**
     * 条件获取条数
     * @param condition
     * @return
     * @throws BusinessException
     */
    public int getCountByCondition(Map<String, Object> condition) throws BusinessException;
    
    /**
     * 根据角色ID查询
     * @param Role_Id
     * @return
     * @throws BusinessException
     */
    public List<Map<String,Object>> getInfoByRoleId(int Role_Id) throws BusinessException;

    /**
     * 获取所有角色信息，不分页
     * @return
     * @throws BusinessException
     */
    public List<Map<String,Object>> getAllInfo(int Show_Status) throws BusinessException;

    /**
     * 获取账户归属地市信息
     * @param condition
     * @return
     * @throws MapperException
     */
    public Map<String,Object> getBelongCityInfo(Map<String, Object> condition) throws MapperException;

}
