package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
public interface SysRoleInfoBusiness extends BaseBusiness {
    /**
     * 条件获取条数
     * @param condition
     * @return
     * @throws BusinessException
     */
    public int getCountByCondition(Map<String, Object> condition) throws BusinessException;
    
    /**
     * 根据roleType获取所有的roleId
     * @param Role_Type
     * @return
     * @throws BusinessException
     */
    public List getRoleIds(String Role_Type) throws BusinessException;
}
