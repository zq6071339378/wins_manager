package com.datacomo.wins.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.datacomo.wins.exception.MapperException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datacomo.wins.business.PolicyBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.mapper.PolicyBusinessMapper;



@Repository("PolicyBusiness")
public class PolicyBusinessImpl implements PolicyBusiness {
	
	private static Logger logger = Logger.getLogger(PolicyBusinessImpl.class.getName());
	@Autowired
	private PolicyBusinessMapper policyBusinessMapper;

	@Override
	public int insertInfo(Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int visitId, int id) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCondition(int visitId, Map<String, Object> condition)
			throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> getInfo(int visitId, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getInfoByCondition(int visitId,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findByCondition(int visitId,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateInfo(int visitId, Map<String, Object> condition, int id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByCondition(int visitId, Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isExist(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 查询策略列表
	 */
	@Override
	public List<Map<String, Object>> findPolicyList(int visitId,
			Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" findPolicyList start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyBusinessMapper.findPolicyList(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" findPolicyList end");
		return result;
	}
	
	@Override
	public List<Map<String, Object>> findPolicyListEn(int visitId,
			Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" findPolicyList start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyBusinessMapper.findPolicyListEn(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" findPolicyList end");
		return result;
	}
	
	@Override
	public Map<String, Object> singlePolicyInfo(int visitId,
			Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyInfo start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			result = policyBusinessMapper.singlePolicyInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyInfo end");
		return result;
	}
	
	@Override
	public Map findAllIndustry(int visitId,
			Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" findAllIndustry start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		
		try {
			List<Map<String, Object>> list=null;
			list = policyBusinessMapper.findAllIndustry(condition);
			result.put("LIST", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" findPolicyList end");
		return result;
	}
	

	@Override
	public Map singlePolicyGroup(int visitId, Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyGroup start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			List<Map<String, Object>> list =null;
			list = policyBusinessMapper.singlePolicyGroup(condition);
			result.put("LIST", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyGroup end");
		return result;
	}

	@Override
	public Map singlePolicyIPS(int visitId, Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyIPS start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			List<Map<String, Object>> list =null;
			list =  policyBusinessMapper.singlePolicyIPS(condition);
			result.put("LIST", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}

		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyIPS end");
		return result;
	}

	@Override
	public Map singlePolicyCustomer(int visitId, Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyCustomer start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			List<Map<String, Object>> list =null;
			list =  policyBusinessMapper.singlePolicyCustomer(condition);
			result.put("LIST", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyCustomer end");
		return result;
	}

	@Override
	public Map singlePolicyArea(int visitId, Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyArea start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			List<Map<String, Object>> list =null;
			list =  policyBusinessMapper.singlePolicyArea(condition);
			result.put("LIST", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyArea end");
		return result;
	}

	@Override
	public Map singlePolicyKewWord(int visitId, Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyKewWord start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			List<Map<String, Object>> list =null;
			list = policyBusinessMapper.singlePolicyKewWord(condition);
			result.put("LIST", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyKewWord end");
		return result;
	}

	@Override
	public Map singlePolicyUrlDomain(int visitId, Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyUrlDomain start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			List<Map<String, Object>> list =null;
			list  = policyBusinessMapper.singlePolicyUrlDomain(condition);
			result.put("LIST", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyUrlDomain end");
		return result;
	}

	@Override
	public Map singlePolicyPushTime(int visitId, Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyPushTime start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			List<Map<String, Object>> list =null;
			list = policyBusinessMapper.singlePolicyPushTime(condition);
			result.put("LIST", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyPushTime end");
		return result;
	}

	@Override
	public Map singlePolicyOutlineWeb(int visitId, Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyOutlineWeb start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			List<Map<String, Object>> list =null;
			list = policyBusinessMapper.singlePolicyOutlineWeb(condition);
			result.put("LIST", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" singlePolicyPushTime end");
		return result;
	}

	/**
	 * 全局策略查询
	 */
	
	@Override
	public Map<String, Object> getGlobalInfo(int visitId,
			Map<String, Object> condition) {
		logger.info(PolicyBusinessImpl.class.getName()+" getGlobalInfo start");
		if(logger.isDebugEnabled()){
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		Map<String, Object> result =new HashMap();
		try {
			result = policyBusinessMapper.getGlobalInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=null;
		}
		
		logger.info(PolicyBusinessImpl.class.getName()+" getGlobalInfo end");
		return result;
	}

	
	@Override
	public int updateGlobalInfo(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			int flag=policyBusinessMapper.checkPass(condition);
			if(flag==1){
				Map map=(Map) condition.get("condition");
				map.remove("checkPass");
				result = policyBusinessMapper.updateGlobalInfo(condition);
			}else{
				result =2;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	/* (non-Javadoc)
	 * @see com.hotdata.dsp.business.BaseBusiness#creatPolicy(java.util.Map)
	 */
	@Override
	public int creatPolicy(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			  policyBusinessMapper.creatPolicy(condition);
      		  if(condition.containsKey("policy_id")){
      			  result = Integer.valueOf(condition.get("policy_id").toString());
      		  }else{
      			  result = -2;//参数错误
      		  }
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	@Override
	public int updataPolicyOne(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.updataPolicyOne(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	@Override
	public int updataPolicyTwo(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.updataPolicyTwo(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	@Override
	public int updataPolicyThree(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.updataPolicyThree(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	
	@Override
	public int policyAddGroup(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.policyAddGroup(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	@Override
	public int policyDeleteGroup(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.policyDeleteGroup(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	@Override
	public int policyAddTime(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result =policyBusinessMapper.policyAddTime(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	
	@Override
	public int policyDeleteTime(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result =policyBusinessMapper.policyDeleteTime(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	
	@Override
	public int policyAddCity(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddCity start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.policyAddCity(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddCity end");
		return result;
	}
	@Override
	public int policyDeleteCity(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddCity start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.policyDeleteCity(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddCity end");
		return result;
	}
	
	@Override
	public int policyAddUrlDomain(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddUrlDomain start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result =policyBusinessMapper.policyAddUrlDomain(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddUrlDomain end");
		return result;
	}

	@Override
	public int policyAddOtherUrl(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddOtherUrl start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result =policyBusinessMapper.policyAddOtherUrl(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddUrlDomain end");
		return result;
	}
	
	@Override
	public int policyDeleteOtherUrl(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyDeleteOtherUrl start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result =policyBusinessMapper.policyDeleteOtherUrl(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddUrlDomain end");
		return result;
	}
	@Override
	public int policyDeleteUrlDomain(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddUrlDomain start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result =policyBusinessMapper.policyDeleteUrlDomain(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddUrlDomain end");
		return result;
	}
	
	@Override
	public int policyAddKeyWord(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddKeyWord start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.policyAddKeyWord(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddKeyWord end");
		return result;
	}
	@Override
	public int policyAddCustomer(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddCustomer start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.policyAddCustomer(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddCustomer end");
		return result;
	}
	@Override
	public int policyDeleteKeyWord(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddKeyWord start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.policyDeleteKeyWord(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddKeyWord end");
		return result;
	}

	@Override
	public int deletePolicy(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" deletePolicy start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result=policyBusinessMapper.deletePolicy(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" deletePolicy end");
		return result;
	}

	@Override
	public int updateStatus(Map<String, Object> condition)
			throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddCity start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.updateStatus(condition);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" policyAddCity end");
		return result;
	}
	/**
	 * 查询策略数量
	 */
	@Override
	public int countByPolicyCondition(Map<String, Object> condition) throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.countByPolicyCondition(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	/**
	 * 查询策略数量
	 */
	@Override
	public int countByPolicyConditionEn(Map<String, Object> condition) throws BusinessException {
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition start");
		if(logger.isDebugEnabled()){
			logger.debug("condition:"+condition);
		}
		int result =0;
		try {
			result = policyBusinessMapper.countByPolicyConditionEn(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=0;
		}
		logger.info(PolicyBusinessImpl.class.getName()+" countByPolicyCondition end");
		return result;
	}
	@Override
	public int deletePolicyById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, Object>> sysCityList(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		 logger.info("sysCityList method start");
	        if (logger.isDebugEnabled()) {
	            logger.debug("visitId:"+visitId);
	            logger.debug("condition:"+condition);
	        }
	        List<Map<String, Object>> result =null;
	        try {
	            result = policyBusinessMapper.sysCityList(condition);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e);
	            return null;
	        }
	        logger.info("sysCityList method end");
	        return result;
	}

	@Override
	public List<Map<String, Object>> sysCityDistrict(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		 logger.info("sysCityList method start");
	        if (logger.isDebugEnabled()) {
	            logger.debug("visitId:"+visitId);
	            logger.debug("condition:"+condition);
	        }
	        List<Map<String, Object>> result =null;
	        try {
	            result = policyBusinessMapper.sysCityDistrict(condition);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e);
	            return null;
	        }
	        logger.info("sysCityList method end");
	        return result;
	}
	@Override
	public List<Map<String, Object>> sysIpsList(int visitId,
			Map<String, Object> condition) throws BusinessException {
		// TODO Auto-generated method stub
		 logger.info("sysCityList method start");
	        if (logger.isDebugEnabled()) {
	            logger.debug("visitId:"+visitId);
	            logger.debug("condition:"+condition);
	        }
	        List<Map<String, Object>> result =null;
	        try {
	            result = policyBusinessMapper.sysIpsList(condition);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e);
	            return null;
	        }
	        logger.info("sysCityList method end");
	        return result;
	}

	@Override
	public List<Map<String, Object>> sysIpsCityList(int visitId, Map<String, Object> condition) throws BusinessException {
		logger.info("sysIpsCityList method start");
		if (logger.isDebugEnabled()) {
			logger.debug("visitId:"+visitId);
			logger.debug("condition:"+condition);
		}
		List<Map<String, Object>> result =null;
		try {
			result = policyBusinessMapper.sysIpsCityList(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
		logger.info("sysIpsCityList method end");
		return result;
	}

	@Override
	public int insertIpsInfo(Map<String, Object> condition){
		logger.info("insertIpsInfo method start");
		if (logger.isDebugEnabled()) {
			logger.debug("condition:" + condition);
		}
		int result= -1;
		try {
			result = policyBusinessMapper.insertIpsInfo(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("insertIpsInfo method end");
		return result;
	}
	
	@Override
	public int queryCustomerActivity(Map<String, Object> condition){
		logger.info("insertIpsInfo method start");
		if (logger.isDebugEnabled()) {
			logger.debug("condition:" + condition);
		}
		int result= 0;
		try {
			result = policyBusinessMapper.queryCustomerActivity(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			return 0;
		}
		logger.info("insertIpsInfo method end");
		return result;
	}
	@Override
	public int insertActivity(Map<String, Object> condition){
		logger.info("insertIpsInfo method start");
		if (logger.isDebugEnabled()) {
			logger.debug("condition:" + condition);
		}
		int result= -1;
		try {
			result = policyBusinessMapper.insertActivity(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("insertIpsInfo method end");
		return result;
	}
	@Override
	public int insertCustomer(Map<String, Object> condition){
		logger.info("insertIpsInfo method start");
		if (logger.isDebugEnabled()) {
			logger.debug("condition:" + condition);
		}
		int result= -1;
		try {
			result = policyBusinessMapper.insertCustomer(condition);
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("insertIpsInfo method end");
		return result;
	}

	@Override
	public int deleteIpsRelation(Map<String, Object> condition) throws BusinessException {
		logger.info("deleteIpsRelation method start");
		if (logger.isDebugEnabled()) {
			logger.debug("condition:" + condition);
		}
		int result = 0;
		try {
			result = policyBusinessMapper.deleteIpsRelation(condition); //先删除之前的ips关系
		} catch (MapperException e) {
			e.printStackTrace();
			logger.error(e);
			return -1;
		}
		logger.info("deleteIpsRelation method end");
		return result;
	}
}
