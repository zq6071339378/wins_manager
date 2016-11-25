package com.datacomo.wins.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.datacomo.wins.business.ActivityManageBusiness;
import com.datacomo.wins.business.CustomerManageBusiness;
import com.datacomo.wins.controller.Config;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.PolicyinfoBusiness;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.PolicyinfoService;


@Service(value="policyinfoService")
public class PolicyinfoServiceImpl implements PolicyinfoService {

	private static Logger logger = Logger.getLogger(PolicyinfoServiceImpl.class.getName());
	@Autowired
	private PolicyinfoBusiness policyinfoBusiness;

	@Override
	public Map<String, Object> showReport(int visitId,Map<String, Object> condition) throws ServiceException {
		logger.info("showReport method start");
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		List<Map<String,Object>> list2 = null;
		String policyIds="";
		try {
			if(condition!=null){
				boolean isEnglish = Boolean.valueOf(String.valueOf(Config.message.get("isEnglish")));
				if(isEnglish){  //英文版-----包含活动 广告主信息
					list = policyinfoBusiness.showReportIncludeActivityInfo(condition);
					int count = policyinfoBusiness.showReportIncludeActivityCount(condition);
					if(condition.containsKey("page")){
						Pagination page =  (Pagination)condition.get("page");
						page.setTotalCount(count);
						page.setCurrentPage(page.getStart()/page.getLimit()+1);
						result.putAll(condition);
						result.put("page", page);
						result.put("LIST", list);
					}
					condition.remove("page");
					list2 = policyinfoBusiness.showReportIncludeActivityInfo(condition);
					if(list2.size()>0){
						int i=1;
						for (Map<String,Object> map:list2){
							if(i==list2.size()){
								policyIds+=String.valueOf(map.get("PolicyId"));
							}else{
								policyIds+=String.valueOf(map.get("PolicyId"))+",";
							}
							i++;
						}
					}
					result.put("policyIds",policyIds);
				}else{
					list = policyinfoBusiness.showReport(visitId, condition);
					int count = policyinfoBusiness.showReportcount(condition);
					if(condition.containsKey("page")){
						Pagination page =  (Pagination)condition.get("page");
						page.setTotalCount(count);
						page.setCurrentPage(page.getStart()/page.getLimit()+1);
						result.putAll(condition);
						result.put("page", page);
						result.put("LIST", list);
					}
					condition.remove("page");
					list2 = policyinfoBusiness.showReport(visitId, condition);
					if(list2.size()>0){
						int i=1;
						for (Map<String,Object> map:list2){
							if(i==list2.size()){
								policyIds+=String.valueOf(map.get("PolicyId"));
							}else{
								policyIds+=String.valueOf(map.get("PolicyId"))+",";
							}
							i++;
						}
					}
					result.put("policyIds",policyIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("showReport method end"); 
		return result;
	}


	@Override
	public Map<String, Object> showDetails(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("showDetails method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.showDetails(visitId, condition);
				int count = policyinfoBusiness.showDetailscount(condition);
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
		logger.info("showDetails method end"); 
		return result;
	}


	@Override
	public Map<String, Object> clickDetails(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("clickDetails method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.clickDetails(visitId, condition);
				int count = policyinfoBusiness.clickDetailscount(condition);
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
		logger.info("clickDetails method end"); 
		return result;
	}


	@Override
	public Map<String, Object> closeDetails(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("closeDetails method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.closeDetails(visitId, condition);
				int count = policyinfoBusiness.closeDetailscount(condition);
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
		logger.info("closeDetails method end"); 
		return result;
	}


	@Override
	public Map<String, Object> reportDetails(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("reportDetails method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		List<Map<String,Object>> lis = null;

		try {
			if(condition!=null){
				
				list = policyinfoBusiness.reportDetails(visitId, condition);
				lis=policyinfoBusiness.showReport(visitId, condition);

					result.putAll(condition);
					result.put("LIST", list);
					result.put("LIS", lis);

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("reportDetails method end"); 
		return result;
	}


	@Override
	public Map<String, Object> reportExcel(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("reportExcel method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.reportExcel(visitId, condition);
					result.put("LIST", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("reportExcel method end"); 
		return result;
	}


	@Override
	public Map<String, Object> detailsExcel(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("detailsExcel method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.detailsExcel(visitId, condition);
					result.put("LIST", list);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("detailsExcel method end"); 
		return result;
	}


	@Override
	public Map<String, Object> clickExcel(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("clickExcel method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.clickExcel(visitId, condition);
					result.put("LIST", list);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("clickExcel method end"); 
		return result;
	}


	@Override
	public Map<String, Object> closeExcel(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("closeExcel method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.closeExcel(visitId, condition);
				result.put("LIST", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("closeExcel method end"); 
		return result;
	}


	@Override
	public Map<String, Object> detailsExcelUser(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("detailsExcelUser method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.detailsExcelUser(visitId, condition);
					result.put("LIST", list);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("detailsExcelUser method end"); 
		return result;
	}


	@Override
	public Map<String, Object> reportDataExcel(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("reportDataExcel method start");
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				boolean isEnglish = Boolean.valueOf(String.valueOf(Config.message.get("isEnglish")));
				if(isEnglish){
					list=policyinfoBusiness.reportIncludeActivityDataExcel(visitId,condition);
				}else{
					list = policyinfoBusiness.reportDataExcel(visitId,condition);
				}
			}
			result.put("LIST",list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("reportDataExcel method end");
		return result;
	}




	@Override
	public Map<String, Object> singlePolicyShow(int visitId,Map<String, Object> condition) {
		// TODO Auto-generated method stub
		logger.info("singlePolicyShow method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.singlePolicyShow(visitId, condition);
				result.put("LIST", list);
				result.putAll(condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyShow method end"); 
		return result;
	}




	@Override
	public Map<String, Object> clickExcelUser(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("clickExcelUser method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.clickExcelUser(visitId, condition);
				result.put("LIST", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("clickExcelUser method end"); 
		return result;
	}




	@Override
	public Map<String, Object> closeExcelUser(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("closeExcelUser method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = policyinfoBusiness.closeExcelUser(visitId, condition);
					result.put("LIST", list);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("closeExcelUser method end"); 
		return result;
	}

	@Override
	public Map<String, Object> nameListBak(int visitId, Map<String, Object> condition) throws ServiceException {
		logger.info("nameListBak method start");
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyinfoBusiness.nameListBak(visitId, condition);
				result.put("LIST", list);
				result.putAll(condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("nameListBak method end");
		return result;
	}

	@Override
	public Map<String, Object> policyEveryData(int visitId, Map<String, Object> condition){
		logger.info("policyEveryData method start");
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyinfoBusiness.policyEveryData(condition);
				result.put("LIST", list);
				result.putAll(condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("policyEveryData method end");
		return result;
	}

	@Override
	public Map<String, Object> policyShowTotalData(int visitId, Map<String, Object> condition){
		logger.info("policyShowTotalData method start");
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyinfoBusiness.policyShowTotalData(condition);
				result.put("LIST", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("policyShowTotalData method end");
		return result;
	}

	@Override
	public Map<String, Object> policyEveryDayData(Map<String, Object> condition){
		logger.info("policyEveryDayData method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyinfoBusiness.policyEveryDayData(condition);
				result.putAll(condition);
				result.put("LIST", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("policyEveryDayData method end");
		return result;
	}

	@Override
	public Map<String, Object> policyEveryDayInfo(Map<String, Object> condition){
		logger.info("policyEveryDayInfo method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyinfoBusiness.policyEveryDayInfo(condition);
				result.putAll(condition);
				result.put("LIST", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("policyEveryDayInfo method end");
		return result;
	}

	@Override
	public Map<String, Object> policyOneDayInfo(Map<String, Object> condition) throws ServiceException {
		logger.info("policyOneDayInfo method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyinfoBusiness.policyOneDayInfo(condition);
				result.putAll(condition);
				result.put("LIST", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("policyOneDayInfo method end");
		return result;
	}

}
