package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.SysRoleInfoBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.AccountManageMapper;
import com.datacomo.wins.mapper.RoleInfoMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
@Repository("sysRoleInfoBusiness")
public class SysRoleInfoBusinessImp implements SysRoleInfoBusiness {
    private static Logger logger = Logger.getLogger(SysRoleInfoBusinessImp.class.getName());
    @Autowired
    private RoleInfoMapper sysRoleInfoMapper;
    @Autowired
    private AccountManageMapper accountManageMapper;

    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result= 0;
        try {
            result = sysRoleInfoMapper.insertInfo(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertInfo method end");
        return result;
    }

    @Override
    public int deleteById(int visitId, int id) throws BusinessException {
        logger.info("deleteById method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId"+visitId);
            logger.debug("id"+id);
        }
        int code=0;
        try {
            code=sysRoleInfoMapper.deleteById(id);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteById method end");
        return code;
    }

    @Override
    public int deleteByCondition(int visitId, Map<String, Object> condition) throws BusinessException {
        return 0;
    }

    @Override
    public Map<String, Object> getInfo(int visitId, int id) {
        return null;
    }

    @Override
    public Map<String, Object> getInfoByCondition(int visitId, Map<String, Object> condition) {
        logger.info("getInfoByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String,Object> result = null;
        try {
            result = sysRoleInfoMapper.getInfoByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getInfoByCondition method start");
        return result;
    }

    @Override
    public List<Map<String, Object>> findByCondition(int visitId, Map<String, Object> condition) {
        logger.info("findByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition"+condition);
        }
        List<Map<String,Object>> result=null;
        try {
            result=sysRoleInfoMapper.findByCondition(condition);
            /*if (!result.isEmpty()){
                for (Map map : result){
                    int userId =Integer.parseInt(String.valueOf(map.get("createUser")));
                    Map<String,Object> userInfo = accountManageMapper.getInfo(userId);
                    if (userInfo!=null){
                        map.put("createName",userInfo.get("userName"));
                    }
                }
            }*/
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("findByCondition method end");
        return result;
    }

    @Override
    public boolean updateInfo(int visitId, Map<String, Object> condition, int id) throws BusinessException {
        logger.info("updateInfo method start");
        if(logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        boolean result = false;
        try {
            sysRoleInfoMapper.updateInfo(condition);
            result = true;
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateInfo method end");
        return result;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int countByCondition(int visitId, Map<String, Object> condition) {
        logger.info("countByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysRoleInfoMapper.countByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("countByCondition method end");
        return result;
    }

    @Override
    public int isExist(Map<String, Object> condition) {
        return 0;
    }

    @Override
    public int getCountByCondition(Map<String, Object> condition) throws BusinessException {
        logger.info("getCountByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysRoleInfoMapper.getCountByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("getCountByCondition method end");
        return result;
    }
    
    @Override
    public List getRoleIds(String Role_Type) throws BusinessException {
        logger.info("getRoleIds method start");
        if (logger.isDebugEnabled()){
            logger.debug("role_type:"+Role_Type);
        }
        List result= null;
        try {
            result = sysRoleInfoMapper.getRoleIds(Role_Type);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getRoleIds method start");
        return result;
    }
}
