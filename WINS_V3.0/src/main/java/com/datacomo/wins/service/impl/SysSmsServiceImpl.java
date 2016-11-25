package com.datacomo.wins.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacomo.wins.business.SysSmsBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysSmsService;


/**
 * 
 * @author gongkaihui
 *
 */
@Service("sysSmsService")
public class SysSmsServiceImpl implements SysSmsService{
	
	@Autowired
	private SysSmsBusiness smsBusiness;
	private static Logger logger = Logger.getLogger(SysSmsServiceImpl.class.getName());
	@Override
	public int insertSms(Map<String, Object> condition) throws ServiceException {
		logger.info(SysSmsServiceImpl.class.getName()+" insertSms start");
		try {
			smsBusiness.insertInfo(condition);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(SysSmsServiceImpl.class.getName()+" insertSms end");
		return 0;
	}
}
