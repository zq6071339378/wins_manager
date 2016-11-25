/**
 * 
 */
package com.datacomo.wins.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.NavInfoBusiness;
import com.datacomo.wins.business.SysUserInfoBusiness;
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.NavInfoService;
import com.datacomo.wins.service.SysUserInfoService;
import com.datacomo.wins.service.TestService;

/**
 * @author zhaizhanpo
 *
 */
@Service("sysUserInfoService")
public class SysUserInfoServiceImpl implements SysUserInfoService {

	private static Logger logger = Logger.getLogger(SysUserInfoServiceImpl.class.getName());
	@Autowired
	SysUserInfoBusiness sysUserInfoBusiness;
	@Override
	public Map<String, Object> findSysUserInfoByCondtion(int visitId, int id,
			Map<String, Object> condition) throws ServiceException {
		logger.info(SysUserInfoServiceImpl.class.getName()+"findSysUserInfoByCondtion start");
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		try {
			if(condition!=null){
				result=sysUserInfoBusiness.getInfoByCondition(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(SysUserInfoServiceImpl.class.getName()+"findSysUserInfoByCondtion end");
		return result;
	}
	@Override
	public boolean updateInfo(int visitId, Map<String, Object> condition, int id)
			throws ServiceException {
		logger.info("updateInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
            logger.debug("id:"+id);
        }
        boolean result=false;
        try {
            result=sysUserInfoBusiness.updateInfo(visitId,condition,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateInfo menthod end");
        return result;
	}

}
