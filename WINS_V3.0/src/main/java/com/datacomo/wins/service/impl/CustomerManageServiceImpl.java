package com.datacomo.wins.service.impl;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.CustomerManageBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.CustomerManageService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/10/12.
 */
@Service("customerManageService")
public class CustomerManageServiceImpl implements CustomerManageService {
    private static Logger logger = Logger.getLogger(CustomerManageServiceImpl.class.getName());
    @Autowired
    private CustomerManageBusiness customerManageBusiness;

    @Override
   	public List<Map<String, Object>> searchCustomer(int visitId,Map<String, Object> condition) throws ServiceException {
   		logger.info("searchPrice method start");
           if (logger.isDebugEnabled()) {
               logger.debug("visitId:"+visitId);
               logger.debug("condition:"+condition);
           }
           List<Map<String,Object>> result = null;
           try {
               result = customerManageBusiness.searchCustomer(visitId,condition);
           } catch (Exception e) {
               e.printStackTrace();
               logger.error(e);
               return null;
           }
           logger.info("searchPrice method end");
           return result;
   	}

    @Override
    public Map<String, Object> findCustomersFinanceByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("findCustomersFinanceByCondtion method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("args:condition"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=customerManageBusiness.findAllByCondition(condition);
                int count=customerManageBusiness.countAllByCondition(condition);
                if (condition.containsKey("page")){
                    Pagination page=(Pagination)condition.get("page");
                    page.setTotalCount(count);
                    page.setCurrentPage(page.getStart()/page.getLimit()+1);
                    result.putAll(condition);
                    result.put("page",page);
                }
                result.put("list",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("findCustomersFinanceByCondtion method end");
        return result;
    }

    @Override
    public Map<String, Object> findCustomerTradeInfoByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("findCustomerTradeInfoByCondtion method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("args:condition"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=customerManageBusiness.selectAllInfo(condition);
                int count=customerManageBusiness.countAllInfo(condition);
                if (condition.containsKey("page")){
                    Pagination page=(Pagination)condition.get("page");
                    page.setTotalCount(count);
                    page.setCurrentPage(page.getStart()/page.getLimit()+1);
                    result.putAll(condition);
                    result.put("page",page);
                }
                result.put("list",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("findCustomerTradeInfoByCondtion method end");
        return result;
    }

    public Map<String, Object> findCustomersByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("findCustomersByCondtion method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("args:condition"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=customerManageBusiness.findByCondition(visitId,condition);
                int count=customerManageBusiness.countByCondition(visitId,condition);
                if (condition.containsKey("page")){
                    Pagination page=(Pagination)condition.get("page");
                    page.setTotalCount(count);
                    page.setCurrentPage(page.getStart()/page.getLimit()+1);
                    result.putAll(condition);
                    result.put("page",page);
                }
                result.put("list",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("findCustomersByCondtion method end");
        return result;
    }

    @Override
    public Map<String, Object> getCustomerInfo(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("getCustomerInfo method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result=null;
        try {
            result = customerManageBusiness.getInfoByCondition(visitId,condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getCustomerInfo method end");
        return result;
    }

    @Override
    public int deleteCustomerById(int visitId, int id) throws ServiceException {
        logger.info("deleteCustomerById method start");
        if (logger.isDebugEnabled()){
            logger.debug("id"+id);
        }
        int result = 0;
        try {
            result=customerManageBusiness.deleteById(visitId,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteCustomerById method end");
        return result;
    }

    @Override
    public int insertCustomerInfo(int visitId, Map<String, Object> condition) {
        logger.info("insertCustomerInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = customerManageBusiness.insertInfo(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertCustomerInfo menthod end");
        return result;
    }

    @Override
    public boolean updateCustomerInfo(int visitId, Map<String, Object> condition, int id) {
        logger.info("updateCustomerInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
            logger.debug("id:"+id);
        }
        boolean result=false;
        try {
            result=customerManageBusiness.updateInfo(visitId,condition,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateCustomerInfo menthod end");
        return result;
    }
    
    @Override
    public int insertRechargenfo(int visitId, Map<String, Object> condition, int id) {
        logger.info("insertRechargenfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
            logger.debug("id:"+id);
        }
        int result=0;
        String tradeAmount= String.valueOf(condition.get("tradeAmount"));
        Map<String,Object> info = null;
        try {
            info=customerManageBusiness.getInfoByCondition(visitId,condition);
            String accountBalance=tradeAmount;
            if(info.get("accountBalance")!=null){
                String oldAccountBalance= String.valueOf(info.get("accountBalance"));
                BigDecimal b2=new BigDecimal(oldAccountBalance);
                BigDecimal b1=new BigDecimal(tradeAmount);
                accountBalance= String.valueOf(b1.add(b2));
            }
            condition.put("accountBalance",accountBalance);
            result=customerManageBusiness.insertTradeInfo(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            return 0;
        }
        logger.info("insertRechargenfo menthod end");
        return result;
    }
}
