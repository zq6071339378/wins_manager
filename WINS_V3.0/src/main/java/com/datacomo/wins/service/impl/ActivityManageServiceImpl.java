package com.datacomo.wins.service.impl;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.ActivityManageBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.ActivityManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/11/16.
 */
@Service("activityManageService")
public class ActivityManageServiceImpl implements ActivityManageService {
    private static Logger logger = Logger.getLogger(ActivityManageServiceImpl.class.getName());
    @Autowired
    private ActivityManageBusiness activityManageBusiness;

    @Override
    public Map<String, Object> findActivityByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("findActivityByCondtion method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("args:condition"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=activityManageBusiness.findByCondition(visitId,condition);
                int count=activityManageBusiness.countByCondition(visitId,condition);
                if (condition.containsKey("page")){
                    Pagination page=(Pagination)condition.get("page");
                    page.setTotalCount(count);
                    page.setCurrentPage(page.getStart()/page.getLimit()+1);
                    result.putAll(condition);
                    result.put("page",page);
                }
                result.put("list",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("findActivityByCondtion method end");
        return result;
    }

    @Override
    public Map<String, Object> getActivityInfo(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("getActivityInfo method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result=null;
        try {
            result = activityManageBusiness.getInfoByCondition(visitId,condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getActivityInfo method end");
        return result;
    }

    @Override
    public int deleteActivityById(int visitId, int id) throws ServiceException {
        logger.info("deleteActivityById method start");
        if (logger.isDebugEnabled()){
            logger.debug("id"+id);
        }
        int result = 0;
        try {
            result=activityManageBusiness.deleteInfoById(id);
            result =  activityManageBusiness.deleteById(visitId,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteActivityById method end");
        return result;
    }

    @Override
    public int deleteActivityCustomerRelation(int visitId, int id) throws ServiceException {
        logger.info("deleteActivityCustomerRelation method start");
        if (logger.isDebugEnabled()){
            logger.debug("id"+id);
        }
        int result = 0;
        try {
            result=activityManageBusiness.deleteInfoById(id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteActivityCustomerRelation method end");
        return result;
    }

    @Override
    public int insertActivityInfo(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("insertActivityInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = activityManageBusiness.insertInfo(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertActivityInfo menthod end");
        return result;
    }

    @Override
    public int insertActivityCustomerRelation(int visitId, Map<String, Object> condition){
        logger.info("insertActivityCustomerRelation method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = activityManageBusiness.insertActivityCustomerRelation(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertActivityCustomerRelation menthod end");
        return result;
    }

    @Override
    public boolean updateActivityInfo(int visitId, Map<String, Object> condition, int id){
        logger.info("updateActivityInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
            logger.debug("id:"+id);
        }
        boolean result=false;
        try {
            result=activityManageBusiness.updateInfo(visitId,condition,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateActivityInfo menthod end");
        return result;
    }
}
