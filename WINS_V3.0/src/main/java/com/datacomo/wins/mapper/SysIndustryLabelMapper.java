package com.datacomo.wins.mapper;

import com.datacomo.wins.exception.MapperException;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
public interface SysIndustryLabelMapper extends BaseMapper {
    /**
     * 删除全部
     * @return
     * @throws MapperException
     */
    public int deleteAll() throws MapperException;

    /**
     * 查询全部
     * @return
     * @throws MapperException
     */
    public List<Map<String,Object>> findAll() throws MapperException;
    
    /**
     * 查询全部
     * @return
     * @throws MapperException
     */
    public Map<String,Object>findByIndustry() throws MapperException;
    
    public List<Map<String, Object>> findByIndustry(Map<String,Object> condition) throws MapperException;
    /**
     * 查询全部行业标签
     * @return
     * @throws MapperException
     */
    public List<Map<String, Object>> findFirstIndustry(Map<String,Object> condition) throws MapperException;
	public List<Map<String, Object>> findChildIndustry(Map<String, Object> condition) throws MapperException;
	
}
