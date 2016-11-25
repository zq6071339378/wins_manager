package com.datacomo.wins.business;

import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;

/**
 * Created by duanlinzhuo on 2016/5/16.
 */
public interface SysTaskBusiness extends BaseBusiness {
    /**
     * 根据条件获取未读消息数目
     * @param condition
     * @return
     * @throws BusinessException
     */
    public int getNotReadCountByCondition(Map<String,Object> condition) throws BusinessException;

    public List<Map<String,Object>> getNotReadInfoByCondition(Map<String,Object> condition) throws BusinessException;

    public int deleteByUserId(int id) throws BusinessException;
    
    public Map<String,Object> getMonitorAlarmInfo() throws BusinessException;
    
    /**
     * 更新告警设置
     * @return
     * @throws MapperException
     */
    public int updateMonitorAlarmInfo(Map<String,Object> condition) throws BusinessException;
    
    public int getUserId(Map<String,Object> condition) throws BusinessException;
    /**
     * 获取服务器登录信息
     * @return
     * @throws MapperException
     */
    public List<Map<String,Object>> getServerInfo() throws BusinessException; 
}
