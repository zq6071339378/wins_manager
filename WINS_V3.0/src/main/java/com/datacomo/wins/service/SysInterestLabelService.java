package com.datacomo.wins.service;

/**
 * Created by yangxiongbin on 2016/9/22.
 */

import com.datacomo.wins.exception.ServiceException;

/**
 * 人群标签service接口
 */
public interface SysInterestLabelService extends BaseService {

    /**
     * 所有人群标签入库
     * @return
     * @throws ServiceException
     */
    public int updateAllInterestLabel() throws ServiceException;

    /**
     * 根据人群标签查询用户入库
     * @return
     * @throws ServiceException
     */
    public int updateAllUserByInterestLabel() throws ServiceException;

}
