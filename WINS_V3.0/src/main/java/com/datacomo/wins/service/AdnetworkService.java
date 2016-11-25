package com.datacomo.wins.service;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.ServiceException;

public interface AdnetworkService extends BaseService {

	 public Map<String,Object> findadnetworkByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException;
	 
	 public Map<String,Object> getAdnetworkInfo(int visitId, Map<String, Object> condition) throws ServiceException;
	   
	 public int insertAdnetworkInfo(int visitId, Map<String, Object> condition) throws ServiceException;
		/*
		 * 查询广告主名
		 * */	
	 public List<Map<String, Object>> searchAdnetwork(int visitId, Map<String, Object> condition) throws ServiceException;
	   
	 public int deleteAdnetworkById(int visitId, int id) throws ServiceException;
	 
	 public boolean updateAdnetworkInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException;
}
