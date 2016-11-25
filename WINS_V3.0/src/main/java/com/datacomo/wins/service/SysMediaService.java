package com.datacomo.wins.service;

import com.datacomo.wins.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
public interface SysMediaService extends BaseService {
	/*
	 * 行业标签媒体URL查询
	 *
	 * */
	public Map<String, Object> searchMedia(int visitId, Map<String, Object> condition) throws ServiceException;
	/*
	 * 行业标签URL价格查询
	 *
	 *
	 * */
	public Map<String, Object> searchPrice(int visitId, Map<String, Object> condition) throws ServiceException;

	/**
	 * 查询所有媒体标签
	 * @param visitId
	 * @param condition
	 * @return
	 * @throws ServiceException
     */
	public List<Map<String,Object>> findAllMedia(int visitId,Map<String,Object> condition) throws ServiceException;

	/**
	 * 根据条件查询媒体URL信息
	 * @param visitId
	 * @param condition
	 * @return
	 * @throws ServiceException
     */
	public Map<String,Object> findMedilInfo(int visitId,Map<String,Object> condition) throws ServiceException;
}
