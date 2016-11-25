package com.datacomo.wins.service;

import com.datacomo.wins.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface ActivityManageService extends BaseService {
    public Map<String,Object> findActivityByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException;

    public Map<String,Object> getActivityInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public int deleteActivityById(int visitId, int id) throws ServiceException;

    public int deleteActivityCustomerRelation(int visitId, int id) throws ServiceException;

    public int insertActivityInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public int insertActivityCustomerRelation(int visitId, Map<String, Object> condition) throws ServiceException;

    public boolean updateActivityInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException;

}
