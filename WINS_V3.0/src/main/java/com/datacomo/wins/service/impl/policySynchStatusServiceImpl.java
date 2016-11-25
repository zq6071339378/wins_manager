package com.datacomo.wins.service.impl;

import com.datacomo.wins.business.PolicySynchStatusBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.PolicySynchStatusService;
import com.datacomo.wins.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/7/6.
 */
@Service("policySynchStatusService")
public class policySynchStatusServiceImpl implements PolicySynchStatusService {
    private static Logger logger = Logger.getLogger(policySynchStatusServiceImpl.class.getName());
    @Autowired
    private PolicySynchStatusBusiness policySynchStatusBusiness;
    @Override
    public Map<String, Object> getInfoByCondition(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("getInfoByCondition method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result=null;
        try {
            result = policySynchStatusBusiness.getInfoByCondition(visitId,condition);
            int synchStatus = Integer.parseInt(String.valueOf(result.get("synchStatus")));
            int lastStatus = Integer.parseInt(String.valueOf(result.get("lastStatus")));
            if(synchStatus > lastStatus){
                condition.put("lastStatus",synchStatus);
                condition.put("lastSynchtime", DateUtil.getDateString());
                boolean bool = policySynchStatusBusiness.updateInfo(visitId,condition,visitId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getInfoByCondition method end");
        return result;
    }

    @Override
    public boolean updateInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException {
        logger.info("updateInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
            logger.debug("id:"+id);
        }
        boolean result=false;
        try {
            result=policySynchStatusBusiness.updateInfo(visitId,condition,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateInfo menthod end");
        return result;
    }
}
