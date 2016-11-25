package com.datacomo.wins.business.impl;

import com.datacomo.wins.business.SysRoleMenuRelationBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.mapper.RoleMenuRelationMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/5/17.
 */
@Repository("sysRoleMenuRelationBusiness")
public class SysRoleMenuRelationBusinessImp implements SysRoleMenuRelationBusiness {
    private static Logger logger = Logger.getLogger(SysRoleMenuRelationBusinessImp.class.getName());
    @Autowired
    private RoleMenuRelationMapper sysRoleMenuRelationMapper;

    @Override
    public int insertInfo(Map<String, Object> condition) throws BusinessException {
        logger.info("insertInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = sysRoleMenuRelationMapper.insertInfo(condition);
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
            logger.debug("visitId:"+visitId);
            logger.debug("id:"+id);
        }
        int result = 0;
        try {
            result = sysRoleMenuRelationMapper.deleteById(id);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteById method start");
        return result;
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
        return null;
    }

    @Override
    public List<Map<String, Object>> findByCondition(int visitId, Map<String, Object> condition) {
        logger.info(SysRoleMenuRelationBusinessImp.class.getName()+"findByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        List<Map<String,Object>> result=null;
        try {
            result=sysRoleMenuRelationMapper.findByCondition(condition);
        } catch (MapperException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info(SysRoleMenuRelationBusinessImp.class.getName()+"findByCondition method end");
        return result;
    }

    @Override
    public boolean updateInfo(int visitId, Map<String, Object> condition, int id) throws BusinessException {
        logger.info("updateInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        boolean result = false;
        try {
            sysRoleMenuRelationMapper.updateInfo(condition);
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
            result = sysRoleMenuRelationMapper.countByCondition(condition);
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
}
