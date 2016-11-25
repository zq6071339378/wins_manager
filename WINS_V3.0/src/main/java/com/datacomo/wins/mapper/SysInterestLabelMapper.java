package com.datacomo.wins.mapper;

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/22.
 */
public interface SysInterestLabelMapper extends BaseMapper {
    /**
     * 删除所有人群标签群组用户
     * @return
     * @throws BusinessException
     */
    public int deleteAll() throws MapperException;

    /**
     * 删除所有人群标签群组
     * @return
     * @throws BusinessException
     */
    public int deleteAllInfo() throws MapperException;

    /**
     * 插入单条数据
     * @param condition
     * @return
     * @throws MapperException
     */
    public int insertOneInfo(Map<String,Object> condition) throws MapperException;

}
