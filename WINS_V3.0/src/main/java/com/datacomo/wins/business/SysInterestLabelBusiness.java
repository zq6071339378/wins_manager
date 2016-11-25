package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/22.
 */
public interface SysInterestLabelBusiness extends BaseBusiness {
    /**
     * 删除所有人群标签用户
     * @return
     * @throws BusinessException
     */
    public int deleteAll() throws BusinessException;

    /**
     * 删除所有群组标签
     * @return
     * @throws BusinessException
     */
    public int deleteAllInfo() throws BusinessException;

    /**
     * 插入单条群组标签数据
     * @param condition
     * @return
     * @throws BusinessException
     */
    public int insertOneInfo(Map<String,Object> condition) throws BusinessException;

}
