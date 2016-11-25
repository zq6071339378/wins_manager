package com.datacomo.wins.service.impl;

import com.datacomo.wins.base.socket.client.IMSyncClient;
import com.datacomo.wins.base.socket.im.IMDataPacket;
import com.datacomo.wins.base.socket.im.proto.DataPacketIPS;
import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.ActivityManageBusiness;
import com.datacomo.wins.business.IPSManageBusiness;
import com.datacomo.wins.business.SysCityInfoBusiness;
import com.datacomo.wins.business.SysProvinceInfoBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.ActivityManageService;
import com.datacomo.wins.service.IPSManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/11/16.
 */
@Service("iPSManageService")
public class IPSManageServiceImpl implements IPSManageService {
    private static Logger logger = Logger.getLogger(IPSManageServiceImpl.class.getName());
    @Autowired
    private IPSManageBusiness ipsManageBusiness;
    @Autowired
    private SysCityInfoBusiness sysCityInfoBusiness;
    @Autowired
    private SysProvinceInfoBusiness sysProvinceInfoBusiness;
    @Override
    public Map<String, Object> findIpsByCondtion(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("findIpsByCondtion method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("args:condition"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=ipsManageBusiness.findByCondition(visitId,condition);
                int count=ipsManageBusiness.countByCondition(visitId,condition);
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
        logger.info("findIpsByCondtion method end");
        return result;
    }

    @Override
    public Map<String, Object> getIpsInfo(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("getIpsInfo method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result=null;
        try {
            result = ipsManageBusiness.getInfoByCondition(visitId,condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getIpsInfo method end");
        return result;
    }

    @Override
    public int deleteIpsById(int visitId, int id) throws ServiceException {
        logger.info("deleteIpsById method start");
        if (logger.isDebugEnabled()){
            logger.debug("id"+id);
        }
        int result = 0;
        try {
            result =  ipsManageBusiness.deleteById(visitId,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("deleteIpsById method end");
        return result;
    }

    @Override
    public int insertIpsInfo(int visitId, Map<String, Object> condition) throws ServiceException {
        logger.info("insertIpsInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("condition:"+condition);
        }
        int result = 0;
        try {
            result = ipsManageBusiness.insertInfo(condition);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return 0;
        }
        logger.info("insertIpsInfo menthod end");
        return result;
    }

    @Override
    public boolean updateIpsInfo(int visitId, Map<String, Object> condition, int id) throws ServiceException {
        logger.info("updateIpsInfo method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
            logger.debug("id:"+id);
        }
        boolean result=false;
        try {
            result=ipsManageBusiness.updateInfo(visitId,condition,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("updateIpsInfo menthod end");
        return result;
    }

    @Override
    public Map<String, Object> findProvinceCityInfo(Map<String, Object> condition) throws ServiceException {
        logger.info("getAccountInfo method start");
        if (logger.isDebugEnabled()) {
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result=new HashMap<>();
        List<Map<String,Object>> province = null;
        List<Map<String,Object>> city = null;
        try {
            province=sysProvinceInfoBusiness.findByCondition(1,condition);
            result.put("province",province);
            if(condition.containsKey("cityId")){
                int cityId = Integer.parseInt(String.valueOf(condition.get("cityId")));
                Map<String,Object> c = sysCityInfoBusiness.getInfo(cityId,cityId);
                List<Map<String,Object>> li=new ArrayList<>();
                li.add(c);
                result.put("city",li);
            }else{
                city=sysCityInfoBusiness.findByCondition(1,condition);
                result.put("city",city);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getAccountInfo method end");
        return result;
    }

    @Override
    public int count() throws ServiceException {
        logger.info("count method start");
        int result=0;
        try {
            result=ipsManageBusiness.count();
        } catch (BusinessException e) {
            e.printStackTrace();
            return -1;
        }
        logger.info("count method end");
        return result;
    }

    @Override
    public int getMaxValue() throws ServiceException {
        logger.info("getMaxValue method start");
        int result=0;
        try {
            result=ipsManageBusiness.getMaxValue();
        } catch (BusinessException e) {
            e.printStackTrace();
            return -1;
        }
        logger.info("getMaxValue method end");
        return result;
    }

    @Override
    public int synchronzeIpsStatusToICS(int ipsId,int ipsStatus){
        logger.debug("synchronzeIpsStatusToICS method start");
        int result=-1;
        IMSyncClient imClient = new IMSyncClient();
        try{
            imClient.createSession();
            logger.info("连接ICS");
            DataPacketIPS dpi=new DataPacketIPS(ipsId,ipsStatus);
            logger.info("创建对象：dpi");
            IMDataPacket imdp =imClient.sendDataMsg(5038,dpi);
            System.out.println("imdp.toString()-------------------------收到的IM协议包---------------------------: "+imdp.toString());
            logger.info("创建对象：imdp");
            dpi.rspDecode(imdp.getRespDataPacketBytes());
            logger.info("返回结果：rspDecode");
            result = imdp.getFunctionCode();
            System.out.println("synchronzeIpsStatusToICS-----------------------------------------------------------------: "+result);
            result =(result==0)?1:0;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            return 0;
        }finally{
            imClient.closeSession();
        }
        logger.debug("synchronzeIpsStatusToICS method end");
        return result;
    }
}
