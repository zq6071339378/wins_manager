package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
public interface SysMediaBusiness extends BaseBusiness {
    /**
     * 删除所有
     * @return
     * @throws BusinessException
     */
    public int deleteAll() throws BusinessException;

	public List<Map<String, Object>> searchMedia(int visitId, Map<String, Object> condition) throws BusinessException;

	public List<Map<String, Object>> searchPrice(int visitId, Map<String, Object> condition) throws BusinessException;
}
