package com.datacomo.wins.service.impl;

import com.datacomo.wins.business.SysRoleMenuRelationBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysRoleMenuRelationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
@Service("sysRoleMenuRelationService")
public class SysRoleMenuRelationServiceImp implements SysRoleMenuRelationService {
    private static Logger logger = Logger.getLogger(SysRoleMenuRelationServiceImp.class.getName());
    @Autowired
    private SysRoleMenuRelationBusiness sysRoleMenuRelationBusiness;

    @Override
    public int insertInfoByCondition(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("insertInfoByCondition method start");
        if(logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysRoleMenuRelationBusiness.insertInfo(condition );
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertInfoByCondition method end");
        return result;
    }

    @Override
    public Boolean updateInfoByCondition(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("updateInfoByCondition method start");
        if(logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        boolean result = false;
        try {
            result = sysRoleMenuRelationBusiness.updateInfo(visitId,condition,visitId);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateInfoByCondition method start");
        return result;
    }
}
