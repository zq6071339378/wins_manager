package com.datacomo.wins.service;

import com.datacomo.wins.exception.ServiceException;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface SysRoleInfoService extends BaseService {

    public Map<String,Object> findRolesByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException;

    public Map<String,Object> getRoleInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public int deleteRoleById(int visitId, int id) throws ServiceException;

    public int insertInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public boolean updateInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException;

    public int getCount(Map<String, Object> condition) throws ServiceException;

    public boolean updateRoleState (int visitId, Map<String, Object> condition, int id) throws ServiceException;


}
