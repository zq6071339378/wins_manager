package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;

import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
public interface IPSManageBusiness extends BaseBusiness {
    public int getMaxValue() throws BusinessException;
}
