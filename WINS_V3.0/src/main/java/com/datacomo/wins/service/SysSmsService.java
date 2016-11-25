package com.datacomo.wins.service;

import java.util.Map;

import com.datacomo.wins.exception.ServiceException;

/**
 * 
 * @author gongkaihui
 *
 */
public interface SysSmsService {
	/**
	 * 保存短信信息
	 * @param condition
	 * @return
	 * @throws ServiceException
	 */
	public int insertSms(Map<String,Object> condition) throws ServiceException;
}
