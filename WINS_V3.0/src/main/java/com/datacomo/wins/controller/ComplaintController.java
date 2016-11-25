package com.datacomo.wins.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datacomo.wins.bean.ResultCodeConstants;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.ComplaintService;

/**
 * 用户投诉管理控制器
 * @author liwenjie
 *
 */
@Controller
@RequestMapping(value="/complaint")
public class ComplaintController extends BaseController {
	private static Logger logger = Logger.getLogger(ComplaintController.class.getName());
	@Autowired
	ComplaintService complaintService;

	
/**
 * 	
 * @return 用户投诉管理总信息
 */
	@RequestMapping(value="complaint")
	public String complaint(){
		return "complaint/complaint";
	}

	@RequestMapping(value="showComplaint")
	public String showComplaint(){
		logger.info("ComplaintController showComplaint method start");
		//生成表名
		String time=this.getRequest().getParameter("time");
		this.getCondition().put("createTime",time);
		DateFormat sf=new SimpleDateFormat("yyyy-MM");
		try {
			Date date=sf.parse(time);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String newtime=formatter.format(date);
			String pushtableName="push_record_t_"+newtime;
			String clicktableName="click_record_t_"+newtime;
			//表名
			this.getCondition().put("pushtableName", pushtableName);
			this.getCondition().put("clicktableName", clicktableName);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//查询条件
		String PushUser=this.getRequest().getParameter("PushUser");
		if(PushUser!=""&&PushUser!=null){
			this.getCondition().put("pushUser",PushUser);
		}
		try {
			Map<String, Object> result = complaintService.showComplaint(this.getVisitId(),this.getCondition());
			this.getRequest().setAttribute("result", result);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("ComplaintController showComplaint method end");
		return "complaint/complaint";
	}
/**
 * 	
 * @return 账户推送详情
 */
	@RequestMapping(value="showPush")
	public String showPush(){
		logger.info("ComplaintController showPush method start");
		//生成表名
		String policyId = this.getRequest().getParameter("policyId");
		this.getCondition().put("policyId",policyId);
		String time=this.getRequest().getParameter("time");
		this.getCondition().put("time", time);
		DateFormat sf=new SimpleDateFormat("yyyy-MM");
		try {
			Date date=sf.parse(time);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String newtime=formatter.format(date);
			String pushtableName="push_record_t_"+newtime;
			String clicktableName="click_record_t_"+newtime;
			//表名
			this.getCondition().put("pushtableName", pushtableName);
			this.getCondition().put("clicktableName", clicktableName);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//查询条件
		String PushUser=this.getRequest().getParameter("PushUser");
		this.getCondition().put("PushUser",PushUser);
		try {
			Map<String, Object> result = complaintService.showPush(this.getVisitId(),this.getCondition());
			result.put("policyId",policyId);
			this.getRequest().setAttribute("result",result);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("ComplaintController showPush method end");
		return "complaint/clickPush";
	}
	/**
	 *
	 * @return 用户点击详情
	 */
	@RequestMapping(value="showClick")
	public String showClick(){
		logger.info("ComplaintController showClick method start");
		//查询条件
		//生成表名
		String policyId = this.getRequest().getParameter("policyId");
		this.getCondition().put("policyId",policyId);
		String time=this.getRequest().getParameter("time");
		this.getCondition().put("time", time);
		DateFormat sf=new SimpleDateFormat("yyyy-MM");
		try {
			Date date=sf.parse(time);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String newtime=formatter.format(date);
			String pushtableName="push_record_t_"+newtime;
			String clicktableName="click_record_t_"+newtime;
			//表名
			this.getCondition().put("pushtableName", pushtableName);
			this.getCondition().put("clicktableName", clicktableName);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String PushUser=this.getRequest().getParameter("PushUser");
		this.getCondition().put("PushUser",PushUser);
		try {
			Map<String, Object> result = complaintService.showClick(this.getVisitId(),this.getCondition());
			result.put("policyId",policyId);
			this.getRequest().setAttribute("result", result);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("ComplaintController showClick method end");
		return "complaint/click";
	}

/**
 * 
 * @return 用户订购详情
 */
	@RequestMapping(value="showOrder")
	public String showOrder(){
		logger.info("ComplaintController showOrder method start");
		//生成表名
		String time=this.getRequest().getParameter("time");
		this.getCondition().put("time", time);
		DateFormat sf=new SimpleDateFormat("yyyy-MM");
		try {
			Date date=sf.parse(time);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
			String newtime=formatter.format(date);
			String pushtableName="push_record_t_"+newtime;
			String clicktableName="click_record_t_"+newtime;
			//表名
			this.getCondition().put("pushtableName", pushtableName);
			this.getCondition().put("clicktableName", clicktableName);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//查询条件
		String PushUser=this.getRequest().getParameter("PushUser");
		try {
			if(StringUtils.isNotBlank(PushUser)){
				PushUser=new String(PushUser.getBytes("ISO-8859-1"), "utf-8");
				this.getCondition().put("PushUser",PushUser);
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Map<String, Object> result = complaintService.showOrder(this.getVisitId(),this.getCondition());
			this.getRequest().setAttribute("result", result);
			this.getModel().setDesc("succeed");
		} catch (ServiceException e) {
			this.getModel().setCode(ResultCodeConstants.ERROR);
			this.getModel().setDesc("abnormal operation");
		}
		logger.info("ComplaintController showOrder method end");
		return "complaint/clickOrder";
	}
	

}
