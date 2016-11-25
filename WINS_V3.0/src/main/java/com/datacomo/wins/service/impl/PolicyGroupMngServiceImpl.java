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
import com.datacomo.wins.business.PolicyGroupMngBusiness;
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.business.impl.PolicyGroupMngBusinessImp;
import com.datacomo.wins.business.impl.PolicyinfoBusinessImpl;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.NavInfoService;
import com.datacomo.wins.service.PolicyGroupMngService;
import com.datacomo.wins.service.TestService;

/**
 * @author gongkaihui
 *
 */
@Service("policyGroupMngService")
public class PolicyGroupMngServiceImpl implements PolicyGroupMngService {

	private static Logger logger = Logger.getLogger(PolicyGroupMngServiceImpl.class.getName());
	@Autowired
	PolicyGroupMngBusiness policyGroupMngBusiness;
	@Override
	public Map<String, Object> findGroupList(int visitId, int id,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findGroupList method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyGroupMngBusiness. findByCondition(visitId, condition);
				int count = policyGroupMngBusiness.countByCondition(visitId, condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("groupLists", list);
					result.put("page", page);
				}else {
					result.put("groupLists", list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findGroupList method end"); 
		return result;
	}
	@Override
	public Map<String, Object> findGroupMemByGroupId(int visitId, int id,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findGroupList method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyGroupMngBusiness.findGroupMemByGroupId(visitId, condition);
				result.put("memberLists", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findGroupList method end"); 
		return result;
	}
	@Override
	public int insertGroupMember(Map<String, Object> condition)
			throws ServiceException {
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" insertGroupMember start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int i=0;
		try {
			i=policyGroupMngBusiness.insertGroupMember(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" insertGroupMember end");
		return i;
	}
	@Override
	public int insertInfo(Map<String, Object> condition)
			throws ServiceException {
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" insertInfo start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int i=0;
		try {
			i=policyGroupMngBusiness.insertInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" insertInfo end");
		return i;
	}
	@Override
	public int deleteGroupById(int id) throws ServiceException {
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" deleteGroupById start");
		try {
			policyGroupMngBusiness.deleteGroupById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" deleteGroupById end");
		return 0;
	}
	@Override
	public int deleteById(int id) throws ServiceException {
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" deleteById start");
		try {
			policyGroupMngBusiness.deleteById(0, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" deleteById end");
		return 0;
	}
	@Override
	public int insertData(Map<String,Object> condition) throws ServiceException {
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" add start");
		try {
			policyGroupMngBusiness.insertData(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" add end");
		return 0;
	}
	@Override
	public void updateGroupById(Map<String, Object> condition){
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" updateGroupById start");
		try {
			policyGroupMngBusiness.updateGroupById(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" updateGroupById end");
	}

	@Override
	public int getGroupUsers(Map<String, Object> condition){
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" getGroupUsers start");
		int result=0;
		try {
			result=policyGroupMngBusiness.isExist(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return 0;
		}
		logger.info(PolicyGroupMngServiceImpl.class.getName()+" getGroupUsers end");
		return result;
	}

}
