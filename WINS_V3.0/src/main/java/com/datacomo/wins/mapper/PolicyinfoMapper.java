package com.datacomo.wins.mapper;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.MapperException;

/**
 * 
 * @author liwenjie
 *
 */
public interface PolicyinfoMapper extends BaseMapper {

	public List<Map<String, Object>> showReport(Map<String, Object> condition)throws MapperException;
	public int showReportcount(Map<String, Object> condition)throws MapperException;

	public List<Map<String, Object>> showReportIncludeActivityInfo(Map<String, Object> condition)throws MapperException;
	public int showReportIncludeActivityCount(Map<String, Object> condition)throws MapperException;
	
	public List<Map<String, Object>> showDetails(Map<String, Object> condition)throws MapperException;
	public int showDetailscount(Map<String, Object> condition)throws MapperException;
	
	public List<Map<String, Object>> clickDetails(Map<String, Object> condition)throws MapperException;
	public int clickDetailscount(Map<String, Object> condition)throws MapperException;
	
	public List<Map<String, Object>> closeDetails(Map<String, Object> condition)throws MapperException;
	public int closeDetailscount(Map<String, Object> condition)throws MapperException;

	public List<Map<String, Object>> reportDetails(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> reportExcel(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> detailsExcel(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> clickExcel(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> closeExcel(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> detailsExcelUser(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> singlePolicyShow(Map<String, Object> condition)throws MapperException;

	public List<Map<String, Object>> reportDataExcel(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> reportIncludeActivityDataExcel(Map<String, Object> condition)throws MapperException;

	public List<Map<String, Object>> clickExcelUser(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> closeExcelUser(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> nameListBak(Map<String, Object> condition)throws MapperException;

	public List<Map<String, Object>> policyEveryData(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> policyShowTotalData(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> policyEveryDayData(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> policyEveryDayInfo(Map<String, Object> condition)throws MapperException;
	public List<Map<String, Object>> policyOneDayInfo(Map<String, Object> condition)throws MapperException;
}
