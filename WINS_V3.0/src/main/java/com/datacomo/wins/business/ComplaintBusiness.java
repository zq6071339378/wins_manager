package com.datacomo.wins.business;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.BusinessException;

/**
 * 
 * @author liwenjie
 *
 */
public interface ComplaintBusiness extends BaseBusiness {

	public List<Map<String, Object>> showComplaint(int visitId,Map<String, Object> condition)throws BusinessException;

	public int showComplaintcount(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> showPush(int visitId,Map<String, Object> condition)throws BusinessException;

	public int showPushcount(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> showClick(int visitId,Map<String, Object> condition)throws BusinessException;

	public int showClickcount(Map<String, Object> condition)throws BusinessException;

	public List<Map<String, Object>> showOrder(int visitId,Map<String, Object> condition)throws BusinessException;

	public int showOrdercount(Map<String, Object> condition)throws BusinessException;



}
