package com.datacomo.wins.business.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datacomo.wins.business.PolicyinfoBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.PolicyinfoMapper;



@Repository("policyinfoBusiness")
public class PolicyinfoBusinessImpl implements PolicyinfoBusiness {
	
	private static Logger logger = Logger.getLogger(PolicyinfoBusinessImpl.class.getName());
	@Autowired
	private PolicyinfoMapper policyinfoMapper;

	@Override
	public int insertInfo(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int visitId, int id) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCondition(int visitId, Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> getInfo(int visitId, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getInfoByCondition(int visitId,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findByCondition(int visitId,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateInfo(int visitId, Map<String, Object> condition, int id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByCondition(int visitId, Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isExist(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return 0;
	}
/**
 * 查询报表总信息
 */
	@Override
	public List<Map<String, Object>> showReport(int visitId,Map<String, Object> condition) {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showReport start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.showReport(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showReport end");
		return result;
	}

	@Override
	public int showReportcount(Map<String, Object> condition) {
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showReportcount end");
		if (logger.isDebugEnabled()) {
			logger.debug("condition："+condition);
		}
		int result=0;
		try {
			result = policyinfoMapper.showReportcount(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			return 0;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showReportcount end");
		return result;
	}

	@Override
	public List<Map<String, Object>> showReportIncludeActivityInfo(Map<String, Object> condition){
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showReportIncludeActivityInfo start");
		if (logger.isDebugEnabled()) {
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.showReportIncludeActivityInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showReportIncludeActivityInfo end");
		return result;
	}

	@Override
	public int showReportIncludeActivityCount(Map<String, Object> condition){
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showReportIncludeActivityCount start");
		if (logger.isDebugEnabled()) {
			logger.debug("condition:"+condition);
		}
		int result=0;
		try {
			result = policyinfoMapper.showReportIncludeActivityCount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showReportIncludeActivityCount end");
		return result;
	}


	@Override
	public List<Map<String, Object>> showDetails(int visitId,Map<String, Object> condition){
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showDetails start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.showDetails(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" showDetails end");
		return result;
	}

	@Override
	public int showDetailscount(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result = policyinfoMapper.showDetailscount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> clickDetails(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" clickDetails start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.clickDetails(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" clickDetails end");
		return result;
	}

	@Override
	public int clickDetailscount(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result = policyinfoMapper.clickDetailscount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> closeDetails(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" closeDetails start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.closeDetails(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" closeDetails end");
		return result;
	}

	@Override
	public int closeDetailscount(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result = policyinfoMapper.closeDetailscount(condition);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> reportDetails(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" reportDetails start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.reportDetails(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" reportDetails end");
		return result;
	}

	@Override
	public List<Map<String, Object>> reportExcel(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" reportExcel start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.reportExcel(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" reportExcel end");
		return result;
	}

	@Override
	public List<Map<String, Object>> detailsExcel(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" detailsExcel start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.detailsExcel(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" detailsExcel end");
		return result;
	}

	@Override
	public List<Map<String, Object>> clickExcel(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" clickExcel start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.clickExcel(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" clickExcel end");
		return result;
	}

	@Override
	public List<Map<String, Object>> closeExcel(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" closeExcel start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.closeExcel(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" closeExcel end");
		return result;
	}

	@Override
	public List<Map<String, Object>> detailsExcelUser(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" detailsExcelUser start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.detailsExcelUser(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" detailsExcelUser end");
		return result;
	}

	@Override
	public List<Map<String, Object>> singlePolicyShow(int visitId, Map<String, Object> condition) {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" singlePolicyShow start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.singlePolicyShow(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" singlePolicyShow end");
		return result;
	}

	@Override
	public List<Map<String, Object>> reportDataExcel(int visitId,Map<String, Object> condition){
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" reportDataExcel start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.reportDataExcel(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" reportDataExcel end");
		return result;
	}

	@Override
	public List<Map<String, Object>> reportIncludeActivityDataExcel(int visitId, Map<String, Object> condition) {
		logger.info(PolicyinfoBusinessImpl.class.getName()+" reportIncludeActivityDataExcel start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.reportIncludeActivityDataExcel(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" reportIncludeActivityDataExcel end");
		return result;
	}

	@Override
	public List<Map<String, Object>> clickExcelUser(int visitId,Map<String, Object> condition) {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" clickExcelUser start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.clickExcelUser(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" clickExcelUser end");
		return result;
	}

	@Override
	public List<Map<String, Object>> closeExcelUser(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		logger.info(PolicyinfoBusinessImpl.class.getName()+" closeExcelUser start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.closeExcelUser(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" closeExcelUser end");
		return result;
	}

	@Override
	public List<Map<String, Object>> nameListBak(int visitId, Map<String, Object> condition) {
		logger.info(PolicyinfoBusinessImpl.class.getName()+" nameListBak start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.nameListBak(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyinfoBusinessImpl.class.getName()+" nameListBak end");
		return result;
	}

	@Override
	public List<Map<String, Object>> policyEveryData(Map<String, Object> condition) {
		logger.info("policyEveryData method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.policyEveryData(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info("policyEveryData method end");
		return result;
	}

	@Override
	public List<Map<String, Object>> policyShowTotalData(Map<String, Object> condition) throws BusinessException {
		logger.info("policyShowTotalData method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.policyShowTotalData(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info("policyShowTotalData method end");
		return result;
	}

	@Override
	public List<Map<String, Object>> policyEveryDayData(Map<String, Object> condition) throws BusinessException {
		logger.info("policyEveryDayData method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.policyEveryDayData(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info("policyEveryDayData method end");
		return result;
	}

	@Override
	public List<Map<String, Object>> policyEveryDayInfo(Map<String, Object> condition){
		logger.info("policyEveryDayInfo method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.policyEveryDayInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info("policyEveryDayInfo method end");
		return result;
	}

	@Override
	public List<Map<String, Object>> policyOneDayInfo(Map<String, Object> condition) throws BusinessException {
		logger.info("policyOneDayInfo method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyinfoMapper.policyOneDayInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info("policyOneDayInfo method end");
		return result;
	}


}
