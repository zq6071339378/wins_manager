/**
 * 
 */
package com.datacomo.wins.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.AreaMngBusiness;
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.AreaMngService;
import com.datacomo.wins.service.TestService;
import com.datacomo.wins.util.IpConvertUtil;

/**
 * @author gongkaihui
 *
 */
@Service("areaMngService")
public class AreaMngServiceImpl implements AreaMngService {

	private static Logger logger = Logger.getLogger(AreaMngServiceImpl.class.getName());
	@Autowired
	AreaMngBusiness areaMngBusiness;
	@Override
	public Map<String, Object> findAreaList(int visitId, int id,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findAreaList method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = areaMngBusiness. findByCondition(visitId, condition);
				for(int i=0;i<list.size();i++){
					Map map=(Map)list.get(i);
					Long startIp=Long.parseLong((String) map.get("startIp"));
					if(startIp instanceof Long){
						String s1=IpConvertUtil.longToIP(startIp);
						map.put("startIp", s1);
					}
					
					Long endIp=Long.parseLong((String) map.get("endIp"));
					if(endIp instanceof Long){
						String s2=IpConvertUtil.longToIP(endIp);
						map.put("endIp", s2);
					}
					
					
					
				}
				int count = areaMngBusiness.countByCondition(visitId, condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("areaLists", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findAreaList method end"); 
		return result;
	}
	@Override
	public Map<String, Object> getAreaById(int visitId, int id) throws ServiceException {
		logger.info("getAreaById method start"); 
		Map<String, Object> result = new HashMap<String,Object>();
		try {
				result = areaMngBusiness.getInfo(visitId, id);
				long startIp=Long.parseLong((String) result.get("startIp"));
				String s1=IpConvertUtil.longToIP(startIp);
				result.put("startIp", s1);
				long endIp=Long.parseLong((String) result.get("endIp"));
				String s2=IpConvertUtil.longToIP(endIp);
				result.put("endIp", s2);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("getAreaById method end"); 
		return result;
	}
	@Override
	public int insertArea(Map<String, Object> condition)
			throws ServiceException {
		try {
			areaMngBusiness.insertInfo(condition);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int deleteAreaById(int id) throws ServiceException {
		try {
			areaMngBusiness.deleteById(0, id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public void updateAreaById(int visitId, Map<String, Object> condition,
			int id) throws ServiceException {
		try {
			areaMngBusiness.updateInfo(0, condition, 0);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
