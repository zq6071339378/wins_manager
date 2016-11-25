package com.datacomo.wins.mapper;

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/22.
 */
public interface PolicyLabelClassifyMapper extends BaseMapper {
    /**
     * 查询全部人群标签分类
     * @return
     * @throws MapperException
     */
    public List<Map<String,Object>> findAllInfo() throws MapperException;

    /**
     * 插入人群标签分类
     * @param condition
     * @return
     * @throws BusinessException
     */
    public int insertAllInfo(Map<String, Object> condition) throws MapperException;

    /**
     * 删除所有人群标签分类
     * @return
     * @throws BusinessException
     */
    public int deleteAllInfo() throws MapperException;



}
