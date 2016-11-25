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
import com.datacomo.wins.business.PushSummaryBusiness;
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.mapper.PushSummaryMapper;
import com.datacomo.wins.service.NavInfoService;
import com.datacomo.wins.service.PushSummaryService;
import com.datacomo.wins.service.TestService;

/**
 * @author gongkaihui
 *
 */
@Service("pushSummaryService")
public class PushSummaryServiceImpl implements PushSummaryService {

	private static Logger logger = Logger.getLogger(PushSummaryServiceImpl.class.getName());
	Map<String, Object> result = new HashMap<String,Object>();
	@Autowired
	PushSummaryBusiness pushSummaryBusiness;
	@Override
	public Map<String, Object> getPushBaseDataByCondition(int visitId,
			Map<String, Object> condition) throws ServiceException {
		 logger.info("getPushBaseDataByCondition method start");
	        if (logger.isDebugEnabled()) {
	            logger.debug("visitId:"+visitId);
	            logger.debug("condition:"+condition);
	        }
	        Map<String, Object> result = null;
	        try {
	        	result=	pushSummaryBusiness.getPushBaseDataByCondition(0, condition);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e);
	            return null;
	        }
	        logger.info("getPushBaseDataByCondition method end");
	        return result;
	}
	@Override
	public Map<String, Object> findPushAndShowAndClickNumByCondition(
			int visitId, Map<String, Object> condition) throws ServiceException {
		logger.info("findPushAndShowAndClickNumByCondition method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list =pushSummaryBusiness.findPushAndShowAndClickNumByCondition(0, condition);
				if(condition.containsKey("page")){
					result.put("pushLists", list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findPushAndShowAndClickNumByCondition method end"); 
		return result;
	}
	
}
