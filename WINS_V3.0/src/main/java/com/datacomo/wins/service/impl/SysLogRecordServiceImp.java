package com.datacomo.wins.service.impl;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.SysLogRecordBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysLogRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
@Service("sysLogRecordService")
public class SysLogRecordServiceImp implements SysLogRecordService {
    private static Logger logger = Logger.getLogger(SysLogRecordServiceImp.class.getName());
    @Autowired
    private SysLogRecordBusiness sysLogRecordBusiness;

    @Override
    public Map<String, Object> findLogsByCondition(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("findLogsByCondtion method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(d);
        result.put("today",today);
        try {
            if (condition!=null){
                list=sysLogRecordBusiness.findByCondition(visitId,condition);
                int count=sysLogRecordBusiness.countByCondition(visitId,condition);
                if (condition.containsKey("page")){
                    Pagination page=(Pagination)condition.get("page");
                    page.setTotalCount(count);
                    page.setCurrentPage(page.getStart()/page.getLimit()+1);
                    result.putAll(condition);
                    result.put("page",page);
                    result.put("list",list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("findLogsByCondtion method end");
        return result;
    }

    @Override
    public int insertLogInfo(int userId, String logTitle, int logType, String logCont, String logIp, String logTime) throws ServiceException {
        logger.info("insertLogInfo method start");
        int result = 0;
        try {
            result = sysLogRecordBusiness.insertLogInfo(userId,logTitle,logType,logCont,logIp,logTime);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertLogInfo method end");
        return result;
    }
}
