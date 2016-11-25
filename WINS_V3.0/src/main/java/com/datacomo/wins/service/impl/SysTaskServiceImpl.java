package com.datacomo.wins.service.impl;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.AccountManageBusiness;
import com.datacomo.wins.business.SysRoleInfoBusiness;
import com.datacomo.wins.business.SysTaskBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.websocket.MyWebSocketHandler;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysTaskService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duanlinzhuo on 2016/3/16.
 */
@Service("sysTaskService")
public class SysTaskServiceImpl implements SysTaskService {
    private static Logger logger=Logger.getLogger(SysTaskServiceImpl.class.getName());
    @Autowired
    private SysTaskBusiness sysTaskBusiness;
    @Autowired
    private SysRoleInfoBusiness sysRoleInfoBusiness;
    @Autowired
    private AccountManageBusiness accountManageBusiness;
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Override
    public Map<String, Object> findTasksByCondition(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("findNewsByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=sysTaskBusiness.findByCondition(visitId,condition);
                int count=sysTaskBusiness.countByCondition(visitId,condition);
                if (condition.containsKey("page")){
                    Pagination page=(Pagination) condition.get("page");
                    page.setTotalCount(count);
                    page.setCurrentPage(page.start/page.limit+1);
                    result.putAll(condition);
                    result.put("list",list);
                    result.put("page",page);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("findNewsByCondition method end");
        return result;
    }
    
    @Override
    public Map<String, Object> getTasksByCondition(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("getTasksByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=sysTaskBusiness.findByCondition(visitId,condition);           
                    result.put("list",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("getTasksByCondition method end");
        return result;
    }

    @Override
    public int deleteById(int visitId, int id) throws ServiceException {
        logger.info("deleteById method start");
        if (logger.isDebugEnabled()){
            logger.debug("id:"+id);
        }
        int result=0;
        try {
            result=sysTaskBusiness.deleteById(visitId,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteById method end");
        return result;
    }
    
    @Override
    public int insertInfo(Map<String, Object> condition) throws ServiceException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result=0;
        try {
            result=sysTaskBusiness.insertInfo(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("insertInfo method end");
        return result;
    }

	@Override
	public boolean sendMassages(int id, String role_type, String type,
			String content, String create_name) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendMassages(int visitId, Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getNotReadInfos(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getMonitorAlarmInfo() throws ServiceException {
		logger.info("getMonitorAlarmInfo method start");
        Map<String, Object> result = null;
        try {
            result=sysTaskBusiness.getMonitorAlarmInfo();
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("getMonitorAlarmInfo method end");
        return result;
	}

	@Override
	public int updateMonitorAlarmInfo(Map<String, Object> condition)
			throws ServiceException {
		logger.info("updateMonitorAlarmInfo method start");
        int result = 0;
        try {
            result=sysTaskBusiness.updateMonitorAlarmInfo(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("updateMonitorAlarmInfo method end");
        return result;
	}

	@Override
	public int getUserId(Map<String, Object> condition) throws ServiceException {
		logger.info("getUserId method start");
        int result = 0;
        try {
            result=sysTaskBusiness.getUserId(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("getUserId method end");
        return result;
	}

	@Override
	public List<Map<String, Object>> getServerInfo() throws ServiceException {
		logger.info("getServerInfo method start");
		List<Map<String, Object>> result = null;
		try {
			result = sysTaskBusiness.getServerInfo();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("getServerInfo method end");
		return result;
	}
}
