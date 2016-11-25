package com.datacomo.wins.service.impl;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.SysRoleInfoBusiness;
import com.datacomo.wins.business.SysRoleMenuRelationBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysRoleInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
@Service("sysRoleInfoService")
public class SysRoleInfoServiceImp implements SysRoleInfoService {
    private static Logger logger = Logger.getLogger(SysRoleInfoServiceImp.class.getName());
    @Autowired
    private SysRoleInfoBusiness sysRoleInfoBusiness;
    @Autowired
    private SysRoleMenuRelationBusiness sysRoleMenuRelationBusiness;

    @Override
    public Map<String, Object> findRolesByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("findRolesByCondtion method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("args:condition"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=sysRoleInfoBusiness.findByCondition(visitId,condition);
                int count=sysRoleInfoBusiness.countByCondition(visitId,condition);
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
        logger.info("findRolesByCondtion method end");
        return result;
    }

    @Override
    public Map<String, Object> getRoleInfo(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("getRoleInfo method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result = null;
        List<Map<String,Object>> list = null;
        try {
            result = sysRoleInfoBusiness.getInfoByCondition(visitId,condition);
            //权限
            list = sysRoleMenuRelationBusiness.findByCondition(visitId,condition);
            for(Map map:list){
                int value = Integer.parseInt(String.valueOf(map.get("roleValue"))) ;
                int see_permission = value&1;
                int add_permission = value&2;
                int update_permission = value&4;
                int delete_permission = value&8;
                int review_permission = value&16;
                int global_permission = value&32;
                map.put("see_permission",see_permission);
                map.put("add_permission",add_permission);
                map.put("update_permission",update_permission);
                map.put("delete_permission",delete_permission);
                map.put("review_permission",review_permission);
                map.put("global_permission",global_permission);
            }
            result.put("MenuRelation",list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getRoleInfo method end");
        return result;
    }

    @Override
    public int deleteRoleById(int visitId, int id) throws ServiceException {
        logger.info("deleteRoleById method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("id:"+id);
        }
        int result = 0;
        int count = 0;
        try {
            Map<String,Object> condition = new HashMap<String, Object>();
            condition.put("roleId",id);
            count = sysRoleMenuRelationBusiness.countByCondition(id,condition);
            if (count != 0){
                sysRoleMenuRelationBusiness.deleteById(visitId,id);  //删除权限信息
            }
            result = sysRoleInfoBusiness.deleteById(visitId,id); //删角色信息
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteRoleById method end");
        return result;
    }

    @Override
    public int insertInfo(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("insertInfo method start");
        if(logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysRoleInfoBusiness.insertInfo(condition );
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertInfo method end");
        return result;
    }

    @Override
    public boolean updateInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException {
        logger.info("updateInfo method start");
        if(logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        boolean result = false;
        try {
            result = sysRoleInfoBusiness.updateInfo(visitId,condition,visitId);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateInfo method start");
        return result;
    }

    @Override
    public int getCount(Map<String, Object> condition) throws ServiceException {
        logger.info("getCount method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result=0;
        try {
            result=sysRoleInfoBusiness.getCountByCondition(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("getCount method end");
        return result;
    }

    @Override
    public boolean updateRoleState(int visitId, Map<String, Object> condition, int id) throws ServiceException {
        logger.info("updateRoleState method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
            logger.debug("id:"+id);
        }
        boolean result = false;
        try {
            result = sysRoleInfoBusiness.updateInfo(visitId,condition,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateRoleState method end");
        return result;
    }

}
