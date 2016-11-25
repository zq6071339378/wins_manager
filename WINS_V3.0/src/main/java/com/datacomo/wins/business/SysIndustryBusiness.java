package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
public interface SysIndustryBusiness extends BaseBusiness {
    /**
     * 删除所有
     * @return
     * @throws BusinessException
     */
    public int deleteAll() throws BusinessException;

    /**
     * 查询所有
     * @return
     * @throws BusinessException
     */
    public List<Map<String,Object>> findAll() throws BusinessException;
    public List<Map<String, Object>> findByIndustry(int visitId, Map<String, Object> condition) throws BusinessException;
    /**
     * 查询所有行业标签
     * @return
     * @throws BusinessException
     */
	public List<Map<String, Object>> findFirstIndustry(int visitId,Map<String,Object> condition);
	public List<Map<String, Object>> findChildIndustry(int visitId, Map<String, Object> condition) throws BusinessException;
}
