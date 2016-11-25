package com.datacomo.wins.business;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;

/**
 * Created by duanlinzhuo on 2016/5/16.
 */
public interface SysNewsInfoBusiness extends BaseBusiness {
    /**
     * 根据条件获取未读消息数目
     * @param condition
     * @return
     * @throws BusinessException
     */
    public int getNotReadCountByCondition(Map<String,Object> condition) throws BusinessException;

    public List<Map<String,Object>> getNotReadInfoByCondition(Map<String,Object> condition) throws BusinessException;

    public int deleteByUserId(int id) throws BusinessException;
    
    public int updateNews(Map<String,Object> condition) throws BusinessException;
    
    /**
     * 根据角色ID查询
     * @param Role_Id
     * @return
     * @throws BusinessException
     */
    public List<Map<String,Object>> getInfoByRoleId(Map<String,Object> condition) throws BusinessException;
}
