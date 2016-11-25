package com.datacomo.wins.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.AdnetworkBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.AdnetworkService;

/**
 * 
 * @author wangfang
 * 2016/11/15
 *
 */
@Service("AdnetworkService")
public class AdnetworkServiceImpl  implements AdnetworkService{

	private static Logger logger = Logger.getLogger(AdnetworkServiceImpl.class.getName());
	@Autowired
    private AdnetworkBusiness adnetworkBusiness;

	@Override
	  public Map<String, Object> findadnetworkByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException {
	        logger.info("findadnetworkByCondtion method start");
	        if (logger.isDebugEnabled()){
	            logger.debug("args:visitId"+visitId);
	            logger.debug("args:condition"+condition);
	        }
	        Map<String,Object> result=new HashMap<String,Object>();
	        List<Map<String,Object>> list=null;
	        try {
	            if (condition!=null){
	                list=adnetworkBusiness.findByCondition(visitId,condition);
	                int count=adnetworkBusiness.countByCondition(visitId,condition);
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
	        logger.info("findadnetworkByCondtion method end");
	        return result;
	    }

	@Override
	public List<Map<String, Object>> searchAdnetwork(int visitId, Map<String, Object> condition){
	// TODO Auto-generated method stub
		logger.info("searchPrice method start");
       if (logger.isDebugEnabled()) {
           logger.debug("visitId:"+visitId);
           logger.debug("condition:"+condition);
       }
       List<Map<String,Object>> result = null;
       try {
           result = adnetworkBusiness.searchAdnetwork(visitId,condition);
       } catch (Exception e) {
           e.printStackTrace();
           logger.error(e);
           return null;
       }
       logger.info("searchPrice method end");
       return result;
	}

	@Override
	public Map<String, Object> getAdnetworkInfo(int visitId, Map<String, Object> condition) throws ServiceException {
		   logger.info("getCustomerInfo method start");
	        if (logger.isDebugEnabled()) {
	            logger.debug("visitId:"+visitId);
	            logger.debug("condition:"+condition);
	        }
	        Map<String, Object> result=null;
	        try {
	            result = adnetworkBusiness.getInfoByCondition(visitId,condition);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e);
	            return null;
	        }
	        logger.info("getCustomerInfo method end");
	        return result;
	}

	   @Override
	    public int insertAdnetworkInfo(int visitId, Map<String, Object> condition) throws ServiceException {
	        logger.info("insertCustomerInfo method start");
	        if (logger.isDebugEnabled()){
	            logger.debug("condition:"+condition);
	        }
	        int result = 0;
	        try {
	            result = adnetworkBusiness.insertInfo(condition);
	        } catch (BusinessException e) {
	            e.printStackTrace();
	            logger.error(e);
	            return 0;
	        }
	        logger.info("insertAdnetworkInfo menthod end");
	        return result;
	    }
	   
	   @Override
	    public int deleteAdnetworkById(int visitId, int id) throws ServiceException {
	        logger.info("deleteCustomerById method start");
	        if (logger.isDebugEnabled()){
	            logger.debug("id"+id);
	        }
	        int result = 0;
	        try {
	            result=adnetworkBusiness.deleteById(visitId,id);
	        } catch (BusinessException e) {
	            e.printStackTrace();
	            logger.error(e);
	            return 0;
	        }
	        logger.info("deleteCustomerById method end");
	        return result;
	    }
	   
	   @Override
	    public boolean updateAdnetworkInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException {
	        logger.info("updateAdnetworkInfo method start");
	        if (logger.isDebugEnabled()){
	            logger.debug("visitId:"+visitId);
	            logger.debug("condition:"+condition);
	            logger.debug("id:"+id);
	        }
	        boolean result=false;
	        try {
	            result=adnetworkBusiness.updateInfo(visitId,condition,id);
	        } catch (BusinessException e) {
	            e.printStackTrace();
	            logger.error(e);
	            return false;
	        }
	        logger.info("updateCustomerInfo menthod end");
	        return result;
	    }

}
