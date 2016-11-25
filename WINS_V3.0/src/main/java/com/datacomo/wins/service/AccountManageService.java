package com.datacomo.wins.service;

import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.exception.ServiceException;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface AccountManageService extends BaseService {

    public Map<String,Object> findAccountsByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException;

    public Map<String,Object> getAccountInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public int deleteAccountById(int visitId, int id) throws ServiceException;

    public int insertInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public boolean updateInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException;

    public int getCount(Map<String, Object> condition) throws ServiceException;

    public Map<String,Object> getInfoByCondition(int visitId, Map<String, Object> condition) throws ServiceException;

    public Map<String,Object> getBelongCityInfo(int id,Map<String, Object> condition) throws MapperException;

}
