package com.datacomo.wins.business;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.BusinessException;

public interface AdnetworkBusiness extends BaseBusiness {
	/**
	 * 条件获取Adnetwork名
	 * @param condition
	 * @return
	 */
	public List<Map<String, Object>> searchAdnetwork(int visitId, Map<String, Object> condition) throws BusinessException;

}
