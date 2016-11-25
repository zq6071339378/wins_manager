package com.datacomo.wins.service;

import java.util.Map;

import com.datacomo.wins.exception.ServiceException;

/**
 * 
 * @author liwenjie
 *
 */
public interface ComplaintService extends BaseService {

	public Map<String, Object> showComplaint(int visitId, Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> showPush(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> showClick(int visitId,Map<String, Object> condition)throws ServiceException;

	public Map<String, Object> showOrder(int visitId,Map<String, Object> condition)throws ServiceException;

}
