package com.datacomo.wins.service.impl;

import com.google.gson.GsonBuilder;
import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.AccountManageBusiness;
import com.datacomo.wins.business.SysRoleInfoBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.util.DateUtil;
import com.datacomo.wins.bean.Message;
import com.datacomo.wins.websocket.MyWebSocketHandler;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.business.SysNewsInfoBusiness;
import com.datacomo.wins.service.SysNewsInfoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duanlinzhuo on 2016/3/16.
 */
@Service("sysNewsInfoService")
public class SysNewsInfoServiceImpl implements SysNewsInfoService {
    private static Logger logger=Logger.getLogger(SysNewsInfoServiceImpl.class.getName());
    @Autowired
    private SysNewsInfoBusiness sysNewsInfoBusiness;
    @Autowired
    private SysRoleInfoBusiness sysRoleInfoBusiness;
    @Autowired
    private AccountManageBusiness accountManageBusiness;
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    //广播消息方法
    private boolean sendNews(String create_time,String news_type,String role_type,String content){
        Message msg = new Message();
        msg.setSendTime(create_time);
        msg.setType(news_type);
        msg.setToType(role_type);
        msg.setText(content);
        boolean result =false;
        try {
            myWebSocketHandler.broadcast(new TextMessage(new GsonBuilder().create().toJson(msg)));
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        return result;
    }

    @Override
    public Map<String, Object> findNewsByCondition(int visitId, int id, Map<String, Object> condition) throws ServiceException {
        logger.info("findNewsByCondition method start");
        if (logger.isDebugEnabled()){
            logger.debug("args:visitId"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> list=null;
        try {
            if (condition!=null){
                list=sysNewsInfoBusiness.findByCondition(visitId,condition);
                int count=sysNewsInfoBusiness.countByCondition(visitId,condition);
                if (condition.containsKey("page")){
                    Pagination page=(Pagination) condition.get("page");
                    page.setTotalCount(count);
                    page.setCurrentPage(page.start/page.limit+1);
                    result.putAll(condition);
                    result.put("list",list);
                    result.put("page",page);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("findNewsByCondition method end");
        return result;
    }

    @Override
    public int deleteById(int visitId, int id) throws ServiceException {
        logger.info("deleteById method start");
        if (logger.isDebugEnabled()){
            logger.debug("id:"+id);
        }
        int result=0;
        try {
            result=sysNewsInfoBusiness.deleteById(visitId,id);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("deleteById method end");
        return result;
    }

    @Override
    public boolean sendMassages(int id,String role_type, String news_type, String content,String create_name,int Show_Status) throws ServiceException {
        logger.info("sendMassages method start");
        if (logger.isDebugEnabled()){
            logger.debug("id:"+id);
            logger.debug("role_type:"+role_type);
            logger.debug("news_type:"+news_type);
            logger.debug("content:"+content);
            logger.debug("create_name:"+create_name);
        }
        boolean result = true;
        int code = 0;
        Map<String,Object> condition = new HashMap<String, Object>();
        List<Integer> roleIds=null;
        List<Map<String,Object>> list=null;
        String create_time = DateUtil.getDateString();
        condition.put("News_Type",news_type);
        condition.put("News_Content",content);
        condition.put("Create_Name",create_name);
        condition.put("Create_Time",create_time);
        condition.put("Is_Read","1");
        try {
            if(role_type.equals("0")){
                list = accountManageBusiness.getAllInfo(Show_Status);
                if (!list.isEmpty()) {
                    for (Map map : list) {
                        condition.put("User_Id", map.get("userId"));
                        condition.put("Receive_Name", map.get("userName"));
                        code = sysNewsInfoBusiness.insertInfo(condition);
                        if(code == 0 ) {
                            result = false;
                            break;
                        }
                    }
                    if(result == true){  //说明code = sysNewsInfoBusiness.insertInfo(condition); 方法没有发生异常
                        boolean bool = true;
                        bool = sendNews(create_time,news_type,role_type,content);
                        if(bool==false){  //广播消息时发生异常
                            result = false;
                        }
                    }
                }
            }else {
                roleIds = sysRoleInfoBusiness.getRoleIds(role_type);
                if (!roleIds.isEmpty()) { //如果
                    for (Integer role_id : roleIds) {
                    	Map<String,Object> roleIdCondition = new HashMap<String, Object>();
                    	roleIdCondition.put("Role_Id", role_id);
                    	roleIdCondition.put("Show_Status", Show_Status);
                        list = sysNewsInfoBusiness.getInfoByRoleId(roleIdCondition);
                        if (!list.isEmpty()) {  //有这个角色对应的用户
                            for (Map map : list) {
                                condition.put("User_Id", map.get("userId"));
                                condition.put("Receive_Name", map.get("userName"));
                                code = sysNewsInfoBusiness.insertInfo(condition);
                                if(code == 0 ){
                                    result = false;
                                    break;
                                }
                            }
                        }
                    }
                    if(result == true){  //说明code = sysNewsInfoBusiness.insertInfo(condition); 方法没有发生异常
                        boolean bool = true;
                        bool = sendNews(create_time,news_type,role_type,content);
                        if(bool==false){  //广播消息时发生异常
                            result = false;
                        }
                    }
                }
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        logger.info("sendMassages method end");
        return result;
    }

    @Override
    public Map<String, Object> getNotReadInfos(int visitId,Map<String,Object> condition) throws ServiceException {
        logger.info("getNotReadInfos method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
        }
        condition.put("User_Id",visitId);
        condition.put("Is_Read","1");
        Map<String,Object> result = new HashMap<String, Object>();
        List<Map<String,Object>> news = null; //通知消息
        try {
            if (condition!=null){
                int count=sysNewsInfoBusiness.getNotReadCountByCondition(condition);
                result.put("count",count);
                news = sysNewsInfoBusiness.getNotReadInfoByCondition(condition);
                result.put("news",news);
                if (condition.containsKey("page")){
                    Pagination page=(Pagination) condition.get("page");
                    page.setTotalCount(count);
                    page.setCurrentPage(page.start/page.limit+1);
                    result.putAll(condition);
                    result.put("page",page);
                }
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        logger.info("getNotReadInfos method end");
        return result;
    }

	@Override
	public boolean sendMassages(int visitId, Map<String, Object> condition)
			throws ServiceException {
		logger.info("sendMassages method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
		boolean flag =false;
		 try {
			int code = sysNewsInfoBusiness.insertInfo(condition);
			if(code>0){
				flag=true;
			}else{
				flag=false;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			flag=false;
			logger.error(e);
		}
		logger.info("sendMassages method end");
		return flag;
	}

	@Override
	public int updateNews(Map<String, Object> condition)
			throws ServiceException {
		logger.info("updateNews method start");
		int result = 0;
		 try {
			 result = sysNewsInfoBusiness.updateNews(condition);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("updateNews method end");
		return result;
	}
}
