package com.datacomo.wins.mapper;

import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
public interface SysMediaLabelMapper extends BaseMapper {
    /**
     * 删除全部
     * @return
     * @throws MapperException
     */
    public int deleteAll() throws MapperException;

	public List<Map<String, Object>> searchMedia(Map<String, Object> condition) throws MapperException;
	/**
     *行业标签URL价格查询
     * @return
     * @throws MapperException
     */
	public List<Map<String, Object>> searchPrice(Map<String, Object> condition) throws MapperException;

}
