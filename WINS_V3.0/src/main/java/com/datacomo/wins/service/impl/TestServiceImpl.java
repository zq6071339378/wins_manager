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
import com.datacomo.wins.business.TestBusiness;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.TestService;

/**
 * @author zhaizhanpo
 *
 */
@Service("testService")
public class TestServiceImpl implements TestService {

	private static Logger logger = Logger.getLogger(TestServiceImpl.class.getName());
	@Autowired
	TestBusiness testBusiness;
	/* (non-Javadoc)
	 * @see com.hotdata.dsp.service.TestService#testInfo(int, int)
	 */
	@Override
	public int testInfo(int visitId, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.service.TestService#deleteTestById(int, int)
	 */
	@Override
	public int deleteTestById(int visitId, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.service.TestService#deleteTestByCondtion(int, java.util.Map)
	 */
	@Override
	public int deleteTestByCondtion(int visitId, Map<String, Object> condition)
			throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hotdata.dsp.service.TestService#findTestByCondtion(int, int, java.util.Map)
	 */
	@Override
	public Map<String, Object> findTestsByCondtion(int visitId, int id,
			Map<String, Object> condition) throws ServiceException {
		logger.info("findTestByCondtion method start"); 
		if(logger.isDebugEnabled()){
			logger.debug("args:visitId="+visitId);
			logger.debug("condition："+condition);
		}
		Map<String, Object> result = new HashMap<String,Object>();;
		List<Map<String,Object>> list = null;
		try {
			if(condition!=null){
				list = testBusiness.findByCondition(visitId, condition);
				int count = testBusiness.countByCondition(visitId, condition);
				if(condition.containsKey("page")){
					Pagination page =  (Pagination)condition.get("page");
					page.setTotalCount(count);
					page.setCurrentPage(page.getStart()/page.getLimit()+1);
					result.putAll(condition);
					result.put("tests", list);
					result.put("page", page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("findTestByCondtion method end"); 
		return result;
	}

}
