package com.datacomo.wins.service;

import com.datacomo.wins.exception.ServiceException;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
public interface SysRoleMenuRelationService extends BaseService {

    /**
     * 插入数据到数据库
     * @param visitId
     * @param condition
     * @return
     * @throws ServiceException
     */
    public int insertInfoByCondition(int visitId, Map<String, Object> condition) throws ServiceException;

    /**
     * 条件更新roleNav
     * @param visitId
     * @param condition
     * @return
     * @throws ServiceException
     */
    public Boolean updateInfoByCondition(int visitId, Map<String, Object> condition) throws ServiceException;
}
