package com.datacomo.wins.service;

import com.datacomo.wins.exception.ServiceException;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/7/6.
 */
public interface PolicySynchStatusService extends BaseService {
    public Map<String,Object> getInfoByCondition(int visitId, Map<String, Object> condition) throws ServiceException;

    public boolean updateInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException;
}
