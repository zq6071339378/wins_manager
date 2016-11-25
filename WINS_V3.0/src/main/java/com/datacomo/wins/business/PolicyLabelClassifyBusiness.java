package com.datacomo.wins.business;

import com.datacomo.wins.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/22.
 */
public interface PolicyLabelClassifyBusiness extends BaseBusiness {
    /**
     * 查询全部
     * @return
     * @throws BusinessException
     */
    public List<Map<String,Object>> findAllInfo() throws BusinessException;

    /**
     * 插入人群标签分类
     * @param condition
     * @return
     * @throws BusinessException
     */
    public int insertAllInfo(Map<String, Object> condition) throws BusinessException;

    /**
     * 删除所有人群标签分类
     * @return
     * @throws BusinessException
     */
    public int deleteAllInfo() throws BusinessException;
}
