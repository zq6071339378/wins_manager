package com.datacomo.wins.service;

import com.datacomo.wins.exception.ServiceException;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface IPSManageService extends BaseService {
    public Map<String,Object> findIpsByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException;

    public Map<String,Object> getIpsInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public int deleteIpsById(int visitId, int id) throws ServiceException;

    public int insertIpsInfo(int visitId, Map<String, Object> condition) throws ServiceException;

    public boolean updateIpsInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException;

    public Map<String,Object> findProvinceCityInfo(Map<String,Object> condition) throws ServiceException;

    public int count() throws ServiceException;

    public int getMaxValue() throws ServiceException;

    public int synchronzeIpsStatusToICS(int ipsId,int ipsStatus);

}
