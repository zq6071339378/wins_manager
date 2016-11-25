package com.datacomo.wins.mapper;

import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
public interface PolicyIPSMapper extends BaseMapper {
    public int getMaxValue() throws MapperException;
}
