package com.datacomo.wins.service;

import com.datacomo.wins.exception.ServiceException;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
public interface SysLogRecordService extends BaseService {
    /**
     * 根据条件获取
     * @param condition
     * @return
     */
    public Map<String,Object> findLogsByCondition(int visitId, int id, Map<String, Object> condition) throws ServiceException;

    public int insertLogInfo(int userId, String logTitle, int logType, String logCont, String logIp, String logTime) throws ServiceException;

}
