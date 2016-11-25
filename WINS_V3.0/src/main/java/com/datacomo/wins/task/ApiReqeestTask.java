package com.datacomo.wins.task;

import com.datacomo.wins.bean.NewThread;
import com.datacomo.wins.controller.Config;
import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysIndustryService;

import com.datacomo.wins.service.SysInterestLabelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiongbin on 2016/9/21.
 */
@Component
public class ApiReqeestTask {
    private static Logger logger = Logger.getLogger(ApiReqeestTask.class.getName());
    @Autowired
    private  SysIndustryService sysIndustryService;
    @Autowired
    private SysInterestLabelService sysInterestLabelService;

    /**
     * 中文版取消定时请求Adtime数据入库
     */
    @Scheduled(cron = "0 0 17 * * ?")  //每天17点执行定时任务 数据入库
    public void getAllLabelsJob(){
        logger.info("Task: getAllLabelsJob method start");
        boolean bool =Boolean.valueOf(Config.message.get("isEnglish").toString());
        if(bool){
            int flag1 = -2;
            int flag2= -2;
            flag1 = sysIndustryService.startExcuteRequest();
            if(flag1==1){ //第三方数据更新成功
                try {
                    sysIndustryService.updateIndustryLabel();
                    sysIndustryService.updateMediaLabel();
                    sysInterestLabelService.updateAllInterestLabel();
                    sysInterestLabelService.updateAllUserByInterestLabel();
                } catch (ServiceException e) {
                    e.printStackTrace();
                    logger.error(e);
                }
            }else if(flag1==0){ //数据还没更新,发送更新请求，直到flag=1
                // 创建线程
                NewThread _sub_thread = new NewThread();
                Thread t1 = new Thread(_sub_thread);
                t1.start();
                int _result = 0;
                while(true) {
                    _result = NewThread.get_result();
                    if (_result == 1) {
                        try {
                            sysIndustryService.updateIndustryLabel();
                            sysIndustryService.updateMediaLabel();
                            sysInterestLabelService.updateAllInterestLabel();
                            sysInterestLabelService.updateAllUserByInterestLabel();
                        } catch (ServiceException e) {
                            e.printStackTrace();
                            logger.error(e);
                        }
                        break;
                    }else if(_result==2){
                        logger.debug("超时---------超时");
                        break;
                    }else{
                        // 继续等待
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            logger.error(e);
                        }
                    }
                }
            }else{
                logger.debug("请求第三方数据入库失败");
            }
        }
        logger.info("Task: getAllLabelsJob method end");
    }

    public static void main(String[] args) {
        long timeOut = System.currentTimeMillis();
        System.out.println("当前系统毫秒数："+timeOut);
        NewThread _sub_thread = new NewThread();
        Thread t1 = new Thread(_sub_thread);
        t1.start();
        int _result = 0;
        while(true) {
            _result = NewThread.get_result();
            if (_result == 1) {
                // 入库
                break;
            }else if(_result==2){
                logger.debug("超时---------超时");
                break;
            }
            else{
                // 继续等待
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
