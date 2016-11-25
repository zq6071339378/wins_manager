package com.datacomo.wins.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.ComplaintBusiness;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.ComplaintService;
/**
 * 
 * @author liwenjie
 *
 */
@Service(value="complaintService")
public class ComplaintServiceImpl implements ComplaintService {
	private static Logger logger = Logger.getLogger(ComplaintServiceImpl.class.getName());
	@Autowired
	ComplaintBusiness complaintBusiness;
	
	
	@Override
	public Map<String, Object> showComplaint(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("showComplaint method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = complaintBusiness.showComplaint(visitId, condition);
				int count = complaintBusiness.showComplaintcount(condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("LIST", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("showComplaint method end"); 
		return result;
	}


	@Override
	public Map<String, Object> showPush(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("showPush method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = complaintBusiness.showPush(visitId, condition);
				int count = complaintBusiness.showPushcount(condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("LIST", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("showPush method end"); 
		return result;
	}


	@Override
	public Map<String, Object> showClick(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("showClick method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = complaintBusiness.showClick(visitId, condition);
				int count = complaintBusiness.showClickcount(condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("LIST", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("showClick method end"); 
		return result;
	}


	@Override
	public Map<String, Object> showOrder(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("showOrder method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = complaintBusiness.showOrder(visitId, condition);
				int count = complaintBusiness.showOrdercount(condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("LIST", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("showOrder method end"); 
		return result;
	}
	
	

}
