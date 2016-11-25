package com.datacomo.wins.service;

import java.util.Map;

import com.datacomo.wins.exception.ServiceException;

/**
 * 
 * @author liwenjie
 *
 */
public interface PolicyinfoService extends BaseService {

	public Map<String, Object> showReport(int visitId, Map<String, Object> condition) throws ServiceException;

	public Map<String, Object> showDetails(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> clickDetails(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> closeDetails(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> reportDetails(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> reportExcel(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> detailsExcel(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> clickExcel(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> closeExcel(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> detailsExcelUser(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> reportDataExcel(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> singlePolicyShow(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> clickExcelUser(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> closeExcelUser(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> nameListBak(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> policyEveryData(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> policyShowTotalData(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> policyEveryDayData(Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> policyEveryDayInfo(Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> policyOneDayInfo(Map<String, Object> condition)throws ServiceException;

}
