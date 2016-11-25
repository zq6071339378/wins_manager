package com.datacomo.wins.business;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.BusinessException;

/**
 * 
 * @author liwenjie 
 *
 */
public interface PolicyinfoBusiness extends BaseBusiness {

	public List<Map<String, Object>> showReport(int visitId,Map<String, Object> condition)throws BusinessException;

	public int showReportcount(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> showReportIncludeActivityInfo(Map<String, Object> condition)throws BusinessException;

	public int showReportIncludeActivityCount(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> showDetails(int visitId,Map<String, Object> condition)throws BusinessException;

	public int showDetailscount(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> clickDetails(int visitId,Map<String, Object> condition)throws BusinessException;

	public int clickDetailscount(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> closeDetails(int visitId,Map<String, Object> condition)throws BusinessException;

	public int closeDetailscount(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> reportDetails(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> reportExcel(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> detailsExcel(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> clickExcel(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> closeExcel(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> detailsExcelUser(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> singlePolicyShow(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> reportDataExcel(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> reportIncludeActivityDataExcel(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> clickExcelUser(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> closeExcelUser(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> nameListBak(int visitId,Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> policyEveryData(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> policyShowTotalData(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> policyEveryDayData(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> policyEveryDayInfo(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> policyOneDayInfo(Map<String, Object> condition)throws BusinessException;

}
