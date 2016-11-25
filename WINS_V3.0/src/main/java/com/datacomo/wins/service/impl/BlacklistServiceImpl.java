package com.datacomo.wins.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.datacomo.wins.base.socket.client.IMSyncClient;
import com.datacomo.wins.base.socket.im.IMDataPacket;
import com.datacomo.wins.base.socket.im.proto.DataPacketUrlFilter;
import com.datacomo.wins.base.socket.im.proto.DataPacketUserFilter;
import com.datacomo.wins.controller.Config;
import com.datacomo.wins.exception.MapperException;
import com.datacomo.wins.push.bean.UrlFilter;
import com.datacomo.wins.push.bean.UserFilter;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.BlacklistBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.BlacklistService;
/**
 * 
 * @author liwenjie
 *
 */
@Service(value="blacklistService")
public class BlacklistServiceImpl implements BlacklistService {
	private static Logger logger = Logger.getLogger(BlacklistServiceImpl.class.getName());
	@Autowired
	BlacklistBusiness blacklistBusiness;
	
	@Override
	public Map<String, Object> userBlacklist(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("userBlacklist method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = blacklistBusiness.userBlacklist(visitId, condition);
				int count = blacklistBusiness.userBlacklistcount(condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("LIST", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("userBlacklist method end"); 
		return result;
	}

	@Override
	public Map<String, Object> urlBlacklist(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("urlBlacklist method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = blacklistBusiness.urlBlacklist(visitId, condition);
				int count = blacklistBusiness.urlBlacklistcount(condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("LIST", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("urlBlacklist method end"); 
		return result;
	}


	@Override
	public int delUser(int visitId, UserFilter userFilter ,int userId) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("delUser method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("userId："+userId);
			logger.debug("userFilter："+userFilter);
		}
		int result=-1;
		boolean bool = Boolean.valueOf(Config.message.get("icsStatusNew").toString());
		if(bool){
			IMSyncClient imClient = new IMSyncClient();
			try{
				imClient.createSession();
				logger.info("连接ICS");
				DataPacketUserFilter dpuf = new DataPacketUserFilter(userFilter,1);
				logger.info("创建对象：dpuf");
				IMDataPacket imdp = imClient.sendDataMsg(5002,dpuf);
				System.out.println("imdp.toString()-------------------------收到的---------IM协议包---------------------------: "+imdp.toString());
				logger.info("创建对象：imdp");
				result = imdp.getFunctionCode();
				System.out.println("deleteUserFilter-----------------------------------------------------------------: "+result);
				dpuf.rspDecode(imdp.getRespDataPacketBytes());
				logger.info("返回结果：rspDecode");
				result =(result==0)?1:0;
			}catch (Exception e){
				e.printStackTrace();
				logger.error(e);
				return 0;
			}finally{
				imClient.closeSession();
			}
		}else {
			try {
				result = blacklistBusiness.delUser(visitId,userId);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				return 0;
			}
		}
		logger.info("delUser method end");
		return result;
	}

	@Override
	public int delUrl(int visitId, UrlFilter urlFilter, int urlId) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("delUrl method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("urlId："+urlId);
			logger.debug("urlFilter："+urlFilter);
		}
		int result=-1;
		boolean bool = Boolean.valueOf(Config.message.get("icsStatusNew").toString());
		if(bool){
			IMSyncClient imClient = new IMSyncClient();
			try{
				imClient.createSession();
				logger.info("连接ics");
				DataPacketUrlFilter dpuf = new DataPacketUrlFilter(urlFilter,1);
				logger.info("创建对象：dpuf");
				IMDataPacket imdp = imClient.sendDataMsg(5004,dpuf);
				logger.info("创建对象：imdp");
				result = imdp.getFunctionCode();
				System.out.println("deleteUrlFilter-----------------------------------------------------------------: "+result);
				System.out.println("imdp.toString()-------------------------收到的---------IM协议包---------------------------: "+imdp.toString());
				byte[] resp = dpuf.rspDecode(imdp.getRespDataPacketBytes());
				logger.info("返回结果：rspDecode: "+resp);
				result =(result==0)?1:0;
			}catch (Exception e){
				e.printStackTrace();
				logger.error(e);
				return 0;
			}finally{
				imClient.closeSession();
			}
		}else{
			try {
				result = blacklistBusiness.delUrl(visitId,urlId);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				return 0;
			}
		}
		logger.info("delUrl method end");
		return result;
	}


	@Override
	public Map<String, Object> showPolicy(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("showPolicy method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;

		try {
			if(condition!=null){
				list = blacklistBusiness.showPolicy(visitId, condition);
					result.putAll(condition);
					result.put("LIST", list);
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("showPolicy method end"); 
		return result;
	}


	@Override
	public int insertBlackUser(int visitId, UserFilter userFilter, Map<String,Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
        logger.info("insertBlackUser method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
			logger.debug("userFilter:"+userFilter);
            logger.debug("condition:"+condition);
        }
        int result=-1;
		boolean bool = Boolean.valueOf(Config.message.get("icsStatusNew").toString());
		if (bool){
			logger.info("传入参数add userFiter:"+userFilter.toString());
			IMSyncClient imClient = new IMSyncClient();
			try{
				imClient.createSession();
				logger.info("连接icss");
				DataPacketUserFilter dpuf = new DataPacketUserFilter(userFilter,0);
				logger.info("创建对象：dpuf");
				IMDataPacket imdp = imClient.sendDataMsg(5002,dpuf);
				logger.info("创建对象：imdp");
				System.out.println("imdp.toString()-------------------------收到的---------IM协议包---------------------------: "+imdp.toString());
				result = imdp.getFunctionCode();
				System.out.println("createUserFilter-----------------------------------------------------------------: "+result);
				dpuf.rspDecode(imdp.getRespDataPacketBytes());
				logger.info("返回结果：rspDecode");
				result =(result==0)?1:0;
			}catch (Exception e){
				e.printStackTrace();
				logger.error(e);
				return 0;
			}finally{
				imClient.closeSession();
			}
		}else{
			try {
				result=blacklistBusiness.insertBlackUser(condition);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error(e);
				return 0;
			}
		}
        logger.info("insertBlackUser method end");
        return result;
	}

	@Override
	public int insertBlackUrl(int visitId, UrlFilter urlFilter , Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
        logger.info("insertBlackUrl method start");
        if (logger.isDebugEnabled()){
            logger.debug("visitId:"+visitId);
			logger.debug("urlFilter:"+urlFilter);
            logger.debug("condition:"+condition);
        }
        int result=0;
		boolean bool = Boolean.valueOf(Config.message.get("icsStatusNew").toString());
		if(bool){
			IMSyncClient imClient = new IMSyncClient();
			try{
				imClient.createSession();
				logger.info("连接icss");
				DataPacketUrlFilter dpuf = new DataPacketUrlFilter(urlFilter,0);
				logger.info("创建对象：dpuf");
				IMDataPacket imdp = imClient.sendDataMsg(5004,dpuf);
				result = imdp.getFunctionCode();
				System.out.println("createUrlFilter-----------------------------------------------------------------: "+result);
				System.out.println("imdp.toString()-------------------------收到的---------IM协议包---------------------------: "+imdp.toString());
				logger.info("创建对象：imdp");
				dpuf.rspDecode(imdp.getRespDataPacketBytes());
				logger.info("返回结果：rspDecode");
				result =(result==0)?1:0;
			}catch (Exception e){
				e.printStackTrace();
				logger.error(e);
				return 0;
			}finally{
				imClient.closeSession();
			}
		}else{
			try {
				result=blacklistBusiness.insertBlackUrl(condition);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error(e);
				return 0;
			}
		}
        logger.info("insertBlackUrl method end");
        return result;
	}
    /**
     * 上传txt文件
     * @param blackfile
     * @param uploadUrl
     * @return
     * @throws ServiceException
     */

	@Override
	public String uploadBlackUserTxt(MultipartFile blackfile, String uploadUrl)
			throws ServiceException {
		// TODO Auto-generated method stub
        logger.info("uploadBlackUserTxt method start");
        String txtName=blackfile.getOriginalFilename();
        try {
            File file=new File(uploadUrl);
            //如果不存在这个目录，就创建一个
            if (!file.exists()){
            	 file.mkdirs();
            }
            InputStream is=blackfile.getInputStream();
            Date date=new Date();
            SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss");
            String time=formatter.format(date);
            String fileFormat=txtName.substring(txtName.lastIndexOf("."),txtName.length()).toLowerCase();
            txtName=time+fileFormat;
            OutputStream outfile=new FileOutputStream(uploadUrl+"/"+txtName);
            int len=0;
            byte[] buff=new byte[2048];
            while ((len=is.read(buff))>0) {
                outfile.write(buff, 0, len);
            }
            outfile.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("uploadBlackUserTxt method start");
        return txtName;
	}

	@Override
	public List<Map<String, Object>> readBlackUserTxt(String txtUrl) throws ServiceException {
		return null;
	}

	@Override
	public int insertBlackUsers(int visitId, Map<String, Object> condition) throws ServiceException {
	   logger.info("insertBlackUsers method start");
		if (logger.isDebugEnabled()){
			//logger.debug("visitId:"+visitId);
			//logger.debug("condition:"+condition);
		}
		int result=-1;
		boolean bool = Boolean.valueOf(Config.message.get("icsStatusNew").toString());
		if(bool){
			List<Map<String,Object>> list = (List<Map<String, Object>>) condition.get("LIST");
			List<UserFilter> list_userFilter = new ArrayList<UserFilter>();
			for(Map map:list){
				UserFilter userFilter = new UserFilter();
				userFilter.setFilterUser(String.valueOf(map.get("Filter_User")));
				userFilter.setPolicyId(Integer.parseInt(String.valueOf(condition.get("Policy_Id"))));
				userFilter.setUserStatus(Integer.parseInt(String.valueOf(condition.get("User_Status"))));
				userFilter.setInvalidTime(String.valueOf(condition.get("Invalid_Time")));
				userFilter.setEffectTime(String.valueOf(condition.get("Effect_Time")));
				userFilter.setCreateUser(Integer.parseInt(String.valueOf(condition.get("Create_User"))));
				userFilter.setCreateTime(String.valueOf(condition.get("Create_Time")));
				list_userFilter.add(userFilter);
			}
			System.out.println("LIST----------------------------------SIZE------------------------------------------: "+list_userFilter.size());
			if (list_userFilter.size()>50) {
				/*int count = (list_userFilter.size()%50==0)?(list_userFilter.size()%50):(list_userFilter.size()%50+1);
				for(int i=1; i<=count;i++){
					List<UserFilter> list_users = new ArrayList<UserFilter>();
					if((list_userFilter.size()-i*50)>=50){
						list_users = list_userFilter.subList((i-1)*50,i*50-1);
					}else{
						list_users = list_userFilter.subList((i-1)*50,list_userFilter.size()-1);
					}
					DataPacketUserFilter dpuf = new DataPacketUserFilter(list_users,0);
					logger.info("创建对象：dpuf");
					int packSize = dpuf.getPacketSize();
					System.out.println("packSize ----------------------------------|||-------------------------------: " +packSize);
					IMDataPacket imdp = imClient.sendDataMsg(5002,dpuf);
					int IMpackLength = imdp.getDataLength();
					System.out.println("IMpack Length--------------------------------___-------------------------------: " +IMpackLength);
					logger.info("创建对象：imdp");
					result = imdp.getFunctionCode();
					System.out.println("importUser返回码-----------------------------------------------------------------: "+result);
					System.out.println("imdp.toString()-------------------------收到的---------IM协议包---------------------------: "+imdp.toString());
					dpuf.rspDecode(imdp.getRespDataPacketBytes());
					logger.info("返回结果--rspDecode： "+dpuf.toString());
					*//*if (result!=0){
						result = 0;
						break ;
					}else{
						result =(result==0)?1:0;
					}*//*
					result = 1;
				}*/
				/**
				 * 之前大批量黑名单循环发给ICS入库，导致ICS处理不完全，现超过50条直接入库，再让ICS同步生效
				 */
				try {
					result=blacklistBusiness.insertBlackUsers(condition);
					IMSyncClient imClient = new IMSyncClient();
					try{
						imClient.createSession();  //连接icss
						IMDataPacket imdpk  = imClient.sendDataMsg(5034); //创建对象imdp
						result=imdpk.getFunctionCode();
						System.out.println("ICS 返回状态码-------------------------------------------------------： "+result);
					}catch(Exception e){
						logger.error(e.toString());
						return -1;
					}finally{
						imClient.closeSession();
					}
				} catch (BusinessException e) {
					e.printStackTrace();
					logger.error(e);
					return 0;
				}
			}else{
				IMSyncClient imClient = new IMSyncClient();
				try{
					imClient.createSession();
					logger.info("连接ics");
					DataPacketUserFilter dpuf = new DataPacketUserFilter(list_userFilter,0);
					logger.info("创建对象：dpuf");
					int packSize = dpuf.getPacketSize();
					System.out.println("packSize--------------------------------------------------------------------------: "+packSize);
					IMDataPacket imdp = imClient.sendDataMsg(5002,dpuf);
					logger.info("创建对象：imdp");
					result = imdp.getFunctionCode();
					System.out.println("importUserFiltersssss-----------------------------------------------------------------: "+result);
					System.out.println("imdp.toString()-------------------------收到的---------IM协议包---------------------------: "+imdp.toString());
					dpuf.rspDecode(imdp.getRespDataPacketBytes());
					logger.info("返回结果：rspDecode");
					result =(result==0)?1:0;
				}catch (Exception e){
					e.printStackTrace();
					logger.error(e);
					return 0;
				}finally{
					imClient.closeSession();
				}
			}
		}else{
			try {
				result=blacklistBusiness.insertBlackUsers(condition);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error(e);
				return 0;
			}
		}
		logger.info("insertBlackUsers method end");
		return result;
	}

	@Override
	public int insertBlackUrls(int visitId, Map<String, Object> condition) throws ServiceException {
		logger.info("insertBlackUrls method start");
		if (logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		int result=-1;
		boolean bool = Boolean.valueOf(Config.message.get("icsStatusNew").toString());
		if(bool){
			List<Map<String,Object>> list = (List<Map<String, Object>>) condition.get("LIST");
			List<UrlFilter> list_urlFilter = new ArrayList<UrlFilter>();
			for(Map map:list){
				UrlFilter urlFilter = new UrlFilter();
				urlFilter.setUrlId(0);
				urlFilter.setUrlStatus(1);
				urlFilter.setUrlDomain(String.valueOf(map.get("Url_Domain")));
				String urlPath = map.get("Url_Path")==null?"":String.valueOf(map.get("Url_Path"));
				urlFilter.setUrlPath(urlPath);
				urlFilter.setCreateUser(Integer.parseInt(String.valueOf(condition.get("Create_User"))));
				urlFilter.setCreateTime(String.valueOf(condition.get("Create_Time")));
				list_urlFilter.add(urlFilter);
			}
			if(list_urlFilter.size()>50) {
				/*int count = (list_urlFilter.size() % 50 == 0) ? (list_urlFilter.size() % 50) : (list_urlFilter.size() % 50 + 1);
				for (int i = 1; i <= count; i++) {
					List<UrlFilter> list_urls = new ArrayList<UrlFilter>();
					if ((list_urlFilter.size() - i * 50) >= 50) {
						list_urls = list_urlFilter.subList((i - 1) * 50, i * 50 - 1);
					} else {
						list_urls = list_urlFilter.subList((i - 1) * 50, list_urlFilter.size() - 1);
					}
					System.out.println("list_urls 50------------------------------------------------------------: " + list_urls);
					DataPacketUrlFilter dpuf = new DataPacketUrlFilter(list_urls, 0);
					logger.info("创建对象：dpuf");
					IMDataPacket imdp = imClient.sendDataMsg(5004, dpuf);
					int IMpackLength = imdp.getDataLength();
					System.out.println("IMpackLength-----------------------------------------------------------------: " + IMpackLength);
					logger.info("创建对象：imdp");
					result = imdp.getFunctionCode();
					System.out.println("importUser返回码-----------------------------------------------------------------: " + result);
					System.out.println("imdp.toString()-------------------------收到的---------IM协议包---------------------------: " + imdp.toString());
					dpuf.rspDecode(imdp.getRespDataPacketBytes());
					logger.info("返回结果--rspDecode： " + dpuf.toString());
				*//*if (result!=0){
					result = 0;
					break ;
				}else{
					result =(result==0)?1:0;
				}*//*
					result = 1;
				}*/
				try {
					result=blacklistBusiness.insertBlackUrls(condition);
					IMSyncClient imClient = new IMSyncClient();
					try{
						imClient.createSession();  //连接icss
						IMDataPacket imdpk  = imClient.sendDataMsg(5034); //创建对象imdp
						result=imdpk.getFunctionCode();
						System.out.println("ICS 返回状态码-------------------------------------------------------： "+result);
					}catch(Exception e){
						logger.error(e.toString());
						return -1;
					}finally{
						imClient.closeSession();
					}
				} catch (BusinessException e) {
					e.printStackTrace();
					logger.error(e);
					return 0;
				}
			}else{
				IMSyncClient imClient = new IMSyncClient();
				try{
					imClient.createSession();
					logger.info("连接ics");
					DataPacketUrlFilter dpuf = new DataPacketUrlFilter(list_urlFilter, 0);
					logger.info("创建对象：dpuf");
					int packSize = dpuf.getPacketSize();
					System.out.println("packSize--------------------------------------------------------------------------: "+packSize);
					IMDataPacket imdp = imClient.sendDataMsg(5004,dpuf);
					logger.info("创建对象：imdp");
					result = imdp.getFunctionCode();
					System.out.println("importUserFiltersssss-----------------------------------------------------------------: "+result);
					System.out.println("imdp.toString()-------------------------收到的---------IM协议包---------------------------: "+imdp.toString());
					dpuf.rspDecode(imdp.getRespDataPacketBytes());
					logger.info("返回结果：rspDecode");
					result =(result==0)?1:0;
				}catch (Exception e){
					e.printStackTrace();
					logger.error(e);
					return 0;
				}finally{
					imClient.closeSession();
				}
			}
		}else {
			try {
				result=blacklistBusiness.insertBlackUrls(condition);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error(e);
				return 0;
			}
		}
		logger.info("insertBlackUrls method end");
		return result;
	}

	@Override
	public int getBlackUrlcount(Map<String, Object> condition) throws MapperException {
		logger.info("getBlackUrlcount method start");
		if(logger.isDebugEnabled()){
			logger.debug("condition: "+condition);
		}
		int result = 0;
		try {
			result = blacklistBusiness.getBlackUrlcount(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("getBlackUrlcount method start");
		return result;
	}

}
