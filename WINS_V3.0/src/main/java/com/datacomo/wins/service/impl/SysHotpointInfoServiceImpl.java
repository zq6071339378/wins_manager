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
import com.datacomo.wins.business.SysCityInfoBusiness;
import com.datacomo.wins.business.SysHotpointInfoBusiness;
import com.datacomo.wins.business.SysProvinceInfoBusiness;
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.NavInfoService;
import com.datacomo.wins.service.SysCityInfoService;
import com.datacomo.wins.service.SysHotpointInfoService;
import com.datacomo.wins.service.SysProvinceInfoService;
import com.datacomo.wins.service.TestService;

/**
 * @author gongkaihui
 *
 */
@Service("sysHotpointInfoService")
public class SysHotpointInfoServiceImpl implements SysHotpointInfoService {

	private static Logger logger = Logger.getLogger(SysHotpointInfoServiceImpl.class.getName());
	Map<String, Object> result = new HashMap<String,Object>();
	@Autowired
	SysHotpointInfoBusiness sysHotpointInfoBusiness;
	@Override
	public Map<String, Object> findHotpointList(int visitId, int id,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findHotpointList method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = sysHotpointInfoBusiness.findByCondition(visitId, condition);
				result.put("hotpoints", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findHotpointList method end"); 
		return result;
	}

}
