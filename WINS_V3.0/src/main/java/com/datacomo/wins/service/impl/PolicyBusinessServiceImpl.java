/**
 * 
 */
package com.datacomo.wins.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.datacomo.wins.business.CustomerManageBusiness;
import com.datacomo.wins.business.PolicySynchStatusBusiness;
import com.datacomo.wins.exception.BusinessException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacomo.wins.base.socket.client.IMSyncClient;
import com.datacomo.wins.base.socket.im.IMDataPacket;
import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.PolicyBusiness;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.PolicyBusinessService;

/**
 * @author zhangming
 *
 */
@Service("PolicyBusinessService")
public class PolicyBusinessServiceImpl implements PolicyBusinessService {

	private static Logger logger = Logger.getLogger(PolicyBusinessServiceImpl.class.getName());
	@Autowired
	PolicyBusiness policyBusiness;
	@Autowired
	PolicySynchStatusBusiness policySynchStatusBusiness;
	@Autowired
	CustomerManageBusiness customerManageBusiness;
	/**
	 * 策略列表信息
	 */
	@Override
	public Map<String, Object> findPolicyList(int visitId,Map<String, Object> condition) throws ServiceException {
		logger.info("findGroupList method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyBusiness.findPolicyList(visitId, condition);
				int count = policyBusiness.countByPolicyCondition(condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("POLICYLIST", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findGroupList method end"); 
		return result;
	}
	/**
	 * 策略列表信息
	 */
	@Override
	public Map<String, Object> findPolicyListEn(int visitId,Map<String, Object> condition) throws ServiceException {
		logger.info("findGroupList method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = policyBusiness.findPolicyListEn(visitId, condition);
				int count = policyBusiness.countByPolicyConditionEn(condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("POLICYLIST", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findGroupList method end"); 
		return result;
	}
	
	
	@Override
	public Map<String, Object> singlePolicyInfo(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findGroupList method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				result = policyBusiness.singlePolicyInfo(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findGroupList method end"); 
		return result;
	}
	@Override
	public Map<String, Object> singlePolicyGroup(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("singlePolicyGroup method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		try {
			if(condition!=null){
				result = policyBusiness.singlePolicyGroup(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyGroup method end"); 
		return result;
	}

	@Override
	public Map<String, Object> singlePolicyIPS(int visitId, Map<String, Object> condition) throws ServiceException {
		logger.info("singlePolicyGroup method start");
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = null;
		try {
			if(condition!=null){
				result = policyBusiness.singlePolicyIPS(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyGroup method end");
		return result;
	}

	@Override
	public Map<String, Object> singlePolicyCustomer(int visitId, Map<String, Object> condition) throws ServiceException {
		logger.info("singlePolicyCustomer method start");
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = null;
		try {
			if(condition!=null){
				result = policyBusiness.singlePolicyCustomer(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyCustomer method end");
		return result;
	}

	@Override
	public Map<String, Object> singlePolicyArea(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("singlePolicyGroup method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		try {
			if(condition!=null){
				result = policyBusiness.singlePolicyArea(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyGroup method end"); 
		return result;
	}


	@Override
	public Map<String, Object> singlePolicyKeyWord(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("singlePolicyGroup method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				result = policyBusiness.singlePolicyKewWord(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyGroup method end"); 
		return result;
	}
	
	
	@Override
	public Map<String, Object> findAllIndustry(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("singlePolicyGroup method start");
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		
		try {
			if(condition!=null){
				result = policyBusiness.findAllIndustry(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyGroup method end");
		return result;
	}


	@Override
	public Map<String, Object> singlePolicyUrlDomain(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("singlePolicyGroup method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				result = policyBusiness.singlePolicyUrlDomain(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyGroup method end"); 
		return result;
	}


	/**
	 * 全局策略信息
	 */
	@Override
	public Map<String, Object> getGlobalInfo(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findGroupList method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		try {
			result = policyBusiness.getGlobalInfo(visitId, condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findGroupList method end"); 
		return result;
	}


	@Override
	public int updateGlobalInfo(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById start");
		int result=0;
		try {
			result=policyBusiness.updateGlobalInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return 0;
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById end");
		return result;
	}

	@Override
	public int deletePolicyById(int id) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById start");
		try {
			policyBusiness.deletePolicyById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById end");
		return 0;
	}
	@Override
	public Map<String, Object> singlePolicyPushTime(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("singlePolicyGroup method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				result = policyBusiness.singlePolicyPushTime(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyGroup method end"); 
		return result;
	}
	@Override
	public Map<String, Object> singlePolicyOutlineWeb(int visitId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("singlePolicyGroup method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				result = policyBusiness.singlePolicyOutlineWeb(visitId, condition);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("singlePolicyGroup method end"); 
		return result;
	}
	@Override
	public int creatPolicy(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById start");
		int result=0;
		try {
			result=policyBusiness.creatPolicy(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById end");
		return result;
	}
	@Override
	public int updataPolicyOne(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById start");
		int result=0;
		try {
			result=policyBusiness.updataPolicyOne(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById end");
		return result;
	}
	@Override
	public int updataPolicyTwo(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById start");
		int result=0;
		try {
			result=policyBusiness.updataPolicyTwo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById end");
		return result;
	}
	@Override
	public int updataPolicyThree(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById start");
		int result=0;
		try {
			result=policyBusiness.updataPolicyThree(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deleteGroupById end");
		return result;
	}
	
	@Override
	public int policyAddGroup(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddGroup start");
		int result=0;
		try {
			result=policyBusiness.policyAddGroup(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddGroup end");
		return result;
	}
	@Override
	public int policyDeleteGroup(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddGroup start");
		int result=0;
		try {
			result=policyBusiness.policyDeleteGroup(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddGroup end");
		return result;
	}
	@Override
	public int policyAddCity(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddCity start");
		int result=0;
		try {
			result=policyBusiness.policyAddCity(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddCity end");
		return result;
	}
	@Override
	public int policyDeleteCity(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddCity start");
		int result=0;
		try {
			result=policyBusiness.policyDeleteCity(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddCity end");
		return result;
	}
	@Override
	public int policyAddTime(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddTime start");
		int result=0;
		try {
			result=policyBusiness.policyAddTime(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddTime end");
		return result;
	}
	
	@Override
	public int policyDeleteTime(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddTime start");
		int result=0;
		try {
			result=policyBusiness.policyDeleteTime(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddTime end");
		return result;
	}
	
	@Override
	public int policyAddUrlDomain(Map<String, Object> condition)
			throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddUrlDomain start");
		int result=0;
		try {
			result=policyBusiness.policyAddUrlDomain(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddUrlDomain end");
		return result;
	}
	@Override
	public int policyDeleteUrlDomain(Map<String, Object> condition)
			throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddUrlDomain start");
		int result=0;
		try {
			result=policyBusiness.policyDeleteUrlDomain(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddUrlDomain end");
		return result;
	}
	@Override
	public int policyAddOtherUrl(Map<String, Object> condition)
			throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddUrlDomain start");
		int result=0;
		try {
			result=policyBusiness.policyAddOtherUrl(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddUrlDomain end");
		return result;
	}
	@Override
	public int policyDeleteOtherUrl(Map<String, Object> condition)
			throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyDeleteOtherUrl start");
		int result=0;
		try {
			result=policyBusiness.policyDeleteOtherUrl(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyDeleteOtherUrl end");
		return result;
	}
	@Override
	public int policyAddKeyWord(Map<String, Object> condition)
			throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddKeyWord start");
		int result=0;
		try {
			result=policyBusiness.policyAddKeyWord(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddKeyWord end");
		return result;
	}
	@Override
	public int policyAddCustomer(Map<String, Object> condition,int policyId){
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddCustomer start");
		int result=0;
		try {
			result=policyBusiness.policyAddCustomer(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddCustomer end");
		return result;
	}

	@Override
	public int policyDeleteCustomer(int policyId) throws ServiceException {
		logger.info("policyDeleteCustomer method start");
		if(logger.isDebugEnabled()){
			logger.debug("policyId : "+policyId);
		}
		int result=-1;
		try {
			result = customerManageBusiness.deleteInfoById(policyId);
		} catch (BusinessException e) {
			e.printStackTrace();
			return -1;
		}
		logger.info("policyDeleteCustomer method start");
		return result;
	}

	@Override
	public int policyDeleteKeyWord(Map<String, Object> condition){
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddKeyWord start");
		int result=0;
		try {
			result=policyBusiness.policyDeleteKeyWord(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddKeyWord end");
		return result;
	}

	@Override
	public int deletePolicy(Map<String, Object> condition)
			throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deletePolicy start");
		int result=0;
		try {
			result=policyBusiness.deletePolicy(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" deletePolicy end");
		return result;
	}


	@Override
	public int updateStatus(Map<String, Object> condition) throws ServiceException {
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddTime start");
		int result=0;
		try {
			result=policyBusiness.updateStatus(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(PolicyBusinessServiceImpl.class.getName()+" policyAddTime end");
		return result;
	}
	
	/**
	 * 同步策略请求到ICS
	 * @return
	 */
	
	public int quiteChangePllicy(){ 
		logger.debug("quiteChangePllicy start");
		int result=-1;
		try{
			IMSyncClient imClient = new IMSyncClient();
			try{
				imClient.createSession();  //连接icss
				IMDataPacket imdpk  = imClient.sendDataMsg(5034); //创建对象imdp
				result=imdpk.getFunctionCode();
				System.out.println("ICS返回码-----------------------------------(success:0)----------------------------------: "+result);
			}catch(Exception e){
				logger.error(e.toString());
				result=-1;
				return result;
			}finally{
				imClient.closeSession();
			}
		}catch(Exception e){
			e.printStackTrace();
			result= -1;
			return result;
		}
		return result;
	}


	@Override
	public List<Map<String, Object>> sysCityList(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("sysCityList method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
		List<Map<String,Object>> list = null;
        try {
            list = policyBusiness.sysCityList(visitId,condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("sysCityList method end");
        return list;
	}


	@Override
	public List<Map<String, Object>> sysCityDistrict(int visitId,
			Map<String, Object> condition) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("sysCityList method start");
        if (logger.isDebugEnabled()) {
            logger.debug("visitId:"+visitId);
            logger.debug("condition:"+condition);
        }
        Map<String, Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		 try {
			 list= policyBusiness.sysCityDistrict(visitId, condition);
	            result.putAll(condition);
	            result.put("LIST"+condition.get("cityId"), list);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e);
	        }
	        logger.info("sysCityList method end");
	        return list;
	}


	@Override
	public List sysIpsList(int visitId, Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("sysIpsList method start");
		if (logger.isDebugEnabled()) {
			logger.debug("visitId:" + visitId);
			logger.debug("condition:" + condition);
		}
		List<Map<String, Object>> result = null;
		try {
			result = policyBusiness.sysIpsList(visitId, condition);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info("sysIpsList method end");
		return result;
	}

	@Override
	public List sysIpsCityList(int visitId, Map<String, Object> condition) throws ServiceException {
		logger.info("sysIpsCityList method start");
		if (logger.isDebugEnabled()) {
			logger.debug("visitId:" + visitId);
			logger.debug("condition:" + condition);
		}
		List<Map<String, Object>> result = null;
		try {
			result = policyBusiness.sysIpsCityList(visitId, condition);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info("sysIpsCityList method end");
		return result;
	}

	@Override
	public int insertIpsInfo(int visitId, Map<String, Object> condition) throws ServiceException {
		logger.info("insertIpsInfo method start");
		if (logger.isDebugEnabled()) {
			logger.debug("visitId:" + visitId);
			logger.debug("condition:" + condition);
		}
		int result= -1;
		try {
			int code = policyBusiness.deleteIpsRelation(condition);
			if (code!=-1){
				result = policyBusiness.insertIpsInfo(condition);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("insertIpsInfo method end");
		return result;
	}
	
	@Override
	public int queryCustomerActivity(int visitId, Map<String, Object> condition)
			throws ServiceException {
		logger.info("insertIpsInfo method start");
		if (logger.isDebugEnabled()) {
			logger.debug("visitId:" + visitId);
			logger.debug("condition:" + condition);
		}
		int result =0;
		try{
			result =policyBusiness.queryCustomerActivity(condition);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("insertIpsInfo method end");
		return result;
	}
	@Override
	public int insertCustomer(int visitId, Map<String, Object> condition) throws ServiceException {
		logger.info("insertIpsInfo method start");
		if (logger.isDebugEnabled()) {
			logger.debug("visitId:" + visitId);
			logger.debug("condition:" + condition);
		}
		int result= -1;
		try {
				result = policyBusiness.insertCustomer(condition);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("insertIpsInfo method end");
		return result;
	}
	@Override
	public int insertActivity(int visitId, Map<String, Object> condition) throws ServiceException {
		logger.info("insertIpsInfo method start");
		if (logger.isDebugEnabled()) {
			logger.debug("visitId:" + visitId);
			logger.debug("condition:" + condition);
		}
		int result= -1;
		try {
				result = policyBusiness.insertActivity(condition);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("insertIpsInfo method end");
		return result;
	}
		/*List<Map<String,Object>> result = new ArrayList<>();
		List<Map<String,Object>> list = null;
		List<Integer> citys = new ArrayList<>();
		 try {
			 list= policyBusiness.sysIpsList(visitId, condition);
			 for (Map<String,Object> map : list){
				 int cityId = Integer.parseInt(String.valueOf(map.get("cityId")));
				 if(!citys.contains(cityId)){
					 citys.add(cityId);
				 }
			 }
			 for (Integer id : citys){
				 Map<String,Object> cityIps = new HashMap<>();
				 List<Map<String,Object>> ips = new ArrayList<>();
				 for(Map<String,Object> map : list){
					 int cityId = Integer.parseInt(String.valueOf(map.get("cityId")));
					 if(cityId==id){
						 ips.add(map);
						 String cityName = String.valueOf(map.get("cityName"));
						 cityIps.put(cityName,ips);
					 }
				 }
				 result.add(cityIps);
			 }
		 } catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
		}
		logger.info("sysCityList method end");
		return result;
	}*/


}
