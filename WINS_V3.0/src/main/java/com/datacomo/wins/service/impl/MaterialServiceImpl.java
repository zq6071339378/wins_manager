/**
 * 
 */
package com.datacomo.wins.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacomo.wins.bean.Pagination;
import com.datacomo.wins.business.MaterialPageBusiness;
import com.datacomo.wins.business.MaterialTemplateBusiness;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.MaterialService;

/**
 * @author zhaizhanpo
 *
 */
@Service
public class MaterialServiceImpl implements MaterialService {

	private static Logger logger = Logger.getLogger(MaterialServiceImpl.class.getName());
	@Autowired
	MaterialTemplateBusiness materialTemplateBusiness;
	@Autowired
	MaterialPageBusiness materialPageBusiness;
	@Override
	public int deleteMaterialTemplateById(int visitId, int id) {
		logger.info(MaterialServiceImpl.class.getName()+" deleteMaterialTemplateByIddeleteMaterialTemplateById method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("id："+id);
		}
		int result = 0;
		try {
			result = materialTemplateBusiness.deleteById(visitId, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=-1;
		}
		logger.info(MaterialServiceImpl.class.getName()+" materialTemplateBusiness method end"); 
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		return result;
	}
	@Override
	public int deleteMaterialPageById(int visitId, int id) {
		logger.info(MaterialServiceImpl.class.getName()+" deleteMaterialPageById method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("id："+id);
		}
		int result = 0;
		try {
			result = materialPageBusiness.deleteById(visitId, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=-1;
		}
		logger.info(MaterialServiceImpl.class.getName()+" deleteMaterialPageById method end"); 
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		return result;
	}
	@Override
	public Map<String, Object> findMaterialTemplateByCondtion(int visitId,
			int id, Map<String, Object> condition) throws ServiceException {
		logger.info(MaterialServiceImpl.class.getName()+" findMaterialTemplateByCondtion method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = materialTemplateBusiness.findByCondition(visitId, condition);
					result.putAll(condition);
					result.put("materials", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(MaterialServiceImpl.class.getName()+" findMaterialTemplateByCondtion  method end"); 
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		return result;
	}
	@Override
	public Map<String, Object> findMaterialPageByCondtion(int visitId, int id,
			Map<String, Object> condition) throws ServiceException {
		logger.info(MaterialServiceImpl.class.getName()+" findMaterialPageByCondtion method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = materialPageBusiness.findByCondition(visitId, condition);
				if(condition.containsKey("page")){
					int count = materialPageBusiness.countByCondition(visitId, condition);
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("materials", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info(MaterialServiceImpl.class.getName()+" findMaterialPageByCondtion  method end"); 
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		return result;
	}
	@Override
	public int insertMaterialTemplate(int visitId, Map<String, Object> condition)
			throws ServiceException {
		logger.info(MaterialServiceImpl.class.getName()+" insertMaterialTemplate method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
		}
		int result = 0;
		try {
			result = materialTemplateBusiness.insertInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=-1;
		}
		logger.info(MaterialServiceImpl.class.getName()+" insertMaterialTemplate method end"); 
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		return result;
	}
	@Override
	public int insertMaterialPage(int visitId, Map<String, Object> condition)
			throws ServiceException {
		logger.info(MaterialServiceImpl.class.getName()+" insertMaterialPage method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
		}
		int result = 0;
		try {
			result = materialPageBusiness.insertInfo(condition);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result=-1;
		}
		logger.info(MaterialServiceImpl.class.getName()+" insertMaterialPage method end"); 
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		return result;
	}
	@Override
	public Map<String, Object> getMaterialTemplateById(int visitId, int id)
			throws ServiceException {
		logger.info(MaterialServiceImpl.class.getName()+" getMaterialTemplateById method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
		}
		Map<String,Object> result = null;
		try {
			result = materialTemplateBusiness.getInfo(visitId, id);
		} catch (Exception e) {
			logger.error(e);
		}
		logger.info(MaterialServiceImpl.class.getName()+" getMaterialTemplateById method end"); 
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		return result;
	}
	@Override
	public Map<String, Object> findPage(int visitId, int visitId2,
			Map<String, Object> condition) throws ServiceException {
		logger.info(MaterialServiceImpl.class.getName()+" findPage method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = materialPageBusiness.findByCondition(visitId, condition);
					result.putAll(condition);
					result.put("materials", list);
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		if(logger.isDebugEnabled()){
			logger.debug("result:"+result);
		}
		return result;
	}
	

}
