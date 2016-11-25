package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
public interface SysLogRecordBusiness extends BaseBusiness {
    public int insertLogInfo(int userId, String logTitle, int logType, String logCont, String logIp, String logTime) throws BusinessException;
}
