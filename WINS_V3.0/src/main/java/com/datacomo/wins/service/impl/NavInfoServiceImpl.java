/**
 * 
 */
package com.datacomo.wins.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.NavInfoBusiness;
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.NavInfoService;
import com.datacomo.wins.service.TestService;

/**
 * @author gongkaihui
 *
 */
@Service("navInfoService")
public class NavInfoServiceImpl implements NavInfoService {

	private static Logger logger = Logger.getLogger(NavInfoServiceImpl.class.getName());
	Map<String, Object> result = new HashMap<String,Object>();
	@Autowired
	NavInfoBusiness navInfoBusiness;
	@Override
	public Map<String, Object> findNavByRoleIdAndParentId(int visitId, int id,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findNavByRoleIdAndParentId method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> ma = new HashMap<String,Object>();
		
		try {
			List<Map<String,Object>> map = navInfoBusiness.findByCondition(visitId, condition);
			System.out.println("map----------------------------------------------------------------: "+map);
			for (Map<String,Object> obj:map) {
				if(obj.containsKey("is_leaf") && obj.get("is_leaf").toString()!="0"){
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		List<Map<String, Object>> sublist =new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> sublist2 =new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rlist =new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rlist2 =new ArrayList<Map<String, Object>>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = navInfoBusiness.findByCondition(visitId,condition);
				for(int i=0;i<list.size();i++){
					Map<String, Object> map=list.get(i);
					for(String key:map.keySet()){
						if("menuId".equals(key)){
							Integer navId=(Integer)map.get(key);
							condition.put("roleId", condition.get("roleId"));
							condition.put("parentId",navId);
							sublist=navInfoBusiness.findByCondition(visitId, condition);
							for(int j=0;j<sublist.size();j++){
								Map<String, Object> map2=sublist.get(j);
								if(map.containsKey("isLeaf") && !map.get("isLeaf").toString().equals("1")){
									if("menuId".equals(key)){
										Integer navId2=(Integer)map2.get(key);
										condition.put("roleId", condition.get("roleId"));
										condition.put("parentId",navId2);
										sublist2=navInfoBusiness.findByCondition(visitId, condition);
										map2.put("subNav2", sublist2);
										rlist2.add(map2);
									}
								}
							}
							map.put("subNav", sublist);
							rlist.add(map);
							break;
						}
						
					}
				}
				result.put("lists", rlist);
				result.put("lists2", rlist2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findNavByRoleIdAndParentId method end"); 
		return result;
	}
	
	
	@Override
	public Map<String, Object> findNavByRoleIdAndParentIdZh(int visitId, int id,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findNavByRoleIdAndParentId method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();
		Map<String, Object> ma = new HashMap<String,Object>();
		
		try {
			List<Map<String,Object>> map = navInfoBusiness.findZHByConditionZH(visitId, condition);
			
			navInfoBusiness.findZHByConditionZH(visitId, condition);
			for (Map<String,Object> obj:map) {
				if(obj.containsKey("is_leaf") && obj.get("is_leaf").toString()!="0"){
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		List<Map<String, Object>> sublist =new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> sublist2 =new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rlist =new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rlist2 =new ArrayList<Map<String, Object>>();
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = navInfoBusiness.findZHByConditionZH(visitId, condition);
				for(int i=0;i<list.size();i++){
					Map<String, Object> map=list.get(i);
					for(String key:map.keySet()){
						if("menuId".equals(key)){
							Integer navId=(Integer)map.get(key);
							condition.put("roleId", condition.get("roleId"));
							condition.put("parentId",navId);
							sublist=navInfoBusiness.findZHByConditionZH(visitId, condition);
							
							
							for(int j=0;j<sublist.size();j++){
								Map<String, Object> map2=sublist.get(j);
								if(map.containsKey("isLeaf") && !map.get("isLeaf").toString().equals("1")){
									if("menuId".equals(key)){
										Integer navId2=(Integer)map2.get(key);
										condition.put("roleId", condition.get("roleId"));
										condition.put("parentId",navId2);
										sublist2=navInfoBusiness.findZHByConditionZH(visitId, condition);
										map2.put("subNav2", sublist2);
										rlist2.add(map2);
									}
								}
							}
							
							
							map.put("subNav", sublist);
							rlist.add(map);
							break;
						}
						
					}
				}
			
				result.put("lists", rlist);
				result.put("lists2", rlist2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findNavByRoleIdAndParentId method end"); 
		return result;
	}
	
	/*private List<Map<String,Object>>  subnav(int visitId, int parentId,Map<String, Object> condition){
		
		List<Map<String,Object>> mapList = navInfoBusiness.findByCondition(visitId, condition);
		for (int i=0;i<mapList.size();i++) {
			Map<String,Object> map=mapList.get(i);
			if(!map.containsKey("isLeaf") && map.get("isLeaf").toString()!="0"){
				condition.put("parentId",map.get("parentId"));
				List<Map<String,Object>> sub = this.subnav(visitId, parentId, condition);
				map.put("sub", sub);
			}
			mapList.set(i, map);
		}
		return mapList;
	} */
	
	/**
	 * [{1,sub[{11,a},{12,b,sub[{121,d},{122,e}]}]},
	 * 
	 * {2}]
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	/*@Override
	public Map<String, Object> findNavByRoleIdAndParentId(int visitId, int parentId,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findNavByRoleIdAndParentId method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		List<Map<String,Object>> mapList = navInfoBusiness.findByCondition(visitId, condition);
		for (int i=0;i<mapList.size();i++) {
			Map<String,Object> map=mapList.get(i);
			if(map.containsKey("isLeaf") && !map.get("isLeaf").toString().equals("1")){
				condition.put("parentId",map.get("menuId"));
				Map<String,Object> sub = this.findNavByRoleIdAndParentId(visitId, parentId, condition);
				map.put("sub", sub);
			}
			mapList.set(i, map);
			result.put("lists", mapList);
		}
		logger.info("findNavByRoleIdAndParentId method end");
		return result;
	}*/
}
