package com.datacomo.wins.service.impl;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.AccountManageBusiness;
import com.datacomo.wins.business.SysCityInfoBusiness;
import com.datacomo.wins.business.SysRoleInfoBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.AccountManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/13.
 */
@Service("accountManageService")
public class AccountManageServiceImp implements AccountManageService {
    private static Logger logger = Logger.getLogger(AccountManageServiceImp.class.getName());
    @Autowired
    private AccountManageBusiness accountManageBusiness;
    @Autowired
    private SysCityInfoBusiness sysCityInfoBusiness;
    @Autowired
    private SysRoleInfoBusiness sysRoleInfoBusiness;

    @Override
    public Map<String, Object> findAccountsByCondtion(int visitId, int id, Map<String, Object> condition){
        logger.info("findAccountsByCondtion method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("args:condition"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=accountManageBusiness.findByCondition(visitId,condition);
                int count=accountManageBusiness.countByCondition(visitId,condition);
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
        logger.info("findAccountsByCondtion method end");
        return result;
    }

    @Override
    public Map<String, Object> getAccountInfo(int visitId, Map<String, Object> condition){
        logger.info("getAccountInfo method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        int showStatus = Integer.parseInt(String.valueOf(condition.get("showStatus")));
        Map<String, Object> result=null;
        List<Map<String,Object>> citys = null;
        List<Map<String,Object>> roles = null;
        try {
            result = accountManageBusiness.getInfoByCondition(visitId,condition);
            condition.clear();
            condition.put("provinceId",result.get("provinceId"));
            citys = sysCityInfoBusiness.findByCondition(visitId,condition);
            if(citys.size()==1){
                citys.clear();
            }
            condition.put("showStatus",showStatus);
            roles = sysRoleInfoBusiness.findByCondition(visitId,condition);
            result.put("citys",citys);
            result.put("roles",roles);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getAccountInfo method end");
        return result;
    }


    @Override
    public int deleteAccountById(int visitId, int id){
        logger.info("deleteAccountById method start");
        if (logger.isDebugEnabled()){
            logger.debug("id"+id);
        }
        int result = 0;
        int roleNum = 0;
        int accountNum = 0;
        Map<String,Object> condition = new HashMap<String ,Object>();
        condition.put("createUser",id);
        try {
            //先判断此用户有没有创建过角色
            roleNum = sysRoleInfoBusiness.countByCondition(visitId,condition);
            //再判断此用户有没有创建过账户
            accountNum = accountManageBusiness.getCountByCondition(condition);
            if(roleNum ==0 && accountNum == 0){
                result=accountManageBusiness.deleteById(visitId,id);
            }else{
                result = -1;
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteAccountById method end");
        return result;
    }

    @Override
    public int insertInfo(int visitId, Map<String, Object> condition){
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = accountManageBusiness.insertInfo(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertInfo menthod end");
        return result;
    }

    @Override
    public boolean updateInfo(int visitId, Map<String, Object> condition, int id){
        logger.info("updateInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
            logger.debug("id:"+id);
        }
        boolean result=false;
        try {
            result=accountManageBusiness.updateInfo(visitId,condition,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateInfo menthod end");
        return result;
    }

    @Override
    public int getCount(Map<String, Object> condition) throws ServiceException {
        logger.info("getCount method start");
        int result=0;
        try {
            result=accountManageBusiness.getCountByCondition(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("getCount method end");
        return result;
    }

    @Override
    public Map<String, Object> getInfoByCondition(int visitId, Map<String, Object> condition){
        logger.info("getInfoByCondition method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result=null;
        try {
            result = accountManageBusiness.getInfoByCondition(visitId,condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getInfoByCondition method end");
        return result;
    }

    @Override
    public Map<String, Object> getBelongCityInfo(int id, Map<String, Object> condition){
        logger.info("getBelongCityInfo method start");
        if(logger.isDebugEnabled()){
            logger.debug("id: "+id);
            logger.debug("condition: "+condition);
        }
        Map<String,Object> result = null;
        List<Map<String, Object>> cities = null;
        int provinceId = 0;
        try {
            result = accountManageBusiness.getBelongCityInfo(condition);
            provinceId = Integer.parseInt(String.valueOf(result.get("provinceId")));
            condition.clear();
            condition.put("provinceId",provinceId);
            cities = sysCityInfoBusiness.findByCondition(provinceId,condition);
            if(cities.size()==1){
                cities.clear();
            }
            result.put("city",cities);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getBelongCityInfo method end");
        return result;
    }


}
