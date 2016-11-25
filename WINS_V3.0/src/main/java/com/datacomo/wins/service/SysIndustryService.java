package com.datacomo.wins.service;

import com.datacomo.wins.exception.ServiceException;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
public interface SysIndustryService extends BaseService {
    /**
     * 开始执行接口
     * @return
     * @throws ServiceException
     */
    public int startExcuteRequest();

    /**
     * 定时查询行业标签入库
     * @return
     * @throws ServiceException
     */
    public int updateIndustryLabel() throws ServiceException;

    /**
     * 定时查询媒体标签入库
     * @return
     * @throws ServiceException
     */
    public int updateMediaLabel() throws ServiceException;
	/*
	 * 通过parent_id查询行业二级标签
	 *
	 *
	 * */
	public Map<String, Object> findChildIndustry(int visitId, Map<String, Object> condition) throws ServiceException;

    /**
     * 查询所有
     * @return
     * @throws ServiceException
     */

    
    public Map<String, Object> findByIndustry(int visitId, Map<String, Object> condition) throws  ServiceException;
    
/*    public Map<String,Object> getGlobalInfo(int visitId,Map<String,Object> condition) throws ServiceException;*/

    public Map<String, Object> findFirstIndustry(int visitId, Map<String, Object> condition) throws  ServiceException;
    
}
